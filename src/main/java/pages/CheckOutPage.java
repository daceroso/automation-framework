package pages;

import drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Constants;

public class CheckOutPage {

	private WebDriver driver;

	public CheckOutPage() {
		driver = DriverSingleton.getDriver();
		PageFactory.initElements(driver, this);
	}


	@FindBy(id = "head > title")
	private WebElement pageTitle;


	@FindBy(css = "#center_column > p.cart_navigation.clearfix > a.button.btn.btn-default.standard-checkout.button-medium > span")
	private WebElement checkoutButtonSummary;


	@FindBy(css = "#center_column > form > p > button > span")
	private WebElement proceedButtonSummary;

	@FindBy(id = "cgv")
	private WebElement confirmShippingCheckBox;

	@FindBy(css = "#form > p > button > span")
	private WebElement checkoutButtonConfirmShipping;

	@FindBy(css = "#HOOK_PAYMENT > div:nth-child(1) > div > p > a")
	private WebElement payByBankWireOption;

	@FindBy(css = "#cart_navigation > button > span")
	private  WebElement confirmOrder;

	@FindBy(css = "#center_column > div > p > strong")
	private WebElement orderConfirmationMessage;



	public Boolean checkTitle(String title) {
		return pageTitle.getText().equals(title);
	}




	public Boolean checkOrderMessage(String orderMessage) {
		return orderConfirmationMessage.getText().equals(orderMessage);
	}

	public void goToCheckout() {
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT);
		wait.until(ExpectedConditions.elementToBeClickable(checkoutButtonSummary));

		checkoutButtonSummary.click();

	}

	public void goToConfirmAddress() {
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT);
		wait.until(ExpectedConditions.elementToBeClickable(proceedButtonSummary));

		proceedButtonSummary.click();

	}

	public void confirmShippingCheckBox() {
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT);
		wait.until(ExpectedConditions.elementToBeClickable(confirmShippingCheckBox));

		confirmShippingCheckBox.click();
		checkoutButtonConfirmShipping.click();

	}

	public void payByBankWire() {
		WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT);
		wait.until(ExpectedConditions.elementToBeClickable(payByBankWireOption));

		payByBankWireOption.click();
	}















}
