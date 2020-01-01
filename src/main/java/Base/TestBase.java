package Base;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


import utilities.PropertyHandling;
import utilities.WebEventListener;

public class TestBase {
	
	public static WebDriver driver;
	public String driverdir=System.getProperty("user.dir");
	public WebDriverWait wait;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener listen;
	public  static String currentclassname=null;
	public static Logger log = null;
	public static ExtentReports extent;
	public static ExtentTest extentTest;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void initializeBrowser() throws IOException {
		log=Logger.getLogger(currentclassname);
		String browsername=PropertyHandling.readProperty("config.properties", "BROWSER");
		switch(browsername.toLowerCase()) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", driverdir+"//resources//chromedriver.exe");
			driver=new ChromeDriver();
			log.info("Chrome Browser is initiated");
			driver.get(PropertyHandling.readProperty("config.properties", "URL"));
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", driverdir+"//resources//geckodriver.exe");
			driver=new FirefoxDriver();
			driver.get(PropertyHandling.readProperty("config.properties", "URL"));
			break;
		default:
			System.out.println("Please choose a valid driver");
		}
		e_driver=new EventFiringWebDriver(driver);
		WebEventListener listen= new WebEventListener();
		e_driver.register(listen);
		driver=e_driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(PropertyHandling.readProperty("config.properties","PAGELOADTIMEOUT")), TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(PropertyHandling.readProperty("config.properties","PAGELOADTIMEOUT")), TimeUnit.SECONDS);
		
	}
	
	
}
