Feature: Adding product to product catalogue 

Scenario Outline: Adding products.
	Given user should login through API call <userName> and <password>
	When user need add the product <productName>
  Then "Product Added Successfully" message should be in response.
Examples:
					|userName           |password		 |productName|
					|spyderman@gmail.com|Webshooter@1|BAT        |
