package gayashanhub.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import gayashanhub.pageobjects.CartPage;
import gayashanhub.pageobjects.MyOrders;

public class AbastractComponent {
	
	WebDriver driver;
	
	public AbastractComponent(WebDriver d) {
		
		this.driver=d;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(css="button[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css = "button[routerlink*='myorders']")
	WebElement orderHeader;

	public void waitElementToBeAppeared(By find)
	{
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(10));
		w.until(ExpectedConditions.visibilityOfElementLocated(find));
	}
	
	public void waitWebElementToBeAppeared(WebElement find)
	{
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(10));
		w.until(ExpectedConditions.invisibilityOf(find));
	}
	
	public void waitElementToBeDisappeared(WebElement ele)
	{
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(10));
		w.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public CartPage goToCartPage()
	{
		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public MyOrders gotoOrderPage()
	{
		orderHeader.click();
		MyOrders myOrders = new MyOrders(driver);
		return myOrders;
	}

}
