package tesrCases;
import java.awt.AWTException;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import GenerateImage.DataProviderClass;
import pageObjects.Addbrandinfo;
import pageObjects.CampaignName;
import pageObjects.Creativedesign;
import pageObjects.Homepage;
import pageObjects.ImageCraftAI;
import pageObjects.ImageGeneratedPage;
import pageObjects.MediaChannels;
import pageObjects.MenuPage;
import testBase.BaseClass;
public class TC_001_DataDrivenTesting_Text_to_ImageGeneration extends BaseClass {

	 private ImageGeneratedPage postimage;

	 @Test(dataProvider = "imageGenerationData", dataProviderClass = DataProviderClass.class, priority = 1)
	    public void ImageGanerationtext(String brandname, String category, String abouturbrand, String tgname,
	                                    String gender, String agegruop, String region, String brandlogo, String productname,
	                                    String productdescription, String productlogo, String campaignname, String objective,
	                                    String socialmedia, String size, String imagecontext, String focuselements,
	                                    String imagestyle, String imageprompt) throws IOException, InterruptedException, AWTException {
		 MenuPage menu = new MenuPage(driver);
		 
		 try {
		        boolean isCreditSufficient = menu.SlideBar();
		       
		        if (!isCreditSufficient) {
		        	logger.error("Insufficient credit balance. Please upgrade plan");
		            Assert.fail("Insufficient credit balance. Please upgrade plan");
		            return; // Exit the test if balance is insufficient
		        }
		    } catch (Exception e) {
		    	
		        logger.error("Error checking credit balance.", e);
		        Assert.fail("Error checking credit balance. " + e.getMessage());
		        return; // Exit the test if there is an error checking the balance
		    }
		 	menu.HomeButton();
		 	logger.info("****Starting TC_001_DataDrivenTesting_Text_to_ImageGeneration***************");
	        Homepage h = new Homepage(driver);
	        MediaChannels m = new MediaChannels(driver);
	        Addbrandinfo a = new Addbrandinfo(driver);
	        Creativedesign cr = new Creativedesign(driver);
	        CampaignName c = new CampaignName(driver);
	        ImageCraftAI im = new ImageCraftAI(driver);
	        try {
	            h.HomeimageCraftAI();
	            im.Tryitouttexttoimage();
	            a.AddNewbrandbutton();
	            a.sendaddbrand(brandname);
	            a.selectCategory(category);
	            a.aboutYourBrand(abouturbrand);
	            a.addTargetGroup();
	            a.TargetNameText(tgname);
	            a.Gender(gender);
	            a.age(agegruop);
	            a.selectRegiontg(region);
	            a.nexttg();
	            a.nextABIbutton();
	            a.addNewProductButton();
	            a.ProductNameText(productname);
	            a.productDescription(productdescription);
	            a.ProdctpageNextButton();
	            a.nextbuttoninEditBrandInfo();
	            a.brandnameselected(brandname);
	            a.clicknextbuttoninbrandselection();
	            c.Campaigname(campaignname);
	            c.Objective(objective);
	            c.Chooseproduct(productname);
	            c.choosetg(tgname);
	            c.nextc();
	            m.socialmedia(socialmedia);
	            m.size(size);
	            m.nexts();
	            cr.contextofimage(imagecontext);
	            cr.focusing(focuselements);
	            cr.imagestyle(imagestyle);
	            cr.submit();
	            cr.generate(imageprompt);
	            cr.generatef();
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	            try {
	                WebElement generatedImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='slick-list']//img")));
	                Assert.assertNotNull(generatedImage, "Image was not generated within 2 minutes.");
	                postimage = new ImageGeneratedPage(driver);
	                testRevealPromptButtonFunctionality();
	                verifyImageRegeneration();
	                LikeGeneratedImageFunctionalityTest();
	                DisLikeGeneratedImageFunctionalityTest();
	                saveGeneratedImageFunctionalityTest();
	                //menu.FullviewMenuButton();
		           // menu.HomeButton();
	            } catch (Exception e) {	  
	                String screenshotPath = captureScreen("ImageGenerationFailed_Iteration_" + brandname);
	                Assert.fail("Image was not generated within 2 minutes. Screenshot saved at: " + screenshotPath);
	            }
	            logger.info("******Finished TC_001_DataDrivenTesting_Text_to_ImageGeneration for brand: " + brandname);
	           
	        } catch (Exception e) {
	            logger.error("Error occurred during brand: " + brandname, e);
	            String screenshotPath = captureScreen("Error_Iteration_" + brandname);
	            logger.error("Error details saved at: " + screenshotPath);
	            Assert.fail("Test failed during brand: " + brandname + ". Error details saved at: " + screenshotPath);
	        }
	 }
	    public void testRevealPromptButtonFunctionality() throws IOException {
	        logger.info("**********Starting testRevealPromptButtonFunctionality Method**********");
	        String[] concatenatedValues = postimage.clickonrevealpromptbutton();
	        String popupText = concatenatedValues[0];
	        String textString1 = concatenatedValues[1];
	        
	        logger.info("Validating expected message..");
	        try {
	            Assert.assertEquals(popupText, textString1, "Strings are not equal!");
	        } catch (AssertionError e) {
	            logger.error("Test failed..");
	            String screenshotPath = captureScreen("ImagePromptTextANDrevealPromptTextAreNotMatching");
	            Assert.fail("ImagePromptText and revealPromptText are not matching. Screenshot saved at: " + screenshotPath);
	        }
	        logger.info("Ending testRevealPromptButtonFunctionality Method");
	    }
