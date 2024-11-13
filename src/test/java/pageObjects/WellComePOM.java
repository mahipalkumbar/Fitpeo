package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WellComePOM extends Basepage{
	public WellComePOM(ThreadLocal<WebDriver> driver) {
		super(driver);
	}
	
	
	@FindBy(xpath="//a[@class='w-full']//button[text()='Login']") 
	WebElement loginbutton;
	
	public void ClickOnLoginButtonn() {
		loginbutton.click();;
	}
}
