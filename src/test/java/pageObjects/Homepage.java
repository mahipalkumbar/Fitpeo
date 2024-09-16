package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
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


public void HomeimageCraftAI() throws InterruptedException {
	// WebElement skipButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("div[text()='Skip']")));
	//driver.findElement(By.xpath("div[text()='Skip']")).click();
	// skipButton.click();
	/*Alert confirmAlert = driver.switchTo().alert();
	 confirmAlert.dismiss();*/
	WebElement element = wait.until(ExpectedConditions.visibilityOf(imageCraftAI));
	wait.until(ExpectedConditions.elementToBeClickable(imageCraftAI));
	//Thread.sleep(2000);
	element.click();
}

}
