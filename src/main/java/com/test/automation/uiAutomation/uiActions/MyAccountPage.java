package com.test.automation.uiAutomation.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.automation.uiAutomation.testBase.TestBase;

public class MyAccountPage extends TestBase {
	public static final Logger logger = Logger.getLogger(HomePage.class.getName());
	WebDriver driver;

	@FindBy(xpath = "//*[@id=\"email\"]")
	WebElement loginEmailTxtBox;

	@FindBy(xpath = "//*[@id=\"passwd\"]")
	WebElement loginPwdTxtBox;

	@FindBy(xpath = "//*[@id=\"SubmitLogin\"]")
	WebElement signInBtn;

	@FindBy(xpath = "//*[@id=\"center_column\"]/div[1]/ol/li")
	WebElement authenticationFailedMsg;

	@FindBy(xpath = "//*[@id=\"email_create\"]")
	WebElement newEmailAddressTxtBox;

	@FindBy(xpath = "//*[@id=\"SubmitCreate\"]/span")
	WebElement createAnAccountBtn;

	@FindBy(xpath = "//*[@id=\"center_column\"]/descendant::p[contains(text(),'Welcome to your account')]")
	WebElement accountLogonSuccessMsg;

	@FindBy(xpath = "//*[@id=\"header\"]/descendant::a[@class='logout']")
	WebElement logoutLnk;

	public MyAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void loginToApplication(String email, String password) {
		loginEmailTxtBox.sendKeys(email);
		logger.info("Entered login email address");
		loginPwdTxtBox.sendKeys(password);
		logger.info("Entered login password");
		signInBtn.click();
		logger.info("Clicked on Submit button");
	}

	public String getAuthenticationFailedMsg() {
		logger.info("Fetching authentication failed error message");
		return authenticationFailedMsg.getText();
	}

	public void createAccount(String emailAddress) {
		newEmailAddressTxtBox.sendKeys(emailAddress);
		createAnAccountBtn.click();
	}

	public boolean verifyAccountLogonSuccessfully() {
		if (accountLogonSuccessMsg.getText().contains(("Welcome to your account"))) {
			return true;
		}
		return false;
	}

	public void clickLogoutLnk() {
		waitForElement(20, logoutLnk, driver);
		logoutLnk.click();
	}

	public boolean verifyLogoutSuccess() {
		waitForElement(20, signInBtn, driver);
		if (signInBtn.isDisplayed()) {
			return true;
		}
		return false;
	}
}
