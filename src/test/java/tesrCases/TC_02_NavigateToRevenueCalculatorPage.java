package tesrCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePageFit;
import testBase.BaseClass;

public class TC_02_NavigateToRevenueCalculatorPage extends BaseClass{
	
	HomePageFit hpf=new HomePageFit(driver);
	 @Test()
	    public void TC_02_NavigateToRevenueCalculatorPageTest() throws IOException {
		 	//HomePageFit hpf=new HomePageFit(driver);
	        logger.info("**** Starting TC_02_NavigateToRevenueCalculatorPageTest ****");
	        hpf.clickonrevenuecalculatorbutton();
	        boolean isDisplayed=hpf.isDiplayedRevenuePage();
	        try {
	            Assert.assertTrue(isDisplayed, "RevenueCalculatorPage is not displayed");
	            System.out.println("Assertion Passed: RevenueCalculatorPage is displayed.");
	        } catch (AssertionError e) {
	        	captureScreen("TC_02_NavigateToRevenueCalculatorPageTestFailed");
	            System.out.println("Assertion Failed: RevenueCalculatorPage is not displayed.");
	            throw e;
	        }

	        logger.info("**** Finished TC_02_NavigateToRevenueCalculatorPageTest ****");
	    }

}
