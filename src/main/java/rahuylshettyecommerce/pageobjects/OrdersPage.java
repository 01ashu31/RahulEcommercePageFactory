package rahuylshettyecommerce.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahuylshettyecommerce.abstractcomponent.AbstractComponent;

public class OrdersPage extends AbstractComponent{
	
	WebDriver driver;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productNames;
	

	public boolean verifyOrderDisplay(String productname) {

		Boolean match = productNames.stream().anyMatch( product-> product.getText().equalsIgnoreCase(productname));
		return match;
	}

	

}
