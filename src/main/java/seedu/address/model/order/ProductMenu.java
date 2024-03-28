package seedu.address.model.order;

import java.util.ArrayList;

/**
 * Represents the menu of what products are being sold.
 */
public class ProductMenu {
    /**
     * The array list containing all the products on the menu.
     */
    private ArrayList<Product> productMenu;

    /**
     * ProductMenu class constructor.
     */
    public ProductMenu() {
        this.productMenu = new ArrayList<>();
    }

    /**
     * Adds a product to the product menu.
     * @param toAdd The product that is to be added.
     */
    public void addProduct(Product toAdd) {
        productMenu.add(toAdd);
    }

    /**
     * Deletes a product from the product menu.
     * @param toDelete The index of the product that is to be deleted.
     */
    public void deleteProduct(int toDelete) {
        productMenu.remove(toDelete);
    }

    /**
     * Edits the product in the product menu given an index.
     * @param toEdit The index of the product that is to be edited.
     * @param editedProduct The new product to be used to replace the old product.
     */
    public void editProduct(int toEdit, Product editedProduct) {
        productMenu.set(toEdit, editedProduct);
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < productMenu.size(); i++) {
            s += i + ". " + productMenu.get(i).toString();
            s += "\n";
        }
        return s;
    }
}
