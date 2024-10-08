package tesrCases;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import GenerateImage.DataProviderClass;
import pageObjects.ImageCraftImageToImage;
import testBase.BaseClass;

public class TC_10_DataDrivenTesting_ProductNameTextFieldPageInImageToImageGeneration extends BaseClass{
	@Test(dataProvider = "ImageToImageGenerationData", dataProviderClass = DataProviderClass.class)
	 @Parameters({"index"})
	public void DataDrivenTesting_ProductNameTextFieldPageInImageToImageGeneration(String ProductName, String selectionofproductimgoption, String UlpoadImageFromSys, String SelectingImageBackdrops,String Prompt, String ReferenceImageSelection,String ReferenceImageFromSys) throws InterruptedException, IOException, AWTException {
		logger.info("****Starting TC_10_DataDrivenTesting_ProductNameTextFieldPageInImageToImageGeneration****");
		ImageCraftImageToImage imgtoimg=new ImageCraftImageToImage(driver);
		CommonProductNameTextFieldPageInImageToImageGeneration cpntfp=new CommonProductNameTextFieldPageInImageToImageGeneration();
		cpntfp.ProductNameTextFieldPageInImageToImageGeneration(ProductName,selectionofproductimgoption,UlpoadImageFromSys,SelectingImageBackdrops,Prompt,ReferenceImageSelection,ReferenceImageFromSys);
		boolean isDisplayed=imgtoimg.ClickOnProductNameNextButton();
		try {
			Assert.assertTrue(isDisplayed, "Product Image page is not displayed!");
		}catch (AssertionError e) {
			 String screenshotPath = captureScreen("DataDrivenTesting_ProductNameTextFieldPageInImageToImageGeneration" + ProductName);
			 logger.error("Failed TC details saved at: " + screenshotPath);
	            throw e; // Re-throw the error to mark the test as failed
	        }
		logger.info("****Finished TC_10_DataDrivenTesting_ProductNameTextFieldPageInImageToImageGeneration for Product Name: " + ProductName+"****");
		}

		//Addbrandinfo a = new Addbrandinfo(driver);
		//boolean isDisplayed=a.addTargetGroup();
	
	

}
