package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusableComponets.AbstarctComponents;

public class DashboardScreen extends AbstarctComponents{
	
	public WebDriver driver;
	
	public DashboardScreen(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	public List<WebElement> productList;
	
	@FindBy(css=".toast-message")
	public WebElement addedMessage;
	
	@FindBy(css= ".ng-animating")
	public WebElement loadingScroll;
	
	By textTag = By.cssSelector("b");
	
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	
	public List<WebElement> getProductList()
	{
		return productList;
	}
	
	public WebElement getProductFromProductList(String productName)
	{
		WebElement ele = productList.stream().filter(s->s.findElement(textTag).getText().contains(productName))
	    .findFirst().orElse(null);
		return ele;
	}
	
	public void addToCart(String productName)
	{
		WebElement ele =getProductFromProductList(productName).findElement(addToCart);
		
		Actions act = new Actions(driver);
		
		act.moveToElement(ele).click().build().perform();
		
		waitForVisibilityOfElement(addedMessage);
		
		waitForInvisibilityOfELement(loadingScroll);
		
		
	}
	
	
		    
		    //System.out.println(ele.getText());
		    //ele.findElement(By.xpath("//button[text()=' Add To Cart']")).click();
		    

}
