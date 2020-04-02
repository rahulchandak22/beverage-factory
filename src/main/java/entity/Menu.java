package entity;

import java.util.*;

public class Menu {
    private Map<String, Drink> drinkMap = new HashMap();
    private Map<String, Ingredient> ingredientMap = new HashMap();

    public Map<String, Drink> getDrinkMap() {
        return drinkMap;
    }

    public void addDrinkMap(Map<String, Drink> drinkMap) {
        this.drinkMap.putAll(drinkMap);
    }

    public Map<String, Ingredient> getIngredientMap() {
        return ingredientMap;
    }

    public void addIngredientMap(Map<String, Ingredient> ingredientMap) {
        this.ingredientMap.putAll(ingredientMap);
    }
}
