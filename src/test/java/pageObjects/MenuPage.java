package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MenuPage extends Basepage{
	public MenuPage(ThreadLocal<WebDriver> driver) {
        super(driver);
    }		Actions act=new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		//POM Menu options full view button
		
		@FindBy(xpath="//button[@class='bg-black text-white rounded-full p-1 absolute -right-3 top-20 z-20']")
		WebElement FullviewMenuButton;
		
		//POM More button
		@FindBy(xpath="//button[@class='transition-all rounded-full group-[.is-opened]:w-full group-[.is-opened]:max-w-36 flex items-center']")
		WebElement MoreButton;
		
		//POM profile button
		@FindBy(xpath="//span[@class='px-2 group-[.is-opened]:inline-block overflow-hidden w-auto duration-1000']")
		WebElement ProfileButtonMenu;
		
		//POM Invite Users
		@FindBy(xpath="//button[normalize-space()='+ Invite Users']")
		WebElement Inviteuserbutton;
		
		//POM Home button
		@FindBy(xpath="//span[normalize-space()='Home']")
		WebElement HomeButton;
		
		//POM Assets button
		@FindBy(xpath="//span[contains(@class,'group-[.is-opened]:inline-block ml-2 overflow-hidden w-auto py-2 duration-500 text-nyx-yellow')]")
		WebElement AssetsButton;
		
		//POM Integrations
		@FindBy(xpath="//span[normalize-space()='Integrations']") 
		WebElement IntegrationsButton;
		
		
		//POM ImageCraft AI
		@FindBy(xpath="//span[normalize-space()='ImageCraft AI']") 
		WebElement ImageCraftAIButton;
		
		
		//POM VideoVista AI
		@FindBy(xpath="//span[normalize-space()='VideoVista AI']")
		WebElement VideoVistaAIButton;
		
		
		//POM Campulse AI
		@FindBy(xpath="//span[normalize-space()='Campulse AI']") 
		WebElement CampulseAIButton;
		
		//POM LyricGenius AI
		@FindBy(xpath="//span[normalize-space()='LyricGenius AI']") 
		WebElement LyricGeniusAIButton;
		
		
		//POM Settings
		@FindBy(xpath="//span[contains(@class,'group-[.is-opened]:inline-block ml-2 overflow-hidden w-auto duration-1000')]")
		WebElement SettingsButton;
		
		//POM Upgrade button
		@FindBy(xpath="//p[normalize-space()='Upgrade']") 
		WebElement UpgradeButton;
		
		//POM Slide Bar
		@FindBy(xpath="//span[@class='absolute']") 
		WebElement SlideBarindicator;
		//p[@class='text-white']
		@FindBy(xpath="//div[@class='inline-flex flex-col w-full p-5 pb-0 relative group z-10 transition-all ease-in duration-1000 is-opened']") 
		WebElement clickoncurrentplan;
		
		public void clickFullviewMenuButton() throws NoSuchElementException {
		    try {
		        js.executeScript("arguments[0].click();", FullviewMenuButton);
		    } catch (NoSuchElementException e) {
		        throw new NoSuchElementException("Fullview Menu Button not found: " + e.getMessage());
		    } catch (Exception e) {
		        throw new RuntimeException("An unexpected error occurred while clicking Fullview Menu Button: " + e.getMessage());
		    }
		}

		public void clickMoreButton() throws NoSuchElementException {
		    try {
		        MoreButton.click();
		    } catch (NoSuchElementException e) {
		        throw new NoSuchElementException("More Button not found: " + e.getMessage());
		    } catch (Exception e) {
		        throw new RuntimeException("An unexpected error occurred while clicking More Button: " + e.getMessage());
		    }
		}

		public void clickProfileButtonMenu() throws NoSuchElementException {
		    try {
		        ProfileButtonMenu.click();
		    } catch (NoSuchElementException e) {
		        throw new NoSuchElementException("Profile Button Menu not found: " + e.getMessage());
		    } catch (Exception e) {
		        throw new RuntimeException("An unexpected error occurred while clicking Profile Button Menu: " + e.getMessage());
		    }
		}

		public void clickInviteUserButton() throws NoSuchElementException {
		    try {
		        Inviteuserbutton.click();
		    } catch (NoSuchElementException e) {
		        throw new NoSuchElementException("Invite User Button not found: " + e.getMessage());
		    } catch (Exception e) {
		        throw new RuntimeException("An unexpected error occurred while clicking Invite User Button: " + e.getMessage());
		    }
		}

		public void clickHomeButton() throws NoSuchElementException {
		    try {
		        act.moveToElement(HomeButton).click().perform();
		    } catch (NoSuchElementException e) {
		        throw new NoSuchElementException("Home Button not found: " + e.getMessage());
		    } catch (Exception e) {
		        throw new RuntimeException("An unexpected error occurred while clicking Home Button: " + e.getMessage());
		    }
		}

		
		
		
		
		
		
		
		public void clickAssetsButton() {
		    try {
		        AssetsButton.click();
		    } catch (NoSuchElementException e) {
		        throw new NoSuchElementException("Assets Button not found: " + e.getMessage());
		    } catch (Exception e) {
		        throw new RuntimeException("An unexpected error occurred while clicking the Assets Button: " + e.getMessage());
		    }
		}		
		
		public void clickIntegrationsButton() {
		    try {
		        IntegrationsButton.click();
		    } catch (NoSuchElementException e) {
		        throw new NoSuchElementException("Integrations Button not found: " + e.getMessage());
		    } catch (Exception e) {
		        throw new RuntimeException("An unexpected error occurred while clicking the Integrations Button: " + e.getMessage());
		    }
		}

		public void clickImageCraftAIButton() {
		    try {
		        js.executeScript("arguments[0].click();", ImageCraftAIButton);
		    } catch (NoSuchElementException e) {
		        throw new NoSuchElementException("Image Craft AI Button not found: " + e.getMessage());
		    } catch (Exception e) {
		        throw new RuntimeException("An unexpected error occurred while clicking the Image Craft AI Button: " + e.getMessage());
		    }
		}

		public void clickVideoVistaAIButton() {
		    try {
		    	js.executeScript("arguments[0].click();", VideoVistaAIButton);
		        //VideoVistaAIButton.click();
		    } catch (NoSuchElementException e) {
		        throw new NoSuchElementException("Video Vista AI Button not found: " + e.getMessage());
		    } catch (Exception e) {
		        throw new RuntimeException("An unexpected error occurred while clicking the Video Vista AI Button: " + e.getMessage());
		    }
		}

		public void clickCampulseAIButton() {
		    try {
		        CampulseAIButton.click();
		    } catch (NoSuchElementException e) {
		        throw new NoSuchElementException("Campulse AI Button not found: " + e.getMessage());
		    } catch (Exception e) {
		        throw new RuntimeException("An unexpected error occurred while clicking the Campulse AI Button: " + e.getMessage());
		    }
		}

		public void clickLyricGeniusAIButton() {
		    try {
		        LyricGeniusAIButton.click();
		    } catch (NoSuchElementException e) {
		        throw new NoSuchElementException("Lyric Genius AI Button not found: " + e.getMessage());
		    } catch (Exception e) {
		        throw new RuntimeException("An unexpected error occurred while clicking the Lyric Genius AI Button: " + e.getMessage());
		    }
		}

		public void clickSettingsButton() {
		    try {
		        SettingsButton.click();
		    } catch (NoSuchElementException e) {
		        throw new NoSuchElementException("Settings Button not found: " + e.getMessage());
		    } catch (Exception e) {
		        throw new RuntimeException("An unexpected error occurred while clicking the Settings Button: " + e.getMessage());
		    }
		}

		public void clickUpgradeButton() {
		    try {
		        UpgradeButton.click();
		    } catch (NoSuchElementException e) {
		        throw new NoSuchElementException("Upgrade Button not found: " + e.getMessage());
		    } catch (Exception e) {
		        throw new RuntimeException("An unexpected error occurred while clicking the Upgrade Button: " + e.getMessage());
		    }
		}
		
		public boolean SlideBar() throws Exception {
		    try {
		        // Click the FullviewMenuButton without waiting
		        FullviewMenuButton.click();

		        // Scroll down to ensure the element is visible
		        JavascriptExecutor js = (JavascriptExecutor) driver;
		        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		        // Define the custom ExpectedCondition to check the slider's left percentage
		        ExpectedCondition<Boolean> sliderCondition = driver -> {
		            WebElement element = driver.findElement(By.xpath("//*[contains(@style,'will-change: auto; left:')]"));
		            String styleValue = element.getAttribute("style");

		            if (styleValue != null && styleValue.contains("left:")) {
		                String percentageString = styleValue.split("left:")[1].split("%")[0].trim();
		                try {
		                    double percentage = Double.parseDouble(percentageString);
		                    return percentage > 0;
		                } catch (NumberFormatException e) {
		                    // Throwing a custom exception with a more specific message
		                    throw new IllegalArgumentException("Error parsing percentage value: " + e.getMessage(), e);
		                }
		            }
		            return false;
		        };

		        // Wait until the slider's left percentage is greater than 0%
		        Boolean isSliderLoaded = wait.until(sliderCondition);

		        if (isSliderLoaded) {
		            System.out.println("The slider's 'left' percentage is greater than 0%.");
		            return true;
		        } else {
		            System.out.println("The slider's 'left' percentage is 0% or less.");
		            return false;
		        }

		    } catch (Exception e) {
		        throw new Exception("Error while checking the slider bar: " + e.getMessage(), e);
		    }
		}        
		}
