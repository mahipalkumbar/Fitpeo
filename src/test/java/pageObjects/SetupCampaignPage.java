package pageObjects;

import java.time.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SetupCampaignPage extends Basepage {
	public SetupCampaignPage(ThreadLocal<WebDriver> driver) {
		super(driver);
	}
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
	Actions act=new Actions(driver);
	JavascriptExecutor js=(JavascriptExecutor) driver;
	
	@FindBy(xpath="//div[text()='Consideration']") 
	WebElement waituntilsetupcampaignpageloads;
	//div[@class=' text-[14px] font-normal select-none text-white ']
	//div[contains(@class, 'h-[40px] w-[204px]') and contains(@class, 'flex flex-row') and contains(@class, 'gap-2') and contains(@class, 'items-center') and contains(@class, 'rounded') and contains(@class, 'cursor-pointer')]
	@FindBy(xpath="//div[@class=' text-[14px] font-normal select-none text-white ' or @class=' text-[14px] font-normal select-none  text-nyx-yellow '] ") 
	List<WebElement> ListSpecifycampaignobjectives;
	//div[@class='text-base font-bold select-none text-white'] 
	
	//@FindBy(xpath="//div[contains(@class, 'h-[40px] w-[204px]') and contains(@class, 'flex flex-row') and contains(@class, 'gap-2') and contains(@class, 'items-center') and contains(@class, 'rounded') and contains(@class, 'cursor-pointer')]") 
	//WebElement ListSpecifycampaignobjectivesdropdowns;
	
	
	@FindBy(xpath="//body/div/div/div/div/div/div/div/div/div/div/div/div[1]/div[1]/div[3]/div[1]//*[name()='svg']") 
	WebElement AppEngagementdropdown;
	

	@FindBy(xpath="//body/div/div/div/div/div/div/div/div/div/div/div/div[2]/div[1]/div[3]/div[1]//*[name()='svg']")
	WebElement AppInstalldropdown;
	
	
	
	@FindBy(xpath="//div[text()='Ios' or text()='Android']") 
	List<WebElement> AppEngagementdropdownOptions;
	
	

	@FindBy(xpath="//div[@class='cursor-pointer text-center flex flex-row gap-1 items-center  p-2  text-white hover:bg-[#192f73] hover:text-nyx-yellow']") 
	List<WebElement> AppInstalldropdownOptions;
	
	//div[@class='text-base font-bold select-none text-white'] 
	//div[contains(@class,'relative group w-[183px] h-[61px] rounded')]
	@FindBy(xpath="//div[@class='text-base font-bold select-none text-white']") 
	List<WebElement> SelectChannelsOptions;
	
	//div[text()='Select Channels']
	@FindBy(xpath="//div[text()='Select Channels']") 
	WebElement scrolltoselectchannels;
	
	

	@FindBy(xpath="//input[@id='Campaign name']") 
	WebElement CampaignNameTextField;
	

	@FindBy(xpath="//input[@id='Campaign URL']")
	WebElement CampaignURLTextField;
	

	@FindBy(xpath="//p[@class='underline text-[14px] leading-[17px] font-medium min-w-[80px] text-left']")
	WebElement Dashboardlink;
	

	@FindBy(xpath="//div[contains(text(),'Manage Mapping')]")  
	WebElement ManageMappingLink;
	

	@FindBy(xpath="//p[@class='text-[14px] leading-5 font-medium']") 
	WebElement UploadAudiencesButton;
	
	
	
	@FindBy(xpath="//div[@class='flex gap-4 flex-wrap']//div[@class='flex flex-col gap-2']//div[contains(@class,'text-base font-bold select-none text')]") 
	List<WebElement> NumberofChannelsAfterSelectingObjective;
	
	@FindBy(xpath="//*[name()='path' and contains(@d,'m19.5 8.25')]") 
	WebElement mapYourAdvertisingIdWithin;
	
	
	@FindBy(xpath="//*[name()='path' and contains(@d,'M15.14 8.5')]") 
	WebElement addTargetGroup;

	
	
	List<String> ListofChannelsAfterSelectingObjective = new ArrayList<>();
	
	
	public void SelectingCampaignObjectives(String expectedObjective) throws Exception {
	    boolean found = false; // Flag to check if any objective was found
	    for (WebElement element : ListSpecifycampaignobjectives) {
	        String actualTitle = element.getText().trim();
	        System.out.println("Comparing actual title: " + actualTitle + " with expected title: " + expectedObjective);

	        // Check if the actual title matches the expected objective
	        if (isObjectiveSelectable(actualTitle, expectedObjective)) {
	            element.click();
	            updateChannelList();
	            found = true; // Set the flag to true if a match is found
	            break; // Exit the loop once the objective is found and clicked
	        }
	    }
	    
	    if (!found) {
	        throw new Exception("No matching campaign objective found for: " + expectedObjective);
	    }
	}

	private boolean isObjectiveSelectable(String actualTitle, String expectedObjective) throws Exception {
	    // Check if actualTitle is "App Engagement" or "App Install"
	    if ("App Engagement".equals(actualTitle) || "App Install".equals(actualTitle)) {
	        return handleDropdownObjective(actualTitle, expectedObjective);
	    }
	    return actualTitle.equals(expectedObjective);
	}

	private boolean handleDropdownObjective(String actualTitle, String expectedObjective) throws Exception {
	    String[] parts = expectedObjective.split(",");
	    String expectedDropdownTitle = parts.length > 1 ? parts[1].trim() : "";

	    if ("App Engagement".equals(actualTitle)) {
	        AppEngagementdropdown.click();
	        return selectDropdownOption(AppEngagementdropdownOptions, expectedDropdownTitle);
	    } else if ("App Install".equals(actualTitle)) {
	        AppInstalldropdown.click();
	        return selectDropdownOption(AppEngagementdropdownOptions, expectedDropdownTitle);
	    }
	    
	    throw new Exception("Unexpected campaign objective: " + actualTitle);
	}

	private boolean selectDropdownOption(List<WebElement> dropdownOptions, String expectedOption) throws Exception {
	    for (WebElement option : dropdownOptions) {
	        String actualTitle = option.getText().trim();
	        if (actualTitle.equals(expectedOption)) {
	            option.click();
	            updateChannelList();
	            return true;
	        }
	    }
	    throw new Exception("Dropdown option not found: " + expectedOption);
	}

	private void updateChannelList() throws Exception {
	    List<WebElement> listElements = wait.until(ExpectedConditions.visibilityOfAllElements(NumberofChannelsAfterSelectingObjective));
	    if (listElements.isEmpty()) {
	        throw new Exception("No channels found after selecting the campaign objective.");
	    }
	    for (WebElement list : listElements) {
	        ListofChannelsAfterSelectingObjective.add(list.getText());
	    }
	    System.out.println(ListofChannelsAfterSelectingObjective);
	}

	
	
	public void SelectingChannels(String expectedObjective) {
	    System.out.println("Expected Objective: " + expectedObjective);
	    List<String> expectedObjectivesList = Arrays.asList(expectedObjective.split(","));
	    System.out.println("Expected Objectives List: " + expectedObjectivesList);

	    // Check if the size of expected objectives is less than or equal to the list size
	    if (expectedObjectivesList.size() > ListofChannelsAfterSelectingObjective.size()) {
	        throw new IllegalArgumentException("The number of channels to select exceeds available channels. Expected: " 
	            + expectedObjectivesList.size() + ", Available: " + ListofChannelsAfterSelectingObjective.size());
	    }

	    // Check if each expected objective exists in the available channels
	    for (String objective : expectedObjectivesList) {
	        System.out.println("Checking availability of channel: " + objective.trim());
	        if (!ListofChannelsAfterSelectingObjective.contains(objective.trim())) {
	            throw new NoSuchElementException("Channel not available in the list: " + objective.trim());
	        }
	    }

	    for (String objective : expectedObjectivesList) {
	        selectChannel(objective.trim());
	    }
	}

	private void selectChannel(String objective) {
	    try {
	        // Scroll into view first
	        js.executeScript("arguments[0].scrollIntoView(true);", scrolltoselectchannels);

	        // Create the dynamic XPath for the objective
	        String xpath = "//div[text()='" + objective + "' and contains(@class,'white')]";
	        WebElement element = driver.findElement(By.xpath(xpath));

	        // Click on the element if it is found
	        element.click();
	        System.out.println("Clicked on the element: " + objective);

	    } catch (NoSuchElementException e) {
	        handleElementNotFound(objective);
	    } catch (Exception e) {
	        System.out.println("An unexpected error occurred while selecting the channel: " + e.getMessage());
	    }
	}

	private void handleElementNotFound(String objective) {
	    System.out.println("Element not found. Clicking on fallback URL.");
	    ManageMappingLink.click();

	    // Handle the new tab
	    ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(1));
	    System.out.println("Switched to the new tab.");

	    String xpath = "//div[text()='" + objective.toUpperCase() + "']";
	    WebElement fallbackElement = driver.findElement(By.xpath(xpath));
	    fallbackElement.click();
	    
	    // Assuming 'mapYourAdvertisingIdWithin' is defined and clickable
	    mapYourAdvertisingIdWithin.click();

	    // Add any additional logic for Instagram and Meta here
	}


	        	
	        	
	        	
	     
	

	public void clickOnTargetGroup() {
	    try {
	        addTargetGroup.click();
	        System.out.println("Clicked on the target group.");
	    } catch (Exception e) {
	        throw new RuntimeException("Error clicking on target group: " + e.getMessage(), e);
	    }
	}

	public void enterCampaignName(String campaignName) {
	    try {
	        js.executeScript("arguments[0].scrollIntoView(true);", CampaignNameTextField);
	        CampaignNameTextField.sendKeys(campaignName);
	        System.out.println("Entered campaign name: " + campaignName);
	    } catch (Exception e) {
	        throw new RuntimeException("Error entering campaign name: " + e.getMessage(), e);
	    }
	}

	public void enterCampaignURL(String campaignURL) {
	    try {
	        CampaignURLTextField.sendKeys(campaignURL);
	        System.out.println("Entered campaign URL: " + campaignURL);
	    } catch (Exception e) {
	        throw new RuntimeException("Error entering campaign URL: " + e.getMessage(), e);
	    }
	}

	
}
