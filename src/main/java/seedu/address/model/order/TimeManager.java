package seedu.address.model.order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class TimeManager {
    public static final DateTimeFormatter nowTime = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Parses a date and time string from various known formats into a standardized format.
     * If the input does not match any known format, it returns the input as is.
     *
     * @param input The date and time string to be parsed.
     * @return A date and time string formatted in the standard output format
     *         "dd MMMM yyyy, h:mm a" if the input matches any of the known formats;
     *         otherwise, returns the original input string.
     */
    public static LocalDate parseTime(String input) {
        List<DateTimeFormatter> formatters = Arrays.asList(
                DateTimeFormatter.ofPattern("dd/MM/yyyy"),
                DateTimeFormatter.ofPattern("yyyy/MM/dd"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy"),
                DateTimeFormatter.ofPattern("M/d/yyyy"),
                DateTimeFormatter.ofPattern("d/M/yyyy"),
                DateTimeFormatter.ofPattern("dd MMMM yyyy"),
                DateTimeFormatter.ofPattern("dd MM yyyy")
        );

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate result = null;
        for (DateTimeFormatter formatter : formatters) {
            try {
                LocalDate currTime = LocalDate.parse(input, formatter);
                return currTime;
            } catch (DateTimeParseException e) {
                continue;
            }

        }
        throw new RuntimeException();
    }
}
