package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VideoContentInTextToVideo extends Basepage {
	public VideoContentInTextToVideo(ThreadLocal<WebDriver> driver) {
		super(driver);
	}
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	Actions act=new Actions(driver);
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	@FindBy(xpath="//div[text()='Video Structure']/following::button[1]") 
	WebElement videostracturenextbutton;
	
	
	@FindBy(xpath="//div[text()='Pin']/following::button[1]") 
	WebElement videosizenextbutton;
	
	
	
	
	public void ClickOnVideoStractureNextButton() {
	    try {
	       
	        wait.until(ExpectedConditions.visibilityOf(videostracturenextbutton));
	        System.out.println("Video structure next button is visible.");

	        // Check if the button is clickable
	        wait.until(ExpectedConditions.elementToBeClickable(videostracturenextbutton));
	        System.out.println("Video structure next button is clickable. Clicking the button...");

	        // Click the button
	        videostracturenextbutton.click();
	        System.out.println("Clicked on the video structure next button.");

	    } catch (TimeoutException e) {
	        // Handle case where the button is not visible or clickable within the timeout
	        System.err.println("Error: Timed out waiting for the video structure next button to be visible/clickable.");
	        throw new RuntimeException("Failed to click on the video structure next button due to timeout: " + e.getMessage());
	    } catch (NoSuchElementException e) {
	        // Handle case where the button is not found in the DOM
	        System.err.println("Error: The video structure next button was not found on the page.");
	        throw new RuntimeException("Failed to find the video structure next button: " + e.getMessage());
	    } catch (WebDriverException e) {
	        // Handle WebDriver-related issues
	        System.err.println("Error: WebDriver encountered an issue while interacting with the video structure next button.");
	        throw new RuntimeException("WebDriver error while clicking the video structure next button: " + e.getMessage());
	    } catch (Exception e) {
	        // Handle any unexpected errors
	        System.err.println("An unexpected error occurred: " + e.getMessage());
	        throw new RuntimeException("Unexpected error while clicking the video structure next button: " + e.getMessage());
	    }
	}

	public void SelectingVideoSizeInVideoContent(String size) {
	    String xPathExpression = "//div[text()='" + size + "']/..";
	    try {
	        System.out.println("Attempting to find the size button using XPath: " + xPathExpression);
	        
	        // Wait until the size button is visible and clickable
	        WebElement sizeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathExpression)));
	        wait.until(ExpectedConditions.elementToBeClickable(sizeButton));

	        System.out.println("Size button found and is visible for size: " + size);

	        // Check if the button is displayed and enabled
	        if (sizeButton.isDisplayed() && sizeButton.isEnabled()) {
	            System.out.println("Size button is clickable. Attempting to click on it.");
	            sizeButton.click();
	            System.out.println("Successfully clicked on size button: " + size);
	        } else {
	            System.out.println("Size button is either not displayed or not enabled: " + size);
	            throw new RuntimeException("Size button is not clickable or displayed: " + size);
	        }

	    } catch (NoSuchElementException e) {
	        System.err.println("Size button not found for size: " + size + " - Exception: " + e.getMessage());
	        throw new RuntimeException("Size button not found for size: " + size, e);
	    } catch (StaleElementReferenceException e) {
	        System.err.println("The size button element is no longer attached to the DOM for size: " + size + " - Exception: " + e.getMessage());
	        throw new RuntimeException("Stale element reference for size button: " + size, e);
	    } catch (TimeoutException e) {
	        System.err.println("Timeout waiting for size button to be visible and clickable: " + size + " - Exception: " + e.getMessage());
	        throw new RuntimeException("Timeout waiting for size button: " + size, e);
	    } catch (WebDriverException e) {
	        System.err.println("WebDriver encountered an error while selecting size: " + size + " - Exception: " + e.getMessage());
	        throw new RuntimeException("WebDriver error while selecting size: " + size, e);
	    } catch (Exception e) {
	        System.err.println("An unexpected error occurred while selecting size: " + size + " - Exception: " + e.getMessage());
	        throw new RuntimeException("Unexpected error while selecting size: " + size, e);
	    }
	}
	
	public void ClickOnNextButtonVideoSize() {
	    try {
	        wait.until(ExpectedConditions.visibilityOf(videosizenextbutton));
	        System.out.println("Next button for video size is visible.");

	        // Click the button
	        videosizenextbutton.click();
	        System.out.println("Clicked on the next button for video size.");
	    } catch (TimeoutException e) {
	        System.err.println("Error: Next button for video size did not become visible within the timeout period.");
	        throw new RuntimeException("Next button not visible: " + e.getMessage());
	    } catch (NoSuchElementException e) {
	        System.err.println("Error: Next button for video size was not found.");
	        throw new RuntimeException("Element not found: " + e.getMessage());
	    } catch (WebDriverException e) {
	        System.err.println("WebDriver error occurred while clicking the next button: " + e.getMessage());
	        throw new RuntimeException("WebDriver error: " + e.getMessage());
	    } catch (Exception e) {
	        System.err.println("An unexpected error occurred while clicking the next button: " + e.getMessage());
	        throw new RuntimeException("Unexpected error: " + e.getMessage());
	    }
	}
		

}
