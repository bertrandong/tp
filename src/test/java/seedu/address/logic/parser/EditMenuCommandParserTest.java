package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MENU;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PRODUCT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PRODUCT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditMenuCommand;
import seedu.address.logic.commands.EditMenuCommand.EditProductDescriptor;
import seedu.address.testutil.EditProductDescriptorBuilder;

public class EditMenuCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditMenuCommand.MESSAGE_USAGE);

    private EditMenuCommandParser parser = new EditMenuCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_PRODUCT_CUPCAKE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, " " + PREFIX_MENU + "1",
                EditMenuCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, " ", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, PREFIX_MENU + "-5", MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, PREFIX_MENU + "0", MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, PREFIX_MENU + "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, PREFIX_MENU + "1 p/n", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PRODUCT;
        String userInput = " " + PREFIX_MENU + targetIndex.getOneBased() + PRODUCT_DESC_CUPCAKE;

        EditProductDescriptor descriptor = new EditProductDescriptorBuilder().withName("Cupcake").build();
        EditMenuCommand expectedCommand = new EditMenuCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        Index targetIndex = INDEX_FIRST_PRODUCT;

        // invalid followed by valid
        String userInput = " " + PREFIX_MENU + targetIndex.getOneBased() + PREFIX_PRODUCT_NAME + INVALID_PRODUCT_DESC +
                " " + PREFIX_PRODUCT_NAME + VALID_PRODUCT_CUPCAKE;
        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PRODUCT_NAME));

        // valid followed by invalid
        userInput = " " + PREFIX_MENU + targetIndex.getOneBased() + PREFIX_PRODUCT_NAME + VALID_PRODUCT_CUPCAKE + " " +
                PREFIX_PRODUCT_NAME + INVALID_PRODUCT_DESC;
        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PRODUCT_NAME));

        // multiple invalid values
        userInput = " " + PREFIX_MENU + targetIndex.getOneBased() + PREFIX_PRODUCT_NAME + INVALID_PRODUCT_DESC + " " +
                PREFIX_PRODUCT_NAME + INVALID_PRODUCT_DESC;
        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PRODUCT_NAME));
    }
}
