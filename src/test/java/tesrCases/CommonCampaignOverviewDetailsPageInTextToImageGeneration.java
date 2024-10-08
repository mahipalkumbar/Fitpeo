package tesrCases;

import pageObjects.CampaignName;
import testBase.BaseClass;

public class CommonCampaignOverviewDetailsPageInTextToImageGeneration extends BaseClass{
	public void CampaignOverviewDetailsPageInTextToImage(String brandname, String category, String abouturbrand, String tgname,
            String gender, String agegruop, String region, String brandlogo, String productname,
            String productdescription, String productlogo, String campaignname, String objective,
            String socialmedia, String size, String imagecontext, String focuselements,
            String imagestyle, String imageprompt) throws InterruptedException {
		 CampaignName c = new CampaignName(driver);
		 c.Campaigname(campaignname);
         c.Objective(objective);
         c.Chooseproduct(productname);
         c.choosetg(tgname);
        // c.nextc();
	}

}
