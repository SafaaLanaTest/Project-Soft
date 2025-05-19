Feature: Financial Report Generation

  As a system administrator,
  I want to generate financial reports
  So that I can analyze revenue and track business performance

  Scenario: Generate daily financial report
    Given the system has recorded the following orders today:
      | Order ID | Total Amount |
      | 101      | 45.00        |
      | 102      | 60.00        |
      | 103      | 30.00        |
    When the system generates the financial report for today
    Then the report should show:
      | Total Revenue | Number of Orders |
      | 135.00        | 3                |

  Scenario: Generate monthly financial report
    Given the system has recorded the following monthly orders:
      | Order ID | Total Amount |
      | 201      | 150.00       |
      | 202      | 200.00       |
      | 203      | 175.50       |
      | 204      | 225.00       |
    When the system generates the financial report for the month
    Then the monthly report should show:
      | Total Revenue | Number of Orders |
      | 750.50        | 4                |