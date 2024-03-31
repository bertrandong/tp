package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static seedu.address.testutil.TypicalCreationDate.APRIL_FOOLS;
import static seedu.address.testutil.TypicalCreationDate.NEW_YEARS;

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

}
