package com.example.sw_project;
import java.util.HashMap;
import java.util.Map;

public class IngredientSubstitutionService {

    private String selectedUnavailableIngredient;
    private String selectedAllergicIngredient;
    private String suggestedAlternative;
    private boolean substitutionEnforced = false;
    private String substitutionAlert;

    private final Map<String, String> substitutionMap = new HashMap<>() {{
        put("Truffle Oil", "Olive Oil");
        put("Peanut Sauce", "Tahini Sauce");
    }};

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
        if (selectedAllergicIngredient != null && substitutionMap.containsKey(selectedAllergicIngredient)) {
            suggestedAlternative = substitutionMap.get(selectedAllergicIngredient);
            substitutionEnforced = true;
        } else if (selectedUnavailableIngredient != null && substitutionMap.containsKey(selectedUnavailableIngredient)) {
            suggestedAlternative = substitutionMap.get(selectedUnavailableIngredient);
        }
    }

    public String getSuggestedAlternative() {
        return suggestedAlternative;
    }

    public boolean isSubstitutionEnforced() {
        return substitutionEnforced;
    }

    public void applySubstitution(String original, String substitute) {
        substitutionAlert = "Substitution applied: " + original + " -> " + substitute;
    }

    public String getSubstitutionAlert() {
        return substitutionAlert;
    }
}