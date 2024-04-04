package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MENU;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_QUANTITY;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddOrderCommand;
import seedu.address.logic.commands.AddProductCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.Quantity;

/**
 * Parses input for AddProductCommand
 */
public class AddProductCommandParser {
    /**
     * Parses the user input to create a AddOrderCommand
     * @param args user input.
     * @return AddOrderCommand based on the user input.
     * @throws ParseException
     */
    public AddProductCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_MENU, PREFIX_PRODUCT_QUANTITY);

        if (!arePrefixesPresent(argMultimap, PREFIX_MENU, PREFIX_PRODUCT_QUANTITY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProductCommand.MESSAGE_USAGE));
        }

        Index productId;
        Quantity quantity;

        try {
            productId = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_MENU).get());
            quantity = ParserUtil.parseQuantity(argMultimap.getValue(PREFIX_PRODUCT_QUANTITY).get());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddProductCommand.MESSAGE_USAGE), ive);
        }

        return new AddProductCommand(productId, quantity);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
