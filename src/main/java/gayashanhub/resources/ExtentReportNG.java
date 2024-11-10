package gayashanhub.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	

	
	public static ExtentReports getReportObject()
	{
		
		//ExtentSparkReporter do all the configurations as below
		//ExtentReports do all the executions
		
		String path = System.getProperty("user.dir")+"\\reports\\index.html\\"; // this is the command to creat a path dynamically and the report name is "index.html"
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");// set report name
		reporter.config().setDocumentTitle("Test Results"); // set title name
		
		
		ExtentReports extent = new ExtentReports(); // responsibe for execute all the report commands
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Gayashan");
		
		return extent;
	}

}
