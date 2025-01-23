import java.util.Objects;
import java.util.Scanner;

public class Lee {
    private static final String horLine = "____________________________________________________________\n";
    private static final String greets = "Hello! I'm Lee.\n"
            + "What can I do for you?\n";
    private static final String exits = "Bye. Hope to see you again soon!\n";

    private static void echo(String command) {
        System.out.println(horLine + command + "\n" + horLine);
    }

    public static void main(String[] args) {
        System.out.println(horLine + greets + horLine);
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();
        while (!cmd.equals("bye")) {
            echo(cmd);
            cmd = sc.nextLine();
        }
        System.out.println(horLine + exits + horLine);
        sc.close();
    }
}
