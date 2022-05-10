package setup;

import org.openqa.selenium.WebDriver;

public class DriverManager {

	
	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

	public static WebDriver getWebDriver(){
		return webDriver.get();
	}

	public void setWebDriver(WebDriver driver2){
		webDriver.set(driver2);
	}
}
