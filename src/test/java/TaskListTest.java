import DataStructure.Task;
import DataStructure.TaskList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    public void testTaskListAdd() {
        // Arrange
        TaskList taskList = new TaskList();
        Task task = new Task(null, null, null, null);

        // Act
        taskList.addTask(task);

        // Assert
        assertEquals(1, taskList.getTaskListSize());
    }

    @Test
    public void testTaskListRemove()
    {
        // Arrange
        TaskList taskList = new TaskList();
        Task task = new Task(null, null, null, null);
        taskList.addTask(task);

        // Act
        taskList.removeTask(task);

        // Assert
        assertEquals(0, taskList.getTaskListSize());
    }
}