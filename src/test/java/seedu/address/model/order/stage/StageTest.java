package seedu.address.model.order.stage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;

public class StageTest {
    private final StageContext stageContext = new StageContext();

    @Test
    public void constructByString_matchesToString() {
        try {
            StageContext underPrep = new StageContext(new UnderPreparationState().toString());
            assertEquals(new UnderPreparationState().toString(), underPrep.toString());

            StageContext ready = new StageContext(new ReadyForDeliveryState().toString());
            assertEquals(new ReadyForDeliveryState().toString(), ready.toString());

            StageContext sent = new StageContext(new SentForDeliveryState().toString());
            assertEquals(new SentForDeliveryState().toString(), sent.toString());

            StageContext received = new StageContext(new ReceivedByCustomerState().toString());
            assertEquals(new ReceivedByCustomerState().toString(), received.toString());

        } catch (IllegalValueException e) {
            fail();
        }
    }

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
