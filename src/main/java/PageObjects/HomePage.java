package PageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.TestBase;
import utilities.UserActions;

public class HomePage extends TestBase{
	
	UserActions action=new UserActions();
	
	@FindBy(xpath="//div[@id='main-nav']//span")
	private List<WebElement> menuitems;
	
	@FindBy(css=".user-display")
	private WebElement userText;
	
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	
	
	public void selectMenu(String item) {
		for(WebElement it:menuitems) {
			if(it.getText().equalsIgnoreCase(item)) {
				action.click(it);
				break;
			}
		}
	}
	
	public boolean checkUser(String user) {
		if(userText.getText().equalsIgnoreCase(user)) {
			return true;
		}else {
			return false;
		}
	}
}
