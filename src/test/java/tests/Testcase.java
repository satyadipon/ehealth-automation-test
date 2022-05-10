package tests;

import java.util.Hashtable;

import org.testng.annotations.Test;

import pages.HomePage;
import pages.LandingPage;
import setup.TestSetup;
import setup.Utils;

public class Testcase extends TestSetup{

	final String email = "qatest.1@ehealthcorp.net";
	final String password = "Welcome1";

	static boolean isLoggedIn = true;

	@Test(priority=0, description = "Verify Search Results")
	public void verifySearchResults() {

		if(isLoggedIn) {
			new LandingPage().gotoLoginPage(email)
			.login(email, password)
			.clickStartNow()
			.closeExistingWindows();

			isLoggedIn = false;
		}

		new HomePage().closeCards()
		.searchCase("SHREE TEST")
		.verifySearchResults("SHREE TEST");

	}


	@Test(priority=1, description = "Approve pending cards", dataProvider="data_provider", dataProviderClass = Utils.class)
	public void processCards(Hashtable<String,String> data) {

		if(isLoggedIn) {
			new LandingPage().gotoLoginPage(email)
			.login(email, password)
			.clickStartNow()
			.closeExistingWindows();

			isLoggedIn = false;
		}

		new HomePage().closeCards()
		.searchCase(data.get("searchtext"))
		.openCard(Integer.parseInt(data.get("searchResultIndex")))
		.selectAndUpdatePendingLocation(data.get("statusToBeSelected"), data.get("spokePerson"), Boolean.parseBoolean(data.get("rcvdRqst")), " "+data.get("status")+" ", Long.parseLong(data.get("days_hours")))
		.verifyCardSecondWindow();

	}

}
