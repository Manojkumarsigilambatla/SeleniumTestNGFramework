package cucumber.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.HashMap;

import org.testng.Assert;


public class EndToEndAPI {
	
	String token;
	String userId;
	String responseMessage;
	static String productId;
	
	String deleteAPIresponse;
	@Before
	public void setUp()
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";
	}
	
	@Given("^user should login through API call (.+) and (.+)$")
	public void user_should_login_through_API_call(String userName, String password)
	{
		
		String response =given().contentType(ContentType.JSON).accept(ContentType.JSON)
		.body("{\r\n"
				+ "  \"userEmail\": \""+userName+"\",\r\n"
				+ "  \"userPassword\": \""+password+"\"\r\n"
				+ "}")
		.when().post("api/ecom/auth/login").then().log().all().assertThat().statusCode(200)
		.extract().response().asPrettyString();
		
		JsonPath json = new JsonPath(response);
		token = json.getString("token");
		userId = json.getString("userId");
	}
	@When("^user need add the product (.+)$")
	public void user_need_add_the_product(String productName)
	{
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("productName",productName);
		map.put("productAddedBy", userId);
		map.put("productCategory", "Game accessories");
		map.put("productSubCategory", "Batter");
		map.put("productPrice", "10000");
		map.put("productDescription", "Cricket Bat English willow");
		map.put("productFor","Unisex");
		
		File file = new File("C:\\Users\\MSIGILAM\\OneDrive - Capgemini\\Desktop\\API\\BAT.png");
		
		responseMessage = given().header("Authorization", token).formParams(map).multiPart("productImage", file)
		.when().post("api/ecom/product/add-product")
		.then().log().all().assertThat().statusCode(201).extract().response().asPrettyString();
		
	}
	
	@Then("{string} message should be in response.")
	public void validatingTheReposne(String message)
	{
		JsonPath jsonPath = new JsonPath(responseMessage);
		productId =  jsonPath.getString("productId");
		String actual = jsonPath.getString("message");
		Assert.assertEquals(actual, message);
	}
	
	@When("user need to hit the Delete API.")
	public void delete_The_Product()
	{
		deleteAPIresponse = given().header("Authorization", token).pathParam("productId", productId).
		when().delete("api/ecom/product/delete-product/{productId}")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
	}
	
	@Then("user succesfully deleted and response contains {string}")
	public void successfullDeletion(String message)
	{
		JsonPath jsonPath = new JsonPath(deleteAPIresponse);
		String actual = jsonPath.getString("message");
		Assert.assertEquals(actual, message);
	}
}
