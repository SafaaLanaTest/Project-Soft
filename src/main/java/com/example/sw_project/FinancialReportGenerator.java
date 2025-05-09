package com.example.sw_project;
import java.util.ArrayList;
import java.util.List;

public class FinancialReportGenerator {

    private final List<Double> dailyOrders = new ArrayList<>();
    private final List<Double> monthlyOrders = new ArrayList<>();

    // تسجيل طلب يومي
    public void recordDailyOrder(double amount) {
        dailyOrders.add(amount);
    }

    // تسجيل طلب شهري
    public void recordMonthlyOrder(double amount) {
        monthlyOrders.add(amount);
    }

    // إجمالي الإيرادات اليومية
    public double getDailyRevenue() {
        return dailyOrders.stream().mapToDouble(Double::doubleValue).sum();
    }

    // عدد الطلبات اليومية
    public int getDailyOrderCount() {
        return dailyOrders.size();
    }

    // إجمالي الإيرادات الشهرية
    public double getMonthlyRevenue() {
        return monthlyOrders.stream().mapToDouble(Double::doubleValue).sum();
    }

    // عدد الطلبات الشهرية
    public int getMonthlyOrderCount() {
        return monthlyOrders.size();
    }

    // إعادة تعيين البيانات (اختياري للاختبارات)
    public void reset() {
        dailyOrders.clear();
        monthlyOrders.clear();
    }
}