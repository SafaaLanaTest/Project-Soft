Feature: Low Stock Ingredient Notification

  Scenario: Notify kitchen manager about low-stock ingredients
    Given the following stock levels:
      | Ingredient | Quantity |
      | Cheese     | 1        |
      | Tomatoes   | 5        |
      | Basil      | 0        |
    And the critical threshold is 2
    When the system checks inventory levels
    Then the system should notify about low-stock ingredients:
      | Ingredient |
      | Cheese     |
      | Basil      |