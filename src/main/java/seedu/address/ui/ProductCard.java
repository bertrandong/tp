package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.order.Product;

/**
 * A UI component that displays information of an {@code Product}.
 */
public class ProductCard extends UiPart<Region> {
    private static final String FXML = "ProductCard.fxml";

    public final Product product;

    @javafx.fxml.FXML
    private HBox cardPane;

    @FXML
    private Label productName;

    /**
     * Creates an {@code ProductCard} with the given {@code Product} to display.
     */
    public ProductCard(Product product, int displayedIndex) {
        super(FXML);
        this.product = product;
        productName.setText(displayedIndex + ". " + product.getName());
    }
}
