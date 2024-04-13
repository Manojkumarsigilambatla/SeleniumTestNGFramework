package pageObjects;

import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusableComponets.AbstarctComponents;

public class CartScreen extends AbstarctComponents{
	
	
	public WebDriver driver;
	
	public CartScreen(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	
	}
	
	
	
	
	@FindBy(css=".cartSection>h3")
	public List<WebElement> cartProductsList;
	
	@FindBy(css=".totalRow>.btn-primary")
	public WebElement checkOutbutton;
	
	
	
	public List<WebElement> getProductList()
	{
		
		return cartProductsList;
	}

	public boolean Verify_Whether_The_Product_Added_Is_Present_In_CartScreen(String productName)
	{
		
		boolean value = getProductList().stream().anyMatch(element -> element.getText().equals(productName));
		return value;
	}
	
	public CheckOutScreen openCheckOutScreen()
	{
		checkOutbutton.click();
		return new CheckOutScreen(driver);
	}
	
	
	// driver.findElement(By.cssSelector("[routerlink='/dashboard/cart']")).click();
	
	//List<WebElement> cartProductsList = driver.findElements(By.cssSelector(".cartSection>h3"));
}
