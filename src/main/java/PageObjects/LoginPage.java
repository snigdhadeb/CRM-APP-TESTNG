package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.TestBase;
import utilities.UserActions;

public class LoginPage extends TestBase {
	
	UserActions action=new UserActions();
	
	//OR
	@FindBy(xpath="//span[contains(text(),'Log In')]")
	private WebElement loginbutton;
	
	@FindBy(name="email")
	private WebElement email;
	
	@FindBy(name="password")
	private WebElement password;
	
	@FindBy(xpath="//div[@class='ui fluid large blue submit button']")
	private WebElement login;
	
	//Constructor
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	
	//Methods
	public String getTitle() {
		return driver.getTitle();
	}
	
	public HomePage login(String emailid, String pass) {
		action.click(loginbutton);
		action.setText(email, emailid);
		action.setText(password, pass);
		action.click(login);
		return new HomePage();
		
		
	}
	
	
}
