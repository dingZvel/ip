package lee.task;

/**
 * Represents ToDo as one of the types of task.
 */
public class ToDo extends Task {

    /**
     * Sets up the description of the task and sets status to not done by default.
     *
     * @param description String description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Sets up the description of the task and initializes the status based on the given parameter.
     *
     * @param description String description of the task.
     * @param isDone Initial status of the task.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFile() {
        return "T|" + super.toFile();
    }
}
