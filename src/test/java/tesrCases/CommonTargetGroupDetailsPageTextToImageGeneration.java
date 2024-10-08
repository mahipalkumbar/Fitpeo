package tesrCases;

import pageObjects.Addbrandinfo;
import testBase.BaseClass;

public class CommonTargetGroupDetailsPageTextToImageGeneration extends BaseClass{
	public void TargetGroupDetailsPageInTextToImage(String brandname, String category, String abouturbrand, String tgname,
            String gender, String agegruop, String region, String brandlogo, String productname,
            String productdescription, String productlogo, String campaignname, String objective,
            String socialmedia, String size, String imagecontext, String focuselements,
            String imagestyle, String imageprompt) throws InterruptedException {
		Addbrandinfo a = new Addbrandinfo(driver);
		 //a.addTargetGroup();
         a.TargetNameText(tgname);
         a.Gender(gender);
         a.age(agegruop);
         a.selectRegiontg(region);
         a.nexttg();
}}
