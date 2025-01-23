import java.util.Objects;
import java.util.Scanner;

public class Lee {
    private static final String horLine = "____________________________________________________________\n";
    private static final String greets = "Hello! I'm Lee.\n"
            + "What can I do for you?\n";
    private static final String exits = "Bye. Hope to see you again soon!\n";
    private static String[] tasks = new String[100];
    private static int numOfTasks = 0;

    private static void analyze(String command) {
        System.out.print(horLine);
        if (command.equals("list")) {
            listTasks();
        }
        else {
            addTasks(command);
        }
//        System.out.println(command + "\n" + horLine);
    }

    private static void listTasks(){
        for (int i = 0; i < numOfTasks; i ++){
            System.out.format("%d. " + tasks[i] + "\n", i + 1);
        }
        System.out.println(horLine);
    }

    private static void addTasks(String task) {
        tasks[numOfTasks] = task;
        numOfTasks ++;
        System.out.format("added: %s\n" + horLine + "\n", task);
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
