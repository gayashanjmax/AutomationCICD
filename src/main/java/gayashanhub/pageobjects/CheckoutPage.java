package gayashanhub.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import gayashanhub.abstractcomponents.AbastractComponent;

public class CheckoutPage extends AbastractComponent{
	
	WebDriver driver;
	//Actions a = new Actions(driver);
	
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement enterCountry;
	
	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	
	@FindBy(css = ".action__submit")
	WebElement order;
	
	By countryList = By.cssSelector(".ta-results");
	
	
	public void placeOrder(String country)
	{
		Actions a = new Actions(driver);
		a.sendKeys(enterCountry,country).build().perform();
		waitElementToBeAppeared(countryList);
		selectCountry.click();	
	}
	
	public ThankYouPage submitOrder()
	{
		Actions a = new Actions(driver);
		WebElement placeOrder1 = order;
		a.moveToElement(placeOrder1).click().build().perform();
		
		ThankYouPage thankyouPage = new ThankYouPage(driver);
		return thankyouPage;
	}
	
	
	
	
}





