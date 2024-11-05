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
import pageObjects.ImageCraftAI;
import pageObjects.ImageCraftImageToImage;
import pageObjects.ImageGeneratedPage;
import pageObjects.MenuPage;
import testBase.BaseClass;

public class TC_002_DataDrivenTesting_Image_to_ImageGeneration extends BaseClass{
	private ImageGeneratedPage postimage;
	
	 @Test(dataProvider = "ImageToImageGenerationData", dataProviderClass = DataProviderClass.class)
	    public void ImageGenerationTestImageToImage(String ProductName, String selectionofproductimgoption, String UlpoadImageFromSys, String SelectingImageBackdrops,String Prompt, String ReferenceImageSelection,String ReferenceImageFromSys) throws IOException, InterruptedException, AWTException {
		 

	        logger.info("**** Starting TC_002_DataDrivenTesting_Image_to_ImageGeneration Image Generation Test with Image to Image  for Product: " + ProductName + " ****");
	        //MenuPage menu = new MenuPage(driver);
	        
	        MenuPage menu = new MenuPage(driver);
	        
	        try {
	            navigateThroughImageGenerationFlow(ProductName, selectionofproductimgoption, UlpoadImageFromSys, SelectingImageBackdrops, Prompt, ReferenceImageSelection, ReferenceImageFromSys);
	            
	            // Wait for the image generation and validate its visibility
	            waitForImageGeneration();
	            
	           
	            
	            // Execute post-image generation tests
	            runPostImageGenerationTests();
	            
	            //menu.clickHomeButton();
	           	            
	        } catch (Exception e) {
	            // Handle image generation failure
	            handleImageGenerationFailure(menu, ProductName, e);
	        }	   	   	 
	           
	        logger.info("**** Finished TC_002_DataDrivenTesting_Image_to_ImageGeneration Image Generation Test with Image to Image for Product: " + ProductName + " ****");
	            //menu.clickHomeButton();
	            
	          
	            
	       
	    }
	
	 //Test(dataProvider = "ImageToImageGenerationData", dataProviderClass = DataProviderClass.class, priority = 1)
	    public void navigateThroughImageGenerationFlow(String ProductName, String selectionofproductimgoption, String UlpoadImageFromSys, String SelectingImageBackdrops,String Prompt, String ReferenceImageSelection,String ReferenceImageFromSys) throws Exception {
	    	CommonImageToImage citi=new CommonImageToImage();
	    			citi.CommonImageToImageMethod(ProductName, selectionofproductimgoption, UlpoadImageFromSys, SelectingImageBackdrops, Prompt, ReferenceImageSelection, ReferenceImageFromSys);
	    }
	    	
	// }
	 
	 private void waitForImageGeneration() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	        WebElement generatedImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='slick-list']//img")));
	        Assert.assertNotNull(generatedImage, "Image was not generated within 3 minutes.");
	        System.out.println("Image generated successfully within the expected time.");
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

