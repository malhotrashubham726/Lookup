package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistration;
import pageObjects.HomePage;
import testBase.TestBase;

public class AccountRegistrationTest extends TestBase {
	@Test
	public void verifyAccountRegistration() {
		try {
			logger.info("Starting Account Registration Test");
			HomePage hp=new HomePage(driver);
			hp.clickRegister();
			logger.info("Clicked on Register");
			
			AccountRegistration actReg=new AccountRegistration(driver);
			actReg.sendFirstName(randomString(5, "alpha"));
			actReg.sendLastName(randomString(5, "alpha"));
			actReg.sendEmail(randomString(5, "email"));
			actReg.sendTelephone(randomString(10, "numeric"));
			
			String password=randomString(5, "password");
			actReg.sendPassword(password);
			actReg.sendConfirmPassword(password);
			actReg.clickPrivacyBtn();
			actReg.clickContinue();
			String confirmation=actReg.getConfirmationMsg();
			Assert.assertEquals(confirmation, "Your Account Has Been Created!");
			logger.info("Account has been created successfully!");
		}
		catch(Exception e) {
			logger.error("Some error occured");
			logger.error(e.toString());
			System.out.println(e.toString());
		}
	}
}
