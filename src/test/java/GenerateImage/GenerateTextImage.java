package GenerateImage;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class GenerateTextImage {

	public static void main(String[] args) throws IOException {
		
		WebDriver driver=new ChromeDriver();
		driver.get("https://dev.nyx.today/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("(//button[normalize-space()='Login'])[2]")).click();
		driver.findElement(By.xpath("//input[@id='phone']")).sendKeys("8123342894");
		driver.findElement(By.cssSelector("input[placeholder='xxxxxxxx']")).sendKeys("QW@$zx123");
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		driver.findElement(By.xpath("//a[contains(text(),'ImageCraft AI')]")).click();
		driver.findElement(By.xpath("//div[contains(@class,'justify-center items-center gap-3 font-semibold rounded-")).click();
		driver.findElement(By.xpath("//button[normalize-space()='+ Add New']")).click();
		String filepath=System.getProperty("user.dir")+"\\TestDataGenerateImageAI\\GenerateImage AI.xlsx";
		int rows=ExcelUtils.getRowcount(filepath, "Sheet1");
		for(int i=1;i<=rows;i++) {
			String brandname=ExcelUtils.getcelldata(filepath, "Sheet1", i, 0);
			String category=ExcelUtils.getcelldata(filepath, "Sheet1", i, 1);
			String aboutbrand=ExcelUtils.getcelldata(filepath, "Sheet1", i, 2);
			String targetname=ExcelUtils.getcelldata(filepath, "Sheet1", i, 3);
			String agegroup=ExcelUtils.getcelldata(filepath, "Sheet1", i, 4);
			String region=ExcelUtils.getcelldata(filepath, "Sheet1", i, 5);
			String campaigname=ExcelUtils.getcelldata(filepath, "Sheet1", i, 6);
			String objective=ExcelUtils.getcelldata(filepath, "Sheet1", i, 7);
			String productname=ExcelUtils.getcelldata(filepath, "Sheet1", i, 8);
			String productdescription=ExcelUtils.getcelldata(filepath, "Sheet1", i, 9);
			String imageprompt=ExcelUtils.getcelldata(filepath, "Sheet1", i, 10);
			
			driver.findElement(By.cssSelector("input[placeholder='Add Brand']")).sendKeys(brandname);
			Select s=new Select(driver.findElement(By.xpath("//div[@class=' css-19bb58m']")));
			s.selectByVisibleText(category);
			driver.findElement(By.cssSelector("textarea[placeholder='About your Brand']")).sendKeys(aboutbrand);
			driver.findElement(By.cssSelector(".w-4.h-4.fill-current.text-nyx-yellow")).click();
			driver.findElement(By.cssSelector("input[placeholder='Suggest Target Group Name']")).sendKeys(targetname);
			
			//String brandname=ExcelUtils.getcelldata(filepath, "Sheet1", i, 0);
			//String brandname=ExcelUtils.getcelldata(filepath, "Sheet1", i, 0);
			
			
		}
		
		

	}

}
