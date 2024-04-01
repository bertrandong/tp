package seedu.address.model.order.stage;

/**
 * Single state in the order stages state machine.
 */
public interface StageState {

    // Solution below and related concrete implementations inspired by
    // https://www.geeksforgeeks.org/state-design-pattern/

    StageState getNextStage();
}
