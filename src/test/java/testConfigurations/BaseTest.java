package testConfigurations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.restassured.path.json.JsonPath;
import pageObjects.LoginScreen;


public class BaseTest {
	
	public WebDriver driver;
	
	public LoginScreen login;
	
	public Map<String, String> orderIdData;
	
	
	
	public WebDriver intializeBrowser() throws FileNotFoundException, IOException
	{
		Properties propertiesFileReader = new Properties();
		propertiesFileReader.load(new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\Global_Properties\\global.properties")));
		String selectedBrowser = System.getProperty("browser")==null ? propertiesFileReader.getProperty("browser") : System.getProperty("browser");
		
		ChromeOptions options = new ChromeOptions();
		if(selectedBrowser.contains("headless"))
		{
			options.addArguments("headless");
		}
				//propertiesFileReader.getProperty("browser");
		
		if(selectedBrowser.contains("chrome"))
		{
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));
			
		}
		else
		{
			driver = new EdgeDriver();
			
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		return driver;
	}
	
	@BeforeMethod(alwaysRun = true)
	public void launchApplication() throws FileNotFoundException, IOException
	{
		driver = intializeBrowser();
		login = new LoginScreen(driver);
		login.openLoginScreen();
		if(orderIdData==null)
		{
		 orderIdData = new HashMap<String, String>();
		}
		
	}
	
	public List<HashMap<String, String>> jsonExtractor()
	{
		JsonPath json = new JsonPath(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TestData.json"));
		System.out.println(json.getInt("$.size()"));
		
		List<HashMap<String, String>> add = new ArrayList<HashMap<String,String>>();
		
		for(int i=0;i<json.getInt("$.size()");i++)
		{
			HashMap<String ,String> map = new HashMap<String, String>();
			
			map.put("email", json.getString("["+i+"].email"));
			map.put("password", json.getString("["+i+"].password"));
			map.put("productName", json.getString("["+i+"].productName"));
			
			add.add(map);
			
		}
		
		return add;
	}
	
	
	public String getScreenShot(String name,WebDriver driver) throws IOException
	{
		TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
		File screenShot = takeScreenshot.getScreenshotAs(OutputType.FILE);
		//
		FileUtils.copyFile(screenShot, new File(System.getProperty("user.dir")+"//Reports//"+name +".png"));
		return System.getProperty("user.dir")+"//Reports//"+name +".png";
	}
	@AfterMethod(alwaysRun = true)
	public void CloseTheDriver()
	{
		driver.close();
	}

}
