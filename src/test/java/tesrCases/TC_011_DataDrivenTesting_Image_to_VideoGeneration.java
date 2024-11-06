package tesrCases;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.Test;

import GenerateImage.DataProviderClass;
import pageObjects.ImageCraftAI;
import pageObjects.MenuPage;
import pageObjects.VideoVistaAI;
import pageObjects.VideoVistaImageToImage;
import testBase.BaseClass;

public class TC_011_DataDrivenTesting_Image_to_VideoGeneration extends BaseClass {
	 @Test(dataProvider = "ImageToVideoGenerationData", dataProviderClass = DataProviderClass.class, priority = 1)
	    public void ImageToVideoGeneration(String Uploadfileselection,String path, String Animationstyleselection, String Amountofmotion, String Animationduration) {
		// int iteration=1;
		 MenuPage menu = new MenuPage(driver);
		 ImageCraftAI im = new ImageCraftAI(driver);
		 VideoVistaAI v=new VideoVistaAI(driver);
		 VideoVistaImageToImage vvii=new VideoVistaImageToImage(driver);
		// logger.info("****Starting TC_010_DataDrivenTesting_Text_to_VideoGeneration:"+ iteration +"***************");
		 
		
	            // Navigate through the steps to generate the image
	            menu.clickFullviewMenuButton();
	            menu.clickVideoVistaAIButton();
	            v.clickOnTryItOutImagetoVideobutton();
	            vvii.clickOnUploadButton();
	            vvii.ClickOnProductImageUploadButton(Uploadfileselection,path);
	            vvii.selectAnimationStyle(Animationstyleselection);
	            vvii.ClickOnAnimationstylenextbutton();
	            vvii.AdjustingAmountofMotionSlider(Amountofmotion);
	            
	            vvii.AdjustAnimationDurationSlider(Animationduration);
	            
	 
		 
		// MenuPage menu = new MenuPage(driver);
	 }}
