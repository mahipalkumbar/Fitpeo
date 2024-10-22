package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CampaignName extends Basepage{
	public CampaignName(WebDriver driver) {
			super(driver);
		}

@FindBy(xpath="//input[@placeholder='Eg - Super Sale']") 
WebElement Campaigname;
@FindBy(xpath="//input[@placeholder='Eg - Campaign for Diwali']") 
WebElement Objective;
@FindBy(xpath="//div[text()='Social Media Creatives']") 
WebElement mediachannelspage;

@FindBy(xpath="//div[text()='Add Target Group']/following::button[2]") 
WebElement nextc;

//@FindBy(xpath="//div[text()='Choose Product']/following-sibling::div//span[contains(@class,'text')]")
//List<WebElement> Chooseproduct;
//@FindBy(xpath="//div[@class='flex flex-col gap-5 w-full']//div[@class='w-full flex flex-wrap gap-3']//div[@class='truncate text-xs font-semibold']")
//List<WebElement> choosetg;

JavascriptExecutor js = (JavascriptExecutor) driver;

WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));

public void Campaigname(String cname) {
	//wait.until(ExpectedConditions.visibilityOf(Campaigname));
    try {
        // Check if the Campaigname element is displayed before sending keys
        if (Campaigname.isDisplayed()) {
            Campaigname.sendKeys(cname);
            // Log action performed
            System.out.println("Entered campaign name: " + cname);
        } else {
            throw new NoSuchElementException("Campaign name field not displayed.");
        }
    } catch (NoSuchElementException e) {
        System.out.println("Campaign Name Text field not displayed in the Add Product page: " + e.getMessage());
        throw new RuntimeException("Campaign Name Text field not displayed in the Add Product page", e);
    } catch (Exception e) {
        System.out.println("An unexpected error occurred while entering the campaign name.");
        throw new RuntimeException("An unexpected error occurred in the Add Product page while entering campaign name", e);
    }
}

public void Objective(String obj) {
    try {
        // Check if the Objective element is displayed before sending keys
        if (Objective.isDisplayed()) {
            Objective.sendKeys(obj);
            // Log action performed
            System.out.println("Entered objective: " + obj);
        } else {
            throw new NoSuchElementException("Objective field not displayed.");
        }
    } catch (NoSuchElementException e) {
        System.out.println("Objective field not displayed in the page: " + e.getMessage());
        throw new RuntimeException("Objective field not displayed in the page", e);
    } catch (Exception e) {
        System.out.println("An unexpected error occurred while entering the objective.");
        throw new RuntimeException("An unexpected error occurred while entering the objective", e);
    }
}

public boolean nextc() {
    JavascriptExecutor js = (JavascriptExecutor) driver;

    // Scroll to the bottom of the page
    js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

    try {
        // Click the next button using JavaScript
        js.executeScript("arguments[0].click();", nextc);
        
        // Wait until the media channels page is visible
        //wait.until(ExpectedConditions.visibilityOf(mediachannelspage));
        
        // Log the action performed
        System.out.println("Clicked on the next button and navigated to the media channels page.");

        return mediachannelspage.isDisplayed();
    } catch (NoSuchElementException e) {
        System.out.println("Next button not found: " + e.getMessage());
        throw new RuntimeException("Next button not found", e);
    } catch (Exception e) {
        System.out.println("An unexpected error occurred while trying to navigate to the media channels page: " + e.getMessage());
        throw new RuntimeException("An unexpected error occurred while trying to navigate to the media channels page", e);
    }
}
public void Chooseproduct(String chhoseprod) {
	try {
        // Log the chosen TG name from Excel
        System.out.println("From Excel sheet: Chose Product Name: " + chhoseprod);
        
        // Construct the XPath for the desired element
        String expath = "//span[text()='" + chhoseprod + "']";
        System.out.println("Constructed XPath: " + expath);
        
        // Find the WebElement using the constructed XPath
        WebElement ele = driver.findElement(By.xpath(expath));
        System.out.println("Element found: " + ele.getText());
        
        // Click on the found WebElement
        ele.click();
        System.out.println("Clicked on the element: " + chhoseprod);
        
    } catch (NoSuchElementException e) {
        System.out.println("Error: Element not found for Product Name: " + chhoseprod);
        throw new RuntimeException("Product Name not found: " + chhoseprod, e);
        
    } catch (ElementClickInterceptedException e) {
        System.out.println("Error: Unable to click on the element: " + chhoseprod);
        throw new RuntimeException("Click intercepted for Product Name: " + chhoseprod, e);
        
    } catch (Exception e) {
        System.out.println("An unexpected error occurred while choosing Product Name: " + chhoseprod);
        throw new RuntimeException("Error while choosing Product Name: " + chhoseprod, e);
    }

    
}



public void choosetg(String chosetg) {
    try {
        // Log the chosen TG name from Excel
        System.out.println("From Excel sheet: Chose TG Name: " + chosetg);
        
        // Construct the XPath for the desired element
        String expath = "//div[text()='" + chosetg + "']";
        System.out.println("Constructed XPath: " + expath);
        
        // Find the WebElement using the constructed XPath
        WebElement ele = driver.findElement(By.xpath(expath));
        System.out.println("Element found: " + ele.getText());
        
        // Click on the found WebElement
        ele.click();
        System.out.println("Clicked on the element: " + chosetg);
        
    } catch (NoSuchElementException e) {
        System.out.println("Error: Element not found for TG Name: " + chosetg);
        throw new RuntimeException("TG Name not found: " + chosetg, e);
        
    } catch (ElementClickInterceptedException e) {
        System.out.println("Error: Unable to click on the element: " + chosetg);
        throw new RuntimeException("Click intercepted for TG Name: " + chosetg, e);
        
    } catch (Exception e) {
        System.out.println("An unexpected error occurred while choosing TG: " + chosetg);
        throw new RuntimeException("Error while choosing TG Name: " + chosetg, e);
    }
}





}
