package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScriptPageInTextToVideo extends Basepage {
	public ScriptPageInTextToVideo(WebDriver driver) {
		super(driver);
	}
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	Actions act=new Actions(driver);
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	@FindBy(xpath="//button[text()='+ Add New']/following::button[5]") 
	WebElement nextc;
	
	@FindBy(xpath="//textarea[@placeholder='Enter text (max 500 characters)']") 
	WebElement AIPrompt;
	
	//div[text()='Your Script']
	@FindBy(xpath="//div[text()='Your Script']") 
	WebElement urscript;
	
	
	@FindBy(xpath="//button[text()='Generate with AI']") 
	WebElement generatewithAIbutton;
	
	//button[text()='+ Add New']/following::button[7]
	@FindBy(xpath="//div[text()='AI Prompt']/following::button[2]") 
	WebElement NextButtonInScript;
	
	@FindBy(xpath="//div[text()='AI Prompt']/following::button[1]") 
	WebElement RetryButtonInScript;
	
	
	
	public void nexbuttonInTagName() {
	    

	    // Scroll to the bottom of the page
	    js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

	    try {
	        // Click the next button using JavaScript
	        js.executeScript("arguments[0].click();", nextc);
	        System.out.println("Clicked on the next button and navigated to the  Script page.");

	       
	    } catch (NoSuchElementException e) {
	        System.out.println("Next button not found: " + e.getMessage());
	        throw new RuntimeException("Next button not found", e);
	    } catch (Exception e) {
	        System.out.println("An unexpected error occurred while trying to navigate to the Script page: " + e.getMessage());
	        throw new RuntimeException("An unexpected error occurred while trying to navigate to the Script page", e);
	    }
	}
	
	public void clickOnScript(String script, String AIprompt, String YourPrompt, String writingstyle, String duration) {
	    System.out.println("Executing script: " + script);
	    try {
	        // Check if the script is "AI Prompt"
	        if (script.equalsIgnoreCase("AI Prompt")) {
	            System.out.println("Selecting AI Prompt script.");
	            AIPrompt.sendKeys(AIprompt);
	            System.out.println("Entered AI Prompt: " + AIprompt);
	            
	            // Construct XPath for writing style
	            String expath = "//div[text()='" + writingstyle + "']";
	            System.out.println("Finding element using XPath: " + expath);
	            
	            WebElement ele = driver.findElement(By.xpath(expath));
	            System.out.println("Element found. Clicking on it.");
	            ele.click();
	            System.out.println("Clicked on writing style: " + writingstyle);
	            
	            //duration = "11"; // Example of setting the target duration to 25 seconds.
	            double desiredValue = Double.parseDouble(duration); // Your target duration (e.g., 25 sec)
	            double maxValue = 30.0; // Max value on the slider
	            
	            // Debugging statements for slider duration
	            System.out.println("Desired Duration: " + desiredValue);
	            System.out.println("Max Value: " + maxValue);
	            
	            try {
	                // Locate the slider handle and track
	                WebElement sliderHandle = driver.findElement(By.cssSelector(".rs-slider-handle"));
	                WebElement sliderTrack = driver.findElement(By.cssSelector(".rs-slider-bar"));
	                
	                // Get the dimensions of the slider
	                int sliderWidth = sliderTrack.getSize().getWidth(); // Full width of the slider track
	                int handleWidth = sliderHandle.getSize().getWidth(); // Handle width for adjustment
	                
	                // Step 1: Move the slider from 5 seconds to 0 seconds
	                double currentPercentage = (5.0 / maxValue) * 100; // Current position is at 5 seconds
	                int currentOffset = (int) ((currentPercentage / 100) * (sliderWidth - handleWidth)); // Offset for 5 sec
	                int zeroOffset = -(currentOffset); // Offset to move back to 0 sec (negative of the current offset)
	                
	                System.out.println("Moving slider to 0 seconds.");
	                
	                // Move slider to 0 seconds
	                Actions actions = new Actions(driver);
	                actions.clickAndHold(sliderHandle)
	                       .moveByOffset(zeroOffset, 0) // Move back to the zero position
	                       .release()
	                       .perform();
	                System.out.println("Slider moved to 0 seconds.");
	                
	                // Step 2: Now move the slider from 0 seconds to the target duration (25 seconds)
	                double targetPercentage = (desiredValue / maxValue) * 100; // Percentage for the target duration
	                int targetOffset = (int) ((targetPercentage / 100) * (sliderWidth - handleWidth)); // Target offset for 25 sec
	                
	                System.out.println("Moving slider to " + desiredValue + " seconds.");
	                
	                // Move slider to the desired duration (25 seconds in this case)
	                actions.clickAndHold(sliderHandle)
	                       .moveByOffset(targetOffset, 0) // Move by the calculated target offset
	                       .release()
	                       .perform();
	                try {
	                    WebElement el = driver.findElement(By.xpath("//div[@role='tooltip']"));
	                    wait.until(ExpectedConditions.visibilityOf(el));

	                    // Check if the slider has moved to the desired value
	                    if ((el.getText() + ".0").equals(duration)) {
	                        System.out.println("Slider moved to: " + desiredValue + " seconds. Current value: " + el.getText());
	                    } else {
	                        System.out.println("Slider not moved to: " + desiredValue + " seconds. Current value: " + el.getText());
	                    }
	                    
	                } catch (NoSuchElementException e) {
	                    // Exception handling for element not found
	                    System.err.println("Slider pointer not available/unable to find. Please check Excel data & code. Error: " + e.getMessage());
	                } catch (TimeoutException e) {
	                    // Exception handling for wait timeout
	                    System.err.println("Timed out while waiting for slider pointer to become visible. Please check Excel data & code. Error: " + e.getMessage());
	                } catch (Exception e) {
	                    // General exception handling
	                    System.err.println("An unexpected error occurred while interacting with the slider. Please check Excel data & code. Error: " + e.getMessage());
	                }

	            } catch (Exception e) {
	                System.err.println("Error while setting slider value: " + e.getMessage());
	            }
	        } else {
	            // Handle user-defined script
	            System.out.println("Selecting user-defined script.");
	            urscript.click();
	            System.out.println("User-defined script clicked.");
	            AIPrompt.sendKeys(YourPrompt);
	            System.out.println("Entered Your Prompt: " + YourPrompt);
	            WebElement ele=driver.findElement(By.xpath("//div[@role='tooltip']"));
	            System.out.println("Depends on your characters enterd its duration is : "+ele.getText());
	            RetryButtonInScript.click();
	            
	            
	            
	        }
	        
	        // Log the duration that was set
	        System.out.println("Duration set to: " + duration);
	        
	    } catch (NoSuchElementException e) {
	        System.err.println("Error: Element not found - " + e.getMessage());
	        throw new RuntimeException("Element not found: " + e.getMessage());
	    } catch (WebDriverException e) {
	        System.err.println("WebDriver error occurred: " + e.getMessage());
	        throw new RuntimeException("WebDriver error: " + e.getMessage());
	    } catch (Exception e) {
	        System.err.println("An unexpected error occurred: " + e.getMessage());
	        throw new RuntimeException("Unexpected error: " + e.getMessage());
	    }
	}

	public void ClickOnGenerateWithAIButtonInScrpt() {
	    try {
	        System.out.println("Attempting to scroll to and click the 'Generate with AI' button.");
	        
	        // Scroll into view and click in one line
	        js.executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", generatewithAIbutton);
	        
	        System.out.println("'Generate with AI' button scrolled into view and clicked successfully.");
	        
	    } catch (Exception e) {
	        System.err.println("Error occurred while interacting with 'Generate with AI' button: " + e.getMessage());
	        throw new RuntimeException("Failed to scroll and click 'Generate with AI' button.");
	    }
	}
	public void ClickOnNextButtonInYOurScript() {
	    try {
	        // Log the start of the method
	        System.out.println("Attempting to click on the Next button in the script.");
	        
	        // Wait until the Next button is visible and clickable
	        wait.until(ExpectedConditions.visibilityOf(NextButtonInScript));
	        System.out.println("Next button is visible.");
	        
	        wait.until(ExpectedConditions.elementToBeClickable(NextButtonInScript));
	        System.out.println("Next button is clickable.");
	        
	        // Click the Next button
	        NextButtonInScript.click();
	        System.out.println("Successfully clicked on the Next button in the script.");
	        
	    } catch (NoSuchElementException e) {
	        System.err.println("Error: Next button not found - " + e.getMessage());
	        throw new RuntimeException("Next button not found: " + e.getMessage());
	        
	    } catch (TimeoutException e) {
	        System.err.println("Error: Timed out waiting for Next button to be clickable - " + e.getMessage());
	        throw new RuntimeException("Timed out waiting for Next button: " + e.getMessage());
	        
	    } catch (WebDriverException e) {
	        System.err.println("Error: WebDriver encountered an issue while interacting with the Next button - " + e.getMessage());
	        throw new RuntimeException("WebDriver error: " + e.getMessage());
	        
	    } catch (Exception e) {
	        System.err.println("An unexpected error occurred: " + e.getMessage());
	        throw new RuntimeException("Unexpected error: " + e.getMessage());
	    }
	}
	
	
	
}
