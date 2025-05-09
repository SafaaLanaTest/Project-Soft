package com.example.sw_project.AcceptanceTest.ProductionCode;
import com.example.sw_project.IngredientSubstitutionService;
import org.junit.Assert;
import io.cucumber.java.en.*;

public class SuggestedIngredient {

    private final IngredientSubstitutionService substitutionService = new IngredientSubstitutionService();
    private boolean isFinalized = false;

    @Given("{string} is currently unavailable")
    public void is_currently_unavailable(String ingredient) {
        substitutionService.setUnavailableIngredient(ingredient);
    }

    @Given("the customer is allergic to {string}")
    public void the_customer_is_allergic_to(String allergen) {
        substitutionService.setAllergicIngredient(allergen);
    }

    @When("the system processes the ingredient substitution logic")
    public void the_system_processes_the_ingredient_substitution_logic() {
        substitutionService.processSubstitutionLogic();
    }

    @Then("the system should suggest {string} as an alternative")
    public void the_system_should_suggest_as_an_alternative(String expectedAlternative) {
        Assert.assertEquals(expectedAlternative, substitutionService.getSuggestedAlternative());
        System.out.println("Suggested Alternative: " + expectedAlternative);
    }

    @Then("the system should suggest {string} as a peanut-free alternative")
    public void the_system_should_suggest_as_a_peanut_free_alternative(String expectedAlternative) {
        Assert.assertEquals(expectedAlternative, substitutionService.getSuggestedAlternative());
        System.out.println("Peanut-Free Alternative: " + expectedAlternative);
    }

    @Then("the system must enforce the substitution for the customer's safety")
    public void the_system_must_enforce_the_substitution_for_the_customer_s_safety() {
        Assert.assertTrue("Substitution should be enforced", substitutionService.isSubstitutionEnforced());
        System.out.println("Substitution enforced for safety.");
    }

    @Given("the system substituted {string} with {string}")
    public void the_system_substituted_with(String original, String substitute) {
        substitutionService.applySubstitution(original, substitute);
    }

    @When("the meal customization is finalized")
    public void the_meal_customization_is_finalized() {
        isFinalized = true;
    }

    @Then("the chef should receive an alert about the substitution")
    public void the_chef_should_receive_an_alert_about_the_substitution() {
        Assert.assertTrue("Meal not finalized", isFinalized);
        Assert.assertNotNull("No substitution alert", substitutionService.getSubstitutionAlert());
        System.out.println("Chef Alert: " + substitutionService.getSubstitutionAlert());
    }
}