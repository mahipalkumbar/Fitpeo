package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MediaChannels extends Basepage{
	public MediaChannels(ThreadLocal<WebDriver> driver) {
		super(driver);
	}
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	@FindBy(xpath="//div[@class='flex w-full gap-3']/button/img")
	List<WebElement> socialmedia;
	//div[@class='flex w-full items-end gap-2']//div[text()='(1080*1080)']
	@FindBy(xpath="//div[@class='flex w-full items-end gap-2'][1]//button[1]")
	List<WebElement> size;
	
	@FindBy(xpath="//div[text()='Color Composition']") 
	WebElement creativedesignpage;
	
	@FindBy(xpath="//div[@class='w-full flex justify-center items-center gap-3 my-5']//button[@class='nyx-button border border-amber-400 text-white hover:text-black hover:bg-amber-300 hover:shadow-glow text-base px-5 text-center rounded-full w-[109px] hover:shadow-none font-semibold py-1.5'][normalize-space()='Next']") 
	WebElement nexts;
	
	 JavascriptExecutor js = (JavascriptExecutor) driver;
	
	 public void socialmedia(String social) {
		 String xPathExpression = "//img[@alt='" + social + "']";
		    
		    try {
		        // Find the social media button
		        WebElement socialMediaButton = driver.findElement(By.xpath(xPathExpression));
		        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Social Media Creatives']")));
		        // Check if the element is displayed before interacting
		        if (socialMediaButton.isDisplayed()) {
		            socialMediaButton.click();
		            System.out.println("In Social Media Creatives page: " + social + " clicked.");
		        } else {
		            System.out.println("In Social Media Creatives page: " + social + " not displayed.");
		        }

		    } catch (NoSuchElementException e) {
		        System.out.println("Element not found: " + social + " - " + e.getMessage());
		    } catch (StaleElementReferenceException e) {
		        System.out.println("Element is no longer attached to the DOM: " + social + ". Retrying...");
		        
		    } catch (Exception e) {
		        System.out.println("An unexpected error occurred: " + e.getMessage());
		    }
		
		 
		    /*for (WebElement element : socialmedia) {
		        try {
		            // Check if the element is displayed and clickable using implicit waits
		            if (element.isDisplayed() && element.isEnabled()) {
		                // Check if the element's alt attribute matches the provided social media name
		                if (social.equals(element.getAttribute("alt"))) {
		                    element.click(); // Click the element if it matches
		                    System.out.println(social + " button clicked.");
		                    break; // Exit loop after clicking
		                }
		            }
		        } catch (NoSuchElementException e) {
		            System.out.println("Element not found: " + e.getMessage());
		        } catch (StaleElementReferenceException e) {
		            System.out.println("Element is no longer attached to the DOM: " + e.getMessage());
		            // Optionally, you can refresh the list of social media elements here if needed
		        } catch (Exception e) {
		            System.out.println("An unexpected error occurred: " + e.getMessage());
		        }
		    }*/
		}
	
	 public void selectSize(String siz) {
		    String xPathExpression = "//div[@class='flex w-full items-end gap-2']//div[text()='" + siz + "']/..//button";

		    try {
		        // Find the button element using the provided XPath expression
		        WebElement sizeButton = driver.findElement(By.xpath(xPathExpression));
		        wait.until(ExpectedConditions.and(ExpectedConditions.visibilityOf(sizeButton),ExpectedConditions.elementToBeClickable(sizeButton)));
		        // Check if the button is displayed and enabled
		        if (sizeButton.isDisplayed() && sizeButton.isEnabled()) {
		            sizeButton.click(); // Click the size button
		            System.out.println("Clicked on size button: " + siz);
		        } else {
		            System.out.println("Size button is not clickable or not displayed: " + siz);
		        }
		    } catch (NoSuchElementException e) {
		        System.out.println("Size button not found: " + e.getMessage());
		    } catch (StaleElementReferenceException e) {
		        System.out.println("The size button element is no longer attached to the DOM: " + e.getMessage());
		    } catch (Exception e) {
		        System.out.println("An unexpected error occurred while selecting size: " + e.getMessage());
		    }
		}

	
	 public boolean goToNext() {
		    try {
		        // Click the next button using JavaScript to ensure it works even if the element is obscured
		        js.executeScript("arguments[0].click();", nexts);
		        
		        // Wait for the creative design page to be visible and return its display status
		        return wait.until(ExpectedConditions.visibilityOf(creativedesignpage)).isDisplayed();
		    } catch (NoSuchElementException e) {
		        System.out.println("Next button or creative design page not found: " + e.getMessage());
		        return false;
		    } catch (StaleElementReferenceException e) {
		        System.out.println("The next button or creative design page is no longer attached to the DOM: " + e.getMessage());
		        return false;
		    } catch (Exception e) {
		        System.out.println("An unexpected error occurred while navigating to the next page: " + e.getMessage());
		        return false;
		    }
		}

	
	
}
