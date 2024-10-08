package tesrCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import GenerateImage.DataProviderClass;
import pageObjects.CampaignName;
import pageObjects.MediaChannels;
import testBase.BaseClass;

public class TC_006_DataDrivenTestingMediaChannelsSelectionPageInTextToImageGeneration extends BaseClass {
	@Test(dataProvider = "imageGenerationData", dataProviderClass = DataProviderClass.class, dependsOnMethods = "tesrCases.TC_005_DataDrivenTestingCampaignOverviewDetailsPageInTextToImageGeneration.DataDrivenTestingCampaignOverviewDetailsPageInTextToImageGeneration")
	 @Parameters({"index"})
    public void DataDrivenTestingMediaChannelsSelectionPageInTextToImageGeneration(String brandname, String category, String abouturbrand, String tgname,String gender, String agegruop, String region, String brandlogo, String productname,String productdescription, String productlogo, String campaignname, String objective,String socialmedia, String size, String imagecontext, String focuselements,String imagestyle, String imageprompt) throws InterruptedException, IOException {
		logger.info("****Starting TC_006_DataDrivenTestingMediaChannelsSelectionPageInTextToImageGeneration****");
		CommonMediaChannelsSelectionPageInTextToImageGeneration csp=new CommonMediaChannelsSelectionPageInTextToImageGeneration();
		csp.MediaChannelsSelectionPageInTextToImage(brandname, category, abouturbrand, tgname, gender, agegruop, region, brandlogo, productname, productdescription, productlogo, campaignname, objective, socialmedia, size, imagecontext, focuselements, imagestyle, imageprompt);
		MediaChannels m = new MediaChannels(driver);
		boolean isDisplayed=m.goToNext();
		try {
			Assert.assertTrue(isDisplayed, "Creative Design page is not displayed!");
		}catch (AssertionError e) {
			 String screenshotPath = captureScreen("DataDrivenTestingMediaChannelsSelectionPageInTextToImageGeneration" + brandname);
			 logger.error("Failed TC details saved at: " + screenshotPath);
	            throw e; // Re-throw the error to mark the test as failed
	        }
		logger.info("****Finished TC_006_DataDrivenTestingMediaChannelsSelectionPageInTextToImageGeneration for brand: " + brandname+"****");
		}
		
}
