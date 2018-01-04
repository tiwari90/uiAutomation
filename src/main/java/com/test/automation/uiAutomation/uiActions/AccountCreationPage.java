package com.test.automation.uiAutomation.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AccountCreationPage {

	public static final Logger logger = Logger.getLogger(AccountCreationPage.class.getName());
	WebDriver driver;
	Select select;
	
	
	@FindBy(xpath = "//*[@id=\"id_gender1\"]")
	WebElement titleRadioBtn;
	
	@FindBy(xpath = "//*[@id=\"customer_firstname\"]")
	WebElement customerFirstNameTxtBox;

	@FindBy(xpath = "//*[@id=\"customer_lastname\"]")
	WebElement customerLastNameTxtBox;
	
	@FindBy(xpath = "//*[@id=\"passwd\"]")
	WebElement passwordTxtBox;
	
	@FindBy(xpath = "//*[@id=\"days\"]")
	WebElement dayDOBRadioBtn;
	
	@FindBy(xpath = "//*[@id=\"months\"]")
	WebElement monthDOBRadioBtn;
	
	@FindBy(xpath = "//*[@id=\"years\"]")
	WebElement yearDOBRadioBtn;
	
	@FindBy(xpath = "//*[@id=\"company\"]")
	WebElement companyTxtBox;
	
	@FindBy(xpath = "//*[@id=\"address1\"]")
	WebElement addressTxtBox;
	
	@FindBy(xpath = "//*[@id=\"city\"]")
	WebElement cityTxtBox;
	
	@FindBy(xpath = "//*[@id=\"id_state\"]")
	WebElement stateDrpDwn;
	
	@FindBy(xpath = "//*[@id=\"postcode\"]")
	WebElement zipTxtBox;
	
	@FindBy(xpath = "//*[@id=\"id_country\"]")
	WebElement countryDrpDwn;
	
	@FindBy(xpath = "//*[@id=\"phone_mobile\"]")
	WebElement mobilePhoneTxtBox;
	
	@FindBy(xpath = "//*[@id=\"alias\"]")
	WebElement addressAliasTxtBox;
	
	@FindBy(xpath = "//*[@id=\"submitAccount\"]")
	WebElement registerBtn;
	
	@FindBy(xpath = "//*[@id=\"center_column\"]/descendant::p[contains(text(),'Welcome to your account')]")
	WebElement accountCreationSuccessMsg;

	
	public AccountCreationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterPersonalInfo(String fName, String lName, String pwd, String day, String month, String year) {
		titleRadioBtn.click();
		customerFirstNameTxtBox.sendKeys(fName);
		customerLastNameTxtBox.sendKeys(lName);
		passwordTxtBox.sendKeys(pwd);
		selectDOB(day, month, year);
	}
	
	private void selectDOB(String day, String month, String year) {
		select = new Select(dayDOBRadioBtn);
		select.selectByValue(day);
		select = new Select(monthDOBRadioBtn);
		select.selectByValue(month);
		select = new Select(yearDOBRadioBtn);
		select.selectByValue(year);
	}
	
	public void enterAddress(String compName, String address, String city, String state, String zip, String country, String mobilePhone, String addressAlias) {
		Actions action = new Actions(driver);
		action.moveToElement(companyTxtBox);
		companyTxtBox.sendKeys(compName);
		addressTxtBox.sendKeys(address);
		cityTxtBox.sendKeys(city);
		selectStateAndCountry(state, country);
		zipTxtBox.sendKeys(zip);
		mobilePhoneTxtBox.sendKeys(mobilePhone);
		addressAliasTxtBox.sendKeys(addressAlias);	
	}
	
	private void selectStateAndCountry(String state, String country) {
		select = new Select(stateDrpDwn);
		select.selectByVisibleText(state);
		select = new Select(countryDrpDwn);
		select.selectByVisibleText(country);
	}
	
	public void clickRegister() {
		registerBtn.click();
	}
	
	public void createAccount(String fName, String lName, String pwd, String day, String month, String year, String compName, String address, String city, String state, String zip, String country, String mobilePhone, String addressAlias) {
		enterPersonalInfo(fName, lName, pwd, day, month, year);
		enterAddress(compName, address, city, state, zip, country, mobilePhone, addressAlias);
		clickRegister();
	}
	
	public boolean verifyAccountCreation() {
		if (accountCreationSuccessMsg.getText().contains(("Welcome to your account"))) {
			return true;
		}
		return false;
	}
	
}
