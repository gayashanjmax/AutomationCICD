package gayashanhub.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import gayashanhub.TestComponents.BaseTest;
import gayashanhub.pageobjects.CartPage;
import gayashanhub.pageobjects.CheckoutPage;
import gayashanhub.pageobjects.LandingPage;
import gayashanhub.pageobjects.ProductCatalog;
import gayashanhub.pageobjects.ThankYouPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImplementation extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalog productCatalog;
	public CartPage cartPage;
	public CheckoutPage checkoutPage;
	public ThankYouPage thankYouPage;
	
	/* Submit Order */
	
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		landingPage = launchApplication();	
	}
	
	@Given("^Logged in with username (.+) and password (.+)$") //use regex to get any username and password parameters from feature file
	public void Logged_in_with_username_and_password(String username,String password) //add parameters to catch the username and password
	{
		ProductCatalog productCatalog = landingPage.loginApplication(username, password);
	}
	
	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_Cart(String productName)
	{
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(productName);
	}
	
	@And("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName)
	{
		cartPage = productCatalog.goToCartPage();
		Boolean match = cartPage.matchSelectedProducts(productName);
		Assert.assertTrue(match);
		checkoutPage = cartPage.checkout();
		checkoutPage.placeOrder("India");
		ThankYouPage thankyouPage = checkoutPage.submitOrder();
	}
	
	@Then("{string} message is displayed in the Confirmation page") // add {} to get the confirmation message from the feature file
	public void message_is_displayed_in_the_Confirmation_page(String confirmatinMessage)
	{
		String orderText = thankYouPage.thankYouPageText();
		Assert.assertTrue(orderText.equalsIgnoreCase(confirmatinMessage));
		driver.close();
	}
	
	/* Error Handling */
	
	@Then("{string} message is displayed") // run error validations
	public void error_message_is_displayed(String errorMessage)
	{
		
		Assert.assertEquals(errorMessage, landingPage.getErrorMessage());
		driver.close();
	}
	

}
