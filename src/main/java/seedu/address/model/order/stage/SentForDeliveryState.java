package seedu.address.model.order.stage;

/**
 * State of an order as sent for delivery.
 */
public class SentForDeliveryState implements StageState {
    @Override
    public StageState getNextStage() {
        return new ReceivedByCustomerState();
    }

    @Override
    public String toString() {
        return "Sent For Delivery";
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof SentForDeliveryState;
    }
}
