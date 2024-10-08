package tesrCases;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import GenerateImage.DataProviderClass;
import pageObjects.Addbrandinfo;
import pageObjects.BrandDetailsPage;
import pageObjects.CampaignName;
import pageObjects.CampulseAI;
import pageObjects.MenuPage;
import testBase.BaseClass;

public class TC_018_DataDrivenTestingFillBrandingDetailsPageIn_AutoAdManager extends BaseClass{
	@Test(dataProvider = "AutoAdManager", dataProviderClass = DataProviderClass.class, priority = 1)
    public void DataDrivenTestingFillBrandingDetailsPageIn_AutoAdManager(String brandname, String brandlogos, String category, String abouturbrand, String productname,String productdescription, String productlogo, String specifycampaignobjectives, String selectchannels,String campaignname, String campaignurl, String uploadaudience) throws IOException, InterruptedException, AWTException {
		logger.info("****Starting TC_018_DataDrivenTestingFillBrandingDetailsPageIn_AutoAdManager***************");
		BrandDetailsPage brdpage=new BrandDetailsPage(driver);
		CommonBrandingDetailsPageAutoAdManager brd=new CommonBrandingDetailsPageAutoAdManager();
		brd.AutoAdManager( brandname,brandlogos,category,abouturbrand,productname,productdescription,productlogo,specifycampaignobjectives,selectchannels,campaignname,campaignurl,uploadaudience);
		boolean isDisplayed=brdpage.SetupCampaignPageIsDisplayed();
		try {
			Assert.assertTrue(isDisplayed, "Setup Campaign page is not displayed!");
		}catch (AssertionError e) {
			 String screenshotPath = captureScreen("FillBrandingDetailsPageInAutoAdManagerFailedForBrand_Iteration_" + brandname);
			 logger.error("Failed TC details saved at: " + screenshotPath);
	            throw e; // Re-throw the error to mark the test as failed
	        }
		logger.info("******Finished TC_003_DataDrivenTestingFillBrandingDetailsPageIn_AutoAdManager for brand: " + brandname);
		}
}
