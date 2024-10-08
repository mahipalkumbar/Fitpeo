package pageObjects;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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

public void sendPhone(String no) {
    try {
        if (phonenoloc.isDisplayed() && phonenoloc.isEnabled()) {
            phonenoloc.clear(); // Clear any existing input before sending new keys
            phonenoloc.sendKeys(no);
        } else {
            System.out.println("Phone number input field is not available.");
        }
    } catch (NoSuchElementException e) {
        System.out.println("Phone number input field not found: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("An error occurred while entering phone number: " + e.getMessage());
    }
}

public void sendPassword(String pwd) {
    try {
        if (passwordloc.isDisplayed() && passwordloc.isEnabled()) {
            passwordloc.clear(); // Clear any existing input before sending new keys
            passwordloc.sendKeys(pwd);
        } else {
            System.out.println("Password input field is not available or not enabled.");
        }
    } catch (NoSuchElementException e) {
        System.out.println("Password input field not found: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("An error occurred while entering the password: " + e.getMessage());
    }
}

public void clickLogin() {
    try {
        if (loginloc.isDisplayed() && loginloc.isEnabled()) {
            loginloc.click(); // Click the login button
            System.out.println("Login button clicked.");
        } else {
            System.out.println("Login button is either not visible or not enabled.");
        }
    } catch (NoSuchElementException e) {
        System.out.println("Login button not found: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("An error occurred while clicking the login button: " + e.getMessage());
    }
}

public boolean isHomePageDisplayed() {
    try {
        return nyxLOGO.isDisplayed(); // Check if the NYX logo is displayed
    } catch (NoSuchElementException e) {
        System.out.println("NYX logo not found on the homepage: " + e.getMessage());
        return false;
    } catch (Exception e) {
        System.out.println("An error occurred while checking if the NYX logo is displayed: " + e.getMessage());
        return false;
    }
}


public boolean isErrorMessageDisplayed() {
    try {
        return errorMessage.isDisplayed(); // Check if the error message is displayed
    } catch (NoSuchElementException e) {
        System.out.println("Error message element not found: " + e.getMessage());
        return false;
    } catch (Exception e) {
        System.out.println("An unexpected error occurred while checking if the error message is displayed: " + e.getMessage());
        return false;
    }
}



}

