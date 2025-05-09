package com.example.sw_project.AcceptanceTest.ProductionCode;

import com.example.sw_project.InventoryManager;
import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;

import java.util.*;

import static org.junit.Assert.*;

public class InventoryTracking {

    private final InventoryManager inventoryManager = new InventoryManager();
    private List<String> suggestedRestock = new ArrayList<>();

    @Given("the following ingredients and their stock levels:")
    public void the_following_ingredients_and_their_stock_levels(DataTable dataTable) {
        inventoryManager.clearInventory();
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            String ingredient = row.get("Ingredient");
            int quantity = Integer.parseInt(row.get("Quantity"));
            inventoryManager.setStockLevel(ingredient, quantity);
        }
    }

    @Given("the restocking threshold is set to {int} units")
    public void the_restocking_threshold_is_set_to_units(Integer threshold) {
        inventoryManager.setRestockingThreshold(threshold);
    }

    @When("the system checks the inventory")
    public void the_system_checks_the_inventory() {
        suggestedRestock = inventoryManager.checkInventoryForRestocking();
    }

    @Then("the system should suggest restocking for:")
    public void the_system_should_suggest_restocking_for(DataTable expectedTable) {
        List<String> expected = expectedTable.asMaps(String.class, String.class)
                .stream()
                .map(row -> row.get("Ingredient"))
                .sorted()
                .toList();

        List<String> actual = new ArrayList<>(suggestedRestock);
        Collections.sort(actual);

        assertEquals("Restocking suggestions do not match.", expected, actual);
    }

    @Then("the system should not suggest any restocking")
    public void the_system_should_not_suggest_any_restocking() {
        assertTrue("Expected no restocking suggestions, but found some.", suggestedRestock.isEmpty());
    }
}