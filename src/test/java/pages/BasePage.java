package pages;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import setup.DriverManager;

public class BasePage {

	
	protected WebDriver driver;
	
	public BasePage() {
		this.driver = DriverManager.getWebDriver();
	}
	
	public Logger log() {
		return LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
	}
	
	
	public void sleep(int secs) {
		try {
			TimeUnit.SECONDS.sleep(secs);
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
		log().info("Waited for " + secs + " seconds");
	}
	
	public void elementVisible(WebElement element, String objectName) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 30);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (TimeoutException e) {
			log().warn("Could not wait for element to be visible: {}", objectName, e);
		}
	}

	public void elementClickable(WebElement element, String objectName) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 30);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (TimeoutException e) {
			log().warn("Could not wait for element to be clickable: {}", objectName, e);
		}
	}
	
	public void scrollToViewUsingJavascriptExecutor(WebElement element, String objectName) {
		((JavascriptExecutor) DriverManager.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
//		elementClickable(element, objectName);
		sleep(2);
	}
	
	public void sendKeysUsingJavascriptExecutor(WebElement locator, String input) {
		((JavascriptExecutor) DriverManager.getWebDriver()).executeScript("arguments[0].value='" + input + "'", locator);
	}
	
	public void clickUsingJavascriptExecutor(WebElement locator) {
		((JavascriptExecutor) DriverManager.getWebDriver()).executeScript("arguments[0].click();", locator);
	}
}
