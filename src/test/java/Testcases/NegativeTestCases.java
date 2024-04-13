package Testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CartScreen;
import pageObjects.DashboardScreen;
import testConfigurations.BaseTest;
import testConfigurations.RetryFailedRun;

public class NegativeTestCases extends BaseTest {
	
	@Test(groups = {"NegativeLoginTest"},retryAnalyzer =RetryFailedRun.class )
	public void negativeLogin()
	{
		login.loginFunctionality("spyderman@gmail.com", "heyhello");
		String incorrectLoginMessage = login.get_Incorrect_Login_Toggle_Message();
		Assert.assertEquals(incorrectLoginMessage,"Incorrect email password.");
	}
	
	@Test
	public void orderNotpresentIntheScreen()
	{
        String Productname = "HONDA";
		
		DashboardScreen dashboard = login.loginFunctionality("robert@gmail.com", "Robert@123");

		dashboard.addToCart(Productname);

		CartScreen cartScreen = dashboard.OpenCartScreen();
		boolean response = cartScreen.Verify_Whether_The_Product_Added_Is_Present_In_CartScreen("Hunda");

		System.out.println(response);
		Assert.assertFalse(response);

	}

}
