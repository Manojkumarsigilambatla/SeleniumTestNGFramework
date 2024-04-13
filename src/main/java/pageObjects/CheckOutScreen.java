package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutScreen {
	
	public WebDriver driver;
	
	public CheckOutScreen(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	
	@FindBy(css = ".form-group>.input")
	public WebElement countrySelectTextBox;
	
	@FindBy(css=".ta-results>button>span")
	public List<WebElement> countryList;
	
	@FindBy(css = ".actions .icon")
	public WebElement placeOrderButton;
	
	
	
	
	public void EnterAndSelectCountry(String countryName)
	{
		countrySelectTextBox.sendKeys(countryName);
		
		WebElement country =countryList.stream().filter(ele -> ele.getText().equals("Australia")).findFirst().orElse(null);
		country.click();
	}
	
	public OrderDetailScreen PlaceTheOrder() throws InterruptedException
	{
		Actions act = new Actions(driver);
		
		act.moveToElement(placeOrderButton).click().build().perform();
		
		return new OrderDetailScreen(driver);
	}
	
	
	
	
	
	
	//driver.findElement(By.cssSelector(".totalRow>.btn-primary")).click();
	
	//driver.findElement(By.cssSelector(".form-group>.input")).sendKeys("Aus");
	
    //List<WebElement> listOfCountry = driver.findElements(By.cssSelector(".ta-results>button>span"));
	

}
