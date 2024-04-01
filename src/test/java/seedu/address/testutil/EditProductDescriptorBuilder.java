package seedu.address.testutil;

import seedu.address.logic.commands.EditMenuCommand.EditProductDescriptor;
import seedu.address.model.order.Product;

/**
 * A utility class to help with building EditProductDescriptor objects.
 */
public class EditProductDescriptorBuilder {
    private EditProductDescriptor descriptor;

    public EditProductDescriptorBuilder() {
        descriptor = new EditProductDescriptor();
    }

    public EditProductDescriptorBuilder(EditProductDescriptor descriptor) {
        this.descriptor = new EditProductDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditProductDescriptor} with fields containing {@code product}'s details
     */
    public EditProductDescriptorBuilder(Product product) {
        descriptor = new EditProductDescriptor();
        descriptor.setName(product.getName());
    }

    /**
     * Sets the name of the {@code EditProductDescriptor} that we are building.
     */
    public EditProductDescriptorBuilder withName(String name) {
        descriptor.setName(name);
        return this;
    }

    public EditProductDescriptor build() {
        return descriptor;
    }
}
