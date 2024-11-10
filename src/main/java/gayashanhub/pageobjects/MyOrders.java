package gayashanhub.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gayashanhub.abstractcomponents.AbastractComponent;

public class MyOrders extends AbastractComponent {
	
	WebDriver driver;

	public MyOrders(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> orderList;
	
	public Boolean getOrderName(String productName)
	{
		Boolean match = orderList.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return match;
	}

}
