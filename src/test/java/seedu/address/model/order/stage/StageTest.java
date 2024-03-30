package seedu.address.model.order.stage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StageTest {
    private final StageContext stageContext = new StageContext();

    @Test
    public void stageTransition() {
        assertEquals(new UnderPreparationState(), stageContext.getState());

        stageContext.goToNextStage();
        assertEquals(new ReadyForDeliveryState(), stageContext.getState());

        stageContext.goToNextStage();
        assertEquals(new SentForDeliveryState(), stageContext.getState());

        stageContext.goToNextStage();
        assertEquals(new ReceivedByCustomerState(), stageContext.getState());

        stageContext.goToNextStage();
        assertEquals(new ReceivedByCustomerState(), stageContext.getState());
    }
}
