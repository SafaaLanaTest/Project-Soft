Feature: Order and Menu Customization

  As a customer
  I want to select ingredients and customize my meal
  So that I can order meals according to my taste and dietary needs

  As a system
  I want to validate ingredient combinations
  So that customers do not select incompatible or unavailable ingredients

  # Scenario 1: Customer customizes a meal with valid ingredients
  Scenario: Customer customizes a meal with valid ingredients
    Given the customer is on the meal customization page
    When the customer selects the following ingredients:
      | Ingredient     |
      | Grilled Chicken |
      | Quinoa          |
      | Avocado         |
      | Spinach         |
    Then the system should accept the customized meal
    And the customized meal should be added to the cart

  # Scenario 2: Customer selects incompatible ingredients
  Scenario: Customer selects incompatible ingredients
    Given the customer is on the meal customization page
    When the customer selects the following ingredients:
      | Ingredient      |
      | Shrimp          |
      | Peanut Sauce    |
    Then the system should display an error message for incompatible ingredients
    And the meal should not be added to the cart

  # Scenario 3: Customer selects unavailable ingredients
  Scenario: Customer selects unavailable ingredients
    Given the customer is on the meal customization page
    When the customer selects "Truffle Oil" as an ingredient
    Then the system should notify the customer that the ingredient is unavailable
    And the customer should be prompted to choose an alternative