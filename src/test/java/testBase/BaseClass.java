package testBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import pageObjects.Loginpage;

public class BaseClass {
	public static WebDriver driver;
	public org.apache.logging.log4j.Logger logger;
	public Properties p;
	@BeforeClass
	@Parameters({"os","browser"})
	public void launchingapplication(String os, String browser) throws IOException {
		p = new Properties();
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p.load(file);
	logger= LogManager.getLogger(this.getClass());
	if (driver == null) {
	if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities cap=new DesiredCapabilities();
			
			if(os.equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac")) {
				cap.setPlatform(Platform.MAC);
			}
			else {
				System.out.println("No matching OS");
				return;
			}
			String b = browser.toLowerCase();
	        if (b.equals("chrome")) {
	           cap.setBrowserName("chrome");
	        } else if (b.equals("edge")) {
	        	 cap.setBrowserName("MicrosoftEdge");
	        }else if (b.equals("firefox")) {
	            cap.setBrowserName("firefox");
	        }else if(b.equals("safari")) {
	        	cap.setBrowserName("safari");
	        }else if(b.equals("brave")) {
	        	ChromeOptions braveOptions = new ChromeOptions();
	            braveOptions.setBinary("D:\\Mahipal\\NYX.today\\BraveBrowser\\Application\\brave.exe");
	            braveOptions.addArguments("--disable-blink-features=AutomationControlled");
	            braveOptions.addArguments("--disable-extensions");
	            braveOptions.addArguments("--disable-popup-blocking");
	            braveOptions.addArguments("--disable-notifications");
	            cap.setCapability(ChromeOptions.CAPABILITY, braveOptions);
	        }
	        else {
	            System.out.println("No Matching browser");
	            return;
	        }
			//driver = new FirefoxDriver();
	        driver=new RemoteWebDriver(new URL("http://192.168.21.200:4444/wd/hub"),cap);
		}
		//String browser="chrome";
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
			String b1 = browser.toLowerCase();
	        if (b1.equals("chrome")) {
	        	
	        	/*String downloadFilePath = System.getProperty("user.dir") + "/downloads";
	            
	            // Set Chrome preferences to set the default download location
	            HashMap<String, Object> chromePrefs = new HashMap<>();
	            chromePrefs.put("profile.default_content_settings.popups", 0);
	            chromePrefs.put("download.default_directory", downloadFilePath); // Set custom download path

	            ChromeOptions options = new ChromeOptions();
	            options.setExperimentalOption("prefs", chromePrefs);

	            // Initialize WebDriver with ChromeOptions
	            driver = new ChromeDriver(options);

	                driver = new ChromeDriver(options);*/
	            driver = new ChromeDriver();
	        } else if (b1.equals("edge")) {
	            driver = new EdgeDriver();
	        } else if (b1.equals("firefox")) {
	            driver = new FirefoxDriver();
	        } else if (b1.equals("safari")) {
	            driver = new SafariDriver();
	        } else if(b1.equals("brave")) {
	        	ChromeOptions braveOptions = new ChromeOptions();
	            braveOptions.setBinary("D:\\Mahipal\\NYX.today\\BraveBrowser\\Application\\brave.exe");
	            braveOptions.addArguments("--disable-features=BraveShield");
	            braveOptions.addArguments("--disable-blink-features=AutomationControlled");
	            braveOptions.addArguments("--disable-extensions");
	            braveOptions.addArguments("--disable-popup-blocking");
	            braveOptions.addArguments("--disable-notifications");
	            braveOptions.addArguments("--disable-features=BraveShield,BraveAds,BraveTrackingProtection");
	            braveOptions.addArguments("--disable-popup-blocking");
	            braveOptions.addArguments("--disable-extensions");
	            braveOptions.addArguments("--disable-infobars");
	            String downloadFilePath = System.getProperty("user.dir") + "/downloads";
                Map<String, Object> bravePrefs = new HashMap<>();
                bravePrefs.put("download.default_directory", downloadFilePath);
                bravePrefs.put("download.prompt_for_download", false);
                bravePrefs.put("safebrowsing.enabled", "true");

                braveOptions.setExperimentalOption("prefs", bravePrefs);
	            driver = new ChromeDriver(braveOptions);
	            
	        }
	        else {
	            System.out.println("Invalid browser name :"+browser);
	            return;
		}}
		//driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		Loginpage l=new Loginpage(driver);
		l.sendphone(p.getProperty("phone_no"));//8123342894
		l.sendpassword(p.getProperty("password"));//QW@$zx123
		l.clicklogin();
		WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'ImageCraft AI')]")));
        boolean loginSuccessful = (logoutButton != null);
	}}
	/*@AfterMethod
	public void logouttheapp() {
		
		
	}*/
	@AfterClass
	public void teardown() {
		driver.quit();
		
	}
	/*@BeforeMethod
	public void logintotheapp() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		Loginpage l=new Loginpage(driver);
		l.sendphone(p.getProperty("phone_no"));//8123342894
		l.sendpassword(p.getProperty("password"));//QW@$zx123
		l.clicklogin();
		WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'ImageCraft AI')]")));
        boolean loginSuccessful = (logoutButton != null);
		//Assert.assertTrue(l.homepageisdisplayed());
		
	}*/
	public String captureScreen(String tname) throws IOException{
		String timestamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		File sourcefile=ts.getScreenshotAs(OutputType.FILE);
		String targetfilepath=System.getProperty("user.dir")+"\\Screenshots\\"+tname+"_"+timestamp+".png";
		File targetfile=new File(targetfilepath);
		sourcefile.renameTo(targetfile);
		return targetfilepath;
		
	}

}
