package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.util.CsvUtil;
import seedu.address.commons.util.FileUtil;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.order.Order;
import seedu.address.model.order.Product;

/**
 * Class that stores completed orders temperari
 */
public class CsvCompletedOrderStorage implements CompletedOrderStorage {

    private static final String[] STRING_TYPE_SPECIFIER = new String[0];

    private static final String[] TABLE_HEADERS = new String[] {"OrderID", "Customer Name", "Product Name", "Quantity",
        "Subtotal Cost of Goods", "Subtotal Revenue of Goods", "Subtotal Profit of Goods"};

    private Path filePath;

    public CsvCompletedOrderStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAddressBookFilePath() {
        return filePath;
    }

    /**
     * Saves the completed {@code Order}s into a Csv file for archiving purposes.
     * @param addressBook Addressbook containing the completed order.
     * @throws IOException
     */
    public void saveCompletedOrders(ReadOnlyAddressBook addressBook) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        List<Order> completedOrderList = addressBook.getCompletedOrderList();
        List<String[]> dataToBeStored = convertOrdersToReadable(completedOrderList);
        CsvUtil.saveCsvFile(filePath, dataToBeStored, TABLE_HEADERS);
    }

    private List<String[]> convertOrdersToReadable(List<Order> orderList) {
        List<String[]> data = new ArrayList<>();
        for (Order order : orderList) {
            order.getProductMap().keySet().stream().forEach(x -> data.add(convertProductMapToReadable(order, x)));
        }
        return data;
    }

    private String[] convertProductMapToReadable(Order order, Product product) {
        requireNonNull(order);
        requireNonNull(product);

        ArrayList<String> orderDetails = new ArrayList<>();
        orderDetails.add(convertOrderIdToReadable(order));
        orderDetails.add(convertOrderCustomerToReadable(order));
        orderDetails.add(convertProductToReadable(product));
        orderDetails.add(convertQuantityToReadable(order, product));
        orderDetails.add(convertSubtotalCostToReadable(order, product));
        orderDetails.add(convertSubtotalRevenueToReadable(order, product));
        orderDetails.add(convertSubtotalProfitToReadable(order, product));
        return orderDetails.toArray(STRING_TYPE_SPECIFIER);
    }

    private String convertOrderIdToReadable(Order order) {
        return Integer.toString(order.getId());
    }

    private String convertOrderCustomerToReadable(Order order) {
        return order.getCustomer().getName().fullName;
    }

    private String convertProductToReadable(Product product) {
        return product.getName();
    }

    private String convertQuantityToReadable(Order order, Product product) {
        return Integer.toString(order.getProductMap().get(product).getValue());
    }

    private String convertSubtotalCostToReadable(Order order, Product product) {
        float quantity = order.getProductMap().get(product).getValue();
        float unitCost = Float.parseFloat(product.getCost());
        float subtotalCost = quantity * unitCost;
        return Float.toString(subtotalCost);
    }

    private String convertSubtotalRevenueToReadable(Order order, Product product) {
        float quantity = order.getProductMap().get(product).getValue();
        float unitRevenue = Float.parseFloat(product.getSales());
        float subtotalRevenue = quantity * unitRevenue;
        return Float.toString(subtotalRevenue);
    }

    private String convertSubtotalProfitToReadable(Order order, Product product) {
        float subtotalCost = Float.parseFloat(convertSubtotalCostToReadable(order, product));
        float subtotalRevenue = Float.parseFloat(convertSubtotalRevenueToReadable(order, product));
        float subtotalProfit = subtotalRevenue - subtotalCost;
        return Float.toString(subtotalProfit);
    }
}
