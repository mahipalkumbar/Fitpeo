package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MenuPage extends Basepage{
		public MenuPage(WebDriver driver) {
			super(driver);
		}
	 //WebDriver driver = getDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
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
		
		
		Actions act=new Actions(driver);
		
		public void FullviewMenuButton() {
			FullviewMenuButton.click();
		}
		
		public void MoreButton(){
			MoreButton.click();
		}
		
		public void ProfileButtonMenu(){
			ProfileButtonMenu.click();
		}
		
		public void Inviteuserbutton(){
			Inviteuserbutton.click();
		}
		
		public void HomeButton(){
			act.moveToElement(HomeButton).click().perform();
		//	HomeButton.click();
		}
		
		public void AssetsButton(){
			AssetsButton.click();
		}
		
		public void IntegrationsButton(){
			IntegrationsButton.click();
		}
		
		public void ImageCraftAIButton(){
			ImageCraftAIButton.click();
		}
		
		public void VideoVistaAIButton(){
			VideoVistaAIButton.click();
		}
		
		public void CampulseAIButton(){
			CampulseAIButton.click();
		}
		
		public void LyricGeniusAIButton(){
			LyricGeniusAIButton.click();
		}
		
		public void SettingsButton(){
			SettingsButton.click();
		}
		
		public void UpgradeButton(){
			UpgradeButton.click();
		}
		
		public boolean SlideBar() {
			wait.until(ExpectedConditions.visibilityOf(FullviewMenuButton));
		    FullviewMenuButton.click();

		    try {
		        // Scroll down the page to ensure the element is visible
		        JavascriptExecutor js = (JavascriptExecutor) driver;
		        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		        Thread.sleep(2000);

		        // Define the WebDriverWait and the custom condition
		       // WebDriverWait wait = new WebDriverWait(driver, 10); // Adjust the timeout as needed

		        // Custom ExpectedCondition to wait until 'left' is greater than 0%
		        ExpectedCondition<Boolean> sliderCondition = new ExpectedCondition<Boolean>() {
		            @Override
		            public Boolean apply(WebDriver driver) {
		                WebElement element = driver.findElement(By.xpath("//*[contains(@style,'will-change: auto; left:')]"));
		                String styleValue = element.getAttribute("style");

		                if (styleValue != null && styleValue.contains("left:")) {
		                    String percentageString = styleValue.split("left:")[1].split("%")[0].trim();

		                    try {
		                        // Parse the percentage value and compare it
		                        double percentage = Double.parseDouble(percentageString);
		                        // Check if the percentage is greater than 0%
		                        return percentage > 0;
		                    } catch (NumberFormatException e) {
		                        System.out.println("Error parsing percentage value: " + e.getMessage());
		                        return false;
		                    }
		                }
		                return false;
		            }
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
		        System.out.println("Error while checking the slider bar: " + e.getMessage());
		        return false;
		    }
		}



		
		        
		}
