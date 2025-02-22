package lee.ui;

import lee.task.Task;
import lee.task.TaskList;

/**
 * Aggregates all ui related methods for tui.
 */
public class Ui {
    protected final String HORlINE;
    protected final String GREETS;
    protected final String EXITS;
    protected String message;

    /**
     * Sets up the basic ui string literals.
     */
    public Ui() {
        this.HORlINE = "____________________________________________________________\n";
        this.GREETS = "Hello! I'm Lee.Lee.\nWhat can I do for you?\n";
        this.EXITS = "Bye. Hope to see you again soon!\n";
        this.message = "";
    }

    /**
     * Get the message from Lee
     *
     * @return The String message from Lee.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Prints warnings and error messages.
     *
     * @param e The exception encountered during runtime.
     */
    public void showLoadingError(Exception e) {
        this.message = "Oops! An error occurred!\n" + e.getMessage() + "\n";
        System.out.print(message);
    }

    /**
     * Prints the start UI.
     */
    public void startUi() {
        this.message = GREETS;
        System.out.print(HORlINE + GREETS + HORlINE);
    }

    /**
     * Prints the exit UI.
     */
    public void exitUi() {
        this.message = EXITS;
        System.out.print(HORlINE + EXITS + HORlINE);
    }

    /**
     * Prints a separating line.
     */
    public void line() {
        System.out.print(HORlINE);
    }

    /**
     * Shows the marked task.
     *
     * @param task The task being marked or unmarked.
     * @param isMarked A boolean indicating the status of the task.
     */
    public void showMarked(Task task, boolean isMarked) {
        this.message = String.format("%s I've marked this task as %s:\n"
                + task + "\n", isMarked ? "Nice!" : "OK,", isMarked ? "done" : "not done yet");
        System.out.print(message);
    }

    /**
     * Shows the new task added.
     *
     * @param task The new task added.
     * @param num The size of the whole task list.
     */
    public void showAddTask(Task task, int num) {
        this.message = String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.\n",
                task, num);
        System.out.print(message);
    }

    /**
     * Shows the task deleted.
     *
     * @param task The task deleted.
     * @param num The size of the whole task list.
     */
    public void showDeleteTask(Task task, int num) {
        this.message = String.format("Noted. I've removed this task:\n  " +
                task + "\n" + "Now you have %d tasks in the list.\n", num);
        System.out.print(message);
    }

    /**
     * Shows the task rescheduled.
     *
     * @param task The task rescheduled.
     * @param num The size of the whole task list.
     */
    public void showRescheduleTask(Task task, int num) {
        this.message = String.format("Noted. I've rescheduled this task:\n  " +
                task + "\n" + "Now you have %d tasks in the list.\n", num);
        System.out.print(message);
    }

    /**
     * Shows the matching task found.
     *
     * @param matchingList The TaskList of all matching tasks.
     */
    public void showFindTask(TaskList matchingList) {
        if (matchingList.size() <= 0) {
            this.message = "Oops! Seems no matching tasks found...\n";
            System.out.print(message);
        } else {
            this.message = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < matchingList.size(); i++) {
                this.message += String.format("%d." + matchingList.get(i) + "\n", i + 1);
            }
            System.out.print(message);
        }
    }

    /**
     * Shows the existing task list.
     *
     * @param tasks The task list.
     */
    public void showTaskList(TaskList tasks) {
        this.message = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            this.message += String.format("%d." + tasks.get(i) + "\n", i + 1);
        }
        System.out.print(message);
    }

}
