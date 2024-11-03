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
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import pageObjects.Loginpage;

public class BaseClass {
    public static WebDriver driver;
    public Logger logger;
    public Properties properties;

    // Constructor
    public BaseClass() {
        logger = LogManager.getLogger(this.getClass());
    }

    // Before Suite - Application Launch
    @BeforeSuite
    @Parameters({"os", "browser"})
    public void launchingApplication(String os, String browser) throws IOException {
        loadProperties();
        if (driver == null) {
            initializeDriver(os, browser);
        }
        configureDriver(); // Ensure driver is configured after initialization
        performLogin();
    }

    // Load properties from config file
    private void loadProperties() throws IOException {
        properties = new Properties();
        try (FileReader fileReader = new FileReader("./src/test/resources/config.properties")) {
            properties.load(fileReader);
        }
    }

    // Driver Initialization based on environment
    private void initializeDriver(String os, String browser) throws IOException {
        logger.info("Initializing driver with OS: " + os + ", Browser: " + browser + ", Environment: " + properties.getProperty("execution_env"));
        if ("remote".equalsIgnoreCase(properties.getProperty("execution_env"))) {
            DesiredCapabilities capabilities = getDesiredCapabilities(os, browser);
            try {
                logger.info("Attempting to connect to Remote WebDriver.");
                driver = new RemoteWebDriver(new URL("http://34.131.38.165:4444/wd/hub"), capabilities);
                logger.info("Remote WebDriver session started successfully.");
            } catch (MalformedURLException e) {
                logger.error("Invalid remote WebDriver URL. Check the remote server address.", e);
            } catch (Exception e) {
                logger.error("Failed to create remote WebDriver session.", e);
            }
        } else {
            driver = getLocalDriver(browser);
        }
    }


    // Set Desired Capabilities for Remote WebDriver
    public DesiredCapabilities getDesiredCapabilities(String os, String browser) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browser.equalsIgnoreCase("brave") ? "chrome" : browser);

        switch (os.toLowerCase()) {
            case "windows":
                capabilities.setPlatform(Platform.WIN11);
                break;
            case "linux":
                capabilities.setPlatform(Platform.LINUX);
                break;
            default:
                logger.error("Invalid OS name: " + os);
                throw new IllegalArgumentException("Invalid OS: " + os);
        }

        if (browser.equalsIgnoreCase("chrome") || browser.equalsIgnoreCase("brave")) {
            ChromeOptions chromeOptions = configureBrowserOptions(browser);
            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        }
        return capabilities;
    }

    // Configure Browser Options
    private ChromeOptions configureBrowserOptions(String browser) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run in headless mode
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-gpu");

        if (browser.equalsIgnoreCase("brave")) {
            options.setBinary("D:\\Mahipal\\NYX.today\\BraveBrowser\\Application\\brave.exe");
            options.addArguments("--disable-blink-features=AutomationControlled");
        }

        setChromeDownloadPreferences(options);
        return options;
    }

    // Local WebDriver setup
    public WebDriver getLocalDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
            case "brave":
                ChromeOptions options = configureBrowserOptions(browser);
                return new ChromeDriver(options);
            default:
                logger.error("Invalid browser name: " + browser);
                throw new IllegalArgumentException("Invalid browser: " + browser);
        }
    }

    // Configure driver settings
    private void configureDriver() {
        if (driver != null) { // Check if driver is initialized
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
            driver.get(properties.getProperty("appURL"));
        } else {
            logger.error("WebDriver is not initialized. Cannot configure driver.");
        }
    }

    // Perform login
    private void performLogin() {
        if (driver != null) { // Check if driver is initialized
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            Loginpage loginPage = new Loginpage(driver);
            loginPage.sendPhone(properties.getProperty("phone_no"));
            loginPage.sendPassword(properties.getProperty("password"));
            loginPage.clickLogin();

            if (loginPage.isHomePageDisplayed()) {
                logger.info("Login successful.");
                loginPage.ClickonSkipButton();
            } else {
                logger.error("Login failed due to timeout.");
            }
        } else {
            logger.error("WebDriver is not initialized. Cannot perform login.");
        }
    }

    // Capture Screenshot
    public String captureScreen(String testName) throws IOException {
        if (driver == null) {
            logger.error("WebDriver is not initialized. Cannot capture screenshot.");
            return null;
        }

        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
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

    // Set Download Preferences for Chrome and Brave
    private void setChromeDownloadPreferences(ChromeOptions options) {
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", System.getProperty("user.home") + "/Automation_Testing/PostImagesDownload");
        options.setExperimentalOption("prefs", chromePrefs);
    }

    // After Suite - Clean up
    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Uncomment this line to quit the driver properly
            logger.info("WebDriver quit successfully.");
        } else {
            logger.warn("WebDriver was not initialized. No action taken on quit.");
        }
    }
}
