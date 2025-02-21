package lee.ui;

import lee.task.Task;
import lee.task.TaskList;
import lee.util.Parser;

/**
 * Aggregates all ui related methods for tui.
 */
public class Ui {
    private final String HORlINE;
    private final String GREETS;
    private final String EXITS;

    /**
     * Sets up the basic ui string literals.
     */
    public Ui() {
        this.HORlINE = "____________________________________________________________\n";
        this.GREETS = "Hello! I'm Lee.Lee.\nWhat can I do for you?\n";
        this.EXITS = "Bye. Hope to see you again soon!\n";
    }

    /**
     * Prints warnings and error messages.
     *
     * @param e The exception encountered during runtime.
     */
    public void showLoadingError(Exception e) {
        System.out.println("Oops! An error occurred!\n" + e.getMessage());
    }

    /**
     * Prints the start UI.
     */
    public void startUi() {
        System.out.print(HORlINE + GREETS + HORlINE);
    }

    /**
     * Prints the exit UI.
     */
    public void exitUi() {
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
        System.out.format("%s I've marked this task as %s:\n"
                + task + "\n", isMarked ? "Nice!" : "OK,", isMarked ? "done" : "not done yet");
    }

    /**
     * Shows the new task added.
     *
     * @param task The new task added.
     * @param num The size of the whole task list.
     */
    public void showAddTask(Task task, int num) {
        System.out.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.\n",
                task, num);
    }

    /**
     * Shows the task deleted.
     *
     * @param task The task deleted.
     * @param num The size of the whole task list.
     */
    public void showDeleteTask(Task task, int num) {
        System.out.format("Noted. I've removed this task:\n  " +
                task + "\n" + "Now you have %d tasks in the list.\n", num);
    }

    /**
     * Shows the matching task found.
     *
     * @param matchingList The TaskList of all matching tasks.
     */
    public void showFindTask(TaskList matchingList) {
        if (matchingList.size() <= 0) {
            System.out.println("Oops! Seems no matching tasks found...");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingList.size(); i++) {
                System.out.format("%d." + matchingList.get(i) + "\n", i);
            }
        }
    }

    /**
     * Shows the existing task list.
     *
     * @param tasks The task list.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.format("%d." + tasks.get(i) + "\n", i + 1);
        }
    }

}
