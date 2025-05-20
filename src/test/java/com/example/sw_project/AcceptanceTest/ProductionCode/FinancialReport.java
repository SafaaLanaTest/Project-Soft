package com.example.sw_project.AcceptanceTest.ProductionCode;

import com.example.sw_project.FinancialReportGenerator;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;


import java.util.Map;

public class FinancialReport {

    private final FinancialReportGenerator report = new FinancialReportGenerator();

    // ==== DAILY REPORT ====

    @Given("the system has recorded the following orders today:")
    public void the_system_has_recorded_the_following_orders_today(DataTable dataTable) {
        report.reset(); // لضمان عدم تراكم البيانات من سيناريوهات سابقة
        for (Map<String, String> row : dataTable.asMaps()) {
            String amountStr = row.get("Total Amount");
            if (amountStr != null) {
                double total = Double.parseDouble(amountStr.trim());
                report.recordDailyOrder(total);
            } else {
                throw new IllegalArgumentException("Missing 'Total Amount' in data row: " + row);
            }
        }
    }

    @When("the system generates the financial report for today")
    public void the_system_generates_the_financial_report_for_today() {
        // الحسابات تتم داخل الكلاس المالي
    }

    @Then("the report should show:")
    public void the_report_should_show(DataTable dataTable) {
        Map<String, String> expected = dataTable.asMaps().get(0);

        double expectedRevenue = Double.parseDouble(expected.get("Total Revenue").trim());
        int expectedOrders = Integer.parseInt(expected.get("Number of Orders").trim());

        Assert.assertEquals("Daily total revenue mismatch", expectedRevenue, report.getDailyRevenue(), 0.01);
        Assert.assertEquals("Daily number of orders mismatch", expectedOrders, report.getDailyOrderCount());
    }

    // ==== MONTHLY REPORT ====

    @Given("the system has recorded the following monthly orders:")
    public void the_system_has_recorded_the_following_monthly_orders(DataTable dataTable) {
        report.reset(); // إعادة التهيئة لتقارير منفصلة
        for (Map<String, String> row : dataTable.asMaps()) {
            String amountStr = row.get("Total Amount");
            if (amountStr != null) {
                double total = Double.parseDouble(amountStr.trim());
                report.recordMonthlyOrder(total);
            } else {
                throw new IllegalArgumentException("Missing 'Total Amount' in data row: " + row);
            }
        }
    }

    @When("the system generates the financial report for the month")
    public void the_system_generates_the_financial_report_for_the_month() {
        // الحساب يتم تلقائيًا داخل الكلاس
    }

    @Then("the monthly report should show:")
    public void the_monthly_report_should_show(DataTable dataTable) {
        Map<String, String> expected = dataTable.asMaps().get(0);

        double expectedRevenue = Double.parseDouble(expected.get("Total Revenue").trim());
        int expectedOrders = Integer.parseInt(expected.get("Number of Orders").trim());

        Assert.assertEquals("Monthly total revenue mismatch", expectedRevenue, report.getMonthlyRevenue(), 0.01);
        Assert.assertEquals("Monthly number of orders mismatch", expectedOrders, report.getMonthlyOrderCount());
    }
}