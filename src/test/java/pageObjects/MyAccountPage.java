package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountPage extends BasePage {
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement pathMyAccount;
	
	@FindBy(xpath="//li/a[normalize-space()='Logout']")
	WebElement pathLogout;
	
	@FindBy(xpath="//a[normalize-space()='Continue']")
	WebElement clickContinue;
	
	public String acPageTitle="My Account";
	
	public void clickLogout() {
		wait.until(ExpectedConditions.elementToBeClickable(pathMyAccount)).click();
		wait.until(ExpectedConditions.elementToBeClickable(pathLogout)).click();
	}
	
	public void clickContinueAfterLogout() {
		wait.until(ExpectedConditions.elementToBeClickable(clickContinue)).click();
	}
}
