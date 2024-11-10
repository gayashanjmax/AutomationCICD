package gayashanhub.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gayashanhub.abstractcomponents.AbastractComponent;

public class ThankYouPage extends AbastractComponent{
	
	WebDriver driver;
	
	public ThankYouPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".hero-primary")
	WebElement orderText;
	
	public String thankYouPageText()
	{
		return orderText.getText();
		
	}

}
