package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_SALES;

import java.util.stream.Stream;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddMenuCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.Product;

/**
 * Parses input for AddMenuCommand
 */
public class AddMenuCommandParser implements Parser<AddMenuCommand> {
    /**
     * Parses the user input to create a AddMenuCommand
     * @param args user input.
     * @return AddMenuCommand based on the user input.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddMenuCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_PRODUCT_NAME, PREFIX_PRODUCT_COST, PREFIX_PRODUCT_SALES);

        if (!arePrefixesPresent(argMultimap, PREFIX_PRODUCT_NAME, PREFIX_PRODUCT_COST, PREFIX_PRODUCT_SALES)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMenuCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_PRODUCT_NAME, PREFIX_PRODUCT_COST, PREFIX_PRODUCT_SALES);

        Product product;

        try {
            product = ParserUtil.parseProduct(argMultimap.getValue(PREFIX_PRODUCT_NAME).get());
            product.setCost(ParserUtil.parsePrice(argMultimap.getValue(PREFIX_PRODUCT_COST).get()));
            product.setSales(ParserUtil.parsePrice(argMultimap.getValue(PREFIX_PRODUCT_SALES).get()));
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddMenuCommand.MESSAGE_USAGE), ive);
        }

        return new AddMenuCommand(product);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
