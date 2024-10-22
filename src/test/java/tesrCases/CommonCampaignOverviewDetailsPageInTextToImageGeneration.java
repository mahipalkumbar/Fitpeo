package tesrCases;

import pageObjects.CampaignName;
import testBase.BaseClass;

public class CommonCampaignOverviewDetailsPageInTextToImageGeneration extends BaseClass{
	public void CampaignOverviewDetailsPageInTextToImage(String campaignname, String objective,
            String productname, String tgname) throws InterruptedException {
		 CampaignName c = new CampaignName(driver);
		 c.Campaigname(campaignname);
         c.Objective(objective);
         c.Chooseproduct(productname);
         c.choosetg(tgname);
         c.nextc();
	}

}
