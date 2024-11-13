package pageObjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VideoVistaImageToImage extends Basepage{
	public VideoVistaImageToImage(ThreadLocal<WebDriver> driver) {
		super(driver);
	}
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	Actions act=new Actions(driver);
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	@FindBy(xpath="//button[text()='Upload']") 
	WebElement uploadbutton;
	
	
	
	@FindBy(xpath="//div[@class='py-3 md:px-7 sm:px-4 amountOfMotionn']//div[@class='rs-slider-handle']") 
	WebElement sliderHandleamountofmotion; // slider dot
	
	
	@FindBy(xpath="//div[@class='py-3 md:px-7 sm:px-4 amountOfMotionn']//div[@class='rs-slider-bar']") 
	WebElement sliderBaramountofmotion; // slider bar or full lider length
	
	@FindBy(xpath="//div[@class=' flex flex-wrap gap-3 my-3 overflow-y-auto overflow-x-hidden h-[280px]']//div//img")
	List<WebElement> selectimgfromasset;
	
	@FindBy(xpath="//button[text()='Upload from Drive']") 
	WebElement uploadfromdrivebutton;
	
	@FindBy(xpath="//div[text()='Animation Style']/following::button[10]") 
	WebElement animationstylenextbutton;
	
	@FindBy(xpath="//div[text()='Browse from System']")  
	WebElement browseFromSystem;
	
	
	@FindBy(xpath="//div[text()='Amount of motion']/following::button[1]") 
	WebElement amountofmotionnextbutton;
	
	@FindBy(xpath="//div[text()='Animation Duration']/following::button[1]") 
	WebElement animationdurationnextbutton;
	
	public void ClickOnNextButtonOnAnimationDuration() {
	    try {
	        // Attempting to click on the 'Next' button
	        System.out.println("Attempting to click on the 'Next' button...");
	        animationdurationnextbutton.click();
	        System.out.println("Successfully clicked on the 'Next' button.");
	    } catch (Exception e) {
	        // Print the exception message and throw a custom error message
	        System.out.println("Failed to click on the 'Next' button: " + e.getMessage());
	        throw new RuntimeException("Error: Could not click on the 'Next' button due to an unexpected issue.");
	    }
	}
	
	
	public void ClickOnNextButtonOnAmountOfMotion() {
	    try {
	        // Attempting to click on the 'Next' button
	        System.out.println("Attempting to click on the 'Next' button...");
	        amountofmotionnextbutton.click();
	        System.out.println("Successfully clicked on the 'Next' button.");
	    } catch (Exception e) {
	        // Print the exception message and throw a custom error message
	        System.out.println("Failed to click on the 'Next' button: " + e.getMessage());
	        throw new RuntimeException("Error: Could not click on the 'Next' button due to an unexpected issue.");
	    }
	}
	
	
	public void clickOnUploadButton() {
	    try {
	        if (uploadbutton.isDisplayed() && uploadbutton.isEnabled()) {
	            uploadbutton.click();
	            System.out.println("Upload button clicked successfully.");
	        } else {
	            System.out.println("Upload button is either not displayed or not enabled.");
	        }
	    } catch (WebDriverException e) {
	        System.err.println("Failed to click on the upload button. Exception: " + e.getMessage());
	        throw e; // Rethrow the exception if needed
	    }
	}	
	
	public void selectAnimationStyle(String animationStyleSelection) {
	    try {
	        WebElement option = driver.findElement(By.xpath("//div[text()='" + animationStyleSelection + "']"));
	        option.click();
	        System.out.println("Successfully selected the animation style: " + animationStyleSelection);
	    } catch (Exception e) {
	        System.err.println("Failed to select the animation style: " + animationStyleSelection);
	        System.err.println("Error: " + e.getMessage());
	    }
	}
	
	public void ClickOnAnimationstylenextbutton() {
		try {
			animationstylenextbutton.click();
	        System.out.println("Successfully Clicked Next Button In Animation Style of Image to Video tool");
	    } catch (Exception e) {
	        System.err.println("Failed to Click Next Button In Animation Style of Image to Video tool");
	        System.err.println("Error: " + e.getMessage());
	    }
	}

	
	String SelectedIMGSRC;
	private int imageIndex = 1; // Moved imageIndex to class level to maintain state across calls

	public void ClickOnProductImageUploadButton(String selectinguploadoption, String path) {
	    try {

	        // Handling the "Use an Asset" option
	        if (selectinguploadoption.equalsIgnoreCase("Upload from Drive")) {
	            System.out.println("Option selected: 'Upload from Drive'.");

	            int totalImages = selectimgfromasset.size();  // Get the total number of images
	            System.out.println("Total images available: " + totalImages);

	            // Reset image index if it exceeds the total number of images
	            if (imageIndex > totalImages) {
	                System.out.println("Image index exceeded total images, resetting index to 1.");
	                imageIndex = 1;
	            }

	            // Select the image at the current index
	            WebElement currentImage = selectimgfromasset.get(imageIndex - 1);  // Adjust for 0-based indexing
	            SelectedIMGSRC = currentImage.getAttribute("src");
	            System.out.println("Selected image source: " + SelectedIMGSRC);

	            // Scroll to the current image to ensure it's in view
	            System.out.println("Scrolling to the selected image at index: " + imageIndex);
	            js.executeScript("arguments[0].scrollIntoView(true);", currentImage);

	            // Click on the image
	            System.out.println("Clicking on the selected image...");
	            currentImage.click();
	            System.out.println("Image clicked successfully. Selected Image Index: " + imageIndex);

	            // Increment the image index for the next iteration
	            imageIndex++;
	            System.out.println("Incremented image index to: " + imageIndex);
	            uploadfromdrivebutton.click();
	            System.out.println("Clicked on Use An Assset Button");;
	            
	        } else {
	        	// wait.until(ExpectedConditions.visibilityOfAllElements(selectimgfromasset));
	            // Handle file upload logic for "Browse from System" option
	            System.out.println("Option selected: 'Browse from System'. Initiating file upload...");

	            // Click the browse from system button
	            browseFromSystem.click();
	            System.out.println("Clicked on 'Browse from System' button.");

	            // Use Robot class for handling file upload dialog
	            Robot robot = new Robot();
	            System.out.println("Robot instance created for handling file dialog.");
	            Thread.sleep(2000);
	            // Copy the file path to clipboard
	            StringSelection filePath = new StringSelection(path);
	            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);
	            System.out.println("File path copied to clipboard: " + path);
	            Thread.sleep(4000);
	            // Simulate Ctrl+V to paste the file path in the file dialog
	            System.out.println("Pasting the file path using Robot (Ctrl+V)...");
	            robot.keyPress(KeyEvent.VK_CONTROL);
	            robot.keyPress(KeyEvent.VK_V);
	            robot.keyRelease(KeyEvent.VK_V);
	            robot.keyRelease(KeyEvent.VK_CONTROL);
	            Thread.sleep(4000);
	            // Simulate pressing Enter to confirm the file upload
	            System.out.println("Pressing 'Enter' to confirm file selection.");
	            robot.keyPress(KeyEvent.VK_ENTER);
	            robot.keyRelease(KeyEvent.VK_ENTER);
	            Thread.sleep(2000);
	            System.out.println("File upload process completed.");
	        }
	    } catch (NoSuchElementException e) {
	        String errorMsg = "The element for product image upload button or selected image was not found.";
	        System.err.println(errorMsg);
	        throw new RuntimeException(errorMsg, e);
	    } catch (StaleElementReferenceException e) {
	        String errorMsg = "The element reference became stale while interacting with the product image.";
	        System.err.println(errorMsg);
	        throw new RuntimeException(errorMsg, e);
	    } catch (IndexOutOfBoundsException e) {
	        String errorMsg = "Image index out of bounds: " + imageIndex + ". Total images available: " + selectimgfromasset.size();
	        System.err.println(errorMsg);
	        throw new RuntimeException(errorMsg, e);
	    } catch (AWTException e) {
	        String errorMsg = "An error occurred while using the Robot class for file upload.";
	        System.err.println(errorMsg);
	        throw new RuntimeException(errorMsg, e);
	    } catch (Exception e) {
	        String errorMsg = "An unexpected error occurred while selecting the product image.";
	        System.err.println(errorMsg);
	        throw new RuntimeException(errorMsg, e);
	    }
	}
	
	public void moveSliderToValue(int targetValue) {
	    // Get the width of the slider bar and the handle
	    int maxSliderWidth = sliderBaramountofmotion.getSize().getWidth();
	    int handleWidth = sliderHandleamountofmotion.getSize().getWidth();

	    // Calculate the current position of the slider handle as a percentage (assuming it's at 20% initially)
	    String styleAttribute = sliderHandleamountofmotion.getAttribute("style");
	    String leftValueStr = styleAttribute.substring(styleAttribute.indexOf("left:") + 5, styleAttribute.indexOf("%"));
	    double currentPercentage = Double.parseDouble(leftValueStr.trim());

	    // Calculate the current offset of the handle based on its percentage
	    int currentOffset = (int) ((currentPercentage / 100) * (maxSliderWidth - handleWidth));

	    // Calculate the offset to move the handle to the zero position
	    int zeroOffset = -currentOffset;

	    // Move the handle to the zero position (0%)
	    Actions actions = new Actions(driver);
	    actions.clickAndHold(sliderHandleamountofmotion)
	           .moveByOffset(zeroOffset, 0)  // Move the handle to the 0% position
	           .release()
	           .perform();
	    System.out.println("Slider moved to 0%.");

	    // Wait for the slider to be fully at 0% (optional, you can adjust the delay)
	    try {
	        Thread.sleep(500);  // Adjust the delay as needed for the slider to settle at 0%
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }

	    // After moving to 0, move the slider to the target value (incrementing by 10 each time)
	    int targetOffset = (int) ((targetValue / 100.0) * (maxSliderWidth - handleWidth));

	    // Move the handle to the target position
	    int increment = 10; // Increment by 10 as per the requirement
	    while (currentPercentage < targetValue) {
	        currentPercentage += increment;

	        // Calculate the offset for the next position
	        int moveOffset = (int) ((currentPercentage / 100.0) * (maxSliderWidth - handleWidth));

	        // Perform the action to move the slider by the calculated offset
	        actions.clickAndHold(sliderHandleamountofmotion)
	               .moveByOffset(moveOffset - currentOffset, 0) // Move the handle horizontally
	               .release()
	               .perform();
	        
	        // Update the current offset to the new position
	        currentOffset = moveOffset;

	        System.out.println("Slider moved to: " + currentPercentage + "%");

	        // Optional: You can add a delay here if needed to allow the slider to move smoothly
	        try {
	            Thread.sleep(100);  // Adjust the delay as needed
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	    System.out.println("Slider reached target value: " + targetValue + "%.");
	}

        
        
        
        
		
    

	   /* //int target = Integer.parseInt(targetPercentage); // Target position in percentage (e.g., 30%)
	    System.out.println("Target slider position: " + target + "%");

	    // Get the full width of the slider and handle
	    int maxSliderWidth = sliderBaramountofmotion.getSize().getWidth();
	    int handleWidth = sliderHandleamountofmotion.getSize().getWidth();
	    int targetOffset = (int) ((target / 100.0) * (maxSliderWidth - handleWidth));

	    Actions actions = new Actions(driver);
	    
	    double currentPercentage = (20.0 /100) * 100;
        int currentOffset1 = (int) ((currentPercentage / 100) * (maxSliderWidth - handleWidth)); // Offset for 5 sec
        int zeroOffset = -(currentOffset1);
        actions.clickAndHold(sliderHandleamountofmotion)
        .moveByOffset(zeroOffset, 0) // Move back to the zero position
        .release()
        .perform();
        Thread.sleep(2000);
	    
	    try {
	        // Click and hold the slider handle
	        actions.clickAndHold(sliderHandleamountofmotion).perform();
	        Thread.sleep(500); // Pause to ensure click and hold registers
	        
	        // Start with the current offset of the slider
	        int currentOffset = getCurrentSliderOffset();
	        System.out.println("Initial slider offset: " + currentOffset + " pixels");

	        // Increment size to move the slider gradually
	        int increment = 2; // Increment value (in pixels)
	        while (currentOffset < targetOffset) {
	            actions.moveByOffset(increment, 0).perform();
	            Thread.sleep(100); // Pause to ensure the move is registered
	            
	            currentOffset = getCurrentSliderOffset();
	            System.out.println("Current slider offset after increment: " + currentOffset + " pixels");

	            // Check if the current offset is close to the target position
	            if (Math.abs(currentOffset - targetOffset) <= increment) {
	                break; // Stop if the current position is close enough to the target
	            }
	        }
	        
	        // Release the slider handle once the target is reached or exceeded
	        actions.release().perform();
	        System.out.println("Slider moved to the target position: " + target + "%");

	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }*/
	

	/*Helper method to get the current offset in pixels from the style attribute
	private int getCurrentSliderOffset() {
	    String styleValue = sliderHandleamountofmotion.getAttribute("style");
	    String pixelStr = styleValue.replaceAll("[^\\d]", ""); // Extract numeric part from "left: XXpx"
	    return Integer.parseInt(pixelStr);
	}*/

	/**/
		   // Thread.sleep(5000);
	    // Step 2: Move to the target percentage
	   /* int targetOffset = (int) ((targetPercentage / 100.0) * (sliderWidth - handleWidth));
	    actions.clickAndHold(sliderHandleamountofmotion)
	           .moveByOffset(targetOffset, 0)
	           .release()
	           .perform();

	    // Verify the slider moved to the expected position
	    String finalValue = sliderHandle.findElement(By.tagName("input")).getAttribute("value");
	    System.out.println("Slider moved to position: " + finalValue + "%");

	    if (Integer.parseInt(finalValue) == targetPercentage) {
	        System.out.println("Slider successfully moved to " + targetPercentage + "%.");
	    } else {
	        System.out.println("Slider move failed. Expected: " + targetPercentage + "%, but got: " + finalValue + "%.");
	    }
	}*/

         //System.out.println("Slider moved to 0 seconds.");
	    // Locate the slider handle and the input range element
	   /* WebElement sliderHandle = driver.findElement(By.cssSelector(".rs-slider-handle"));
	    WebElement sliderInput = driver.findElement(By.cssSelector(".rs-slider-handle input[type='range']"));
	    
	    // Execute JavaScript to move slider to 0%
	    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	    
	    // Set slider handle style to 0% and update the input's value to 0
	    jsExecutor.executeScript(
	        "arguments[0].style.left = '0%'; arguments[1].value = '0';", 
	        sliderHandle, sliderInput
	    );

	    // Optionally trigger a change event to ensure the UI updates
	    jsExecutor.executeScript("arguments[0].dispatchEvent(new Event('change'));", sliderInput);

	    // Verify the slider moved to the 0% position
	    String sliderHandlePosition = sliderHandle.getAttribute("style");
	    System.out.println("Slider handle position after JavaScript move: " + sliderHandlePosition);

	    // Confirm the value set in the input field
	    String inputValue = sliderInput.getAttribute("value");
	    System.out.println("Slider input value: " + inputValue);
	    
	    if ("0".equals(inputValue)) {
	        System.out.println("Slider successfully moved to 0%.");
	    } else {
	        System.out.println("Slider move failed. Current input value: " + inputValue + " (Expected: 0)");
	    }
	}

	
	
	 public void AdjustingAmountofMotionSlider(String targetValue1) {
	        // Convert targetValue from String to int
	        int targetValue = Integer.parseInt(targetValue1);
	        
	        WebElement sliderHandle = driver.findElement(By.cssSelector(".rs-slider-handle"));
	        WebElement sliderTooltip = driver.findElement(By.cssSelector(".rs-slider-tooltip"));
	        
	        // Parse the current slider value from the tooltip text
	        int currentValue = Integer.parseInt(sliderTooltip.getText());

	        // Initialize the Actions class to perform drag-and-drop
	        Actions actions = new Actions(driver);

	        // Move slider handle until the target value is reached
	        while (currentValue != targetValue) {
	            // Determine whether to move forward or backward
	            if (currentValue < targetValue) {
	                actions.dragAndDropBy(sliderHandle, 5, 0).perform();  // Move to the right
	            } else {
	                actions.dragAndDropBy(sliderHandle, -5, 0).perform();  // Move to the left
	            }

	            // Re-check the current value after moving
	            currentValue = Integer.parseInt(sliderTooltip.getText());
	        }*/
	    

	




	
	public void AdjustAnimationDurationSlider(String target) {
		
        WebElement sliderHandle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".rs-slider-handle")));
        
        int targetValue = 4; // Set your desired slider value (between 0 to 6)

        // Calculate the target position in percentage based on the value (0 to 6 range)
        double targetPercentage = (targetValue / 6.0) * 100;

        // Use JavaScript to set the slider position directly
        
        js.executeScript("arguments[0].style.left='" + targetPercentage + "%';", sliderHandle);

        // Optionally, use Actions to move the slider for more realistic interaction
        WebElement sliderBar = driver.findElement(By.cssSelector(".rs-slider-bar"));
        int sliderBarWidth = sliderBar.getSize().getWidth();
        int xOffset = (int) (sliderBarWidth * (targetPercentage / 100.0));

        act.clickAndHold(sliderHandle).moveByOffset(xOffset, 0).release().perform();

        // Verify the slider position
        String leftStyle = sliderHandle.getAttribute("style");
        System.out.println("Slider moved to position: " + leftStyle);
	}
}
