package com.example.sw_project;

import java.util.*;

public class Chef {
    private Client2 currentClient;

    public void loadCustomerProfile(Client2 client) {
        this.currentClient = client;
    }

    public List<String> getCustomerPastOrders() {
        if (currentClient == null) return Collections.emptyList();
        return currentClient.getPastOrders();
    }

    public List<String> generatePersonalizedMealPlan() {
        if (currentClient == null) return Collections.emptyList();

        List<String> orders = currentClient.getPastOrders();
        Map<String, Integer> frequencyMap = new HashMap<>();

        for (String meal : orders) {
            frequencyMap.put(meal, frequencyMap.getOrDefault(meal, 0) + 1);
        }

        // ترتيب حسب الأكثر تكراراً
        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(frequencyMap.entrySet());
        sorted.sort((a, b) -> b.getValue() - a.getValue());

        List<String> suggested = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : sorted) {
            suggested.add(entry.getKey());
        }

        return suggested;
    }
}
