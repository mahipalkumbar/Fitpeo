package pageObjects;

import java.time.Duration;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VideoVistaAI extends Basepage{
	public VideoVistaAI(ThreadLocal<WebDriver> driver) {
		super(driver);
	}
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	Actions act=new Actions(driver);
	
	
	@FindBy(xpath="//p[text()='VideoVista Script-to-Video']/..//div[text()='Try It Out']") 
	WebElement tryItOut1;
	
	@FindBy(xpath="//p[text()='VideoVista Image-to-Video']/..//div[text()='Try It Out']") 
	WebElement tryItOut2;
	
	public void clickOnTryItOutTexttoVideobutton() {
	    try {
	        // Click the Try It Out Image to Image button
	    	tryItOut1.click(); // Implicit wait will handle visibility and clickability
	        System.out.println("Clicked on Try It Out Text to Video button successfully.");
	    } catch (NoSuchElementException e) {
	        System.out.println("Try It Out Text to Video button not found.");
	        throw new RuntimeException("TryItOutTexttoVideobutton not displayed in VideoVistaAI page.", e);
	    } catch (ElementNotInteractableException e) {
	        System.out.println("Try It Out Text to Video button is not interactable.");
	        throw new RuntimeException("TryItOutTexttoVideobutton is not interactable in VideoVistaAI page.", e);
	    } catch (Exception e) {
	        System.out.println("An unexpected error occurred while clicking the Try It Out Text to Video button.");
	        throw new RuntimeException("An unexpected error occurred while loading VideoVistaAI page.", e);
	    }
	}
	public void clickOnTryItOutImagetoVideobutton() {
	    try {
	        // Click the Try It Out Image to Image button
	    	tryItOut2.click(); // Implicit wait will handle visibility and clickability
	        System.out.println("Clicked on Try It Out Image to Video button successfully.");
	    } catch (NoSuchElementException e) {
	        System.out.println("Try It Out Image to Video button not found.");
	        throw new RuntimeException("TryItOutImagetoVideobutton not displayed in VideoVistaAI page.", e);
	    } catch (ElementNotInteractableException e) {
	        System.out.println("Try It Out Image to Video button is not interactable.");
	        throw new RuntimeException("TryItOutImagetoVideobutton is not interactable in VideoVistaAI page.", e);
	    } catch (Exception e) {
	        System.out.println("An unexpected error occurred while clicking the Try It Out Image to Video button.");
	        throw new RuntimeException("An unexpected error occurred while loading VideoVistaAI page.", e);
	    }
	}

}
