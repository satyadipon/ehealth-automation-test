package setup;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TestSetup {
	
	
	
	private WebDriver driver = null;


	@BeforeSuite(alwaysRun = true)
	public void setUp() {

		WebDriverManager.chromedriver().setup();

	}

	@BeforeClass(alwaysRun = true)
	public synchronized void beforeClass() {
		
		if (driver == null) {
			driver = new ChromeDriver();
			
			DriverManager driverManager = new DriverManager();
			driverManager.setWebDriver(driver);
			
			DriverManager.getWebDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			DriverManager.getWebDriver().manage().window().maximize();
			
			DriverManager.getWebDriver().get("https://outreach.eht-qa-01.ehealth-development.com");
			

		}

	}




	@AfterClass(alwaysRun = true)
	public synchronized void afterClass() {

		DriverManager.getWebDriver().quit();

	}
	
	

}
