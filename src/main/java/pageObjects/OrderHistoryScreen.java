package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderHistoryScreen {
	
	public WebDriver driver;
	public OrderHistoryScreen(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "tr.ng-star-inserted>th")
	public List<WebElement> listOfOrderId;
	
	
	public List<WebElement> getList()
	{
		
		return listOfOrderId;
	}
	
	public boolean search_And_Confirm_Whether_The_PlacedOrder_Is_present_In_ordersHistory(String orderID) throws InterruptedException
	{
		System.out.println(orderID);
		Thread.sleep(3000);
		Boolean response = getList().stream().anyMatch(s ->s.getText().equals(orderID));
		return response;
	}

}
