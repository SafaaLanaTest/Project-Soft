package com.example.sw_project;

import java.util.*;

public class MealCustomizer {

    private final List<String> selectedIngredients = new ArrayList<>();
    private final Set<String> incompatiblePairs = Set.of("Shrimp:Peanut Sauce", "Beef:Yogurt");
    private final Set<String> unavailableIngredients = Set.of("Truffle Oil", "Caviar");

    private boolean isValidCombination = true;
    private boolean isAvailable = true;
    private boolean addedToCart = false;
    private String errorMessage = "";
    private String unavailableMessage = "";
    private String alternativeSuggestion = "";

    public void reset() {
        selectedIngredients.clear();
        isValidCombination = true;
        isAvailable = true;
        addedToCart = false;
        errorMessage = "";
        unavailableMessage = "";
        alternativeSuggestion = "";
    }

    public void selectIngredients(List<String> ingredients) {
        selectedIngredients.clear();
        selectedIngredients.addAll(ingredients);

        // Check incompatible
        for (String pair : incompatiblePairs) {
            String[] ing = pair.split(":");
            if (selectedIngredients.contains(ing[0]) && selectedIngredients.contains(ing[1])) {
                isValidCombination = false;
                errorMessage = "Incompatible ingredients: " + ing[0] + " and " + ing[1];
                return;
            }
        }

        // Check availability
        for (String ingredient : selectedIngredients) {
            if (unavailableIngredients.contains(ingredient)) {
                isAvailable = false;
                unavailableMessage = ingredient + " is currently unavailable.";
                alternativeSuggestion = "Please choose an alternative for " + ingredient + ".";
                return;
            }
        }
    }

    public void selectSingleIngredient(String ingredient) {
        reset();
        selectedIngredients.add(ingredient);
        if (unavailableIngredients.contains(ingredient)) {
            isAvailable = false;
            unavailableMessage = ingredient + " is currently unavailable.";
            alternativeSuggestion = "Please choose an alternative for " + ingredient + ".";
        }
    }

    public boolean acceptMeal() {
        return isValidCombination && isAvailable;
    }

    public boolean addToCart() {
        if (acceptMeal()) {
            addedToCart = true;
        }
        return addedToCart;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isMealInCart() {
        return addedToCart;
    }

    public String getUnavailableMessage() {
        return unavailableMessage;
    }

    public String getAlternativeSuggestion() {
        return alternativeSuggestion;
    }

    public List<String> getSelectedIngredients() {
        return new ArrayList<>(selectedIngredients);
    }
}