package tesrCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import GenerateImage.DataProviderClass;
import pageObjects.Addbrandinfo;
import pageObjects.CampaignName;
import testBase.BaseClass;

public class TC_005_DataDrivenTestingCampaignOverviewDetailsPageInTextToImageGeneration extends BaseClass {
	@Test(dataProvider = "imageGenerationData", dataProviderClass = DataProviderClass.class, dependsOnMethods = "tesrCases.TC_004_DataDrivenTestingBrandSelectionPageInTextToImageGeneration.DataDrivenTestingBrandSelectionPageInTextToImageGeneration")
	 @Parameters({"index"})
    public void DataDrivenTestingCampaignOverviewDetailsPageInTextToImageGeneration(String brandname, String category, String abouturbrand, String tgname,String gender, String agegruop, String region, String brandlogo, String productname,String productdescription, String productlogo, String campaignname, String objective,String socialmedia, String size, String imagecontext, String focuselements,String imagestyle, String imageprompt) throws InterruptedException, IOException {
		logger.info("****Starting TC_005_DataDrivenTestingCampaignOverviewDetailsPageInTextToImageGeneration****");
		CommonCampaignOverviewDetailsPageInTextToImageGeneration codp=new CommonCampaignOverviewDetailsPageInTextToImageGeneration();
		codp.CampaignOverviewDetailsPageInTextToImage(brandname, category, abouturbrand, tgname, gender, agegruop, region, brandlogo, productname, productdescription, productlogo, campaignname, objective, socialmedia, size, imagecontext, focuselements, imagestyle, imageprompt);
		CampaignName c = new CampaignName(driver);
		boolean isDisplayed=c.nextc();
		try {
			Assert.assertTrue(isDisplayed, "Media Channels page is not displayed!");
		}catch (AssertionError e) {
			 String screenshotPath = captureScreen("DataDrivenTestingCampaignOverviewDetailsPageInTextToImageGeneration" + brandname);
			 logger.error("Failed TC details saved at: " + screenshotPath);
	            throw e; // Re-throw the error to mark the test as failed
	        }
		logger.info("****Finished TC_005_DataDrivenTestingCampaignOverviewDetailsPageInTextToImageGeneration for brand: " + brandname+"****");
		}
		
}
