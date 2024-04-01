package seedu.address.model.order;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the Product in the Order of the Customer.
 */
public class Product {

    public static final String MESSAGE_CONSTRAINTS =
            "Product names should only contain alphanumeric characters and spaces, and it should not be blank. "
                    + "Prices should be at least 1 digit long.";

    /*
     * The first character of the product name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String NAME_VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public static final String PRICE_VALIDATION_REGEX = "[+-]?([0-9]*[.])?[0-9]+";
    private String name;
    private String sales;
    private String cost;

    /**
     * Constructs a {@code Product} with name, cost and sales.
     *
     * @param name name of the Product
     * @param cost cost of the Product
     * @param sales sales of the Product
     */
    public Product(String name, String cost, String sales) {
        requireNonNull(name);
        checkArgument(isValidProduct(name), MESSAGE_CONSTRAINTS);
        checkArgument(isValidPrice(sales), MESSAGE_CONSTRAINTS);
        checkArgument(isValidPrice(cost), MESSAGE_CONSTRAINTS);
        this.name = name;
        this.cost = cost;
        this.sales = sales;
    }

    /**
     * Constructs a {@code Product} with name only.
     *
     * @param name name of the Product
     */
    public Product(String name) {
        requireNonNull(name);
        checkArgument(isValidProduct(name), MESSAGE_CONSTRAINTS);
        this.name = name;
        this.cost = "0";
        this.sales = "0";
    }

    /**
     * Copy constructor.
     * @param product Product to be copied.
     */
    public Product(Product product) {
        this.name = product.getName();
        this.cost = product.getCost();
        this.sales = product.getSales();
    }

    @Override
    public String toString() {
        return this.name + " Cost: $" + this.cost + " Sales: $" + this.sales;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Product)) {
            return false;
        }

        Product otherProduct = (Product) other;
        return this.name.equals(otherProduct.name);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    /**
     * Gets the name of the {@code Product}.
     * @return string value of the name of the {@code Product}.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the sales of the {@code Product}.
     * @return string value of the sales of the {@code Product}.
     */
    public String getSales() {
        return this.sales;
    }

    /**
     * Gets the cost of the {@code Product}.
     * @return string value of the cost of the {@code Product}.
     */
    public String getCost() {
        return this.cost;
    }

    /**
     * Sets the sales of the {@code Product}.
     * @param newSales new sales of the {@code Product}.
     */
    public void setSales(String newSales) {
        checkArgument(isValidPrice(sales), MESSAGE_CONSTRAINTS);
        this.sales = newSales;
    }

    /**
     * Sets the cost of the {@code Product}.
     * @param newCost new sales of the {@code Product}.
     */
    public void setCost(String newCost) {
        checkArgument(isValidPrice(cost), MESSAGE_CONSTRAINTS);
        this.cost = newCost;
    }

    /**
     * Sets the name of the {@code Product}.
     * @param newName new name of the {@code Product}.
     */
    public void rename(String newName) {
        checkArgument(isValidProduct(name), MESSAGE_CONSTRAINTS);
        this.name = newName;
    }

    /**
     * Returns true if a given string is a valid {@code Product} name.
     */
    public static boolean isValidProduct(String test) {
        return test.matches(NAME_VALIDATION_REGEX);
    }

    public static boolean isValidPrice(String test) {
        return test.matches(PRICE_VALIDATION_REGEX);
    }

    /**
     * Returns true if both products have the same name.
     * This defines a weaker notion of equality between two products.
     */
    public boolean isSameProduct(Product otherProduct) {
        if (otherProduct == this) {
            return true;
        }

        return otherProduct != null
                && otherProduct.getName().equals(getName());
    }
}
