package com.test.automation.uiAutomation.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {
	public static final Logger logger = Logger.getLogger(HomePage.class.getName());
	WebDriver driver;
	
	@FindBy(xpath = "//*[@id=\"email\"]")
	WebElement loginEmailTxtBox;
	
	@FindBy(xpath = "//*[@id=\"passwd\"]")
	WebElement loginPwdTxtBox;
	
	@FindBy(xpath = "//*[@id=\"SubmitLogin\"]")
	WebElement submitBtn;
	
	@FindBy(xpath = "//*[@id=\"center_column\"]/div[1]/ol/li")
	WebElement authenticationFailedMsg;
	
	@FindBy(xpath = "//*[@id=\"email_create\"]")
	WebElement newEmailAddressTxtBox;
	
	@FindBy(xpath = "//*[@id=\"SubmitCreate\"]/span")
	WebElement createAnAccountBtn;
	
	
	public MyAccountPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void loginToApplication(String email, String password) {
		logger.info("Clicked on sign in button");
		loginEmailTxtBox.sendKeys(email);
		logger.info("Entered login email address");
		loginPwdTxtBox.sendKeys(password);
		logger.info("Entered login password");
		submitBtn.click();
		logger.info("Clicked on submit button");
	}
	
	public String getAuthenticationFailedMsg() {
		logger.info("Fetching authentication failed error message");
		return authenticationFailedMsg.getText();
	}
	
	public void createAccount(String emailAddress) {
		newEmailAddressTxtBox.sendKeys(emailAddress);
		createAnAccountBtn.click();
	}
}
