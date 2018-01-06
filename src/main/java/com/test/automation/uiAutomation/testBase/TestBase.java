package com.test.automation.uiAutomation.testBase;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.automation.uiAutomation.excelReader.ExcelReader;

public class TestBase {

	public WebDriver driver;
	String url = "http://automationpractice.com/index.php";
	String browser = "firefox";
	public static final Logger logger = Logger.getLogger(TestBase.class.getName());
	ExcelReader excelReader;
	WebDriverWait wait;

	public void init() {
		selectBrowser(browser);
		getUrl(url);
		String log4jConfig = "log4j.properties";
		PropertyConfigurator.configure(log4jConfig);
	}

	public void selectBrowser(String browser) {
		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
			logger.info("creating object of " + browser);
			driver = new FirefoxDriver();
		}
	}

	public void getUrl(String url) {
		driver.get(url);
		logger.info("navigating to " + url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public String[][] getExcelData(String fileName, String sheetName) {
		String excelPath = System.getProperty("user.dir") + "/src/main/java/com/test/automation/uiAutomation/data/"
				+ fileName;
		excelReader = new ExcelReader(excelPath);
		String[][] excelLogindata = excelReader.getTestDataFromExcel(fileName, sheetName);
		return excelLogindata;
	}

	public void waitForElement(int timeOutInSeconds, WebElement element, WebDriver driver) {
		wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
}
