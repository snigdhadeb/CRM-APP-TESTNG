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

public class LoginTest extends TestBase{
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
		String classname=this.getClass().getName();
		currentclassname=classname;
		try {
			initializeBrowser();
			loginpage=new LoginPage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test(priority=1)
	public void checkTitle() {
		extentTest=extent.startTest("CheckTitle");
		Assert.assertEquals(loginpage.getTitle(), "Free CRM #1 cloud software for any business large or small");
	}
	
	@Test(priority=2)
	public void loginTest() throws IOException {
		extentTest=extent.startTest("LoginTest");
		String usr=PropertyHandling.readProperty("config.properties", "USERNAME");
		String pswd=PropertyHandling.readProperty("config.properties", "PASSWORD");
		homepage=loginpage.login(usr,pswd);
		Assert.assertTrue(false);
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		erh=new ExtentReportHandler();
		erh.logTestIntoExtent(result);
		extent.endTest(extentTest);
		driver.quit();
	}
	
	
	@AfterSuite
	public void endExtentReport() {
		erh.endReport();
		
	}
	
	
}
