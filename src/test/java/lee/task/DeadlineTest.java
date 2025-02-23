package lee.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void reschedule_correctDate_success() {
        Deadline deadline = new Deadline("Test deadline", " 2019-02-12 16:00");
        try {
            deadline.reschedule(" 2020-02-12 16:00");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void reschedule_inCorrectDate_exceptionThrown() {
        Deadline deadline = new Deadline("Test deadline", " 2019-02-12 16:00");
        try {
            deadline.reschedule(" 2024-10-006 10:00");
            fail();
        } catch (Exception e) {
            assertEquals("Please give the time in correct form", e.getMessage());
        }
    }
}
