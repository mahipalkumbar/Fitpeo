package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ImageGeneratedPage extends Basepage {

    private WebDriverWait wait;
    private Actions act;

    public ImageGeneratedPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        act = new Actions(driver);
    }

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
    	act.moveToElement(campaignname).perform();
    	System.out.println("cursor moved on campaign button");
   	 	WebElement downloadButton = wait.until(ExpectedConditions.elementToBeClickable(downloadbutton));
   	 	
   	 	downloadButton.click();
   	 System.out.println("download button clicked");
    }
    
 

    public boolean clickonsavebutton() {
        boolean display = false;
        try {
            savebut.click();
            WebElement popup = wait.until(ExpectedConditions.visibilityOf(imageSavedImageHasBeenSave));
            if (popup.isDisplayed()) {
                display = true;
                okforimagesaved.click();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Save button or confirmation dialog not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred while clicking on the save button: " + e.getMessage());
        }
        return display;
    }
    
    public boolean clickOnLikeButtonAfterImageGenerated() {
    	act.moveToElement(campaignname).perform();
    	 WebElement likeButton = wait.until(ExpectedConditions.elementToBeClickable(likebutton));
    	 likeButton.click();
    	 WebElement isLiked=wait.until(ExpectedConditions.visibilityOf(selectedlikebutton));
    	 boolean isLike = isLiked.getAttribute("class").contains("bg-nyx-sky");
    	 return isLike;
    	
    }
    public boolean clickOnDisLikeButtonAfterImageGenerated() {
    	act.moveToElement(campaignname).perform();
    	 WebElement dislikeButton = wait.until(ExpectedConditions.elementToBeClickable(dislikebutton));
    	 dislikeButton.click();
    	 WebElement isDisLiked=wait.until(ExpectedConditions.visibilityOf(selecteddislikebutton));
    	 boolean isdisLike = isDisLiked.getAttribute("class").contains("bg-nyx-sky");
    	 return isdisLike;
    	
    }

    public String clickonOpenwithBrandCanvas() {
        String actualTitle = "";
        try {
            act.moveToElement(campaignname).perform();
            Thread.sleep(30000);
            wait.until(ExpectedConditions.visibilityOf(openwithbrancanvas));
            WebElement dropdownButton= wait.until(ExpectedConditions.elementToBeClickable(openwithbrancanvas));
           // dropdownButton.click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownButton);
          /*  String xpathExpre = "//*[text()='" + region + "']";
			WebElement option = driver.findElement(By.xpath(xpathExpression));
			Actions act=new Actions(driver);
			act.moveToElement(option).click().perform();*/
            System.out.println("Clicked on 'Open with Brand Canvas' dropdown button.");
            WebElement dropdownOption = wait.until(ExpectedConditions.visibilityOf(openwithbrancanvasoption));
            dropdownOption.click();
            System.out.println("Selected 'Open with Option' from the dropdown.");
            
            // Handle the new tab
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            System.out.println("Switched to the new tab.");
            
            WebElement pageTitle = wait.until(ExpectedConditions.visibilityOf(brandcanvas));
            actualTitle = pageTitle.getText();
        } catch (NoSuchElementException e) {
            System.out.println("Element for 'Open with Brand Canvas' or its option not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred while clicking 'Open with Brand Canvas': " + e.getMessage());
        }
        return actualTitle;
    }

    public String[] clickonrevealpromptbutton() {
        String popupText = "";
        String textstring1 = "";

        System.out.println("Starting clickonrevealpromptbutton method.");

        try {
            // Move to the campaign name and wait for the reveal prompt button to be visible
            act.moveToElement(campaignname).perform();
            System.out.println("Moved to campaign name.");
            wait.until(ExpectedConditions.visibilityOf(revealprompt));
            System.out.println("Reveal prompt button is visible.");
            
            if (revealprompt.isDisplayed()) {
                // Click the reveal prompt button
                revealprompt.click();
                System.out.println("Reveal prompt button clicked.");
                
                // Wait for and get the popup text
                WebElement popupElement = wait.until(ExpectedConditions.visibilityOf(revealprompttext));
                popupText = popupElement.getText();
                System.out.println("Popup text retrieved: " + popupText);
                
                // Click the cancel button
               // wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='overlay-class']")));
              // WebElement revealButton = wait.until(ExpectedConditions.elementToBeClickable(revealpromptcancel));
               //((JavascriptExecutor) driver).executeScript("arguments[0].click();", revealButton);
               revealpromptcancel.click();

               // revealpromptcancel.click();
                System.out.println("Reveal prompt pop up Cancel button clicked.");
            } else {
                System.out.println("Reveal prompt button is not displayed.");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Reveal prompt button or its elements not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred while handling the reveal prompt button: " + e.getMessage());
        }

        try {
            // Wait for and get the text string
            WebElement popupElement1 = wait.until(ExpectedConditions.visibilityOf(textstring));
            textstring1 = popupElement1.getText();
            System.out.println("Text string retrieved: " + textstring1);
        } catch (NoSuchElementException e) {
            System.out.println("Text string element not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred while retrieving text string: " + e.getMessage());
        }
        
        return new String[] {popupText, textstring1};
    }


    public int[] clickonRegenerateButton() {
        int[] imageCount = new int[2];
        try {
            act.moveToElement(campaignname).perform();
            int beforeClickingOnButton = noofimage.size();
            WebElement regenerate= wait.until(ExpectedConditions.elementToBeClickable(button_regenerate));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", regenerate);
            //regenerate.click();
            WebDriverWait waitAfter = new WebDriverWait(driver, Duration.ofMinutes(4));
            waitAfter.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//div[@class='slick-list']//img"), beforeClickingOnButton));

            int afterClickingOnButton = noofimage.size();
            System.out.println("After regenerate no of images are= "+beforeClickingOnButton);
            System.out.println("After regenerate no of images are= "+afterClickingOnButton);
            imageCount[0] = beforeClickingOnButton;
            imageCount[1] = afterClickingOnButton;
        } catch (NoSuchElementException e) {
            System.out.println("Regenerate button or image elements not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred while regenerating images: " + e.getMessage());
        }
        return imageCount;
    }
}
