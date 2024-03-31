package seedu.address.model.order.stage;

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
