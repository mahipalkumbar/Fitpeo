package pageObjects;



import java.awt.AWTException;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Addbrandinfo extends Basepage{
		public Addbrandinfo(ThreadLocal<WebDriver> driver) {
			super(driver);
		}
		
		//POM Choosing particular barnd after adding new brand in Branding details page
		@FindBy(xpath="//div[@class='flex flex-col']//div[@class='w-full flex flex-wrap gap-2']") 
		List<WebElement> selct;
		
		
		//div[@class='w-full flex flex-wrap gap-2']//button[@title]
		@FindBy(xpath="//button[contains(@class,'group relative')]//div[contains(@class,'text')]") 
		List<WebElement> nyxtest2adBrandABrandAZeptoBr;

		
		@FindBy(xpath="//div[text()='Choose the Brand ']")
		WebElement brandingdetailspage;
		
		
		//POM Next button in Branding details page(Before Going to Next button You must choose one brand mandatory)
		@FindBy(xpath="//button[text()='+ Add New']/following::button[1]")
		WebElement nextinbrandselection;
		
		
		@FindBy(xpath="//div[text()='Campaign Name ']")
		WebElement campaignoverviewpage;
		
		//POM +Add New Button while choosing brand
		@FindBy(xpath="//button[normalize-space()='+ Add New']")
		WebElement AddNewbrandbutton;
		
		
		//POM Brand Name Text field while entering brand name
		@FindBy(xpath="//input[@placeholder='Add Brand']") 
		WebElement addBrand;
		
		//POM Category Drop-down while while selecting category option
		@FindBy(xpath="//p[text()='Category']/..//div[@class]") 
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
		

		@FindBy(xpath="//div[text()='Target Name ']") 
		WebElement targetgrouppage;
		
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
		
		@FindBy(xpath="//p[text()='Add Brand logos and colors']")
		WebElement editbrandinfopage;
		
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
		//button[text()='Next']
		@FindBy(xpath="//button[@class='nyx-button border border-amber-400 hover:text-black hover:bg-amber-300 hover:shadow-glow px-5 py-2 text-center text-md text-[#FFFFFF] font-bold rounded-full w-40 hover:shadow-none'][normalize-space()='Next']")  
		WebElement ProdctpageNextButton;
		

		@FindBy(xpath="//div[@class='font-medium truncate text-sm']")  
		WebElement techEnthusiast;
		
		
		@FindBy(xpath="//button[text()='+ Add New']/preceding-sibling::button[1]")  
		WebElement besideaddnewbutton;
		
		
		@FindBy(xpath="//div[text()='Choose the Brand ']")  
		WebElement choosethebrand;
		//button[text()='+ Add New']/preceding-sibling::button[1]
		
		  JavascriptExecutor js = (JavascriptExecutor) driver;
		  
		  WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(15));		  
		  
		 // List<WebElement> nyxtest2adBrandABrandAZeptoBr;
		  
		
		  
		 @FindBy(xpath="//div[text()='Brand Details']")  
		 WebElement brandingdetailsfullview;
		  
		  public void clickonbrandingdetailsfullview() {
			  try {
				  wait.until(ExpectedConditions.visibilityOfAllElements(brandingdetailsfullview));
				  brandingdetailsfullview.click();
			    } catch (NoSuchElementException e) {
			        throw new NoSuchElementException("brandingdetailsfullview Button not found: " + e.getMessage());
			    } catch (Exception e) {
			        throw new RuntimeException("An unexpected error occurred while clicking the brandingdetailsfullview Button: " + e.getMessage());
			    }
		  }
		  
		  
		  private int listSize;

		  public void addNewBrandButton() {
			  WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(30));
			  wait.until(ExpectedConditions.visibilityOfAllElements(AddNewbrandbutton));
		      try {
		          // Wait for brand list visibility and handle TimeoutException
		          try {
		              wait1.until(ExpectedConditions.visibilityOfAllElements(nyxtest2adBrandABrandAZeptoBr));
		          } catch (TimeoutException e) {
		              System.out.println("List not displayed. Number of brands: 0");
		              listSize = 0;
		              //return;
		          }

		          // Get the size of the brand list
		          listSize = nyxtest2adBrandABrandAZeptoBr != null ? nyxtest2adBrandABrandAZeptoBr.size() : 0;
		          System.out.println("Number of brands displayed: " + listSize);

		          // Scroll and click the "Add New Brand" button
		          ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", AddNewbrandbutton);

		      } catch (TimeoutException e) {
		          System.out.println("Add New Brand button not found due to TimeoutException.");
		          throw new RuntimeException("Add New Brand button not found.", e);
		      } catch (NoSuchElementException e) {
		          System.out.println("Add New Brand button not found due to NoSuchElementException.");
		          throw new RuntimeException("Add New Brand button not found.", e);
		      } catch (Exception e) {
		          System.out.println("Unexpected error while interacting with the Add New Brand button.");
		          throw new RuntimeException("Unexpected error.", e);
		      }
		  }

		  public void sendAddBrand(String brand) {
		      try {
		          // Send brand name to input field
		          addBrand.sendKeys(brand);
		      } catch (NoSuchElementException e) {
		          System.out.println("Brand Name field not displayed.");
		          throw new RuntimeException("Brand Name field not found.", e);
		      } catch (Exception e) {
		          System.out.println("Unexpected error while interacting with Brand Name field.");
		          throw new RuntimeException("Unexpected error.", e);
		      }
		  }

		  public void selectCategory(String category) {
		      try {
		          // Click to open category dropdown and select the desired category
		          selectCategory.click();
		          WebElement option = driver.findElement(By.xpath("//*[text()='"+category+"']"));
		          //wait.until(ExpectedConditions.visibilityOf(option));
		          // Scroll and select category using Actions
		          new Actions(driver).moveToElement(option).click().perform();
		          System.out.println("Category '" + category + "' selected successfully.");
		          
		      } catch (NoSuchElementException e) {
		          System.out.println("Category '" + category + "' not found.");
		          throw new RuntimeException("Category '" + category + "' not found.", e);
		      } catch (Exception e) {
		          System.out.println("Unexpected error while selecting the category.");
		          throw new RuntimeException("Unexpected error.", e);
		      }
		  }


		
		  public void aboutYourBrand(String abd) {
			    try {
			        // Check if the aboutYourBrand element is visible and ready for interaction
			       // WebElement element = wait.until(ExpectedConditions.visibilityOf(aboutYourBrand));
			        aboutYourBrand.clear(); // Optional: Clear any pre-filled text if needed
			        aboutYourBrand.sendKeys(abd);
			    } catch (TimeoutException e) {
			        System.out.println("The 'About Your Brand' text field is not visible due to a TimeoutException");
			        throw new RuntimeException("Failed to locate 'About Your Brand' text field", e);
			    } catch (NoSuchElementException e) {
			        System.out.println("The 'About Your Brand' text field was not found in the DOM");
			        throw new RuntimeException("The 'About Your Brand' text field is missing", e);
			    } catch (Exception e) {
			        System.out.println("An unexpected error occurred while entering text in 'About Your Brand'");
			        throw new RuntimeException("An unexpected error occurred", e);
			    }
			}


		
		  public void addTargetGroup() {
			    try {
			        // Directly click on the "Add Target Group" button (implicit wait will handle the waiting)
			        addTargetGroup.click();

			        // Check if the target group page is displayed (implicit wait handles element availability)
			       // return targetgrouppage.isDisplayed();
			    } catch (NoSuchElementException e) {
			        System.out.println("Target Group page or Add Target Group button not found in the DOM.");
			        throw new RuntimeException("Element missing in the DOM", e);
			    } catch (Exception e) {
			        System.out.println("An unexpected error occurred while loading the Target Group page.");
			        throw new RuntimeException("Unexpected error", e);
			    }
			}


		
		  public boolean nextABIbutton() {
			    try {
			        // Click the "Next ABI" button using JavaScript
			    	wait.until(ExpectedConditions.visibilityOf(techEnthusiast));
			        js.executeScript("arguments[0].click();", nextABI);

			        // Since implicit wait is applied globally, no need for explicit wait
			        return editbrandinfopage.isDisplayed();
			    } catch (NoSuchElementException e) {
			        System.out.println("Next ABI button or edit brand info page not found.");
			        throw new RuntimeException("Element not found in DOM", e);
			    } catch (Exception e) {
			        System.out.println("An unexpected error occurred while trying to click Next ABI button.");
			        throw new RuntimeException("Unexpected error occurred", e);
			    }
			}

		
		
		  public void TargetNameText(String tgname) {
			    try {
			        // Using implicit wait, so no need for explicit visibility check
			        TargetNameText.sendKeys(tgname);
			    } catch (NoSuchElementException e) {
			        System.out.println("Target Name text field not found in New Target Group page.");
			        throw new RuntimeException("Target Name text field not found.", e);
			    } catch (Exception e) {
			        System.out.println("An unexpected error occurred while loading the New Target Group page.");
			        throw new RuntimeException("Unexpected error occurred.", e);
			    }
			}


		  public void Gender(String gende) {
			    try {
			        /*boolean found = false; // Flag to check if the gender is found
			        for (WebElement w : Gender) {
			            if (w.getText().equals(gende)) {
			                w.click();
			                found = true; // Set flag to true when the element is clicked
			                break; // Exit the loop after clicking the desired gender
			            }
			        }
			        if (!found) {
			            throw new NoSuchElementException("Gender option '" + gende + "' not found.");
			        }*/
			    	String xpathExpression = "//button[text()='" + gende + "']";
			    	WebElement option = driver.findElement(By.xpath(xpathExpression));
			    	option.click();
			    	System.out.println("In New target group page: "+option.getText()+"is selected");
			    	
			    } catch (NoSuchElementException e) {
			        System.out.println(e.getMessage());
			        throw new RuntimeException("Failed to select gender: " + gende, e);
			    } catch (Exception e) {
			        System.out.println("An unexpected error occurred while selecting gender.");
			        throw new RuntimeException("An unexpected error occurred while selecting gender: " + gende, e);
			    }
			}

		  public void age(String age) throws InterruptedException {
			    try {
			        selectAgetg.click(); // Click the age dropdown

			        // Construct the XPath expression for the desired age option
			        String xpathExpression = "//*[text()='" + age + "']";
			        WebElement option = driver.findElement(By.xpath(xpathExpression));

			        // Use Actions to move to the element and click
			        new Actions(driver)
			            .moveToElement(option)
			            .click()
			            .perform();
			    } catch (NoSuchElementException e) {
			        System.out.println("Age option '" + age + "' not found in the dropdown.");
			        throw new RuntimeException("Age option not found: " + age, e);
			    } catch (Exception e) {
			        System.out.println("An unexpected error occurred while selecting the age.");
			        throw new RuntimeException("An unexpected error occurred while selecting the age: " + age, e);
			    }
			}
		
		  public void selectRegiontg(String region) throws InterruptedException {
			    try {
			    	//wait.until(ExpectedConditions.elementToBeClickable(selectRegiontg)).click();
			        selectRegiontg.click(); // Click the region dropdown

			        // Construct the XPath expression for the desired region option
			        String xpathExpression = "//*[text()='" + region + "']";
			        WebElement option = driver.findElement(By.xpath(xpathExpression));

			        // Use Actions to move to the element and click
			        new Actions(driver)
			            .moveToElement(option)
			            .click()
			            .perform();
			    } catch (NoSuchElementException e) {
			        System.out.println("Region option '" + region + "' not found in the dropdown.");
			        throw new RuntimeException("Region option not found: " + region, e);
			    } catch (Exception e) {
			        System.out.println("An unexpected error occurred while selecting the region.");
			        throw new RuntimeException("An unexpected error occurred while selecting the region: " + region, e);
			    }
			}


		  public void nexttg() {
			    try {
			        // Use JavaScript to click the nexttg element
			       // js.executeScript("arguments[0].click();", nexttg);
			    	nexttg.click();			    	
			    } catch (StaleElementReferenceException e) {
			        System.out.println("The element for 'nexttg' is no longer attached to the DOM.");
			        throw new RuntimeException("StaleElementReferenceException: Unable to click on 'nexttg'.", e);
			    } catch (Exception e) {
			        System.out.println("An unexpected error occurred while clicking the 'nexttg' button.");
			        throw new RuntimeException("An unexpected error occurred while clicking 'nexttg'.", e);
			    }
			}

		
		
		  public void skip() {
			    try {
			        // Attempt to click the skip button
			        skip.click();
			    } catch (StaleElementReferenceException e) {
			        System.out.println("The skip button is no longer attached to the DOM.");
			        throw new RuntimeException("StaleElementReferenceException: Unable to click on 'skip' button.", e);
			    } catch (NoSuchElementException e) {
			        System.out.println("Skip button not displayed in Edit Brand Info page because of NoSuchElementException");
			        throw new RuntimeException("Skip button not found in Edit Brand Info page.", e);
			    } catch (Exception e) {
			        System.out.println("An unexpected error occurred while trying to click the skip button in Edit Brand Info page");
			        throw new RuntimeException("An unexpected error occurred while clicking the skip button in Edit Brand Info page", e);
			    }
			}
			
			
			public void addLogosBrand(String brandlogo) throws AWTException {
				//WebElement e=driver.findElement(By.xpath("input[@type='file']"));
				////input[@type='file']
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
			    try {
			        addNewProductButton.click();
			    } catch (StaleElementReferenceException e) {
			        System.out.println("The 'Add New Product' button is no longer attached to the DOM.");
			        throw new RuntimeException("StaleElementReferenceException: Unable to click on 'Add New Product' button.", e);
			    } catch (NoSuchElementException e) {
			        System.out.println("Add New Product button not displayed.");
			        throw new RuntimeException("Add New Product button not found.", e);
			    } catch (ElementClickInterceptedException e) {
			        System.out.println("Unable to click the 'Add New Product' button due to overlapping elements.");
			        throw new RuntimeException("ElementClickInterceptedException: Unable to click on 'Add New Product' button.", e);
			    } catch (Exception e) {
			        System.out.println("An unexpected error occurred while trying to click the 'Add New Product' button.");
			        throw new RuntimeException("An unexpected error occurred while clicking 'Add New Product' button.", e);
			    }
			}

			
			
			public boolean nextButtonInEditBrandInfo() {
			    try {
			        // Click the 'Next' button using JavaScript Executor
			        js.executeScript("arguments[0].click();", nextbuttoninEditBrandInfo);
			        
			        // Wait for the branding details page to become visible
			        // Assuming that the implicit wait is set in the BaseClass
			        if (brandingdetailspage.isDisplayed()) {
			            return true; // Branding details page is displayed
			        } else {
			            System.out.println("Branding details page is not displayed after clicking 'Next' button.");
			            return false; // Branding details page is not displayed
			        }
			    } catch (StaleElementReferenceException e) {
			        System.out.println("The 'Next' button is no longer attached to the DOM.");
			        throw new RuntimeException("StaleElementReferenceException: Unable to click on 'Next' button in Edit Brand Info.", e);
			    } catch (NoSuchElementException e) {
			        System.out.println("Next button in Edit Brand Info page not found.");
			        throw new RuntimeException("NoSuchElementException: 'Next' button in Edit Brand Info not found.", e);
			    } catch (ElementClickInterceptedException e) {
			        System.out.println("Unable to click the 'Next' button due to overlapping elements.");
			        throw new RuntimeException("ElementClickInterceptedException: Unable to click on 'Next' button in Edit Brand Info.", e);
			    } catch (Exception e) {
			        System.out.println("An unexpected error occurred while trying to click the 'Next' button in Edit Brand Info.");
			        throw new RuntimeException("An unexpected error occurred while clicking 'Next' button in Edit Brand Info.", e);
			    }
			}


			
			
			public void ProductNameText(String productname) throws InterruptedException {
			    try {
			        // Wait for the Product Name field to be visible
			        //WebElement elementAddProductName = wait.until(ExpectedConditions.visibilityOf(ProductNameText));
			        
			        // Interact with the field
			        ProductNameText.sendKeys(productname);

			    } catch (TimeoutException e) {
			        System.out.println("Product Name field not displayed due to TimeoutException.");
			        throw new RuntimeException("Product Name field not displayed in Add Product page due to TimeoutException.", e);

			    } catch (NoSuchElementException e) {
			        System.out.println("Product Name field not found due to NoSuchElementException.");
			        throw new RuntimeException("Product Name field not displayed in Add Product page due to NoSuchElementException.", e);

			    } catch (Exception e) {
			        System.out.println("An unexpected error occurred while interacting with the Product Name field.");
			        throw new RuntimeException("Unexpected error in Add Product page while interacting with the Product Name field.", e);
			    }
			}


			 

			public void productDescription(String roductDescription) {
			    try {
			        // Send the product description to the input field
			    	
			    	//WebElement descriptionField = wait.until(ExpectedConditions.visibilityOf(productDescription));
			    	productDescription.sendKeys(roductDescription);
			    } catch (NoSuchElementException e) {
			        System.out.println("Product Description field not displayed in Add Product page due to NoSuchElementException.");
			        throw new RuntimeException("Product Description field not displayed in Add Product page.", e);
			    } catch (ElementNotInteractableException e) {
			        System.out.println("Product Description field is not interactable in Add Product page.");
			        throw new RuntimeException("Product Description field is not interactable in Add Product page.", e);
			    } catch (Exception e) {
			        System.out.println("An unexpected error occurred while entering the product description in Add Product page.");
			        throw new RuntimeException("An unexpected error occurred in Add Product page while entering the product description.", e);
			    }
			}
			
			public void setProductLogo(String logoPath) {
			    try {
			        // Assuming ProdctLogo is a WebElement for the logo input field
			        ProdctLogo.sendKeys(logoPath);
			    } catch (NoSuchElementException e) {
			        System.out.println("Product logo input field not found on the page.");
			        throw new RuntimeException("Product logo input field not found.", e);
			    } catch (Exception e) {
			        System.out.println("An unexpected error occurred while setting the product logo.");
			        throw new RuntimeException("An unexpected error occurred while setting the product logo.", e);
			    }
			}


			public void ProdctpageNextButton() {
			    try {
			        // Using JavaScript to click the button
			        js.executeScript("arguments[0].click();", ProdctpageNextButton);
			    } catch (NoSuchElementException e) {
			        System.out.println("Product page next button not found.");
			        throw new RuntimeException("Product page next button not found.", e);
			    } catch (Exception e) {
			        System.out.println("An unexpected error occurred while clicking the product page next button.");
			        throw new RuntimeException("An unexpected error occurred while clicking the product page next button.", e);
			    }
			}
			
			
			public void brandNameSelected() throws InterruptedException {
				WebElement ele=driver.findElement(By.xpath("//button[text()='+ Add New']"));
				 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
				
			    try {
			        // Wait until the brand list size increases
			        wait.until(driver -> driver.findElements(By.xpath("//button[text()='+ Add New']/preceding-sibling::button")).size() > listSize);

			        // Get the updated brand list
			        List<WebElement> updatedBrandList = driver.findElements(By.xpath("//button[text()='+ Add New']/preceding-sibling::button"));
			        
			        // Check if the list is empty
			        if (updatedBrandList.isEmpty()) {
			            System.out.println("Brand list is empty.");
			            return; // Exit early if there are no brands
			        }

			        // Reverse the brand list to get the newest brand at index 0
			        Collections.reverse(updatedBrandList);
			        
			        // Log all brand names
			       /* for (WebElement brand : updatedBrandList) {
			            System.out.println(brand.getText());
			        }*/

			        // Click the newest brand
			        WebElement newestBrand = updatedBrandList.get(0);
			        System.out.println("Newest Brand: " + newestBrand.getText());

			        // Scroll to the newest brand and click it
			        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", newestBrand);
			        newestBrand.click();
			        System.out.println("Clicked on the newest brand.");
			        
			    } catch (NoSuchElementException e) {
			        System.out.println("Error: Brand elements not found - " + e.getMessage());
			        throw new RuntimeException("Brand elements not found", e);
			    } catch (TimeoutException e) {
			        System.out.println("Error: Waiting for brand list timeout - " + e.getMessage());
			        throw new RuntimeException("Timeout waiting for brand list", e);
			    } catch (Exception e) {
			        System.out.println("Error while selecting the brand: " + e.getMessage());
			        throw new RuntimeException("Unexpected error while selecting the brand", e);
			    }
			}




			public boolean clicknextbuttoninbrandselection() {
			    try {
			        // Scroll the next button into view
			        js.executeScript("arguments[0].scrollIntoView(true);", nextinbrandselection);
			        
			        // Click the next button
			        js.executeScript("arguments[0].click();", nextinbrandselection);
			        
			        // Print message indicating the action has been performed
			        System.out.println("Clicked the next button in brand selection.");

			        // Verify that the campaign overview page is displayed
			        return campaignoverviewpage.isDisplayed();
			    } catch (NoSuchElementException e) {
			        System.out.println("Next button in brand selection not found.");
			        throw new RuntimeException("Next button in brand selection not found.", e);
			    } catch (Exception e) {
			        System.out.println("An unexpected error occurred while clicking the next button in brand selection.");
			        throw new RuntimeException("An unexpected error occurred while clicking the next button in brand selection.", e);
			    }
			}
	}

