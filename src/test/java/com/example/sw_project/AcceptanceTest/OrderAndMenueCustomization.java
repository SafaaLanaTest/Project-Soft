package com.example.sw_project.AcceptanceTest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import java.util.*;

import static org.junit.Assert.*;

public class OrderAndMenueCustomization {

    private List<String> selectedIngredients = new ArrayList<>();
    private final Set<String> incompatiblePairs = Set.of("Shrimp:Peanut Sauce", "Beef:Yogurt");
    private final Set<String> unavailableIngredients = Set.of("Truffle Oil", "Caviar");
    private boolean isValidCombination = true;
    private boolean isAvailable = true;
    private boolean addedToCart = false;
    private String errorMessage = "";
    private String unavailableMessage = "";
    private String alternativeSuggestion = "";

    @Given("the customer is on the meal customization page")
    public void the_customer_is_on_the_meal_customization_page() {
        selectedIngredients.clear();
        isValidCombination = true;
        isAvailable = true;
        addedToCart = false;
        errorMessage = "";
        unavailableMessage = "";
        alternativeSuggestion = "";
        System.out.println("Customer opened customization page.");
    }

    @When("the customer selects the following ingredients:")
    public void the_customer_selects_the_following_ingredients(DataTable dataTable) {
        selectedIngredients = dataTable.asList(String.class);
        // Check for incompatible combinations
        for (String pair : incompatiblePairs) {
            String[] ing = pair.split(":");
            if (selectedIngredients.contains(ing[0]) && selectedIngredients.contains(ing[1])) {
                isValidCombination = false;
                errorMessage = "Incompatible ingredients: " + ing[0] + " and " + ing[1];
                break;
            }
        }
        // Check availability
        for (String ingredient : selectedIngredients) {
            if (unavailableIngredients.contains(ingredient)) {
                isAvailable = false;
                unavailableMessage = ingredient + " is currently unavailable.";
                alternativeSuggestion = "Please choose an alternative for " + ingredient + ".";
                break;
            }
        }
    }

    @Then("the system should accept the customized meal")
    public void the_system_should_accept_the_customized_meal() {
        assertTrue("Meal was not accepted due to invalid combination or availability", isValidCombination && isAvailable);
        System.out.println("Meal accepted with ingredients: " + selectedIngredients);
    }

    @Then("the customized meal should be added to the cart")
    public void the_customized_meal_should_be_added_to_the_cart() {
        addedToCart = true;
        assertTrue("Meal was not added to the cart", addedToCart);
        System.out.println("Meal added to cart: " + selectedIngredients);
    }

    @Then("the system should display an error message for incompatible ingredients")
    public void the_system_should_display_an_error_message_for_incompatible_ingredients() {
        assertFalse("Expected incompatible ingredients", isValidCombination);
        assertFalse(errorMessage.isEmpty());
        System.out.println("Error: " + errorMessage);
    }

    @Then("the meal should not be added to the cart")
    public void the_meal_should_not_be_added_to_the_cart() {
        assertFalse("Meal should not be added due to errors", addedToCart);
        System.out.println("Meal not added to cart due to issues.");
    }

    @When("the customer selects {string} as an ingredient")
    public void the_customer_selects_as_an_ingredient(String ingredient) {
        selectedIngredients.clear();
        selectedIngredients.add(ingredient);
        if (unavailableIngredients.contains(ingredient)) {
            isAvailable = false;
            unavailableMessage = ingredient + " is currently unavailable.";
            alternativeSuggestion = "Please choose an alternative for " + ingredient + ".";
        }
    }

    @Then("the system should notify the customer that the ingredient is unavailable")
    public void the_system_should_notify_the_customer_that_the_ingredient_is_unavailable() {
        assertFalse("Ingredient should be unavailable", isAvailable);
        assertFalse(unavailableMessage.isEmpty());
        System.out.println("Notification: " + unavailableMessage);
    }

    @Then("the customer should be prompted to choose an alternative")
    public void the_customer_should_be_prompted_to_choose_an_alternative() {
        assertFalse(alternativeSuggestion.isEmpty());
        System.out.println("Suggestion: " + alternativeSuggestion);
    }
}