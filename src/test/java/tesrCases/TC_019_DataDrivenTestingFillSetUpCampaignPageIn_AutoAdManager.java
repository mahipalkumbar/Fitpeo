package tesrCases;

import org.testng.annotations.Test;

import GenerateImage.DataProviderClass;
import pageObjects.Addbrandinfo;
import pageObjects.BrandDetailsPage;
import pageObjects.SetupCampaignPage;
import testBase.BaseClass;

public class TC_019_DataDrivenTestingFillSetUpCampaignPageIn_AutoAdManager extends BaseClass{


	@Test(dependsOnMethods = {"tesrCases.TC_018_DataDrivenTestingFillBrandingDetailsPageIn_AutoAdManager.DataDrivenTestingFillBrandingDetailsPageIn_AutoAdManager"}, dataProvider = "AutoAdManager", dataProviderClass = DataProviderClass.class, priority = 2)
	    public void DataDrivenTestingFillSetUpCampaignPageIn_AutoAdManager(String brandname, String brandlogos, String category, String abouturbrand, String productname,String productdescription, String productlogo, String specifycampaignobjectives, String selectchannels,String campaignname, String campaignurl, String selectingTargetGroupUploadfile,String UploadAudiencePath,String country,String targetname,String gender,String agegroup,String language,String uploadmediaselection,String AskAISuggestion,String headline1,String headline2,String headline3,String description1,String description2,String caption,String Budget,String AISuggestionForBudget) throws Exception {
	    	logger.info("****Starting TC_019_DataDrivenTestingFillSetUpCampaignPageIn_AutoAdManager***************");
	    	SetupCampaignPage setuppage=new SetupCampaignPage(driver);
	    	Addbrandinfo a = new Addbrandinfo(driver);
	       // PageTwo pageTwo = new PageTwo(driver);
	    	setuppage.SelectingCampaignObjectives(specifycampaignobjectives);
	    	
	    	setuppage.SelectingChannels(selectchannels);
	    	setuppage.enterCampaignName(campaignname);
	    	setuppage.enterCampaignURL(campaignurl);
	    	if(selectingTargetGroupUploadfile.equalsIgnoreCase("Target Groups")) {
	    	setuppage.clickOnTargetGroup();
	    	a.TargetNameText(targetname);
	    	a.selectCategory(category);
	    	a.age(language);
	    	//a.TargetNameText();
	    	
	    	}
	        logger.info("****Finished Page Two Test****");
	    }     
	}

	


