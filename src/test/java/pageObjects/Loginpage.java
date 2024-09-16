package pageObjects;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Loginpage extends Basepage {
	public Loginpage(WebDriver driver) {
		super(driver);
	}

@FindBy(xpath="//input[@id='phone']")
WebElement phonenoloc;

@FindBy(xpath="//input[@placeholder='xxxxxxxx']")
WebElement passwordloc;
@FindBy(xpath="//button[normalize-space()='Login']") 
WebElement loginloc;

@FindBy(xpath="//span[@class='flex w-full h-full items-center justify-center rounded-full bg-[#250e35] hover:bg-[#2F2546] back ']")
WebElement nyxLOGO;

////p[text()='Phone number and Password combination do not match']
@FindBy(xpath="p[text()='Phone number and Password combination do not match']")
WebElement errorMessage;

//div[text()='Skip']

WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));

public void sendphone(String no){
	wait.until(ExpectedConditions.visibilityOf(phonenoloc));
	phonenoloc.sendKeys(no);
}
public void sendpassword(String pwd){
	passwordloc.sendKeys(pwd);
}
public void clicklogin(){
	loginloc.click();
	wait.until(ExpectedConditions.invisibilityOf(loginloc));
}
public boolean isHomePageDisplayed() {
	return nyxLOGO.isDisplayed();
}

public boolean isErrorMessageDisplayed() {
    return errorMessage.isDisplayed();
}


}

