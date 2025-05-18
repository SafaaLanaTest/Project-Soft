Feature: Inventory Tracking and Restocking Suggestion

  As a kitchen manager,
  I want to track ingredient stock levels in real time
  So that I can prevent shortages and ensure continuous operations

  As a system,
  I want to automatically suggest restocking when ingredients are low
  So that kitchen managers can take action promptly

  Scenario: Track stock level and suggest restocking
    Given the following ingredients and their stock levels:
      | Ingredient     | Quantity |
      | Tomatoes       | 5        |
      | Olive Oil      | 20       |
      | Basil          | 2        |
    And the restocking threshold is set to 5 units
    When the system checks the inventory
    Then the system should suggest restocking for:
      | Ingredient     |
      | Tomatoes       |
      | Basil          |

  Scenario: No restocking needed when all items are above threshold
    Given the following ingredients and their stock levels:
      | Ingredient     | Quantity |
      | Cheese         | 10       |
      | Flour          | 15       |
      | Eggs           | 30       |
    And the restocking threshold is set to 5 units
    When the system checks the inventory
    Then the system should not suggest any restocking