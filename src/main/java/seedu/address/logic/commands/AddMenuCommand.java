package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_SALES;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.Product;

/**
 * Adds a product to the menu.
 */
public class AddMenuCommand extends Command {
    public static final String COMMAND_WORD = "menu";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds products to the last created order "
            + "Parameters: "
            + PREFIX_PRODUCT_NAME + "PRODUCT NAME "
            + PREFIX_PRODUCT_COST + "PRODUCT COST "
            + PREFIX_PRODUCT_SALES + "PRODUCT SALES "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PRODUCT_NAME + "cake "
            + PREFIX_PRODUCT_COST + "15 "
            + PREFIX_PRODUCT_SALES + "20 ";

    public static final String MESSAGE_SUCCESS = "New product added: %1$s";

    public static final String MESSAGE_DUPLICATE_PRODUCT = "This product already exists in the menu";

    private final Product toAdd;

    /**
     * Creates an AddMenuCommand to add the specified {@code Product}
     */
    public AddMenuCommand(Product product) {
        requireAllNonNull(product);
        this.toAdd = product;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasProduct(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PRODUCT);
        }

        model.addProduct(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof AddMenuCommand)) {
            return false;
        }
        AddMenuCommand e = (AddMenuCommand) other;
        return (toAdd.equals(e.toAdd));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
