package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.TestBase;


public class UserActions extends TestBase {
	
	public static Logger log = null;
	
	public void click(WebElement ele) {
		try {
		wait=new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		ele.click();
		
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void waitForElement(WebElement ele) {
		try {
			wait=new WebDriverWait(driver,5);
			wait.until(ExpectedConditions.visibilityOf(ele));
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void setText(WebElement ele, String text) {
		try {
			wait=new WebDriverWait(driver,5);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			ele.clear();
			ele.sendKeys(text);
			String elem=ele.toString().split("->")[1];
			log = Logger.getLogger(currentclassname);
			log.info(elem+" is populated with "+text);
			}catch(Exception ex) {
				ex.printStackTrace();
			}
	}
	
	public static String getScreenShot(WebDriver driver, String screenshotname) throws IOException {
		String datename=new SimpleDateFormat("yyMMddHHmmss").format(new Date());
		File source=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destination=System.getProperty("user.dir")+"\\Screenshots\\"+screenshotname+datename+".png";
		FileUtils.copyFile(source, new File(destination));
		return destination;
	}
	
	
}
