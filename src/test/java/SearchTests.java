import drivers.DriverSingleton;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.CheckOutPage;
import pages.HomePage;
import pages.SignInPage;
import utils.Constants;
import utils.FrameworkProperties;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SearchTests {


	static FrameworkProperties frameworkProperties;
	static WebDriver driver;
	static HomePage homePage;
	static SignInPage signInPage;
	static CheckOutPage checkOutPage;
	static String inputString;
	static Boolean expectedResults;


	@BeforeClass
	public static void initializeObjects() {
		frameworkProperties = new FrameworkProperties();
		DriverSingleton.getInstance(frameworkProperties.getProperty(Constants.BROWSER));
		driver = DriverSingleton.getDriver();
		homePage = new HomePage();
		signInPage = new SignInPage();
		checkOutPage = new CheckOutPage();


	}


	public SearchTests(String inputString, boolean expectedResults) {

		this.inputString = inputString;
		this.expectedResults = expectedResults;
	}

	@Parameterized.Parameters
	public static Collection searchOptions() {
		return Arrays.asList(new Object[][] {
				{ "Shirt", true },
				{ "Blouse", true },
				{ "Dress", true },
				{ "", false },
				{ "test", true }
		});
	}

	@Test
	public void testingSearch(){
		driver.get(Constants.URL);
		assertEquals(expectedResults, homePage.searchElement(inputString));

	}

	@AfterClass
	public static void closeObject(){
		driver.close();
	}

}
