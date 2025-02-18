package lee.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getStatusBool() {
        return (isDone ? "1" : "0");
    }

    @Override
    public String toString() {
        return String.format("[%s] " + this.description, this.getStatusIcon());
    }

    public String toFile() {
        return String.format("%s|%s", this.getStatusBool(), this.description);
    }

    public void markDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean match(String keyword) {
        return this.description.contains(keyword);
    }
}
