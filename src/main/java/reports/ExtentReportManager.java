package reports;

import java.util.Date;

import org.testng.ITestContext;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
       public static ExtentReports report;
       
       public static ExtentReports getReport(ITestContext context) {
    	if(report==null) {
    		report = new ExtentReports();
    		Date date = new Date();
    		String reportFolderName = date.toString().replace(":", "_").replace(" ", "-");
    		String reportPath = System.getProperty("user.dir") + "\\Reports\\" + reportFolderName;
        	ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        	
        	reporter.config().setDocumentTitle(context.getCurrentXmlTest().getName());
        	reporter.config().setEncoding("utf-8");
        	reporter.config().setTheme(Theme.DARK);
        	reporter.config().setReportName(context.getCurrentXmlTest().getSuite().getName());
        	
        	report.attachReporter(reporter);
    	}
    	   
		return report;
    	   
       }
}
