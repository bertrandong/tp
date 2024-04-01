package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ProductTest {
    @Test
    public void constructor_nameNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Product(null, "15", "18"));
    }

    @Test
    public void constructor_costNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Product("Cupcake", null, "18"));
    }

    @Test
    public void constructor_salesNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Product("Cupcake", "15", null));
    }

    @Test
    public void constructor_invalidProductName_throwsIllegalArgumentException() {
        String invalidProductName = "";
        assertThrows(IllegalArgumentException.class, () -> new Product(invalidProductName, "15", "18"));
    }

    @Test
    public void constructor_invalidProductSales_throwsIllegalArgumentException() {
        String invalidProductSales = "";
        assertThrows(IllegalArgumentException.class, () ->
                new Product("Cupcakes", "15", invalidProductSales));
    }

    @Test
    public void constructor_invalidProductCost_throwsIllegalArgumentException() {
        String invalidProductCost = "";
        assertThrows(IllegalArgumentException.class, () ->
                new Product("Cupcakes", invalidProductCost, "18"));
    }

    @Test
    public void isValidProduct() {
        // null product
        assertThrows(NullPointerException.class, () -> Product.isValidProduct(null));

        // invalid products
        assertFalse(Product.isValidProduct("")); // empty string
        assertFalse(Product.isValidProduct(" ")); // spaces only
        assertFalse(Product.isValidProduct(" Cupcake")); // first character space
        assertFalse(Product.isValidProduct("^")); // only non-alphanumeric characters
        assertFalse(Product.isValidProduct("Cupcake*")); // contain non-alphanumeric characters

        // valid name
        assertTrue(Product.isValidProduct("cupcake")); // alphabets only
        assertTrue(Product.isValidProduct("123")); // numbers only
        assertTrue(Product.isValidProduct("c00kies")); // alphanumeric characters
        assertTrue(Product.isValidProduct("Cupcake")); // with capital letters
        assertTrue(Product.isValidProduct("Cookies and Cupcakes with Ice Cream")); // long product names
    }

    @Test
    public void isValidPrice() {
        // null sales
        assertThrows(NullPointerException.class, () -> Product.isValidPrice(null));

        // invalid saless
        assertFalse(Product.isValidPrice("")); // empty string
        assertFalse(Product.isValidPrice(" ")); // spaces only
        assertFalse(Product.isValidPrice(" 1")); // first character space
        assertFalse(Product.isValidPrice("Cupcake")); // only non-numeric characters

        // valid sales
        assertTrue(Product.isValidPrice("15")); // $15
        assertTrue(Product.isValidPrice("1")); // exactly one digit
        assertTrue(Product.isValidPrice("0")); // free
        assertTrue(Product.isValidPrice("934579475985")); // long digits
    }

    @Test
    public void equals() {
        Product product = new Product("Valid Product", "15", "18");

        // same values -> returns true
        assertTrue(product.equals(new Product("Valid Product", "15", "18")));

        // same object -> returns true
        assertTrue(product.equals(product));

        // null -> returns false
        assertFalse(product.equals(null));

        // different types -> returns false;
        assertFalse(product.equals(5.0f));

        // different name same sales and cost -> returns false
        assertFalse(product.equals(new Product("Other Valid Product", "15", "18")));

        // different sales same name and cost -> returns true
        assertTrue(product.equals(new Product("Valid Product", "15", "17")));

        // different cost same name and sales -> returns true
        assertTrue(product.equals(new Product("Valid Product", "16", "18")));
    }
}
