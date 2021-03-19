import datastructure.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
        assertEquals(status, task.getStatus());
        assertEquals("datastructure.Task should have a project", project, task.getProject());
    }
}
