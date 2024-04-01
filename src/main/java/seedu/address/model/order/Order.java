package seedu.address.model.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.order.stage.StageContext;
import seedu.address.model.person.Person;

/**
 * Represents a Customer's Order in the Addressbook.
 */
public class Order implements Comparable<Order> {
    public static final String MESSAGE_CONSTRAINTS = "Orders need to contain alphanumeric "
            + "product names and numeric quantities";
    private int id;
    private Map<Product, Quantity> productMap;

    private Person customer;
    private StageContext stageContext;
    private float totalCost = 0;
    private float totalSales = 0;

    private float profit = 0;

    /**
     * Constructs an {@code Order} Object.
     */
    public Order() {
        this.productMap = new HashMap<>();
        this.stageContext = new StageContext();
    }

    /**
     * Constructs an {@code Order} Object with {@code id}.
     *
     * @param id ID of the Order.
     */
    public Order(int id) {
        this.id = id;
        productMap = new HashMap<>();
        this.stageContext = new StageContext();
    }

    /**
     * Contructs an {@code Order} object with {@code map}.
     * @param map Mappings of Products and Quantity
     */
    public Order(int id, Person customer, Map<Product, Quantity> map, StageContext stageContext) {
        this.id = id;
        this.customer = customer;
        productMap = map;
        this.stageContext = stageContext;
        updateNumbers();
    }

    /**
     * Contructs an {@code Order} object copied from another {@code order}.
     * @param order The other order to copy from
     */
    public Order(Order order) {
        this.id = order.getId();
        this.productMap = new HashMap<>(order.getProductMap());
        this.customer = order.getCustomer();
        this.stageContext = order.stageContext;
        updateNumbers();
    }

    /**
     * Sets the Order ID.
     * @param id ID of the Order.
     */
    public void setID(int id) {
        this.id = id;
    }

    /**
     * Gets the Order ID.
     * @return the Order ID.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Gets the Product Quantity Map.
     * @return the Product Quantity Map.
     */
    public Map<Product, Quantity> getProductMap() {
        return this.productMap;
    }

    /**
     * Adds a specified quantity of Product into the order.
     * @param newProduct Product to be added.
     * @param newQuantity Quantity of Product to be added.
     */
    public void addProduct(Product newProduct, Quantity newQuantity) {
        productMap.put(newProduct, newQuantity);
        updateNumbers();
    }

    /**
     * Get quantity of this product in the order.
     * @param product Product of which quantity is returned.
     * @return quantity of the product.
     */
    public Optional<Quantity> getQuantity(Product product) {
        return Optional.ofNullable(productMap.get(product));
    }

    /**
     * Gets the quantity values of the product in the order.
     * @param product Product of which int quantity is returned.
     * @return int quantity of the product.
     */
    public int getQuantityValue(Product product) {
        int value = productMap.get(product).getValue();
        return value;
    }

    /**
     * Sets the quantity values of the product in the order.
     *
     * @param currProduct Product of which quantity to be edited.
     * @param newQuantity New Quantity of the specified product.
     * @return Updated order.
     */
    public Order changeQuantity(Product currProduct, Quantity newQuantity) {
        Map<Product, Quantity> newMap = new HashMap<>(productMap);
        newMap.put(currProduct, newQuantity);
        return new Order(this.id, this.customer, newMap, this.stageContext);
    }

    /**
     * Deletes the specified product from the order.
     * @param product Product to be deleted.
     * @return Updated order.
     */
    public Order deleteProduct(Product product) {
        Map<Product, Quantity> newMap = new HashMap<>(productMap);
        newMap.remove(product);
        return new Order(this.id, this.customer, newMap, this.stageContext);
    }

    /**
     * Updates the specified product from the order.
     * If new Quantity is zero, the product is removed from the map.
     * @param currProduct Product to be updated.
     * @param newQuantity Quantity to update to.
     * @return Updated order.
     */
    public Order updateOrder(Product currProduct, Quantity newQuantity) {
        return newQuantity.getValue() == 0
                ? deleteProduct(currProduct)
                : changeQuantity(currProduct, newQuantity);
    }

    /**
     * Clears the product map.
     */
    public void clearProductMap() {
        this.productMap.clear();
        updateNumbers();
    }

