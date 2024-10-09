package tesrCases;

import pageObjects.Addbrandinfo;
import pageObjects.ImageCraftAI;
import pageObjects.MenuPage;
import testBase.BaseClass;

public class CommonBrandingDetailsPageTextToImageGeneration extends BaseClass{


	public boolean BrandingDetailsPageInTextToImage(String brandname, String category, String abouturbrand) throws InterruptedException {
		MenuPage menu = new MenuPage(driver);
		Addbrandinfo a = new Addbrandinfo(driver);
		ImageCraftAI im = new ImageCraftAI(driver);
		menu.clickFullviewMenuButton();
		menu.clickImageCraftAIButton();
		im.Tryitouttexttoimage();
		a.addNewBrandButton();
        a.sendAddBrand(brandname);
        a.selectCategory(category);
        a.aboutYourBrand(abouturbrand);
        boolean isDisplayed=a.addTargetGroup();
        return isDisplayed;
        
	}

}
