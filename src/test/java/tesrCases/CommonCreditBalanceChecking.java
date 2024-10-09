package tesrCases;

import org.testng.Assert;

import pageObjects.MenuPage;
import testBase.BaseClass;

public class CommonCreditBalanceChecking extends BaseClass{
	public void CreditBalanceCheckingMethod() {
		MenuPage menu = new MenuPage(driver);
		 try {
		        boolean isCreditSufficient = menu.SlideBar();
		       
		        if (!isCreditSufficient) {
		        	logger.error("Insufficient credit balance. Please upgrade plan");
		            Assert.fail("Insufficient credit balance. Please upgrade plan");
		            System.out.println("Insufficient credit balance. Please upgrade plan");
		            return; // Exit the test if balance is insufficient
		        }
		    } catch (Exception e) {
		    	
		        logger.error("Error checking credit balance.", e);
		        Assert.fail("Error checking credit balance. " + e.getMessage());
		        return; // Exit the test if there is an error checking the balance
		    }

	}
}
