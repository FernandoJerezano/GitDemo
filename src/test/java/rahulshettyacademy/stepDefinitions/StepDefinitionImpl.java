package rahulshettyacademy.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest {
	
	public LandingPage landinPage;
	ProductCatalogue productCatalogue;
	ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) {
		productCatalogue = landingPage.loginApplication(username, password);
	}
	
	@When("^When I add the product (.+) to Cart$")
	public void I_add_the_product_to_Cart(String productName) throws InterruptedException {
		productCatalogue.addProductToCart(productName);
	}
	
	@And("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String product) {
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(product);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String message) {
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(message));
	}
	
	
	@Then("{string} message is displayed")
	public void something_message_is_displayed(String strArgs1) throws Exception {
		Assert.assertEquals(strArgs1, landingPage.getErrorMessage());
	}
	
}
