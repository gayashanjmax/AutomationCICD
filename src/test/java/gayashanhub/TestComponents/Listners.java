package gayashanhub.TestComponents;

import org.openqa.selenium.WebDriver;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import gayashanhub.resources.ExtentReportNG;

public class Listners extends BaseTest implements ITestListener{
	
	ExtentTest test;
	ExtentReports extent = ExtentReportNG.getReportObject();// call the method written in the ExtentReportNG class
	
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();// This is a thread safe machanisum by using ThreadLocal class by creating objects from it's own thread

	@Override
	public void onTestStart(ITestResult result)
	{
		test = extent.createTest(result.getMethod().getMethodName());//test case name
		extentTest.set(test); //assign unique thread id for the "test" object each time
	}
	
	@Override
	public void onTestSuccess(ITestResult result)
	{
		test.log(Status.PASS, "Test Passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result)
	{
		extentTest.get().fail(result.getThrowable());//this will get the test object set above
		
		
		//Get the driver which initialize in the Base class
		try
		{
		driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
		
		
		//Screenshot operations
		String filePath = null;
		try 
		{
		filePath = getScreenshot(result.getMethod().getMethodName(),driver);//get the filepath of the screenshot taken
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());//attach the screenshot to the Extent report
		
		//take a screenshot
	}
	
	@Override
	public void onTestSkipped(ITestResult result)
	{
		
	}
	
	@Override
	public void onFinish(ITestContext context)
	{
		extent.flush();
	}
}
