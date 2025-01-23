import java.util.Scanner;

public class Lee {
    private static final String horLine = "____________________________________________________________\n";
    private static final String greets = "Hello! I'm Lee.\n"
            + "What can I do for you?\n";
    private static final String exits = "Bye. Hope to see you again soon!\n";
    private static final Task[] tasks = new Task[100];
//    private static String[] tasks = new String[100];
//    private static boolean[] taskStatus = new boolean[100];
    private static int numOfTasks = 0;

    private static void analyze(String command) {
        System.out.print(horLine);
        String[] cmds = command.split(" ");
        String first = cmds[0];
        if (command.equals("list")) {
            listTasks();
        }
        else if (first.equals("mark")) {
            mark(cmds[1], true);
        }
        else if (first.equals("unmark")) {
            mark(cmds[1], false);
        }
        else if (first.equals("todo")) {
            String task = command.split(" ", 2)[1];
            addToDo(task);
        }
        else if (first.equals("deadline")) {
            String order = command.split(" ", 2)[1];
            String task = order.split("/by")[0];
            String by = order.split("/by")[1];
            addDeadline(task, by);
        }
        else if (first.equals("event")) {
            String order = command.split(" ", 2)[1];
            String task = order.split("/from")[0];
            String from = order.split("/from")[1].split("/to")[0];
            String to = order.split("/from")[1].split("/to")[1];
            addEvent(task, from, to);
        }
        else {
            addTasks(command);
        }
        System.out.println(horLine);
    }

    private static void listTasks(){
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < numOfTasks; i ++){
            System.out.format("%d." + showTask(i), i + 1);
        }
    }

    private static void addTasks(String task) {
        tasks[numOfTasks] = new Task(task);
        numOfTasks ++;
        System.out.format("added: %s\n", task);
    }

    private static String showTask(int index) {
        return tasks[index].toString() + "\n";
    }

    private static void mark(String num, boolean b){
        int index = Integer.parseInt(num) - 1;
        tasks[index].isDone = b;
        System.out.format("%s I've marked this task as %s:\n"
                + showTask(index), b ? "Nice!" : "OK,", b ? "done" : "not done yet");
    }

    private static void addToDo(String task) {
        tasks[numOfTasks] = new ToDo(task);
        numOfTasks ++;
        System.out.format("Got it. I've added this task:\n  %s\n" +
                "Now you have %d tasks in the lists.\n",
                tasks[numOfTasks - 1].toString(), numOfTasks);
    }

    private static void addDeadline(String task, String by) {
        tasks[numOfTasks] = new Deadline(task, by);
        numOfTasks ++;
        System.out.format("Got it. I've added this task:\n  %s\n" +
                        "Now you have %d tasks in the lists.\n",
                tasks[numOfTasks - 1].toString(), numOfTasks);
    }

    private static void addEvent(String task, String from, String to) {
        tasks[numOfTasks] = new Event(task, from, to);
        numOfTasks ++;
        System.out.format("Got it. I've added this task:\n  %s\n" +
                        "Now you have %d tasks in the lists.\n",
                tasks[numOfTasks - 1].toString(), numOfTasks);
    }

    public static void main(String[] args) {
        System.out.println(horLine + greets + horLine);
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
