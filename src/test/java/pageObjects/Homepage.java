package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Homepage extends Basepage{
		public Homepage(WebDriver driver) {
			super(driver);
		}
		

@FindBy(xpath="//a[contains(text(),'ImageCraft AI')]") 
WebElement imageCraftAI;

WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));


public void HomeimageCraftAI() {
    try {
        // Wait for the imageCraftAI element to be visible and clickable
        WebElement element = wait.until(ExpectedConditions.visibilityOf(imageCraftAI));
        element.click(); // Click the element
        System.out.println("Clicked on Home Image Craft AI successfully.");
    } catch (NoSuchElementException e) {
        System.out.println("Home Image Craft AI element not found.");
        throw new RuntimeException("Home Image Craft AI element not found.", e);
    } catch (ElementNotInteractableException e) {
        System.out.println("Home Image Craft AI element is not interactable.");
        throw new RuntimeException("Home Image Craft AI element is not interactable.", e);
    } catch (Exception e) {
        System.out.println("An unexpected error occurred while clicking on Home Image Craft AI.");
        throw new RuntimeException("An unexpected error occurred while clicking on Home Image Craft AI.", e);
    }
}

}
