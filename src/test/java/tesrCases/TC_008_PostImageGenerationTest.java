package tesrCases;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ImageGeneratedPage;
import pageObjects.MenuPage;
import testBase.BaseClass;

public class TC_008_PostImageGenerationTest extends BaseClass {
   // ImageGeneratedPage postImagePage = new ImageGeneratedPage(driver);

    @Test(priority=1)
    public void likeGeneratedImageFunctionalityTest() throws IOException {
    	ImageGeneratedPage postImagePage = new ImageGeneratedPage(driver);
        logger.info("*************** Starting LikeGeneratedImageFunctionalityTest ***************");

        try {
            boolean isLikeButtonClicked = postImagePage.clickOnLikeButtonAfterImageGenerated();
            Assert.assertTrue(isLikeButtonClicked, "Like button action failed to perform.");
            logger.info("Test Passed: Like button action performed successfully.");
        } catch (AssertionError e) {
            handleErrorWithScreenshot(e, "likeGeneratedImageFunctionalityTest");
        }

        logger.info("*************** Ending LikeGeneratedImageFunctionalityTest ***************");
    }

    @Test(priority=2)
    public void dislikeGeneratedImageFunctionalityTest() throws IOException {
    	ImageGeneratedPage postImagePage = new ImageGeneratedPage(driver);
        logger.info("*************** Starting DisLikeGeneratedImageFunctionalityTest ***************");

        try {
            boolean isDislikeButtonClicked = postImagePage.clickOnDisLikeButtonAfterImageGenerated();
            Assert.assertTrue(isDislikeButtonClicked, "Dislike button action failed to perform.");
            logger.info("Test Passed: Dislike button action performed successfully.");
        } catch (AssertionError e) {
            handleErrorWithScreenshot(e, "dislikeGeneratedImageFunctionalityTest");
        }

        logger.info("*************** Ending DisLikeGeneratedImageFunctionalityTest ***************");
    }

    @Test(priority=3)
    public void saveGeneratedImageFunctionalityTest() throws Exception {
    	ImageGeneratedPage postImagePage = new ImageGeneratedPage(driver);
        logger.info("*************** Starting saveGeneratedImageFunctionalityTest ***************");

        try {
            boolean isPopupDisplayed = postImagePage.clickOnSaveButton();
            Assert.assertTrue(isPopupDisplayed, "Save button click did not display the popup.");
            logger.info("Test Passed: Popup displayed successfully after clicking the Save button.");
        } catch (AssertionError e) {
            handleErrorWithScreenshot(e, "saveGeneratedImageFunctionalityTest");
        }

        logger.info("*************** Ending saveGeneratedImageFunctionalityTest ***************");
    }

    @Test(priority=4)
    public void verifyImageRegeneration() throws Exception {
    	ImageGeneratedPage postImagePage = new ImageGeneratedPage(driver);
        logger.info("*************** Starting verifyImageRegeneration Method ***************");

        try {
            boolean isRegenerationSuccessful = postImagePage.clickOnRegenerateButton();
            Assert.assertTrue(isRegenerationSuccessful, "Image regeneration failed. The number of images did not increase by 1.");
            logger.info("Test Passed: Image regeneration successful. The number of images increased by 1.");
        } catch (AssertionError e) {
            handleErrorWithScreenshot(e, "verifyImageRegeneration");
        }

        logger.info("*************** Ending verifyImageRegeneration Method ***************");
    }
    
    @Test(priority=5)
    public void testRevealPromptButtonFunctionality() throws IOException {
        ImageGeneratedPage postImagePage = new ImageGeneratedPage(driver);
        logger.info("**********Starting testRevealPromptButtonFunctionality Method**********");

        // Call the clickOnRevealPromptButton method and store the returned values
        String[] concatenatedValues = postImagePage.clickOnRevealPromptButton();
        String popupText = concatenatedValues[0];
        String textString1 = concatenatedValues[1];
        MenuPage menu = new MenuPage(driver);
        // Validate the expected message
        logger.info("Validating expected message..");
        try {
            Assert.assertEquals(popupText, textString1, "Strings are not equal!");
            menu.clickHomeButton();
        } catch (AssertionError e) {
            handleErrorWithScreenshot(e, "testRevealPromptButtonFunctionality");
        }

        logger.info("Ending testRevealPromptButtonFunctionality Method");
    }



    // Method to handle error, log and capture screenshot
    private void handleErrorWithScreenshot(AssertionError e, String testMethod) throws IOException {
        logger.error("********** Error occurred during " + testMethod + " **********");
        logger.error("Error Message: " + e.getMessage());

        // Capture screenshot on failure
        String screenshotPath = captureScreen(testMethod);
        logger.error("Screenshot captured: " + screenshotPath);

        // Rethrow the assertion error to fail the test
        throw e;
    }
}
