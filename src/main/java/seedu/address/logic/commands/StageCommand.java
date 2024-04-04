package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ORDERS;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.exceptions.OrderNotFoundException;
import seedu.address.model.order.Order;

/**
 * Moves an order to the next stage. If the order is already at its final
 * stage, keep it in the final stage.
 */
public class StageCommand extends Command {
    public static final String COMMAND_WORD = "stage";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Move the order to the next stage identified"
            + "by the index number used in the displayed order list.\n"
            + "Parameters: [" + PREFIX_ORDER + "INDEX (must be a positive integer)] "
            + "Example: " + COMMAND_WORD + " " + PREFIX_ORDER + "1 ";

    public static final String MESSAGE_STAGE_ORDER_SUCCESS = "Staged Order: %1$s";

    private Index index;

    /**
     * @param index of the order in the filtered order list to edit
     */
    public StageCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            model.getOrder(index.getOneBased());
        } catch (OrderNotFoundException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        Order orderToUpdate = new Order(model.getOrder(index.getOneBased()));

        Order updatedOrder = model.goToNextStage(orderToUpdate);
        model.setOrder(orderToUpdate, updatedOrder);
        model.updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);
        return new CommandResult(String.format(MESSAGE_STAGE_ORDER_SUCCESS,
                Messages.format(updatedOrder)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StageCommand)) {
            return false;
        }

        StageCommand otherStageCommand = (StageCommand) other;
        return index.equals(otherStageCommand.index);
    }
}
