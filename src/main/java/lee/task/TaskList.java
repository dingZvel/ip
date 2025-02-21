package lee.task;

import java.util.ArrayList;

/**
 * Represents a list of Task objects.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Initializes the object with a task list.
     *
     * @param tasks the task list to start with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }


    /**
     * Gets a Task object based on the given index.
     *
     * @param index The index of the wanted Task object in the list.
     * @return The wanted Task object.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of Task objects in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Adds a new Task object to the end of the list.
     *
     * @param newTask The new Task object to be added to the list.
     */
    public void add(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Removes a Task object based on the given index.
     *
     * @param index The index of the Task object to be removed.
     * @return Returns true if the Task object is successfully removed.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }
}
