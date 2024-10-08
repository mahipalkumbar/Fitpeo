package tesrCases;

import java.awt.AWTException;
import java.io.IOException;

import pageObjects.ImageCraftAI;
import pageObjects.ImageCraftImageToImage;
import pageObjects.MenuPage;
import testBase.BaseClass;

public class CommonProductNameTextFieldPageInImageToImageGeneration extends BaseClass{
	 public void ProductNameTextFieldPageInImageToImageGeneration(String ProductName, String selectionofproductimgoption, String UlpoadImageFromSys, String SelectingImageBackdrops,String Prompt, String ReferenceImageSelection,String ReferenceImageFromSys) throws IOException, InterruptedException, AWTException {
		 MenuPage menu = new MenuPage(driver);
		 ImageCraftAI im = new ImageCraftAI(driver);
		 ImageCraftImageToImage imgtoimg=new ImageCraftImageToImage(driver);
		 menu.clickImageCraftAIButton();
		 im.clickOnTryItOutImagetoImagebutton();
		 imgtoimg.SendProductNameTextfieldData(ProductName);
	 }
}
