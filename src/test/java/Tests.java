import drivers.DriverSingleton;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import pages.CheckOutPage;
import pages.HomePage;
import pages.SignInPage;
import utils.Constants;
import utils.FrameworkProperties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tests {

	static FrameworkProperties frameworkProperties;
	static WebDriver driver;
	static HomePage homePage;
	static SignInPage signInPage;
	static CheckOutPage checkOutPage;

	@BeforeClass
	public static void initializeObjects() {
		frameworkProperties = new FrameworkProperties();
		DriverSingleton.getInstance(frameworkProperties.getProperty(Constants.BROWSER));
		driver = DriverSingleton.getDriver();
		homePage = new HomePage();
		signInPage = new SignInPage();
		checkOutPage = new CheckOutPage();


	}

	@Test
	public void test01TestingAuthentication(){
		driver.get(Constants.URL);
		homePage.clickSignIn();
		signInPage.login(frameworkProperties.getProperty(Constants.EMAIL), frameworkProperties.getProperty(Constants.PASSWORD));
		assertEquals(frameworkProperties.getProperty(Constants.USERNAME), homePage.getUsername());

	}

	@Test
	public void test02TestingAddingThingsToCart() {
		driver.get(Constants.URL);
		homePage.addFirstElementToCart();
		homePage.addSecondElementToCart();
		assertEquals(Constants.CART_QUANTITY_TEXT, checkOutPage.summaryProducts());
	}

	@Test
	public void test03TestingTheFullBuyingProcess(){
		driver.get(Constants.URL);
		homePage.addFirstElementToCart();
		homePage.addSecondElementToCart();
		checkOutPage.goToCheckout();
		checkOutPage.goToConfirmAddress();
		checkOutPage.confirmShippingCheckBox();
		checkOutPage.payByBankWire();
		checkOutPage.confirmFinalOrder();
		assertTrue(checkOutPage.checkFinalStatus());





	}



	@AfterClass
	public static void closeObject(){
		driver.close();

	}



}
