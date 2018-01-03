package com.test.automation.uiAutomation.homepage;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.automation.uiAutomation.testBase.TestBase;
import com.test.automation.uiAutomation.uiActions.HomePage;

public class TC001_VerifyLoginWithInvalidCredentials extends TestBase{
 
	public static final Logger logger = Logger.getLogger(TC001_VerifyLoginWithInvalidCredentials.class.getName());
	HomePage homePage;
	
	@BeforeTest
	public void setup() {
		init();
	}
	
	//Comments
	@Test
	public void verifyLoginWithInvalidCredentials() {
		logger.info("******************* Verification Started *******************");
		homePage = new HomePage(driver);
		homePage.loginToApplication("email", "password");
		Assert.assertEquals(homePage.getAuthenticationFailedMsg(), "Invalid email address.");
		logger.info("******************* Verification Completed *******************");
	}
	
	@AfterTest
	public void endTest() {
		driver.close();
		
	}
}
