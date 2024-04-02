package seedu.address.model.order;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalCreationDate.APRIL_FOOLS;
import static seedu.address.testutil.TypicalCreationDate.NEW_YEARS;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;


public class CreationDateTest {

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
    public void defaultConstructor_usesCurrentDate() {
        CreationDate today = new CreationDate();
        assertEquals(TimeManager.formatter(LocalDate.now()), today.toString());
    }

    @Test
    public void isValidCreationDate() {
        // Assuming CreationDate has a static method isValidCreationDate similar to Deadline
        assertTrue(CreationDate.isValidCreationDate("01/01/2024"));
        assertFalse(CreationDate.isValidCreationDate("2024-01-01"));
    }

}
