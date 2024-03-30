package seedu.address.model.order.stage;

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
