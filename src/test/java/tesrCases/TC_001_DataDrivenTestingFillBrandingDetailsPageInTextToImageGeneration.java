package tesrCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import GenerateImage.DataProviderClass;
import testBase.BaseClass;

public class TC_001_DataDrivenTestingFillBrandingDetailsPageInTextToImageGeneration extends BaseClass {
	 @Parameters("index") 
	 @Test(dataProvider = "imageGenerationData", dataProviderClass = DataProviderClass.class) 
    public void DataDrivenTestingFillBrandingDetailsPageInTextToImageGeneration(
        String brandname, String category, String abouturbrand, String tgname, String gender, 
        String agegruop, String region, String brandlogo, String productname, 
        String productdescription, String productlogo, String campaignname, 
        String objective, String socialmedia, String size, String imagecontext, 
        String focuselements, String imagestyle, String imageprompt
    ) throws InterruptedException, IOException {
        
        logger.info("****Starting Test ***************");

        CommonBrandingDetailsPageTextToImageGeneration cbdp = new CommonBrandingDetailsPageTextToImageGeneration();
        boolean isDisplayed = cbdp.BrandingDetailsPageInTextToImage(brandname, category, abouturbrand);
        
        try {
            Assert.assertTrue(isDisplayed, "New Target Group page is not displayed!");
        } catch (AssertionError e) {
            String screenshotPath = captureScreen("DataDrivenTestingFillBrandingDetailsPageInTextToImageGeneration" + brandname);
            logger.error("Failed Test Case details saved at: " + screenshotPath);
            throw e;  // Re-throw the error to mark the test as failed
        }
        
        logger.info("****Finished Test for brand: " + brandname + " ***************");
    }
}
