package pages;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class HomePage extends BasePage
{
	@FindBy(xpath = "//*[text()='Start Now']/ancestor::button")
	private WebElement oButtonStartNow;
	@FindBy(css = "#mat-input-1")
	private WebElement oInputSearchBox;
	@FindBy(xpath = "(//div[@role='listbox']//span[@class='mat-option-text'])")
	private List<WebElement> oTextSearchResults;
	@FindBy(xpath = "//a[text()='View More']")
	private List<WebElement> oLinkViewMore;


	@FindBy(xpath = "//*[contains(@class,'close icon icon-12')]")
	private List<WebElement> oButtonCloseCard;

	@FindBy(xpath = "//div[text()='Requested Items']")
	private List<WebElement> oTabRequestedItems;
	@FindBy(xpath = "//div[text()='Files']")
	private List<WebElement> oTabFiles;
	@FindBy(xpath = "//div[text()='Provider Details']")
	private List<WebElement> oTabProviderDetails;
	@FindBy(xpath = "//*[text()='Yes']/ancestor::button")
	private WebElement oButtonYes;




	@FindBy(xpath = "//*[@id='mat-select-0']")
	private List<WebElement> oTextAvailableRequestedItems;
	@FindBy(xpath = "//tbody/tr")
	private List<WebElement> oRowRequestedItems;
	String oPath_SoColRequestedItems = "//tbody/tr[$index]/td";

	@FindBy(css = "#viewerContainer canvas")
	private List<WebElement> oViewerPDF;
	@FindBy(css = "span.folder-name")
	private List<WebElement> oTextFolderName;
	@FindBy(css = "dd.file-item")
	private List<WebElement> oTextFile;
	@FindBy(css = "div.field-text")
	private List<WebElement> oText_FilesPatientNameAndDOB;


	@FindBy(css = "div.case-location-status")
	private List<WebElement> oTextCaseStatus;
	@FindBy(xpath = "//span[@class='report-request-label']/ancestor::h6")
	private List<WebElement> oTextRequestType;
	@FindBy(xpath = "//*[text()='Yes']/ancestor::button")
	private WebElement oButtonSearchProvider;

	@FindBy(xpath = "//eht-text-input//input")
	private WebElement oInputSpokeWith;
	@FindBy(xpath = "//eht-text-input//input[@placeholder='Days/Hours']")
	private WebElement oInputDaysHours;


	@FindBy(css = ".mat-radio-container input")
	private List<WebElement> oInputRcvdRqst;

	String oGenericElementByText = "//span[text()='$key']";

	@FindBy(xpath = "//button[@aria-label='Open calendar']")
	private List<WebElement> oButtonCalendar;
	@FindBy(css = "div.mat-calendar-body-today")
	private WebElement oTextCalendarDateToday;

	@FindBy(css = "em.icon_send")
	private WebElement oIconSend;
	
	@FindBy(css = ".note-text")
	private List<WebElement> oTextNote;

	@FindBy(xpath = "//*[@class='icon icon-32 nav_profile unav-icon']/ancestor::button")
	private WebElement oButtonProfile;
	@FindBy(xpath = "//a[text()='Sign out']")
	private WebElement oLinkSgnout;
	
	
	

	String oPath_SearchResultsSearcedText = "(//div[@role='listbox']//span[@class='mat-option-text'])[$index]//*[text()]";


	public HomePage() {
		super();
		PageFactory.initElements(super.driver, this);
	}


	public HomePage clickStartNow() {

		elementClickable(oButtonStartNow, "oButtonStartNow");
		oButtonStartNow.click();

		return this;
	}

	public HomePage closeExistingWindows() {

		try {
			new WebDriverWait(driver,7).until(ExpectedConditions.numberOfWindowsToBe(2));
		}catch(Exception e) {
			log().info("No extra windows present!");
		}
		String currentWindow = driver.getWindowHandle();

		Set<String> windows = driver.getWindowHandles();

		for(String window : windows) {

			if(!window.equals(currentWindow)) {
				driver.switchTo().window(window);
				driver.close();
				driver.switchTo().window(currentWindow);
			}
		}

		//		if(!oButtonCloseCard.isEmpty()) {
		//			for(WebElement e : oButtonCloseCard)
		//			{
		//				e.click();
		//				sleep(2);
		//			}
		//		}

		return this;
	}

	public HomePage closeCards() {


		if(oButtonCloseCard.size()>1) {
			for(WebElement e : oButtonCloseCard)
			{
				e.click();
				sleep(2);
				oButtonYes.click();
				sleep(2);

				closeExistingWindows();

				break;
			}
		}

		return this;
	}

	public HomePage searchCase(String searchText) {

		elementClickable(oInputSearchBox, "oInputSearchBox");
		oInputSearchBox.clear();
		oInputSearchBox.sendKeys(searchText);
		sleep(2);

		return this;
	}

	HashMap<String, String> patientDetails = new HashMap<String, String>();
	public HomePage openCard(int index) {

		String currentWindow = driver.getWindowHandle();

		elementClickable(oTextSearchResults.get(index), "oTextSearchResults");
		oTextSearchResults.get(index).click();

		try {
			new WebDriverWait(driver,7).until(ExpectedConditions.numberOfWindowsToBe(2));
		}catch(Exception e) {
			log().info("No extra windows present!");
		}


		Set<String> windows = driver.getWindowHandles();

		for(String window : windows) {

			if(!window.equals(currentWindow)) {
				driver.switchTo().window(window);
				driver.switchTo().window(currentWindow);
				break;
			}
		}

		List<WebElement> patientDetailsKey = driver.findElements(By.cssSelector("div h6"));
		List<WebElement> patientDetailsValue = driver.findElements(By.cssSelector("h5 b"));
		log().info("*************** Patient Details *********************");
		for(int i=0,j=0;j<patientDetailsValue.size();i++,j++) {

			log().info(patientDetailsKey.get(i).getAttribute("innerText")+" : "+patientDetailsValue.get(j).getAttribute("innerText"));
			patientDetails.put(patientDetailsKey.get(i).getAttribute("innerText"), patientDetailsValue.get(j).getAttribute("innerText"));
			if(i==0)
				i++;
		}

		return this;
	}

	public HomePage selectAndUpdatePendingLocation(String statusToBeSelected, String spokePerson, boolean rcvdRqst, String status, long days_hours) {


		for(WebElement available_status : oTextCaseStatus) {
			if(available_status.getAttribute("innerText").equalsIgnoreCase(statusToBeSelected))
			{
				available_status.click();
				break;
			}
		}


		List<WebElement> contactDetailsKey = driver.findElements(By.cssSelector("eht-label label"));
		List<WebElement> contactDetailsValue = driver.findElements(By.cssSelector("span.label-value"));
		List<WebElement> contactValue = driver.findElements(By.cssSelector("h3.label-value"));
		
		
		log().info("*************** Provider Details *********************");
		for(int i=0,j=0;j<6;i++,j++) {

			if(i==0)
				{
				log().info(contactDetailsKey.get(i).getAttribute("innerText")+" : "+contactValue.get(j).getAttribute("innerText"));
				j=0;
				}
			else
				log().info(contactDetailsKey.get(i).getAttribute("innerText")+" : "+contactDetailsValue.get(j).getAttribute("innerText"));
			//			patientDetails.put(patientDetailsKey.get(i).getAttribute("innerText"), patientDetailsValue.get(j).getAttribute("innerText"));
			
			

		}
		
		//Process Request
		oInputSpokeWith.sendKeys(spokePerson);

		if(rcvdRqst)
			clickUsingJavascriptExecutor(oInputRcvdRqst.get(0));
		else
			clickUsingJavascriptExecutor(oInputRcvdRqst.get(1));

		sleep(2);
		driver.findElement(By.xpath(oGenericElementByText.replace("$key", "Select"))).click();
		sleep(2);
		driver.findElement(By.xpath(oGenericElementByText.replace("$key", status))).click();
		sleep(2);

		oButtonCalendar.get(0).click();
		sleep(2);
		oTextCalendarDateToday.click();

		oInputDaysHours.sendKeys(String.valueOf(days_hours));

		oIconSend.click();

		//		driver.findElement(By.xpath(oGenericElementByText.replace("$key", "Next"))).click();
		sleep(2);

//		log().info(oTextNote.get(oTextNote.size()-1).getText());
//		log().info(status.trim().toLowerCase());
		Assert.assertTrue((oTextNote.get(oTextNote.size()-1).getText().contains(status.trim().toLowerCase())), "notes assertion failed!!!");

		return this;
	}
	
	public HomePage verifyCard() {

		elementClickable(oTextSearchResults.get(0), "oTextSearchResults");
		oTextSearchResults.get(0).click();



		return this;
	}

	public HomePage verifyCardSecondWindow() {

		try {
			new WebDriverWait(driver,7).until(ExpectedConditions.numberOfWindowsToBe(2));
		}catch(Exception e) {
			log().info("No extra windows present!");
		}

		String currentWindow = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();

		for(String window : windows) {

			if(!window.equals(currentWindow)) {
				driver.switchTo().window(window);
			}
		}

		Assert.assertTrue(!oTabRequestedItems.isEmpty());
		Assert.assertTrue(!oTabFiles.isEmpty());
		Assert.assertTrue(!oTabProviderDetails.isEmpty());

		//Requested Items
		log().info("*************** Requested Items *********************");
		log().info("AvailableRequestedItems: "+oTextAvailableRequestedItems.get(0).getAttribute("innerText"));

		for(int i=1;i<=oRowRequestedItems.size();i++) {

			log().info("__________________ "+i);
			List<WebElement> columns = driver.findElements(By.xpath(oPath_SoColRequestedItems.replace("$index", String.valueOf(i))));

			String requestType = "";
			String desc = "";
			String dataRange = "";

			for(int j=0; j<columns.size()-1; j++) {

				if(j==0)
					requestType = columns.get(j).getAttribute("innerText");
				if(j==1)
					desc = columns.get(j).getAttribute("innerText");
				if(j==2)
					dataRange = columns.get(j).getAttribute("innerText");
			}

			log().info("requestType: "+requestType);
			log().info("desc: "+desc);
			log().info("dataRange: "+dataRange);

		}

		log().info("*************** Files *********************");
		oTabFiles.get(0).click();
		Assert.assertEquals(oText_FilesPatientNameAndDOB.get(0).getAttribute("innerText"), patientDetails.get("PATIENT NAME"));
		Assert.assertEquals(oText_FilesPatientNameAndDOB.get(1).getAttribute("innerText"), patientDetails.get("DOB"));
		sleep(5);
		log().info("*************** File Items *********************");
		for(WebElement file:oTextFile) {
			log().info(file.getAttribute("innerText"));
			file.click();
			sleep(5);
			if("active-file".contains(file.getAttribute("class")))
				Assert.assertTrue(oViewerPDF.get(0).getCssValue("display").equalsIgnoreCase("block"));
		}
		
		log().info("*************** ProviderDetails Tab *********************");
		oTabProviderDetails.get(0).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("div h4.sub-heading")).isDisplayed());
		log().info("ProviderDetails Tab Header validation successful.");

		driver.close();
		driver.switchTo().window(currentWindow);

		return this;
	}
	
	public LoginPage signout() {
		
		oButtonProfile.click();
		oLinkSgnout.click();
		return new LoginPage();
	}

	public HomePage verifySearchResults(String searchText) {

		SoftAssert sofa = new SoftAssert();

		int searchResultIndexStart = 0;

		elementClickable(oTextSearchResults.get(0), "oTextSearchResults");
		log().info("++++++ "+oTextSearchResults.size());

		List<WebElement> searchResultsCount = driver.findElements(By.xpath("(//div[@role='listbox']//span[@class='mat-option-text'])"));

		for(int i=1;i<searchResultsCount.size() && i<30;i++) {

			scrollToViewUsingJavascriptExecutor(oTextSearchResults.get(i), "oTextSearchResults");

			List<WebElement> SearchResults = driver.findElements(By.xpath(oPath_SearchResultsSearcedText.replace("$index", String.valueOf(i))));

			String patientname = "";
			String patientdetails = "";

			for(int j=0; j<SearchResults.size(); j++) {

				if(j==0)
					patientname = SearchResults.get(j).getText();
				else
					patientdetails = patientdetails + SearchResults.get(j).getText();
			}

			log().info("**********************************");
			log().info("Patient count: "+i);
			log().info("patientname: "+patientname);
			log().info("patientdetails: "+patientdetails);

			String[] search_text = searchText.split(" ");

			boolean patientNameCheck = false;
			for(int k=0;k<search_text.length;k++) {
				if(search_text[k].contains(patientname))
				{
					patientNameCheck = true;
					sofa.assertTrue(patientNameCheck, "patientname: "+patientname+" validation failed!");
				}
			}


			if(i==oTextSearchResults.size()-1 && !oLinkViewMore.isEmpty()) {
				oLinkViewMore.get(0).click();
				sleep(5);
			}

			searchResultsCount = driver.findElements(By.xpath("(//div[@role='listbox']//span[@class='mat-option-text'])"));
			log().info("++++++++++++ "+searchResultsCount.size());
		}
		sofa.assertAll();
		return this;
	}
}
