package com.example.sw_project.AcceptanceTest.ProductionCode;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.*;
import java.util.*;
import org.junit.Assert;
import com.example.sw_project.Client2;
import com.example.sw_project.Chef;
import com.example.sw_project.Admin;
public class TrackPastOrder {

    private Client2 client2 = new Client2();
    private Chef chef = new Chef();
    private Admin admin = new Admin();
    private List<String> pastOrders = new ArrayList<>();
    private List<String> suggestedMealPlan = new ArrayList<>();

    @Given("the customer has placed previous meal orders")
    public void the_customer_has_placed_previous_meal_orders() {
        pastOrders.add("Grilled Chicken Salad");
        pastOrders.add("Vegan Burger");
        pastOrders.add("Pasta Alfredo");
        client2.setPastOrders(pastOrders);
        System.out.println("Customer has previous meal orders.");
    }

    @When("the customer navigates to the {string} page")
    public void the_customer_navigates_to_the_page(String page) {
        Assert.assertEquals("Order History", page);
        System.out.println("Customer navigated to: " + page);
    }

    @Then("the system should display a list of meals the customer previously ordered")
    public void the_system_should_display_a_list_of_meals_the_customer_previously_ordered() {
        List<String> orders = client2.getPastOrders();
        Assert.assertFalse(orders.isEmpty());
        System.out.println("Past meals displayed: " + orders);
    }

    @And("the customer is viewing their past meal orders")
    public void the_customer_is_viewing_their_past_meal_orders() {
        Assert.assertFalse(client2.getPastOrders().isEmpty());
        System.out.println("Customer is viewing: " + client2.getPastOrders());
    }

    @When("the customer clicks the {string} button next to a meal")
    public void the_customer_clicks_the_button_next_to_a_meal(String meal) {
        client2.addToCart(meal);
        System.out.println("Customer clicked reorder for: " + meal);
    }

    @Then("the system should add that meal to the current order cart")
    public void the_system_should_add_that_meal_to_the_current_order_cart() {
        Assert.assertFalse(client2.getCart().isEmpty());
        System.out.println("Meal added to cart: " + client2.getCart());
    }

    @Given("the chef is viewing a customer's profile")
    public void the_chef_is_viewing_a_customer_s_profile() {
        // Ensure client has past orders
        if (client2.getPastOrders().isEmpty()) {
            client2.setPastOrders(List.of("Grilled Chicken Salad", "Vegan Burger", "Pasta Alfredo"));
        }
        chef.loadCustomerProfile(client2);
        System.out.println("Chef is viewing profile.");
    }

    @When("the chef selects the {string} tab")
    public void the_chef_selects_the_tab(String tab) {
        Assert.assertEquals("Order History", tab);
        System.out.println("Chef selected tab: " + tab);
    }

    @Then("the system should display a list of the customer's past orders")
    public void the_system_should_display_a_list_of_the_customer_s_past_orders() {
        List<String> history = chef.getCustomerPastOrders();
        Assert.assertFalse("Past order list is empty", history.isEmpty());
        System.out.println("Chef sees past orders: " + history);
    }

    @Given("the chef has access to the customer's order history")
    public void the_chef_has_access_to_the_customer_s_order_history() {
        if (client2.getPastOrders().isEmpty()) {
            client2.setPastOrders(List.of("Pasta Alfredo", "Pasta Alfredo", "Grilled Chicken Salad"));
        }
        chef.loadCustomerProfile(client2);
        System.out.println("Chef has access to order history.");
    }

    @When("the chef analyzes the most frequently ordered meals")
    public void the_chef_analyzes_the_most_frequently_ordered_meals() {
        suggestedMealPlan = chef.generatePersonalizedMealPlan();
        Assert.assertNotNull(suggestedMealPlan);
        System.out.println("Chef generated suggestions: " + suggestedMealPlan);
    }

    @Then("the chef should suggest a personalized meal plan based on the customer's preferences")
    public void the_chef_should_suggest_a_personalized_meal_plan_based_on_the_customer_s_preferences() {
        Assert.assertFalse("Suggested meal plan is empty", suggestedMealPlan.isEmpty());
        System.out.println("Suggested meal plan: " + suggestedMealPlan);
    }

    @Given("the system has recorded all customer meal orders")
    public void the_system_has_recorded_all_customer_meal_orders() {
        admin.recordOrders(List.of("Pasta", "Burger", "Salad"));
        System.out.println("System recorded meal orders.");
    }

    @When("the administrator queries the order history database")
    public void the_administrator_queries_the_order_history_database() {
        admin.queryOrderHistory();
        System.out.println("Admin queried history database.");
    }

    @Then("the system should return historical order data for trend analysis")
    public void the_system_should_return_historical_order_data_for_trend_analysis() {
        List<String> trends = admin.getTrendData();
        Assert.assertNotNull(trends);
        Assert.assertFalse(trends.isEmpty());
        System.out.println("Returned data for trends: " + trends);
    }
}
