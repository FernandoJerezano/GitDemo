
@tag
  Feature: Error validation
    I want to use this template for my feature file
    
    @ErrorValidation
    Scenario Outline: 
      Given I landed on Ecommerce page
      When Logged in with username <name> and password <password>
      Then "Incorrect email or password." message is displayed.
      
      
        Examples:
          | name | password |
          | fdernando@gmail.com | password123 |