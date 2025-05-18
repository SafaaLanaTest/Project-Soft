package com.example.sw_project;

import java.util.HashMap;
import java.util.Map;

public class Client {

    private String status;
    private String allergies;
    private final Map<String, Boolean> dietaryPreferences;

    public Client() {
        this.dietaryPreferences = new HashMap<>();
    }

    // الحالة مثل "Approved"
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    // تحديد تفضيل غذائي (مثل Vegan أو Gluten-Free)
    public void setDietaryPreference(String type, boolean value) {
        dietaryPreferences.put(type, value);
    }

    public boolean getDietaryPreference(String type) {
        return dietaryPreferences.getOrDefault(type, false);
    }

    // تحديد الحساسية
    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getAllergies() {
        return allergies;
    }

    // حذف جميع التفضيلات الغذائية
    public void clearDietaryPreferences() {
        dietaryPreferences.clear();
    }

    @Override
    public String toString() {
        return "Client{" +
                "status='" + status + '\'' +
                ", allergies='" + allergies + '\'' +
                ", dietaryPreferences=" + dietaryPreferences +
                '}';
    }
}