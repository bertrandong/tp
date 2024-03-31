package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.PRODUCT_DESC_COOKIE;
import static seedu.address.logic.commands.CommandTestUtil.PRODUCT_DESC_CUPCAKE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRODUCT_CUPCAKE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddMenuCommand;
import seedu.address.model.order.Product;
import seedu.address.testutil.ProductBuilder;

public class AddMenuCommandParserTest {
    private AddMenuCommandParser parser = new AddMenuCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Product expectedProduct = new ProductBuilder().build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + PRODUCT_DESC_CUPCAKE,
                new AddMenuCommand(expectedProduct));
    }

    @Test
    public void parse_repeatedValue_failure() {
        String validExpectedProductString = PRODUCT_DESC_CUPCAKE;

        // multiple product names
        assertParseFailure(parser, PRODUCT_DESC_COOKIE + validExpectedProductString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PRODUCT_NAME));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMenuCommand.MESSAGE_USAGE);

        // missing name product name prefix
        assertParseFailure(parser, VALID_PRODUCT_CUPCAKE, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + PRODUCT_DESC_CUPCAKE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMenuCommand.MESSAGE_USAGE));
    }
}
