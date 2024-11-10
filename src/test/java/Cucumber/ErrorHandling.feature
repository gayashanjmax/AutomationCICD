
@tag
Feature: Error validation
  I want to use this template for my feature file


  @tag2
  Scenario Outline: Entering invalid username and password
    Given I landed on Ecommerce page
    When I Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | name  						       | password 	       |
      | gayashaninvalid@test.com | Testinvalid @1234 | 
