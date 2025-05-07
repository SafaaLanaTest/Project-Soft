package com.example.sw_project.AcceptanceTest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.*;

public class CustomerInvoicing {

    private static class InvoiceItem {
        int quantity;
        double unitPrice;
        double total;

        InvoiceItem(int quantity, double unitPrice) {
            this.quantity = quantity;
            this.unitPrice = unitPrice;
            this.total = quantity * unitPrice;
        }
    }

    private final Map<String, InvoiceItem> invoiceItems = new HashMap<>();
    private double totalAmountDue = 0.0;

    @Given("a customer has completed an order with the following items:")
    public void a_customer_has_completed_an_order_with_the_following_items(DataTable dataTable) {
        invoiceItems.clear();
        totalAmountDue = 0.0;

        for (Map<String, String> row : dataTable.asMaps()) {
            String item = row.get("Item").trim();
            int quantity = Integer.parseInt(row.get("Quantity").trim());
            double unitPrice = Double.parseDouble(row.get("Unit Price").trim());

            InvoiceItem invoiceItem = new InvoiceItem(quantity, unitPrice);
            invoiceItems.put(item, invoiceItem);
            totalAmountDue += invoiceItem.total;
        }
    }

    @When("the system generates the invoice")
    public void the_system_generates_the_invoice() {
        // No action needed, already calculated in @Given
    }

    @Then("the customer should receive an invoice containing:")
    public void the_customer_should_receive_an_invoice_containing(DataTable dataTable) {
        for (Map<String, String> row : dataTable.asMaps()) {
            String item = row.get("Item").trim();
            int expectedQty = Integer.parseInt(row.get("Quantity").trim());
            double expectedUnitPrice = Double.parseDouble(row.get("Unit Price").trim());
            double expectedTotal = Double.parseDouble(row.get("Total").trim());

            Assert.assertTrue("Missing item in invoice: " + item, invoiceItems.containsKey(item));
            InvoiceItem actual = invoiceItems.get(item);

            Assert.assertEquals("Incorrect quantity for " + item, expectedQty, actual.quantity);
            Assert.assertEquals("Incorrect unit price for " + item, expectedUnitPrice, actual.unitPrice, 0.01);
            Assert.assertEquals("Incorrect total for " + item, expectedTotal, actual.total, 0.01);
        }
    }

    @Then("the total amount due should be {double}")
    public void the_total_amount_due_should_be(Double expectedTotal) {
        Assert.assertEquals("Total amount due mismatch", expectedTotal, totalAmountDue, 0.01);
    }
}