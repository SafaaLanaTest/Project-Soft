package com.example.sw_project;

import java.util.*;

public class InventoryManager {

    private final Map<String, Integer> stockLevels = new HashMap<>();
    private int restockingThreshold = 0;

    public void setStockLevel(String ingredient, int quantity) {
        stockLevels.put(ingredient, quantity);
    }

    public void setRestockingThreshold(int threshold) {
        this.restockingThreshold = threshold;
    }

    public List<String> checkInventoryForRestocking() {
        List<String> toRestock = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : stockLevels.entrySet()) {
            if (entry.getValue() <= restockingThreshold) {
                toRestock.add(entry.getKey());
            }
        }
        return toRestock;
    }

    public void clearInventory() {
        stockLevels.clear();
    }

    public Map<String, Integer> getCurrentInventory() {
        return new HashMap<>(stockLevels);
    }
}