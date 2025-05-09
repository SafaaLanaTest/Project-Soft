package com.example.sw_project;
import java.util.ArrayList;
import java.util.List;

public class Admin {
    private List<String> recordedOrders = new ArrayList<>();

    public void recordOrders(List<String> orders) {
        this.recordedOrders.clear();
        this.recordedOrders.addAll(orders);
    }

    public void queryOrderHistory() {
        // ممكن لاحقاً تحط فلترة أو تحليل
    }

    public List<String> getTrendData() {
        return new ArrayList<>(recordedOrders);
    }
}