package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ORDER;
import static seedu.address.testutil.TypicalIndexes.INDEX_LIST_FIRST_ORDER;
import static seedu.address.testutil.TypicalIndexes.INDEX_LIST_FIRST_SECOND_ORDER;
import static seedu.address.testutil.TypicalIndexes.INDEX_LIST_SECOND_ORDER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ORDER;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class CompleteOrderCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validSingleIndex_success() {
        CompleteOrderCommand completeCommand =
                new CompleteOrderCommand(INDEX_LIST_FIRST_ORDER);

        String expectedMessage = String.format(CompleteOrderCommand.MESSAGE_COMPLETE_ORDER_SUCCESS,
                INDEX_FIRST_ORDER.getOneBased());

        ModelManager expectedModel =
                new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.completeOrder(INDEX_FIRST_ORDER.getOneBased());

        assertCommandSuccess(completeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validMultipleIndex_success() {
        CompleteOrderCommand completeCommand =
                new CompleteOrderCommand(INDEX_LIST_FIRST_SECOND_ORDER);

        String expectedIndexString = INDEX_FIRST_ORDER.getOneBased() + ", " + INDEX_SECOND_ORDER.getOneBased();
        String expectedMessage = String.format(CompleteOrderCommand.MESSAGE_COMPLETE_ORDER_SUCCESS,
                expectedIndexString);
        ModelManager expectedModel =
                new ModelManager(getTypicalAddressBook(), new UserPrefs());
        for (Index targetIndex : INDEX_LIST_FIRST_SECOND_ORDER) {
            expectedModel.completeOrder(targetIndex.getOneBased());
        }
        assertCommandSuccess(completeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        int invalidIndex = model.getOrderListSize() + 1;
        Index outOfBoundIndex = Index.fromOneBased(invalidIndex);
        ArrayList<Index> invalidListOfIndex = new ArrayList<>();
        invalidListOfIndex.add(outOfBoundIndex);
        CompleteOrderCommand completeOrderCommand = new CompleteOrderCommand(invalidListOfIndex);

        assertCommandFailure(completeOrderCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        CompleteOrderCommand completeFirstCommand = new CompleteOrderCommand(INDEX_LIST_FIRST_ORDER);
        CompleteOrderCommand completeSecondCommand = new CompleteOrderCommand(INDEX_LIST_SECOND_ORDER);

        // same object -> returns true
        assertTrue(completeFirstCommand.equals(completeFirstCommand));

        // same values -> returns true
        CompleteOrderCommand completeFirstCommandCopy = new CompleteOrderCommand(INDEX_LIST_FIRST_ORDER);
        assertTrue(completeFirstCommand.equals(completeFirstCommandCopy));

        // different types -> returns false
        assertFalse(completeFirstCommand.equals(1));

        // null -> returns false
        assertFalse(completeFirstCommand.equals(null));

        // different order being completed -> returns false
        assertFalse(completeFirstCommand.equals(completeSecondCommand));
    }

    @Test
    public void toStringMethod() {
        ArrayList<Index> targetIndexArr = INDEX_LIST_FIRST_ORDER;
        CompleteOrderCommand completeOrderCommand = new CompleteOrderCommand(INDEX_LIST_FIRST_ORDER);
        String expected = CompleteOrderCommand.class.getCanonicalName() + "{targetIndex=" + targetIndexArr + "}";
        assertEquals(expected, completeOrderCommand.toString());
    }
}
