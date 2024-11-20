package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;  // WebDriverManager import
import pageObjects.Loginpage;

public class BaseClass {
    
    // ThreadLocal to ensure WebDriver is thread-safe
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public ThreadLocal<String> downloadDir = new ThreadLocal<>(); // Unique download directory per thread
    public Logger logger;
    public Properties properties;

    // Constructor
    public BaseClass() {
        logger = LogManager.getLogger(this.getClass());
    }

    // Before Test - Setup
    @BeforeTest
    @Parameters({"os", "browser"})
    public void launchingApplication(String os, String browser) throws IOException {
        loadProperties();
        
        // Generate a unique download directory for each test run
        String uniqueDownloadDir = "C:\\Users\\Public\\JenkinsDownloads" + UUID.randomUUID().toString();
        downloadDir.set(uniqueDownloadDir);
        new File(uniqueDownloadDir).mkdirs();  // Ensure the directory is created

        // Ensure WebDriver initialization is thread-safe using double-checked locking
        if (driver.get() == null) {
            synchronized (BaseClass.class) {
                if (driver.get() == null) {
                    initializeDriver(os, browser, uniqueDownloadDir);
                }
            }
        }

        configureDriver();
        performLogin();
    }

    // Load properties from config file
    private void loadProperties() throws IOException {
        properties = new Properties();
        try (FileReader fileReader = new FileReader("./src/test/resources/config.properties")) {
            properties.load(fileReader);
        }
    }

    // Driver Initialization
    private void initializeDriver(String os, String browser, String uniqueDownloadDir) throws IOException {
        logger.info("Initializing driver with OS: " + os + ", Browser: " + browser);
        
        // WebDriverManager automatically downloads and sets up the correct version of chromedriver
       // WebDriverManager.chromedriver().setup();
        
        // Remote WebDriver setup or Local WebDriver setup
        if ("remote".equalsIgnoreCase(properties.getProperty("execution_env"))) {
            DesiredCapabilities capabilities = getDesiredCapabilities(os, browser, uniqueDownloadDir);
            try {
                driver.set(new RemoteWebDriver(new URL("http://34.47.205.0:4444/wd/hub"), capabilities));
            } catch (MalformedURLException e) {
                logger.error("Invalid remote WebDriver URL.", e);
            }
        } else {
            driver.set(getLocalDriver(browser, uniqueDownloadDir));
        }
    }

    // Desired Capabilities with Download Preferences
    private DesiredCapabilities getDesiredCapabilities(String os, String browser, String uniqueDownloadDir) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browser.equalsIgnoreCase("brave") ? "chrome" : browser);

        switch (os.toLowerCase()) {
            case "windows":
                capabilities.setPlatform(Platform.WINDOWS);
                break;
            case "linux":
                capabilities.setPlatform(Platform.LINUX);
                break;
            default:
                logger.error("Invalid OS name: " + os);
                throw new IllegalArgumentException("Invalid OS: " + os);
        }

        if ("chrome".equalsIgnoreCase(browser) || "brave".equalsIgnoreCase(browser)) {
            ChromeOptions chromeOptions = configureBrowserOptions(browser, uniqueDownloadDir);
            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        }
        return capabilities;
    }

    // Configure Browser Options
    private ChromeOptions configureBrowserOptions(String browser, String uniqueDownloadDir) {
        ChromeOptions options = new ChromeOptions();
        // Set headless mode for running in headless environment (uncomment if needed)
        options.addArguments("--headless");
        // Set window size to avoid issues in headless mode
        options.addArguments("--window-size=1920x1080");

        if ("brave".equalsIgnoreCase(browser)) {
            options.setBinary("D:\\Mahipal\\NYX.today\\BraveBrowser\\Application\\brave.exe");
            options.addArguments("--disable-blink-features=AutomationControlled");
        }

        setChromeDownloadPreferences(options, uniqueDownloadDir);
        return options;
    }

    // Local WebDriver setup
    private WebDriver getLocalDriver(String browser, String uniqueDownloadDir) {
        switch (browser.toLowerCase()) {
            case "chrome":
            case "brave":
                ChromeOptions options = configureBrowserOptions(browser, uniqueDownloadDir);
                return new ChromeDriver(options);
            default:
                logger.error("Invalid browser name: " + browser);
                throw new IllegalArgumentException("Invalid browser: " + browser);
        }
    }

    // Configure WebDriver settings
    private void configureDriver() {
        if (driver.get() != null) {
            driver.get().manage().deleteAllCookies();
            driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
            driver.get().get(properties.getProperty("appURL"));
            // Optionally, maximize the window
            // driver.get().manage().window().maximize();
        } else {
            logger.error("WebDriver is not initialized.");
        }
    }

    // Perform login
    private void performLogin() {
        if (driver.get() != null) {
            WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30));
            Loginpage loginPage = new Loginpage(driver);
            loginPage.sendPhone(properties.getProperty("phone_no"));
            loginPage.sendPassword(properties.getProperty("password"));
            loginPage.clickLogin();

            if (loginPage.isHomePageDisplayed()) {
                logger.info("Login successful.");
                loginPage.clickOnSkipButton();
            } else {
                logger.error("Login failed due to timeout.");
            }
        } else {
            logger.error("WebDriver is not initialized.");
        }
    }

    // Capture Screenshot
    public String captureScreen(String testName) throws IOException {
        if (driver.get() == null) {
            logger.error("WebDriver is not initialized.");
            return null;
        }

        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver.get();
        String targetFilePath = Paths.get(System.getProperty("user.dir"), "Screenshots", testName + "_" + timestamp + ".png").toString();

        new File(Paths.get(System.getProperty("user.dir"), "Screenshots").toString()).mkdirs();

        try {
            File sourceFile = ts.getScreenshotAs(OutputType.FILE);
            File targetFile = new File(targetFilePath);
            sourceFile.renameTo(targetFile);
            return targetFilePath;
        } catch (Exception e) {
            logger.error("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }

    // Set Chrome Download Preferences
    private void setChromeDownloadPreferences(ChromeOptions options, String uniqueDownloadDir) {
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", uniqueDownloadDir);
        options.setExperimentalOption("prefs", chromePrefs);
    }

    // After Test - Clean up
    @AfterTest
    public void tearDown() {
        if (driver.get() != null) {
            driver.get().quit(); // Quit WebDriver after test completion
            logger.info("WebDriver quit successfully.");
        }

        // Deleting the unique download directory after test completion
        String uniqueDownloadDir = downloadDir.get(); // Retrieve the unique download directory
        if (uniqueDownloadDir != null) {
            deleteDirectory(new File(uniqueDownloadDir)); // Delete the directory and its contents
            logger.info("Download directory deleted: " + uniqueDownloadDir);
        }
    }

    // Helper method to delete a directory and its contents
    private void deleteDirectory(File directory) {
        if (directory != null && directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteDirectory(file); // Recursively delete subdirectories
                    } else {
                        file.delete(); // Delete individual file
                    }
                }
            }
            directory.delete(); // Delete the directory itself
        }
    }
}