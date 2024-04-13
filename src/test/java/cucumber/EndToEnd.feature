@EndToEnd
Feature: Ecomerce application teting

  @CreateProduct
  Scenario Outline: Adding products.
	Given user should login through API call <userName> and <password>
	When user need add the product <productName>
  Then "Product Added Successfully" message should be in response.
		Examples:
					|userName           |password		 |productName|
					|spyderman@gmail.com|Webshooter@1|BAT        |
  
  @PlaceTheOrder
  Scenario Outline: Placing the order.
    Given User should launch the browser with website
    And user need to login with valid credentials <userName> and <password>
    When User need to select the <productName> and add it to the cart.
    And user need to checkout the product.
    Then verify "Thankyou for the order." is message is present on order confirmation page. 

    Examples: 
      | userName  					| password     | productName  	 |
      | spyderman@gmail.com | Webshooter@1 | BAT |
#			| robert@gmail.com    | Robert@123   | HONDA |


  @DeleteProduct
  Scenario: Deleting the created product using API Call
  			
  		Given user should login through API call <userName> and <password>
  		When  user need to hit the Delete API.
  		Then user succesfully deleted and response contains "Product Deleted Successfully"
  		
  	Examples: 
      | userName  					| password     |
      | spyderman@gmail.com | Webshooter@1 |