    /**
     * Sets the ProductMap of the order.
     *
     * @param productMap ProductMap to set to
     */
    public void setProductMap(Map<Product, Quantity> productMap) {
        this.productMap = productMap;
        updateNumbers();
    }

    /**
     * Checks if the product map is empty.
     * @return boolean value of whether the product map is empty.
     */
    public boolean isEmpty() {
        return productMap.isEmpty();
    }

    /**
     * Gets the {@code Person} ordering the order.
     *
     * @return the customer ordering the order
     */
    public Person getCustomer() {
        return this.customer;
    }

    /**
     * Sets the {@code Person} ordering the order.
     * @param person the customer that the order belongs to
     */
    public void setCustomer(Person person) {
        this.customer = person;
    }

    /**
     * Advances the order to the next stage.
     *
     * @return a new instance of modified order.
     */
    public Order goToNextStage() {
        this.stageContext.goToNextStage();
        return new Order(this);
    }

    public StageContext getStageContext() {
        return this.stageContext;
    }

    public void setStageContext(StageContext stageContext) {
        this.stageContext = stageContext;
    }

    /**
     * Compares the other Order Object with this Object based on the OrderID
     * @param otherOrder the object to be compared.
     * @return negative integer, zero, or a positive integer as this object is less than,
     *         equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Order otherOrder) {
        if (this.id < otherOrder.id) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * Checks if two orders are the same.
     * @param otherOrder The other order to be checked against.
     * @return A boolean value of whether the two orders are the same.
     */
    public boolean isSameOrder(Order otherOrder) {
        if (otherOrder == null) {
            return false;
        }
        if (otherOrder.equals(this)) {
            return true;
        }

        return otherOrder != null
                && otherOrder.id == this.id
                && otherOrder.customer.equals(this.customer);
    }

    /**
     * Updates the total sales of the order.
     */
    private void updateTotalSales() {
        float newTotalSales = 0;
        for (Map.Entry<Product, Quantity> entry : productMap.entrySet()) {
            newTotalSales += Float.parseFloat(entry.getKey().getSales()) * entry.getValue().getValue();
        }
        this.totalSales = newTotalSales;
    }

    /**
     * Updates the total cost of the order.
     */
    private void updateTotalCost() {
        float newTotalCost = 0;
        for (Map.Entry<Product, Quantity> entry : productMap.entrySet()) {
            newTotalCost += Float.parseFloat(entry.getKey().getCost()) * entry.getValue().getValue();
        }
        this.totalCost = newTotalCost;
    }

    /**
     * Updates the profit of the order.
     */
    private void updateProfit() {
        this.profit = this.totalSales - this.totalCost;
    }

    /**
     * Updates the cost, sales and profit of the order.
     */
    private void updateNumbers() {
        updateTotalCost();
        updateTotalSales();
        updateProfit();
    }
    /**
     * Gets the total sales of the order.
     *
     * @return the sales of the order
     */
    public float getTotalSales() {
        return this.totalSales;
    }

    /**
     * Gets the total cost of the order.
     *
     * @return the cost of the order
     */
    public float getTotalCost() {
        return this.totalCost;
    }

    /**
     * Gets the profit of the order.
     *
     * @return the cost of the order
     */
    public float getProfit() {
        return this.profit;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Order)) {
            return false;
        }
        Order otherOrder = (Order) other;

        return (this.id == otherOrder.id)
                && this.productMap.equals(otherOrder.productMap)
                && this.stageContext.equals(otherOrder.stageContext);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productMap);
    }

    @Override
    public String toString() {
        Set<Product> set = productMap.keySet();
        ArrayList<Product> productList = new ArrayList<>(set);
        StringBuilder str = new StringBuilder();
        for (Product product : productList) {
            str.append(product.getName());
            str.append(",");
            str.append(productMap.get(product).getValue());
            str.append("\n");
        }
        str.append(stageContext);
        str.append("\n");
        str.append("Total Cost: " + this.totalCost + "\n");
        str.append("Total Sales: " + this.totalSales + "\n");
        str.append("Profit: " + this.profit + "\n");
        return str.toString();
    }
}
