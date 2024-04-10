package seedu.address.model.order.stage;

/**
 * State of an order as received by customer, i.e. completed.
 */
public class ReceivedByCustomerState implements StageState {
    @Override
    public StageState getNextStage() {
        return this;
    }

    @Override
    public String toString() {
        return "Received By Customer";
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ReceivedByCustomerState;
    }
}
