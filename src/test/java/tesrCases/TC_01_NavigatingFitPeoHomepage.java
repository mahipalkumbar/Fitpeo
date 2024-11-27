package tesrCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePageFit;
import testBase.BaseClass;

public class TC_01_NavigatingFitPeoHomepage extends BaseClass{
	

	 @Test(priority=1)
	    public void TC_01_NavigatingFitPeoHomepageTest() throws IOException {
		 HomePageFit hpf=new HomePageFit(driver);
	        logger.info("**** Starting TC_01_NavigatingFitPeoHomepageTest ****");
	        
	        boolean isDisplayed=hpf.isDisplayedHomePageFitpeo();
	        try {
	            Assert.assertTrue(isDisplayed, "Homepage is not displayed");
	            System.out.println("Assertion Passed: HomePage is displayed.");
	        } catch (AssertionError e) {
	        	captureScreen("TC_01_NavigatingFitPeoHomepageTestFailed");
	            System.out.println("Assertion Failed: HomePage is not displayed.");
	            throw e;
	        }

	        logger.info("**** Finished TC_01_NavigatingFitPeoHomepageTest ****");
	    }
	 
	 @Test(priority=2, dependsOnMethods= {"TC_01_NavigatingFitPeoHomepageTest"})
	    public void TC_02_NavigateToRevenueCalculatorPageTest() throws IOException {
		 	HomePageFit hpf=new HomePageFit(driver);
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
	 @Test(priority=3, dependsOnMethods= {"TC_02_NavigateToRevenueCalculatorPageTest"})
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
	 
	 @Test(priority=4, dependsOnMethods= {"TC_03_ScrollDownToSliderUntilVisibleTest"})
	    public void TC_04_AdjustTheSliderFunctionalityTest() throws IOException {
		 	HomePageFit hpf=new HomePageFit(driver);
	        logger.info("**** Starting TC_04_AdjustTheSliderFunctionalityTest ****");
	        boolean isDisplayed=hpf.adjustSliderToDesiredValue(820);
	        try {
	            Assert.assertTrue(isDisplayed, "ScrollDownToSlider is not displayed");
	            System.out.println("Assertion Passed: ScrollDownToSlider is displayed.");
	        } catch (AssertionError e) {
	        	captureScreen("TC_04_AdjustTheSliderFunctionalityTestFailed");
	            System.out.println("Assertion Failed: ScrollDownToSlider is not displayed.");
	            throw e;
	        }

	        logger.info("**** Finished TC_04_AdjustTheSliderFunctionalityTest ****");
	    }
	 
	 @Test(priority=5, dependsOnMethods= {"TC_04_AdjustTheSliderFunctionalityTest"})
	    public void TC_05_UpdateTheTextFieldFunctionalityTest() throws Exception {
		 	HomePageFit hpf=new HomePageFit(driver);
	        logger.info("**** Starting TC_05_UpdateTheTextFieldFunctionalityTest ****");
	        boolean isDisplayed=hpf.enteringtextfield("560");
	        try {
	            Assert.assertTrue(isDisplayed, "ScrollDownToSlider is not displayed");
	            System.out.println("Assertion Passed: TextField is displayed.");
	        } catch (AssertionError e) {
	        	captureScreen("TC_05_UpdateTheTextFieldFunctionalityTestFailed");
	            System.out.println("Assertion Failed: TextField is not displayed.");
	            throw e;
	        }

	        logger.info("**** Finished TC_05_UpdateTheTextFieldFunctionalityTest ****");
	    }
	 
	 @Test(priority=6, dependsOnMethods= {"TC_05_UpdateTheTextFieldFunctionalityTest"})
	 public void TC_06_SelectCPTCodesFunctionalityTest() throws Exception {
	     HomePageFit hpf = new HomePageFit(driver); 
	     logger.info("**** Starting TC_06_SelectCPTCodesFunctionalityTest ****");
	     boolean isDisplayed1 = hpf.enteringtextfield("820");
	     System.out.println("Text field entered successfully: " + isDisplayed1);
	     String[] cptCodes = {"CPT-99091", "CPT-99453", "CPT-99454", "CPT-99474"};
	     boolean isDisplayed = hpf.SelectCPTCodes(cptCodes);

	     try {
	         Assert.assertTrue(isDisplayed, "SelectCPTCodes is not selected or not displayed.");
	         System.out.println("Assertion Passed: SelectCPTCodes are selected and displayed.");
	     } catch (AssertionError e) {
	         captureScreen("TC_06_SelectCPTCodesFunctionalityTestFailed");
	         System.out.println("Assertion Failed: SelectCPTCodes are not selected or not displayed.");
	         throw e; 		
	         }

	     logger.info("**** Finished TC_06_SelectCPTCodesFunctionalityTest ****");
	 }
	 	 

	 @Test(priority=7, dependsOnMethods= {"TC_06_SelectCPTCodesFunctionalityTest"})
	    public void TC_07_ValidateTotalRecurringReimbursementTest() throws Exception {
		 	HomePageFit hpf=new HomePageFit(driver);
	        logger.info("**** Starting TC_07_ValidateTotalRecurringReimbursementTest ****");
	        boolean isDisplayed=hpf.ValidateTotalRecurring();
	        try {
	            Assert.assertTrue(isDisplayed, "ValidateTotalRecurringReimbursement is not displayed");
	            System.out.println("Assertion Passed: ValidateTotalRecurringReimbursement is displayed.");
	        } catch (AssertionError e) {
	        	captureScreen("TC_07_ValidateTotalRecurringReimbursementTestFailed");
	            System.out.println("Assertion Failed: ValidateTotalRecurringReimbursement is not displayed.");
	            throw e;
	        }

	        logger.info("**** Finished TC_07_ValidateTotalRecurringReimbursementTest ****");
	    }


}
