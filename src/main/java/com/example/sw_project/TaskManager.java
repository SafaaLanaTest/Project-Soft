package com.example.sw_project;

import java.util.*;

public class TaskManager {

    public static class Chef {
        private final String name;
        private final String expertise;
        private int currentTasks;

        public Chef(String name, String expertise, int currentTasks) {
            this.name = name;
            this.expertise = expertise;
            this.currentTasks = currentTasks;
        }

        public String getName() {
            return name;
        }

        public String getExpertise() {
            return expertise;
        }

        public int getCurrentTasks() {
            return currentTasks;
        }

        public void incrementTasks() {
            this.currentTasks++;
        }
    }

    private final List<Chef> chefs = new ArrayList<>();
    private final Map<String, String> notifications = new HashMap<>();
    private String assignedChefName = null;

    public void addChef(String name, String expertise, int currentTasks) {
        chefs.add(new Chef(name, expertise, currentTasks));
    }

    public void assignTask(String taskName) {
        Chef bestChef = null;

        for (Chef chef : chefs) {
            if (taskName.toLowerCase().contains("lasagna")
                    && chef.getExpertise().equalsIgnoreCase("Italian Cuisine")
                    && (bestChef == null || chef.getCurrentTasks() < bestChef.getCurrentTasks())) {
                bestChef = chef;
            }
        }

        if (bestChef != null) {
            bestChef.incrementTasks();
            assignedChefName = bestChef.getName();
            notifications.put(assignedChefName, "You have been assigned: " + taskName);
        }
    }

    public String getAssignedChefName() {
        return assignedChefName;
    }

    public void notifyChef(String chefName, String task) {
        notifications.put(chefName, "You have been assigned: " + task);
    }

    public String getNotification(String chefName) {
        return notifications.get(chefName);
    }

    public boolean hasNotification(String chefName) {
        return notifications.containsKey(chefName);
    }
}