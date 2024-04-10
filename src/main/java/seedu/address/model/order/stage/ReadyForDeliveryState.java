package seedu.address.model.order.stage;

/**
 * State of an order as ready for delivery.
 */
public class ReadyForDeliveryState implements StageState {
    @Override
    public StageState getNextStage() {
        return new SentForDeliveryState();
    }

    @Override
    public String toString() {
        return "Ready For Delivery";
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ReadyForDeliveryState;
    }
}
