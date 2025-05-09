package com.example.sw_project;

import java.util.*;

public class InvoiceService {

    public static class InvoiceItem {
        private final String name;
        private final int quantity;
        private final double unitPrice;
        private final double total;

        public InvoiceItem(String name, int quantity, double unitPrice) {
            this.name = name;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
            this.total = quantity * unitPrice;
        }

        public String getName() {
            return name;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getUnitPrice() {
            return unitPrice;
        }

        public double getTotal() {
            return total;
        }
    }

    private final Map<String, InvoiceItem> invoiceItems = new LinkedHashMap<>();
    private double totalAmountDue = 0.0;

    public void addItem(String name, int quantity, double unitPrice) {
        InvoiceItem item = new InvoiceItem(name, quantity, unitPrice);
        invoiceItems.put(name, item);
        totalAmountDue += item.getTotal();
    }

    public Map<String, InvoiceItem> getInvoiceItems() {
        return new LinkedHashMap<>(invoiceItems);
    }

    public double getTotalAmountDue() {
        return totalAmountDue;
    }

    public void reset() {
        invoiceItems.clear();
        totalAmountDue = 0.0;
    }
}