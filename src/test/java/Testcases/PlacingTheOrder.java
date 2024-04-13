package Testcases;

import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CartScreen;
import pageObjects.CheckOutScreen;
import pageObjects.DashboardScreen;
import pageObjects.OrderDetailScreen;
import pageObjects.OrderHistoryScreen;
import testConfigurations.BaseTest;

public class PlacingTheOrder extends BaseTest{
	 
	public String orderId;
	
	
	
	
    @Test(dataProvider = "dataSet")
	public void submitOrder(HashMap<String,String> map) throws FileNotFoundException, IOException, InterruptedException
	{
		// TODO Auto-generated method stub

		//String Productname = "IPHONE 13 PRO";
		
		DashboardScreen dashboard = login.loginFunctionality(map.get("email"),map.get("password"));

		dashboard.addToCart(map.get("productName"));

		CartScreen cartScreen = dashboard.OpenCartScreen();
		boolean response = cartScreen.Verify_Whether_The_Product_Added_Is_Present_In_CartScreen(map.get("productName"));

		System.out.println(response);
		Assert.assertTrue(response);

		CheckOutScreen checkOutScreen = cartScreen.openCheckOutScreen();

		checkOutScreen.EnterAndSelectCountry("Aus");

		OrderDetailScreen orderDetailScreen = checkOutScreen.PlaceTheOrder();

		String reposneText = orderDetailScreen.get_Text_Present_On_Order_Details_Screen();

		Assert.assertTrue(reposneText.equalsIgnoreCase("Thankyou for the order."));
		
		orderId = orderDetailScreen.getOrderId();
		
		orderIdData.put(map.get("email"), orderId);

	}
    
    @Test(dependsOnMethods = {"submitOrder"},dataProvider = "dataSet")
    public void verifying_whether_the_created_order_is_present_in_The_order_screen(HashMap<String, String> map) throws InterruptedException 
    {
    	DashboardScreen dashboard = login.loginFunctionality(map.get("email"),map.get("password"));
    	OrderHistoryScreen orderHistoryScreen =dashboard.openOrdersHistoryScreen();
    	Boolean value = orderHistoryScreen.search_And_Confirm_Whether_The_PlacedOrder_Is_present_In_ordersHistory(orderIdData.get(map.get("email")));
    	assertTrue(value);
    }
    
    @DataProvider
    public Object[][] dataSet()
    {
    	List<HashMap<String, String>> listOfHashMaps = jsonExtractor();
    	
    	return new Object[][] {
    		
    		{listOfHashMaps.get(0)},
    		{listOfHashMaps.get(1)}
    	};
    	
    	
    }
//    
//    @DataProvider
//    public Object[][] dataSet()
//    {
//    	return new Object[][] {
//    		{"spyderman@gmail.com","Webshooter@1" ,"ADIDAS ORIGINAL"},
//    		{"robert@gmail.com","Robert@123","HONDA"}
//    		   
//    	};
//    }
    
//    @DataProvider
//    public Object[][] dataSet()
//    {
//    	HashMap<String,String> map1 = new HashMap<String, String>();
//    	map1.put("email", "spyderman@gmail.com");
//    	map1.put("password","Webshooter@1" );
//    	map1.put("productName","ADIDAS ORIGINAL");
//    	HashMap<String,String> map2 = new HashMap<String, String>();
//    	map2.put("email", "robert@gmail.com");
//    	map2.put("password","Robert@123" );
//    	map2.put("productName","HONDA");
//    	
//    	return new Object[][] {
//    		
//    		{map1},
//    		{map2}
//    	};
//    	
//    	
//    }

}
