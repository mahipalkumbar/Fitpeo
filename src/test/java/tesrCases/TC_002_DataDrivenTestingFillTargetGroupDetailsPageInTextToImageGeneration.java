package tesrCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import GenerateImage.DataProviderClass;
import pageObjects.Addbrandinfo;
import testBase.BaseClass;

public class TC_002_DataDrivenTestingFillTargetGroupDetailsPageInTextToImageGeneration extends BaseClass {
	
	@Test(dataProvider = "imageGenerationData", dataProviderClass = DataProviderClass.class, dependsOnMethods = "tesrCases.TC_001_DataDrivenTestingFillBrandingDetailsPageInTextToImageGeneration.DataDrivenTestingFillBrandingDetailsPageInTextToImageGeneration")
	@Parameters({"index"})
    public void DataDrivenTestingFillTargetGroupDetailsPageInTextToImageGeneration(String brandname, String category, String abouturbrand, String tgname,String gender, String agegruop, String region, String brandlogo, String productname,String productdescription, String productlogo, String campaignname, String objective,String socialmedia, String size, String imagecontext, String focuselements,String imagestyle, String imageprompt) throws InterruptedException, IOException {
		logger.info("****Starting Test ***************");
		
		CommonTargetGroupDetailsPageTextToImageGeneration ctgdp=new CommonTargetGroupDetailsPageTextToImageGeneration();
		ctgdp.TargetGroupDetailsPageInTextToImage(brandname, category, abouturbrand, tgname, gender, agegruop, region, brandlogo, productname, productdescription, productlogo, campaignname, objective, socialmedia, size, imagecontext, focuselements, imagestyle, imageprompt);
		Addbrandinfo a = new Addbrandinfo(driver);
		boolean isDisplayed=a.nextABIbutton();
		try {
			Assert.assertTrue(isDisplayed, "Edit Brand Info page is not displayed!");
		}catch (AssertionError e) {
			 String screenshotPath = captureScreen("DataDrivenTestingFillTargetGroupDetailsPageInTextToImageGeneration" + brandname);
			 logger.error("Failed TC details saved at: " + screenshotPath);
	            throw e; // Re-throw the error to mark the test as failed
	        }
		 logger.info("****Finished Test for brand: " + brandname + " ***************");
		}		
}
