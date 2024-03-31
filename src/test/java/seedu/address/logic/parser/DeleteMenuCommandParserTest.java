package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MENU;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PRODUCT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteMenuCommand;

public class DeleteMenuCommandParserTest {
    private DeleteMenuCommandParser parser = new DeleteMenuCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteMenuCommand() {
        assertParseSuccess(parser, " " + PREFIX_MENU + "1",
                new DeleteMenuCommand(INDEX_FIRST_PRODUCT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " " + PREFIX_MENU + "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        DeleteMenuCommand.MESSAGE_USAGE));
    }
}
