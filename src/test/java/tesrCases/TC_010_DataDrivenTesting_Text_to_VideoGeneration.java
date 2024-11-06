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
import pageObjects.ImageCraftAI;
import pageObjects.MenuPage;
import pageObjects.ScriptPageInTextToVideo;
import pageObjects.StartSlateEndSlate;
import pageObjects.VideoContentInTextToVideo;
import pageObjects.VideoSettingsInTextToVideo;
import pageObjects.VideoVistaAI;
import testBase.BaseClass;

public class TC_010_DataDrivenTesting_Text_to_VideoGeneration extends BaseClass {
	
	 @Test(dataProvider = "TextToVideoGenerationData", dataProviderClass = DataProviderClass.class)
	 public void TextToVideoGeneration(String brandname, String category, String abouturbrand, String tgname,
             String gender, String agegruop, String region, String brandlogo, String productname,
             String productdescription, String productlogo, String campaignname,String objective,
             String ScriptSelection, String AIPrompt, String YourScript,String writingstyle, String duration, String VideoStactureSelection,
             String VideoSizeSelection, String PaceSelection,String CaptionSelection,String slateselection,String StartSlateUpload,String Endslateupload) throws IOException, InterruptedException, AWTException {

	        logger.info("**** Starting TC_010_DataDrivenTesting_Text_to_VideoGeneration Video Generation Test with Text to Video  for Brand: " + brandname + " ****");
	        //MenuPage menu = new MenuPage(driver);
	        
	        MenuPage menu = new MenuPage(driver);
	        
	        try {
	            navigateThroughImageGenerationFlow(brandname, category, abouturbrand, tgname, gender, agegruop, region, brandlogo, productname, productdescription, productlogo, campaignname, objective, ScriptSelection, AIPrompt, YourScript,writingstyle, duration, VideoStactureSelection, VideoSizeSelection, PaceSelection, CaptionSelection, slateselection, StartSlateUpload, Endslateupload);
	            
	            // Wait for the image generation and validate its visibility
	            waitForImageGeneration();
	            
	           
	            
	            // Execute post-image generation tests
	            runPostImageGenerationTests();
	            
	            //menu.clickHomeButton();
	           	            
	        } catch (Exception e) {
	            // Handle image generation failure
	            handleImageGenerationFailure(menu, brandname, e);
	        }	   	   	 
	           
	        logger.info("**** Finished TC_010_DataDrivenTesting_Text_to_VideoGeneration Video Generation Test with Text to Video  for Brand: " + brandname + " ****");
	            //menu.clickHomeButton();
	            
	          
	            
	       
	    }
		
	 public void navigateThroughImageGenerationFlow(String brandname, String category, String abouturbrand, String tgname,
             String gender, String agegruop, String region, String brandlogo, String productname,
             String productdescription, String productlogo, String campaignname,String objective,
             String ScriptSelection, String AIPrompt, String YourScript,String writingstyle, String duration, String VideoStactureSelection,
             String VideoSizeSelection, String PaceSelection,String CaptionSelection,String slateselection,String StartSlateUpload,String Endslateupload) throws Exception {
		 CommonTextToVideo cttv=new CommonTextToVideo();
		 cttv.CommonTextToVideoMethod(brandname, category, abouturbrand, tgname, gender, agegruop, region, brandlogo, productname, productdescription, productlogo, campaignname, objective, ScriptSelection, AIPrompt, YourScript,writingstyle, duration, VideoStactureSelection, VideoSizeSelection, PaceSelection, CaptionSelection, slateselection, StartSlateUpload, Endslateupload);
	    }
	 private void waitForImageGeneration() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	        WebElement generatedImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='slick-list']//img")));
	        Assert.assertNotNull(generatedImage, "Video was not generated within 3 minutes.");
	        System.out.println("Video generated successfully within the expected time with Text to Video");
	    }
	 
	 private void runPostImageGenerationTests() throws Exception {
	        TC_008_PostImageGenerationTest test = new TC_008_PostImageGenerationTest();
	        test.dislikeGeneratedImageFunctionalityTest();
	        test.likeGeneratedImageFunctionalityTest();
	        //test.saveGeneratedImageFunctionalityTest();
	        test.verifyImageRegeneration();
	        test.ImageDownloading();
	        //test.testRevealPromptButtonFunctionality();
	        test.testOpenWithBrandCanvasFunctionality();
	    }
	 private void handleImageGenerationFailure(MenuPage menu, String brandname, Exception e) throws IOException {
		 logger.error("Image generation failed for Product: " + brandname, e);
	        
	        // Capture a screenshot
	       // String screenshotPath = captureScreen("ImageGenerationFailed_Iteration_" + brandname);
	        //logger.info("Screenshot captured: " + screenshotPath);
	        
	        // Check if credits are available
	        try {
	            boolean isCreditSufficient = menu.SlideBar();
	            if (!isCreditSufficient) {
	                logger.error("Insufficient credit balance for brand: " + brandname);
	                String screenshotPath = captureScreen("ImageGenerationFailed_Iteration_" + brandname);
	                Assert.fail("Insufficient credit balance. Please upgrade the plan. Screenshot saved at: " + screenshotPath);
	            } else {
	                logger.info("Sufficient credits are available. Proceeding with the test.");
	            }
	        } catch (Exception creditException) {
	            logger.error("Error checking credit balance for Product: " + brandname, creditException);
	            String creditErrorScreenshotPath = captureScreen("CreditCheckError_Iteration_" + brandname);
	            logger.error("Credit check error details saved at: " + creditErrorScreenshotPath);
	            Assert.fail("Error during credit check. Screenshot saved at: " + creditErrorScreenshotPath);
	        }
	    }
	            
		 
	            
	            
}
