package lee.util;

import lee.LeeException;
import lee.task.Deadline;
import lee.task.Event;
import lee.task.TaskList;
import lee.task.ToDo;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Parses and analyzes the given commands.
 */
public class Parser {

    private final TaskList tasks;

    /**
     * Initializes the TaskList object to be operated on.
     *
     * @param tasks The TaskList object.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
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
            if (command.equals("list")) {
                listTasks();
            } else if (first.equals("mark")) {
                if (commands.length < 2) {
                    throw new LeeException("Please indicate which task you want to mark with the task index");
                }
                mark(commands[1], true);
                refreshTaskList();
            } else if (first.equals("unmark")) {
                if (commands.length < 2) {
                    throw new LeeException("Please indicate which task you want to unmark with the task index");
                }
                mark(commands[1], false);
                refreshTaskList();
            }
            else if (first.equals("todo")) {
                if (command.split(" ", 2).length < 2) {
                    throw new LeeException("Please give the task description.");
                }
                String task = command.split(" ", 2)[1];
                addToDo(task);
                refreshTaskList();
            } else if (first.equals("deadline")) {
                if (command.split(" ", 2).length < 2) {
                    throw new LeeException("Please give the task description.");
                }
                String order = command.split(" ", 2)[1];
                if (order.split(" /by").length < 2) {
                    throw new LeeException("Please make sure to use \"/by\" to indicate the deadline");
                }
                String task = order.split(" /by")[0];
                String by = order.split(" /by")[1];
                addDeadline(task, by);
                refreshTaskList();
            } else if (first.equals("event")) {
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
                addEvent(task, from, to);
                refreshTaskList();
            } else if (first.equals("delete")) {
                if (commands.length < 2) {
                    throw new LeeException("Please indicate which task you want to delete with the task index");
                }
                deleteTask(commands[1]);
                refreshTaskList();
            } else if (first.equals("find")) {
                if (commands.length < 2) {
                    throw new LeeException("Please indicate a keyword for the task you want to search for");
                }
                findTask(commands[1]);
            }
            else {
                throw new LeeException("Command not found TT");
            }
        } catch (LeeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Shows the string representation of a given task.
     *
     * @param index The index of the Task object in the task list.
     * @return The string representation of the given task.
     */
    private String showTask(int index) {
        return tasks.get(index).toString() + "\n";
    }

    /**
     * Marks the status of a given task.
     *
     * @param num The string literal representing the given task index.
     * @param b The new status of the task.
     * @throws LeeException If the given command syntax is incorrect.
     */
    private void mark(String num, boolean b) throws LeeException {
        int index = Integer.parseInt(num) - 1;
        if (index >= tasks.size()) {
            throw new LeeException("Please input a correct task index");
        }
        tasks.get(index).markDone(b);
        System.out.format("%s I've marked this task as %s:\n"
                + showTask(index), b ? "Nice!" : "OK,", b ? "done" : "not done yet");
    }

    /**
     * Adds a ToDo task to the list.
     *
     * @param task The string representation of the task.
     */
    private void addToDo(String task) {
        tasks.add(new ToDo(task));
        System.out.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.\n",
                tasks.get(tasks.size() - 1).toString(), tasks.size());
    }

    /**
     * Adds a Deadline task to the list.
     *
     * @param task The string representation of the task.
     * @param by The deadline of the task.
     */
    private void addDeadline(String task, String by) {
        tasks.add(new Deadline(task, by));
        System.out.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.\n",
                tasks.get(tasks.size() - 1).toString(), tasks.size());
    }

    /**
     * Adds an Event task to the list.
     *
     * @param task The string representation of the task.
     * @param from The start time of the task.
     * @param to The end time of the task.
     */
    private void addEvent(String task, String from, String to) {
        tasks.add(new Event(task, from, to));
        System.out.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.\n",
                tasks.get(tasks.size() - 1).toString(), tasks.size());
    }

    /**
     * Deletes a task based on the given task index.
     *
     * @param num The string literal index of the task to be deleted.
     * @throws LeeException If the task index is out of bound.
     */
    private void deleteTask(String num) throws LeeException {
        int index = Integer.parseInt(num) - 1;
        if (index >= tasks.size()) {
            throw new LeeException("Please input a correct task index");
        }
        System.out.format("Noted. I've removed this task:\n  " +
                tasks.remove(index).toString() + "\n" +
                "Now you have %d tasks in the list.\n", tasks.size());
    }

    private void findTask(String keyword) {
       System.out.println("Here are the matching tasks in your list:");
       int count = 0;
       for (int i = 0; i < tasks.size(); i++) {
           if (tasks.get(i).match(keyword)) {
               count++;
               System.out.format("%d." + showTask(i), count);
           }
       }
    }

    /**
     * Updates the task list saved in the data file.
     */
    private void refreshTaskList() {
        try {
            FileWriter fw = new FileWriter("./data/taskList.txt");
//            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).toFile() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Lists out all the task currently in the list.
     */
    private void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.format("%d." + showTask(i), i + 1);
        }
    }
}
