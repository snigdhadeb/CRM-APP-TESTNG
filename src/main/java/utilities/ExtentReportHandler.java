package utilities;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.internal.TestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import Base.TestBase;

public class ExtentReportHandler extends TestBase{
	
	public void setupExtentReport() {
		extent=new ExtentReports(System.getProperty("user.dir")+"\\test-output\\ExtentReport.html",true);
		extent.addSystemInfo("Host Name","Snigdhadeb-PC");
		extent.addSystemInfo("User Name","Snigdhadeb");
		extent.addSystemInfo("Environment","QA");
	}
	
	public void endReport() {
		extent.flush();
		extent.close();
	}

	public void logTestIntoExtent(ITestResult result) throws IOException {
		if(result.getStatus()==TestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "Test case Failed is: "+result.getName());
			extentTest.log(LogStatus.FAIL, result.getThrowable());
			String screenshotpath=UserActions.getScreenShot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotpath));
			
		}else if(result.getStatus()==TestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test Case Skipped is:"+result.getName());
			
		}else if(result.getStatus()==TestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test Case Passed is:"+result.getName());
		}
		
	}

	
}
