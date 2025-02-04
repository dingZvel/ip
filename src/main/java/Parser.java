import java.io.FileWriter;
import java.io.IOException;

public class Parser {

    private final TaskList tasks;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    public void parse(String command) {
        String[] commands = command.split(" ");
        String first = commands[0];
        try {
            if (command.equals("list")) {
                listTasks();
            }
            else if (first.equals("mark")) {
                if (commands.length < 2) {
                    throw new LeeException("Please indicate which task you want to mark with the task index");
                }
                mark(commands[1], true);
                refreshTaskList();
            }
            else if (first.equals("unmark")) {
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
            }
            else if (first.equals("deadline")) {
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
            }
            else if (first.equals("event")) {
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
            }
            else if (first.equals("delete")) {
                if (commands.length < 2) {
                    throw new LeeException("Please indicate which task you want to delete with the task index");
                }
                deleteTask(commands[1]);
                refreshTaskList();
            }
            else {
                //addTasks(command);
                throw new LeeException("Command not found TT");
            }
        } catch (LeeException e) {
            System.out.println(e.getMessage());
        }
    }

    private String showTask(int index) {
        return tasks.get(index).toString() + "\n";
    }

    private void mark(String num, boolean b) throws LeeException {
        int index = Integer.parseInt(num) - 1;
        if (index >= tasks.size()) {
            throw new LeeException("Please input a correct task index");
        }
        tasks.get(index).isDone = b;
        System.out.format("%s I've marked this task as %s:\n"
                + showTask(index), b ? "Nice!" : "OK,", b ? "done" : "not done yet");
    }

    private void addToDo(String task) {
        tasks.add(new ToDo(task));
        System.out.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.\n",
                tasks.get(tasks.size() - 1).toString(), tasks.size());
    }

    private void addDeadline(String task, String by) {
        tasks.add(new Deadline(task, by));
        System.out.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.\n",
                tasks.get(tasks.size() - 1).toString(), tasks.size());
    }

    private void addEvent(String task, String from, String to) {
        tasks.add(new Event(task, from, to));
        System.out.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.\n",
                tasks.get(tasks.size() - 1).toString(), tasks.size());
    }

    private void deleteTask(String num) throws LeeException {
        int index = Integer.parseInt(num) - 1;
        if (index >= tasks.size()) {
            throw new LeeException("Please input a correct task index");
        }
        System.out.format("Noted. I've removed this task:\n  " +
                tasks.remove(index).toString() + "\n" +
                "Now you have %d tasks in the list.\n", tasks.size());
    }


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
    private void listTasks(){
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i ++){
            System.out.format("%d." + showTask(i), i + 1);
        }
    }
}
