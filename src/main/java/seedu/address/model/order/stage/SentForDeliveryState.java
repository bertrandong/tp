package seedu.address.model.order.stage;

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
