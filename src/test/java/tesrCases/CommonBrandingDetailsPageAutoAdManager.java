package tesrCases;

import java.awt.AWTException;
import java.io.IOException;

import pageObjects.Addbrandinfo;
import pageObjects.BrandDetailsPage;
import pageObjects.CampaignName;
import pageObjects.CampulseAI;
import pageObjects.MenuPage;
import testBase.BaseClass;

public class CommonBrandingDetailsPageAutoAdManager extends BaseClass{
	public void AutoAdManager(String brandname, String brandlogos, String category, String abouturbrand, String productname,String productdescription, String productlogo, String specifycampaignobjectives, String selectchannels,String campaignname, String campaignurl, String uploadaudience) throws IOException, InterruptedException, AWTException {
		MenuPage menu = new MenuPage(driver);
		CampulseAI cai=new CampulseAI(driver);
		BrandDetailsPage brpgamanagr=new BrandDetailsPage(driver);
		Addbrandinfo a = new Addbrandinfo(driver);
		CampaignName c = new CampaignName(driver);
		menu.clickFullviewMenuButton();
		menu.clickCampulseAIButton();
		cai.ClickOnAutoAdManagerTryItOutButton();
		brpgamanagr.AddNewbrandbutton();
		 a.sendAddBrand(brandname);
         a.selectCategory(category);
         a.aboutYourBrand(abouturbrand);
         a.addNewProductButton();
         a.ProductNameText(productname);
         a.productDescription(productdescription);
         a.ProdctpageNextButton();
         brpgamanagr.ClickOnNextButtonInBrandingDetailsPageOfAutoAdManager();
        // a.brandnameselected(brandname);
         brpgamanagr.chooseproductinautoadmanagerselected(productname);
         brpgamanagr.ClickOnNextButtonInBrandingDetailsPageOfAutoAdManager();

}}

