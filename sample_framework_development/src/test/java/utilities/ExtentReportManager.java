package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

// https://extentreports.com/docs/versions/5/java/index.html					documentation for Extent Reports

// Step 1 - Add Maven dependancy

public class ExtentReportManager implements ITestListener 
{

	public ExtentSparkReporter sparkReporter;		// UI of the report
	public ExtentReports extent;					// Populate common info on the report(e.g. tester name, f/w version, module name etc.)
	public ExtentTest test;						// creating test case entries in the report and status of the test methods		(e.g. update pass, fail, attach screenshot)
	
	String reportname;
	
	
	
	public void onStart(ITestContext context)
	{
		String timestamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // time stamp for report name")
		
		reportname="Test-Report-"+timestamp+".html"; // report name with timestamp
		
		sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/myReport.html");
		
		sparkReporter.config().setDocumentTitle("Automation Report");		// Title of the report
		sparkReporter.config().setReportName("Functional Testing");			// name of the report
		sparkReporter.config().setTheme(Theme.DARK);			// Alternamte Theme.STANDARD
		
		extent=new ExtentReports();
		
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Computer Name", "localhost");		// Key value format
		extent.setSystemInfo("Environment", "QA");
		
		extent.setSystemInfo("Tester Name", System.getProperty("user.name")); // Get the system username dynamically);
		
		String br=context.getCurrentXmlTest().getParameter("browser"); // Get the browser name dynamically from xml file
		extent.setSystemInfo("Browser", br);
		
		String os=context.getCurrentXmlTest().getParameter("os"); // Get the browser name dynamically from xml file
		extent.setSystemInfo("Operating System", os);
		
		List<String> includedGroups= context.getCurrentXmlTest().getIncludedGroups(); // Get the groups included in the test dynamically from xml file
		if (!includedGroups.isEmpty())  // check if the list is not empty and there are any included groups fetched from xml file 
		{
			extent.setSystemInfo("Included Groups", includedGroups.toString());
		}
		
	// As of now it is static but actually it will be dynamic and will be detailed in Framework sessions
		
	}
	
	public void onTestSuccess(ITestResult result)
	{
	test= extent.createTest(result.getTestClass().getName());		// create a new entry in the report
	test.assignCategory(result.getMethod().getGroups()); 			// to display group in the report
	test.log(Status.PASS,"Test case passed is :" + result.getName());		// update status pass/fail/skip
	}

	public void onTestFailure(ITestResult result)
	{
	test= extent.createTest(result.getTestClass().getName());		// create a new entry in the report
	test.assignCategory(result.getMethod().getGroups()); 			// to display group in the report
	test.log(Status.FAIL,"Test case Failed is :" + result.getName());		// update status pass/fail/skip
	test.log(Status.FAIL,"Test case Failed cause is :" + result.getThrowable().getMessage());		// update status pass/fail/skip
	
	try 
	{
	String imgPath= new BaseClass().captureScreen(result.getName()); // Capture screenshot on failure calling Baseclass method
	test.addScreenCaptureFromPath(imgPath); // Attach screenshot to the report
	} 
	
	catch (Exception e) 
	{
	e.printStackTrace(); // Print stack trace if screenshot capture fails
	}
	
	
	
	}

	public void onTestSkipped(ITestResult result)
	{
	test= extent.createTest(result.getName());		// create a new entry in the report
	test.assignCategory(result.getMethod().getGroups()); 			// to display group in the report
	test.log(Status.SKIP,"Test case Skipped is :" + result.getName());		// update status pass/fail/skip
	}

	public void onFinish(ITestContext context)
	{
	extent.flush();

	// to automatically open the report after test execution
	String pathofExtentReport= System.getProperty("user.dir")+"/reports/"+reportname; // path of the report
	File extentReportFile = new File(pathofExtentReport);
	
	try 
	{
	Desktop.getDesktop().browse(extentReportFile.toURI()); // Open the report automatically after test execution
    } 
    catch (Exception e) 
    {
		e.printStackTrace(); // Print stack trace if report opening fails
	}
	
	}
}


