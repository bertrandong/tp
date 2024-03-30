package seedu.address.model.order.stage;

public class StageContext {

    // Solution below inspired by
    // https://www.geeksforgeeks.org/state-design-pattern/

    private StageState state;

    public StageContext() {
        this.state = new UnderPreparationState();
    }

    public void goToNextStage() {
        this.state = this.state.getNextStage();
    }

    public StageState getState() {
        return this.state;
    }
}
