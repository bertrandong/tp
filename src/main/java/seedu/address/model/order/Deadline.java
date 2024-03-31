package seedu.address.model.order;

import java.time.LocalDate;



public class Deadline {

    public static final String MESSAGE_CONSTRAINTS =
            "Deadlines should only contain numeric characters, and be in the format dd/MM/yyyy";
    private LocalDate deadline;

    public static final String VALIDATION_REGEX = "^\\d{2}/\\d{2}/\\d{4}$";

    public Deadline(String timeString){
        deadline = TimeManager.parseTime(timeString);
    }

    public static boolean isValidDeadline(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return this.deadline.toString();
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
