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
	public VideoVistaImageToImage(WebDriver driver) {
		super(driver);
	}
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	Actions act=new Actions(driver);
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	@FindBy(xpath="//button[text()='Upload']") 
	WebElement uploadbutton;
	
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
	
	
	public void AdjustingAmountofMotionSlider(String targetPosition) {
	    int targetValue;

	    // Parse the target position, default to 20% if invalid
	    try {
	        targetValue = Integer.parseInt(targetPosition);
	    } catch (NumberFormatException e) {
	        System.err.println("Invalid target position provided. Defaulting to 20.");
	        targetValue = 20;
	    }

	    // Ensure target value is within 0-100%
	    if (targetValue < 0 || targetValue > 100) {
	        throw new IllegalArgumentException("Target position must be between 0 and 100.");
	    }

	    // Locate elements
	    WebElement sliderHandle = driver.findElement(By.cssSelector(".rs-slider-handle"));
	    WebElement sliderBar = driver.findElement(By.cssSelector(".rs-slider-bar"));

	    // Get the width of the slider bar for accurate movement
	    int sliderWidth = sliderBar.getSize().getWidth();
	    if (sliderWidth == 0) {
	        throw new IllegalStateException("Slider bar width is zero. Cannot move slider.");
	    }

	    // Calculate the target position in pixels (based on target value as percentage)
	    int targetLocation = (int) ((targetValue / 100.0) * sliderWidth);
	    
	    // Log the calculated positions and target for debugging
	    System.out.println("Slider bar width (in pixels): " + sliderWidth);
	    System.out.println("Calculated target position (in pixels): " + targetLocation);

	    // Get the initial position of the slider handle before moving
	    int initialSliderPosition = sliderHandle.getLocation().getX();
	    System.out.println("Initial slider handle position: " + initialSliderPosition + "px");

	    // Calculate the required drag offset from the current position to the target position
	    int dragOffset = targetLocation - initialSliderPosition;
	    System.out.println("Drag offset (pixels) to move slider: " + dragOffset);

	    // Perform the drag action
	    act.clickAndHold(sliderHandle)
	       .moveByOffset(dragOffset, 0)  // Move by the calculated offset
	       .release()
	       .perform();

	    // Verify that the slider moved to the expected position
	    String progressBarWidth = sliderBar.getAttribute("style");
	    System.out.println("Slider progress after move: " + progressBarWidth);

	    // Log the final position of the slider handle for debugging
	    int finalSliderPosition = sliderHandle.getLocation().getX();
	    System.out.println("Slider handle position after move: " + finalSliderPosition + "px");

	    // Check if the slider moved to the expected value
	    if (finalSliderPosition == targetLocation) {
	        System.out.println("Slider successfully moved to: " + targetValue + "%");
	    } else {
	        System.out.println("Slider move failed. Current position: " + finalSliderPosition + " (Expected: " + targetLocation + ")");
	    }
	}




	
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
