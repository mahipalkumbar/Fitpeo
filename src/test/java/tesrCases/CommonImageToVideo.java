package tesrCases;

import pageObjects.Creativedesign;
import pageObjects.MenuPage;
import pageObjects.VideoVistaAI;
import pageObjects.VideoVistaImageToImage;
import testBase.BaseClass;

public class CommonImageToVideo extends BaseClass{
	public void CommonImageToVideoMethod(String Uploadfileselection,String path, String Animationstyleselection, String Amountofmotion, String Animationduration) throws Exception {
		 MenuPage menu = new MenuPage(driver);
		 VideoVistaAI v=new VideoVistaAI(driver);
		 VideoVistaImageToImage vvii=new VideoVistaImageToImage(driver);
		 Creativedesign cr = new Creativedesign(driver);
				
	            // Navigate through the steps to generate the image
	            menu.clickFullviewMenuButton();
	            menu.clickVideoVistaAIButton();
	            v.clickOnTryItOutImagetoVideobutton();
	            vvii.clickOnUploadButton();
	            vvii.ClickOnProductImageUploadButton(Uploadfileselection,path);
	            vvii.selectAnimationStyle(Animationstyleselection);
	            vvii.ClickOnAnimationstylenextbutton();
	            vvii.moveSliderToValue(50);//Amountofmotion
	            vvii.ClickOnNextButtonOnAmountOfMotion();
	            vvii.ClickOnNextButtonOnAnimationDuration();
	            cr.generatef();

	}}
