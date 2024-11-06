package tesrCases;

import pageObjects.Addbrandinfo;
import pageObjects.CampaignName;
import pageObjects.ImageCraftAI;
import pageObjects.MenuPage;
import pageObjects.ScriptPageInTextToVideo;
import pageObjects.StartSlateEndSlate;
import pageObjects.VideoContentInTextToVideo;
import pageObjects.VideoSettingsInTextToVideo;
import pageObjects.VideoVistaAI;
import testBase.BaseClass;

public class CommonTextToVideo extends BaseClass{
	public void CommonTextToVideoMethod(String brandname, String category, String abouturbrand, String tgname,
            String gender, String agegruop, String region, String brandlogo, String productname,
            String productdescription, String productlogo, String campaignname,String objective,
            String ScriptSelection, String AIPrompt, String YourScript,String writingstyle, String duration, String VideoStactureSelection,
            String VideoSizeSelection, String PaceSelection,String CaptionSelection,String slateselection,String StartSlateUpload,String Endslateupload) throws InterruptedException {
		 MenuPage menu = new MenuPage(driver);
		 ImageCraftAI im = new ImageCraftAI(driver);
		 Addbrandinfo a = new Addbrandinfo(driver);
		 CampaignName c = new CampaignName(driver);
		 VideoVistaAI v=new VideoVistaAI(driver);
		 ScriptPageInTextToVideo s=new ScriptPageInTextToVideo(driver);
		 VideoContentInTextToVideo vc=new VideoContentInTextToVideo(driver);
		 VideoSettingsInTextToVideo vs=new VideoSettingsInTextToVideo(driver);
		 StartSlateEndSlate ss=new StartSlateEndSlate(driver);
		 menu.clickFullviewMenuButton();
         menu.clickVideoVistaAIButton();
         v.clickOnTryItOutTexttoVideobutton();
        // a.clickonbrandingdetailsfullview();
         a.addNewBrandButton();
         a.sendAddBrand(brandname);
         a.selectCategory(category);
         a.aboutYourBrand(abouturbrand);
         a.addTargetGroup();
         a.TargetNameText(tgname);
         a.Gender(gender);
         a.age(agegruop);
         a.selectRegiontg(region);
         a.nexttg();
         a.nextABIbutton();
         a.addNewProductButton();
         a.ProductNameText(productname);
         a.productDescription(productdescription);
         //Thread.sleep(4000);
         a.ProdctpageNextButton();
         //Thread.sleep(4000);
         a.nextButtonInEditBrandInfo();
        // Thread.sleep(4000);
         //a.clickonbrandingdetailsfullview();
         a.brandNameSelected();
         a.clicknextbuttoninbrandselection();
         //Thread.sleep(6000);
         c.Campaigname(campaignname);
         c.Objective(objective);
         c.Chooseproduct(productname);
         c.choosetg(tgname);
         s.nexbuttonInTagName();
         s.clickOnScript(ScriptSelection,AIPrompt,YourScript,writingstyle,duration);
         s.ClickOnGenerateWithAIButtonInScrpt();
         s.ClickOnNextButtonInYOurScript();
         vc.ClickOnVideoStractureNextButton();
         vc.SelectingVideoSizeInVideoContent(VideoSizeSelection);
         vc.ClickOnNextButtonVideoSize();
         vs.ClickOnPaceNextButton();
         vs.SelectingCaptionsOption(CaptionSelection);
         vs.ClickOnCaptionNextButton();
         //ss.UploadStartEndSlateFromSystem(slateselection,StartSlateUpload,Endslateupload);
         ss.clickOnNextButtonInSlate();
         ss.ClickOnGenerateButton();
		
	}

}
