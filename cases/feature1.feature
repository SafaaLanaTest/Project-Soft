Feature: Customer Profile Management - Dietary Preferences and Allergies
  As a customer
  I want to input my dietary preferences and allergies
  So that the system can recommend appropriate meals and prevent unwanted ingredients

  As a chef
  I want to view customer dietary preferences
  So that I can customize meals accordingly

  # Scenario 1: Customer adds dietary preferences and allergies
  Scenario: Customer adds dietary preferences and allergies
    Given the customer has a profile in Approved state
    When the customer selects the following dietary preferences and allergies:
      | Type             | Value            |
      | Vegan            | Yes              |
      | Gluten-Free      | No               |
      | Low-Carb         | Yes              |
      | Allergies        | Peanuts, Shellfish |
    And saves the changes
    Then the profile should include the following dietary data:
      | Type             | Value            |
      | Vegan            | Yes              |
      | Gluten-Free      | No               |
      | Low-Carb         | Yes              |
      | Allergies        | Peanuts, Shellfish |

  # Scenario 2: Chef views dietary preferences for a customer
  Scenario: Chef views dietary preferences for a customer
    Given the customer has dietary preferences and allergies saved in their profile
    When the chef views the customer's profile
    Then the following dietary data should be visible:
      | Type             | Value            |
      | Vegan            | Yes              |
      | Gluten-Free      | No               |
      | Low-Carb         | Yes              |
      | Allergies        | Peanuts, Shellfish |
    # Scenario 3: Customer updates dietary preferences and allergies
  Scenario: Customer updates dietary preferences and allergies
    Given the customer has the following dietary preferences and allergies saved:
      | Type             | Value               |
      | Vegan            | Yes                 |
      | Gluten-Free      | No                  |
      | Low-Carb         | Yes                 |
      | Allergies        | Peanuts, Shellfish  |
    When the customer updates the dietary preferences as follows:
      | Type             | New Value           |
      | Gluten-Free      | Yes                 |
      | Low-Carb         | No                  |
      | Allergies        | Shellfish           |
    And saves the changes
    Then the profile should reflect the updated dietary data:
      | Type             | Value               |
      | Vegan            | Yes                 |
      | Gluten-Free      | Yes                 |
      | Low-Carb         | No                  |
      | Allergies        | Shellfish           |

  # Scenario 4: Customer removes all dietary preferences and allergies
  Scenario: Customer removes all dietary preferences and allergies
    Given the customer has dietary preferences and allergies saved in their profile
    When the customer clears all dietary preferences and allergy information
    And saves the changes
    Then the profile should indicate that no dietary preferences or allergies are specified