package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
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
@FindBy(xpath="/html/body/div[1]/div[2]/div/div/div[1]/div[2]/div/div/div/div/div[4]/div[3]//button") 
WebElement nextc;

@FindBy(xpath="//div[@class='w-full flex flex-col']//div[@class='w-full flex flex-wrap gap-2']//span[@class='text-sm font-medium w-full']")
List<WebElement> Chooseproduct;
@FindBy(xpath="//div[@class='flex flex-col gap-5 w-full']//div[@class='w-full flex flex-wrap gap-3']//div[@class='truncate text-xs font-semibold']")
List<WebElement> choosetg;

public void Campaigname(String cname) throws InterruptedException {
	try {
		 WebElement elementCampaigname= new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(Campaigname));
			elementCampaigname.sendKeys(cname);
			//elementCampaigname.sendKeys(Keys.TAB);
	} catch (TimeoutException e) {
   System.out.println("Product Name Text fieald not displayed in Add Product page because of TimeoutException");
   throw new RuntimeException("Product Name Text fieald not displayed in Add Product page because of TimeoutException", e);
	} catch (NoSuchElementException e) {
   System.out.println("Product Name Text fieald not displayed in Add Product page because of NoSuchElementException");
   throw new RuntimeException("Product Name Text fieald not displayed in Add Product page because of NoSuchElementException", e);
	} catch (Exception e) {
   System.out.println("An unexpected error occurred while loading Add Product page");
   throw new RuntimeException("An unexpected error occurred in Add Product page while loading", e);
	}}
	//Campaigname.sendKeys(cname);

public void Objective(String obj) {
	Objective.sendKeys(obj);
	//Objective.sendKeys(Keys.TAB);
}
public void nextc() {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	 js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ReactModal__Overlay")));
	WebElement elemen= wait.until(ExpectedConditions.elementToBeClickable(nextc));
	elemen.click();

}
public void Chooseproduct(String chhoseprod) {
	for(WebElement w:Chooseproduct) {
		if(w.getText().equals(chhoseprod)){
			w.click();
		}}}

public void choosetg(String chosetg) {
for(WebElement w:choosetg) {
	if(w.getText().equals(chosetg)) {
		w.click();
	}}}

}
