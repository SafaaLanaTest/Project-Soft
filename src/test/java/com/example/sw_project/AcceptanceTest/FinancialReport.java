package com.example.sw_project.AcceptanceTest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.*;

public class FinancialReport {

    private final List<Double> dailyOrderTotals = new ArrayList<>();
    private final List<Double> monthlyOrderTotals = new ArrayList<>();
    private double dailyRevenue = 0.0;
    private double monthlyRevenue = 0.0;

    // ==== DAILY REPORT ====

    @Given("the system has recorded the following orders today:")
    public void the_system_has_recorded_the_following_orders_today(DataTable dataTable) {
        dailyOrderTotals.clear();
        dailyRevenue = 0.0;

        for (Map<String, String> row : dataTable.asMaps()) {
            String amountStr = row.get("Total Amount");
            if (amountStr != null) {
                double total = Double.parseDouble(amountStr.trim());
                dailyOrderTotals.add(total);
                dailyRevenue += total;
            } else {
                throw new IllegalArgumentException("Missing 'Total Amount' in data row: " + row);
            }
        }
    }

    @When("the system generates the financial report for today")
    public void the_system_generates_the_financial_report_for_today() {
        // الحسابات تمت في Given
    }

    @Then("the report should show:")
    public void the_report_should_show(DataTable dataTable) {
        Map<String, String> expected = dataTable.asMaps().get(0);

        double expectedRevenue = Double.parseDouble(expected.get("Total Revenue").trim());
        int expectedOrders = Integer.parseInt(expected.get("Number of Orders").trim());

        Assert.assertEquals("Daily total revenue mismatch", expectedRevenue, dailyRevenue, 0.01);
        Assert.assertEquals("Daily number of orders mismatch", expectedOrders, dailyOrderTotals.size());
    }

    // ==== MONTHLY REPORT ====

    @Given("the system has recorded the following monthly orders:")
    public void the_system_has_recorded_the_following_monthly_orders(DataTable dataTable) {
        monthlyOrderTotals.clear();
        monthlyRevenue = 0.0;

        for (Map<String, String> row : dataTable.asMaps()) {
            String amountStr = row.get("Total Amount");
            if (amountStr != null) {
                double total = Double.parseDouble(amountStr.trim());
                monthlyOrderTotals.add(total);
                monthlyRevenue += total;
            } else {
                throw new IllegalArgumentException("Missing 'Total Amount' in data row: " + row);
            }
        }
    }

    @When("the system generates the financial report for the month")
    public void the_system_generates_the_financial_report_for_the_month() {
        // الحسابات تمت في Given
    }

    @Then("the monthly report should show:")
    public void the_monthly_report_should_show(DataTable dataTable) {
        Map<String, String> expected = dataTable.asMaps().get(0);

        double expectedRevenue = Double.parseDouble(expected.get("Total Revenue").trim());
        int expectedOrders = Integer.parseInt(expected.get("Number of Orders").trim());

        Assert.assertEquals("Monthly total revenue mismatch", expectedRevenue, monthlyRevenue, 0.01);
        Assert.assertEquals("Monthly number of orders mismatch", expectedOrders, monthlyOrderTotals.size());
    }
}