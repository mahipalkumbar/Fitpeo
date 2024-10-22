package tesrCases;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.Test;

import GenerateImage.DataProviderClass;
import pageObjects.ImageCraftAI;
import pageObjects.MenuPage;
import pageObjects.VideoVistaAI;
import testBase.BaseClass;

public class TC_011_DataDrivenTesting_Image_to_VideoGeneration extends BaseClass {
	 @Test(dataProvider = "ImageToVideoGenerationData", dataProviderClass = DataProviderClass.class, priority = 1)
	    public void ImageToVideoGeneration(String Uploadfileselection, String Animationstyleselection, String Amountofmotion, String Animationduration) {
		 int iteration=1;
		 MenuPage menu = new MenuPage(driver);
		 ImageCraftAI im = new ImageCraftAI(driver);
		 VideoVistaAI v=new VideoVistaAI(driver);
		 logger.info("****Starting TC_010_DataDrivenTesting_Text_to_VideoGeneration:"+ iteration +"***************");
		 
		/* try {
	            // Navigate through the steps to generate the image
	            menu.clickFullviewMenuButton();
	            menu.clickVideoVistaAIButton();
	            v.clickOnTryItOutImagetoVideobutton();
	 }*/
		 
		// MenuPage menu = new MenuPage(driver);
	 }}
