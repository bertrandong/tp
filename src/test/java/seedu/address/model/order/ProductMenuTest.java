package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.exceptions.DuplicateProductException;
import seedu.address.model.exceptions.ProductNotFoundException;
import seedu.address.model.person.UniquePersonList;
import seedu.address.testutil.ProductBuilder;

public class ProductMenuTest {
    private final ProductMenu productMenu = new ProductMenu();

    @Test
    public void contains_nullProduct_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> productMenu.contains(null));
    }

    @Test
    public void contains_productNotInMenu_returnsFalse() {
        assertFalse(productMenu.contains(new ProductBuilder().build()));
    }

    @Test
    public void contains_productInMenu_returnsTrue() {
        productMenu.addProduct(new ProductBuilder().build());
        assertTrue(productMenu.contains(new ProductBuilder().build()));
    }

    @Test
    public void addProduct_nullProduct_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> productMenu
                .addProduct(null));
    }

    @Test
    public void editProduct_nullTargetProduct_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> productMenu.editProduct(null, null));
    }

    @Test
    public void editProduct_nullEditedProduct_throwsNullPointException() {
        assertThrows(NullPointerException.class, () -> productMenu.editProduct(null, null));
    }

    @Test
    public void editProduct_targetProductNotInList_throwsProductNotFoundException() {
        assertThrows(ProductNotFoundException.class, () -> productMenu
                .editProduct(new ProductBuilder().build(), new ProductBuilder().withName("Tart").build()));
    }

    @Test
    public void editProduct_editedProductIsSameProduct_success() {
        productMenu.addProduct(new ProductBuilder().build());
        productMenu.editProduct(new ProductBuilder().build(), new ProductBuilder().build());
        ProductMenu expectedProductMenu = new ProductMenu();
        expectedProductMenu.addProduct(new ProductBuilder().build());
        assertEquals(expectedProductMenu, productMenu);
    }

    @Test
    public void editProduct_editedProductIsDifferentProduct_success() {
        productMenu.addProduct(new ProductBuilder().build());
        productMenu.editProduct(new ProductBuilder().build(), new ProductBuilder().withName("Tart").build());
        ProductMenu expectedProductMenu = new ProductMenu();
        expectedProductMenu.addProduct(new ProductBuilder().withName("Tart").build());
        assertEquals(expectedProductMenu, productMenu);
    }

    @Test
    public void editProduct_editedProductHasNonUniqueName_throwsDuplicateProductException() {
        Product targetProduct = new ProductBuilder().build();
        Product replacingProduct = new ProductBuilder().withName("Tart").build();
        productMenu.addProduct(targetProduct);
        productMenu.addProduct(replacingProduct);
        assertThrows(DuplicateProductException.class, () -> productMenu.editProduct(targetProduct,
                replacingProduct));
    }

    @Test
    public void deleteProduct_nullProduct_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> productMenu.deleteProduct(null));
    }

    @Test
    public void deleteProduct_productDoesNotExist_throwsProductNotFoundException() {
        assertThrows(ProductNotFoundException.class, () -> productMenu.deleteProduct(new ProductBuilder().build()));
    }

    @Test
    public void deleteProduct_existingProduct_removesProduct() {
        productMenu.addProduct(new ProductBuilder().build());
        productMenu.deleteProduct(new ProductBuilder().build());
        ProductMenu expectedProductMenu = new ProductMenu();
        assertEquals(expectedProductMenu, productMenu);
    }

    @Test
    public void setProducts_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> productMenu.setProducts(null));
    }

    @Test
    public void setProducts_list_replacesOwnListWithProvidedList() {
        productMenu.addProduct(new ProductBuilder().build());
        List<Product> productList = Collections.singletonList(new ProductBuilder().withName("Tart").build());
        productMenu.setProducts(productList);
        ProductMenu expectedProductMenu = new ProductMenu();
        expectedProductMenu.addProduct(new ProductBuilder().withName("Tart").build());
        assertEquals(expectedProductMenu, productMenu);
    }

    @Test
    public void setProducts_listWithDuplicateProducts_throwsDuplicateProductException() {
        List<Product> listWithDuplicateProducts = Arrays.asList(new ProductBuilder().build(),
                new ProductBuilder().build());
        assertThrows(DuplicateProductException.class, () -> productMenu.setProducts(listWithDuplicateProducts));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> productMenu.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(productMenu.asUnmodifiableObservableList().toString(), productMenu.toString());
    }
}
