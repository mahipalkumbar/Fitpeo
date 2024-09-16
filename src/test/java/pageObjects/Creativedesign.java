package pageObjects;

import java.time.Duration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Creativedesign extends Basepage{
	public Creativedesign(WebDriver driver) {
		super(driver);
	}
	WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));

@FindBy(xpath="//div[@class='w-full flex gap-3']//button") 
List<WebElement> contextofimage;
@FindBy(xpath="//div[@class='w-full flex gap-3 flex-wrap']//button")   
List<WebElement> focusing;
@FindBy(xpath="//div[@class='w-full flex flex-col gap-5']//div[@class='w-full flex flex-wrap gap-3']")   
List<WebElement> imagestyle;
@FindBy(xpath="//button[normalize-space()='Submit']")   
WebElement submit;

@FindBy(xpath="/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div/div/button")
WebElement generatef;

@FindBy(xpath="//textarea[@class='w-full h-[100px] bg-transparent border placeholder:italic border-x-nyx-gray-1 rounded-lg p-5 text-white outline-none']")  
WebElement generate;

JavascriptExecutor js = (JavascriptExecutor) driver;

public void contextofimage(String img) {
for(WebElement opt:contextofimage) {
	if(opt.getText().equals(img)) {
		opt.click();
}}}

public void focusing(String focus) {
	 wait.until(ExpectedConditions.visibilityOfAllElements(focusing));
for(WebElement opt:focusing) {
	if(opt.getText().equals(focus)) {
		opt.click();
}}}

public void imagestyle(String style) {
	String xpathexp="//div[text()='"+style+"']/..";
	WebElement ele=driver.findElement(By.xpath(xpathexp));
	wait.until(ExpectedConditions.visibilityOf(ele));
	ele.click();
}
		
public void submit() {
	submit.click();
	wait.until(ExpectedConditions.invisibilityOf(submit));
}

public void generate(String prompt) {
	generate.sendKeys(prompt);
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	 
		//generate.sendKeys(Keys.TAB);*/
}

public void generatef() {
	Actions act=new Actions(driver);
	act.moveToElement(generate).perform();
	wait.until(ExpectedConditions.visibilityOf(generate));
	WebElement generatebutton=wait.until(ExpectedConditions.elementToBeClickable(generatef));
	((JavascriptExecutor) driver).executeScript("arguments[0].click();", generatebutton);
	//generatebutton.click();
	
	

}}
