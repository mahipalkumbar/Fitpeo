package pageObjects;

import java.time.Duration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


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

@FindBy(xpath="//button[text()='Generate']")
WebElement generatef;

@FindBy(xpath="//textarea[@class='w-full h-[100px] bg-transparent border placeholder:italic border-x-nyx-gray-1 rounded-lg p-5 text-white outline-none']")  
WebElement generate;


@FindBy(xpath="//div[@class='slick-list']//img")
WebElement imagegenerationstatus;



@FindBy(xpath="//body/div/div/div/div/div/div[4]/h2[1]/div[1]")  
WebElement creativeDesign;


JavascriptExecutor js = (JavascriptExecutor) driver;

public void contextofimage(String img) {
    boolean imageFound = false; // Flag to check if the image was found and clicked

    for (WebElement opt : contextofimage) {
        try {
            if (opt.getText().equals(img)) {
                opt.click(); // Click the image if the text matches
                imageFound = true; // Set the flag to true
                System.out.println("Image context '" + img + "' selected.");
                break; // Exit the loop once the image is clicked
            }
        } catch (StaleElementReferenceException e) {
            // Handle stale element reference
            System.out.println("StaleElementReferenceException: The element is no longer attached to the DOM.");
            throw new RuntimeException("The image context '" + img + "' could not be clicked due to a stale reference.", e);
        } catch (WebDriverException e) {
            // Handle general WebDriver exceptions
            System.out.println("WebDriverException occurred while clicking the image context '" + img + "': " + e.getMessage());
            throw new RuntimeException("An error occurred while trying to click the image context '" + img + "'.", e);
        }
    }

    if (!imageFound) {
        System.out.println("Image context '" + img + "' not found in the list.");
        throw new NoSuchElementException("Image context '" + img + "' not found.");
    }
}


public void focusing(String focus) {
    boolean optionFound = false; // Flag to check if the focus option was found and clicked

    try {
        for (WebElement opt : focusing) {
            if (opt.getText().equals(focus)) {
                opt.click(); // Click the option if the text matches
                optionFound = true; // Set the flag to true
                System.out.println("Focus option '" + focus + "' selected.");
                break; // Exit the loop once the option is clicked
            }
        }

        if (!optionFound) {
            System.out.println("Focus option '" + focus + "' not found in the list.");
            throw new NoSuchElementException("Focus option '" + focus + "' not found.");
        }
    } catch (NoSuchElementException e) {
        throw new RuntimeException("Error: " + e.getMessage(), e);
    } catch (Exception e) {
        throw new RuntimeException("An unexpected error occurred while selecting focus option.", e);
    }
}


public void imagestyle(String style) {
    String xpathexp = "//div[text()='" + style + "']/..";
    boolean elementFound = false; // Flag to check if the element is found

    try {
        WebElement ele = driver.findElement(By.xpath(xpathexp));
        if (ele.isDisplayed()) { // Check if the element is displayed before clicking
            ele.click();
            elementFound = true; // Set the flag to true
            System.out.println("Style '" + style + "' selected.");
        }
    } catch (NoSuchElementException e) {
        System.out.println("Style '" + style + "' not found.");
        throw new RuntimeException("Style '" + style + "' not found.", e);
    } catch (Exception e) {
        System.out.println("An unexpected error occurred while selecting style.");
        throw new RuntimeException("An unexpected error occurred while selecting style.", e);
    }

    if (!elementFound) {
        throw new NoSuchElementException("Element for style '" + style + "' was not found.");
    }
}

		
public void submit() {
    try {
        if (submit.isDisplayed() && submit.isEnabled()) { // Check if the button is displayed and enabled
            submit.click(); // Click the submit button
            System.out.println("Submit button clicked.");
        } else {
            System.out.println("Submit button is not clickable.");
            throw new RuntimeException("Submit button is not clickable.");
        }
    } catch (NoSuchElementException e) {
        System.out.println("Submit button not found.");
        throw new RuntimeException("Submit button not found.", e);
    } catch (StaleElementReferenceException e) {
        System.out.println("Submit button is no longer attached to the DOM.");
        throw new RuntimeException("Submit button is no longer attached to the DOM.", e);
    } catch (Exception e) {
        System.out.println("An unexpected error occurred while trying to click the submit button.");
        throw new RuntimeException("An unexpected error occurred while trying to click the submit button.", e);
    }
}


