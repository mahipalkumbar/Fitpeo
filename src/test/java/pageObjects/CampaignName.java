package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
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

@FindBy(xpath="/html/body/div[1]/div[2]/div/div/div[1]/div[2]/div/div/div/div/div[4]/div[3]//button") 
WebElement nextc;

@FindBy(xpath="//div[@class='w-full flex flex-col']//div[@class='w-full flex flex-wrap gap-2']//span[@class='text-sm font-medium w-full']")
List<WebElement> Chooseproduct;
@FindBy(xpath="//div[@class='flex flex-col gap-5 w-full']//div[@class='w-full flex flex-wrap gap-3']//div[@class='truncate text-xs font-semibold']")
List<WebElement> choosetg;

JavascriptExecutor js = (JavascriptExecutor) driver;

WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));

public void Campaigname(String cname) {
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
        wait.until(ExpectedConditions.visibilityOf(mediachannelspage));
        
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
    boolean productFound = false; // Flag to check if the product was found and clicked

    for (WebElement w : Chooseproduct) {
        if (w.getText().equals(chhoseprod)) {
            try {
                w.click(); // Click the product if the text matches
                productFound = true; // Set the flag to true
                System.out.println("Product '" + chhoseprod + "' selected.");
                break; // Exit the loop once the product is clicked
            } catch (NoSuchElementException e) {
                System.out.println("Error: Product element not found for '" + chhoseprod + "'.");
                throw new RuntimeException("Product element not found: " + chhoseprod, e);
            } catch (ElementNotInteractableException e) {
                System.out.println("Error: Product '" + chhoseprod + "' is not interactable.");
                throw new RuntimeException("Product is not interactable: " + chhoseprod, e);
            } catch (Exception e) {
                System.out.println("An unexpected error occurred while trying to select product '" + chhoseprod + "'.");
                throw new RuntimeException("An unexpected error occurred for product: " + chhoseprod, e);
            }
        }
    }

    if (!productFound) {
        System.out.println("Product '" + chhoseprod + "' not found in the list.");
    }
}



public void choosetg(String chosetg) {
    System.out.println("From Excel sheet: Chose TG Name: " + chosetg);
    boolean tgFound = false; // Flag to track if the TG was found

    for (WebElement w : choosetg) {
        String tgText = w.getText();
        if (tgText.equals(chosetg)) {
            try {
                System.out.println("From page: Chose TG Name: " + tgText);
                w.click(); // Click the matching TG
                tgFound = true; // Set the flag to true
                break; // Exit the loop after clicking the TG
            } catch (Exception e) {
                System.out.println("Error clicking on TG Name: " + tgText);
                throw new RuntimeException("Failed to click on TG Name: " + tgText, e);
            }
        }
    }

    if (!tgFound) {
        System.out.println("TG Name '" + chosetg + "' not found in the list.");
    }
}


}
