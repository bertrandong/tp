package seedu.address.model.order;

import java.time.LocalDate;



public class Deadline {

    public static final String MESSAGE_CONSTRAINTS =
            "Deadlines should only contain numeric characters, and be in the format dd/MM/yyyy";
    private LocalDate deadline;

    public static final String VALIDATION_REGEX = "^\\d{2}/\\d{2}/\\d{4}$";

    /**
     * Constructs a {@code Deadline} object after parsing the given time string.
     *
     * @param timeString The deadline date as a string in the format dd/MM/yyyy.
     */
    public Deadline(String timeString) {
        deadline = TimeManager.parseTime(timeString);
    }

    /**
     * Checks if the given string matches the deadline format.
     *
     * @param test The string to test.
     * @return true if the given string is in the format dd/MM/yyyy, false otherwise.
     */
    public static boolean isValidDeadline(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the deadline date.
     *
     * @return The deadline as a LocalDate object.
     */
    public LocalDate getDeadline() {
        return this.deadline;
    }


    @Override
    public String toString() {
        return TimeManager.formatter(this.deadline);
    }

    @Override
    public int hashCode() {
        return this.deadline.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Deadline)) {
            return false;
        }

        Deadline otherDeadline = (Deadline) other;
        return this.deadline.equals(otherDeadline.deadline);
    }
}
