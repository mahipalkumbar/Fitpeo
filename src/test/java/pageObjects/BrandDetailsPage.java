package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrandDetailsPage extends Basepage {
	public BrandDetailsPage(WebDriver driver) {
		super(driver);
	}
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
	Actions act=new Actions(driver);
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	@FindBy(xpath="//button[@class='bg-[#1D1138] w-auto p-2 text-nyx-yellow rounded-md flex gap-2 text-sm font-medium hover:shadow-gray-800 shadow-none hover:shadow-md']")
	WebElement AddNewbrandbutton;
	
	@FindBy(xpath="//span[@class='text-sm font-medium w-full']")
	List<WebElement> chooseproductinautoadmanager;
	
	@FindBy(xpath="//button[normalize-space()='Next']")
	WebElement NextButtoninBrandingDetailspageAutoAdManager;
	
	//div[text()='Specify campaign objectives']
	@FindBy(xpath="//div[text()='Specify campaign objectives']")
	WebElement setupcampaignpage;
	
	
/*@FindBy(xpath="//button[normalize-space()='Next']")  
WebElement BrandDetailsPageNextButton;

public void ClickOnBrandDetailsPageNextButton() {
	WebElement button= wait.until(ExpectedConditions.elementToBeClickable(BrandDetailsPageNextButton));
	button.click();
}*/
	public void AddNewbrandbutton() throws InterruptedException {
	    try {
	        // Find the Add New Brand button using the implicit wait
	        WebElement button = AddNewbrandbutton; // Assuming this is already defined as a WebElement
	        
	        // Scroll to the button until it is displayed
	        while (true) {
	            try {
	                if (button.isDisplayed()) {
	                    break;  // Exit the loop if the button is found
	                }
	            } catch (Exception e) {
	                js.executeScript("window.scrollBy(0, 500);"); // Scroll down
	                Thread.sleep(1000); // Delay to allow for scrolling
	            }
	        }
	        
	        // Click the button using JavaScript
	        js.executeScript("arguments[0].click();", button);
	        
	        // Print confirmation message
	        System.out.println("Add New Brand button clicked successfully.");
	        
	    } catch (NoSuchElementException e) {
	        System.out.println("Add New button not displayed in Branding details page due to NoSuchElementException.");
	        throw new RuntimeException("Add New button not displayed in Branding details page.", e);
	    } catch (Exception e) {
	        System.out.println("An unexpected error occurred while trying to load the Branding details page.");
	        throw new RuntimeException("An unexpected error occurred while loading Branding details page.", e);
	    }
	}

	public void chooseproductinautoadmanagerselected(String chooseProd) {
	    try {
	        boolean productFound = false;
	        
	        for (WebElement w : chooseproductinautoadmanager) {
	            if (w.getText().equals(chooseProd)) {
	                w.click();
	                productFound = true;
	                System.out.println("Product '" + chooseProd + "' selected successfully.");
	                break; // Exit the loop once the product is found and clicked
	            }
	        }

	        if (!productFound) {
	            System.out.println("Product '" + chooseProd + "' not found in the list.");
	        }
	        
	    } catch (NoSuchElementException e) {
	        System.out.println("An error occurred while trying to select the product: NoSuchElementException.");
	        throw new RuntimeException("An error occurred while trying to select the product.", e);
	    } catch (Exception e) {
	        System.out.println("An unexpected error occurred while selecting the product.");
	        throw new RuntimeException("An unexpected error occurred while selecting the product.", e);
	    }
	}

	
	public void ClickOnNextButtonInBrandingDetailsPageOfAutoAdManager() {
	    try {
	        // Click using JavaScript to ensure it works in all cases
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", NextButtoninBrandingDetailspageAutoAdManager);
	        
	        // Log success
	        System.out.println("Clicked on the Next button in Branding Details page of Auto Ad Manager.");
	    } catch (NoSuchElementException e) {
	        System.out.println("Next button in Branding Details page of Auto Ad Manager not found.");
	        throw new RuntimeException("Next button not found.", e);
	    } catch (Exception e) {
	        System.out.println("An unexpected error occurred while clicking the Next button in Branding Details page of Auto Ad Manager.");
	        throw new RuntimeException("Unexpected error while clicking Next button.", e);
	    }
	}

	
	public boolean SetupCampaignPageIsDisplayed() {
	    try {
	        // Check if the setup campaign page is displayed
	        boolean isDisplayed = setupcampaignpage.isDisplayed();
	        
	        // Log the result
	        System.out.println("Setup Campaign Page is displayed: " + isDisplayed);
	        
	        return isDisplayed;
	    } catch (NoSuchElementException e) {
	        System.out.println("Setup Campaign Page element not found.");
	        return false; // Element is not displayed
	    } catch (Exception e) {
	        System.out.println("An unexpected error occurred while checking if the Setup Campaign Page is displayed.");
	        throw new RuntimeException("Unexpected error while checking Setup Campaign Page display.", e);
	    }
	}

	

}
