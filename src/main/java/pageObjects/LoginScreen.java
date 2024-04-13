package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusableComponets.AbstarctComponents;

public class LoginScreen extends AbstarctComponents{
 
	public WebDriver driver;
	
	public LoginScreen(WebDriver driver)
	{
		super(driver);
		this.driver = driver ;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(id="userEmail")
	public WebElement userEmail;
	
	@FindBy(css="#userPassword")
    public WebElement password;
	
	@FindBy(name="login")
	public WebElement submit;
	
	@FindBy(css = ".toast-message")
	public WebElement incorrectToggleMessage;
	
	public void openLoginScreen()
	{
		driver.get("https://rahulshettyacademy.com/client/");
		
	}
	
	public DashboardScreen loginFunctionality(String email, String pwd)
	{
		userEmail.sendKeys(email);
		password.sendKeys(pwd);
		submit.click();
		
		DashboardScreen dashboard = new DashboardScreen(driver);
		return dashboard;
	}
	
	public String get_Incorrect_Login_Toggle_Message()
	{
	    waitForVisibilityOfElement(incorrectToggleMessage);
		return incorrectToggleMessage.getText();
	}
	
}
