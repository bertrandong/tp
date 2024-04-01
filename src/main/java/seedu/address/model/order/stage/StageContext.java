package seedu.address.model.order.stage;

import static java.util.Objects.requireNonNull;

import java.util.InputMismatchException;

import seedu.address.commons.exceptions.IllegalValueException;

public class StageContext {

    // Solution below inspired by
    // https://www.geeksforgeeks.org/state-design-pattern/

    private StageState state;

    public StageContext() {
        this.state = new UnderPreparationState();
    }

    public StageContext(StageState state) {
        this.state = state;
    }


    public StageContext(String str) throws IllegalValueException {
        requireNonNull(str);
        switch (str) {
        case "Under Preparation":
            this.state = new UnderPreparationState();
            break;
        case "Ready For Delivery":
            this.state = new ReadyForDeliveryState();
            break;
        case "Sent For Delivery":
            this.state = new SentForDeliveryState();
            break;
        case "Received By Customer":
            this.state = new ReceivedByCustomerState();
            break;
        default:
            throw new IllegalValueException("Unable to parse stage context");
        }
    }

    public void goToNextStage() {
        this.state = this.state.getNextStage();
    }

    public StageState getState() {
        return this.state;
    }

    @Override
    public String toString() {
        return this.state.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof StageContext)) {
            return false;
        }

        StageContext otherStage = (StageContext) other;
        return this.state.equals(otherStage.state);
    }
}
