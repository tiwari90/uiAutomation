package com.test.automation.uiAutomation.homepage;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.automation.uiAutomation.testBase.TestBase;
import com.test.automation.uiAutomation.uiActions.HomePage;
import com.test.automation.uiAutomation.uiActions.MyAccountPage;

public class TC001_VerifyLoginWithInvalidCredentials extends TestBase{
 
	public static final Logger logger = Logger.getLogger(TC001_VerifyLoginWithInvalidCredentials.class.getName());
	MyAccountPage myAccount;
	HomePage homepage;
	
	@BeforeTest
	public void setup() throws IOException {
		init();
	}
	
	@Test
	public void verifyLoginWithInvalidCredentials() {
		logger.info("******************* Verification Started *******************");
		homepage = new HomePage(driver);
		homepage.clickSignIn();
		myAccount = new MyAccountPage(driver);
		myAccount.loginToApplication("email", "password");
		highlightElement(driver);
		Assert.assertEquals(myAccount.getAuthenticationFailedMsg(), "Invalid email address.");
		logger.info("******************* Verification Completed *******************");
	}
	
	@AfterTest
	public void endTest() {
		driver.close();
		
	}
}
