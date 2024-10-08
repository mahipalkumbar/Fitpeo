package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ImageGeneratedPage extends Basepage {
    public ImageGeneratedPage(WebDriver driver) {
        super(driver);
    }
     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
     Actions act=new Actions(driver);

    // POM Of RevealPromptButton
    @FindBy(xpath="//button[@class='nyx-button border border-amber-400 text-white hover:text-black hover:bg-amber-300 hover:shadow-glow text-base font-normal px-5 py-2 text-center rounded-full w-40'][normalize-space()='Cancel']")   
    private WebElement revealpromptcancel;

    @FindBy(xpath="//button[text()='Reveal Prompt']")   
    private WebElement revealprompt;    

    @FindBy(xpath="//div[text()='Reveal Prompt']/../..//div[@class='w-full text-[#FFFFFF] text-base font-normal my-12 text-left']") 
    private WebElement revealprompttext;

    @FindBy(xpath="//div[@class=' css-19bb58m']") 
    private WebElement openWithBrandCanvas;

    @FindBy(xpath="//span[text()='Campaign :']")   
    private WebElement campaignname;    

    @FindBy(xpath="//button[@class='text-[#FFFFFF] border rounded-full p-2 focus:outline-none hover:bg-nyx-sky hover:border-nyx-sky active:bg-nyx-sky active:border-nyx-sky']") 
    private WebElement download;

    @FindBy(xpath="//textarea[@class='w-full h-[100px] bg-transparent border placeholder:italic border-x-nyx-gray-1 rounded-lg p-5 text-white outline-none']") 
    private WebElement textstring;

    // POM of Open Brand with Canvas drop down
    @FindBy(xpath="//*[text()='Open with Brand Canvas']") 
    private WebElement openwithbrancanvas;

    @FindBy(xpath="//*[text()='Open with Brand Canvas']") 
    private WebElement openwithbrancanvasoption;

    @FindBy(xpath="//p[text()='Brand Canvas']") 
    private WebElement brandcanvas;

    // POM of Download button
    @FindBy(xpath="//button[@class='text-[#FFFFFF] border rounded-full p-2 focus:outline-none hover:bg-nyx-sky hover:border-nyx-sky active:bg-nyx-sky active:border-nyx-sky']") 
    private WebElement downloadbutton;

    @FindBy(xpath="//img[@alt='Images']") 
    private WebElement Assetimg;

    // POM of Regenerate Button
    @FindBy(xpath="//div[@class='slick-list']//img") 
    private List<WebElement> noofimage;
    
    @FindBy(xpath="//button[@class='text-white border border-white rounded-full p-2 disabled:opacity-50 hover:bg-nyx-sky hover:border-nyx-sky active:bg-nyx-sky active:border-nyx-sky']") 
    WebElement button_regenerate;

    // POM of Save Button
    @FindBy(xpath="//button[@class=' text-[#FFFFFF] border rounded-full p-2 focus:outline-none hover:bg-nyx-sky hover:border-nyx-sky active:bg-nyx-sky active:border-nyx-sky']") 
    private WebElement savebut;

    @FindBy(xpath="//div[@role='dialog']") 
    private WebElement imageSavedImageHasBeenSave;

    @FindBy(xpath="//button[normalize-space()='Ok']") 
    private WebElement okforimagesaved;
    
    
    //POM of Like Button
  
    //button[@data-testid='like']
    @FindBy(xpath="//button[@data-testid='like']//*[name()='svg']") 
    WebElement likebutton;
    
  //button[@data-testid='like']//*[name()='svg']/../..//button[contains(@class, 'bg-nyx-sky')]
    @FindBy(xpath="//button[@data-testid='like']//*[name()='svg']/../..//button[contains(@class, 'bg-nyx-sky')]") 
    WebElement selectedlikebutton;
    //POM of DisLlike Button
    
    //button[@data-testid='dislike']
    @FindBy(xpath="//button[@data-testid='dislike']//*[name()='svg']") 
    WebElement dislikebutton;
    
    @FindBy(xpath="//button[@data-testid='dislike']//*[name()='svg']/../..//button[contains(@class, 'bg-nyx-sky')]") 
    WebElement selecteddislikebutton;
    
    public void ImageDownloadPage() {
        try {
            // Move the cursor to the campaign button and wait for it to become clickable
            act.moveToElement(campaignname).perform();
            System.out.println("Cursor moved on campaign button");

            // Wait for the download button to be clickable and click it
            WebElement downloadButton = wait.until(ExpectedConditions.elementToBeClickable(downloadbutton));
            downloadButton.click();
            System.out.println("Download button clicked");

        } catch (NoSuchElementException e) {
            throw new RuntimeException("Campaign name or download button not found.", e);
        } catch (TimeoutException e) {
            throw new RuntimeException("Download button not clickable within the specified wait time.", e);
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while interacting with the download page.", e);
        }
    }

    
 

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


    
    public boolean clickOnLikeButtonAfterImageGenerated() {
        // Move the cursor to the campaign name
        act.moveToElement(campaignname).perform();
        
        try {
            // Click the like button directly
            likebutton.click();

            // Check the state of the like button immediately after the click
            // Assuming selectedlikebutton is always present after clicking the like button
            boolean isLike = selectedlikebutton.getAttribute("class").contains("bg-nyx-sky");
            return isLike;
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
        // Move the cursor to the campaign name
        act.moveToElement(campaignname).perform();
        
        try {
            // Click the dislike button directly
            dislikebutton.click();

            // Check the state of the dislike button immediately after the click
            boolean isDisLiked = selecteddislikebutton.getAttribute("class").contains("bg-nyx-sky");
            return isDisLiked;
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


    public String clickOnOpenWithBrandCanvas() {
        String actualTitle = "";

        // Move to the campaign name element
        act.moveToElement(campaignname).perform();

        // Wait for and click on the 'Open with Brand Canvas' dropdown button
        wait.until(ExpectedConditions.visibilityOf(openwithbrancanvas));
        WebElement dropdownButton = wait.until(ExpectedConditions.elementToBeClickable(openwithbrancanvas));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownButton);
        System.out.println("Clicked on 'Open with Brand Canvas' dropdown button.");

        // Wait for and select the option from the dropdown
        WebElement dropdownOption = wait.until(ExpectedConditions.visibilityOf(openwithbrancanvasoption));
        dropdownOption.click();
        System.out.println("Selected 'Open with Option' from the dropdown.");

        // Switch to the new tab
        String originalTab = driver.getWindowHandle();  // Store the original tab
        for (String tab : driver.getWindowHandles()) {
            if (!tab.equals(originalTab)) {
                driver.switchTo().window(tab);
                System.out.println("Switched to the new tab.");
                break; // Exit the loop once we switch to the new tab
            }
        }

        // Wait for the brand canvas element and retrieve the title
        WebElement pageTitle = wait.until(ExpectedConditions.visibilityOf(brandcanvas));
        actualTitle = pageTitle.getText();

        return actualTitle;
    }


    public String[] clickOnRevealPromptButton() {
        String popupText = "";
        String textString1 = "";

        System.out.println("Starting clickOnRevealPromptButton method.");

        try {
            // Move to the campaign name and ensure the reveal prompt button is displayed
            act.moveToElement(campaignname).perform();
            System.out.println("Moved to campaign name.");
            
            if (revealprompt.isDisplayed()) {
                // Click the reveal prompt button
                revealprompt.click();
                System.out.println("Reveal prompt button clicked.");
                
                // Retrieve popup text
                popupText = revealprompttext.getText();
                System.out.println("Popup text retrieved: " + popupText);
                
                // Click the cancel button
                revealpromptcancel.click();
                System.out.println("Reveal prompt pop up Cancel button clicked.");
            } else {
                System.out.println("Reveal prompt button is not displayed.");
            }

            // Retrieve additional text string
            textString1 = textstring.getText();
            System.out.println("Text string retrieved: " + textString1);
        } catch (NoSuchElementException e) {
            System.out.println("Reveal prompt button or its elements not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred while handling the reveal prompt button: " + e.getMessage());
        }

        return new String[] {popupText, textString1};
    }




    public boolean clickOnRegenerateButton() {
        boolean isRegenerationSuccessful = false; // Initialize success flag
        int[] imageCount = new int[2];
        
        try {
            // Move to the campaign name and record image count before regeneration
            act.moveToElement(campaignname).perform();
            int beforeClickingOnButton = noofimage.size();
            System.out.println("Before regenerate, number of images: " + beforeClickingOnButton);

            // Use JS Executor to click the regenerate button
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button_regenerate);

            // Explicitly wait for new images to load
            WebDriverWait waitAfter = new WebDriverWait(driver, Duration.ofMinutes(3));
            waitAfter.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.xpath("//div[@class='slick-list']//img"), beforeClickingOnButton)
            );

            // Get the updated image count after regeneration
            int afterClickingOnButton = noofimage.size();
            System.out.println("After regenerate, number of images: " + afterClickingOnButton);

            // Store counts in the array
            imageCount[0] = beforeClickingOnButton;
            imageCount[1] = afterClickingOnButton;

            // Check if the image count increased by 1
            isRegenerationSuccessful = (imageCount[1] == imageCount[0] + 1);

        } catch (NoSuchElementException e) {
            System.out.println("Regenerate button or image elements not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred while regenerating images: " + e.getMessage());
        }
        
        return isRegenerationSuccessful;
    }


}
