package pageObjects;

import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TextToVideoPostVideoGeneration extends Basepage {
    public TextToVideoPostVideoGeneration(ThreadLocal<WebDriver> driver) {
        super(driver);
    }
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    Actions act=new Actions(driver);
    // POM of Save Button
    @FindBy(xpath="//button[@class=' text-[#FFFFFF] border rounded-full p-2 focus:outline-none hover:bg-nyx-sky hover:border-nyx-sky active:bg-nyx-sky active:border-nyx-sky']") 
    private WebElement savebut;
    
    @FindBy(xpath="//button[normalize-space()='Ok']") 
    private WebElement okforimagesaved;

    @FindBy(xpath="//div[@role='dialog']") 
    private WebElement imageSavedImageHasBeenSave;

    // POM of Download button
    @FindBy(xpath="//button[@class='text-[#FFFFFF] border rounded-full p-2 focus:outline-none hover:bg-nyx-sky hover:border-nyx-sky active:bg-nyx-sky active:border-nyx-sky']") 
    private WebElement downloadbutton;
    
    //button[@data-testid='like']
    @FindBy(xpath="//div[text()='Like']/..") 
    WebElement likebutton;
    
    //button[@data-testid='like']//*[name()='svg']/../..//button[contains(@class, 'bg-nyx-sky')]
    // @FindBy(xpath="//div[@class='flex gap-3']//div[text()='Like']/..//button[contains(@class, 'bg-nyx-sky')]") 
    //WebElement selectedlikebutton;
    
    @FindBy(xpath="//div[@class='Toastify']//div[text()='You Liked the Video']") 
    WebElement selectedlikebutton;

    //POM of DisLlike Button
    
    //button[@data-testid='dislike']
    @FindBy(xpath="//div[text()='Dislike']/..") 
    WebElement dislikebutton;
    
    //@FindBy(xpath="//div[text()='Dislike']/..//button[contains(@class, 'bg-nyx-sky')]") 
    //WebElement selecteddislikebutton;
    
  
    @FindBy(xpath="//div[@class='Toastify']//div[text()='You Disliked the Video']") 
    WebElement selecteddislikebutton;

    public boolean clickOnSaveButton() throws Exception {
        try {
            // Ensure the save button is clickable and then click it
        	
        	
            wait.until(ExpectedConditions.elementToBeClickable(savebut)).click();
            
            // Wait for the confirmation dialog to be visible
            WebElement popup = wait.until(ExpectedConditions.visibilityOf(imageSavedImageHasBeenSave));

            // If the dialog is displayed, click OK and return true
            if (popup.isDisplayed()) {
                okforimagesaved.click(); // Click OK on the dialog
                return true; // Confirmation is successful
            } else {
                throw new Exception("Confirmation dialog not displayed after clicking the Save button.");
            }
        } catch (NoSuchElementException e) {
            throw new Exception("Save button or confirmation dialog not found: " + e.getMessage(), e);
        } catch (TimeoutException e) {
            throw new Exception("Timeout waiting for the confirmation dialog: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new Exception("An unexpected error occurred while clicking on the save button: " + e.getMessage(), e);
        }
    }
    public boolean DownloadCheckWithBrowserCondition(String projectDownloadDir,String fileExtension, int timeoutSeconds) {
        // Use the unique download directory from the BaseClass
    	//String downloadDirectory = getDownloadDir();
    	// String projectDownloadDir = downloadDir.get();
        System.out.println("Download directory set to: " + projectDownloadDir);

        // Ensure the download directory exists
        File downloadDirFile = new File(projectDownloadDir);
        if (!downloadDirFile.exists()) {
            downloadDirFile.mkdirs(); // Create the directory if it doesn't exist
            System.out.println("Download directory created.");
        } else {
            System.out.println("Download directory already exists.");
        }
        deleteExistingFilesWithExtension(projectDownloadDir, fileExtension);


        // Check the browser type using the WebDriver instance
        String browserType = ((RemoteWebDriver) driver).getCapabilities().getBrowserName().toLowerCase();
        System.out.println("Browser detected: " + browserType);

        // Check if the browser type is Chrome
        if ("chrome".equals(browserType)) {
            try {
                System.out.println("Browser is Chrome. Proceeding with download.");
                // Move to the video element and perform the download
                //act.moveToElement(video).perform();
                System.out.println("Moved to campaign name element.");

                // Click the download button
                downloadbutton.click();
                System.out.println("Download button clicked.");

                long startTime = System.currentTimeMillis();
                System.out.println("Monitoring download directory for file with extension: ." + fileExtension);

                // Monitor the Downloads directory for the new file
                while (System.currentTimeMillis() - startTime < timeoutSeconds * 1000) {
                    File latestFile = getLatestFileFromDir(projectDownloadDir, fileExtension);
                    if (latestFile != null) {
                        System.out.println("File downloaded successfully: " + latestFile.getName());
                        return true; // File found, return true
                    }

                    // Sleep for a short duration before checking again
                    System.out.println("Waiting for file download...");
                    Thread.sleep(1000); // Increased sleep time to reduce aggressive checking
                }

                System.out.println("File download timed out after " + timeoutSeconds + " seconds.");
                return false; // No file found after the timeout period
            } catch (Exception e) {
                System.out.println("An error occurred during the download check: " + e.getMessage());
                e.printStackTrace(); // Print stack trace for debugging
                return false; // Return false in case of any exceptions
            }
        } else {
            // Placeholder for other browsers
            System.out.println("This method currently supports only Chrome browser. Browser detected: " + browserType);
            return false; // Return false if browser is not Chrome
        }
    }
    private void deleteExistingFilesWithExtension(String dirPath, String fileExtension) {
        System.out.println("Checking and deleting existing files with extension: ." + fileExtension + " in directory: " + dirPath);
        File dir = new File(dirPath);
        File[] files = dir.listFiles((d, name) -> name.endsWith("." + fileExtension));

        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.delete()) {
                    System.out.println("Deleted file: " + file.getName());
                } else {
                    System.out.println("Failed to delete file: " + file.getName());
                }
            }
        } else {
            System.out.println("No existing files found with the specified extension.");
        }
    }


    // Helper method to get the latest file with the specified extension
    private File getLatestFileFromDir(String dirPath, String fileExtension) {
        System.out.println("Checking directory: " + dirPath + " for files with extension: ." + fileExtension);
        File dir = new File(dirPath);
        File[] files = dir.listFiles((d, name) -> name.endsWith("." + fileExtension));

        if (files == null || files.length == 0) {
            System.out.println("No files found in directory with the specified extension.");
            return null; // No files found
        }

        File latestFile = Arrays.stream(files)
                .max(Comparator.comparingLong(File::lastModified))
                .orElse(null);

        if (latestFile != null) {
            System.out.println("Most recently modified file: " + latestFile.getName());
        } else {
            System.out.println("No files found.");
        }

        return latestFile; // Return the most recently modified file
    }
    
    public boolean clickOnOpenWithBrandCanvas() {
        String originalTab = driver.getWindowHandle();
        boolean isBrandCanvasDisplayed = false;

        try {
            // Move to the campaign name element
            //act.moveToElement(video).perform();
            System.out.println("Moved to the campaign name.");

            // Wait for and select the dropdown option
            WebElement option = driver.findElement(By.xpath("//*[text()='Open with Brand Canvas']"));

            // Use Actions to move to the element and click
            new Actions(driver)
                .moveToElement(option)
                .click()
                .sendKeys(Keys.ENTER)   // Simulate pressing the Enter key
                .perform();             // Execute the action
            System.out.println("Selected 'Open with Brand Canvas' from the dropdown.");

            // Switch to the new tab
            driver.getWindowHandles()
                  .stream()
                  .filter(tab -> !tab.equals(originalTab))
                  .findFirst()
                  .ifPresent(tab -> {
                      driver.switchTo().window(tab);
                      System.out.println("Switched to the new tab.");
                  });

            // Wait for the Brand Canvas image to be visible and check if it's displayed
          
            //WebElement pageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//video[@class='w-[125px] rounded-lg h-[80px]']")));
            WebElement pageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Brand Canvas']")));
            isBrandCanvasDisplayed = pageTitle.isDisplayed();

            // Print confirmation message if the Brand Canvas image is displayed
            if (isBrandCanvasDisplayed) {
                System.out.println("Brand Canvas page successfully loaded, and the Video is displayed.");
                driver.close();
            } else {
                System.out.println("Brand Canvas image is not displayed.");
            }

            // Switch back to the original tab
            driver.switchTo().window(originalTab);
            System.out.println("Switched back to the original tab.");
           // wait.until(ExpectedConditions.visibilityOf(campaignname));

        } catch (NoSuchElementException e) {
            System.err.println("Element not found: " + e.getMessage());
            throw new RuntimeException("Required element not found during 'Open with Brand Canvas' operation.");
        } catch (TimeoutException e) {
            System.err.println("Timeout waiting for element: " + e.getMessage());
            throw new RuntimeException("Timeout occurred while waiting for elements during 'Open with Brand Canvas' operation.");
        } catch (Exception e) {
            System.err.println("Unexpected error during 'Open with Brand Canvas' operation: " + e.getMessage());
            e.printStackTrace(); // Log the full stack trace to help with debugging
            throw new RuntimeException("Unexpected error during 'Open with Brand Canvas' operation.");
        }

        return isBrandCanvasDisplayed;
    }
    
    
    public boolean clickOnLikeButtonAfterImageGenerated() {
        // Move the cursor to the campaign name
    	//wait.until(ExpectedConditions.visibilityOf(video));
       // act.moveToElement(campaignname).perform();
    	wait.until(ExpectedConditions.elementToBeClickable(likebutton));
        
        try {
            // Click the like button directly
            likebutton.click();
           
            if (selectedlikebutton.isDisplayed()) {
                return true; // Return true if the selected dislike button is displayed
            } else {
                return false; // Return false if the selected dislike button is not displayed
            }
        } catch (NoSuchElementException e) {
            System.out.println("Like button or selected like button not found: " + e.getMessage());
            return false; // Return false if the like button could not be clicked or found
        } catch (StaleElementReferenceException e) {
            System.out.println("Element reference became stale, please check the elements: " + e.getMessage());
            return false; // Return false if the element reference is stale
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while clicking on the like button: " + e.getMessage());
            return false; // Return false for any other exceptions
        }
    }

    public boolean clickOnDisLikeButtonAfterImageGenerated() {
        try {
        	
        	wait.until(ExpectedConditions.elementToBeClickable(dislikebutton));
            // Click the dislike button directly
            dislikebutton.click();

            // Check if the selected dislike button is displayed
            if (selecteddislikebutton.isDisplayed()) {
                return true; // Return true if the selected dislike button is displayed
            } else {
                return false; // Return false if the selected dislike button is not displayed
            }
        } catch (NoSuchElementException e) {
            System.out.println("Dislike button or selected dislike button not found: " + e.getMessage());
            return false; // Return false if the dislike button could not be clicked or found
        } catch (StaleElementReferenceException e) {
            System.out.println("Element reference became stale, please check the elements: " + e.getMessage());
            return false; // Return false if the element reference is stale
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while clicking on the dislike button: " + e.getMessage());
            return false; // Return false for any other exceptions
        }
    }



}
