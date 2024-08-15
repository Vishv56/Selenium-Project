package com.vishvpatel.orangehrmpages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageMethodUsingFindBy
{

	WebDriver driver;
	
	// Locators using @FindBy
	@FindBy(xpath = "//input[@name='username' or @placeholder='username']")
	WebElement webUserName;

	@FindBy(xpath = "//input[@name='password' or @placeholder='password']")
	WebElement webPassword;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement webSubmitButton;

	// Constructor
	public LoginPageMethodUsingFindBy(WebDriver driver)
	{
		this.driver = driver;

		// Initialize Page Factory
		PageFactory.initElements(driver, this);
	}

	// Method to open URL
	public void openUrl(String webUrl) 
	{
		driver.get(webUrl);
	}

	// Method to set username
	public void setUserName(String userNameValue) throws InterruptedException
	{
		webUserName.sendKeys(userNameValue);
		Thread.sleep(2000);
	}

	// Method to set password
	public void setPassword(String passwordValue) throws InterruptedException 
	{
		webPassword.sendKeys(passwordValue);
		Thread.sleep(2000);
	}

	// Method to click submit button
	public void clickSubmitButton() throws InterruptedException 
	{
		webSubmitButton.click();
		Thread.sleep(2000);
	}
}
