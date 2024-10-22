package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VideoSettingsInTextToVideo extends Basepage {
	public VideoSettingsInTextToVideo(WebDriver driver) {
		super(driver);
	}
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	Actions act=new Actions(driver);
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	
	@FindBy(xpath="//div[text()='Pace']/following::button[1]") 
	WebElement pacenextbutton;
	
	@FindBy(xpath="//div[text()='Captions']/following::button[1]") 
	WebElement captionsnextbutton;
	
	
	public void ClickOnPaceNextButton() {
	    try {
	        System.out.println("Attempting to click on the Pace next button.");
	        pacenextbutton.click();
	        System.out.println("Successfully clicked on the Pace next button.");
	    } catch (NoSuchElementException e) {
	        System.err.println("Error: Pace next button not found - " + e.getMessage());
	        throw new RuntimeException("Pace next button not found: " + e.getMessage());
	    } catch (ElementNotInteractableException e) {
	        System.err.println("Error: Pace next button is not interactable - " + e.getMessage());
	        throw new RuntimeException("Pace next button is not interactable: " + e.getMessage());
	    } catch (WebDriverException e) {
	        System.err.println("WebDriver error occurred while clicking Pace next button: " + e.getMessage());
	        throw new RuntimeException("WebDriver error: " + e.getMessage());
	    } catch (Exception e) {
	        System.err.println("An unexpected error occurred while clicking the Pace next button: " + e.getMessage());
	        throw new RuntimeException("Unexpected error: " + e.getMessage());
	    }
	}
	
	public void SelectingCaptionsOption(String caption) {
	    String expath = "//span[text()='" + caption + "']";
	    try {
	        System.out.println("Waiting for the caption element to be visible: " + caption);
	        WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(expath)));
	        System.out.println("Caption element found: " + caption);
	        ele.click();
	        System.out.println("Clicked on caption: " + caption);
	    } catch (TimeoutException e) {
	        System.err.println("Error: Timeout while waiting for caption element: " + caption);
	        throw new RuntimeException("Timeout waiting for caption: " + caption, e);
	    } catch (NoSuchElementException e) {
	        System.err.println("Error: Caption element not found: " + caption);
	        throw new RuntimeException("Caption not found: " + caption, e);
	    } catch (WebDriverException e) {
	        System.err.println("WebDriver error occurred while selecting caption: " + caption);
	        throw new RuntimeException("WebDriver error: " + e.getMessage(), e);
	    } catch (Exception e) {
	        System.err.println("An unexpected error occurred while selecting caption: " + caption);
	        throw new RuntimeException("Unexpected error: " + e.getMessage(), e);
	    }}
	
	public void ClickOnCaptionNextButton() {
	    try {
	        System.out.println("Attempting to click on the caption next button.");
	        captionsnextbutton.click();
	        System.out.println("Successfully clicked on the caption next button.");
	    } catch (NoSuchElementException e) {
	        System.err.println("Error: Caption next button not found - " + e.getMessage());
	        throw new RuntimeException("Caption next button not found: " + e.getMessage());
	    } catch (ElementNotInteractableException e) {
	        System.err.println("Error: Caption next button is not interactable - " + e.getMessage());
	        throw new RuntimeException("Caption next button is not interactable: " + e.getMessage());
	    } catch (WebDriverException e) {
	        System.err.println("WebDriver error occurred while clicking caption next button: " + e.getMessage());
	        throw new RuntimeException("WebDriver error: " + e.getMessage());
	    } catch (Exception e) {
	        System.err.println("An unexpected error occurred while clicking the caption next button: " + e.getMessage());
	        throw new RuntimeException("Unexpected error: " + e.getMessage());
	    }
	}
}
