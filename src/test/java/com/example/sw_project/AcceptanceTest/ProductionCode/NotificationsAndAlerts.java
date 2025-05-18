package com.example.sw_project.AcceptanceTest.ProductionCode;

import com.example.sw_project.NotificationService;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class NotificationsAndAlerts {

    private final NotificationService notificationService = new NotificationService();

    @Given("a customer has a scheduled meal delivery at {string}")
    public void a_customer_has_a_scheduled_meal_delivery_at(String time) {
        notificationService.scheduleDelivery(time);
    }

    @When("the system checks for upcoming deliveries")
    public void the_system_checks_for_upcoming_deliveries() {
        notificationService.checkDeliveries();
    }

    @Then("the customer should receive a reminder for the delivery at {string}")
    public void the_customer_should_receive_a_reminder_for_the_delivery_at(String expectedTime) {
        String expectedReminder = "Reminder: Your meal will be delivered at " + expectedTime;
        Assert.assertEquals("Customer did not receive the correct delivery reminder",
                expectedReminder, notificationService.getCustomerReminder());
    }

    @Given("a chef has a scheduled cooking task at {string}")
    public void a_chef_has_a_scheduled_cooking_task_at(String time) {
        notificationService.scheduleCookingTask(time);
    }

    @When("the system checks for upcoming kitchen tasks")
    public void the_system_checks_for_upcoming_kitchen_tasks() {
        notificationService.checkCookingTasks();
    }

    @Then("the chef should receive a notification for the task at {string}")
    public void the_chef_should_receive_a_notification_for_the_task_at(String expectedTime) {
        String expectedNotification = "Alert: You have a cooking task scheduled at " + expectedTime;
        Assert.assertEquals("Chef did not receive the correct cooking task notification",
                expectedNotification, notificationService.getChefNotification());
    }
}