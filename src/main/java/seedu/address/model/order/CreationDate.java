package seedu.address.model.order;

import java.time.LocalDate;

public class CreationDate {
    private LocalDate creationDate;

    public static final String VALIDATION_REGEX = "^\\d{2}/\\d{2}/\\d{4}$";

    public CreationDate() {
        this.creationDate = TimeManager.parseTime(LocalDate.now().toString());
    }

    public boolean isValidCreationDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return this.creationDate.toString();
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
