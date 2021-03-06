import drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import pages.CheckOutPage;
import pages.HomePage;
import pages.SignInPage;
import utils.Constants;
import utils.FrameworkProperties;

public class Main {
	public static void main(String[] args) {
		FrameworkProperties frameworkProperties = new FrameworkProperties();
		DriverSingleton.getInstance(frameworkProperties.getProperty("browser"));
		WebDriver driver = DriverSingleton.getDriver();
		driver.get("http://automationpractice.com");
		HomePage homePage = new HomePage();
		homePage.addFirstElementToCart();
		homePage.addSecondElementToCart();


		CheckOutPage checkout = new CheckOutPage();
		checkout.goToCheckout();


		SignInPage signInPage = new SignInPage();
		signInPage.login(frameworkProperties.getProperty(Constants.EMAIL), frameworkProperties.getProperty(Constants.PASSWORD));

		checkout.goToConfirmAddress();
		checkout.confirmShippingCheckBox();
		checkout.payByBankWire();
		checkout.confirmFinalOrder();
		if(
		checkout.checkFinalStatus())
			System.out.println("Test case completed");



	}
}