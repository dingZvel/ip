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

public class Storage {
    public String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private void addTaskFromLine(ArrayList<Task> tasks, String line) throws LeeException {
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
            if (new File("./data").mkdirs()) {
                if (!f.createNewFile()) {
                    throw new LeeException("Cannot create ./data/taskList.txt!");
                }
                return new ArrayList<>();
            } else {
                throw new LeeException("Cannot create ./data directory!");
            }
        }
    }
}
