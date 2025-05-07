package com.example.sw_project.AcceptanceTest;

import java.util.*;

public class Admin {

    private List<String> orderHistory = new ArrayList<>();
    private List<String> trendData = new ArrayList<>();

    public void recordOrders(List<String> orders) {
        orderHistory.clear();
        orderHistory.addAll(orders);
    }

    public void queryOrderHistory() {
        // Simulate retrieving most common meals
        Map<String, Integer> freqMap = new HashMap<>();
        for (String order : orderHistory) {
            freqMap.put(order, freqMap.getOrDefault(order, 0) + 1);
        }

        // Sort by most frequent
        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(freqMap.entrySet());
        sorted.sort((a, b) -> b.getValue() - a.getValue());

        trendData.clear();
        for (Map.Entry<String, Integer> entry : sorted) {
            trendData.add(entry.getKey());
        }
    }

    public List<String> getTrendData() {
        return new ArrayList<>(trendData);
    }
}