package cucumber.stepdefinitions;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CartScreen;
import pageObjects.CheckOutScreen;
import pageObjects.DashboardScreen;
import pageObjects.OrderDetailScreen;
import testConfigurations.BaseTest;

public class PlacingTheOrder extends BaseTest {
	
	public DashboardScreen dashboard;
	public CartScreen cartScreen;
	public CheckOutScreen checkOutScreen;
	public OrderDetailScreen orderDetailScreen;
	
	@Given("User should launch the browser with website")
	public void User_should_launch_the_browser_with_website() throws FileNotFoundException, IOException
	{
		launchApplication();
	}
	
	@Given("^user need to login with valid credentials (.+) and (.+)$")
	public void login(String userName, String password)
	{
		System.out.println(userName +" "+ password);
		dashboard = login.loginFunctionality(userName, password);
	}
	
	@When("User need to select the (.+) and add it to the cart.$")
	public void User_need_to_select_the_product_and_add_it_to_the_cart(String productName)
	{
		dashboard.addToCart(productName);
		cartScreen = dashboard.OpenCartScreen();
		boolean response = cartScreen.Verify_Whether_The_Product_Added_Is_Present_In_CartScreen(productName);
		Assert.assertTrue(response);
	}
	
	@When("user need to checkout the product.")
	public void user_need_to_checkout_the_product() throws InterruptedException
	{
		checkOutScreen = cartScreen.openCheckOutScreen();

		checkOutScreen.EnterAndSelectCountry("Aus");

		orderDetailScreen = checkOutScreen.PlaceTheOrder();
	}
	
	@Then("verify {string} is message is present on order confirmation page.")
	public void verify_Text_is_message_is_present_on_order_confirmation_page(String message)
	{
		String response = orderDetailScreen.get_Text_Present_On_Order_Details_Screen();
		
		Assert.assertTrue(response.equalsIgnoreCase(message));
		
		driver.quit();
	}
	

}