Feature: Suggest Ingredient Substitutions Based on Dietary Restrictions

  As a customer
  I want the system to suggest alternative ingredients
  If an ingredient is unavailable or doesn't fit my dietary restrictions
  So that I can enjoy my meal without compromising my health

  As a chef
  I want to receive an alert when an ingredient substitution is applied
  So that I can approve or adjust the final recipe

  # Scenario 1: Ingredient unavailable
  Scenario: Suggest alternative when ingredient is unavailable
    Given the customer selects "Truffle Oil" as an ingredient
    And "Truffle Oil" is currently unavailable
    When the system processes the ingredient substitution logic
    Then the system should suggest "Olive Oil" as an alternative

  # Scenario 2: Enforced substitution due to peanut allergy
  Scenario: Enforce substitution for peanut allergy
    Given the customer is allergic to "Peanuts"
    And the customer selects "Peanut Sauce" as an ingredient
    When the system processes the ingredient substitution logic
    Then the system should suggest "Tahini Sauce" as a peanut-free alternative
    And the system must enforce the substitution for the customer's safety

  # Scenario 3: Notify chef of substitution
  Scenario: Notify chef of ingredient substitution
    Given the system substituted "Peanut Sauce" with "Tahini Sauce"
    When the meal customization is finalized
    Then the chef should receive an alert about the substitution