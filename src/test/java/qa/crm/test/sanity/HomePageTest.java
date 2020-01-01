package qa.crm.test.sanity;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Base.TestBase;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import utilities.ExtentReportHandler;
import utilities.PropertyHandling;

public class HomePageTest extends TestBase {
	
	LoginPage loginpage;
	HomePage homepage;
	ExtentReportHandler erh;
	
	
	@BeforeTest
	public void setupExtent() {
		erh=new ExtentReportHandler();
		erh.setupExtentReport();
	}
	
	@BeforeMethod
	public void initializeSetUp() {
		try {
			String classname=this.getClass().getName();
			currentclassname=classname;
			initializeBrowser();
			loginpage=new LoginPage();
			String usr=PropertyHandling.readProperty("config.properties", "USERNAME");
			String pswd=PropertyHandling.readProperty("config.properties", "PASSWORD");
			homepage=loginpage.login(usr,pswd);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void checkUser() throws IOException {
		extentTest=extent.startTest("CheckUser");
		String user=PropertyHandling.readProperty("config.properties", "USER");
		Assert.assertTrue(homepage.checkUser(user));
	}
	
	
	
	
	
	
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		erh=new ExtentReportHandler();
		erh.logTestIntoExtent(result);
		extent.endTest(extentTest);
		driver.quit();
		
	}
	
	
}
