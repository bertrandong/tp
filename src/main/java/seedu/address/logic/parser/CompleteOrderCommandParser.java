package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CompleteOrderCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CompleteOrderCommand object
 */
public class CompleteOrderCommandParser implements Parser<CompleteOrderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CompleteCommand
     * and returns a CompleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CompleteOrderCommand parse(String args) throws ParseException {
        try {
            String trimmedArgs = args.trim();
            String[] nameKeywords = trimmedArgs.split("\\s+");
            ArrayList<Index> indexArr = new ArrayList<>();
            for (String s : nameKeywords) {
                Index index = ParserUtil.parseIndex(s);
                if (indexArr.contains(index)) {
                    throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
                }
                indexArr.add(index);
            }
            return new CompleteOrderCommand(indexArr);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CompleteOrderCommand.MESSAGE_USAGE), pe);
        }
    }
}
