package gayashanhub.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gayashanhub.abstractcomponents.AbastractComponent;

public class ProductCatalog extends AbastractComponent {

	WebDriver driver;
	
	public ProductCatalog(WebDriver D)
	{
		super(D);
		this.driver=D;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList()
	{
		waitElementToBeAppeared(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement product = getProductList().stream().filter(s->
		s.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		return product;
	}
	
	public void addProductToCart(String productName)
	{
		WebElement product = getProductByName(productName);
		product.findElement(addToCart).click();
		waitElementToBeAppeared(toastMessage);
		waitElementToBeDisappeared(spinner);
	}
	
}
