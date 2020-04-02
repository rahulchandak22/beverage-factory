import entity.Menu;
import helpers.BeverageFactory;
import service.OrderService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Map;

public class BeverageFactoryApplication {
    //Only to run application from terminal
    public static void main(String[] args) throws IOException {

        //Create Menu
        Menu menu = BeverageFactory.populateMenu();
        OrderService orderService = new OrderService(menu);


        //Show Menu
        System.out.println("Welcome to Beverage Factory!");
        System.out.println("Please Select drinks from below menu to order");
        menu.getDrinkMap().entrySet().stream().map(Map.Entry::getKey).map((k) -> String.format("%s comes with %s , priced at ",
                menu.getDrinkMap().get(k).getDrinkName(),
                menu.getDrinkMap().get(k).getIngredients())
                + new DecimalFormat("$#.00").format(menu.getDrinkMap().get(k).getPrice()))
                .forEach(System.out::println);

        //Take Order
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] orderedDrinks = br.readLine().trim().split("\\\",");

        //Validate order & Calculate Total bill
        double totalPrice = orderService.getBillFor(orderedDrinks);

        //Print total bill amount
        System.out.println(totalPrice);
        br.close();

    }
}
