package pageObjects;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Addbrandinfo extends Basepage{
		public Addbrandinfo(WebDriver driver) {
			super(driver);
		}
		
		//POM Choosing particular barnd after adding new brand in Branding details page
		@FindBy(xpath="//div[@class='flex flex-col']//div[@class='w-full flex flex-wrap gap-2']") 
		List<WebElement> selct;
		
		//POM Next button in Branding details page(Before Going to Next button You must choose one brand mandatory)
		@FindBy(xpath="//button[contains(@class,'nyx-button border border-amber-400 text-white hover:text-black hover:bg-amber-300 hover:shadow-glow text-base px-5 text-center rounded-full w-[109px] hover:shadow-none font-semibold py-1.5')][normalize-space()='Next']")
		WebElement nextinbrandselection;
		
		//POM +Add New Button while choosing brand
		@FindBy(xpath="//button[normalize-space()='+ Add New']")
		WebElement AddNewbrandbutton;
		
		
		//POM Brand Name Text field while entering brand name
		@FindBy(xpath="//input[@placeholder='Add Brand']") 
		WebElement addBrand;
		
		//POM Category Drop-down while while selecting category option
		@FindBy(xpath="//div[@class=' css-19bb58m']") 
		WebElement selectCategory;
		
		
		// POM Add your Brand text field while entering your brand details
		@FindBy(xpath="//textarea[@placeholder='About your Brand']") 
		WebElement aboutYourBrand;
		
		//POM Add Target Group button while adding target group in Add brand info page
		@FindBy(xpath="//*[name()='path' and contains(@d,'M15.14 8.5')]") 
		WebElement addTargetGroup;
		
		//POM Next Button in Add Brand Info Page
		@FindBy(xpath="//button[normalize-space()='Next']") 
		WebElement nextABI;
		
		
		//Target Group Page Starts
		
		
		//POM Target Name text field in New Target group page
		@FindBy(xpath="//input[@placeholder='Suggest Target Group Name']") 
		WebElement TargetNameText;
		
		//POM Gender button while selecting gender in target group page
		@FindBy(xpath="//div[@class='w-full flex gap-3']/button")
		List<WebElement> Gender;
		
		//POM Age Group Drop-down in target group page

		
		@FindBy(xpath="//div[@class='ReactModalPortal']//div[3]//div[2]//div[1]//div[2]//div[1]")  
		WebElement selectAgetg;
		
		//POM Region Group Drop-down in target group page
		@FindBy(xpath="//div[@class='ReactModalPortal']//div[4]//div[2]//div[1]//div[2]//div[1]") 
		WebElement selectRegiontg;

		//POM Next button in target group page
		@FindBy(xpath="//button[@class='nyx-button border border-amber-400 hover:text-black hover:bg-amber-300 hover:shadow-glow px-5 py-2 text-center text-md text-[#FFFFFF] font-bold rounded-full w-40 hover:shadow-none'][normalize-space()='Next']") 
		WebElement nexttg;

		
		
		//Edit Brand Info Starts
		
		
		
		//POM Skip button in Edit Brand Info page
		@FindBy(xpath="//div[@class='text-[#FFFFFF] cursor-pointer underline']")
		WebElement skip;
		
		//POM Brand Logo button while uploading the image/logo from your laptop in edit brand info page
		@FindBy(xpath="//label[@class='w-10 h-10 cursor-pointer inline-flex items-center justify-center bg-transparent border border-nyx-yellow rounded-full p-2 font-normal']//*[name()='path' and contains(@d,'M15.14 8.5')][1]") 
		WebElement addLogosBrand;
		
		//POM Add New Product button in Edit Brand Info page
		@FindBy(xpath="//button[normalize-space()='Add New Product']") 
		WebElement addNewProductButton;
		
		//POM Next Button in Edit Brand Info page
		@FindBy(xpath="//button[normalize-space()='Next']") 
		WebElement nextbuttoninEditBrandInfo;
		

		
		//Add Product page starts
		
		//POM Product Name text field while adding product name in Add product page
		@FindBy(xpath="//input[@placeholder='Add Product Name']")
		WebElement ProductNameText;

		//POM Product description text field while entering description in Add product page
		@FindBy(xpath="//input[@placeholder='Product Description']") 
		WebElement productDescription;
		
		//POM Product logo button while uploading the image/logo from your laptop in Add Product page
		@FindBy(xpath="//div[@class='mt-3']//label[@class='w-10 h-10 cursor-pointer inline-flex items-center justify-center bg-transparent border border-nyx-yellow rounded-full p-2 font-normal']") 
		WebElement ProdctLogo;
		
		//POM Next button in Add product page
		@FindBy(xpath="//button[@class='nyx-button border border-amber-400 hover:text-black hover:bg-amber-300 hover:shadow-glow px-5 py-2 text-center text-md text-[#FFFFFF] font-bold rounded-full w-40 hover:shadow-none'][normalize-space()='Next']")  
		WebElement ProdctpageNextButton;
		
		  JavascriptExecutor js = (JavascriptExecutor) driver;
		  
		  WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
		
			
		 	public void AddNewbrandbutton() throws InterruptedException  {
		 	try {
		         WebElement button = wait.until(ExpectedConditions.elementToBeClickable(AddNewbrandbutton));
		         boolean elementFound = false;
		            while (!elementFound) {
		                try {
		                    if (button.isDisplayed()) {
		                        elementFound = true;
		                    }
		                } catch (Exception e) {
		                    js.executeScript("window.scrollBy(0, 500);"); // Adjust scroll amount as needed
		                    Thread.sleep(1000); // 1-second delay
		                }
		            }
		            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
		       //  button.click();
		    } catch (NoSuchElementException e) {
		        System.out.println("AddNewbutton not displayed in Branding details page because of NoSuchElementException");
		        throw new RuntimeException("AddNewbutton not displayed in Branding details page because of NoSuchElementException", e);
		    } catch (TimeoutException e) {
		        System.out.println("AddNewbutton not displayed in Branding details page because of TimeoutException");
		        throw new RuntimeException("AddNewbutton not displayed in Branding details page because of TimeoutException", e);
		    } catch (Exception e) {
		        System.out.println("An unexpected error occurred while trying to load the Branding details page");
		        throw new RuntimeException("An unexpected error occurred while loading Branding details page", e);
		    }
			}

		public void sendaddbrand(String brd) throws InterruptedException {
		try {
			 WebElement element1= wait.until(ExpectedConditions.visibilityOf(addBrand));
				element1.sendKeys(brd);
				//element1.sendKeys(Keys.TAB);
		} catch (TimeoutException e) {
        System.out.println("Brand Name text field not displayed in Add Brand Info because of TimeoutException");
        throw new RuntimeException("addBrand text field not displayed because of TimeoutException", e);
		} catch (NoSuchElementException e) {
        System.out.println("Brand Name text field not displayed in Add Brand Info because of NoSuchElementException");
        throw new RuntimeException("aBrand Name text field not displayed in Add Brand Info because of NoSuchElementException", e);
		} catch (Exception e) {
        System.out.println("An unexpected error occurred while trying to load the addBrand info page field");
        throw new RuntimeException("An unexpected error occurred while loading Add brand info page", e);
		}}
		 

		public void selectCategory(String category) {
			selectCategory.click();
			String xpathExpression = "//*[text()='" + category + "']";
			
			WebElement option = driver.findElement(By.xpath(xpathExpression));
			WebElement cat = wait.until(ExpectedConditions.elementToBeClickable(option));
			Actions act=new Actions(driver);
			act.moveToElement(cat).click().perform();
		}

		
		public void aboutYourBrand(String abd) {
			aboutYourBrand.sendKeys(abd);
			//aboutYourBrand.sendKeys(Keys.TAB);
		}

		
		public void addTargetGroup() {
			addTargetGroup.click();
		}

		
		public void nextABIbutton() {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ReactModal__Overlay")));
			WebElement elementnextabi = wait.until(ExpectedConditions.elementToBeClickable(nextABI));
			elementnextabi.click();
			
		}
		
		
		public void TargetNameText(String tgname) throws InterruptedException {
		try {
			 WebElement element2= wait.until(ExpectedConditions.visibilityOf(TargetNameText));
				element2.sendKeys(tgname);
				//
		} catch (TimeoutException e) {
        System.out.println("Target Name text field not displayed in New Target Group page because of TimeoutException");
        throw new RuntimeException("Target Name text field not displayed in New Target Group page because of TimeoutException", e);
		} catch (NoSuchElementException e) {
        System.out.println("Target Name text field not displayed in New Target Group page because of NoSuchElementException");
        throw new RuntimeException("Target Name text field not displayed in New Target Group page because of NoSuchElementException", e);
		} catch (Exception e) {
        System.out.println("An unexpected error occurred while loading New Target Group page");
        throw new RuntimeException("An unexpected error occurred while loading New Target Group page", e);
		}}

		public void Gender(String gende) {
			for(WebElement w:Gender) {
				if(w.getText().equals(gende)) {
					w.click();
				}}}

		public void age(String age) throws InterruptedException {
			selectAgetg.click();
			String xpathExpression = "//*[text()='" + age + "']";
			WebElement option = driver.findElement(By.xpath(xpathExpression));
			Actions act=new Actions(driver);
			act.moveToElement(option).click().perform();
		}
		
		public void selectRegiontg(String region) throws InterruptedException {
			selectRegiontg.click();
			String xpathExpression = "//*[text()='" + region + "']";
			WebElement option = driver.findElement(By.xpath(xpathExpression));
			Actions act=new Actions(driver);
			act.moveToElement(option).click().perform();
		}

		public void nexttg() {
			nexttg.click();
		}
		
		
		public void skip() throws InterruptedException {
			try {
				 WebElement elementskip= wait.until(ExpectedConditions.visibilityOf(skip));
					elementskip.click();
			} catch (TimeoutException e) {
	        System.out.println("skip button not displayed in Edit Brand Info page because of TimeoutException");
	        throw new RuntimeException("skip button not displayed in Edit Brand Info page because of TimeoutException", e);
			} catch (NoSuchElementException e) {
	        System.out.println("skip button not displayed in Edit Brand Info page because of NoSuchElementException");
	        throw new RuntimeException("skip button not displayed in Edit Brand Info page because of NoSuchElementException", e);
			} catch (Exception e) {
	        System.out.println("An unexpected error occurred while trying to skip button in Edit Brand Info page");
	        throw new RuntimeException("An unexpected error occurred in Edit Brand Info page while loading", e);
			}}
			
			
			public void addLogosBrand(String brandlogo) throws AWTException {
				//WebElement e=driver.findElement(By.xpath("input[@type='file']"));
				////input[@type='file']

		        JavascriptExecutor js = (JavascriptExecutor) driver;
		        js.executeScript("arguments[0].style.display='block';", addLogosBrand);
		        addLogosBrand.sendKeys(brandlogo); 
		        
				
				/*
				WebElement elementnextab1= wait.until(ExpectedConditions.visibilityOf(addLogosBrand));
				elementnextab1.click();
				Robot rb=new Robot();
				rb.delay(2000);
				StringSelection s=new StringSelection(brandlogo);
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
				
				rb.keyPress(KeyEvent.VK_CONTROL); //press on control key
				rb.keyPress(KeyEvent.VK_V);  //press the control key
				
				rb.keyRelease(KeyEvent.VK_CONTROL);
				rb.keyRelease(KeyEvent.VK_V);
				
				rb.keyPress(KeyEvent.VK_ENTER);
				rb.keyRelease(KeyEvent.VK_ENTER);
				
				//elementnextab1.click();
				//elementnextab1.sendKeys(brandlogo);  */
				
			}
			
			

			public void addNewProductButton() {
				addNewProductButton.click();
			}
			
			
			public void nextbuttoninEditBrandInfo() {
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ReactModal__Overlay")));
				WebElement elementnextab= wait.until(ExpectedConditions.elementToBeClickable(nextbuttoninEditBrandInfo));
				elementnextab.click();
			}

			
			
			 public void ProductNameText(String productname) throws InterruptedException {
			try {
				 WebElement elementaddProductName= wait.until(ExpectedConditions.visibilityOf(ProductNameText));
					elementaddProductName.sendKeys(productname);
					//elementaddProductName.sendKeys(Keys.TAB);
			} catch (TimeoutException e) {
	        System.out.println("Product Name Text fieald not displayed in Add Product page because of TimeoutException");
	        throw new RuntimeException("Product Name Text fieald not displayed in Add Product page because of TimeoutException", e);
			} catch (NoSuchElementException e) {
	        System.out.println("Product Name Text fieald not displayed in Add Product page because of NoSuchElementException");
	        throw new RuntimeException("Product Name Text fieald not displayed in Add Product page because of NoSuchElementException", e);
			} catch (Exception e) {
	        System.out.println("An unexpected error occurred while loading Add Product page");
	        throw new RuntimeException("An unexpected error occurred in Add Product page while loading", e);
			}}
			 

			public void productDescription(String productdescription) {
				productDescription.sendKeys(productdescription);
				//productDescription.sendKeys(Keys.TAB);
			}
			
			public void ProdctLogo(String pl) {
				ProdctLogo.sendKeys(pl);
				
			}

			public void ProdctpageNextButton() {
				ProdctpageNextButton.click();
			}
			
			

			public void brandnameselected(String brd) throws InterruptedException {
				String xpthexp="//div[text()='"+brd+"']";
				 WebElement el2=driver.findElement(By.xpath(xpthexp));
				 boolean elementFound1 = false;
		            while (!elementFound1) {
		                try {
		                    if (el2.isDisplayed()) {
		                        elementFound1 = true;
		                    }
		                } catch (Exception e) {
		                    js.executeScript("window.scrollBy(0, 1000);"); // Adjust scroll amount as needed
		                    Thread.sleep(1000); // 1-second delay
		                }
		            }
			      WebElement elementv = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpthexp)));
			      elementv.click();
				
			}	

			public void clicknextbuttoninbrandselection() {
		        js.executeScript("arguments[0].scrollIntoView(true);", nextinbrandselection);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ReactModal__Overlay")));
				WebElement elementnextab1= wait.until(ExpectedConditions.elementToBeClickable(nextinbrandselection));
				
				elementnextab1.click();
			}
	}

