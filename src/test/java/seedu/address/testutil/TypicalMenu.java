package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.order.Product;


/**
 * A utility class containing a list of {@code Product} objects to be used in tests.
 */
public class TypicalMenu {
    public static final Product CUPCAKE = new ProductBuilder().withName("Cupcake").withCost("10")
            .withSales("20").build();

    public static final Product TART = new ProductBuilder().withName("Tart").build();

    public static final Product PIE = new ProductBuilder().withName("Pie").build();

    public static List<Product> getTypicalMenu() {
        return new ArrayList<>(Arrays.asList(CUPCAKE, TART, PIE));
    }
}
