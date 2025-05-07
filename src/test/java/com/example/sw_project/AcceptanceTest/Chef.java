package com.example.sw_project.AcceptanceTest;

import java.util.*;

public class Chef {

    private Client customer;
    private List<String> customerPastOrders = new ArrayList<>();

    public void loadCustomerProfile(Client client) {
        this.customer = client;
        this.customerPastOrders.clear();
        this.customerPastOrders.addAll(client.getPastOrders()); // نقل الطلبات من العميل إلى الشيف
        System.out.println("Chef viewed customer profile: " + client.toString());
    }

    public List<String> getCustomerPastOrders() {
        return new ArrayList<>(customerPastOrders);
    }

    public List<String> generatePersonalizedMealPlan() {
        if (customerPastOrders.isEmpty()) {
            return Collections.emptyList();
        }

        // حساب تكرار كل وجبة
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String meal : customerPastOrders) {
            frequencyMap.put(meal, frequencyMap.getOrDefault(meal, 0) + 1);
        }

        // ترتيب الوجبات حسب التكرار تنازلياً
        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(frequencyMap.entrySet());
        sorted.sort((a, b) -> b.getValue() - a.getValue());

        // اختيار الوجبات الأكثر تكراراً
        List<String> suggestions = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : sorted) {
            suggestions.add(entry.getKey());
        }

        return suggestions;
    }
}