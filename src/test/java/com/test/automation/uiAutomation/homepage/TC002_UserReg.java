package com.test.automation.uiAutomation.homepage;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.automation.uiAutomation.testBase.TestBase;
import com.test.automation.uiAutomation.uiActions.AccountCreationPage;
import com.test.automation.uiAutomation.uiActions.HomePage;
import com.test.automation.uiAutomation.uiActions.MyAccountPage;

public class TC002_UserReg extends TestBase{

	public static final Logger logger = Logger.getLogger(TC002_UserReg.class.getName());
	HomePage homePage;
	MyAccountPage myaccount;
	AccountCreationPage accountCreationPage;
	String email = "automation1@test.com";
	String fName = "Ashish";
	String lName = "Tiwari";
	String pwd = "test@123";
	String day = "1";
	String month = "3";
	String year = "1990";
	String compName = "Property Solutions";
	String address = "115 East Street";
	String city = "Provo";
	String state = "Utah";
	String zip = "10021";
	String country = "United States";
	String mobilePhone = "7898654532";
	String addressAlias = "Office";
	
	
	
	@BeforeTest
	public void setup() throws IOException {
		init();
	}
	
	@Test
	public void VerifyUserReg() {
		homePage = new HomePage(driver);
		homePage.clickSignIn();
		myaccount = new MyAccountPage(driver);
		myaccount.createAccount(email);
		accountCreationPage = new AccountCreationPage(driver);
		accountCreationPage.createAccount(fName, lName, pwd, day, month, year, compName, address, city, state, zip, country, mobilePhone, addressAlias);
		Assert.assertTrue(accountCreationPage.verifyAccountCreation());
	}
	
	@AfterTest
	public void endTest() {
		driver.close();
	}
}
