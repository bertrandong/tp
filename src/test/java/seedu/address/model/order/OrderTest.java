package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRODUCT_COOKIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRODUCT_CUPCAKE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_THREE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_TWO;
import static seedu.address.testutil.TypicalOrders.COOKIES_ONLY;
import static seedu.address.testutil.TypicalOrders.CUPCAKES_AND_COOKIES;
import static seedu.address.testutil.TypicalOrders.CUPCAKES_ONLY;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.model.order.stage.ReadyForDeliveryState;
import seedu.address.model.order.stage.SentForDeliveryState;
import seedu.address.testutil.OrderBuilder;

public class OrderTest {
    @Test
    public void isSameOrder() {
        // same object -> returns true
        assertTrue(CUPCAKES_ONLY.isSameOrder(CUPCAKES_ONLY));

        // null -> returns false
        assertFalse(CUPCAKES_ONLY.isSameOrder(null));

        // different object -> returns false
        assertFalse(CUPCAKES_ONLY.isSameOrder(COOKIES_ONLY));
    }

    @Test
    public void equals() {
        // same values -> return true
        Order cupcakesCopy = new OrderBuilder(CUPCAKES_ONLY).build();
        assertTrue(CUPCAKES_ONLY.equals(cupcakesCopy));

        // same object -> returns true
        assertTrue(CUPCAKES_ONLY.equals(CUPCAKES_ONLY));

        // null -> returns false
        assertFalse(CUPCAKES_ONLY.equals(null));

        // different type -> returns false
        assertFalse(CUPCAKES_ONLY.equals(5));

        // different order -> returns false;
        assertFalse(CUPCAKES_ONLY.equals(COOKIES_ONLY));

        // different order id -> returns false;
        Order editedCupcakes = new OrderBuilder(CUPCAKES_ONLY).withIndex(3).build();
        assertFalse(CUPCAKES_ONLY.equals(editedCupcakes));

        // different person -> returns false;
        Order aliceCupcakes = new OrderBuilder(CUPCAKES_ONLY).withIndex(1).withPerson(ALICE).build();
        Order bobCupcakes = new OrderBuilder(CUPCAKES_ONLY).withIndex(2).withPerson(BOB).build();
        assertFalse(aliceCupcakes.equals(bobCupcakes));

        // different stage -> returns false;
        Order ready = new OrderBuilder(CUPCAKES_ONLY).withIndex(1)
                .withStage(new ReadyForDeliveryState()).build();
        Order sent = new OrderBuilder(CUPCAKES_ONLY).withIndex(2)
                .withStage(new SentForDeliveryState()).build();
        assertNotEquals(ready, sent);
    }

    @Test
    public void toStringMethod() {
        Order newCupcakesOnly = new OrderBuilder().withIndex(1)
                .withProductQuantity(VALID_PRODUCT_CUPCAKE, VALID_QUANTITY_THREE).build();
        Order newCupcakesAndCookies = new OrderBuilder().withIndex(3)
                .withProductQuantity(VALID_PRODUCT_CUPCAKE, VALID_QUANTITY_ONE)
                .withProductQuantity(VALID_PRODUCT_COOKIE, VALID_QUANTITY_TWO).build();

        // single product order
        String expectedCupcakes = "Cupcake,3\nUnder Preparation\n"
                + "Total Cost: 0.0\n" + "Total Sales: 0.0\n" + "Profit: 0.0\n";
        assertEquals(expectedCupcakes, newCupcakesOnly.toString());

        // multiple products order
        String expectedCupcakesAndCookies = "Cookie,2\nCupcake,1\nUnder Preparation\n"
                + "Total Cost: 0.0\n" + "Total Sales: 0.0\n" + "Profit: 0.0\n";
        assertEquals(expectedCupcakesAndCookies, newCupcakesAndCookies.toString());
    }

    @Test
    public void updateTotalSales() {

        Order order = new OrderBuilder().withProductPriceQuantity("Cupcakes", "0", "0", "5")
                .build();
        assertEquals(0, order.getTotalSales());

        order = new OrderBuilder().withProductPriceQuantity("Cupcakes", "0", "10", "5")
                .build();
        assertEquals(10 * 5, order.getTotalSales());

        //add product
        order.addProduct(new Product("Cookies", "10", "20"), new Quantity(3));
        assertEquals(10 * 5 + 20 * 3, order.getTotalSales());

        //remove product
        order = order.deleteProduct(new Product("Cookies"));
        assertEquals(10 * 5, order.getTotalSales());

        //update product
        order = order.updateOrder(new Product("Cupcakes"), new Quantity(7));
        assertEquals(7 * 10, order.getTotalSales());
    }

    @Test
    public void updateTotalCost() {
        //cost 0, sales non-zero
        Order order = new OrderBuilder().withProductPriceQuantity("Cupcakes", "0", "5", "5")
                .build();
        assertEquals(0, order.getTotalCost());

        //cost 5, sales 0
        order = new OrderBuilder().withProductPriceQuantity("Cupcakes", "5", "0", "5")
                .build();
        assertEquals(25, order.getTotalCost());

        //add product
        order.addProduct(new Product("Cookies", "10", "20"), new Quantity(3));
        assertEquals(5 * 5 + 10 * 3, order.getTotalCost());

        //remove product
        order = order.deleteProduct(new Product("Cookies"));
        assertEquals(5 * 5, order.getTotalCost());

        //update product
        order = order.updateOrder(new Product("Cupcakes"), new Quantity(7));
        assertEquals(7 * 5, order.getTotalCost());
    }

    @Test
    public void updateProfit() {
        //cost 0, sales 0
        Order order = new OrderBuilder().withProductPriceQuantity("Cupcakes", "0", "0", "5")
                .build();
        assertEquals(0, order.getProfit());

        //cost 5, sales 0
        order = new OrderBuilder().withProductPriceQuantity("Cupcakes", "5", "0", "5")
                .build();
        assertEquals(-25, order.getProfit());

        //cost 0, sales 5
        order = new OrderBuilder().withProductPriceQuantity("Cupcakes", "0", "5", "5")
                .build();
        assertEquals(25, order.getProfit());

        //add product
        order.addProduct(new Product("Cookies", "10", "20"), new Quantity(3));
        assertEquals(5 * 5 + 10 * 3, order.getProfit());

        //remove product
        order = order.deleteProduct(new Product("Cookies"));
        assertEquals(5 * 5, order.getProfit());

        //update product
        order = order.updateOrder(new Product("Cupcakes"), new Quantity(7));
        assertEquals(7 * 5, order.getProfit());
    }

    @Test
    public void copyOrder() {
        Order order = new OrderBuilder().withProductPriceQuantity("Cupcakes", "0", "0", "5")
                .build();
        Order copiedOrder = new Order(order);
        assertEquals(order, copiedOrder);
    }
}
