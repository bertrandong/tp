package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.StageCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class StageCommandParser implements Parser<StageCommand> {

    /**
     * Parses input arguments and creates a new EditOrderCommand object.
     */
    public StageCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ORDER);

        Index index;

        try {
            index = ParserUtil.parseIndex(
                    argMultimap.getValue(PREFIX_ORDER)
                            .orElseThrow(() -> new ParseException("")));
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    StageCommand.MESSAGE_USAGE), pe);
        }

        return new StageCommand(index);
    }
}
