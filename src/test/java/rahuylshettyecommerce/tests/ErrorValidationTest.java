package rahuylshettyecommerce.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import io.github.bonigarcia.wdm.WebDriverManager;

import rahuylshettyecommerce.pageobjects.CartPage;
import rahuylshettyecommerce.pageobjects.CheckoutPage;
import rahuylshettyecommerce.pageobjects.ConfirmationOderPage;
import rahuylshettyecommerce.pageobjects.LoginPage;
import rahuylshettyecommerce.pageobjects.ProductCatalogue;
import rahuylshettyecommerce.testcomponenent.BaseTest;

public class ErrorValidationTest extends BaseTest{
	
	@Test(groups={"ErrorHanling"}, retryAnalyzer= Retry.class)
	public void submitOrder() throws IOException, InterruptedException {
	String productName="ZARA COAT 3";
	
	ProductCatalogue productcatalogue=loginPage.productLogin("anshika@gmail.com", "Iamking@0001");
	Assert.assertEquals(loginPage.getErrorMessage(), "Incorrect email or password.");
	
	}
	
	@Test	
	public void productErrorValidation() throws IOException, InterruptedException {
	String productName="ZARA COAT 3";
	
	ProductCatalogue productcatalogue=loginPage.productLogin("anshika@gmail.com", "Iamking@000");
	
	List<WebElement> products=productcatalogue.getProductList();
	productcatalogue.addProductToCart(productName);
	
	CartPage cartPage=productcatalogue.goToCartPage();
	
	
	Boolean match=cartPage.verifyProductDisplay("Zara coat 33");
	Assert.assertFalse(match);
	
	
	}

}
