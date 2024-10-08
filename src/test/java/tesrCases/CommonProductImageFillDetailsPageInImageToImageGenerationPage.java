package tesrCases;

import java.awt.AWTException;
import java.io.IOException;

import pageObjects.ImageCraftAI;
import pageObjects.ImageCraftImageToImage;
import pageObjects.MenuPage;
import testBase.BaseClass;

public class CommonProductImageFillDetailsPageInImageToImageGenerationPage extends BaseClass{
	 public void ProductImageFillDetailsPageInImageToImageGenerationPage(String ProductName, String selectionofproductimgoption, String UlpoadImageFromSys, String SelectingImageBackdrops,String Prompt, String ReferenceImageSelection,String ReferenceImageFromSys) throws IOException, InterruptedException, AWTException {
		 ImageCraftImageToImage imgtoimg=new ImageCraftImageToImage(driver);
		 imgtoimg.ClickOnProductImageUploadButton(selectionofproductimgoption);
			 imgtoimg.ImgUploadUseAnAssetButton();
			// imgtoimg.ClickOnuploadimgUseAnAssetNextButton();
	 }}
