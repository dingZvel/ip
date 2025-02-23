package lee.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void getStatusIcon_doneTask_stringX() {
        Task task = new Task("Test task", true);
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void getStatusIcon_unDoneTask_stringSpace() {
        Task task = new Task("Test task", false);
        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    public void markDone_markUnDoneTask_marked() {
        Task task = new Task("Test task", false);
        task.markDone(true);
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void markDone_markDoneTask_marked() {
        Task task = new Task("Test task", true);
        task.markDone(true);
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void markDone_unMarkUnDoneTask_marked() {
        Task task = new Task("Test task", false);
        task.markDone(false);
        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    public void markDone_unMarkDoneTask_marked() {
        Task task = new Task("Test task", true);
        task.markDone(false);
        assertEquals(" ", task.getStatusIcon());
    }
}