public void generate(String prompt) {
    try {
    	wait.until(ExpectedConditions.invisibilityOf(submit));
        if (generate.isDisplayed() && generate.isEnabled()) { // Check if the element is displayed and enabled
            generate.sendKeys(prompt); // Enter the prompt into the input field
            System.out.println("Prompt entered: " + prompt);
        } else {
            System.out.println("Generate input field is not available for input.");
            throw new RuntimeException("Generate input field is not available for input.");
        }
    } catch (NoSuchElementException e) {
        System.out.println("Generate input field not found.");
        throw new RuntimeException("Generate input field not found.", e);
    } catch (StaleElementReferenceException e) {
        System.out.println("Generate input field is no longer attached to the DOM.");
        throw new RuntimeException("Generate input field is no longer attached to the DOM.", e);
    } catch (Exception e) {
        System.out.println("An unexpected error occurred while trying to enter the prompt.");
        throw new RuntimeException("An unexpected error occurred while trying to enter the prompt.", e);
    }
}


public void generatef() {
    try {
        // Wait until the generate button is both visible and clickable in one line
        wait.until(ExpectedConditions.and(
            ExpectedConditions.visibilityOf(generatef),
            ExpectedConditions.elementToBeClickable(generatef)
        ));

        // Use JavaScript to click the button as a fallback
        js.executeScript("arguments[0].click();", generatef);
        
        System.out.println("Clicked on the generate button successfully.");
        
    } catch (TimeoutException e) {
        System.out.println("Generate button not clickable after waiting.");
        throw new RuntimeException("Generate button not clickable after timeout.", e);
    } catch (NoSuchElementException e) {
        System.out.println("Generate button not found.");
        throw new RuntimeException("Generate button not found.", e);
    } catch (StaleElementReferenceException e) {
        System.out.println("Generate button is no longer attached to the DOM.");
        throw new RuntimeException("Generate button is no longer attached to the DOM.", e);
    } catch (Exception e) {
        System.out.println("An unexpected error occurred while trying to click the generate button.");
        throw new RuntimeException("An unexpected error occurred while trying to click the generate button.", e);
    }
}




public boolean ImageGenerationStatus() {
    try {
        // Create a WebDriverWait with a maximum wait time of 180 seconds (3 minutes)
        WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(180)); // Assuming `driver` is your WebDriver instance

        // Wait until the image generation status element is visible
        wait4.until(ExpectedConditions.visibilityOf(imagegenerationstatus));

        // Check the visibility of the image generation status element
        boolean isDisplayed = imagegenerationstatus.isDisplayed();
        System.out.println("Image generation status is displayed: " + isDisplayed);
        return isDisplayed; // Return the visibility status of the element

    } catch (NoSuchElementException e) {
        System.out.println("Image generation status element not found.");
        return false; // Return false if the element is not found
    } catch (StaleElementReferenceException e) {
        System.out.println("Image generation status element is no longer attached to the DOM.");
        return false; // Return false if the element is stale
    } catch (TimeoutException e) {
        System.out.println("Timeout: Image generation status not displayed within 3 minutes.");
        throw new RuntimeException("Timeout: Image generation status not displayed within 3 minutes.", e);
    } catch (Exception e) {
        System.out.println("An unexpected error occurred while checking image generation status.");
        throw new RuntimeException("An unexpected error occurred while checking image generation status.", e);
    }
}
}
