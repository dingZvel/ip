package lee.util;

import lee.LeeException;
import lee.task.Deadline;
import lee.task.Event;
import lee.task.Task;
import lee.task.ToDo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Aggregates all storage related methods.
 */
public class Storage {
    public String filePath;

    /**
     * Initializes the path to the data file.
     *
     * @param filePath The string literal representing the path to the data file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Retrieves one task from one line of the data file.
     *
     * @param tasks The current task list.
     * @param line One line from the data file.
     * @throws LeeException If the data file is corrupted.
     */
    private void addTaskFromLine(ArrayList<Task> tasks, String line) throws LeeException {
        String[] task = line.split("\\|");
        if (task.length < 3) {
            throw new LeeException("Data file corrupted!");
        }
        boolean isOne = task[1].equals("1");
        boolean isZero = task[1].equals("0");
        boolean secondPlaceCorrect = isOne || isZero;
        if (!secondPlaceCorrect) {
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

    /**
     * Loads the task list saved in the data file.
     *
     * @return The task list.
     * @throws LeeException If the data file cannot be created due to some reason.
     * @throws IOException If encounters any issue when reading the file.
     */
    public ArrayList<Task> load() throws LeeException, IOException {
        File f = new File(filePath);
        if (f.exists()) {
            ArrayList<Task> tasks = new ArrayList<>();
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                addTaskFromLine(tasks, s.nextLine());
            }
            return tasks;
        } else {
            if (!new File("./data").mkdirs()) {
                throw new LeeException("Cannot create ./data directory!");
            }
            if (!f.createNewFile()) {
                throw new LeeException("Cannot create ./data/taskList.txt!");
            }
            assert f.exists() : "The data file should be exist at this point.";
            return new ArrayList<>();
        }
    }
}
