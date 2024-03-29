package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteMenuCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteMenuCommand object
 */
public class DeleteMenuCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteMenuCommand
     * and returns a DeleteMenuCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteMenuCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteMenuCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteMenuCommand.MESSAGE_USAGE), pe);
        }
    }
}
