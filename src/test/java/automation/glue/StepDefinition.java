package automation.glue;

import automation.config.AutomationFrameworkConfiguration;
import automation.drivers.DriverSingleton;
import automation.pages.CheckOutPage;
import automation.pages.HomePage;
import automation.pages.SignInPage;
import automation.utils.*;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(classes = AutomationFrameworkConfiguration.class)
public class StepDefinition {

	private WebDriver driver;
	private HomePage homePage;
	private SignInPage signInPage;
	private CheckOutPage checkOutPage;
	ExtentTest test;
	static ExtentReports report = new ExtentReports("report/TestReport.html");


	@Autowired
	ConfigurationProperties configurationProperties;

	@Before
	public void initializeObject() {
		DriverSingleton.getInstance(configurationProperties.getBrowser());
		homePage = new HomePage();
		signInPage = new SignInPage();
		checkOutPage = new CheckOutPage();
		TestCases[] tests = TestCases.values();
		test = report.startTest(tests[Utils.testCount].getTestName());
		Log.getLogData(Log.class.getName());
		Log.startTest(tests[Utils.testCount].getTestName());
		Log.endTest(tests[Utils.testCount].getTestName());

		Utils.testCount++;


	}

	@Given("^I go to the Website")
	public void i_go_to_the_website() {
		driver = DriverSingleton.getDriver();
		driver.get(Constants.URL);
		Log.info("Navigating to " + Constants.URL);
		test.log(LogStatus.PASS, "Navigation to " + Constants.URL);

	}

	@When("^I click on Sign In button")
	public void i_click_on_sign_in_button() {
		homePage.clickSignIn();
		Log.info("Sign in button has been clicked");
		test.log(LogStatus.PASS, "Sign in button has been clicked");

	}

	@When("^I add two elements to the cart")
	public void i_add_two_elements_to_cart() {
		homePage.addFirstElementToCart();
		homePage.addSecondElementToCart();
		test.log(LogStatus.PASS, "Two elements were added to the cart");

	}

	@And("^I specify my credentials and click Login")
	public void i_specify_my_credentials_and_click_login() {
		signInPage.login(configurationProperties.getEmail(), configurationProperties.getPassword());
		test.log(LogStatus.PASS, "Login has been clicked");

	}

	@And("^I proceed to checkout")
	public void i_proceed_to_checkout() {
		checkOutPage.goToCheckout();
		test.log(LogStatus.PASS, "We proceed to checkout");
	}

	@And("^I confirm address, shipping and final order")
	public void i_confirm_address_shipping_and_final_order() {
		checkOutPage.goToConfirmAddress();
		checkOutPage.confirmShippingCheckBox();
		checkOutPage.payByBankWire();
		checkOutPage.confirmFinalOrder();
		test.log(LogStatus.PASS, "We confirm the final order");

	}


	@Then("^I can log into the website")
	public void i_can_log_into_the_website() {
		if (configurationProperties.getUsername().equals(homePage.getUserName())) {

			Log.info("The authentication is sucessful");
			test.log(LogStatus.PASS, "The authentication is successful");
		} else {
			Log.error("Authentication is not successful");
			test.log(LogStatus.FAIL, "Authentication is not successful");
		}

		assertEquals(configurationProperties.getUsername(), homePage.getUserName());
	}


	@Then("^The elements are bought$")
	public void the_elements_are_bought() {

		if (checkOutPage.checkFinalStatus())
			test.log(LogStatus.PASS, "The two items are bought");
		else
			test.log(LogStatus.FAIL, "The items weren't bought");

		assertTrue(checkOutPage.checkFinalStatus());
	}

	@After
	public void closeObject() {
		report.endTest(test);
		report.flush();
		DriverSingleton.closeObjectInstance();
		Log.info("Browser Closed");
	}
}
