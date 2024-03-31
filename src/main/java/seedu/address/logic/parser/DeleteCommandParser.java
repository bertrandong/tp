package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CUSTOMER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MENU;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        requireNonNull(args);

        if (args.startsWith(" " + PREFIX_CUSTOMER_ID.toString())) {
            DeleteCustomerCommandParser deleteCustomerCommandParser = new DeleteCustomerCommandParser();
            return deleteCustomerCommandParser.parse(args);
        } else if (args.startsWith(" " + PREFIX_MENU.toString())) {
            DeleteMenuCommandParser deleteMenuCommandParser = new DeleteMenuCommandParser();
            return deleteMenuCommandParser.parse(args);
        } else {
            throw new ParseException(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }

}
