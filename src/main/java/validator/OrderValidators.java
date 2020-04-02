package validator;

import entity.Menu;
import exception.InvalidOrderException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class OrderValidators {

    public static void validate(Menu menu, String[] drinks) {
        validateDrink(menu, drinks[0]);
        for (int i = 1; i < drinks.length; i++) {
            validateIngredient(menu, drinks[0], drinks[i]);
        }
        validateExclusionIngredientForRepetition(drinks);
        validateExclusionIngredientForMinimumIngredients(menu, drinks);
    }

    private static void validateExclusionIngredientForMinimumIngredients(Menu menu, String[] drinks) {
        if(menu.getDrinkMap().get(drinks[0]).getIngredients().size() <= drinks.length){
            throw new InvalidOrderException("Order cannot be placed with all ingredients excluded.");
        }
    }

    private static void validateExclusionIngredientForRepetition(String[] ingredients) {
        Set<String> ingredientSet = new HashSet<>(Arrays.asList(ingredients));
        if (ingredientSet.size() != ingredients.length){
            throw new InvalidOrderException("Exclusions are repeated.");
        }
    }

    private static void validateIngredient(Menu menu, String drink, String ingredient) {
        if (!menu.getDrinkMap().get(drink).getIngredients().contains(ingredient) || !menu.getIngredientMap().containsKey(ingredient)){
            throw new InvalidOrderException(ingredient+ " is not present in menu.");
        }
    }

    private static void validateDrink(Menu menu, String drink) {
        if (!menu.getDrinkMap().containsKey(drink)){
            throw new InvalidOrderException(drink+ " is not present in menu.");
        }
    }
}