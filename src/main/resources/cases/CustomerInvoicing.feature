Feature: Customer Invoicing

  As a customer,
  I want to receive an invoice
  So that I have a record of my purchase

  Scenario: Generate invoice for completed order
    Given a customer has completed an order with the following items:
      | Item           | Quantity | Unit Price |
      | Grilled Chicken| 2        | 12.00      |
      | Salad          | 1        | 6.00       |
    When the system generates the invoice
    Then the customer should receive an invoice containing:
      | Item           | Quantity | Unit Price | Total |
      | Grilled Chicken| 2        | 12.00      | 24.00 |
      | Salad          | 1        | 6.00       | 6.00  |
    And the total amount due should be 30.00