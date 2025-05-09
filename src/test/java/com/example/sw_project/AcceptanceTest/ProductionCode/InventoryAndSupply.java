package com.example.sw_project.AcceptanceTest.ProductionCode;

import com.example.sw_project.SupplyManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.*;

public class InventoryAndSupply {

    private final SupplyManager manager = new SupplyManager();
    private Map<String, Double> fetchedPrices = new HashMap<>();
    private List<String> purchaseOrderItems = new ArrayList<>();

    @Given("the supplier API is available")
    public void the_supplier_api_is_available() {
        System.out.println("Supplier API is available.");
    }

    @Given("the following ingredients need pricing:")
    public void the_following_ingredients_need_pricing(DataTable dataTable) {
        manager.clear();
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            String ingredient = row.get("Ingredient");
            double price = Double.parseDouble(row.get("Price"));
            manager.setSupplierPrice(ingredient, price);
        }
    }

    @When("the system fetches current prices")
    public void the_system_fetches_current_prices() {
        fetchedPrices = manager.fetchCurrentPrices();
    }

    @Then("the system should display the following prices:")
    public void the_system_should_display_the_following_prices(DataTable dataTable) {
        Map<String, Double> expectedPrices = new HashMap<>();
        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            expectedPrices.put(row.get("Ingredient"), Double.parseDouble(row.get("Price")));
        }
        Assert.assertEquals("Prices do not match expected values", expectedPrices, fetchedPrices);
    }

    @Given("the current stock levels are:")
    public void the_current_stock_levels_are(DataTable dataTable) {
        manager.clear();
        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            String ingredient = row.get("Ingredient");
            int quantity = Integer.parseInt(row.get("Quantity"));
            manager.setStockLevel(ingredient, quantity);
        }
    }

    @Given("the critical threshold is set to {int} units")
    public void the_critical_threshold_is_set_to_units(Integer threshold) {
        manager.setCriticalThreshold(threshold);
    }

    @When("the system reviews inventory")
    public void the_system_reviews_inventory() {
        purchaseOrderItems = manager.generatePurchaseOrder();
    }

    @Then("the system should generate a purchase order for:")
    public void the_system_should_generate_a_purchase_order_for(DataTable dataTable) {
        List<String> expected = dataTable.asMaps(String.class, String.class)
                .stream()
                .map(row -> row.get("Ingredient"))
                .toList();
        Assert.assertEquals("Purchase order items do not match", expected, purchaseOrderItems);
    }
}