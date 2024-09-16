package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ImageCraftAI extends Basepage{
	public ImageCraftAI(WebDriver driver) {
		super(driver);
	}
		@FindBy(xpath="//a[@href='/apphome/vingupta3/image-craft-ai/text-to-image']/div[1]")
		WebElement ImageCraftAI;
		//div//div//div[1]//div[2]//div[2]//a[1]//span[1]
@FindBy(xpath="//div//div//div[1]//div[2]//div[2]//a[1]//span[1]") 
WebElement tryItOut;
		
		public void Tryitouttexttoimage() {
			try {
		    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // 10 seconds timeout
		         WebElement button = wait.until(ExpectedConditions.elementToBeClickable(tryItOut));
		         button.click();
		    } catch (NoSuchElementException e) {
		        System.out.println("tryItOut button not displayed in ImageCraftAI page because of NoSuchElementException");
		        throw new RuntimeException("tryItOut button not displayed in ImageCraftAI page because of NoSuchElementException", e);
		    } catch (TimeoutException e) {
		        System.out.println("tryItOut button not displayed in ImageCraftAI page because of TimeoutException");
		        throw new RuntimeException("tryItOut button not displayed in ImageCraftAI page because of TimeoutException", e);
		    } catch (Exception e) {
		        System.out.println("An unexpected error occurred while trying to load the ImageCraftAI page");
		        throw new RuntimeException("An unexpected error occurred while loading ImageCraftAI page", e);
		    }
			// WebElement element = new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/div[contains(@class,'justify-center items-center gap-3 font-semibold rounded-[30px] border-2 text-[#FFC01D] border-[#FFC01D] h-12 hover:bg-[#FFC01D] hover:text-black py-5 px-8 border-solid cursor-pointer')]")));
			//tryItOut.click();
	}}

