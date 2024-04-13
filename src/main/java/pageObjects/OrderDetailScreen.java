package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusableComponets.AbstarctComponents;

public class OrderDetailScreen extends AbstarctComponents {
	
	public WebDriver driver;
	
	public OrderDetailScreen(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css = ".hero-primary")
	public WebElement confirmMessage;
	
	public String get_Text_Present_On_Order_Details_Screen()
	{
		return confirmMessage.getText();
	}
	
	@FindBy(css= "label.ng-star-inserted")
	public WebElement orderId;
	
	
	public String getOrderId()
	{
		waitForVisibilityOfElement(orderId);
		return orderId.getText().split("\\|")[1].trim();
	}
	
	//String text = driver.findElement(By.cssSelector(".hero-primary")).getText();

}
