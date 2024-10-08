package tesrCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import GenerateImage.DataProviderClass;
import pageObjects.Creativedesign;
import pageObjects.MenuPage;
import testBase.BaseClass;

public class TC_007_DataDrivenTestingCreativeDesignSelectionPageInTextToImageGeneration extends BaseClass {
	@Test(dataProvider = "imageGenerationData", dataProviderClass = DataProviderClass.class, dependsOnMethods = "tesrCases.TC_006_DataDrivenTestingMediaChannelsSelectionPageInTextToImageGeneration.DataDrivenTestingMediaChannelsSelectionPageInTextToImageGeneration")
	 @Parameters({"index"})
    public void DataDrivenTestingCreativeDesignSelectionPageInTextToImageGeneration(String brandname, String category, String abouturbrand, String tgname,String gender, String agegruop, String region, String brandlogo, String productname,String productdescription, String productlogo, String campaignname, String objective,String socialmedia, String size, String imagecontext, String focuselements,String imagestyle, String imageprompt) throws InterruptedException, IOException {
		logger.info("****Starting TC_007_DataDrivenTestingCreativeDesignSelectionPageInTextToImageGeneration****");
		CommonCreativeDesignSelectionPageInTextToImageGeneration ccdsp=new CommonCreativeDesignSelectionPageInTextToImageGeneration();
		ccdsp.CreativeDesignSelectionPageInTextToImage(brandname, category, abouturbrand, tgname, gender, agegruop, region, brandlogo, productname, productdescription, productlogo, campaignname, objective, socialmedia, size, imagecontext, focuselements, imagestyle, imageprompt);
		Creativedesign cr = new Creativedesign(driver);
		MenuPage menu = new MenuPage(driver);
		boolean isDisplayed=cr.ImageGenerationStatus();
		try {
			Assert.assertTrue(isDisplayed, "Image is not Generated!");
			menu.clickHomeButton();
		}catch (AssertionError e) {
			 String screenshotPath = captureScreen("DataDrivenTestingCreativeDesignSelectionPageInTextToImageGeneration" + brandname);
			 logger.error("Failed TC details saved at: " + screenshotPath);
	            throw e; // Re-throw the error to mark the test as failed
	        }
		logger.info("****Finished TC_007_DataDrivenTestingCreativeDesignSelectionPageInTextToImageGeneration for brand: " + brandname+"****");
		}
}
