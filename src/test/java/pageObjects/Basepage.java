package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Basepage {
    WebDriver driver;

    // Constructor that accepts ThreadLocal<WebDriver>
    public Basepage(ThreadLocal<WebDriver> driver) {
        this.driver = driver.get();  // Get WebDriver from the ThreadLocal instance
        PageFactory.initElements(driver.get(), this);  // Initialize elements
    }
}
