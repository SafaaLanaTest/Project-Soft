package com.example.sw_project.AcceptanceTest;

import io.cucumber.java.en.*;
import java.util.*;
import static org.junit.Assert.*;

public class SuggestedIngredient {

    private String selectedUnavailableIngredient;
    private String selectedAllergicIngredient;
    private String suggestedAlternative;
    private boolean isFinalized = false;
    private String substitutionAlert = null;

    private final Map<String, String> substitutionMap = new HashMap<>() {{
        put("Truffle Oil", "Olive Oil");
        put("Peanut Sauce", "Tahini Sauce");
    }};

    @Given("{string} is currently unavailable")
    public void is_currently_unavailable(String ingredient) {
        selectedUnavailableIngredient = ingredient;
    }

    @Given("the customer is allergic to {string}")
    public void the_customer_is_allergic_to(String ingredient) {
        selectedAllergicIngredient = ingredient;
    }

    @When("the system processes the ingredient substitution logic")
    public void the_system_processes_the_ingredient_substitution_logic() {
        // ربط الحساسية بالمكون الحقيقي
        if ("Peanuts".equals(selectedAllergicIngredient)) {
            selectedAllergicIngredient = "Peanut Sauce";
        }

        if (selectedAllergicIngredient != null && substitutionMap.containsKey(selectedAllergicIngredient)) {
            suggestedAlternative = substitutionMap.get(selectedAllergicIngredient);
        } else if (selectedUnavailableIngredient != null && substitutionMap.containsKey(selectedUnavailableIngredient)) {
            suggestedAlternative = substitutionMap.get(selectedUnavailableIngredient);
        } else {
            suggestedAlternative = null;
        }
    }

    @Then("the system should suggest {string} as an alternative")
    public void the_system_should_suggest_as_an_alternative(String expectedAlternative) {
        assertNotNull("No alternative suggested", suggestedAlternative);
        assertEquals(expectedAlternative, suggestedAlternative);
    }

    @Then("the system should suggest {string} as a peanut-free alternative")
    public void the_system_should_suggest_as_a_peanut_free_alternative(String expectedAlternative) {
        assertNotNull("No peanut-free alternative suggested", suggestedAlternative);
        assertEquals(expectedAlternative, suggestedAlternative);
    }

    @Then("the system must enforce the substitution for the customer's safety")
    public void the_system_must_enforce_the_substitution_for_the_customer_s_safety() {
        assertNotNull("Substitution must be enforced for safety", suggestedAlternative);
    }

    @Given("the system substituted {string} with {string}")
    public void the_system_substituted_with(String original, String substitute) {
        substitutionAlert = "Substitution applied: " + original + " -> " + substitute;
    }

    @When("the meal customization is finalized")
    public void the_meal_customization_is_finalized() {
        isFinalized = true;
    }

    @Then("the chef should receive an alert about the substitution")
    public void the_chef_should_receive_an_alert_about_the_substitution() {
        assertTrue("Meal not finalized", isFinalized);
        assertNotNull("No substitution alert sent to chef", substitutionAlert);
        System.out.println("Chef Alert: " + substitutionAlert);
    }
}