package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testBase.BaseClass;

public class CampulseAI extends Basepage {
	public CampulseAI(WebDriver driver) {
		super(driver);
	}
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
	Actions act=new Actions(driver);
	JavascriptExecutor js = (JavascriptExecutor) driver;

	@FindBy(xpath="//div[contains(@class,'w-[70%] p-3 relative')]//div[contains(@class,'py-5 px-8 border-solid cursor-pointer hover:text-black')][normalize-space()='Try It Out']") 
	WebElement AutoAdManagerTryItOutButton;


	@FindBy(xpath="//div[contains(@class,'w-[70%] px-3 pt-[22px] relative')]//div[contains(@class,'py-5 px-8 border-solid cursor-pointer hover:text-black')][normalize-space()='Try It Out']") 
	WebElement UniviewAnalyticsTryItOutButton;
	


	public void ClickOnAutoAdManagerTryItOutButton() {
	    try {
	        // Locate the button using implicit wait
	        WebElement button = AutoAdManagerTryItOutButton;
	        button.click(); // Click the button
	        System.out.println("AutoAdManager Try It Out button clicked successfully.");
	    } catch (NoSuchElementException e) {
	        throw new RuntimeException("AutoAdManagerTryItOutButton not found in Campulse AI page.", e);
	    } catch (ElementClickInterceptedException e) {
	        throw new RuntimeException("Unable to click AutoAdManagerTryItOutButton due to it being obscured by another element.", e);
	    } catch (Exception e) {
	        throw new RuntimeException("An unexpected error occurred while trying to click AutoAdManager Try It Out button.", e);
	    }
	}

	
	public void ClickOnUniviewAnalyticsTryItOutButton() {
	    try {
	        // Locate the button using implicit wait
	        WebElement button = UniviewAnalyticsTryItOutButton;
	        button.click(); // Click the button
	        System.out.println("UniviewAnalytics Try It Out button clicked successfully.");
	    } catch (NoSuchElementException e) {
	        throw new RuntimeException("UniviewAnalyticsTryItOutButton not found in Campulse AI page.", e);
	    } catch (ElementClickInterceptedException e) {
	        throw new RuntimeException("Unable to click UniviewAnalyticsTryItOutButton due to it being obscured by another element.", e);
	    } catch (Exception e) {
	        throw new RuntimeException("An unexpected error occurred while trying to click UniviewAnalytics Try It Out button.", e);
	    }
	}

	
}
