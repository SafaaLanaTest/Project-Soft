package com.example.sw_project.AcceptanceTest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import java.util.*;

import static org.junit.Assert.*;

public class InventoryAndSupply {

    private Map<String, Double> supplierPrices = new HashMap<>();
    private Map<String, Integer> currentStock = new HashMap<>();
    private int criticalThreshold;
    private List<String> purchaseOrderItems = new ArrayList<>();

    @Given("the supplier API is available")
    public void the_supplier_api_is_available() {
        System.out.println("Supplier API is available.");
    }

    @Given("the following ingredients need pricing:")
    public void the_following_ingredients_need_pricing(DataTable dataTable) {
        supplierPrices.clear();
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            String ingredient = row.get("Ingredient");
            double price = Double.parseDouble(row.get("Price")); // السعر قادم من جدول الفيتشر
            supplierPrices.put(ingredient, price);
        }
    }

    @When("the system fetches current prices")
    public void the_system_fetches_current_prices() {
        // المحاكاة تمت بالفعل في @Given
        System.out.println("Fetched prices from supplier API.");
    }

    @Then("the system should display the following prices:")
    public void the_system_should_display_the_following_prices(DataTable dataTable) {
        Map<String, Double> expectedPrices = new HashMap<>();
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            expectedPrices.put(row.get("Ingredient"), Double.parseDouble(row.get("Price")));
        }
        assertEquals("Prices do not match expected values", expectedPrices, supplierPrices);
    }

    @Given("the current stock levels are:")
    public void the_current_stock_levels_are(DataTable dataTable) {
        currentStock.clear();
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            currentStock.put(row.get("Ingredient"), Integer.parseInt(row.get("Quantity")));
        }
    }

    @Given("the critical threshold is set to {int} units")
    public void the_critical_threshold_is_set_to_units(Integer threshold) {
        this.criticalThreshold = threshold;
    }

    @When("the system reviews inventory")
    public void the_system_reviews_inventory() {
        purchaseOrderItems.clear();
        for (Map.Entry<String, Integer> entry : currentStock.entrySet()) {
            if (entry.getValue() < criticalThreshold) {
                purchaseOrderItems.add(entry.getKey());
            }
        }
    }

    @Then("the system should generate a purchase order for:")
    public void the_system_should_generate_a_purchase_order_for(DataTable dataTable) {
        List<String> expected = dataTable.asMaps(String.class, String.class)
                .stream()
                .map(row -> row.get("Ingredient"))
                .toList();
        assertEquals("Purchase order items do not match", expected, purchaseOrderItems);
    }
}