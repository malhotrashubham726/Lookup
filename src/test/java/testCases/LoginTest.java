package testCases;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.TestBase;
import utilities.DataProviders;
import utilities.ExcelUtility;

public class LoginTest extends TestBase {
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups= {"regression", "check", "functional"}, invocationCount = 2)
	public void login(String email, String password, String row) {
		try {
			HomePage hp=new HomePage(driver);
			hp.clickLogin();
			logger.info("Logged in login page");
			
			LoginPage lp=new LoginPage(driver);
//			lp.sendEmail(prop.getProperty("email"));
//			lp.sendPassword(prop.getProperty("password"));
			
			lp.sendEmail(email);
			lp.sendPassword(password);
			
			lp.clickLogin();

			MyAccountPage myAcPage=new MyAccountPage(driver);
			
			wait.until(ExpectedConditions.or(ExpectedConditions.visibilityOf(lp.errorMsg), ExpectedConditions.titleIs(myAcPage.acPageTitle)));
			logger.info("Logging in using email and password");
			
			ExcelUtility utils=new ExcelUtility(System.getProperty("user.dir") + prop.getProperty("excelInputPath"));
			String outputPath=System.getProperty("user.dir") + prop.getProperty("excelInputPath");
			int integerRow=Integer.valueOf(row);
			
			if(driver.getTitle().equals(myAcPage.acPageTitle)) {
				logger.info("User logged in using " + email);
				myAcPage.clickLogout();
				myAcPage.clickContinueAfterLogout();
//				utils.setCellData("Sheet1", integerRow, 2, "Pass", outputPath);
			}
			
			else {
				logger.info("Login failed using " + email);
				
//				utils.setCellData("Sheet1", integerRow, 2, "Fail", outputPath);
				Assert.fail();
			}
			
		}
	
		catch(Exception e) {
			System.out.println(e);
			Assert.fail();
		}
	}
}
