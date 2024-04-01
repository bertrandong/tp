package seedu.address.testutil;

import seedu.address.model.order.Product;

/**
 * A utility class to help with building Product objects.
 */
public class ProductBuilder {
    public static final String DEFAULT_PRODUCT_NAME = "Cupcake";
    public static final String DEFAULT_PRODUCT_COST = "10";
    public static final String DEFAULT_PRODUCT_SALES = "20";

    private String productName;
    private String productCost;
    private String productSales;

    /**
     * Creates a {@code ProductBuilder} with the default details.
     */
    public ProductBuilder() {
        productName = DEFAULT_PRODUCT_NAME;
        productCost = DEFAULT_PRODUCT_COST;
        productSales = DEFAULT_PRODUCT_SALES;

    }

    /**
     * Initializes the ProductBuilder with the data of {@code productToCopy}.
     */
    public ProductBuilder(Product productToCopy) {
        productName = productToCopy.getName();
        productSales = productToCopy.getSales();
        productCost = productToCopy.getCost();
    }

    /**
     * Sets the {@code productName} of the {@code Product} that we are building.
     */
    public ProductBuilder withName(String name) {
        this.productName = name;
        return this;
    }

    /**
     * Sets the {@code productCost} of the {@code Product} that we are building.
     */
    public ProductBuilder withCost(String cost) {
        this.productCost = cost;
        return this;
    }

    /**
     * Sets the {@code productSales} of the {@code Product} that we are building.
     */
    public ProductBuilder withSales(String sales) {
        this.productSales = sales;
        return this;
    }

    public Product build() {
        return new Product(productName, productCost, productSales);
    }
}
