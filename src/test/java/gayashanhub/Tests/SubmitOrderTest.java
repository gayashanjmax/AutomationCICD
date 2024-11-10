package gayashanhub.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gayashanhub.TestComponents.BaseTest;
import gayashanhub.pageobjects.CartPage;
import gayashanhub.pageobjects.CheckoutPage;
import gayashanhub.pageobjects.LandingPage;
import gayashanhub.pageobjects.MyOrders;
import gayashanhub.pageobjects.ProductCatalog;
import gayashanhub.pageobjects.ThankYouPage;

public class SubmitOrderTest extends BaseTest{
	
	//String product1 = "ADIDAS ORIGINAL";

	@Test(dataProvider= "getData",groups = "Purchase")
	public void submitOrder(HashMap<String,String> input) throws IOException
	{
		String text = "Thankyou for the order.";
		String country = "India";
		//LandingPage landingPage = launchApplication(); // the BeforeMethod anotation is given for launchApplication(). hence this method is no longer required
		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalog.goToCartPage();
		Boolean match = cartPage.matchSelectedProducts(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.checkout();
		checkoutPage.placeOrder(country);
		ThankYouPage thankyouPage = checkoutPage.submitOrder();
		String orderText = thankyouPage.thankYouPageText();
		Assert.assertTrue(orderText.equalsIgnoreCase("Thankyou for the order."));
	}
	
	//Verify the product name in the order
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest()
	{
		ProductCatalog productCatalog = landingPage.loginApplication("gayashan@test.com", "Test@1234");
		MyOrders myOrders = productCatalog.gotoOrderPage();
		Assert.assertTrue(myOrders.getOrderName("ADIDAS ORIGINAL"));
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		//call the method written in the base class
		List<HashMap<String,String>> data = getJasonDatatoMap(System.getProperty("user.dir")+"\\src\\test\\java\\gayashanhub\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	

	
	
	/*@DataProvider
	public Object[][] getData()
	{
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", "gayashan@test.com");
		map.put("password", "Test@1234");
		map.put("product", "ADIDAS ORIGINAL");
		
		HashMap<String,String> map2 = new HashMap<String,String>();
		map2.put("email", "gayashan@test.com");
		map2.put("password", "Test@1234");
		map2.put("product", "ADIDAS ORIGINAL");
		
		return new Object[][] {{map},{map2}};
		*/
	
	
	/*@DataProvider
	public Object[][] getData()
	{
		return new Object[][] {{"gayashan@test.com","Test@1234","ADIDAS ORIGINAL"} , {"shetty@gmail.com","Iamking@000","IPHONE 13 PRO"}}; // in Object variable we can declare any data type
		
	} */

}
