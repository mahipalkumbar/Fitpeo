package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MediaChannels extends Basepage{
	public MediaChannels(WebDriver driver) {
		super(driver);
	}
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	@FindBy(xpath="//div[@class='flex w-full gap-3']/button/img")
	List<WebElement> socialmedia;
	//div[@class='flex w-full items-end gap-2']//div[text()='(1080*1080)']
	@FindBy(xpath="//div[@class='flex w-full items-end gap-2'][1]//button[1]")
	List<WebElement> size;
	
	@FindBy(xpath="//div[@class='w-full flex justify-center items-center gap-3 my-5']//button[@class='nyx-button border border-amber-400 text-white hover:text-black hover:bg-amber-300 hover:shadow-glow text-base px-5 text-center rounded-full w-[109px] hover:shadow-none font-semibold py-1.5'][normalize-space()='Next']") 
	WebElement nexts;
	
	public void socialmedia(String social) {
		 for (WebElement element : socialmedia) {
	            try {
	                wait.until(ExpectedConditions.visibilityOf(element));
	                wait.until(ExpectedConditions.elementToBeClickable(element));
	                if (element.getAttribute("alt").equals(social)) {
	                    element.click();
	                    break; 
	                }
	            } catch (Exception e) {
	                System.out.println("Exception occurred: " + e.getMessage());
	            }
	        }}
		
		
		
	/*	for (WebElement w : socialmedia) {
	        if(w.getAttribute("alt").equals(social)) {
	        wait.until(ExpectedConditions.elementToBeClickable(w));
	            break;
	        }
	}}*/
			//if(w.getAttribute("alt").equals(social)){
			//	w.click();
			//}}}
	
		public void size(String siz) {
			String xpathexp="//div[@class='flex w-full items-end gap-2']//div[text()='"+siz+"']/..//button";
			WebElement el1=driver.findElement(By.xpath(xpathexp));
			wait.until(ExpectedConditions.visibilityOf(el1));
            wait.until(ExpectedConditions.elementToBeClickable(el1));
            el1.click();
			// wait.until(ExpectedConditions.visibilityOfElementLocated(el1));
			// JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			// jsExecutor.executeScript("arguments[0].click();", button);
			/*WebElement el=driver.findElement(By.xpath(xpathexp));
			System.out.println(el);
			el.click();*/
		}
	
	public void nexts() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ReactModal__Overlay")));
		WebElement elementnextab1= wait.until(ExpectedConditions.elementToBeClickable(nexts));
		elementnextab1.click();
		
	}
	
	
}
