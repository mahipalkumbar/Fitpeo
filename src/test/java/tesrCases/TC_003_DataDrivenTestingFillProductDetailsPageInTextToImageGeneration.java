package tesrCases;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import GenerateImage.DataProviderClass;
import pageObjects.Addbrandinfo;
import testBase.BaseClass;

public class TC_003_DataDrivenTestingFillProductDetailsPageInTextToImageGeneration extends BaseClass {
	@Test(dataProvider = "imageGenerationData", dataProviderClass = DataProviderClass.class, dependsOnMethods = "tesrCases.TC_002_DataDrivenTestingFillTargetGroupDetailsPageInTextToImageGeneration.DataDrivenTestingFillTargetGroupDetailsPageInTextToImageGeneration")
	 @Parameters({"index"})
    public void DataDrivenTestingFillProductDetailsPageInTextToImageGeneration(String brandname, String category, String abouturbrand, String tgname,String gender, String agegruop, String region, String brandlogo, String productname,String productdescription, String productlogo, String campaignname, String objective,String socialmedia, String size, String imagecontext, String focuselements,String imagestyle, String imageprompt) throws InterruptedException, IOException, AWTException {
		logger.info("****Starting TC_003_DataDrivenTestingFillProductDetailsPageInTextToImageGeneration***************");
		CommonProductDetailsPageTextToImageGeneration cpdp=new CommonProductDetailsPageTextToImageGeneration();
		cpdp.ProductDetailsPageInTextToImage(brandname, category, abouturbrand, tgname, gender, agegruop, region, brandlogo, productname, productdescription, productlogo, campaignname, objective, socialmedia, size, imagecontext, focuselements, imagestyle, imageprompt);
		Addbrandinfo a = new Addbrandinfo(driver);
		boolean isDisplayed=a.nextButtonInEditBrandInfo();
		try {
			Assert.assertTrue(isDisplayed, "Branding details page is not displayed! while choosing brand");
		}catch (AssertionError e) {
			 String screenshotPath = captureScreen("DataDrivenTestingFillProductDetailsPageInTextToImageGeneration" + brandname);
			 logger.error("Failed TC details saved at: " + screenshotPath);
	            throw e; // Re-throw the error to mark the test as failed
	        }
		logger.info("******Finished TC_003_DataDrivenTestingFillProductDetailsPageInTextToImageGeneration for brand: " + brandname);
		}
		
}