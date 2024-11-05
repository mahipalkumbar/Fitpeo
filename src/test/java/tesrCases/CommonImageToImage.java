package tesrCases;

import java.awt.AWTException;
import java.io.IOException;

import pageObjects.ImageCraftAI;
import pageObjects.ImageCraftImageToImage;
import pageObjects.MenuPage;
import testBase.BaseClass;

public class CommonImageToImage extends BaseClass{
	public void CommonImageToImageMethod(String ProductName, String selectionofproductimgoption, String UlpoadImageFromSys, String SelectingImageBackdrops,String Prompt, String ReferenceImageSelection,String ReferenceImageFromSys) throws Exception {
	 MenuPage menu = new MenuPage(driver);
	 ImageCraftImageToImage imgtoimg=new ImageCraftImageToImage(driver);
	 ImageCraftAI im = new ImageCraftAI(driver);
	 
	 menu.clickImageCraftAIButton();
	 im.clickOnTryItOutImagetoImagebutton();
	 imgtoimg.SendProductNameTextfieldData(ProductName);
	 imgtoimg.ClickOnProductNameNextButton();
	 //imgtoimg.ClickOnProductImageUploadButton(selectionofproductimgoption);
	  //imgtoimg.SelectImagesWithUseAsset();
	 imgtoimg.ClickOnProductImageUploadButton(selectionofproductimgoption, ReferenceImageFromSys);
	 imgtoimg.clickOnUploadImgUseAnAssetNextButton();
	 imgtoimg.ClickOnAutoGenerateButton(SelectingImageBackdrops,Prompt);
	 imgtoimg.ClickOnImageToImageGenerateButton();

}}
