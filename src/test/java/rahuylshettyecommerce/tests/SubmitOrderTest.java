package rahuylshettyecommerce.tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import rahuylshettyecommerce.pageobjects.CartPage;
import rahuylshettyecommerce.pageobjects.CheckoutPage;
import rahuylshettyecommerce.pageobjects.ConfirmationOderPage;
import rahuylshettyecommerce.pageobjects.LoginPage;
import rahuylshettyecommerce.pageobjects.OrdersPage;
import rahuylshettyecommerce.pageobjects.ProductCatalogue;
import rahuylshettyecommerce.testcomponenent.BaseTest;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test (dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {

		ProductCatalogue productcatalogue = loginPage.productLogin(input.get("email"), input.get("password"));
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productcatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cartPage.goToCheckout();
		checkoutpage.selectCountry("india");
		ConfirmationOderPage confirmationOrderPage = checkoutpage.submitOrder();

		// place order page
		String confirmmessage = confirmationOrderPage.getConfimationMessage();
		Assert.assertTrue(confirmmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER"));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest() {

		ProductCatalogue productcatalogue = loginPage.productLogin("anshika@gmail.com", "Iamking@000");
		OrdersPage ordersPage = productcatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
	}
	
	public String getScreenShot(String testCaseName) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source= ts.getScreenshotAs(OutputType.FILE);
		File dest= new File(System.getProperty("usr.dir")+ "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, dest);
		return System.getProperty("usr.dir")+ "//reports//" + testCaseName + ".png";
	}

	@DataProvider
	public Object[][] getData() throws IOException {

//		HashMap<String, String> map= new HashMap<String, String>();
//		map.put("email", "anshika@gmail.com");
//		map.put("password", "Iamking@000");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String, String> map1= new HashMap<String, String>();
//		map1.put("email", "shetty@gmail.com");
//		map1.put("password", "Iamking@000");
//		map1.put("product", "ADIDAS ORIGINAL");
		
		List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+
				"//src//test//java//rahuylshettyecommerce//data//PurchaseOrder.json");
		
		return new Object[][] { { data.get(0) },{ data.get(1) } };

	}

}
