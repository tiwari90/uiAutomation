package com.test.automation.uiAutomation.customListner;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.test.automation.uiAutomation.testBase.TestBase;

public class Listner extends TestBase implements ITestListener {

	public static Logger log = Logger.getLogger(Listner.class.getName());
	String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		try {
			log.info("Test Passed: Capturing Screenshot");
			String methodName = result.getName();
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String destinationPath = new File(System.getProperty("user.dir"))
					+ "/src/main/java/com/test/automation/uiAutomation/screenshot/";
			File destFile = new File(destinationPath + methodName + timeStamp + ".png");
			FileUtils.copyFile(srcFile, destFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			log.info("Test Failed: Capturing Screenshot");
			String methodName = result.getName();
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String destinationPath = new File(System.getProperty("user.dir"))
					+ "/src/main/java/com/test/automation/uiAutomation/screenshot/";
			File destFile = new File(destinationPath + methodName + timeStamp + ".png");
			FileUtils.copyFile(srcFile, destFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

}