public void verifyImageRegeneration() throws IOException {
    logger.info("***************Starting verifyImageRegeneration Method***************");
    int[] res = postimage.clickonRegenerateButton();

    try {
        Assert.assertEquals(res[0]+1, res[1], "Image regeneration failed. The number of images did not increase by 1.");
        logger.info("Image regeneration successful. The number of images increased by 1.");
    } catch (AssertionError e) {
        try {
            logger.error("**********Test failed..**********");
            logger.debug("**********Debug logs..**********");
            String screenshotPath = captureScreen("ImageRegenerationFailed");
            logger.error("Screenshot captured: " + screenshotPath);
            Assert.fail("Image regeneration is not working. Screenshot saved at: " + screenshotPath);
        } catch (IOException ioException) {
            logger.error("Failed to capture screenshot: " + ioException.getMessage());
        }
    }
    logger.info("***************Ending verifyImageRegeneration Method***************");
}

public void likeButtonFunctionalityTest() {
	logger.info("***************Starting likeButtonFunctionalityTest Method***************");
	
}
public void saveGeneratedImageFunctionalityTest() throws IOException {
    logger.info("***************Starting saveGeneratedImageFunctionalityTest Method***************");
    boolean isPopupDisplayed = false;
    
    try {
        isPopupDisplayed = postimage.clickonsavebutton();
        if (isPopupDisplayed) {
            logger.info("Test Passed: Popup displayed successfully after clicking the Save button.");
        } else {
            throw new Exception("Popup not displayed after clicking the Save button.");
        }
    } catch (Exception e) {
        logger.error("**********Exception occurred in saveGeneratedImageFunctionalityTest**********");
        logger.error("Error Message: " + e.getMessage());
        
        // Capture screenshot and log path
        String screenshotPath = captureScreen("saveGeneratedImageFunctionalityTest");
        logger.error("Screenshot captured: " + screenshotPath);
    }
    
    // Ensure the test is marked as failed if necessary
    if (!isPopupDisplayed) {
        Assert.fail("Test Failed: Popup not displayed after clicking the Save button.");
    }
    
    logger.info("***************Ending saveGeneratedImageFunctionalityTest Method***************");
}

