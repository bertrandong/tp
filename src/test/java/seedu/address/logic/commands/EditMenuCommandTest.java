package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRODUCT_COOKIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRODUCT_CUPCAKE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PRODUCT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PRODUCT;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditMenuCommand.EditProductDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.Product;
import seedu.address.testutil.EditProductDescriptorBuilder;
import seedu.address.testutil.ProductBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditMenuCommand.
 */
public class EditMenuCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Product editedProduct = new ProductBuilder().build();
        EditProductDescriptor descriptor = new EditProductDescriptorBuilder(editedProduct).build();
        EditMenuCommand editMenuCommand = new EditMenuCommand(INDEX_FIRST_PRODUCT, descriptor);

        String expectedMessage = String.format(EditMenuCommand.MESSAGE_EDIT_PRODUCT_SUCCESS,
                Messages.format(editedProduct));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setProduct(model.getFilteredMenuList().get(0), editedProduct);

        assertCommandSuccess(editMenuCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_oneFieldSpecifiedUnfilteredList_success() {
        Index indexLastProduct = Index.fromOneBased(model.getFilteredMenuList().size());
        Product lastProduct = model.getFilteredMenuList().get(indexLastProduct.getZeroBased());

        ProductBuilder productInList = new ProductBuilder(lastProduct);
        Product editedProduct = productInList.withCost("50").build();

        EditMenuCommand.EditProductDescriptor descriptor = new EditProductDescriptorBuilder().withCost("50").build();
        EditMenuCommand editMenuCommand = new EditMenuCommand(indexLastProduct, descriptor);

        String expectedMessage = String.format(EditMenuCommand.MESSAGE_EDIT_PRODUCT_SUCCESS,
                Messages.format(editedProduct));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setProduct(lastProduct, editedProduct);

        assertCommandSuccess(editMenuCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditMenuCommand editMenuCommand = new EditMenuCommand(INDEX_FIRST_PRODUCT,
                new EditProductDescriptor());
        Product editedProduct = model.getFilteredMenuList().get(INDEX_FIRST_PRODUCT.getZeroBased());

        String expectedMessage = String.format(EditMenuCommand.MESSAGE_EDIT_PRODUCT_SUCCESS,
                Messages.format(editedProduct));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editMenuCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateProductUnfilteredList_failure() {
        Product firstProduct = model.getFilteredMenuList().get(INDEX_FIRST_PRODUCT.getZeroBased());
        EditProductDescriptor descriptor = new EditProductDescriptorBuilder(firstProduct).build();
        EditMenuCommand editMenuCommand = new EditMenuCommand(INDEX_SECOND_PRODUCT, descriptor);

        assertCommandFailure(editMenuCommand, model, EditMenuCommand.MESSAGE_DUPLICATE_PRODUCT);
    }

    @Test
    public void execute_invalidProductIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMenuList().size() + 1);
        EditProductDescriptor descriptor = new EditProductDescriptorBuilder().withName(VALID_PRODUCT_CUPCAKE).build();
        EditMenuCommand editMenuCommand = new EditMenuCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editMenuCommand, model, Messages.MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        EditProductDescriptor descriptor = new EditProductDescriptorBuilder().withName(VALID_PRODUCT_CUPCAKE).build();
        final EditMenuCommand standardCommand = new EditMenuCommand(INDEX_FIRST_PRODUCT, descriptor);

        // same values -> returns true
        EditProductDescriptor copyDescriptor = new EditProductDescriptorBuilder().withName(VALID_PRODUCT_CUPCAKE)
                .build();
        EditProductDescriptor diffDescriptor = new EditProductDescriptorBuilder().withName(VALID_PRODUCT_COOKIE)
                .build();
        EditMenuCommand commandWithSameValues = new EditMenuCommand(INDEX_FIRST_PRODUCT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditMenuCommand(INDEX_SECOND_PRODUCT, descriptor)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditMenuCommand(INDEX_FIRST_PRODUCT, diffDescriptor)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditProductDescriptor editProductDescriptor = new EditProductDescriptor();
        EditMenuCommand editMenuCommand = new EditMenuCommand(index, editProductDescriptor);
        String expected = EditMenuCommand.class.getCanonicalName() + "{index=" + index
                + ", editProductDescriptor=" + editProductDescriptor + "}";
        assertEquals(expected, editMenuCommand.toString());
    }
}
