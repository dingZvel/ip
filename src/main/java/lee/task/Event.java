package lee.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents event as one of the types of task. It has two additional field
 * representing the start time and end time of the event.
 */
public class Event extends Task {

    protected LocalDateTime from, to;
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(" yyyy-MM-dd HH:mm");

    /**
     * Sets up the fields of the Event object.
     *
     * @param description String description of the event.
     * @param from Start time of the event.
     * @param to End time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, FORMATTER);
        this.to = LocalDateTime.parse(to, FORMATTER);
    }

    /**
     * Sets up the fields of the Event object and sets status of the Event based on the given parameter.
     *
     * @param description String description of the event.
     * @param from Start time of the event.
     * @param to End time of the event.
     * @param isDone Initial status of the event.
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = LocalDateTime.parse(from, FORMATTER);
        this.to = LocalDateTime.parse(to, FORMATTER);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + from.format(FORMATTER) + " to:" + to.format(FORMATTER) + ")";
    }

    @Override
    public String toFile() {
        return "E|" + super.toFile() + "|" + from.format(FORMATTER) + "|" + to.format(FORMATTER);
    }
}