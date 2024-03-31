package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MENU;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_NAME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteMenuCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteMenuCommand object
 */
public class DeleteMenuCommandParser implements Parser<DeleteMenuCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteMenuCommand
     * and returns a DeleteMenuCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteMenuCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_MENU, PREFIX_PRODUCT_NAME);

        Index index;

        try {
            index = ParserUtil.parseIndex(
                    argMultimap.getValue(PREFIX_MENU)
                            .orElseThrow(() -> new ParseException("")));
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteMenuCommand.MESSAGE_USAGE), pe);
        }

        return new DeleteMenuCommand(index);
    }
}
