package seedu.address.testutil;

import seedu.address.model.order.Product;

/**
 * A utility class to help with building Product objects.
 */
public class ProductBuilder {
    public static final String DEFAULT_PRODUCT_NAME = "Cupcake";

    private String productName;

    /**
     * Creates a {@code ProductBuilder} with the default details.
     */
    public ProductBuilder() {
        productName = DEFAULT_PRODUCT_NAME;
    }

    /**
     * Initializes the ProductBuilder with the data of {@code productToCopy}.
     */
    public ProductBuilder(Product productToCopy) {
        productName = productToCopy.getName();
    }

    /**
     * Sets the {@code productName} of the {@code Product} that we are building.
     */
    public ProductBuilder withName(String name) {
        this.productName = name;
        return this;
    }

    public Product build() {
        return new Product(productName);
    }
}
