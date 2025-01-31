import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Lee {
    private static final String horLine = "____________________________________________________________\n";
    private static final String greets = "Hello! I'm Lee.\n"
            + "What can I do for you?\n";
    private static final String exits = "Bye. Hope to see you again soon!\n";
    private static final ArrayList<Task> tasks = new ArrayList<>();
//    private static final Task[] tasks = new Task[100];
//    private static String[] tasks = new String[100];
//    private static boolean[] taskStatus = new boolean[100];
//    private static int numOfTasks = 0;

    private static void analyze(String command) {
        System.out.print(horLine);
        String[] cmds = command.split(" ");
        String first = cmds[0];
        try {
            if (command.equals("list")) {
                listTasks();
            }
            else if (first.equals("mark")) {
                if (cmds.length < 2) {
                    throw new LeeException("Please indicate which task you want to mark with the task index");
                }
                mark(cmds[1], true);
            }
            else if (first.equals("unmark")) {
                if (cmds.length < 2) {
                    throw new LeeException("Please indicate which task you want to unmark with the task index");
                }
                mark(cmds[1], false);
            }
            else if (first.equals("todo")) {
                if (command.split(" ", 2).length < 2) {
                    throw new LeeException("Please give the task description.");
                }
                String task = command.split(" ", 2)[1];
                addToDo(task);
            }
            else if (first.equals("deadline")) {
                if (command.split(" ", 2).length < 2) {
                    throw new LeeException("Please give the task description.");
                }
                String order = command.split(" ", 2)[1];
                if (order.split("/by").length < 2) {
                    throw new LeeException("Please make sure to use \"/by\" to indicate the deadline");
                }
                String task = order.split("/by")[0];
                String by = order.split("/by")[1];
                addDeadline(task, by);
            }
            else if (first.equals("event")) {
                if (command.split(" ", 2).length < 2) {
                    throw new LeeException("Please give the task description.");
                }
                String order = command.split(" ", 2)[1];
                if (order.split("/from").length < 2) {
                    throw new LeeException("Please make sure to use \"/from\" to indicate the start time");
                }
                String task = order.split("/from")[0];
                if (order.split("/from")[1].split("/to").length < 2) {
                    throw new LeeException("Please make sure to use \"/to\" to indicate the end time");
                }
                String from = order.split("/from")[1].split("/to")[0];
                String to = order.split("/from")[1].split("/to")[1];
                addEvent(task, from, to);
            }
            else if (first.equals("delete")) {
                if (cmds.length < 2) {
                    throw new LeeException("Please indicate which task you want to delete with the task index");
                }
                deleteTask(cmds[1]);
            }
            else {
                //addTasks(command);
                throw new LeeException("Command not found TT");
            }
        } catch (LeeException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(horLine);
    }

    private static void listTasks(){
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i ++){
            System.out.format("%d." + showTask(i), i + 1);
        }
    }

//    private static void addTasks(String task) {
//        tasks[numOfTasks] = new Task(task);
//        numOfTasks ++;
//        System.out.format("added: %s\n", task);
//    }

    private static String showTask(int index) {
        return tasks.get(index).toString() + "\n";
    }

    private static void mark(String num, boolean b) throws LeeException {
        int index = Integer.parseInt(num) - 1;
        if (index >= tasks.size()) {
            throw new LeeException("Please input a correct task index");
        }
        tasks.get(index).isDone = b;
        System.out.format("%s I've marked this task as %s:\n"
                + showTask(index), b ? "Nice!" : "OK,", b ? "done" : "not done yet");
    }

    private static void addToDo(String task) {
        tasks.add(new ToDo(task));
        System.out.format("Got it. I've added this task:\n  %s\n" +
                        "Now you have %d tasks in the list.\n",
                tasks.get(tasks.size() - 1).toString(), tasks.size());
    }

    private static void addDeadline(String task, String by) {
        tasks.add(new Deadline(task, by));
        System.out.format("Got it. I've added this task:\n  %s\n" +
                        "Now you have %d tasks in the list.\n",
                tasks.get(tasks.size() - 1).toString(), tasks.size());
    }

    private static void addEvent(String task, String from, String to) {
        tasks.add(new Event(task, from, to));
        System.out.format("Got it. I've added this task:\n  %s\n" +
                        "Now you have %d tasks in the list.\n",
                tasks.get(tasks.size() - 1).toString(), tasks.size());
    }

    private static void deleteTask(String num) throws LeeException {
        int index = Integer.parseInt(num) - 1;
        if (index >= tasks.size()) {
            throw new LeeException("Please input a correct task index");
        }
        System.out.format("Noted. I've removed this task:\n  " +
                tasks.remove(index).toString() + "\n" +
                        "Now you have %d tasks in the list.\n", tasks.size());
    }

    private static void addTaskFromFile(String line) throws LeeException {
        String[] task = line.split("\\|");
        if (task.length < 3) {
            throw new LeeException("Data file corrupted!");
        }
        if (!task[1].equals("1") && !task[1].equals("0")) {
            throw new LeeException("Data file corrupted!");
        }
        boolean isDone = task[1].equals("1");
        switch (task[0]) {
        case "T" -> {
            if (task.length != 3) {
                throw new LeeException("Data file corrupted!");
            }
            tasks.add(new ToDo(task[2], isDone));
        }
        case "D" -> {
            if (task.length != 4) {
                throw new LeeException("Data file corrupted!");
            }
            tasks.add(new Deadline(task[2], task[3], isDone));
        }
        case "E" -> {
            if (task.length != 5) {
                throw new LeeException("Data file corrupted!");
            }
            tasks.add(new Event(task[2], task[3], task[4], isDone));
        }
        default -> throw new LeeException("Data file corrupted!");
        }
    }

    public static void main(String[] args) {
        System.out.println(horLine + greets + horLine);
        try {
            File f = new File("./data/taskList.txt");
            if (f.exists()) {
                Scanner s = new Scanner(f);
                while (s.hasNext()) {
                    addTaskFromFile(s.nextLine());
                }
            } else {
                if (new File("./data").mkdirs()) {
                    if (!f.createNewFile()) {
                        throw new LeeException("Cannot create ./data/taskList.txt!");
                    }
                } else {
                    throw new LeeException("Cannot create ./data directory!");
                }
            }
        } catch (LeeException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("An error occurred.");
            throw new RuntimeException(e);
        }
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();
        while (!cmd.equals("bye")) {
            analyze(cmd);
            cmd = sc.nextLine();
        }
        System.out.print(horLine + exits + horLine);
        sc.close();
    }
}
