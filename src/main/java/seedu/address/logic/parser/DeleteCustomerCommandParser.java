package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCustomerCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteCustomerCommandParser implements Parser<DeleteCustomerCommand> {
    public DeleteCustomerCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_CUSTOMER_ID, PREFIX_NAME,
                        PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_CUSTOMER_ID)
                    .orElseThrow(() -> new ParseException("")));
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCustomerCommand.MESSAGE_USAGE), pe);
        }

        return new DeleteCustomerCommand(index);
    }
}
