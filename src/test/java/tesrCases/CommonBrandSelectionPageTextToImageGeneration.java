package tesrCases;

import pageObjects.Addbrandinfo;
import testBase.BaseClass;

public class CommonBrandSelectionPageTextToImageGeneration extends BaseClass{
	public void BrandSelectionPageInTextToImage(String brandname, String category, String abouturbrand, String tgname,
            String gender, String agegruop, String region, String brandlogo, String productname,
            String productdescription, String productlogo, String campaignname, String objective,
            String socialmedia, String size, String imagecontext, String focuselements,
            String imagestyle, String imageprompt) throws InterruptedException {
		Addbrandinfo a = new Addbrandinfo(driver);
		Thread.sleep(5000);
		 a.brandNameSelected();
         //a.clicknextbuttoninbrandselection();
		
	}
}
