package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderList;
import seedu.address.model.order.Product;
import seedu.address.model.order.ProductMenu;
import seedu.address.model.order.Quantity;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final OrderList activeOrders;
    private final OrderList completedOrders;
    private final ProductMenu menu;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        activeOrders = new OrderList();
        completedOrders = new OrderList();
        menu = new ProductMenu();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    public void setOrders(List<Order> orders) {
        this.activeOrders.setOrders(orders);
    }

    public void setCompletedOrders(List<Order> completedOrders) {
        this.completedOrders.setOrders(completedOrders);
    }

    public void setProducts(List<Product> products) {
        this.menu.setProducts(products);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setOrders(newData.getOrderList());
        setCompletedOrders(newData.getCompletedOrderList());
        setOrderListIdCounter(newData.getOrderListCounter());
        setProducts(newData.getMenuList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Returns true if a product with the same identity as {@code product} exists in the menu.
     */
    public boolean hasProduct(Product product) {
        requireNonNull(product);
        return menu.contains(product);
    }

    /**
     * Adds a product to the menu.
     * The product must not already exist in the menu.
     */
    public void addProduct(Product product) {
        menu.addProduct(product);
    }

    /**
     * Adds an {@code Order} to the {@code OrderList} of this address book.
     *
     */
    public void addOrder(Order order) {
        int orderCounter = activeOrders.getOrderIdCounter();
        order.setID(orderCounter);

        Person editedCustomer = order.getCustomer();
        ArrayList<Order> listToEdit = editedCustomer.getOrders();
        listToEdit.add(order);
        editedCustomer.setOrders(listToEdit);
        this.setPerson(order.getCustomer(), editedCustomer);

        activeOrders.addOrder(order);
    }

    /**
     * Returns true if an order with the same identity exists in the OrderList.
     */
    public boolean hasOrder(Order order) {
        requireNonNull(order);
        return activeOrders.contains(order);
    }

    /**
     * Adds an {@code Order} to the {@code OrderList} of this addressbook with a predefined ID (for storage purposes).
     * @param order Order Object to be added into the Order List.
     */
    public void addOrderWithID(Order order) {
        int orderId = order.getId();
        activeOrders.addOrderWithID(order, orderId);
    }

    public void setOrderListIdCounter(int counter) {
        activeOrders.setOrderIdCounter(counter);
    }

    @Override
    public Integer getOrderListCounter() {
        return activeOrders.getOrderIdCounter();
    }

    public Order findOrderByIndex(int id) {
        return activeOrders.getOrder(id);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    public void setOrder(Order target, Order edittedOrder) {
        requireNonNull(edittedOrder);

        activeOrders.setOrder(target, edittedOrder);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public Order editOrder(Order target, Product currProduct, Quantity newQuantity) {
        requireNonNull(target);
        return target.updateOrder(currProduct, newQuantity);
    }

    /**
     * Advances the target order to the next stage.
     *
     * @param target order to update
     * @return a copy of modified order.
     */
    public Order goToNextStage(Order target) {
        requireNonNull(target);
        return target.goToNextStage();
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    /**
     * Removes {@code Order} from the {@code OrderList} of this {@code AddressBook}.
     *
     * @param id index of order to remove.
     */
    public void removeOrder(int id) {
        Order orderToDelete = this.findOrderByIndex(id);

        Person editedCustomer = orderToDelete.getCustomer();
        ArrayList<Order> listToEdit = editedCustomer.getOrders();
        listToEdit.remove(orderToDelete);
        editedCustomer.setOrders(listToEdit);
        this.setPerson(orderToDelete.getCustomer(), editedCustomer);

        activeOrders.deleteOrder(id);
    }

    /**
     * Completes {@code Order} by removing it from the activeOrders and adding it into the completedOrders.
     * @param id index of order to remove.
     */
    public void completeOrder(int id) {
        Order orderToComplete = this.findOrderByIndex(id);
        Person editedCustomer = orderToComplete.getCustomer();
        ArrayList<Order> listToEdit = editedCustomer.getOrders();
        listToEdit.remove(orderToComplete);
        editedCustomer.setOrders(listToEdit);
        this.setPerson(orderToComplete.getCustomer(), editedCustomer);

        activeOrders.deleteOrder(id);
        completedOrders.addOrderWithID(orderToComplete, orderToComplete.getId());
    }


    public void clearCompletedOrders() {
        completedOrders.clearOrders();
    }

    public boolean orderIdExists(int orderId) {
        return activeOrders.orderIdExist(orderId);
    }
    /**
     * Removes {@code Product} from the {@code ProductMenu} of this {@code AddressBook}.
     * @param key product to remove
     */
    public void removeProduct(Product key) {
        menu.deleteProduct(key);
    }

    /**
     * Replaces the given product {@code target} in the list with {@code editedProduct}.
     * {@code target} must exist in the menu.
     * The product identity of {@code editedProduct} must not be the same as another existing product in the menu.
     */
    public void setProduct(Product target, Product editedProduct) {
        requireNonNull(editedProduct);

        menu.editProduct(target, editedProduct);
    }

    /**
     * Gets a product in the {@code ProductMenu} by the zero-based Index.
     *
     * @param id the index of the {@code Product} to search for
     * @return the {@code Product} to search for
     */
    public Product findProductByIndex(int id) {
        return menu.findProductByIndex(id);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .toString();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    //Make sure to implement abstract method for this
    public ObservableList<Order> getOrderList() {
        return activeOrders.asUnmodifiableObservableList();
    }

    public ObservableList<Order> getCompletedOrderList() {
        return completedOrders.asUnmodifiableObservableList();
    }

    public ObservableList<Product> getMenuList() {
        return menu.asUnmodifiableObservableList();
    }

    public OrderList getOrderListClass() {
        return activeOrders;
    }

    public int getOrderListSize() {
        return activeOrders.size();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return persons.equals(otherAddressBook.persons) && activeOrders.equals(otherAddressBook.activeOrders);
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
