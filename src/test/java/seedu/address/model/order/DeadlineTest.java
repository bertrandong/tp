package seedu.address.model.order;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import seedu.address.model.exceptions.InvalidDateException;
import static seedu.address.testutil.TypicalDeadline.APRIL_FOOLS;
import static seedu.address.testutil.TypicalDeadline.NEW_YEARS;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(APRIL_FOOLS.equals(APRIL_FOOLS));

        // null -> returns false
        assertFalse(APRIL_FOOLS.equals(null));

        // different object -> returns false
        assertFalse(APRIL_FOOLS.equals(NEW_YEARS));
    }


    @Test
    public void toStringMethod() {
        String expectedDate = "01/04/2024";
        assertEquals(expectedDate, APRIL_FOOLS.toString());

    }

    @Test
    public void isValidDeadline() {
        assertTrue(Deadline.isValidDeadline("01/04/2024"));

        assertFalse(Deadline.isValidDeadline("2024-04-01"));
        assertFalse(Deadline.isValidDeadline("April 1, 2024"));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "32/04/2024";
        assertThrows(InvalidDateException.class, () -> new Deadline(invalidDate));
    }
}
