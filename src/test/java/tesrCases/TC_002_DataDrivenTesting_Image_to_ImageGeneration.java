package tesrCases;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import GenerateImage.DataProviderClass;
import pageObjects.ImageCraftAI;
import pageObjects.ImageCraftImageToImage;
import pageObjects.ImageGeneratedPage;
import pageObjects.MenuPage;
import testBase.BaseClass;

public class TC_002_DataDrivenTesting_Image_to_ImageGeneration extends BaseClass{
	private ImageGeneratedPage postimage;
	 
	 @Test(dataProvider = "ImageToImageGenerationData", dataProviderClass = DataProviderClass.class, priority = 1)
	    public void ImageGanerationtext(String ProductName, String selectionofproductimgoption, String UlpoadImageFromSys, String SelectingImageBackdrops,String Prompt, String ReferenceImageSelection,String ReferenceImageFromSys) throws IOException, InterruptedException, AWTException {
		// MenuPage menu = new MenuPage(driver);
		 MenuPage menu = new MenuPage(driver);
		 ImageCraftImageToImage imgtoimg=new ImageCraftImageToImage(driver);
		 ImageCraftAI im = new ImageCraftAI(driver);
		 logger.info("****Starting TC_002_DataDrivenTesting_Image_to_ImageGeneration***************");
		 menu.clickImageCraftAIButton();
		 im.clickOnTryItOutImagetoImagebutton();
		 imgtoimg.SendProductNameTextfieldData(ProductName);
		 imgtoimg.ClickOnProductNameNextButton();
		 //imgtoimg.ClickOnProductImageUploadButton(selectionofproductimgoption);
		  //imgtoimg.SelectImagesWithUseAsset();
		 imgtoimg.ClickOnProductImageUploadButton(selectionofproductimgoption, ReferenceImageFromSys);
		 //imgtoimg.ImgUploadUseAnAssetButton();
		 imgtoimg.ClickOnuploadimgUseAnAssetNextButton();
		 imgtoimg.ClickOnAutoGenerateButton(SelectingImageBackdrops,Prompt);
		 Thread.sleep(2000);
		 imgtoimg.ClickOnImageToImageGenerateButton();
		 Thread.sleep(1800);	 
	 
	 }}

