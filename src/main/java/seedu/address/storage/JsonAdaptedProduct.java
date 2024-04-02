package seedu.address.storage;

import static seedu.address.model.order.Product.isValidProduct;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.Product;

/**
 * Jackson-friendly version of {@link Product}.
 */
public class JsonAdaptedProduct {

    public static final String MISSING_NAME_FIELD_MESSAGE = "Product's %s field is missing!";

    private final String name;
    private final String sales;

    private final String cost;


    /**
     * Constructs a {@code JsonAdaptedProduct} with the given product details.
     * @param name name of the Product.
     */
    @JsonCreator
    public JsonAdaptedProduct(@JsonProperty("product") String name, @JsonProperty("unitCost") String cost,
                              @JsonProperty("unitSales") String sales) {
        this.name = name;
        this.cost = cost;
        this.sales = sales;
    }

    /**
     * Converts a given {@code Product} into this class for Jackson use.
     */
    public JsonAdaptedProduct(Product source) {
        this.name = source.getName();
        this.cost = source.getCost();
        this.sales = source.getSales();
    }

    /**
     * Converts this Jackson-friendly adapted product object into the model's {@code Product}.
     * @return Model-friendly version of product object.
     * @throws IllegalValueException if there were any data constraints violated in the adapted product.
     */
    public Product toModelType() throws IllegalValueException {
        if (!isValidProduct(this.name)) {
            throw new IllegalValueException(MISSING_NAME_FIELD_MESSAGE);
        }
        if (this.cost == null) {
            throw new IllegalValueException(String.format(MISSING_NAME_FIELD_MESSAGE, "cost"));
        }
        if (this.sales == null) {
            throw new IllegalValueException(String.format(MISSING_NAME_FIELD_MESSAGE, "sales"));
        }
        return new Product(this.name, this.cost, this.sales);
    }
}
