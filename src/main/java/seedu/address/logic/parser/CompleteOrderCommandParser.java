package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CompleteOrderCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class CompleteOrderCommandParser implements Parser<CompleteOrderCommand>{

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CompleteOrderCommand parse(String args) throws ParseException {
        try {
            String trimmedArgs = args.trim();
            String[] nameKeywords = trimmedArgs.split("\\s+");
            ArrayList<Index> indexArr = new ArrayList<>();
            for (String s : nameKeywords) {
                Index index = ParserUtil.parseIndex(s);
                indexArr.add(index);
            }
            return new CompleteOrderCommand(indexArr);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CompleteOrderCommand.MESSAGE_USAGE), pe);
        }
    }
}
