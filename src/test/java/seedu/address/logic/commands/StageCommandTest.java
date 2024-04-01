package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalOrders.CUPCAKES_ONLY;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class StageCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        StageCommand stageCommand = new StageCommand(Index.fromOneBased(1));

        // same command
        assertTrue(stageCommand.equals(stageCommand));

        // argument is null
        assertFalse(stageCommand.equals(null));
    }

    @Test
    public void validOrder_success() {
        StageCommand stageCommand = new StageCommand(Index.fromOneBased(1));
        expectedModel.goToNextStage(CUPCAKES_ONLY);
        String expectedMessage = "Staged Order: Cupcake,3\n"
                + "Sent For Delivery\n";
        assertCommandSuccess(stageCommand, model, expectedMessage, expectedModel);
    }
}
