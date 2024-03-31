package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.Product;

/**
 * Deletes a product identified using it's displayed index from the menu.
 */
public class DeleteMenuCommand extends DeleteCommand {
    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the product identified by the index number used in the displayed menu.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PRODUCT_SUCCESS = "Deleted Product: %1$s";

    private final Index targetIndex;

    public DeleteMenuCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Product> lastShownList = model.getFilteredMenuList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX);
        }

        Product productToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteProduct(productToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PRODUCT_SUCCESS, Messages.format(productToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteMenuCommand)) {
            return false;
        }

        DeleteMenuCommand otherDeleteMenuCommand = (DeleteMenuCommand) other;
        return targetIndex.equals(otherDeleteMenuCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
