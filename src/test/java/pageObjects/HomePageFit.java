package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageFit extends Basepage{
	public HomePageFit(WebDriver driver) {
        super(driver); 
    }
	JavascriptExecutor js = (JavascriptExecutor) driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	Actions act = new Actions(driver);
	
	@FindBy(xpath="//div[text()='Revenue Calculator']")
	WebElement revenuecalculatorbutton;
	
	
	@FindBy(xpath="//input[@data-index='0' and @aria-valuenow='820']")
	WebElement sliderbar820isdisplayed;
	
	@FindBy(xpath="(//span[text()='Patients should be between 0 to 2000']/..//input)[2]")
	WebElement textbox;
	
	@FindBy(xpath="//input[@value='820' and @aria-invalid='false']")
	WebElement textbox820isdisplayed;
	
	@FindBy(xpath="//span[contains(@class, 'Mui-checked')]")
	List<WebElement> checkboxdisplayed;
	
	@FindBy(xpath="//h4[text()='How Many Medicare Patients Would You Include in ']")
	WebElement revenuecalculatorpage;
	
	
	@FindBy(xpath="//h5[text()='FitPeo helps you with']")
	WebElement fitpeohomepage;
	
	@FindBy(xpath="//h4[text()=' Your Remote Patient Monitoring program?']")
	WebElement scrolltoslidervisible;
	
	
	@FindBy(xpath="(//p[text()='110700'])[1]")
	WebElement totalrecurringpatientpermonth;
	
	
	public void clickonrevenuecalculatorbutton() {
	    try {
	        System.out.println("Attempting to click on the Revenue Calculator button...");
	        revenuecalculatorbutton.click();
	        System.out.println("Successfully clicked on the Revenue Calculator button.");
	    } catch (Exception e) {
	        System.out.println("Failed to click on the Revenue Calculator button.");
	        throw new RuntimeException("Error while clicking on the Revenue Calculator button: " + e.getMessage(), e);
	    }
	}
	public boolean ScrollDownToSliderUntilDisplay() {
		 js.executeScript("arguments[0].scrollIntoView(true);", scrolltoslidervisible);
		
		return scrolltoslidervisible.isDisplayed();
	}
	public boolean isDiplayedRevenuePage() {
		
		return revenuecalculatorpage.isDisplayed();
		
	}
	
	public boolean isDisplayedHomePageFitpeo() {
		wait.until(ExpectedConditions.visibilityOf(fitpeohomepage));
		return fitpeohomepage.isDisplayed();
	}
	


	public boolean adjustSliderToDesiredValue(int desiredValue) {
	    WebElement slider = driver.findElement(By.cssSelector("input[type='range']"));
	    wait.until(ExpectedConditions.elementToBeClickable(slider));
	    int currentValue = Integer.parseInt(slider.getAttribute("value"));
	    int minValue = Integer.parseInt(slider.getAttribute("min"));
	    int maxValue = Integer.parseInt(slider.getAttribute("max"));
	    if (desiredValue < minValue || desiredValue > maxValue) {
	        System.out.println("Desired value is out of range.");
	        return false;
	    }
	    
	    System.out.println("Starting slider value: " + currentValue);
	    while (currentValue != desiredValue) {
	        if (desiredValue > currentValue) {
	            for (int i = 0; i < 10; i++) {
	                slider.sendKeys(Keys.ARROW_RIGHT);
	            }
	        }
	        else if (desiredValue < currentValue) {
	            for (int i = 0; i < 10; i++) {
	                slider.sendKeys(Keys.ARROW_LEFT);
	            }
	        }
	        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(slider, "value", String.valueOf(currentValue))));
	        currentValue = Integer.parseInt(slider.getAttribute("value"));
	        System.out.println("Current value: " + currentValue);
	        if (currentValue == desiredValue) {
	            break;
	        }
	    }
	    if (currentValue == desiredValue) {
	        System.out.println("Slider successfully reached the desired value: " + currentValue);
	    } else {
	        System.out.println("Slider could not reach the exact desired value. Final value: " + currentValue);
	    }
	    String exe="//input[@value='"+desiredValue+"' and @aria-invalid='false']";
	    
	    WebElement ele=driver.findElement(By.xpath(exe));
	    return ele.isDisplayed();
	}

	
	
	
	
	public boolean enteringtextfield(String value) throws Exception {
	    try {
	        
	        System.out.println("Attempting to clear the text field...");
	        textbox.clear();
	        System.out.println("Text field cleared successfully.");
	        System.out.println("Attempting to enter the value: " + value);
	        textbox.sendKeys(value);
	        System.out.println("Value entered successfully.");

	        return sliderbar820isdisplayed.isDisplayed(); 
	    } catch (Exception e) {
	        System.out.println("An error occurred while interacting with the text field: " + e.getMessage());
	        e.printStackTrace();
	        throw new Exception("Failed to interact with the text field.", e);
	    }
	}
	
	public boolean SelectCPTCodes(String checks[]) throws Exception {
		int given=checks.length;
	    try {
	    	for (String s : checks) {
	            String ele = "//p[text()='" + s + "']/ancestor::div[1]//input[@type='checkbox']";
	            WebElement checkbox = driver.findElement(By.xpath(ele));
	            checkbox.click();
	            System.out.println("Clicked checkbox for: " + s);
	        }
	        System.out.println(given+"=="+checkboxdisplayed.size());
	        if(given!=checkboxdisplayed.size()) {
	                System.out.println("Checkbox not displayed: ");
	                return false; 
	                }
	        System.out.println("All checkboxes are displayed.");
	        return true;
	    } catch (Exception e) {
	        System.out.println("An error occurred while selecting CPT codes: " + e.getMessage());
	        e.printStackTrace();
	        return false; 
	    }
	    
	
	
	}
	public boolean ValidateTotalRecurring() throws Exception {
	    try {
	    	wait.until(ExpectedConditions.visibilityOf(totalrecurringpatientpermonth));	
	        boolean isDisplayed = totalrecurringpatientpermonth.isDisplayed();
	        System.out.println("Total Recurring Patient Per Month is displayed: " + isDisplayed);
	        return isDisplayed;
	    } catch (NoSuchElementException e) {
	        System.out.println("Element not found: " + e.getMessage());
	        throw e;
	    } catch (TimeoutException e) {
	        System.out.println("Timed out waiting for the element to be visible: " + e.getMessage());
	        throw e; 
	    } catch (Exception e) {
	        System.out.println("An unexpected error occurred: " + e.getMessage());
	        throw e; 
	    }
	}




}














































