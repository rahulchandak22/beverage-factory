package service;

import entity.Menu;
import exception.InvalidOrderException;
import validator.OrderValidators;

import java.util.stream.IntStream;

public class OrderService {
    private Menu menu;

    public OrderService(Menu menu) {
        this.menu = menu;
    }

    public double getBillFor(String[] orderedDrinks) {
        double totalPrice = 0;
        if (orderedDrinks.length == 0) {
            throw new InvalidOrderException("Order is blank.");
        }
        for (String orderDrink : orderedDrinks) {
            String[] customDrinks = orderDrink
                    .replaceAll("\\s+$", "")
                    .replace("-", "")
                    .replaceAll("\\\"", "").toLowerCase()
                    .split(",");

            OrderValidators.validate(menu, customDrinks);

            double drinkPrice = menu.getDrinkMap().get(customDrinks[0]).getPrice();
            double sum = IntStream.range(1, customDrinks.length)
                    .mapToDouble(i -> menu.getIngredientMap().get(customDrinks[i]).getPrice())
                    .sum();
            totalPrice += drinkPrice - sum;
        }
        return totalPrice;
    }
}
