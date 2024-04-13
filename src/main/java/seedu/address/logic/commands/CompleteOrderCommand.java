package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

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

    private final ArrayList<Index> targetIndexArr;

    public CompleteOrderCommand(ArrayList<Index> targetIndex) {
        this.targetIndexArr = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String successStr = "";
        boolean isFirstOrderToBeCompleted = true;
        for (Index targetIndex : targetIndexArr) {
            if (!model.orderIdExists(targetIndex.getOneBased())) {
                throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
            }
        }
        for (Index targetIndex : targetIndexArr) {
            model.completeOrder(targetIndex.getOneBased());
            if (isFirstOrderToBeCompleted) {
                successStr += targetIndex.getOneBased();
                isFirstOrderToBeCompleted = false;
            } else {
                successStr = successStr + ", " + targetIndex.getOneBased();
            }
        }

        return new CommandResult(String.format(MESSAGE_COMPLETE_ORDER_SUCCESS, successStr));
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
        return targetIndexArr.equals(otherCompleteOrderCommand.targetIndexArr);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndexArr)
                .toString();
    }
}
