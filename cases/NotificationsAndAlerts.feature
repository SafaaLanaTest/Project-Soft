Feature: Notifications and Alerts

  Scenario: Send reminder to customer for upcoming meal delivery
    Given a customer has a scheduled meal delivery at "5:00 PM"
    When the system checks for upcoming deliveries
    Then the customer should receive a reminder for the delivery at "5:00 PM"

  Scenario: Notify chef of upcoming cooking tasks
    Given a chef has a scheduled cooking task at "3:00 PM"
    When the system checks for upcoming kitchen tasks
    Then the chef should receive a notification for the task at "3:00 PM"