package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Driver;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class TestBase {
	public static WebDriver driver;
	public Logger logger;
	public Properties prop;
	public WebDriverWait wait;
	
	@BeforeClass(alwaysRun=true)
	@Parameters({"browser"})
	public void beforeClass(String browser) throws IOException {
		logger=LogManager.getLogger(this.getClass());
		logger.info("Before class using browser " + browser);
		
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
				Assert.fail("Invalid browser " + browser);
			}
		}
		
		FileInputStream file=new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
		prop=new Properties();
		prop.load(file);
		
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().deleteAllCookies();
		
		wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	@AfterClass(alwaysRun=true)
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
	
	public String captureScreenshot(String name) {
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		
		String fileLocation=System.getProperty("user.dir") + "//screenshots//" + name + "_" + System.currentTimeMillis();
		File destination=new File(fileLocation);
		
		source.renameTo(destination);
		
		return fileLocation;
	}

}
