package reusableComponets;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.CartScreen;
import pageObjects.OrderHistoryScreen;

public class AbstarctComponents {
	
	public WebDriver driver;
	public Actions action;
	
	public AbstarctComponents(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[routerlink='/dashboard/cart']")
	public WebElement goToCart;

	public CartScreen OpenCartScreen()
	{
		moveToElementAndClickOnElement(goToCart);
		return new CartScreen(driver);
	}

	@FindBy(css = "[routerlink*='myorders']")
	public WebElement goToorders;
	
	public OrderHistoryScreen openOrdersHistoryScreen()
	{
		moveToElementAndClickOnElement(goToorders);
		
		return new OrderHistoryScreen(driver);
		
	}
	
	public void waitForVisibilityOfElement(WebElement addedMessage)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(addedMessage));
	}
	
	public void waitForInvisibilityOfELement(WebElement loadingScroll)
	{
		 WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		 wait.until(ExpectedConditions.invisibilityOf(loadingScroll));
	}
	
	
	public void moveToElementAndClickOnElement(WebElement element)
	{
		if(action==null)
		{
			action = new Actions(driver);
		}
		
		action.moveToElement(element).click().build().perform();
	}
    //wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".toast-message"))));
    
   
    

}
