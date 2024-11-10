package gayashanhub.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gayashanhub.abstractcomponents.AbastractComponent;

public class LandingPage extends AbastractComponent{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		//In PageFactory design pattern we can use initElements method to initialize all the elements
		
	}
	//WebElement userEmail = driver.findElement(By.id("userEmail")); == Instead of this, we can use pagefacotry design pattern to reduce the syntaxes
	//Here we can use FindBy annotation to store the locator and value, and in runtime,it will construct the above and store in the variable
	//PageFactory
	@FindBy(id="userEmail") 
	WebElement userEmail;

	@FindBy(id="userPassword") 
	WebElement password;
	
	@FindBy(id="login") 
	WebElement loginButton;
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;
	
	//Action method
	public ProductCatalog loginApplication(String email, String pwd)
	{
		userEmail.sendKeys(email);
		password.sendKeys(pwd);
		loginButton.click();
		
		ProductCatalog productCatalog = new ProductCatalog(driver);
		return productCatalog;
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage()
	{
		waitWebElementToBeAppeared(errorMessage);
		return errorMessage.getText();
	}
	
}
