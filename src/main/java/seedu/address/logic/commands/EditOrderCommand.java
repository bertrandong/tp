package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MENU;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_QUANTITY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ORDERS;

import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.exceptions.OrderNotFoundException;
import seedu.address.model.exceptions.ProductNotFoundException;
import seedu.address.model.order.Deadline;
import seedu.address.model.order.Order;
import seedu.address.model.order.Product;
import seedu.address.model.order.Quantity;

/**
 * Edits the details of an existing order.
 */
public class EditOrderCommand extends EditCommand {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the details of the order identified "
            + "by the index number used in the displayed order list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: [" + PREFIX_ORDER + "INDEX (must be a positive integer)] "
            + "[" + PREFIX_MENU + "MENU_ID] "
            + "[" + PREFIX_DEADLINE + "DEADLINE] "
            + "Example: " + COMMAND_WORD + " " + PREFIX_ORDER + "1 "
            + PREFIX_MENU + "1 "
            + PREFIX_PRODUCT_QUANTITY + "2 "
            + "or " + COMMAND_WORD + " " + PREFIX_ORDER + "1 "
            + PREFIX_DEADLINE + "01/04/2024";

    public static final String MESSAGE_EDIT_ORDER_SUCCESS = "Edited Order: %1$s";
    public static final String MESSAGE_NOT_EDITED =
            "Both product and quantity must be provided.";

    public static final String MESSAGE_NOT_EDITED_EXTRA = "Please provide either both product and quantity, "
            + "or the order's deadline to be edited";

    private final Index orderIndex;
    private final Index productIndex;
    private final EditOrderDescriptor editOrderDescriptor;

    /**
     * @param orderIndex of the order in the filtered order list to edit
     * @param productIndex of the product in the filtered product menu to edit
     * @param editOrderDescriptor details to edit the order with
     */
    public EditOrderCommand(Index orderIndex, Index productIndex, EditOrderDescriptor editOrderDescriptor) {
        requireNonNull(orderIndex);
        requireNonNull(productIndex);
        requireNonNull(editOrderDescriptor);

        this.orderIndex = orderIndex;
        this.productIndex = productIndex;
        this.editOrderDescriptor = new EditOrderDescriptor(editOrderDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            model.getOrder(orderIndex.getOneBased());
        } catch (OrderNotFoundException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        try {
            model.findProductByIndex(productIndex.getZeroBased());
        } catch (ProductNotFoundException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        Order orderToEdit = new Order(model.getOrder(orderIndex.getOneBased()));
        Product productToEdit = new Product(model.findProductByIndex(productIndex.getZeroBased()));
        editOrderDescriptor.setProduct(productToEdit);
        Order editedOrder = createEditOrder(model, orderToEdit);

        model.setOrder(orderToEdit, editedOrder);
        model.updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);
        return new CommandResult(String.format(MESSAGE_EDIT_ORDER_SUCCESS,
                Messages.format(editedOrder)));
    }
    /**
     * Creates or updates an order based on the specified edit descriptors.
     * This method decides how to edit an existing order based on the presence of product,
     * quantity, and deadline information within an edit descriptor.
     *
     * @param model The application model that contains the order data and editing methods.
     * @param orderToEdit The order to be edited.
     * @return The edited order with the updates applied.
     */

    public Order createEditOrder(Model model, Order orderToEdit) {
        Order editedOrder;
        if (editOrderDescriptor.getProduct() != null && editOrderDescriptor.getQuantity() != null) {
            editedOrder = model.editOrder(orderToEdit,
                    editOrderDescriptor.getProduct(), editOrderDescriptor.getQuantity());
        } else if (editOrderDescriptor.getProduct() == null && editOrderDescriptor.getQuantity() == null
                && this.editOrderDescriptor.getDeadline() != null) {
            editedOrder = model.editOrderDeadline(orderToEdit, this.editOrderDescriptor.getDeadline());
        } else {
            editedOrder = model.editOrder(orderToEdit,
                    editOrderDescriptor.getProduct(), editOrderDescriptor.getQuantity());
            editedOrder.setDeadline(this.editOrderDescriptor.getDeadline());
        }

        return editedOrder;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditOrderCommand)) {
            return false;
        }

        EditOrderCommand otherEditOrderCommand = (EditOrderCommand) other;
        return orderIndex.equals(otherEditOrderCommand.orderIndex)
                && productIndex.equals(otherEditOrderCommand.productIndex)
                && editOrderDescriptor.equals(otherEditOrderCommand.editOrderDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("orderIndex", orderIndex)
                .add("productIndex", productIndex)
                .add("editPersonDescriptor", editOrderDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditOrderDescriptor {
        private Product product;
        private Quantity quantity;
        private Deadline deadline;

        public EditOrderDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditOrderDescriptor(EditOrderDescriptor toCopy) {
            setProduct(toCopy.product);
            setQuantity(toCopy.quantity);
            setDeadline(toCopy.deadline);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAllFieldsEdited() {
            return product != null && quantity != null;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        public Product getProduct() {
            return product;
        }

        public void setQuantity(Quantity quantity) {
            this.quantity = quantity;
        }

        public Quantity getQuantity() {
            return quantity;
        }

        public void setDeadline(Deadline deadline) {
            this.deadline = deadline;
        }

        public Deadline getDeadline() {
            return this.deadline;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditOrderDescriptor)) {
                return false;
            }

            EditOrderDescriptor otherEditOrderDescriptor = (EditOrderDescriptor) other;
            return Objects.equals(product, otherEditOrderDescriptor.product)
                    && Objects.equals(quantity, otherEditOrderDescriptor.quantity);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("product", product)
                    .add("quantity", quantity)
                    .toString();
        }
    }
}
