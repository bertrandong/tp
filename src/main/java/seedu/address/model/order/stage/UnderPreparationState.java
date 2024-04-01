package seedu.address.model.order.stage;

/**
 * State of an order as under preparation.
 */
public class UnderPreparationState implements StageState {
    @Override
    public StageState getNextStage() {
        return new ReadyForDeliveryState();
    }

    @Override
    public String toString() {
        return "Under Preparation";
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof UnderPreparationState;
    }
}
