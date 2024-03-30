package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.Order;

/**
 * Deletes an order by its Order ID.
 */
public class CompleteOrderCommand extends Command {
    public static final String COMMAND_WORD = "complete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Completes an order by its Order ID.\n"
            + "Parameters: ORDER_ID (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_COMPLETE_ORDER_SUCCESS = "Completed Order: %1$s";

    private final Index targetIndex;

    public CompleteOrderCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (targetIndex.getZeroBased() >= model.getOrderListSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }
        Order completedOrder= model.findOrderByIndex(targetIndex.getOneBased());
        model.deleteOrder(targetIndex.getOneBased());
        return new CommandResult(String.format(MESSAGE_COMPLETE_ORDER_SUCCESS, targetIndex.getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CompleteOrderCommand)) {
            return false;
        }

        CompleteOrderCommand otherCompleteOrderCommand = (CompleteOrderCommand) other;
        return targetIndex.equals(otherCompleteOrderCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
