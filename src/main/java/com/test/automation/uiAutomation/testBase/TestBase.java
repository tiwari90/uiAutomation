package com.test.automation.uiAutomation.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.automation.uiAutomation.excelReader.ExcelReader;

public class TestBase {

	public static WebDriver driver;
	public static final Logger logger = Logger.getLogger(TestBase.class.getName());
	ExcelReader excelReader;
	WebDriverWait wait;
	Properties properties;
	
	FileInputStream fis;

	public void loadConfigProperties() throws IOException {
		properties = new Properties();
		File file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\test\\automation\\uiAutomation\\config\\config.properties");
		FileInputStream fis = new FileInputStream(file);
		properties.load(fis);
	}
	
	public void init() throws IOException {
		loadConfigProperties();
		selectBrowser(properties.getProperty("browser"));
		getUrl(properties.getProperty("url"));
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

	public void takeScreenShot(String screenShotFileName) {
		try {
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String destinationPath = new File(System.getProperty("user.dir"))
					+ "/src/main/java/com/test/automation/uiAutomation/screenshot/";
			File destFile = new File(destinationPath + screenShotFileName + ".png");
			FileUtils.copyFile(srcFile, destFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void highlightElement(WebDriver driver){
		String newXpath = "//*[@id=\\\"center_column\\\"]/div[1]/ol/li";
        WebElement element = driver.findElement(By.xpath(newXpath));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].style.border='3px solid red'", element);
    }
}
