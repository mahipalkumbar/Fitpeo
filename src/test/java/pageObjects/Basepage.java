package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Basepage {
    
    protected WebDriver driver;

    // Constructor that accepts WebDriver directly
    public Basepage(WebDriver driver) {
        this.driver = driver;  // Directly assign the WebDriver instance
        PageFactory.initElements(driver, this);  // Initialize page elements
    }
}
