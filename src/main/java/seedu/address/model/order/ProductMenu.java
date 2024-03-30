package seedu.address.model.order;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.exceptions.DuplicateProductException;
import seedu.address.model.exceptions.ProductNotFoundException;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Represents the menu of what products are being sold.
 */
public class ProductMenu implements Iterable<Product> {
    private final ObservableList<Product> internalList = FXCollections.observableArrayList();
    private final ObservableList<Product> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Adds a product to the product menu.
     * @param toAdd The product that is to be added.
     */
    public void addProduct(Product toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateProductException();
        }
        internalList.add(toAdd);
    }

    /**
     * Deletes a product from the product menu.
     * @param toDelete The product that is to be deleted.
     */
    public void deleteProduct(Product toDelete) {
        requireNonNull(toDelete);
        if (!internalList.remove(toDelete)) {
            throw new PersonNotFoundException();
        }
    }

    /**
     * Replaces the product in the product menu given an index.
     * @param target The product that is to be edited.
     * @param editedProduct The new product to be used to replace the old product.
     */
    public void editProduct(Product target, Product editedProduct) {
        requireAllNonNull(target, editedProduct);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ProductNotFoundException();
        }

        internalList.set(index, editedProduct);
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if the list contains an equivalent product as the given argument.
     */
    public boolean contains(Product toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameProduct);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Product> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Product> iterator() {
        return internalList.iterator();
    }

    /**
     * Returns true if {@code products} contains only unique products.
     */
    private boolean productsAreUnique(List<Product> products) {
        for (int i = 0; i < products.size() - 1; i++) {
            for (int j = i + 1; j < products.size(); j++) {
                if (products.get(i).isSameProduct(products.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ProductMenu)) {
            return false;
        }

        ProductMenu otherProductMenu = (ProductMenu) other;
        return internalList.equals(otherProductMenu.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setProducts(List<Product> products) {
        requireAllNonNull(products);
        if (!productsAreUnique(products)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(products);
    }
}
