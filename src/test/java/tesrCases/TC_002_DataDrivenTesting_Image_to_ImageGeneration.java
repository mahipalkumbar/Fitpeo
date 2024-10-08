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
		 
		 try {
		        boolean isCreditSufficient = menu.SlideBar();
		       
		        if (!isCreditSufficient) {
		        	logger.error("Insufficient credit balance. Please upgrade plan");
		            Assert.fail("Insufficient credit balance. Please upgrade plan");
		            return; // Exit the test if balance is insufficient
		        }
		    } catch (Exception e) {
		    	
		        logger.error("Error checking credit balance.", e);
		        Assert.fail("Error checking credit balance. " + e.getMessage());
		        return; // Exit the test if there is an error checking the balance
		    }
	 //menu.FullviewMenuButton();
	 logger.info("****Starting TC_002_DataDrivenTesting_Image_to_ImageGeneration***************");
	 menu.clickImageCraftAIButton();
	 im.clickOnTryItOutImagetoImagebutton();
	 imgtoimg.SendProductNameTextfieldData(ProductName);
	 imgtoimg.ClickOnProductNameNextButton();
	 imgtoimg.ClickOnProductImageUploadButton(selectionofproductimgoption);
	// imgtoimg.SelectImagesWithUseAsset();
	 imgtoimg.ImgUploadUseAnAssetButton();
	 imgtoimg.ClickOnuploadimgUseAnAssetNextButton();
	 imgtoimg.ClickOnAutoGenerateButton(SelectingImageBackdrops,Prompt);
	 Thread.sleep(2000);
	 imgtoimg.ClickOnImageToImageGenerateButton();
	 Thread.sleep(1800);	 
	 
	 
	 
	 
	 
	 }}

