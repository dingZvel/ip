package lee;

import lee.task.TaskList;
import lee.ui.Ui;
import lee.util.Parser;
import lee.util.Storage;

import java.io.IOException;
import java.util.Scanner;

/**
 * Serves as the entry point of the whole project.
 */
public class Lee {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /**
     * Sets up the basic fields and instantiate relevant objects.
     *
     * @param filePath The file path to the taskList data file.
     */
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
        parser = new Parser(tasks, ui);
    }

    /**
     * Runs the main method.
     */
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

    /**
     * Serves as the entry point of the whole project.
     *
     * @param args Arguments from commandline.
     */
    public static void main(String[] args) {
        new Lee("./data/taskList.txt").run();
    }
}
