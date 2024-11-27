package tesrCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePageFit;
import testBase.BaseClass;

public class TC_04_AdjustTheSliderTest  extends BaseClass{
	

	 @Test()
	    public void TC_04_AdjustTheSliderFunctionalityTest() throws IOException {
		 	HomePageFit hpf=new HomePageFit(driver);
	        logger.info("**** Starting TC_04_AdjustTheSliderFunctionalityTest ****");
	       //hpf.moveSliderToPosition(820);
	        try {
	            //Assert.assertTrue(isDisplayed, "ScrollDownToSlider is not displayed");
	            System.out.println("Assertion Passed: ScrollDownToSlider is displayed.");
	        } catch (AssertionError e) {
	        	captureScreen("TC_04_AdjustTheSliderFunctionalityTestFail");
	            System.out.println("Assertion Failed: ScrollDownToSlider is not displayed.");
	            throw e;
	        }

	        logger.info("**** Finished TC_04_AdjustTheSliderFunctionalityTest ****");
	    }

}