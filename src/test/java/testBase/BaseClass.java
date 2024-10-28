package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import pageObjects.Loginpage;

public class BaseClass {
    public static WebDriver driver;
    public Logger logger;
    public Properties properties;

    public BaseClass() {
        logger = LogManager.getLogger(this.getClass());
    }

    @BeforeSuite
    @Parameters({"os", "browser"})
    public void launchingApplication(String os, String browser) throws IOException {
        properties = new Properties();
        try (FileReader fileReader = new FileReader("./src/test/resources/config.properties")) {
            properties.load(fileReader);
        }

        if (driver == null) {
            initializeDriver(os, browser);
            configureDriver();
            performLogin();
        }
    }

    private void initializeDriver(String os, String browser) throws IOException {
        if (properties.getProperty("execution_env").equalsIgnoreCase("remote")) {
            DesiredCapabilities capabilities = getDesiredCapabilities(os, browser);
            driver = new RemoteWebDriver(new URL("http://192.168.21.200:4444/wd/hub"), capabilities);
        } else {
            driver = getLocalDriver(browser);
        }
    }

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

        if (browser.equalsIgnoreCase("brave")) {
            ChromeOptions braveOptions = configureBrowserOptions("brave");
            capabilities.setCapability(ChromeOptions.CAPABILITY, braveOptions);
        } else if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = configureBrowserOptions("chrome");
            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        }
        return capabilities;
    }

    private ChromeOptions configureBrowserOptions(String browser) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run in headless mode

        if (browser.equalsIgnoreCase("brave")) {
            options.setBinary("D:\\Mahipal\\NYX.today\\BraveBrowser\\Application\\brave.exe");
            options.addArguments("--disable-blink-features=AutomationControlled");
        }

        setChromeDownloadPreferences(options);
        return options;
    }

    public WebDriver getLocalDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
            case "brave":
                ChromeOptions options = configureBrowserOptions(browser);
                return new ChromeDriver(options);
            case "edge":
                return new EdgeDriver();
            case "firefox":
                return new FirefoxDriver();
            case "safari":
                return new SafariDriver();
            default:
                logger.error("Invalid browser name: " + browser);
                throw new IllegalArgumentException("Invalid browser: " + browser);
        }
    }

    private void configureDriver() {
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get(properties.getProperty("appURL"));
        driver.manage().window().maximize();
    }

    private void performLogin() {
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
    }

    // Method to capture screenshot
    public String captureScreen(String testName) throws IOException {
        if (driver == null) {
            logger.error("WebDriver is not initialized. Cannot capture screenshot.");
            return null;
        }

        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        String targetFilePath = Paths.get(System.getProperty("user.dir"), "Screenshots", testName + "_" + timestamp + ".png").toString();

        // Ensure the screenshots directory exists
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

    // Helper method to set download preferences for Chrome and Brave
    private void setChromeDownloadPreferences(ChromeOptions options) {
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", "/home/abhipatil0692/Automation_Testing/PostImagesDownload");
        options.setExperimentalOption("prefs", chromePrefs);
    }
}
