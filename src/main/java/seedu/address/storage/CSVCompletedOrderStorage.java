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

    public CSVCompletedOrderStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAddressBookFilePath() {
        return filePath;
    }

    public void saveCompletedOrder(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        ObservableList<Order> completedOrderList = addressBook.getCompletedOrderList();
        List<String[]> dataToBeStored = convertOrdersToReadable(completedOrderList);
        CSVUtil.saveCSVFile(filePath, dataToBeStored);
    }

    public List<String[]> convertOrdersToReadable(List<Order> orderList) {
        List<String[]> data = new ArrayList<>();
        for(Order order : orderList) {
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
        return orderDetails.toArray(STRING_TYPE_SPECIFIER);
    }

    public String convertOrderIdToReadable(Order order) {
        return Integer.toString(order.getId());
    }

    public String convertOrderCustomerToReadable(Order order) {
        return order.getCustomer().getName().fullName;
    }

    public String convertProductToReadable(Product product) {
        return product.getName();
    }

    public String convertQuantityToReadable(Order order, Product product) {
        return Integer.toString(order.getProductMap().get(product).getValue());
    }
}
