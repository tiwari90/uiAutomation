
package com.test.automation.uiAutomation.homepage;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.test.automation.uiAutomation.testBase.TestBase;
import com.test.automation.uiAutomation.uiActions.HomePage;
import com.test.automation.uiAutomation.uiActions.MyAccountPage;

import freemarker.log.Logger;

public class TC003_VerifyloginWithExcel extends TestBase {

	public static final Logger logger = Logger.getLogger(TC003_VerifyloginWithExcel.class.getName());
	HomePage homePage;
	MyAccountPage myAccountPage;
	String fileName = "Test_Data.xlsx";
	String sheetName = "Login_Data";

	@DataProvider(name = "loginData")
	public String[][] getDataFromExcel() {
		String[][] testData = getExcelData(fileName, sheetName);
		return testData;
	}

	@BeforeTest
	public void setup() throws IOException {
		init();
	}

	@Test(dataProvider = "loginData")
	public void verifyMultipleReg(String email, String password) {
		homePage = new HomePage(driver);
		homePage.clickSignIn();
		myAccountPage = new MyAccountPage(driver);
		myAccountPage.loginToApplication(email, password);
		Assert.assertTrue(myAccountPage.verifyAccountLogonSuccessfully());
		myAccountPage.clickLogoutLnk();
		Assert.assertTrue(myAccountPage.verifyLogoutSuccess());
	}

	@AfterTest
	public void endTest() {

	}

}
