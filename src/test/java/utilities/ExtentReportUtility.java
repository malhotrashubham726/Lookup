package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.TestBase;

public class ExtentReportUtility implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String filePath;
	
	public void onStart(ITestContext context) {
		LocalDateTime dateTime=LocalDateTime.now();
		DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
		String timestamp=dateTime.format(dtf);
		
		filePath="//reports//report" + timestamp + ".html";
		
		sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir") + filePath);
		sparkReporter.config().setDocumentTitle("Reports");
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Lookup Reports");
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("QA", "Shubham");
	}
	
	public void onTestSuccess(ITestResult result) {
		System.out.println("Success: " + result.getName());
		test=extent.createTest(result.getName());
		test.log(Status.PASS, result.getName());
	}
	
	public void onTestFailure(ITestResult result) {
		System.out.println("Failure: " + result.getName());
		test=extent.createTest(result.getName());
		test.log(Status.FAIL, result.getName());
		test.log(Status.INFO, result.getThrowable());
		 
		try {
			TestBase obj=new TestBase();
			String path=obj.captureScreenshot(result.getName());
			test.addScreenCaptureFromPath(path);
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result) {
		System.out.println("SKIPPED: " + result.getName());
		test=extent.createTest(result.getName());
		test.log(Status.SKIP, result.getName());
	}
	
	public void onFinish(ITestContext context) {
		extent.flush();
		
		try {
			File fileLocation=new File(System.getProperty("user.dir") + filePath);
			Desktop.getDesktop().browse(fileLocation.toURI());
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
