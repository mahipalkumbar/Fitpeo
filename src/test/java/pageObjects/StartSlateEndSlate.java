package pageObjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StartSlateEndSlate extends Basepage {
	public StartSlateEndSlate(WebDriver driver) {
		super(driver);
	}
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	Actions act=new Actions(driver);
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	@FindBy(xpath="//label[@for='uploadPlate_0']") 
	WebElement uploadslate;
	
	
	@FindBy(xpath="//div[text()='Start Slate']/following::button[1]") 
	WebElement startslatenextbutton;
	
	
	
	@FindBy(xpath="//button[text()='Generate']") 
	WebElement generatebutton;
	
	public void UploadStartEndSlateFromSystem(String selection, String path1, String path2) throws AWTException {
	    System.out.println("Starting UploadStartEndSlateFromSystem method for selection: " + selection);
	    
	    try {
	        String expath = "//div[text()='End Slate']";
	        WebElement ele = driver.findElement(By.xpath(expath));
	        
	        if (selection.equalsIgnoreCase("Start Slate")) {
	            System.out.println("Selected Start Slate. Uploading Start Slate...");
	            SelectingStartEndSlateFromSystem(path1);
	        } else if (selection.equalsIgnoreCase("End Slate")) {
	            System.out.println("Selected End Slate. Waiting for End Slate element to be visible.");
	            wait.until(ExpectedConditions.visibilityOf(ele));
	            System.out.println("End Slate element is visible. Clicking on it...");
	            ele.click();
	            SelectingStartEndSlateFromSystem(path2);
	        } else {
	            System.out.println("Both Start and End Slate selected. Uploading both...");
	            SelectingStartEndSlateFromSystem(path1);
	            wait.until(ExpectedConditions.visibilityOf(ele));
	            System.out.println("End Slate element is visible. Clicking on it...");
	            ele.click();
	            SelectingStartEndSlateFromSystem(path2);
	        }
	    } catch (NoSuchElementException e) {
	        System.err.println("Error: Element not found for selection: " + selection + " - " + e.getMessage());
	        throw new RuntimeException("Error: Element not found for the selection: " + selection, e);
	    } catch (Exception e) {
	        System.err.println("An unexpected error occurred while handling Start/End Slate: " + e.getMessage());
	        throw new RuntimeException("Error during Start/End Slate process for selection: " + selection, e);
	    }
	}

	public void SelectingStartEndSlateFromSystem(String path) throws AWTException {
	    System.out.println("Starting SelectingStartEndSlateFromSystem for file path: " + path);
	    
	    try {
	        // Wait until the upload slate element is visible
	        wait.until(ExpectedConditions.visibilityOf(uploadslate));
	        System.out.println("Upload slate element is visible.");
	        
	        // Click the browse from system button
	        uploadslate.click();
	        System.out.println("Clicked on Uploadslate button.");

	        // Use Robot class to handle the file upload dialog
	        Robot robot = new Robot();
	        System.out.println("Robot instance created for handling file dialog.");

	        // Copy the file path to clipboard
	        StringSelection filePath = new StringSelection(path);
	        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);
	        System.out.println("File path copied to clipboard: " + path);

	        // Add a small delay to ensure clipboard is ready
	        Thread.sleep(2000); // 2 seconds delay to ensure the clipboard content is processed

	        // Simulate Ctrl+V to paste the file path in the file dialog
	        System.out.println("Pasting the file path using Robot (Ctrl+V)...");
	        robot.keyPress(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_CONTROL);

	        // Add a longer delay to ensure the file path is pasted properly before pressing Enter
	        Thread.sleep(3000); // Increased delay to ensure the file dialog processes the pasted path

	        // Simulate pressing Enter to confirm the file upload
	        System.out.println("Pressing 'Enter' to confirm file selection.");
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.keyRelease(KeyEvent.VK_ENTER);

	        // Add a delay after pressing Enter to ensure the file dialog completes the action
	        System.out.println("Waiting for file upload dialog to process the selected file...");
	        Thread.sleep(5000); // 5 seconds to ensure the dialog processes the file selection

	        System.out.println("File upload process completed successfully for path: " + path);

	    } catch (InterruptedException e) {
	        System.err.println("Thread was interrupted: " + e.getMessage());
	        Thread.currentThread().interrupt();
	        throw new RuntimeException("Thread interruption during file upload", e);
	    } catch (AWTException e) {
	        System.err.println("Error with Robot class while handling file upload: " + e.getMessage());
	        throw new RuntimeException("Robot class error during file upload process", e);
	    } catch (Exception e) {
	        System.err.println("An unexpected error occurred during file upload: " + e.getMessage());
	        throw new RuntimeException("Error during file upload process", e);
	    }
	}

	
	public void clickOnNextButtonInSlate() {
	    try {
	        // Wait for the 'Next' button to be visible
	        System.out.println("Waiting for the 'Next' button to be visible.");
	        wait.until(ExpectedConditions.visibilityOf(startslatenextbutton));
	        System.out.println("'Next' button is visible now.");
	        
	        // Scroll to the bottom of the page first
	        System.out.println("Scrolling to the bottom of the page.");
	        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

	        // Scroll 'Next' button into view and click it using JavaScript Executor
	        System.out.println("Scrolling 'Next' button into view and clicking using JavaScript.");
	        js.executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", startslatenextbutton);
	        System.out.println("'Next' button clicked successfully using JavaScript.");

	    } catch (TimeoutException e) {
	        // Handle case when the button is not visible within the wait time
	        System.err.println("Error: 'Next' button was not visible within the expected time.");
	        throw new RuntimeException("Timeout: 'Next' button did not appear.", e);

	    } catch (NoSuchElementException e) {
	        // Handle case when the button is not found
	        System.err.println("Error: 'Next' button not found on the page.");
	        throw new RuntimeException("Element Not Found: Unable to locate 'Next' button.", e);

	    } catch (WebDriverException e) {
	        // General WebDriver exception handling
	        System.err.println("Error: WebDriver encountered an issue while interacting with the 'Next' button.");
	        throw new RuntimeException("WebDriver Error: Unable to interact with 'Next' button.", e);

	    } catch (Exception e) {
	        // Catch any unexpected exceptions
	        System.err.println("Error: An unexpected error occurred while clicking the 'Next' button.");
	        throw new RuntimeException("Unexpected Error: Unable to click 'Next' button.", e);
	    }
	}



	
	public void ClickOnGenerateButton() {
	    try {
	        System.out.println("Waiting for the 'Generate' button to be clickable...");
	        // Wait for the element to be clickable
	 wait.until(ExpectedConditions.visibilityOf(generatebutton));

	        wait.until(ExpectedConditions.elementToBeClickable(generatebutton));
	        System.out.println("'Generate' button is clickable now. Attempting to click...");

	        // Use JavaScript Executor to click the button
	        System.out.println("Clicking 'Generate' button using JavaScript Executor.");
	        js.executeScript("arguments[0].click();", generatebutton);
	        System.out.println("Successfully clicked the 'Generate' button using JavaScript.");

	    } catch (TimeoutException e) {
	        System.err.println("Error: Timeout waiting for 'Generate' button to be clickable.");
	        throw new RuntimeException("Timeout while waiting for 'Generate' button: " + e.getMessage());

	    } catch (NoSuchElementException e) {
	        System.err.println("Error: 'Generate' button not found on the page.");
	        throw new RuntimeException("Element not found: 'Generate' button - " + e.getMessage());

	    } catch (ElementNotInteractableException e) {
	        System.err.println("Error: 'Generate' button is visible but not interactable.");
	        throw new RuntimeException("'Generate' button not interactable: " + e.getMessage());

	    } catch (WebDriverException e) {
	        System.err.println("WebDriver error occurred while interacting with 'Generate' button: " + e.getMessage());
	        throw new RuntimeException("WebDriver error: " + e.getMessage());

	    } catch (Exception e) {
	        System.err.println("An unexpected error occurred: " + e.getMessage());
	        throw new RuntimeException("Unexpected error while clicking 'Generate' button: " + e.getMessage());
	    }
	}


	
}

