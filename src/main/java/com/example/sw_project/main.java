package com.example.sw_project;


import java.util.Arrays;
import java.util.List;

public class main {

    public static void main(String[] args) {
        // تجربة FinancialReportGenerator
        FinancialReportGenerator report = new FinancialReportGenerator();
        report.recordDailyOrder(45.0);
        report.recordDailyOrder(60.0);
        report.recordMonthlyOrder(100.0);
        report.recordMonthlyOrder(120.0);
        System.out.println("Daily Revenue: " + report.getDailyRevenue());
        System.out.println("Monthly Revenue: " + report.getMonthlyRevenue());

        // تجربة MealCustomizer
        MealCustomizer customizer = new MealCustomizer();
        customizer.selectIngredients(Arrays.asList("Grilled Chicken", "Quinoa", "Avocado", "Spinach"));
        System.out.println("Accepted: " + customizer.acceptMeal());
        if (!customizer.acceptMeal()) {
            System.out.println("Error: " + customizer.getErrorMessage());
        }

        customizer.selectIngredients(Arrays.asList("Shrimp", "Peanut Sauce"));
        System.out.println("Accepted: " + customizer.acceptMeal());
        System.out.println("Error: " + customizer.getErrorMessage());

        // تجربة IngredientSubstitutionService
        IngredientSubstitutionService substitution = new IngredientSubstitutionService();
        substitution.setAllergicIngredient("Peanuts");
        substitution.processSubstitutionLogic();
        System.out.println("Suggested Alternative: " + substitution.getSuggestedAlternative());
        System.out.println("Substitution Enforced: " + substitution.isSubstitutionEnforced());

        // تجربة NotificationService
        NotificationService notify = new NotificationService();
        notify.scheduleDelivery("6:00 PM");
        notify.checkDeliveries();
        System.out.println("Customer Reminder: " + notify.getCustomerReminder());

        notify.scheduleCookingTask("3:00 PM");
        notify.checkCookingTasks();
        System.out.println("Chef Notification: " + notify.getChefNotification());

        // تجربة Admin و Client2 و Chef
        Client2 c = new Client2();
        c.setPastOrders(Arrays.asList("Grilled Chicken Salad", "Vegan Burger", "Pasta Alfredo"));
        Chef chef = new Chef();
        chef.loadCustomerProfile(c);
        System.out.println("Past Orders: " + chef.getCustomerPastOrders());
        System.out.println("Suggested Plan: " + chef.generatePersonalizedMealPlan());

        // تجربة InventoryManager
        InventoryManager inv = new InventoryManager();
        inv.setStockLevel("Tomatoes", 5);
        inv.setStockLevel("Cheese", 1);
        inv.setStockLevel("Basil", 0);
        inv.setRestockingThreshold(2);
        System.out.println("To restock: " + inv.checkInventoryForRestocking());

        // تجربة SupplyManager
        SupplyManager supply = new SupplyManager();
        supply.setStockLevel("Milk", 1);
        supply.setStockLevel("Butter", 4);
        supply.setCriticalThreshold(3);
        System.out.println("Purchase Order: " + supply.generatePurchaseOrder());

        // تجربة TaskManager
        TaskManager manager = new TaskManager();
        manager.addChef("Ahmed", "Italian Cuisine", 2);
        manager.addChef("Sara", "Italian Cuisine", 1);
        manager.assignTask("Prepare lasagna");
        System.out.println("Assigned Chef: " + manager.getAssignedChefName());
        System.out.println("Notification: " + manager.getNotification(manager.getAssignedChefName()));
    }
}
