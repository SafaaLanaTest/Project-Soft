package com.example.sw_project.AcceptanceTest.ProductionCode;
import com.example.sw_project.TaskManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import java.util.*;
import static org.junit.Assert.*;

public class SchedulingAndTask {

    private final TaskManager taskManager = new TaskManager();

    @Given("the kitchen manager is logged into the task management system")
    public void the_kitchen_manager_is_logged_into_the_task_management_system() {
        System.out.println("Kitchen manager logged in.");
    }

    @Given("the system has the following chefs and their workload:")
    public void the_system_has_the_following_chefs_and_their_workload(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            String name = row.get("Name");
            String expertise = row.get("Expertise");
            int currentTasks = Integer.parseInt(row.get("Current Tasks"));
            taskManager.addChef(name, expertise, currentTasks);
        }
    }

    @When("the manager assigns a new task {string}")
    public void the_manager_assigns_a_new_task(String task) {
        taskManager.assignTask(task);
    }

    @Then("the system should assign the task to chef {string}")
    public void the_system_should_assign_the_task_to_chef(String expectedChef) {
        assertEquals(expectedChef, taskManager.getAssignedChefName());
    }

    @Then("the system should notify chef {string} about the new task")
    public void the_system_should_notify_chef_about_the_new_task(String chefName) {
        assertTrue(taskManager.hasNotification(chefName));
        System.out.println("Notification sent to " + chefName + ": " + taskManager.getNotification(chefName));
    }

    @Given("chef {string} has been assigned the task {string}")
    public void chef_has_been_assigned_the_task(String chefName, String task) {
        taskManager.notifyChef(chefName, task);
    }

    @When("the system sends the notification")
    public void the_system_sends_the_notification() {
        // Notification already sent in the step above
    }

    @Then("chef {string} should receive a message: {string}")
    public void chef_should_receive_a_message(String chefName, String expectedMessage) {
        assertTrue(taskManager.hasNotification(chefName));
        assertEquals(expectedMessage, taskManager.getNotification(chefName));
        System.out.println("Chef Message: " + taskManager.getNotification(chefName));
    }
}