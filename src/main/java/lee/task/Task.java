package lee.task;

/**
 * Represents an entry of task. A task entry has a string field for description and a boolean
 * field to represent whether the task is already done or not.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initializes task description and sets isDone to false by default.
     *
     * @param description String description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Initializes task description and sets status to not done by default.
     *
     * @param description The task content.
     * @param isDone Status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns statusIcon for the task
     *
     * @return String statusIcon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns 1 or 0 to represent status on data file.
     *
     * @return String statusBool
     */
    public String getStatusBool() {
        return (isDone ? "1" : "0");
    }

    @Override
    public String toString() {
        return String.format("[%s] " + this.description, this.getStatusIcon());
    }

    /**
     * Returns a line of entry representing this task.
     *
     * @return String representation of the task to be saved in the data file.
     */
    public String toFile() {
        return String.format("%s|%s", this.getStatusBool(), this.description);
    }

    /**
     * Marks the status of the task.
     *
     * @param isDone The new status of the task.
     */
    public void markDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean match(String keyword) {
        return this.description.contains(keyword);
    }
}
