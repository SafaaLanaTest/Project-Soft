package com.example.sw_project.AcceptanceTest.ProductionCode;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import com.example.sw_project.InventoryService;

import java.util.*;
import static org.junit.Assert.*;

public class LowStockIngredientNotification {

    private final InventoryService inventoryService = new InventoryService();

    @Given("the following stock levels:")
    public void the_following_stock_levels(DataTable dataTable) {
        inventoryService.clearInventory(); // تنظيف بين السيناريوهات
        for (Map<String, String> row : dataTable.asMaps()) {
            inventoryService.setStockLevel(row.get("Ingredient"), Integer.parseInt(row.get("Quantity")));
        }
    }

    @Given("the critical threshold is {int}")
    public void the_critical_threshold_is(Integer threshold) {
        inventoryService.setCriticalThreshold(threshold);
    }

    @When("the system checks inventory levels")
    public void the_system_checks_inventory_levels() {
        inventoryService.checkInventoryLevels();
    }

    @Then("the system should notify about low-stock ingredients:")
    public void the_system_should_notify_about_low_stock_ingredients(DataTable dataTable) {
        List<String> expected = new ArrayList<>();
        for (Map<String, String> row : dataTable.asMaps()) {
            expected.add(row.get("Ingredient"));
        }

        List<String> actual = inventoryService.getLowStockIngredients();
        Collections.sort(expected);
        Collections.sort(actual);
        assertEquals(expected, actual);
    }
}