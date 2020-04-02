package service;

import exception.InvalidOrderException;
import helpers.BeverageFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderServiceTest {

    OrderService orderService;

    @BeforeEach
    void setUp() throws IOException {
        orderService = new OrderService(BeverageFactory.populateMenu());
    }

    @Test
    void testGetBillForSingleOrderReturnsSuccess() {
        String[] inputOrderArr = new String[]{"Chai"};

        //act
        double billAmount = orderService.getBillFor(inputOrderArr);

        //assert
        assertEquals(4, billAmount);
    }

    @Test
    void testGetBillForBlankOrderThrowsException() {
        String[] inputOrderArr = new String[0];

        //act
        InvalidOrderException invalidOrderException = Assertions.assertThrows(InvalidOrderException.class, () -> orderService.getBillFor(inputOrderArr));
        //double billAmount = orderService.getBillFor(inputOrderArr);

        //assert
        assertEquals("Order is blank.", invalidOrderException.getMessage());
    }

    @Test
    void testGetBillForSingleOrderWithExclusionReturnsSuccess() {
        String[] inputOrderArr = new String[]{"Chai,-Milk"};

        //act
        double billAmount = orderService.getBillFor(inputOrderArr);

        //assert
        assertEquals(3, billAmount);
    }

    @Test
    void testGetBillForMultipleOrderWithExclusionReturnsSuccess() {
        String[] inputOrderArr = new String[]{"Chai,-MiLk", "COFFEE"};

        //act
        double billAmount = orderService.getBillFor(inputOrderArr);

        //assert
        assertEquals(8, billAmount);
    }

    @Test
    void testGetBillForOrderNotPresentInMenuThrowsException() {
        String[] inputOrderArr = new String[]{"Iced Tea"};

        //act
        InvalidOrderException invalidOrderException = Assertions.assertThrows(InvalidOrderException.class, () -> orderService.getBillFor(inputOrderArr));

        //assert
        assertEquals("iced tea is not present in menu.", invalidOrderException.getMessage());
    }

    @Test
    void testGetBillForOrderContainsInvalidIngredientForExclusionThrowsException() {
        String[] inputOrderArr = new String[]{"Chai,-Coffee"};

        //act
        InvalidOrderException invalidOrderException = Assertions.assertThrows(InvalidOrderException.class, () -> orderService.getBillFor(inputOrderArr));

        //assert
        assertEquals("coffee is not present in menu.", invalidOrderException.getMessage());
    }

    @Test
    void testGetBillForOrderContainsRepetitingIngredientForExclusionThrowsException() {
        String[] inputOrderArr = new String[]{"Chai,-Milk,-Milk"};

        //act
        InvalidOrderException invalidOrderException = Assertions.assertThrows(InvalidOrderException.class, () -> orderService.getBillFor(inputOrderArr));

        //assert
        assertEquals("Exclusions are repeated.", invalidOrderException.getMessage());
    }

    @Test
    void testGetBillForOrderWhereAllIngredientsAreRemovedThrowsException() {
        String[] inputOrderArr = new String[]{"Soda Water,-Soda,-Sugar,-Water"};
        //String[] inputOrderArr = new String[]{"Chai,-Milk,-Sugar,-Water"};

        //act
        InvalidOrderException invalidOrderException = Assertions.assertThrows(InvalidOrderException.class, () -> orderService.getBillFor(inputOrderArr));

        //assert
        assertEquals("Order cannot be placed with all ingredients excluded.", invalidOrderException.getMessage());
    }
}