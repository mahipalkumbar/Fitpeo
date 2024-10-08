package tesrCases;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import GenerateImage.DataProviderClass;
import pageObjects.ImageCraftImageToImage;
import testBase.BaseClass;

public class TC_11_DataDrivenTesting_ProductImagePageInImageToImageGeneration extends BaseClass{
	@Test(dataProvider = "ImageToImageGenerationData", dataProviderClass = DataProviderClass.class)
	 @Parameters({"index"})
	public void DataDrivenTesting_ProductImagePageInImageToImageGeneration(String ProductName, String selectionofproductimgoption, String UlpoadImageFromSys, String SelectingImageBackdrops,String Prompt, String ReferenceImageSelection,String ReferenceImageFromSys) throws InterruptedException, IOException, AWTException {
		logger.info("****Starting TC_11_DataDrivenTesting_ProductImagePageInImageToImageGeneration****");
		ImageCraftImageToImage imgtoimg=new ImageCraftImageToImage(driver);
		CommonProductImageFillDetailsPageInImageToImageGenerationPage cpifdp=new CommonProductImageFillDetailsPageInImageToImageGenerationPage();
		cpifdp.ProductImageFillDetailsPageInImageToImageGenerationPage(ProductName,selectionofproductimgoption,UlpoadImageFromSys,SelectingImageBackdrops,Prompt,ReferenceImageSelection,ReferenceImageFromSys);
		boolean isDisplayed=imgtoimg.ClickOnuploadimgUseAnAssetNextButton();
		try {
			Assert.assertTrue(isDisplayed, "Image Backdrops page is not displayed!");
		}catch (AssertionError e) {
			 String screenshotPath = captureScreen("DataDrivenTesting_ProductImagePageInImageToImageGeneration" + ProductName);
			 logger.error("Failed TC details saved at: " + screenshotPath);
	            throw e; // Re-throw the error to mark the test as failed
	        }
		logger.info("****Finished TC_11_DataDrivenTesting_ProductImagePageInImageToImageGeneration for Product Name: " + ProductName+"****");
		}
}
