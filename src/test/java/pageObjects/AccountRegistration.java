package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountRegistration extends BasePage {
	
	public AccountRegistration(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement pathFirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement pathLastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement pathEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement pathTelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement pathPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement pathConfirmPassword;
	
	@FindBy(xpath = "//input[@type='checkbox']")
	WebElement btnPrivacyPolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[contains(normalize-space(), 'Account Has Been Created')]")
	WebElement msgConfirm;
	
	public void sendFirstName(String fName) {
		pathFirstName.sendKeys(fName);
	}
	
	public void sendLastName(String lName) {
		pathLastName.sendKeys(lName);
	}
	
	public void sendEmail(String email) {
		pathEmail.sendKeys(email);
	}
	
	public void sendTelephone(String telephone) {
		pathTelephone.sendKeys(telephone);
	}
	
	public void sendPassword(String password) {
		pathPassword.sendKeys(password);
	}
	
	public void sendConfirmPassword(String confirmPassword) {
		pathConfirmPassword.sendKeys(confirmPassword);
	}
	
	public void clickPrivacyBtn() {
		btnPrivacyPolicy.click();
	}
	
	public void clickContinue() {
		btnContinue.click();
	}
	
	public String getConfirmationMsg() {
		try {
			return msgConfirm.getText();
		}
		
		catch(Exception e) {
			return e.getMessage();
		}
	}
	
	
}
