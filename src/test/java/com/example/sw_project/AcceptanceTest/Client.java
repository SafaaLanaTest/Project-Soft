package com.example.sw_project.AcceptanceTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Client {

    private String username;
    private int age;
    private String goals;
    private String mail;
    private String phoneNumber;
    private String address;
    private String maritalStatus;
    private String status;
    private String allergies;
    private String errorMessage;

    private final Map<String, Boolean> dietaryPreferences = new HashMap<>();
    private final List<String> pastOrders = new ArrayList<>();
    private final List<String> cart = new ArrayList<>();

    // setters
    public void setUsername(String username) { this.username = username; }
    public void setAge(int age) { this.age = age; }
    public void setGoals(String goals) { this.goals = goals; }
    public void setMail(String mail) { this.mail = mail; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setAddress(String address) { this.address = address; }
    public void setMaritalStatus(String maritalStatus) { this.maritalStatus = maritalStatus; }
    public void setStatus(String status) { this.status = status; }
    public void setAllergies(String allergies) { this.allergies = allergies; }

    public void setDietaryPreference(String key, boolean value) {
        dietaryPreferences.put(key, value);
    }

    public void clearDietaryPreferences() {
        dietaryPreferences.clear();
    }

    // past orders & cart handling
    public void setPastOrders(List<String> orders) {
        pastOrders.clear();
        pastOrders.addAll(orders);
    }


    public List<String> getPastOrders() {
        return new ArrayList<>(pastOrders);
    }

    public void addToCart(String meal) {
        cart.add(meal);
    }

    public List<String> getCart() {
        return new ArrayList<>(cart);
    }

    // validation
    public void submitpending() {
        if (username == null || username.isEmpty() ||
                mail == null || mail.isEmpty() ||
                phoneNumber == null || phoneNumber.isEmpty()) {
            errorMessage = "Required fields are missing";
        } else {
            status = "Pending";
        }
    }

    public void submit() {
        if (username == null || username.isEmpty() ||
                mail == null || mail.isEmpty() ||
                phoneNumber == null || phoneNumber.isEmpty()) {
            errorMessage = "Required fields are missing";
            throw new RuntimeException(errorMessage);
        }
    }

    // getters
    public String getUsername() { return username; }
    public int getAge() { return age; }
    public String getGoals() { return goals; }
    public String getMail() { return mail; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getAddress() { return address; }
    public String getMaritalStatus() { return maritalStatus; }
    public String getStatus() { return status; }
    public String getAllergies() { return allergies; }
    public String getErrorMessage() { return errorMessage; }

    public boolean getDietaryPreference(String key) {
        return dietaryPreferences.getOrDefault(key, false);
    }

    @Override
    public String toString() {
        return "Client{" +
                "username='" + username + '\'' +
                ", age=" + age +
                ", goals='" + goals + '\'' +
                ", mail='" + mail + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", status='" + status + '\'' +
                ", allergies='" + allergies + '\'' +
                ", dietaryPreferences=" + dietaryPreferences +
                ", pastOrders=" + pastOrders +
                ", cart=" + cart +
                '}';
    }
}