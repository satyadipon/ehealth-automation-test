package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LandingPage extends BasePage
{
	@FindBy(name = "email")
	private WebElement oInputEmail;
	@FindBy(css = ".auth0-lock-submit")
	private WebElement oButtonSignin;
	
	
	public LandingPage() {
		super();
		PageFactory.initElements(super.driver, this);
	}
	
	
	public LoginPage gotoLoginPage(String email) {
		
		oInputEmail.sendKeys(email);
		oButtonSignin.click();
		
		return new LoginPage();
	}
}
