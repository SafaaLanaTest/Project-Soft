package com.example.sw_project;

import java.util.*;

public class InventoryService {

    private final Map<String, Integer> stockLevels;
    private final List<String> lowStockIngredients;
    private int criticalThreshold;

    public InventoryService() {
        this.stockLevels = new HashMap<>();
        this.lowStockIngredients = new ArrayList<>();
        this.criticalThreshold = 0;
    }

    /**
     * Sets the stock quantity for a specific ingredient.
     *
     * @param ingredient Name of the ingredient
     * @param quantity   Quantity in stock
     */
    public void setStockLevel(String ingredient, int quantity) {
        stockLevels.put(ingredient, quantity);
    }

    /**
     * Sets the critical stock threshold. Any ingredient below this value is considered low in stock.
     *
     * @param threshold The critical threshold
     */
    public void setCriticalThreshold(int threshold) {
        this.criticalThreshold = threshold;
    }

    /**
     * Checks all inventory levels and identifies ingredients that are below the critical threshold.
     */
    public void checkInventoryLevels() {
        lowStockIngredients.clear();
        for (Map.Entry<String, Integer> entry : stockLevels.entrySet()) {
            if (entry.getValue() < criticalThreshold) {
                lowStockIngredients.add(entry.getKey());
            }
        }
    }

    public List<String> getLowStockIngredients() {
        return new ArrayList<>(lowStockIngredients);
    }

    public void clearInventory() {
        stockLevels.clear();
        lowStockIngredients.clear();
        criticalThreshold = 0;
    }

    public Map<String, Integer> getAllStockLevels() {
        return new HashMap<>(stockLevels);
    }
}