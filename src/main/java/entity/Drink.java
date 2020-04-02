package entity;

import java.util.HashSet;
import java.util.Set;

public class Drink {
    private String drinkName;
    private double price;
    private Set<String> ingredients = new HashSet();

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void addIngredients(Set<String> ingredients) {
        this.ingredients.addAll(ingredients);
    }

    @Override
    public String toString() {
        return "Drink{" +
                "drinkName='" + drinkName + '\'' +
                ", price=" + price +
                ", ingredients=" + ingredients +
                '}';
    }
}
