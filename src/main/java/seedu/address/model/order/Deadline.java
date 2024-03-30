package seedu.address.model.order;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Deadline {
    private LocalDate deadline;

    public static final String VALIDATION_REGEX = "^\\d{2}/\\d{2}/\\d{4}$";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public Deadline(String timeString){
        try {
            deadline = LocalDate.parse(timeString, FORMATTER);
        } catch (DateTimeParseException e) {

        }
    }

    public boolean isValidDeadline(String test) {
        return test.matches(VALIDATION_REGEX);
    }
}
