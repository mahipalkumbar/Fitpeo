package tesrCases;

import pageObjects.Addbrandinfo;
import pageObjects.ImageCraftAI;
import pageObjects.MenuPage;
import testBase.BaseClass;

public class CommonBrandingDetailsPageTextToImageGeneration extends BaseClass{


	public void BrandingDetailsPageInTextToImage(String brandname, String category, String abouturbrand, String tgname,
            String gender, String agegruop, String region, String brandlogo, String productname,
            String productdescription, String productlogo) throws InterruptedException {
		MenuPage menu = new MenuPage(driver);
		Addbrandinfo a = new Addbrandinfo(driver);
		ImageCraftAI im = new ImageCraftAI(driver);
		//menu.clickFullviewMenuButton();
        menu.clickImageCraftAIButton();
        im.Tryitouttexttoimage();
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
        a.ProdctpageNextButton();
        a.nextButtonInEditBrandInfo();
        a.brandNameSelected();
        a.clicknextbuttoninbrandselection();
        
	}

}
