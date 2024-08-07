package rahuylshettyecommerce.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahuylshettyecommerce.abstractcomponent.AbstractComponent;

import org.openqa.selenium.WebElement;

public class LoginPage extends AbstractComponent{

	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}



      @FindBy(id= "userEmail")
      WebElement userEmailId;
      
      @FindBy(id="userPassword")
      WebElement userPassword;
      
      @FindBy(id="login")
      WebElement loginButton;
      
      @FindBy(css="[class*='flyInOut']")
      WebElement errorMessage;
      
      
      public ProductCatalogue productLogin(String email, String password) {
    	  userEmailId.sendKeys(email);
    	  userPassword.sendKeys(password);
    	  loginButton.click();
    	  ProductCatalogue productCatalogue= new ProductCatalogue(driver);
    	  return productCatalogue;
      }
      
      public String getErrorMessage() {
    	  waitForWebElementToAppear(errorMessage);
    	  return errorMessage.getText();
      }
      
      public void goTo() {
    	  driver.get("https://rahulshettyacademy.com/client");
      }
      
      
      
      
      
      
      
}