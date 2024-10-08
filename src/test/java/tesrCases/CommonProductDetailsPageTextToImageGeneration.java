package tesrCases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.Addbrandinfo;
import testBase.BaseClass;

public class CommonProductDetailsPageTextToImageGeneration extends BaseClass{
	public void ProductDetailsPageInTextToImage(String brandname, String category, String abouturbrand, String tgname,
            String gender, String agegruop, String region, String brandlogo, String productname,
            String productdescription, String productlogo, String campaignname, String objective,
            String socialmedia, String size, String imagecontext, String focuselements,
            String imagestyle, String imageprompt) throws InterruptedException, AWTException {
		Addbrandinfo a = new Addbrandinfo(driver);
		a.addNewProductButton();
     /*   driver.findElement(By.xpath("//div[@class='w-full text-left text-[#FFFFFF] font-semibold gap-2 text-sm'][2]//*[name()='path' and contains(@d,'M15.14 8.5')]")).click();
        Thread.sleep(8000);
        Robot rb=new Robot();
        StringSelection str=new StringSelection("D:\\\\Mahipal\\\\NYX.today\\\\BrandLogos\\\\EcoBlend.png");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_K);
        
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_K);

        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);*/
        a.ProductNameText(productname);
        a.productDescription(productdescription);
        a.ProdctpageNextButton();
	}	

}
