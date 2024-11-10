
@tag
Feature: Purchase the order from Ecommerce website
  I want to use this template for my feature file

	Background:
	Given I landed on Ecommerce page

  @Regression
  Scenario Outline: Submit the order
    Given I Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "Thankyou for the order." message is displayed in the Confirmation page

    Examples: 
      | name  						| password 	| productName  		|
      | gayashan@test.com | Test@1234 | ADIDAS ORIGINAL |
