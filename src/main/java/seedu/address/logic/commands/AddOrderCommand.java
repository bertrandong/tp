package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Optional;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.Deadline;
import seedu.address.model.order.Order;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * Adds an order to the address book.
 */
public class AddOrderCommand extends Command {
    public static final String COMMAND_WORD = "order";
    public static final String MESSAGE_ARGUMENTS = "Index: %1$d";
    public static final String MESSAGE_ADD_ORDER_SUCCESS = "Added order to Person: %1$s";
    public static final String MESSAGE_DELETE_ORDER_SUCCESS = "Removed order from Person: %1$s";
    public static final String MESSAGE_ADD_PRODUCTS = "Add products using this command: "
            + "product m/[PRODUCT_ID] pq/[QUANTITY]";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the order of the person identified "
            + "by the phone number of person. "
            + "\n"
            + "Parameters: phone number (must be a positive integer) "
            + "p/[PHONE_NUMBER]\n"
            + "Example: " + COMMAND_WORD
            + " p/87438807.";

    private final Phone phone;
    private Order order;

    /**
     * Constructs an {@code AddOrderCommand} with the given {@code Phone}.
     * @param phone
     */
    public AddOrderCommand(Phone phone) {
        requireAllNonNull(phone);

        this.phone = phone;
        this.order = new Order();
    }

    /**
     * Creates an {@code AddOrderCommand} to add an order associated with the specified phone number and deadline.
     * This constructor initializes a new order with the given deadline
     * and associates it with the provided phone number.
     * The newly created order is set as the last order processed by this command.
     * <p>
     * Note: The phone number is assumed to be validated before being passed to this constructor.
     *
     * @param phone The phone number associated with the new order.
     *              This parameter is required and cannot be {@code null}.
     * @param deadline The deadline for the new order. It can be {@code null}
     *                 if the order does not have a specific deadline.
     * @throws NullPointerException if the {@code phone} parameter is {@code null}.
     */
    public AddOrderCommand(Phone phone, Deadline deadline) {
        requireAllNonNull(phone);

        this.phone = phone;
        this.order = new Order();
        this.order.setDeadline(deadline);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (!Phone.isValidPhone(phone.toString())) {
            throw new CommandException(Messages.MESSAGE_INVALID_PHONE_NUMBER);
        }

        // To Check if phone number belongs to existing person
        Optional<Person> maybeEditablePerson = model.findPersonByPhoneNumber(this.phone.value);
        if (!maybeEditablePerson.isPresent()) {
            throw new CommandException(Messages.MESSAGE_PHONE_NUMBER_NOT_FOUND);
        }
        Person personToEdit = maybeEditablePerson.get();
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTags(), personToEdit.getOrders());

        this.order.setCustomer(editedPerson);
        AddProductCommand.setLastOrder(this.order);
        AddProductCommand.setPersonToEdit(personToEdit);


        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a success message with a {@code Person}
     * @param personToEdit
     * @return
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !order.isEmpty() ? MESSAGE_ADD_ORDER_SUCCESS : MESSAGE_ADD_PRODUCTS;
        return String.format(message, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof AddOrderCommand)) {
            return false;
        }
        AddOrderCommand e = (AddOrderCommand) other;
        return phone == e.phone;
    }
}
