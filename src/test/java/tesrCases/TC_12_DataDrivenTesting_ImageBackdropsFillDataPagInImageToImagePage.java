package tesrCases;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import GenerateImage.DataProviderClass;
import pageObjects.Creativedesign;
import pageObjects.MenuPage;
import testBase.BaseClass;

public class TC_12_DataDrivenTesting_ImageBackdropsFillDataPagInImageToImagePage extends BaseClass{
	@Test(dataProvider = "ImageToImageGenerationData", dataProviderClass = DataProviderClass.class)
	 @Parameters({"index"})
	public void DataDrivenTesting_ImageBackdropsFillDataPagInImageToImagePage(String ProductName, String selectionofproductimgoption, String UlpoadImageFromSys, String SelectingImageBackdrops,String Prompt, String ReferenceImageSelection,String ReferenceImageFromSys) throws InterruptedException, IOException, AWTException {
		logger.info("****Starting TC_012_DataDrivenTesting_ImageBackdropsFillDataPagInImageToImagePage****");
		//ImageCraftImageToImage imgtoimg=new ImageCraftImageToImage(driver);
		Creativedesign cr = new Creativedesign(driver);
		MenuPage menu = new MenuPage(driver);
		CommonImageBackdropsFillDataPagInImageToImagePage cibfdp=new CommonImageBackdropsFillDataPagInImageToImagePage();
		cibfdp.ImageBackdropsFillDataPagInImageToImagePage(ProductName,selectionofproductimgoption,UlpoadImageFromSys,SelectingImageBackdrops,Prompt,ReferenceImageSelection,ReferenceImageFromSys);
		boolean isDisplayed=cr.ImageGenerationStatus();
		try {
			Assert.assertTrue(isDisplayed, "Image is not Generated!");
			menu.clickHomeButton();
		}catch (AssertionError e) {
			 String screenshotPath = captureScreen("DataDrivenTesting_ImageBackdropsFillDataPagInImageToImagePage" + ProductName);
			 logger.error("Failed TC details saved at: " + screenshotPath);
	            throw e; // Re-throw the error to mark the test as failed
	        }
		logger.info("****Finished TC_012_DataDrivenTesting_ImageBackdropsFillDataPagInImageToImagePage for Product Name: " + ProductName+"****");
		}

}
