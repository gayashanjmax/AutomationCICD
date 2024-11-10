package gayashanhub.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import gayashanhub.TestComponents.BaseTest;
import gayashanhub.pageobjects.CartPage;
import gayashanhub.pageobjects.CheckoutPage;
import gayashanhub.pageobjects.ProductCatalog;
import gayashanhub.pageobjects.ThankYouPage;

public class ErrorValidationTest extends BaseTest{

	@Test
	public void errorPageVlidation() throws IOException
	{
		String errorMessage = "Incorrect email or password.";
		
		landingPage.loginApplication("gayashan@test.com", "InvalidPWD");
		Assert.assertEquals(errorMessage, landingPage.getErrorMessage());	
	}
	
	@Test(groups= {"ErrorHandling"})//add "retryAnalyzer=Retry.class" for each of the Flacky tests
	public void productPageValidation() throws IOException
	{
		String product1 = "ADIDAS ORIGINAL";
		
		//LandingPage landingPage = launchApplication(); // the BeforeMethod anotation is given for launchApplication(). hence this method is no longer required
		ProductCatalog productCatalog = landingPage.loginApplication("gayashan@test.com", "Test@1234");
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(product1);
		CartPage cartPage = productCatalog.goToCartPage();
		Boolean match = cartPage.matchSelectedProducts("ADIDAS ORIGINALError");
		Assert.assertFalse(match);
	}

}
