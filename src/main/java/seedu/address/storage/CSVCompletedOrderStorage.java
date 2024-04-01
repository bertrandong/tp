package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.scene.layout.BorderRepeat;
import seedu.address.commons.util.CSVUtil;
import seedu.address.commons.util.FileUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderList;
import seedu.address.model.order.Product;
import seedu.address.model.order.Quantity;

/**
 * Class that stores completed orders temperari
 */
public class CSVCompletedOrderStorage {

    private Path filePath;

    private final String[] STRING_TYPE_SPECIFIER = new String[0];

    private final String[] TABLE_HEADERS = new String[] {
            "OrderID", "Customer Name", "Product Name", "Quantity"
    };

    public CSVCompletedOrderStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAddressBookFilePath() {
        return filePath;
    }

    public void saveCompletedOrder(ReadOnlyAddressBook addressBook) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        OrderList completedOrderList = addressBook.getCompletedOrderList();
        List<String[]> dataToBeStored = convertOrdersToReadable(completedOrderList);
        CSVUtil.saveCSVFile(filePath, dataToBeStored, TABLE_HEADERS);
    }

    public List<String[]> convertOrdersToReadable(OrderList orderList) {
        List<Order> list = orderList.getOrderList();
        List<String[]> data = new ArrayList<>();
        for(Order order : list) {
            order.getProductMap().keySet().stream().forEach(x -> data.add(convertProductMapToReadable(order, x)));
        }
        return data;
    }

    public String[] convertProductMapToReadable(Order order, Product product) {
        requireNonNull(order);
        requireNonNull(product);

        ArrayList<String> orderDetails = new ArrayList<>();
        orderDetails.add(convertOrderIdToReadable(order));
        orderDetails.add(convertOrderCustomerToReadable(order));
        orderDetails.add(convertProductToReadable(product));
        orderDetails.add(convertQuantityToReadable(order, product));
        System.out.println(orderDetails.toArray(STRING_TYPE_SPECIFIER));
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
}
