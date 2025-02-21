package lee.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents event as one of the types of task. It has one additional field
 * representing the end time of the event.
 */
public class Deadline extends Task {

    protected LocalDateTime by;
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(" yyyy-MM-dd HH:mm");

    /**
     * Sets up the fields of the task object and sets the status to not done by default.
     *
     * @param description String description of the task.
     * @param by Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, FORMATTER);
    }

    /**
     * Sets up the fields of the task object and sets the status based on the given parameter.
     *
     * @param description String description of the task.
     * @param by Deadline of the task.
     * @param isDone Initial status of the task.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = LocalDateTime.parse(by, FORMATTER);
    }

    public void reschedule(String time) {
        by = LocalDateTime.parse(time, FORMATTER);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by.format(FORMATTER) + ")";
    }

    @Override
    public String toFile() {
        return "D|" + super.toFile() + "|" + by.format(FORMATTER);
    }
}
