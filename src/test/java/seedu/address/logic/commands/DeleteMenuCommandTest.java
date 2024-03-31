package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PRODUCT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PRODUCT;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.Product;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteMenuCommand}.
 */
public class DeleteMenuCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Product productToDelete = model.getFilteredMenuList().get(INDEX_FIRST_PRODUCT.getZeroBased());
        DeleteMenuCommand deleteMenuCommand = new DeleteMenuCommand(INDEX_FIRST_PRODUCT);

        String expectedMessage = String.format(DeleteMenuCommand.MESSAGE_DELETE_PRODUCT_SUCCESS,
                Messages.format(productToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteProduct(productToDelete);

        assertCommandSuccess(deleteMenuCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMenuList().size() + 1);
        DeleteMenuCommand deleteMenuCommand = new DeleteMenuCommand(outOfBoundIndex);

        assertCommandFailure(deleteMenuCommand, model, Messages.MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteMenuCommand deleteFirstCommand = new DeleteMenuCommand(INDEX_FIRST_PRODUCT);
        DeleteMenuCommand deleteSecondCommand = new DeleteMenuCommand(INDEX_SECOND_PRODUCT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteMenuCommand deleteFirstCommandCopy = new DeleteMenuCommand(INDEX_FIRST_PRODUCT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteMenuCommand deleteMenuCommand = new DeleteMenuCommand(targetIndex);
        String expected = DeleteMenuCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteMenuCommand.toString());
    }
}
