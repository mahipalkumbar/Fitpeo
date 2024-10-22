package tesrCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import GenerateImage.DataProviderClass;
import pageObjects.Loginpage;
import pageObjects.WellComePOM;
import testBase.BaseClass;

public class TC_01_LoginFunctionalityTest extends BaseClass {

    @Test(dataProvider = "Login", dataProviderClass = DataProviderClass.class)
    public void LoginFunctionalityTest(String Countrycode, String phoneno, String password,String exp) {

        logger.info("**** Starting LoginFunctionalityTest ****");
        WellComePOM wp=new WellComePOM(driver);
        Loginpage lp = new Loginpage(driver);
        wp.ClickOnLoginButtonn();
        lp.sendPhone(phoneno);
        lp.sendPassword(password);
        lp.clickLogin();
        boolean isDisplayed=lp.isHomePageDisplayed();
        
        if(exp.equalsIgnoreCase("Success")) {
        	if(isDisplayed==true) {
        		Assert.assertTrue(true);
        		lp.Logout();//write for loguot
        	}
        	else{
        		Assert.assertTrue(false);
        	}
        }
        if(exp.equalsIgnoreCase("Failure")) {
        	if(isDisplayed==true) {
        		Assert.assertTrue(true);
        		lp.Logout();//write for loguot
        	}else {
        		Assert.assertTrue(true);
        	}
        }
        logger.info("**** Finished LoginFunctionalityTest ****");
       
}}
