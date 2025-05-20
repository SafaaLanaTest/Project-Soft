package com.example.sw_project;

import java.util.HashMap;
import java.util.Map;

public class IngredientSubstitutionService {
    private String selectedUnavailableIngredient;
    private String selectedAllergicIngredient;
    private String suggestedAlternative;
    private boolean substitutionEnforced = false;
    private String substitutionAlert;
    private final Map<String, String> substitutionMap;

    // Constructor to initialize the substitution map
    public IngredientSubstitutionService() {
        substitutionMap = new HashMap<>();
        substitutionMap.put("Truffle Oil", "Olive Oil");
        substitutionMap.put("Peanut Sauce", "Tahini Sauce");
    }

    public void setUnavailableIngredient(String ingredient) {
        this.selectedUnavailableIngredient = ingredient;
    }

    public void setAllergicIngredient(String allergen) {
        if ("Peanuts".equalsIgnoreCase(allergen)) {
            this.selectedAllergicIngredient = "Peanut Sauce";
        } else {
            this.selectedAllergicIngredient = allergen;
        }
    }

    public void processSubstitutionLogic() {
        if (this.selectedAllergicIngredient != null && this.substitutionMap.containsKey(this.selectedAllergicIngredient)) {
            this.suggestedAlternative = this.substitutionMap.get(this.selectedAllergicIngredient);
            this.substitutionEnforced = true;
        } else if (this.selectedUnavailableIngredient != null && this.substitutionMap.containsKey(this.selectedUnavailableIngredient)) {
            this.suggestedAlternative = this.substitutionMap.get(this.selectedUnavailableIngredient);
        }
    }

    public String getSuggestedAlternative() {
        return this.suggestedAlternative;
    }

    public boolean isSubstitutionEnforced() {
        return this.substitutionEnforced;
    }

    public void applySubstitution(String original, String substitute) {
        this.substitutionAlert = "Substitution applied: " + original + " -> " + substitute;
    }

    public String getSubstitutionAlert() {
        return this.substitutionAlert;
    }
}