public void LikeGeneratedImageFunctionalityTest() throws IOException {
    logger.info("***************Starting LikeGeneratedImageFunctionalityTest Method***************");

    try {
        // Call the method to click the like button and check if it is selected
        boolean isLikeSelected = postimage.clickOnLikeButtonAfterImageGenerated();
        System.out.println(isLikeSelected);

        // Assert that the like button is selected
       
        logger.info("Test Passed: Like button selected successfully after clicking.");
        Assert.assertTrue(isLikeSelected, "Test Passed: Like button selected successfully after clicking.");
        
    } catch (Exception e) {
        logger.error("**********Exception occurred in LikeGeneratedImageFunctionalityTest**********");
        logger.error("Error Message: " + e.getMessage());

        // Capture screenshot and log path
        String screenshotPath = captureScreen("LikeGeneratedImageFunctionalityTest");
        logger.error("Screenshot captured: " + screenshotPath);
        Assert.fail("Test Failed: " + e.getMessage());
    }

    logger.info("***************Ending LikeGeneratedImageFunctionalityTest Method***************");
}
public void DisLikeGeneratedImageFunctionalityTest() throws IOException {
    logger.info("***************Starting DisLikeGeneratedImageFunctionalityTest Method***************");

    try {
        // Call the method to click the like button and check if it is selected
        boolean isDLikeSelected = postimage.clickOnDisLikeButtonAfterImageGenerated();

        // Assert that the like button is selected
       
        logger.info("Test Passed: DisLike button selected successfully after clicking.");
        Assert.assertTrue(isDLikeSelected, "Test Passed: DisLike button selected successfully after clicking.");
        
    } catch (Exception e) {
        logger.error("**********Exception occurred in DisLikeGeneratedImageFunctionalityTest**********");
        logger.error("Error Message: " + e.getMessage());

        // Capture screenshot and log path
        String screenshotPath = captureScreen("DisLikeGeneratedImageFunctionalityTest");
        logger.error("Screenshot captured: " + screenshotPath);
        Assert.fail("Test Failed: " + e.getMessage());
    }

    logger.info("***************Ending DisLikeGeneratedImageFunctionalityTest Method***************");
}

/* @Test(priority = 15, dependsOnMethods = {"ImageGanerationtext"})
public void validateImageDownload() throws InterruptedException {
    logger.info("***************Starting validateImageDownload Method***************");

    // Get the browser capabilities to ensure it's Chrome
    Capabilities cap1 = ((RemoteWebDriver) driver).getCapabilities();
    String browserName = cap1.getBrowserName();
    System.out.println(browserName);

    if (browserName.equalsIgnoreCase("chrome")) {
        // Use the already set download directory from BaseClass
        String downloadFilePath = System.getProperty("user.dir") + "/downloads";

        // Capture the list of files in the download directory before downloading
        File downloadDir = new File(downloadFilePath);
        File[] filesBeforeDownload = downloadDir.listFiles();

        System.out.println("Open the page where the image is generated");
        // Open the image generation page (replace with the actual URL for image generation)
       // driver.get(p.getProperty("image_generation_url"));

        System.out.println("Initialize the POM for image download");
        // Initialize the POM for image download (adjust as per your Page Object Model)
        postimage.ImageDownloadPage();

        // Trigger the image download (e.g., clicking the download button)
        // downloadPage.clickDownloadButton(); // Make sure you implement this part correctly

        // Wait for the download to complete (adjust the wait time if necessary)
        Thread.sleep(5000);

        // Capture the list of files in the download directory after downloading
        File[] filesAfterDownload = downloadDir.listFiles();

        // Find the newly downloaded file by comparing the file lists
        File downloadedFile = null;
        if (filesAfterDownload.length > filesBeforeDownload.length) {
            for (File file : filesAfterDownload) {
                boolean isNew = true;
                for (File existingFile : filesBeforeDownload) {
                    if (file.getName().equals(existingFile.getName())) {
                        isNew = false;
                        break;
                    }
                }
                if (isNew) {
                    downloadedFile = file;
                    break;
                }
            }
        }

        // Check if the downloaded file exists and has a valid size
        if (downloadedFile != null && downloadedFile.length() > 0) {
            logger.info("Image file downloaded successfully: " + downloadedFile.getName());
            System.out.println("File downloaded successfully: " + downloadedFile.getName());
            Assert.assertTrue(true);
        } else {
            System.out.println("File not found or incomplete in the download directory.");
            logger.error("Image file not found or incomplete in download directory.");
            Assert.fail();
        }
    } else {
        System.out.println("The browser is not Chrome. Skipping download validation.");
    }
}
*/

	 

}


