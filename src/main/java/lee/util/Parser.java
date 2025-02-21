package lee.util;

import lee.LeeException;
import lee.task.Deadline;
import lee.task.Event;
import lee.task.TaskList;
import lee.task.ToDo;
import lee.ui.Ui;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Parses and analyzes the given commands.
 */
public class Parser {

    private final TaskList tasks;
    private final Ui ui;

    /**
     * Initializes the TaskList object to be operated on.
     *
     * @param tasks The TaskList object.
     */
    public Parser(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Parses the given command and do the relevant operations.
     *
     * @param command The command input by user.
     */
    public void parse(String command) {
        String[] commands = command.split(" ");
        String first = commands[0];
        try {
            switch (first) {
            case "list" -> listTasks();
            case "mark" -> markTask(commands);
            case "unmark" -> unMarkTask(commands);
            case "todo" -> addTodoTask(command);
            case "deadline" -> addDeadlineTask(command);
            case "event" -> addEventTask(command);
            case "delete" -> deleteTask(commands);
            case "find" -> findTask(commands);
            default -> throw new LeeException("Command not found TT");
            }
        } catch (LeeException e) {
            ui.showLoadingError(e);
        }
    }

    /**
     * Lists out all the task currently in the list.
     */
    private void listTasks() {
        ui.showTaskList(tasks);
    }

    /**
     * Marks the given task marked.
     *
     * @param commands Commands from user input.
     * @throws LeeException If the commands is in incorrect form.
     */
    private void markTask(String[] commands) throws LeeException {
        if (commands.length < 2) {
            throw new LeeException("Please indicate which task you want to mark with the task index");
        }
        mark(commands[1], true);
        refreshTaskList();
    }

    /**
     * Marks the given task unmarked.
     *
     * @param commands Commands from user input.
     * @throws LeeException If the commands is in incorrect form.
     */
    private void unMarkTask(String[] commands) throws LeeException {
        if (commands.length < 2) {
            throw new LeeException("Please indicate which task you want to unmark with the task index");
        }
        mark(commands[1], false);
        refreshTaskList();
    }

    /**
     * Adds a todo task.
     *
     * @param command Command from user input.
     * @throws LeeException If the commands is in incorrect form.
     */
    private void addTodoTask(String command) throws LeeException {
        if (command.split(" ", 2).length < 2) {
            throw new LeeException("Please give the task description.");
        }
        String task = command.split(" ", 2)[1];
        tasks.add(new ToDo(task));
        ui.showAddTask(tasks.get(tasks.size() - 1), tasks.size());
        refreshTaskList();
    }

    /**
     * Adds a deadline task.
     *
     * @param command Command from user input.
     * @throws LeeException If the commands is in incorrect form.
     */
    private void addDeadlineTask(String command) throws LeeException {
        if (command.split(" ", 2).length < 2) {
            throw new LeeException("Please give the task description.");
        }
        String order = command.split(" ", 2)[1];
        if (order.split(" /by").length < 2) {
            throw new LeeException("Please make sure to use \"/by\" to indicate the deadline");
        }
        String task = order.split(" /by")[0];
        String by = order.split(" /by")[1];
        tasks.add(new Deadline(task, by));
        ui.showAddTask(tasks.get(tasks.size() - 1), tasks.size());
        refreshTaskList();
    }

    /**
     * Adds an event task.
     *
     * @param command Command from user input.
     * @throws LeeException If the commands is in incorrect form.
     */
    private void addEventTask(String command) throws LeeException {
        if (command.split(" ", 2).length < 2) {
            throw new LeeException("Please give the task description.");
        }
        String order = command.split(" ", 2)[1];
        if (order.split(" /from").length < 2) {
            throw new LeeException("Please make sure to use \"/from\" to indicate the start time");
        }
        String task = order.split(" /from")[0];
        if (order.split(" /from")[1].split(" /to").length < 2) {
            throw new LeeException("Please make sure to use \"/to\" to indicate the end time");
        }
        String from = order.split(" /from")[1].split(" /to")[0];
        String to = order.split(" /from")[1].split(" /to")[1];
        tasks.add(new Event(task, from, to));
        ui.showAddTask(tasks.get(tasks.size() - 1), tasks.size());
        refreshTaskList();
    }

    /**
     * Deletes the given task.
     *
     * @param commands Command from user input.
     * @throws LeeException If the commands is in incorrect form.
     */
    private void deleteTask(String[] commands) throws LeeException {
        if (commands.length < 2) {
            throw new LeeException("Please indicate which task you want to delete with the task index");
        }
        int index = Integer.parseInt(commands[1]) - 1;
        if (index >= tasks.size()) {
            throw new LeeException("Please input a correct task index");
        }
        ui.showDeleteTask(tasks.remove(index), tasks.size());
        refreshTaskList();
    }

    /**
     * Finds all tasks matching the given keyword.
     *
     * @param commands Command from user input.
     * @throws LeeException If the commands is in incorrect form.
     */
    private void findTask(String[] commands) throws LeeException {
        if (commands.length < 2) {
            throw new LeeException("Please indicate a keyword for the task you want to search for");
        }
        String keyword = commands[1];
        TaskList matchingList = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).match(keyword)) {
                matchingList.add(tasks.get(i));
            }
        }
        ui.showFindTask(matchingList);
    }

    /**
     * Marks the status of a given task.
     *
     * @param num The string literal representing the given task index.
     * @param isMarked The new status of the task.
     * @throws LeeException If the given command syntax is incorrect.
     */
    private void mark(String num, boolean isMarked) throws LeeException {
        int index = Integer.parseInt(num) - 1;
        if (index >= tasks.size()) {
            throw new LeeException("Please input a correct task index");
        }
        tasks.get(index).markDone(isMarked);
        ui.showMarked(tasks.get(index), isMarked);
    }

    /**
     * Updates the task list saved in the data file.
     */
    private void refreshTaskList() {
        try {
            FileWriter fw = new FileWriter("./data/taskList.txt");
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).toFile() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            ui.showLoadingError(e);
        }
    }
}
