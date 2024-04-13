package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.COST_DESC_TEN;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRODUCT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRODUCT_DESC_COST;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRODUCT_DESC_MISSING_COST;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRODUCT_DESC_MISSING_COST_PREFIX;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRODUCT_DESC_MISSING_NAME;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRODUCT_DESC_MISSING_NAME_PREFIX;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRODUCT_DESC_MISSING_SALES;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRODUCT_DESC_MISSING_SALES_PREFIX;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRODUCT_DESC_SALES;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.PRODUCT_DESC_COOKIE;
import static seedu.address.logic.commands.CommandTestUtil.PRODUCT_DESC_CUPCAKE;
import static seedu.address.logic.commands.CommandTestUtil.PRODUCT_DESC_CUPCAKE_WITH_PRICE;
import static seedu.address.logic.commands.CommandTestUtil.SALES_DESC_TWENTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_SALES;
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
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + PRODUCT_DESC_CUPCAKE_WITH_PRICE,
                new AddMenuCommand(expectedProduct));
    }

    @Test
    public void parse_repeatedValue_failure() {
        String validExpectedProductString = PRODUCT_DESC_CUPCAKE_WITH_PRICE;

        // multiple product names
        assertParseFailure(parser, PRODUCT_DESC_COOKIE + validExpectedProductString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PRODUCT_NAME));

        // multiple product costs
        assertParseFailure(parser, COST_DESC_TEN + " " + validExpectedProductString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PRODUCT_COST));

        // multiple product sales
        assertParseFailure(parser, SALES_DESC_TWENTY + " " + validExpectedProductString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PRODUCT_SALES));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMenuCommand.MESSAGE_USAGE);

        // missing product name prefix
        assertParseFailure(parser, INVALID_PRODUCT_DESC_MISSING_NAME_PREFIX, expectedMessage);

        // missing product cost prefix
        assertParseFailure(parser, INVALID_PRODUCT_DESC_MISSING_COST_PREFIX, expectedMessage);

        // missing product name prefix
        assertParseFailure(parser, INVALID_PRODUCT_DESC_MISSING_SALES_PREFIX, expectedMessage);

        // missing name
        assertParseFailure(parser, INVALID_PRODUCT_DESC_MISSING_NAME, expectedMessage);

        // missing cost
        assertParseFailure(parser, INVALID_PRODUCT_DESC_MISSING_COST, expectedMessage);

        //missing sales
        assertParseFailure(parser, INVALID_PRODUCT_DESC_MISSING_SALES, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMenuCommand.MESSAGE_USAGE);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + PRODUCT_DESC_CUPCAKE, expectedMessage);

        // invalid cost
        assertParseFailure(parser, INVALID_PRODUCT_DESC_COST, expectedMessage);

        // invalid sales
        assertParseFailure(parser, INVALID_PRODUCT_DESC_SALES, expectedMessage);

        // invalid name
        assertParseFailure(parser, INVALID_PRODUCT_DESC, expectedMessage);

    }
}
