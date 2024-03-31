package seedu.address.model.order;

import java.time.LocalDate;

public class CreationDate {
    public static final String VALIDATION_REGEX = "^\\d{2}/\\d{2}/\\d{4}$";
    private LocalDate creationDate;

    /**
     * Constructs a {@code CreationDate} object set to the current date.
     */
    public CreationDate() {
        this.creationDate = TimeManager.parseTime(LocalDate.now().toString());
    }

    /**
     * Constructs a {@code CreationDate} object from the given time string.
     *
     * @param time The creation date as a string in the format dd/MM/yyyy.
     */
    public CreationDate(String time) {
        this.creationDate = TimeManager.parseTime(time);
    }

    /**
     * Checks if the provided string is a valid creation date.
     *
     * @param test The string to test.
     * @return true if the string is in the format dd/MM/yyyy, false otherwise.
     */
    public boolean isValidCreationDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Sets the creation date based on the provided string.
     *
     * @param date The new creation date as a string in the format dd/MM/yyyy.
     */
    public void setCreationDate(String date) {
        this.creationDate = TimeManager.parseTime(date);
    }

    @Override
    public String toString() {
        return TimeManager.formatter(this.creationDate);
    }

    @Override
    public int hashCode() {
        return this.creationDate.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof CreationDate)) {
            return false;
        }

        CreationDate otherCreationDate = (CreationDate) other;
        return this.creationDate.equals(otherCreationDate.creationDate);
    }
}
