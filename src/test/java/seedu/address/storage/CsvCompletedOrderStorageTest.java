package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.testutil.TypicalPersons;

public class CsvCompletedOrderStorageTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "CSVCompleteOrderStorageTest");

    @TempDir
    public Path testFolder = Paths.get("src", "test", "data", "CSVCompleteOrderStorageTest");;

    private Path testDataPath(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    private AddressBook addressBookContainingCompletedOrder() {
        AddressBook ab = TypicalPersons.getTypicalAddressBook();
        ab.completeOrder(1);
        return ab;
    }

    /**
     * Saves {@code addressBook}'s completed order list at the specified {@code filePath}.
     */
    private void saveCompletedOrders(ReadOnlyAddressBook addressBook, Path filePath) {
        try {
            new CsvCompletedOrderStorage(filePath)
                    .saveCompletedOrders(addressBook);
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveCompletedOrder_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCompletedOrders(null,
                        testDataPath("CompletedOrderStorageTest.csv")));
    }

    @Test
    public void saveCompletedOrder_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCompletedOrders(new AddressBook(),
                        testDataPath(null)));
    }

    @Test
    public void saveCompletedOrder_completedOrder_success() throws IOException {
        saveCompletedOrders(addressBookContainingCompletedOrder(),
                testFolder.resolve("CompletedOrderStorageTest.csv"));
        assertEquals(Files.readAllLines(testFolder.resolve("CompletedOrderStorageTest.csv")),
                Files.readAllLines(testDataPath("CompletedOrderStorageTestOneCompletedOrder.csv")));
    }

}
