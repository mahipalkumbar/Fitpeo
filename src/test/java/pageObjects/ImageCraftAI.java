package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ImageCraftAI extends Basepage{
	public ImageCraftAI(ThreadLocal<WebDriver> driver) {
		super(driver);
	}
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	Actions act=new Actions(driver);
		@FindBy(xpath="//a[@href='/apphome/vingupta3/image-craft-ai/text-to-image']/div[1]")
		WebElement ImageCraftAI;
		
		
		/*@FindBy(xpath="//a[@href='/apphome/vingupta3/image-craft-ai/image-to-image']//span[text()='Try It Out']")
		WebElement TryItOutImagetoImagebutton;*/
		

		@FindBy(xpath="//body//div//div//div//div[2]//div[2]//div[2]//a[1]//span[1]")
		WebElement TryItOutImagetoImagebutton;
		
		@FindBy(xpath="//div//div//div[1]//div[2]//div[2]//a[1]//span[1]") 
		WebElement tryItOut;


		public void clickOnTryItOutImagetoImagebutton() {
		    try {
		        // Click the Try It Out Image to Image button
		        TryItOutImagetoImagebutton.click(); // Implicit wait will handle visibility and clickability
		        System.out.println("Clicked on Try It Out Image to Image button successfully.");
		    } catch (NoSuchElementException e) {
		        System.out.println("Try It Out Image to Image button not found.");
		        throw new RuntimeException("TryItOutImagetoImagebutton not displayed in ImageCraftAI page.", e);
		    } catch (ElementNotInteractableException e) {
		        System.out.println("Try It Out Image to Image button is not interactable.");
		        throw new RuntimeException("TryItOutImagetoImagebutton is not interactable in ImageCraftAI page.", e);
		    } catch (Exception e) {
		        System.out.println("An unexpected error occurred while clicking the Try It Out Image to Image button.");
		        throw new RuntimeException("An unexpected error occurred while loading ImageCraftAI page.", e);
		    }
		}

		public void Tryitouttexttoimage() {
		    try {
		        // Click the tryItOut button directly
		        tryItOut.click(); // Implicit wait will handle visibility and clickability
		        System.out.println("Clicked on tryItOut button successfully.");
		    } catch (NoSuchElementException e) {
		        System.out.println("tryItOut button not found.");
		        throw new RuntimeException("tryItOut button not displayed in ImageCraftAI page.", e);
		    } catch (ElementNotInteractableException e) {
		        System.out.println("tryItOut button is not interactable.");
		        throw new RuntimeException("tryItOut button is not interactable in ImageCraftAI page.", e);
		    } catch (Exception e) {
		        System.out.println("An unexpected error occurred while clicking the tryItOut button.");
		        throw new RuntimeException("An unexpected error occurred while loading ImageCraftAI page.", e);
		    }
		}

	}

