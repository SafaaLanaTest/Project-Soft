package com.example.sw_project.AcceptanceTest.ProductionCode;

import com.example.sw_project.InvoiceService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.*;

public class CustomerInvoicing {

    private final InvoiceService invoice = new InvoiceService();

    @Given("a customer has completed an order with the following items:")
    public void a_customer_has_completed_an_order_with_the_following_items(DataTable dataTable) {
        invoice.reset();

        for (Map<String, String> row : dataTable.asMaps()) {
            String item = row.get("Item").trim();
            int quantity = Integer.parseInt(row.get("Quantity").trim());
            double unitPrice = Double.parseDouble(row.get("Unit Price").trim());

            invoice.addItem(item, quantity, unitPrice);
        }
    }

    @When("the system generates the invoice")
    public void the_system_generates_the_invoice() {
        // الحساب تم مسبقًا في addItem، لا حاجة لخطوة إضافية
    }

    @Then("the customer should receive an invoice containing:")
    public void the_customer_should_receive_an_invoice_containing(DataTable dataTable) {
        Map<String, InvoiceService.InvoiceItem> actualItems = invoice.getInvoiceItems();

        for (Map<String, String> row : dataTable.asMaps()) {
            String item = row.get("Item").trim();
            int expectedQty = Integer.parseInt(row.get("Quantity").trim());
            double expectedUnitPrice = Double.parseDouble(row.get("Unit Price").trim());
            double expectedTotal = Double.parseDouble(row.get("Total").trim());

            Assert.assertTrue("Missing item in invoice: " + item, actualItems.containsKey(item));
            InvoiceService.InvoiceItem actual = actualItems.get(item);

            Assert.assertEquals("Incorrect quantity for " + item, expectedQty, actual.getQuantity());
            Assert.assertEquals("Incorrect unit price for " + item, expectedUnitPrice, actual.getUnitPrice(), 0.01);
            Assert.assertEquals("Incorrect total for " + item, expectedTotal, actual.getTotal(), 0.01);
        }
    }

    @Then("the total amount due should be {double}")
    public void the_total_amount_due_should_be(Double expectedTotal) {
        Assert.assertEquals("Total amount due mismatch", expectedTotal, invoice.getTotalAmountDue(), 0.01);
    }
}