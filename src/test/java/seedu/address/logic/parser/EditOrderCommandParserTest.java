package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_QUANTITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_THREE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_TWO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MENU;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_QUANTITY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PRODUCT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditOrderCommand;
import seedu.address.model.order.Quantity;
import seedu.address.testutil.EditOrderDescriptorBuilder;

public class EditOrderCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditOrderCommand.MESSAGE_USAGE);

    private EditOrderCommandParser parser = new EditOrderCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no field specified
        assertParseFailure(parser, " " + PREFIX_ORDER + "1", EditOrderCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, " " + PREFIX_ORDER + "1 " + PREFIX_MENU + "1 " + INVALID_QUANTITY_DESC,
                Quantity.MESSAGE_CONSTRAINTS); // invalid quantity

        assertParseFailure(parser, " " + PREFIX_ORDER + "a " + PREFIX_MENU + "1 " + INVALID_QUANTITY_DESC,
                MESSAGE_INVALID_FORMAT); // invalid order

        assertParseFailure(parser, " " + PREFIX_ORDER + "1 " + PREFIX_MENU + "a " + INVALID_QUANTITY_DESC,
                MESSAGE_INVALID_INDEX); // invalid menu
    }

    @Test
    public void parse_bothFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        Index productIndex = INDEX_FIRST_PRODUCT;
        String userInput = " " + PREFIX_ORDER + targetIndex.getOneBased() + " " + PREFIX_MENU + " "
                + productIndex.getOneBased() + " " + PREFIX_PRODUCT_QUANTITY + " " + VALID_QUANTITY_TWO;

        EditOrderCommand.EditOrderDescriptor descriptor = new EditOrderDescriptorBuilder()
                .withQuantity(Integer.parseInt(VALID_QUANTITY_TWO))
                .build();

        EditOrderCommand expectedCommand = new EditOrderCommand(targetIndex, productIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_failure() {
        // product only
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = " " + PREFIX_ORDER + targetIndex.getOneBased()
                + " " + PREFIX_MENU + 1;
        EditOrderCommand.EditOrderDescriptor descriptor = new EditOrderDescriptorBuilder().build();
        assertParseFailure(parser, userInput, EditOrderCommand.MESSAGE_NOT_EDITED);

        // quantity only
        userInput = " " + PREFIX_ORDER + targetIndex.getOneBased()
                + " " + PREFIX_PRODUCT_QUANTITY + VALID_QUANTITY_THREE;
        descriptor = new EditOrderDescriptorBuilder()
                .withQuantity(Integer.parseInt(VALID_QUANTITY_THREE)).build();
        assertParseFailure(parser, userInput, EditOrderCommand.MESSAGE_NOT_EDITED);
    }
}
