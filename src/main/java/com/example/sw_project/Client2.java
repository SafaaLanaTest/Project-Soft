package com.example.sw_project;

import java.util.ArrayList;
import java.util.List;

public class Client2 {
    private List<String> pastOrders = new ArrayList<>();
    private List<String> cart = new ArrayList<>();

    public void setPastOrders(List<String> orders) {
        this.pastOrders = new ArrayList<>(orders);
    }

    public List<String> getPastOrders() {
        return new ArrayList<>(pastOrders);
    }

    public void addToCart(String meal) {
        this.cart.add(meal);
    }

    public List<String> getCart() {
        return new ArrayList<>(cart);
    }
}