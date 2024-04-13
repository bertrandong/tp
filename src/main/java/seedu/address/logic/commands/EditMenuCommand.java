package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MENU;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PRODUCTS;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.Product;

/**
 * Edits the details of an existing product on the menu.
 */
public class EditMenuCommand extends EditCommand {
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the details of the product identified "
            + "by the index number used in the displayed menu list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: [" + PREFIX_MENU + "INDEX (must be a positive integer)] "
            + "[" + PREFIX_PRODUCT_NAME + "PRODUCT_QUANTITY] "
            + "Example: " + COMMAND_WORD + " " + PREFIX_MENU + "1 "
            + PREFIX_PRODUCT_NAME + "cupcake";

    public static final String MESSAGE_EDIT_PRODUCT_SUCCESS = "Edited Product: %1$s";

    public static final String MESSAGE_NOT_EDITED =
            "Either a new product name, product cost or product sales must be provided.";

    public static final String MESSAGE_DUPLICATE_PRODUCT = "This product already exists in the menu.";

    private final Index index;
    private final EditProductDescriptor editProductDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editProductDescriptor details to edit the person with
     */
    public EditMenuCommand(Index index, EditProductDescriptor editProductDescriptor) {
        requireNonNull(index);
        requireNonNull(editProductDescriptor);

        this.index = index;
        this.editProductDescriptor = new EditProductDescriptor(editProductDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Product> lastShownList = model.getFilteredMenuList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX);
        }

        Product productToEdit = new Product(lastShownList.get(index.getZeroBased()));
        Product editedProduct = createEditedProduct(productToEdit, editProductDescriptor);

        if (!productToEdit.isSameProduct(editedProduct) && model.hasProduct(editedProduct)) {
            throw new CommandException(MESSAGE_DUPLICATE_PRODUCT);
        }

        model.setProduct(productToEdit, editedProduct);
        model.updateFilteredMenuList(PREDICATE_SHOW_ALL_PRODUCTS);
        return new CommandResult(String.format(MESSAGE_EDIT_PRODUCT_SUCCESS,
                Messages.format(editedProduct)));
    }

    /**
     * Creates and returns a {@code Product} with the details of {@code productToEdit}
     * edited with {@code editProductDescriptor}.
     */
    private static Product createEditedProduct(Product productToEdit, EditProductDescriptor editProductDescriptor) {
        assert productToEdit != null;

        String cost = editProductDescriptor.getCost().orElse(productToEdit.getCost());
        String sales = editProductDescriptor.getSales().orElse(productToEdit.getSales());
        String name = editProductDescriptor.getName().orElse(productToEdit.getName());

        return new Product(name, cost, sales);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditMenuCommand)) {
            return false;
        }

        EditMenuCommand otherEditMenuCommand = (EditMenuCommand) other;
        return index.equals(otherEditMenuCommand.index)
                && editProductDescriptor.equals(otherEditMenuCommand.editProductDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editProductDescriptor", editProductDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the product with. Each non-empty field value will replace the
     * corresponding field value of the product.
     */
    public static class EditProductDescriptor {
        private String name;
        private String cost;
        private String sales;

        public EditProductDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditProductDescriptor(EditProductDescriptor toCopy) {
            setName(toCopy.name);
            setCost(toCopy.cost);
            setSales(toCopy.sales);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAllFieldsEdited() {
            return name != null || cost != null || sales != null;
        }

        public void setProduct(Product product) {
            this.name = product.getName();
            this.cost = product.getCost();
            this.sales = product.getSales();
        }

        public void setName(String name) {
            this.name = name;
        }

        public Optional<String> getName() {
            return Optional.ofNullable(name);
        }

        public void setCost(String cost) {
            this.cost = cost;
        }

        public Optional<String> getCost() {
            return Optional.ofNullable(cost);
        }

        public void setSales(String sales) {
            this.sales = sales;
        }

        public Optional<String> getSales() {
            return Optional.ofNullable(sales);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditProductDescriptor)) {
                return false;
            }

            EditProductDescriptor otherEditProductDescriptor = (EditProductDescriptor) other;
            return Objects.equals(name, otherEditProductDescriptor.name)
                    && Objects.equals(cost, otherEditProductDescriptor.cost)
                    && Objects.equals(sales, otherEditProductDescriptor.sales);

        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("cost", cost)
                    .add("sales", sales)
                    .toString();
        }
    }
}
