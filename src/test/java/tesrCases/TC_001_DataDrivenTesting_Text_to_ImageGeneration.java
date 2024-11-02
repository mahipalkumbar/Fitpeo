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
import pageObjects.MenuPage;
import testBase.BaseClass;

public class TC_001_DataDrivenTesting_Text_to_ImageGeneration extends BaseClass {

    @Test(dataProvider = "imageGenerationData", dataProviderClass = DataProviderClass.class)
    public void ImageGenerationTest(String brandname, String category, String abouturbrand, String tgname,
                                    String gender, String agegroup, String region, String brandlogo, String productname,
                                    String productdescription, String productlogo, String campaignname, String objective,
                                    String socialmedia, String size, String imagecontext, String focuselements,
                                    String imagestyle, String imageprompt) throws IOException, InterruptedException, AWTException {

        logger.info("**** Starting Image Generation Test for brand: " + brandname + " ****");

        MenuPage menu = new MenuPage(driver);

        try {
            navigateThroughImageGenerationFlow(brandname, category, abouturbrand, tgname, gender, agegroup, region, brandlogo, productname, 
                                                productdescription, productlogo, campaignname, objective, socialmedia, size, 
                                                imagecontext, focuselements, imagestyle, imageprompt);

            // Wait for the image generation and validate its visibility
            waitForImageGeneration();

            // Execute post-image generation tests
            runPostImageGenerationTests();

            //menu.clickHomeButton();
            logger.info("**** Finished Image Generation Test for brand: " + brandname + " ****");

        } catch (Exception e) {
            // Handle image generation failure
            handleImageGenerationFailure(menu, brandname, e);
        }
    }

    /**
     * Navigate through the steps to generate the image.
     */
    private void navigateThroughImageGenerationFlow(String brandname, String category, String abouturbrand, String tgname,
                                                    String gender, String agegroup, String region, String brandlogo, String productname,
                                                    String productdescription, String productlogo, String campaignname, String objective,
                                                    String socialmedia, String size, String imagecontext, String focuselements,
                                                    String imagestyle, String imageprompt) throws IOException, InterruptedException, AWTException {
        CommonBrandingDetailsPageTextToImageGeneration cbdp = new CommonBrandingDetailsPageTextToImageGeneration();
        CommonCampaignOverviewDetailsPageInTextToImageGeneration codp = new CommonCampaignOverviewDetailsPageInTextToImageGeneration();
        CommonMediaChannelsSelectionPageInTextToImageGeneration csp = new CommonMediaChannelsSelectionPageInTextToImageGeneration();
        CommonCreativeDesignSelectionPageInTextToImageGeneration ccdsp = new CommonCreativeDesignSelectionPageInTextToImageGeneration();

        cbdp.BrandingDetailsPageInTextToImage(brandname, category, abouturbrand, tgname, gender, agegroup, region, brandlogo, productname, productdescription, productlogo);
        codp.CampaignOverviewDetailsPageInTextToImage(campaignname, objective, productname, tgname);
        csp.MediaChannelsSelectionPageInTextToImage(socialmedia, size);
        ccdsp.CreativeDesignSelectionPageInTextToImage(imagecontext, focuselements, imagestyle, imageprompt);
    }

    /**
     * Wait for the image generation and validate it.
     */
    private void waitForImageGeneration() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement generatedImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='slick-list']//img")));
        Assert.assertNotNull(generatedImage, "Image was not generated within 3 minutes.");
    }

    /**
     * Run all post-image generation tests.
     * @throws Exception 
     */
    private void runPostImageGenerationTests() throws Exception {
        TC_008_PostImageGenerationTest test = new TC_008_PostImageGenerationTest();
        test.dislikeGeneratedImageFunctionalityTest();
        test.likeGeneratedImageFunctionalityTest();
        test.saveGeneratedImageFunctionalityTest();
        test.verifyImageRegeneration();
        //test.ImageDownloading();
        //test.testRevealPromptButtonFunctionality();
        test.testOpenWithBrandCanvasFunctionality();
    }

    /**
     * Handle the failure during image generation, check credit availability, and take appropriate actions.
     */
    private void handleImageGenerationFailure(MenuPage menu, String brandname, Exception e) throws IOException {
        logger.error("Image generation failed for brand: " + brandname, e);

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
            logger.error("Error checking credit balance for brand: " + brandname, creditException);
            String creditErrorScreenshotPath = captureScreen("CreditCheckError_Iteration_" + brandname);
            logger.error("Credit check error details saved at: " + creditErrorScreenshotPath);
            Assert.fail("Error during credit check. Screenshot saved at: " + creditErrorScreenshotPath);
        }
    }
}
