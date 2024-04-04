package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.fail;
import seedu.address.model.order.Deadline;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.exceptions.OrderNotFoundException;
import seedu.address.model.order.Order;
import seedu.address.model.order.Product;
import seedu.address.model.order.Quantity;
import seedu.address.model.person.Person;

public class EditOrderCommandTest {

    @Test
    public void invalidOrderIndex_throwsException() {
        EditOrderCommand command = new EditOrderCommand(Index.fromOneBased(1),
                new EditOrderCommand.EditOrderDescriptor());
        try {
            assertThrows(CommandException.class, () -> command.execute(new ModelStubGettingOrder()));
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * A default model stub that have all the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addOrder(Order newOrder, Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setOrder(Order target, Order editedOrder) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasOrder(Order target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Order getOrder(int id) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Order editOrder(Order order, Product product, Quantity quantity) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Order editOrderDeadline(Order order, Deadline deadline){
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Order goToNextStage(Order target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteOrder(int id) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Order findOrderByIndex(int id) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getOrderListSize() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void clearOrderFilter() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasProduct(Product product) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addProduct(Product product) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteProduct(Product target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setProduct(Product target, Product editedProduct) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Product findProductByIndex(int id) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Product> getFilteredMenuList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredMenuList(Predicate<Product> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Person> findPersonByPhoneNumber(String phoneNumber) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredOrderList(Predicate<Order> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Order> getFilteredOrderList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void clearCompletedOrders() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void completeOrder(int id) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean orderIdExists(int id) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A model stub which only supports basic index-handling of getOrder implementation.
     */
    private class ModelStubGettingOrder extends EditOrderCommandTest.ModelStub {

        @Override
        public Order getOrder(int id) throws OrderNotFoundException {
            if (id == 2) {
                return new Order();
            } else {
                throw new OrderNotFoundException();
            }
        }
    }
}
