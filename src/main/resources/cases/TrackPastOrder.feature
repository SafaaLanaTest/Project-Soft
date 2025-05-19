Feature: Track Past Orders and Personalized Meal Plans
  As a customer,
  I want to view and reorder meals I previously ordered,
  So that I can easily reorder my favorite meals.

  As a chef,
  I want to view customers' order histories,
  So that I can suggest personalized meal plans based on their preferences.

  As a system administrator,
  I want to store and retrieve customer order history,
  So that I can analyze trends and improve service offerings.

  # Scenario 1: Customer views past meal orders
  Scenario: Customer views past meal orders
    Given the customer has placed previous meal orders
    When the customer navigates to the "Order History" page
    Then the system should display a list of meals the customer previously ordered

  # Scenario 2: Customer reorders a previously ordered meal
  Scenario: Customer reorders a previously ordered meal
    Given the customer has placed previous meal orders
    And the customer is viewing their past meal orders
    When the customer clicks the "Grilled Chicken Salad" button next to a meal
    Then the system should add that meal to the current order cart

  # Scenario 3: Chef views customer's order history
  Scenario: Chef views customer's order history
    Given the chef is viewing a customer's profile
    When the chef selects the "Order History" tab
    Then the system should display a list of the customer's past orders

  # Scenario 4: Chef suggests a personalized meal plan
  Scenario: Chef suggests a personalized meal plan
    Given the chef has access to the customer's order history
    When the chef analyzes the most frequently ordered meals
    Then the chef should suggest a personalized meal plan based on the customer's preferences

  # Scenario 5: Administrator stores and retrieves order history
  Scenario: Administrator retrieves order history
    Given the system has recorded all customer meal orders
    When the administrator queries the order history database
    Then the system should return historical order data for trend analysis