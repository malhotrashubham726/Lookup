package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(how=How.XPATH, using="//a[@title='My Account']")
	WebElement pathMyAccount;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement pathRegister;
	
	@FindBy(xpath = "//a[normalize-space()='Login']")
	WebElement pathLogin;
	
	public void clickAccount() {
		pathMyAccount.click();
	}
	
	public void clickRegister() {
		wait.until(ExpectedConditions.elementToBeClickable(pathMyAccount)).click();
		wait.until(ExpectedConditions.elementToBeClickable(pathRegister)).click();
	}
	
	public void clickLogin() {
		wait.until(ExpectedConditions.elementToBeClickable(pathMyAccount)).click();
		wait.until(ExpectedConditions.elementToBeClickable(pathLogin)).click();
	}

}
