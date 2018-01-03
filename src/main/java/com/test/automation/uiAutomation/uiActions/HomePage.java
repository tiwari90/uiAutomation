package com.test.automation.uiAutomation.uiActions;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	public static final Logger logger = Logger.getLogger(HomePage.class.getName());
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(xpath = "//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")
	WebElement signInBtn;
	
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
	
	
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void loginToApplication(String email, String password) {
		clickSignIn();
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
	
	public void clickSignIn() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		signInBtn.click();
	}
	
	public void createAccount(String emailAddress) {
		clickSignIn();
		wait.until(ExpectedConditions.elementToBeClickable(createAnAccountBtn));
		newEmailAddressTxtBox.sendKeys(emailAddress);
		createAnAccountBtn.click();
	}
}
