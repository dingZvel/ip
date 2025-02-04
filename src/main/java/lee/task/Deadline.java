package lee.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(" yyyy-MM-dd HH:mm");

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, FORMATTER);
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = LocalDateTime.parse(by, FORMATTER);
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
