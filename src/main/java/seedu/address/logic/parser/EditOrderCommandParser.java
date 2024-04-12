package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MENU;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_QUANTITY;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditOrderCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input for EditOrderCommand.
 */
public class EditOrderCommandParser implements Parser<EditOrderCommand> {
    /**
     * Parses input arguments and creates a new EditOrderCommand object.
     */
    public EditOrderCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_ORDER, PREFIX_MENU, PREFIX_PRODUCT_QUANTITY, PREFIX_DEADLINE);

        Index orderIndex;
        Index productIndex;

        try {
            orderIndex = ParserUtil.parseIndex(
                    argMultimap.getValue(PREFIX_ORDER)
                            .orElseThrow(() -> new ParseException("")));
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditOrderCommand.MESSAGE_USAGE), pe);
        }

        try {
            productIndex = ParserUtil.parseIndex(
                    argMultimap.getValue(PREFIX_ORDER)
                            .orElseThrow(() -> new ParseException("")));
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditOrderCommand.MESSAGE_USAGE), pe);
        }

        EditOrderCommand.EditOrderDescriptor editOrderDescriptor =
                new EditOrderCommand.EditOrderDescriptor();

        if (argMultimap.getValue(PREFIX_MENU).isPresent()) {
            productIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_MENU).get());
        }
        if (argMultimap.getValue(PREFIX_PRODUCT_QUANTITY).isPresent()) {
            editOrderDescriptor.setQuantity(ParserUtil.parseQuantity(
                    argMultimap.getValue(PREFIX_PRODUCT_QUANTITY).get()));
        }

        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            editOrderDescriptor.setDeadline(ParserUtil.parseDeadline(
                    argMultimap.getValue(PREFIX_DEADLINE).get()));
        }

        if ((argMultimap.getValue(PREFIX_MENU).isPresent()
                && !argMultimap.getValue(PREFIX_PRODUCT_QUANTITY).isPresent())
                || (!argMultimap.getValue(PREFIX_MENU).isPresent()
                && argMultimap.getValue(PREFIX_PRODUCT_QUANTITY).isPresent())) {
            throw new ParseException(EditOrderCommand.MESSAGE_NOT_EDITED);
        }

        if (!argMultimap.getValue(PREFIX_MENU).isPresent()
                && !argMultimap.getValue(PREFIX_PRODUCT_QUANTITY).isPresent()
                && !argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            throw new ParseException(EditOrderCommand.MESSAGE_NOT_EDITED_EXTRA);
        }


        return new EditOrderCommand(orderIndex, productIndex, editOrderDescriptor);
    }
}
