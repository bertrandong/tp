package seedu.address.ui;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.order.Deadline;
import seedu.address.model.order.Order;

/**
 * A UI component that displays information of an {@code Order}.
 */
public class OrderCard extends UiPart<Region> {
    private static final String FXML = "OrderListCard.fxml";

    public final Order order;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label orderId;
    @FXML
    private Label customerName;
    @FXML
    private Label deadline;
    @FXML
    private Label creationDate;
    @FXML
    private FlowPane products;
    @FXML
    private Label totalCost;
    @FXML
    private Label totalSales;
    @FXML
    private Label profit;


    /**
     * Creates an {@code OrderCard} with the given {@code Order} to display.
     */
    public OrderCard(Order order) {
        super(FXML);
        this.order = order;
        orderId.setText("Order " + order.getId());

        deadline.setText("Deadline: " + order.getDeadline());

        deadline.setStyle("-fx-text-fill: black;");
        if (order.getDeadlineObject().isWithinDaysFromNow(3)) {
            //deadline.setStyle("-fx-text-fill: #FFFFFF");
            deadline.setStyle("-fx-text-fill: red;");
        } else {
            deadline.setStyle("-fx-text-fill: #FFFFFF;");
        }


        creationDate.setText("Created On: " + order.getCreationDate());

        customerName.setText(order.getCustomer().getName().fullName);
        totalCost.setText("Total Cost: " + order.getTotalCost());
        totalSales.setText("Total Sales: " + order.getTotalSales());
        profit.setText("Profit: " + order.getProfit());
        ArrayList<String> productList = new ArrayList<>();
        order.getProductMap().forEach((product, quantity) -> productList.add(product + " x " + quantity));
        productList.stream().forEach(product -> products.getChildren().add(new Label(product)));
    }
}
