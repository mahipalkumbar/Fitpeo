package tesrCases;

import java.awt.AWTException;
import java.io.IOException;
import java.time.Duration;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import GenerateImage.DataProviderClass;
import pageObjects.Creativedesign;
import pageObjects.ImageCraftAI;
import pageObjects.MenuPage;
import pageObjects.VideoFGeneratedPage;
import pageObjects.VideoVistaAI;
import pageObjects.VideoVistaImageToImage;
import testBase.BaseClass;

public class TC_011_DataDrivenTesting_Image_to_VideoGeneration extends BaseClass {
	
	    
    @Test(dataProvider = "ImageToVideoGenerationData", dataProviderClass = DataProviderClass.class)
    public void ImageGenerationTestImageToVideo(String Uploadfileselection, String path, String Animationstyleselection, 
        String Amountofmotion, String Animationduration) throws IOException, InterruptedException, AWTException {
        
        logger.info("**** Starting TC_011_DataDrivenTesting_Image_to_VideoGeneration Image Generation Test with Image to Video ****");
        MenuPage menu = new MenuPage(driver);
        
        try {
            navigateThroughImageGenerationFlow(Uploadfileselection, path, Animationstyleselection, Amountofmotion, Animationduration);
            
            // Wait for the image generation and validate its visibility
            waitForImageGeneration();
            
            // Execute post-image generation tests
            runPostImageGenerationTests();
            
        } catch (Exception e) {
            // Handle image generation failure
            handleImageGenerationFailure(menu, e);
        }
        
        logger.info("**** Finished TC_011_DataDrivenTesting_Image_to_VideoGeneration Image Generation Test with Image to Video ****");
    }

    public void navigateThroughImageGenerationFlow(String Uploadfileselection, String path, String Animationstyleselection, 
        String Amountofmotion, String Animationduration) throws Exception {
        
        CommonImageToVideo citv = new CommonImageToVideo();
        citv.CommonImageToVideoMethod(Uploadfileselection, path, Animationstyleselection, Amountofmotion, Animationduration);
    }

    private void waitForImageGeneration() {
        WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(300)); // Use driver.get() to ensure thread-safety
        WebElement generatedImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='flex flex-col items-center justify-center']/following::video")));
        Assert.assertNotNull(generatedImage, "Image was not generated within 5 minutes.");
        System.out.println("Image generated successfully within the expected time.");
    }

    private void runPostImageGenerationTests() throws Exception {
        VideoFGeneratedPage test = new VideoFGeneratedPage(driver);
        test.clickOnDisLikeButtonAfterImageGenerated();
        test.clickOnLikeButtonAfterImageGenerated();
        //test.saveGeneratedImageFunctionalityTest();
        String projectDownloadDir = downloadDir.get();
       // test.DownloadCheckWithBrowserCondition(projectDownloadDir, "mp4", 30);
        //test.testRevealPromptButtonFunctionality();
        test.clickOnOpenWithBrandCanvas();
    }

    private void handleImageGenerationFailure(MenuPage menu, Exception e) throws IOException {
        logger.error("Image generation failed for Image to Video tool: " + e);
        
        // Capture a screenshot
        // String screenshotPath = captureScreen("ImageGenerationFailed_Iteration_" + brandname);
        //logger.info("Screenshot captured: " + screenshotPath);
        
        // Check if credits are available
        try {
            boolean isCreditSufficient = menu.SlideBar();
            if (!isCreditSufficient) {
                logger.error("Insufficient credit balance for ImageToVideo: ");
                String screenshotPath = captureScreen("ImageGenerationFailed_Iteration_");
                Assert.fail("Insufficient credit balance. Please upgrade the plan. Screenshot saved at: " + screenshotPath);
            } else {
                logger.info("Sufficient credits are available. Proceeding with the test.");
            }
        } catch (Exception creditException) {
            logger.error("Error checking credit balance for Product: " + "ImageToVideo", creditException);
            String creditErrorScreenshotPath = captureScreen("CreditCheckError_Iteration_" + "ImageToVideo");
            logger.error("Credit check error details saved at: " + creditErrorScreenshotPath);
            Assert.fail("Error during credit check. Screenshot saved at: " + creditErrorScreenshotPath);
        }
    }
    
   
    
    
}
