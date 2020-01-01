package PageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.TestBase;
import utilities.UserActions;

public class ContactsPage extends TestBase{
	UserActions action=new UserActions();
	
	@FindBy(css=".ui.header.item.mb5.light-black")
	private WebElement headertext;
	
	@FindBy(xpath="//button[text()='New']")
	private WebElement newbutton;
	
	
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String getHeader() {
		return headertext.getText();
	}
	
	
	public NewContactPage clickNew() {
		action.click(newbutton);
		return new NewContactPage();
	}
	
	public ArrayList<String> getAllContactsNames(){
		ArrayList<String> namelist=new ArrayList<String>();
		action.waitForElement(headertext);
		int size=driver.findElements(By.xpath("//table[@class='ui celled sortable striped table custom-grid table-scroll']//tr//td[2]")).size();
		for(int i=1;i<=size;i++) {
			
			namelist.add(driver.findElement(By.xpath("//table[@class='ui celled sortable striped table custom-grid table-scroll']//tr["+i+"]//td[2]")).getText());
			
		}
		return namelist;
	}
}
