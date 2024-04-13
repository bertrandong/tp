package seedu.address.storage;


import java.io.IOException;
import java.nio.file.Path;

import seedu.address.model.ReadOnlyAddressBook;

/**
 * Represents a storage for completed {@code Order}.
 */
public interface CompletedOrderStorage {

    /**
     * Returns the data file path for completed orders.
     * @return the data file path for completed orders.
     */
    Path getAddressBookFilePath();

    /**
     * Saves the completed {@code Order}s into a Csv file for archiving purposes.
     * @param addressBook Addressbook containing the completed order.
     * @throws IOException
     */
    void saveCompletedOrders(ReadOnlyAddressBook addressBook) throws IOException;
}
