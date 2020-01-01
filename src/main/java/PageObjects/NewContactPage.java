package PageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.TestBase;
import utilities.UserActions;

public class NewContactPage extends TestBase{
	
	UserActions action=new UserActions();
	
	@FindBy(xpath="//div[text()='Create New Contact']")
	private WebElement header;
	
	@FindBy(name="first_name")
	private WebElement firstname;
	
	@FindBy(name="last_name")
	private WebElement lastname;
	
	@FindBy(xpath="//input[@placeholder='Email address']")
	private WebElement emailaddress;
	
	@FindBy(css=".ui.linkedin.button")
	private WebElement Save;
	
	@FindBy(xpath="//*[text()='Error']")
	private List<WebElement> ErrorText;
	
	public NewContactPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	public void createContactWithMin(String fname, String lname, String email) {
		action.setText(firstname, fname);
		action.setText(lastname, lname);
		action.click(emailaddress);
		action.setText(emailaddress, email);
		action.click(Save);
	}
	
	public String getHeader() {
		return header.getText();
	}
	
	public void clickSave() {
		action.click(Save);
	}
	
	public boolean ErrorPopupCheck() {
		if(ErrorText.size()>0) {
			return true;
		}else {
			return false;
		}
	}

}
