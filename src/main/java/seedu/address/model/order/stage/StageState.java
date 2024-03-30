package seedu.address.model.order.stage;

public interface StageState {

    // Solution below and related concrete implementations inspired by
    // https://www.geeksforgeeks.org/state-design-pattern/

    StageState getNextStage();
}
