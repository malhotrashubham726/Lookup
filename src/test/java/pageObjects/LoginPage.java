package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement pathEmail;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement pathPassword;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement btnLogin;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	WebElement errorMsg;
	
	public void sendEmail(String email) {
		pathEmail.sendKeys(email);
	}
	 
	public void sendPassword(String password) {
		pathPassword.sendKeys(password);
	}
	
	public void clickLogin() {
		btnLogin.click();
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
}
