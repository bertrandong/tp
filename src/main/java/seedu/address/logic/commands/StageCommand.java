package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ORDERS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.Order;

public class StageCommand extends Command {
    public static final String COMMAND_WORD = "stage";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Move the order to the next stage identified"
            + "by the index number used in the displayed order list.\n"
            + "Parameters: [" + PREFIX_ORDER + "INDEX (must be a positive integer)] "
            + "Example: " + COMMAND_WORD + " " + PREFIX_ORDER + "1 ";

    public static final String MESSAGE_STAGE_ORDER_SUCCESS = "Staged Order: %1$s";

    private Index index;

    public StageCommand(Index index) {
        this.index = index;
    }

    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Order> lastShownList = model.getFilteredOrderList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        Order orderToUpdate = new Order(lastShownList.get(index.getZeroBased()));

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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .toString();
    }
}
