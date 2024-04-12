package seedu.address.storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonProcessingException;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.order.Order;
import seedu.address.model.order.Product;
import seedu.address.model.person.Person;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final Integer DEFAULT_EMPTY_ORDER_COUNTER = 1;

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedOrder> orders = new ArrayList<>();
    private final List<JsonAdaptedProduct> menu = new ArrayList<>();
    private final Integer orderIdCounter;

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                       @JsonProperty("orders") List<JsonAdaptedOrder> orders,
                                       @JsonProperty("orderIdCounter") Integer orderIdCounter,
                                       @JsonProperty("menu") List<JsonAdaptedProduct> menu) {
        this.persons.addAll(persons);
        this.orders.addAll(orders);
        this.orderIdCounter = orderIdCounter;
        this.menu.addAll(menu);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) throws JsonProcessingException {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        for (Order order : source.getOrderList()) {
            orders.add(new JsonAdaptedOrder(order));
        }
        //orders.addAll(source.getOrderList().stream().map(JsonAdaptedOrder::new).collect(Collectors.toList()));
        this.orderIdCounter = source.getOrderListCounter();
        menu.addAll(source.getMenuList().stream().map(JsonAdaptedProduct::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException, IOException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedProduct jsonAdaptedProduct : menu) {
            Product product = jsonAdaptedProduct.toModelType();
            addressBook.addProduct(product);

        }
        for (JsonAdaptedOrder jsonAdaptedOrder : orders) {
            Order order = jsonAdaptedOrder.toModelType();
            addressBook.addOrderWithID(order);
        }
        Optional<Integer> orderIdCounter = Optional.ofNullable(this.orderIdCounter);
        addressBook.setOrderListIdCounter(orderIdCounter.orElse(DEFAULT_EMPTY_ORDER_COUNTER));
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            for (int orderId : jsonAdaptedPerson.getOrderIdList()) {
                Order currOrder = addressBook.getOrderListClass().getOrder(orderId);
                person.addOrder(currOrder);
                currOrder.setCustomer(person);
            }
            addressBook.addPerson(person);
        }
        return addressBook;
    }

}
