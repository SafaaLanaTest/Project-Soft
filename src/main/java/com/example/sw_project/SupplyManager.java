package com.example.sw_project;

import java.util.*;

public class SupplyManager {

    private final Map<String, Double> supplierPrices = new HashMap<>();
    private final Map<String, Integer> currentStock = new HashMap<>();
    private int criticalThreshold = 0;

    // محاكاة جلب الأسعار من مزود
    public void setSupplierPrice(String ingredient, double price) {
        supplierPrices.put(ingredient, price);
    }

    public Map<String, Double> fetchCurrentPrices() {
        return new HashMap<>(supplierPrices); // simulate API response
    }

    // إعداد مستويات المخزون الحالية
    public void setStockLevel(String ingredient, int quantity) {
        currentStock.put(ingredient, quantity);
    }

    public void setCriticalThreshold(int threshold) {
        this.criticalThreshold = threshold;
    }

    // فحص المخزون لإصدار أمر شراء
    public List<String> generatePurchaseOrder() {
        List<String> purchaseList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : currentStock.entrySet()) {
            if (entry.getValue() < criticalThreshold) {
                purchaseList.add(entry.getKey());
            }
        }
        return purchaseList;
    }

    public void clear() {
        supplierPrices.clear();
        currentStock.clear();
    }
}