package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_LIST_FIRST_ORDER;
import static seedu.address.testutil.TypicalIndexes.INDEX_LIST_FIRST_SECOND_ORDER;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CompleteOrderCommand;

public class CompleteOrderCommandParserTest {

    private CompleteOrderCommandParser parser = new CompleteOrderCommandParser();

    @Test
    public void parse_validArgs_returnsCancelCommand() {
        assertParseSuccess(parser, "1", new CompleteOrderCommand(INDEX_LIST_FIRST_ORDER));
    }

    @Test
    public void parse_validMultipleArgs_returnsCancelCommand() {
        assertParseSuccess(parser, "1 2", new CompleteOrderCommand(INDEX_LIST_FIRST_SECOND_ORDER));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CompleteOrderCommand.MESSAGE_USAGE));
    }
}
