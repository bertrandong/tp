package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MENU;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_QUANTITY;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.Order;
import seedu.address.model.order.Product;
import seedu.address.model.order.Quantity;
import seedu.address.model.person.Person;

/**
 * Adds a product to the order.
 */
public class AddProductCommand extends Command {
    public static final String COMMAND_WORD = "product";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds products to the last created order "
            + "Parameters: "
            + PREFIX_MENU + "PRODUCT INDEX "
            + PREFIX_PRODUCT_QUANTITY + "QUANTITY\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MENU + "1 "
            + PREFIX_PRODUCT_QUANTITY + "2";
    private static Order lastOrder;

    private static Person personToEdit;

    private final Index productId;
    private final Quantity quantity;

    /**
     * Class constructor for AddProductCommand.
     * @param productId the one-based {@code Index} of the product to be added
     * @param quantity the quantity of the product to be added
     */
    public AddProductCommand(Index productId, Quantity quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    /**
     * Sets the last order
     * @param order the last order
     */
    public static void setLastOrder(Order order) {
        lastOrder = order;
    }

    /**
     * Sets the Person To Edit
     * @param person the last customer making an order
     */
    public static void setPersonToEdit(Person person) {
        personToEdit = person;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof AddOrderCommand)) {
            return false;
        }
        AddProductCommand e = (AddProductCommand) other;
        return (productId.equals(e.productId) && quantity.equals(e.quantity));
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (productId.getOneBased() < 1 || productId.getOneBased() > model.getFilteredMenuList().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MENU_DISPLAYED_INDEX);
        }
        if (lastOrder == null) {
            throw new CommandException(Messages.MESSAGE_ORDER_NOT_CREATED);
        }
        Product product = model.findProductByIndex(productId.getZeroBased());
        //Add ability to add product to order
        if (lastOrder.getProductMap().containsKey(product)) {
            Order newOrder = lastOrder.changeQuantity(
                    product, new Quantity(lastOrder.getQuantityValue(product) + quantity.getValue()));
            model.setOrder(lastOrder, newOrder);
            lastOrder = newOrder;
        } else {
            if (!model.hasOrder(lastOrder)) {
                lastOrder.addProduct(product, quantity);
                Person customer = lastOrder.getCustomer();
                model.addOrder(lastOrder, customer);
            } else {
                Order newOrder = lastOrder;
                newOrder.addProduct(product, quantity);
                model.setOrder(lastOrder, newOrder);
                lastOrder = newOrder;
            }
        }

        return new CommandResult(generateSuccessMessage(product));
    }
    private String generateSuccessMessage(Product product) {
        String message = "Successfully added " + product.toString() + " to the order.";
        return message;
    }

}
