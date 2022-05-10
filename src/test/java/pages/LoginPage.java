package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage
{
	@FindBy(css = "#userNameInput")
	private WebElement oInputEmail;
	@FindBy(css = "#passwordInput")
	private WebElement oInputPassword;
	@FindBy(css = "#submitButton")
	private WebElement oButtonSignin;
	
	
	public LoginPage() {
		super();
		PageFactory.initElements(super.driver, this);
	}
	
	
	public HomePage login(String email, String pass) {
		
		oInputEmail.clear();
		oInputEmail.sendKeys(email);
		oInputPassword.clear();
		oInputPassword.sendKeys(pass);
		oButtonSignin.click();
		
		return new HomePage();
	}
}
