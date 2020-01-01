package qa.crm.test.sanity;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.TestBase;
import PageObjects.ContactsPage;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.NewContactPage;
import utilities.ExcelHandling;
import utilities.ExtentReportHandler;
import utilities.PropertyHandling;

public class NewContactPageTest extends TestBase{
	LoginPage loginpage;
	HomePage homepage;
	ContactsPage contactpage;
	NewContactPage newcontactpage;
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
	
	@DataProvider(name="AddContact")
	public Object[][] getData(){
		return ExcelHandling.getDatafromExcel(driverdir+"\\src\\main\\java\\TestData\\Testdata_sanity.xlsx", "AddContact");
	}
	
	@Test(dataProvider="AddContact")
	public void validateAddContact(HashMap<Object,Object> map) throws InterruptedException {
		extentTest=extent.startTest("ValidateAddContact");
		String fname=(String) map.get("FirstName");
		String lname=(String)map.get("LastName");
		String email=(String)map.get("EmailAddress");

		homepage.selectMenu("Contacts");
		contactpage=new ContactsPage();
		Assert.assertEquals(contactpage.getHeader(),"Contacts");
		newcontactpage=contactpage.clickNew();
		Assert.assertEquals(newcontactpage.getHeader(), "Create New Contact");
		newcontactpage.createContactWithMin(fname, lname, email);
		newcontactpage.clickSave();
		//Assert.assertFalse(newcontactpage.ErrorPopupCheck());
		
		//validate in comntact page
		Thread.sleep(1000);
		homepage.selectMenu("Contacts");
		Assert.assertEquals(contactpage.getHeader(),"Contacts");
		String fullname=fname+" "+lname;
		boolean namefound=false;
		Thread.sleep(3000);
		for(String ele:contactpage.getAllContactsNames()) {
			if(ele.equalsIgnoreCase(fullname)) {
				namefound=true;
				break;
			}else {
				namefound=false;
			}
		}
		
		Assert.assertTrue(namefound);
		
	}
	
	
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		erh=new ExtentReportHandler();
		erh.logTestIntoExtent(result);
		extent.endTest(extentTest);
		driver.quit();
		
	}
	
	
}
