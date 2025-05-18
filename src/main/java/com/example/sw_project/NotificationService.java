package com.example.sw_project;

import java.util.HashMap;
import java.util.Map;

public class NotificationService {

    private String deliveryTime;
    private String cookingTaskTime;
    private final Map<String, String> messages = new HashMap<>();

    public void scheduleDelivery(String time) {
        this.deliveryTime = time;
    }

    public void checkDeliveries() {
        if (deliveryTime != null) {
            messages.put("customer", "Reminder: Your meal will be delivered at " + deliveryTime);
        }
    }

    public String getCustomerReminder() {
        return messages.get("customer");
    }

    public void scheduleCookingTask(String time) {
        this.cookingTaskTime = time;
    }

    public void checkCookingTasks() {
        if (cookingTaskTime != null) {
            messages.put("chef", "Alert: You have a cooking task scheduled at " + cookingTaskTime);
        }
    }

    public String getChefNotification() {
        return messages.get("chef");
    }
}