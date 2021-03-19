import datastructure.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

public class TaskTest {
    @Test
    public void testTaskHasTitleDueDateStatusAndProject() {
        String title = "title";
        LocalDate dueDate = LocalDate.now();
        Boolean status = true;
        String project = "project";
        Task task = new Task(title, dueDate, status, project);
        assertEquals(title, task.getTitle());
        assertEquals(dueDate, task.getDueDate());
        assertTrue(task.getStatus());
        assertEquals(project, task.getProject());
    }
}
