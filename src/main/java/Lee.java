import java.io.IOException;
import java.util.Scanner;

public class Lee {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    public Lee(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (LeeException e) {
            ui.showLoadingError(e);
        } catch (IOException e) {
            ui.showLoadingError(e);
            throw new RuntimeException(e);
        }
        parser = new Parser(tasks);
    }

    public void run() {
        ui.startUi();
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();
        while (!cmd.equals("bye")) {
            ui.line();
            parser.parse(cmd);
            ui.line();
            cmd = sc.nextLine();
        }
        ui.exitUi();
        sc.close();
    }

    public static void main(String[] args) {
        new Lee("./data/taskList.txt").run();
    }
}
