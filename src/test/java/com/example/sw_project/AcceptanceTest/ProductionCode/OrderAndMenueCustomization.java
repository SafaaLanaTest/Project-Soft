package com.example.sw_project.AcceptanceTest.ProductionCode;

import com.example.sw_project.MealCustomizer;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import java.util.List;
import static org.junit.Assert.*;

public class OrderAndMenueCustomization {

    private final MealCustomizer customizer = new MealCustomizer();

    @Given("the customer is on the meal customization page")
    public void the_customer_is_on_the_meal_customization_page() {
        customizer.reset();
        System.out.println("Customer opened customization page.");
    }

    @When("the customer selects the following ingredients:")
    public void the_customer_selects_the_following_ingredients(DataTable dataTable) {
        List<String> ingredients = dataTable.asList(String.class);
        customizer.selectIngredients(ingredients);
        System.out.println("Customer selected ingredients: " + ingredients);
    }

    @Then("the system should accept the customized meal")
    public void the_system_should_accept_the_customized_meal() {
        assertTrue("Meal was not accepted", customizer.acceptMeal());
        System.out.println("Meal accepted: " + customizer.getSelectedIngredients());
    }

    @Then("the customized meal should be added to the cart")
    public void the_customized_meal_should_be_added_to_the_cart() {
        assertTrue("Meal was not added to cart", customizer.addToCart());
        System.out.println("Meal added to cart: " + customizer.getSelectedIngredients());
    }

    @Then("the system should display an error message for incompatible ingredients")
    public void the_system_should_display_an_error_message_for_incompatible_ingredients() {
        assertNotNull(customizer.getErrorMessage());
        assertFalse(customizer.getErrorMessage().isEmpty());
        System.out.println("Error: " + customizer.getErrorMessage());
    }

    @Then("the meal should not be added to the cart")
    public void the_meal_should_not_be_added_to_the_cart() {
        assertFalse("Meal should not be added", customizer.isMealInCart());
        System.out.println("Meal not added to cart.");
    }

    @When("the customer selects {string} as an ingredient")
    public void the_customer_selects_as_an_ingredient(String ingredient) {
        customizer.selectSingleIngredient(ingredient);
        System.out.println("Customer selected ingredient: " + ingredient);
    }

    @Then("the system should notify the customer that the ingredient is unavailable")
    public void the_system_should_notify_the_customer_that_the_ingredient_is_unavailable() {
        assertNotNull(customizer.getUnavailableMessage());
        assertFalse(customizer.getUnavailableMessage().isEmpty());
        System.out.println("Notification: " + customizer.getUnavailableMessage());
    }

    @Then("the customer should be prompted to choose an alternative")
    public void the_customer_should_be_prompted_to_choose_an_alternative() {
        assertNotNull(customizer.getAlternativeSuggestion());
        assertFalse(customizer.getAlternativeSuggestion().isEmpty());
        System.out.println("Suggestion: " + customizer.getAlternativeSuggestion());
    }
}