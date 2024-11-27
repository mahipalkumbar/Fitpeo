package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseClass {

    public WebDriver driver;
    public Logger logger;
    public Properties properties;
    
    public BaseClass() {
        logger = LogManager.getLogger(this.getClass());
    }

    
    @BeforeSuite
    @Parameters({"browser"})
    public void setUp(String browser) throws IOException {
        loadProperties();
        if ("chrome".equalsIgnoreCase(browser)) {
            driver = initializeChromeDriver();
        } else {
            logger.error("Only Chrome is supported as a browser.");
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        configureDriver();
    }
    private void loadProperties() throws IOException {
        properties = new Properties();
        try (FileReader fileReader = new FileReader("./src/test/resources/config.properties")) {
            properties.load(fileReader);
        }
    }
    private WebDriver initializeChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); 
        return new ChromeDriver(options);
    }
    private void configureDriver() {
        if (driver != null) {
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(20));
            driver.get(properties.getProperty("appURL"));
        } else {
            logger.error("WebDriver is not initialized.");
        }
    }
    public String captureScreen(String testName) throws IOException {
        if (driver == null) {
            logger.error("WebDriver is not initialized.");
            return null;
        }

        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        String screenshotDir = "reports" + File.separator + "Screenshots";
        String fileName = testName + "_" + timestamp + ".png";
        String targetFilePath = Paths.get(screenshotDir, fileName).toString();

        createDirectory(screenshotDir);

        try {
            File sourceFile = ts.getScreenshotAs(OutputType.FILE);
            File targetFile = new File(targetFilePath);
            FileUtils.copyFile(sourceFile, targetFile);
            return targetFilePath.replace("\\", "/");
        } catch (IOException e) {
            logger.error("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
    private void createDirectory(String path) {
        File screenshotFolder = new File(path);
        if (!screenshotFolder.exists() && !screenshotFolder.mkdirs()) {
            logger.error("Failed to create directory: " + path);
        }
    }
    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("WebDriver quit successfully.");
        }
    }
}
