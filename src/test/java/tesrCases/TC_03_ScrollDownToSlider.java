package tesrCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePageFit;
import testBase.BaseClass;

public class TC_03_ScrollDownToSlider extends BaseClass{
	

	 @Test()
	    public void TC_03_ScrollDownToSliderUntilVisibleTest() throws IOException {
		 	HomePageFit hpf=new HomePageFit(driver);
	        logger.info("**** Starting TC_03_ScrollDownToSliderUntilVisibleTest ****");
	        boolean isDisplayed=hpf.ScrollDownToSliderUntilDisplay();
	        try {
	            Assert.assertTrue(isDisplayed, "ScrollDownToSlider is not displayed");
	            System.out.println("Assertion Passed: ScrollDownToSlider is displayed.");
	        } catch (AssertionError e) {
	        	captureScreen("TC_03_ScrollDownToSliderUntilVisibleTestFailed");
	            System.out.println("Assertion Failed: ScrollDownToSlider is not displayed.");
	            throw e;
	        }

	        logger.info("**** Finished TC_03_ScrollDownToSliderUntilVisibleTest ****");
	    }

}
