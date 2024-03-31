package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CUSTOMER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MENU;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input for EditCommand for customer and order.
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CUSTOMER_ID, PREFIX_ORDER);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_CUSTOMER_ID, PREFIX_ORDER);

        //if (ParserUtil.isCustomer(args)) {
        //    EditCustomerCommandParser editCustomerCommandParser = new EditCustomerCommandParser();
        //    return editCustomerCommandParser.parse(args);
        //} else {
        //    EditOrderCommandParser editOrderCommandParser = new EditOrderCommandParser();
        //    return editOrderCommandParser.parse(args);
        //}

        if (args.startsWith(" " + PREFIX_CUSTOMER_ID.toString())) {
            EditCustomerCommandParser editCustomerCommandParser = new EditCustomerCommandParser();
            return editCustomerCommandParser.parse(args);
        } else if (args.startsWith(" " + PREFIX_ORDER.toString())) {
            EditOrderCommandParser editOrderCommandParser = new EditOrderCommandParser();
            return editOrderCommandParser.parse(args);
        } else if (args.startsWith(" " + PREFIX_MENU.toString())) {
            EditMenuCommandParser editMenuCommandParser = new EditMenuCommandParser();
            return editMenuCommandParser.parse(args);
        } else {
            throw new ParseException(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }
}
