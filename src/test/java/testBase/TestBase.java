package testBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Driver;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class TestBase {
	public WebDriver driver;
	public Logger logger;
	public Properties prop;
	
	@BeforeClass
	@Parameters({"browser"})
	public void beforeClass(String browser) throws IOException {
		switch(browser) {
			case "chrome": {
				driver=new ChromeDriver();
				break;
			}
			
			case "edge": {
				driver=new EdgeDriver();
				break;
			}
			
			case "firefox": {
				driver=new FirefoxDriver();
				break;
			}
			
			default: {
				return;
			}
		}
		
		FileInputStream file=new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
		prop=new Properties();
		prop.load(file);
		
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().deleteAllCookies();
		// Lets check
		logger=LogManager.getLogger(this.getClass());
	}
	
	@AfterClass
	public void afterClass() {
		driver.close();
	}
	
	public String randomString(int count, String type) {
		switch(type) {
		case "alpha": {
			return RandomStringUtils.randomAlphabetic(count).toUpperCase();
		}
	
		case "numeric": {
			return RandomStringUtils.randomNumeric(count);
		}
		
		case "email": {
			return RandomStringUtils.randomAlphabetic(count) + "@abc.com";
		}
		
		case "password": {
			return RandomStringUtils.randomAlphabetic(count) + RandomStringUtils.randomNumeric(count);
		}
		
		default:
			return "";
		}
	}

}
