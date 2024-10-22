package pageObjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ImageCraftImageToImage extends Basepage{
	public ImageCraftImageToImage(WebDriver driver) {
		super(driver);
	}
	JavascriptExecutor js = (JavascriptExecutor) driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	@FindBy(xpath="//input[@placeholder='Light Basketball Shoes']")
	WebElement productnametextfield;
	

	@FindBy(xpath="//button[@class='nyx-button border border-amber-400 hover:text-black hover:bg-amber-300 hover:shadow-glow text-base px-5 py-2 text-center rounded-full font-semibold w-32 mb-4 mt-4 text-nyx-yellow hover:shadow-none focus:shadow-glow']")
	WebElement productnamenextbutton;
	

	@FindBy(xpath="//button[normalize-space()='Upload']")
	WebElement productimageuploadbutton;
	
	
	@FindBy(xpath="//div[@class=' flex flex-wrap gap-3 my-3 overflow-y-auto overflow-x-hidden h-[280px]']//div//img")
	List<WebElement> selectimgfromasset;
	
	//button[text()='Use an Asset']
	
	@FindBy(xpath="//button[text()='Use an Asset']")
	WebElement uploadimgUseAnAssetButton;
	
	@FindBy(xpath="//h2[contains(@class,'mb-4  text-white ')]")
	List<WebElement> selectimgbackdropsoptions;
	

	@FindBy(xpath="//div[@class='w-full flex justify-center items-center gap-3 mb-5']//button[text()='Next']")
	WebElement uploadimgUseAnAssetNextButton;
	
	//h2[text()='Auto Generate']
	
	@FindBy(xpath="//h2[text()='Prompt']")
	WebElement PromptButton;
	
	
	
	@FindBy(xpath="//h2[text()='Reference Image']")
	WebElement ReferenceimginbackdropsButton;
	
	@FindBy(xpath="//h2[text()='Auto Generate']")
	WebElement AutoGenerateButton;
	
	@FindBy(xpath="//button[text()='Generate']")
	WebElement ImageToImageGenereteButton;
	
	

	@FindBy(xpath="//textarea[@placeholder='Enter prompt here (maximum 100 characters)']") 
	WebElement enterPromptHere;
	
	

	@FindBy(xpath="//div[text()='Browse from System']")  
	WebElement browseFromSystem;
	
	
	
	
	public void SendProductNameTextfieldData(String pname) {
	    try {
	        // Directly send keys to the product name text field
	        productnametextfield.sendKeys(pname);
	        System.out.println("Product name '" + pname + "' entered successfully.");
	    } catch (NoSuchElementException e) {
	        System.out.println("Product name text field not found.");
	        throw new RuntimeException("Product name text field not displayed.", e);
	    } catch (ElementNotInteractableException e) {
	        System.out.println("Product name text field is not interactable.");
	        throw new RuntimeException("Product name text field is not interactable.", e);
	    } catch (Exception e) {
	        System.out.println("An unexpected error occurred while entering product name.");
	        throw new RuntimeException("An unexpected error occurred while entering product name.", e);
	    }
	}

	
	
	public boolean ClickOnProductNameNextButton() {
	    try {
	    	 js.executeScript("arguments[0].click();", productnamenextbutton);
	        System.out.println("Clicked on Product Name Next Button.");

	        // Check if the product image upload button is displayed
	        wait.until(ExpectedConditions.visibilityOf(productimageuploadbutton));
	        return productimageuploadbutton.isDisplayed();
	    } catch (NoSuchElementException e) {
	        System.out.println("Product Name Next Button or Product Image Upload Button not found.");
	        throw new RuntimeException("Product Name Next Button or Product Image Upload Button not found.", e);
	    } catch (ElementNotInteractableException e) {
	        System.out.println("Product Name Next Button is not interactable.");
	        throw new RuntimeException("Product Name Next Button is not interactable.", e);
	    } catch (Exception e) {
	        System.out.println("An unexpected error occurred while clicking the Product Name Next Button.");
	        throw new RuntimeException("An unexpected error occurred.", e);
	    }
	}

	String SelectedIMGSRC;
	private int imageIndex = 1; // Moved imageIndex to class level to maintain state across calls

	public void ClickOnProductImageUploadButton(String selectinguploadoption, String path) {
	    try {
	        // Clicking the product image upload button
	        System.out.println("Clicking on the product image upload button...");
	        productimageuploadbutton.click();

	        // Handling the "Use an Asset" option
	        if (selectinguploadoption.equalsIgnoreCase("Use an Asset")) {
	            System.out.println("Option selected: 'Use an Asset'.");

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


	
	public void ImgUploadUseAnAssetButton() {
	    try {
	        // Click on the 'Use an Asset' upload button
	        uploadimgUseAnAssetButton.click();
	    } catch (NoSuchElementException e) {
	        throw new RuntimeException("The 'Use an Asset' button is not found in the Image upload section.", e);
	    } catch (StaleElementReferenceException e) {
	        throw new RuntimeException("The 'Use an Asset' button reference became stale during the click operation.", e);
	    } catch (Exception e) {
	        throw new RuntimeException("An unexpected error occurred while clicking on the 'Use an Asset' button.", e);
	    }
	}

	
	
	public boolean ClickOnuploadimgUseAnAssetNextButton() {
	    System.out.println("ClickOnuploadimgUseAnAssetNextButton");

	    try {
	        // Wait until the 'Next' button is clickable
	        wait.until(ExpectedConditions.elementToBeClickable(uploadimgUseAnAssetNextButton));

	        // Additional check to ensure the button is visible and enabled
	        if (uploadimgUseAnAssetNextButton.isDisplayed() && uploadimgUseAnAssetNextButton.isEnabled()) {
	            System.out.println("'Use an Asset' Next button is displayed and enabled.");

	            try {
	            	// Wait for any loader or overlay to disappear
	            	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading-overlay")));
	                // Scroll into view
	                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", uploadimgUseAnAssetNextButton);

	                // Attempt to click the button
	                uploadimgUseAnAssetNextButton.click();
	                System.out.println("'Use an Asset' Next button clicked.");

	            } catch (ElementClickInterceptedException e) {
	                System.out.println("'Use an Asset' Next button was obstructed. Attempting to click via JavaScript.");
	                // If the button is obstructed, click via JavaScript
	                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", uploadimgUseAnAssetNextButton);
	                System.out.println("'Use an Asset' Next button clicked via JavaScript.");
	            }

	            // Wait for the PromptButton to appear
	            wait.until(ExpectedConditions.visibilityOf(PromptButton));

	            boolean isDisplayed = PromptButton.isDisplayed();
	            System.out.println("Prompt button displayed: " + isDisplayed);

	            System.out.println("Ending");
	            return isDisplayed;

	        } else {
	            throw new RuntimeException("The 'Use an Asset' Next button is either not displayed or not enabled.");
	        }

	    } catch (NoSuchElementException e) {
	        throw new RuntimeException("The 'Use an Asset' Next button is not found.", e);
	    } catch (StaleElementReferenceException e) {
	        throw new RuntimeException("The 'Use an Asset' Next button reference became stale.", e);
	    } catch (ElementClickInterceptedException e) {
	        throw new RuntimeException("The 'Use an Asset' Next button was obstructed and could not be clicked.", e);
	    } catch (Exception e) {
	        throw new RuntimeException("An unexpected error occurred while clicking the 'Use an Asset' Next button.", e);
	    }
	}

	


	String SelectedIMGSRC1;

	public void ClickOnAutoGenerateButton(String selectingimgbackdrops, String Prompt) {
	    try {
	        // Print the total number of backdrop options
	        System.out.println("Total backdrop options: " + selectimgbackdropsoptions.size());

	        // Loop through the backdrop options
	        for (WebElement opt : selectimgbackdropsoptions) {
	            String optionText = opt.getText().trim();  // Trim the text to remove leading/trailing spaces

	            // Log both the passed parameter and the optionText for debugging
	            System.out.println("Passed Parameter: '" + selectingimgbackdrops + "', Option Text: '" + optionText + "'");

	            // Handle "Auto Generate" option
	            if ("Auto Generate".equalsIgnoreCase(selectingimgbackdrops) && optionText.equalsIgnoreCase("Auto Generate")) {
	                js.executeScript("arguments[0].click();", opt);  // Click the option
	                System.out.println("Selected Auto Generate Button");
	                break;  // Exit loop after selection
	            }

	            // Handle "Prompt" option
	            else if ("Prompt".equalsIgnoreCase(selectingimgbackdrops) && optionText.equalsIgnoreCase("Prompt")) {
	                js.executeScript("arguments[0].click();", opt);  // Click the option
	                System.out.println("Entering prompt into prompt text");
	                enterPromptHere.sendKeys(Prompt);  // Enter the prompt text
	                break;  // Exit loop after entering prompt
	            }

	            // Handle image selection for "Reference Image" option
	            else if ("Reference Image".equalsIgnoreCase(selectingimgbackdrops) && optionText.equalsIgnoreCase("Reference Image")) {
	                System.out.println("Selecting image-related option: '" + optionText + "'");
	                js.executeScript("arguments[0].click();", opt);  // Click the option for image selection

	                // Upload the image
	                WebElement uploadButton = productimageuploadbutton;  // Implicit wait should suffice here
	                uploadButton.click();

	                // Select the image from the asset
	                int imageIndex = 0;  // Initialize imageIndex to 0 for 0-based indexing
	                if (selectimgfromasset.size() > 0) {
	                    WebElement currentImage = selectimgfromasset.get(imageIndex);  // Get the first image
	                    SelectedIMGSRC1 = currentImage.getAttribute("src");  // Store the image's src
	                    js.executeScript("arguments[0].scrollIntoView(true);", currentImage);  // Scroll image into view
	                    currentImage.click();  // Click the image
	                    System.out.println("Selected Image: " + (imageIndex + 1));  // Log selected image
	                } else {
	                    System.out.println("No images available to select.");
	                }
	                break;  // Exit loop after processing
	            }
	        }
	    } catch (NoSuchElementException e) {
	        throw new RuntimeException("The backdrop option or associated elements could not be found.", e);
	    } catch (TimeoutException e) {
	        throw new RuntimeException("The operation timed out while waiting for the backdrop options.", e);
	    } catch (Exception e) {
	        throw new RuntimeException("An unexpected error occurred while selecting the backdrop option.", e);
	    }
	}



	// Method to handle file uploads
	/*public void uploadFile(String filePath) {
	    // Find the input element for file uploads
	    WebElement uploadElement = driver.findElement(By.id("uploadInputElementId"));  // Update with actual ID or locator for the upload input element

	    // Use sendKeys to upload the file
	    uploadElement.sendKeys(filePath);
	    
	    // Optionally, wait for the file to upload (based on your UI's response)
	    WebElement uploadSuccess = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uploadSuccessElementId"))); // Update with the actual locator
	    System.out.println("File uploaded successfully.");
	}*/

		
		//js.executeScript("arguments[0].click();", AutoGenerateButton);
		//WebElement ele=wait.until(ExpectedConditions.elementToBeClickable(AutoGenerateButton));
		//ele.click();
	
	
	public void ClickOnImageToImageGenerateButton() {
	    try {
	        // Scroll to the button and click it
	        js.executeScript("arguments[0].scrollIntoView(true);", ImageToImageGenereteButton);
	        js.executeScript("arguments[0].click();", ImageToImageGenereteButton);
	    } catch (NoSuchElementException e) {
	        throw new RuntimeException("ImageToImageGenerateButton not found on the page.", e);
	    } catch (Exception e) {
	        throw new RuntimeException("An unexpected error occurred while clicking the ImageToImageGenerateButton.", e);
	    }
	}

	
    
	
	
	
	
	
}
