package com.example.sw_project.AcceptanceTest.ProductionCode;

import io.cucumber.datatable.DataTable;
import java.util.Map;
import org.junit.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import com.example.sw_project.Client;

public class feature1 {

    private Client client = new Client();

    @When("saves the changes")
    public void saves_the_changes() {
        System.out.println("Client saved changes to profile.");
    }

    @Given("the customer has a profile in Approved state")
    public void the_customer_has_a_profile_in_approved_state() {
        client.setStatus("Approved");
        System.out.println("Customer profile is in Approved state.");
    }

    @When("the customer selects the following dietary preferences and allergies:")
    public void the_customer_selects_the_following_dietary_preferences_and_allergies(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        if (data.containsKey("Vegan"))
            client.setDietaryPreference("Vegan", "Yes".equalsIgnoreCase(data.get("Vegan")));
        if (data.containsKey("Gluten-Free"))
            client.setDietaryPreference("Gluten-Free", "Yes".equalsIgnoreCase(data.get("Gluten-Free")));
        if (data.containsKey("Low-Carb"))
            client.setDietaryPreference("Low-Carb", "Yes".equalsIgnoreCase(data.get("Low-Carb")));
        if (data.containsKey("Allergies"))
            client.setAllergies(data.get("Allergies").trim());
        System.out.println("Customer selected dietary preferences and allergies: " + data);
    }
    @Then("the profile should include the following dietary data:")
    public void the_profile_should_include_the_following_dietary_data(DataTable dataTable) {
        Map<String, String> expected = dataTable.asMap(String.class, String.class);
        Assert.assertEquals(expected.get("Vegan"), client.getDietaryPreference("Vegan") ? "Yes" : "No");
        Assert.assertEquals(expected.get("Gluten-Free"), client.getDietaryPreference("Gluten-Free") ? "Yes" : "No");
        Assert.assertEquals(expected.get("Low-Carb"), client.getDietaryPreference("Low-Carb") ? "Yes" : "No");
        Assert.assertEquals(expected.get("Allergies"), client.getAllergies());
        System.out.println("Dietary data verified: " + expected);
    }

    @Given("the customer has dietary preferences and allergies saved in their profile")
    public void the_customer_has_dietary_preferences_and_allergies_saved_in_their_profile() {
        client.setDietaryPreference("Vegan", true);
        client.setDietaryPreference("Gluten-Free", false);
        client.setDietaryPreference("Low-Carb", true);
        client.setAllergies("Peanuts, Shellfish");
        System.out.println("Customer has saved dietary preferences and allergies.");
    }

    @When("the chef views the customer's profile")
    public void the_chef_views_the_customer_s_profile() {
        System.out.println("Chef viewed customer profile: " + client);
    }

    @Then("the following dietary data should be visible:")
    public void the_following_dietary_data_should_be_visible(DataTable dataTable) {
        Map<String, String> expected = dataTable.asMap(String.class, String.class);
        Assert.assertEquals(expected.get("Vegan"), client.getDietaryPreference("Vegan") ? "Yes" : "No");
        Assert.assertEquals(expected.get("Gluten-Free"), client.getDietaryPreference("Gluten-Free") ? "Yes" : "No");
        Assert.assertEquals(expected.get("Low-Carb"), client.getDietaryPreference("Low-Carb") ? "Yes" : "No");
        Assert.assertEquals(expected.get("Allergies"), client.getAllergies());
        System.out.println("Chef confirmed dietary data: " + expected);
    }

    @Given("the customer has the following dietary preferences and allergies saved:")
    public void the_customer_has_the_following_dietary_preferences_and_allergies_saved(DataTable dataTable) {
        the_customer_selects_the_following_dietary_preferences_and_allergies(dataTable);
    }

    @When("the customer updates the dietary preferences as follows:")
    public void the_customer_updates_the_dietary_preferences_as_follows(DataTable dataTable) {
        the_customer_selects_the_following_dietary_preferences_and_allergies(dataTable);
    }

    @Then("the profile should reflect the updated dietary data:")
    public void the_profile_should_reflect_the_updated_dietary_data(DataTable dataTable) {
        the_profile_should_include_the_following_dietary_data(dataTable);
    }

    @When("the customer clears all dietary preferences and allergy information")
    public void the_customer_clears_all_dietary_preferences_and_allergy_information() {
        client.clearDietaryPreferences();
        client.setAllergies("");
        System.out.println("Customer cleared all dietary preferences and allergy info.");
    }

    @Then("the profile should indicate that no dietary preferences or allergies are specified")
    public void the_profile_should_indicate_that_no_dietary_preferences_or_allergies_are_specified() {
        Assert.assertFalse(client.getDietaryPreference("Vegan"));
        Assert.assertFalse(client.getDietaryPreference("Gluten-Free"));
        Assert.assertFalse(client.getDietaryPreference("Low-Carb"));
        Assert.assertTrue(client.getAllergies() == null || client.getAllergies().isEmpty());
        System.out.println("Verified that profile has no dietary preferences or allergies.");
    }
}