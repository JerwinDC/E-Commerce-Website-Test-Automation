package BaseTest;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import keywords.GenericKeywords;
import reports.ExtentReportManager;

public class TestBase extends GenericKeywords {
	public GenericKeywords app = null;
	public ExtentReports extentReport = null;
	public ExtentTest extentTest = null;
	
	@BeforeTest(alwaysRun = true)
	public void beforeTest(ITestContext context) {
		app = new GenericKeywords();
		extentReport = ExtentReportManager.getReport(context);
		extentTest = extentReport.createTest(context.getCurrentXmlTest().getName());
		app.setTestReport(extentTest);
		context.setAttribute("extentTest", extentTest);
		context.setAttribute("extentReport", extentReport);
		context.setAttribute("app", app);
		
	}
	
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(ITestContext context) {
		app = (GenericKeywords) context.getAttribute("app");
		extentReport = (ExtentReports) context.getAttribute("extentReport");
		extentTest = (ExtentTest) context.getAttribute("extentTest");
		
	}
	
	@AfterTest
	public void afterTest() {
		if (extentReport != null) {
			extentReport.flush();
		}
		
		if(driver!=null) {
			app.quitDriver();
		}
	}
	
	@AfterMethod
	public void afterMethod() {
		
		if (app != null) {
			app.quitDriver();
		}
			app.reportAll();
		
	}
}
