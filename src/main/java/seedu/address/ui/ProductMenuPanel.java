package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.order.Product;

/**
 * Panel containing the list of available products.
 */
public class ProductMenuPanel extends UiPart<Region> {
    private static final String FXML = "ProductMenuPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ProductMenuPanel.class);

    @javafx.fxml.FXML
    private Label title;
    @javafx.fxml.FXML
    private ListView<Product> productMenuView;

    /**
     * Creates a {@code ProductMenuPanel} with the given {@code ObservableList}.
     */
    public ProductMenuPanel(ObservableList<Product> productMenu) {
        super(FXML);
        title.setText("Menu");
        productMenuView.setItems(productMenu);
        productMenuView.setCellFactory(listView -> new ProductMenuPanel.ProductMenuViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of an {@code Product} using a {@code ProductCard}.
     */
    class ProductMenuViewCell extends ListCell<Product> {
        @Override
        protected void updateItem(Product product, boolean empty) {
            super.updateItem(product, empty);

            if (empty || product == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ProductCard(product, getIndex() + 1).getRoot());
            }
        }
    }

    /**
     * Updates the menu list view with a given list of products.
     * This method sets the items of the order list view to the specified list of products,
     * effectively updating the UI to display these products. It can be used to apply filters
     * or to reset the menu list view to its original state.
     *
     * @param products The {@link ObservableList} of {@link Product} objects to be displayed.
     *               This list replaces the current items in the menu list view.
     */
    public void updateDisplayedProducts(ObservableList<Product> products) {
        productMenuView.setItems(products);
    }

}