/*  try {
	    	// Locate the slider thumb and the value display element
	    	// Define the desired value and the slider maximum value
	    	//int desiredValue = 820;
	    	int sliderMaxValue = 2000; // Adjust according to your application's slider range

	    	// Locate the slider element
	    	WebElement slider = driver.findElement(By.cssSelector("div.MuiSlider-root")); // Update selector if necessary

	    	// Wait until the slider is visible
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	wait.until(ExpectedConditions.visibilityOf(slider));

	    	// Calculate the slider's move percentage
	    	double movePercentage = (desiredValue / (double) sliderMaxValue) * 100;

	    	// Locate the slider's thumb (movable part of the slider)
	    	WebElement sliderThumb = driver.findElement(By.cssSelector("span.MuiSlider-thumb")); // Adjust selector as necessary

	    	// Get the initial position of the slider thumb
	    	String initialStyle = sliderThumb.getAttribute("style");
	    	double initialLeftPercentage = Double.parseDouble(initialStyle.split("left: ")[1].split("%")[0]);
	    	System.out.println("Initial slider position: " + initialLeftPercentage + "%");

	    	// Use Actions class to click and hold on the slider and move it
	    	//Actions actions = new Actions(driver);
	    	actions.clickAndHold(sliderThumb).perform();

	    	// Gradually move the slider thumb by dragging to the desired value
	    	while (initialLeftPercentage < movePercentage) {
	    	    // Move a small step
	    	    actions.moveByOffset(5, 0).perform();
	    	    Thread.sleep(100); // Wait briefly to simulate smooth movement

	    	    // Update the current position after each move
	    	    String currentStyle = sliderThumb.getAttribute("style");
	    	    initialLeftPercentage = Double.parseDouble(currentStyle.split("left: ")[1].split("%")[0]);
	    	    System.out.println("Current slider position: " + initialLeftPercentage + "%");

	    	    // Break if the desired value is reached or exceeded
	    	    if (initialLeftPercentage >= movePercentage) {
	    	        break;
	    	    }
	    	}

	    	// Release the slider thumb
	    	actions.release().perform();

	    	// Add a wait to ensure the change is applied
	    	wait.until(ExpectedConditions.attributeToBe(sliderThumb, "aria-valuenow", String.valueOf(desiredValue)));

	    	// Verify the final position of the slider
	    	String finalStyle = sliderThumb.getAttribute("style");
	    	double finalLeftPercentage = Double.parseDouble(finalStyle.split("left: ")[1].split("%")[0]);
	    	System.out.println("Final slider position: " + finalLeftPercentage + "%");

	    	// Verify the desired value
	    	String finalValue = sliderThumb.getAttribute("aria-valuenow");
	    	if (Integer.parseInt(finalValue) == desiredValue) {
	    	    System.out.println("Slider successfully moved to the desired value: " + finalValue);
	    	} else {
	    	    System.err.println("Failed to move the slider to the desired value. Current value: " + finalValue);
	    	}

*/
	        // Locate the slider element (not the thumb)
	       /* WebElement slider = driver.findElement(By.cssSelector("div.MuiSlider-root")); // Adjust selector as necessary
	        
	        // Wait until the slider is visible
	        wait.until(ExpectedConditions.visibilityOf(slider));

	        // Get the maximum value of the slider (if not available, we assume a max value)
	        int sliderMaxValue = 2000;  // Max value of the slider (adjust if needed)
	        
	        // Calculate the target value's percentage based on desired value
	        double targetLeftPercentage = (desiredValue / (double) sliderMaxValue) * 100;
	        System.out.println("Target Left Position (calculated): " + targetLeftPercentage + "%");

	        // Using JavaScript to set the slider's value directly
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].setAttribute('aria-valuenow', arguments[1]);", slider, desiredValue);
	        System.out.println("Slider value set to: " + desiredValue);

	        // Optionally: Update the visual appearance of the slider by setting the position
	        js.executeScript("arguments[0].style.left = arguments[1] + '%';", slider, targetLeftPercentage);

	        // Wait for the UI to update
	        Thread.sleep(500);  // Adjust the delay as needed

	        // Verify the position by printing the new value
	        WebElement updatedSliderThumb = driver.findElement(By.cssSelector("span.MuiSlider-thumb"));
	        String style = updatedSliderThumb.getAttribute("style");
	        String leftStyle = style.split("left: ")[1].split("%")[0];
	        double updatedLeftPercentage = Double.parseDouble(leftStyle);
	        System.out.println("Slider moved to position: " + updatedLeftPercentage + "%");
	        */

	   /* } catch (Exception e) {
	        System.out.println("Error while moving the slider: " + e.getMessage());
	    }*/
	


