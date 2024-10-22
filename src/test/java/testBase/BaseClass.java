package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
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
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
            driver = getLocalDriver(browser);  // Call for local browser initialization
        }
    }

    public DesiredCapabilities getDesiredCapabilities(String os, String browser) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setPlatform(os.equalsIgnoreCase("windows") ? Platform.WIN11 : Platform.MAC);
        capabilities.setBrowserName(browser.equalsIgnoreCase("brave") ? "chrome" : browser);

        if (browser.equalsIgnoreCase("brave")) {
            ChromeOptions braveOptions = new ChromeOptions();
            braveOptions.setBinary("D:\\Mahipal\\NYX.today\\BraveBrowser\\Application\\brave.exe");
            braveOptions.addArguments("--disable-blink-features=AutomationControlled");
            setChromeDownloadPreferences(braveOptions);
            capabilities.setCapability(ChromeOptions.CAPABILITY, braveOptions);
        } else if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            setChromeDownloadPreferences(chromeOptions);
            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        }
        return capabilities;
    }

    public WebDriver getLocalDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                setChromeDownloadPreferences(chromeOptions); // Set download preferences
                return new ChromeDriver(chromeOptions);

            case "brave":
                ChromeOptions braveOptions = new ChromeOptions();
                braveOptions.setBinary("D:\\Mahipal\\NYX.today\\BraveBrowser\\Application\\brave.exe");
                braveOptions.addArguments("--disable-blink-features=AutomationControlled");
                setChromeDownloadPreferences(braveOptions); // Reuse the method for Brave
                return new ChromeDriver(braveOptions);

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
        if(loginPage.isHomePageDisplayed()) {
            logger.info("Login successful.");
            loginPage.ClickonSkipButton();
        } else  {
            logger.error("Login failed due to timeout.");
        }
    }

    // Method to capture screenshot
    public String captureScreen(String testName) throws IOException {
        if (driver == null) {
            logger.error("WebDriver is not initialized. Cannot capture screenshot.");
            return null;
        }

        String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        String targetFilePath = System.getProperty("user.dir") + "\\Screenshots\\" + testName + "_" + timestamp + ".png";

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
        chromePrefs.put("download.default_directory", "D:\\Mahipal\\NYX.today\\New folder");
        options.setExperimentalOption("prefs", chromePrefs);
    }
}
