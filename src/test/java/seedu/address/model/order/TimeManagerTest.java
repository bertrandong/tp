package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.model.exceptions.InvalidDateException;


public class TimeManagerTest {
    @Test
    void parseTime_validFormats_parsedCorrectly() {
        // Test various valid formats
        assertEquals(LocalDate.of(2024, 4, 1), TimeManager.parseTime("01/04/2024"));
        assertEquals(LocalDate.of(2024, 4, 1), TimeManager.parseTime("2024/04/01"));
        assertEquals(LocalDate.of(2024, 4, 1), TimeManager.parseTime("2024-04-01"));
        assertEquals(LocalDate.of(2024, 4, 1), TimeManager.parseTime("01-04-2024"));
        assertEquals(LocalDate.of(2024, 4, 1), TimeManager.parseTime("1/4/2024"));
        assertEquals(LocalDate.of(2024, 4, 1), TimeManager.parseTime("01 April 2024"));
        assertEquals(LocalDate.of(2024, 4, 1), TimeManager.parseTime("01 04 2024"));
    }

    @Test
    void parseTime_invalidFormat_throwsInvalidDateException() {
        // Test an invalid date format
        assertThrows(InvalidDateException.class, () -> TimeManager.parseTime("April 1, 2024"));
    }

    @Test
    void formatter_correctFormatting() {
        // Test the formatting of a LocalDate object
        LocalDate testDate = LocalDate.of(2024, 4, 1);
        String expectedFormattedDate = "01/04/2024";
        assertEquals(expectedFormattedDate, TimeManager.formatter(testDate));
    }
}
