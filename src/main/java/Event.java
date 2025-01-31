import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime from, to;
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(" yyyy-MM-dd HH:mm");

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, FORMATTER);
        this.to = LocalDateTime.parse(to, FORMATTER);
    }

    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = LocalDateTime.parse(from, FORMATTER);
        this.to = LocalDateTime.parse(to, FORMATTER);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + from.format(FORMATTER) + " to:" + to.format(FORMATTER)+ ")";
    }

    @Override
    public String toFile() {
        return "E|" + super.toFile() + "|" + from .format(FORMATTER)+ "|" + to.format(FORMATTER);
    }
}