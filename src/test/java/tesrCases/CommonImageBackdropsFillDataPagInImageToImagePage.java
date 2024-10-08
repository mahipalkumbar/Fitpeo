package tesrCases;

import java.awt.AWTException;
import java.io.IOException;

import pageObjects.ImageCraftImageToImage;
import testBase.BaseClass;

public class CommonImageBackdropsFillDataPagInImageToImagePage extends BaseClass{
	 public void ImageBackdropsFillDataPagInImageToImagePage(String ProductName, String selectionofproductimgoption, String UlpoadImageFromSys, String SelectingImageBackdrops,String Prompt, String ReferenceImageSelection,String ReferenceImageFromSys) throws IOException, InterruptedException, AWTException {
		 ImageCraftImageToImage imgtoimg=new ImageCraftImageToImage(driver);
		 imgtoimg.ClickOnAutoGenerateButton(SelectingImageBackdrops,Prompt);
		 imgtoimg.ClickOnImageToImageGenerateButton(); 
	 }}
