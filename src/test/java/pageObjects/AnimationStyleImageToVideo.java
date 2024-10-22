package pageObjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AnimationStyleImageToVideo extends Basepage{
	public AnimationStyleImageToVideo(WebDriver driver) {
		super(driver);
	}
	JavascriptExecutor js = (JavascriptExecutor) driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	
	@FindBy(xpath="//button[normalize-space()='Upload']")
	WebElement productimageuploadbutton;
	
	@FindBy(xpath="//div[@class=' flex flex-wrap gap-3 my-3 overflow-y-auto overflow-x-hidden h-[280px]']//div//img")
	List<WebElement> selectimgfromasset;
	
	@FindBy(xpath="//button[text()='Browse from System']")  
	WebElement browseFromSystem;
	
	
	@FindBy(xpath="//div[@class='bg-[#28134B] w-[95%] rounded-lg h-[95%] flex justify-center items-center mt-4']//img")  
	WebElement uploadedimage;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	String SelectedIMGSRC;
	private int imageIndex = 1; // Moved imageIndex to class level to maintain state across calls

	public void ClickOnProductImageUploadButton(String selectinguploadoption, String path) {
	    try {
	        // Clicking the product image upload button
	        System.out.println("Clicking on the product image upload button...");
	        productimageuploadbutton.click();

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
	        } else {
	        	 wait.until(ExpectedConditions.visibilityOfAllElements(selectimgfromasset));
	            // Handle file upload logic for "Browse from System" option
	            System.out.println("Option selected: 'Browse from System'. Initiating file upload...");

	            // Click the browse from system button
	            browseFromSystem.click();
	            System.out.println("Clicked on 'Browse from System' button.");

	            // Use Robot class for handling file upload dialog
	            Robot robot = new Robot();
	            System.out.println("Robot instance created for handling file dialog.");

	            // Copy the file path to clipboard
	            StringSelection filePath = new StringSelection(path);
	            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);
	            System.out.println("File path copied to clipboard: " + path);

	            // Simulate Ctrl+V to paste the file path in the file dialog
	            System.out.println("Pasting the file path using Robot (Ctrl+V)...");
	            robot.keyPress(KeyEvent.VK_CONTROL);
	            robot.keyPress(KeyEvent.VK_V);
	            robot.keyRelease(KeyEvent.VK_V);
	            robot.keyRelease(KeyEvent.VK_CONTROL);

	            // Simulate pressing Enter to confirm the file upload
	            System.out.println("Pressing 'Enter' to confirm file selection.");
	            robot.keyPress(KeyEvent.VK_ENTER);
	            robot.keyRelease(KeyEvent.VK_ENTER);

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
}
