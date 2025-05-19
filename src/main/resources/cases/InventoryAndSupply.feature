Feature: Supplier Integration for Pricing and Automatic Ordering

  As a kitchen manager,
  I want the system to fetch real-time ingredient prices from suppliers
  So that I can make cost-effective purchasing decisions

  As a system,
  I want to automatically generate purchase orders when stock levels are critically low
  So that supplies are replenished without manual intervention

  Scenario: Fetch real-time prices from suppliers
    Given the supplier API is available
    And the following ingredients need pricing:
      | Ingredient | Price |
      | Tomatoes   | 1.5   |
      | Basil      | 0.75  |
    When the system fetches current prices
    Then the system should display the following prices:
      | Ingredient | Price |
      | Tomatoes   | 1.5   |
      | Basil      | 0.75  |

  Scenario: Auto-generate purchase order when stock is critically low
    Given the current stock levels are:
      | Ingredient | Quantity |
      | Cheese     | 1        |
      | Flour      | 3        |
    And the critical threshold is set to 2 units
    When the system reviews inventory
    Then the system should generate a purchase order for:
      | Ingredient |
      | Cheese     |