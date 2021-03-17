

import java.time.LocalDate;

/**
 * The class Task possesses four fields - title, due date, status, and project,
 * that, together with some included methods allows the instantiation of Task objets.
 *
 * @author Dakouri Maurille-Constant Kobri
 * @version 1.0
 * @since 2021.03.09
 *
 */

public class Task implements Comparable<Task>{

    private String title;
    private LocalDate dueDate;
    private Boolean status;
    private String project;
    public static String TABLE_FORMAT = "%-10s  %-6s  %-20.20s  %-40.40s";

    /**
     * Constructor through which the objects of class Task will be instantiated.
     * It uses the four following Task class field as:
     *
     * @param title    task title
     * @param dueDate  task due date
     * @param status   task status
     * @param project  task project
     */
    public Task(String title, LocalDate dueDate, Boolean status, String project) {
        this.title = title;
        this.dueDate = dueDate;
        this.status = status;
        this.project = project;
    }

    /**
     * Returns task title.
     *
     * @return task title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns task due date.
     *
     * @return task due date.
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Returns task status.
     *
     * @return task status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * Returns task project.
     *
     * @return task project.
     */
    public String getProject() {
        return project;
    }

    /**
     * Returns the values of the variables of the task object in a tabular format.
     *
     * @return task parameters' values
     */
    public String toString() {

        String statusToString;

        if (!status) {
            statusToString = "TODO";
        } else {
            statusToString = "DONE";
        }

        return String.format(TABLE_FORMAT, dueDate, statusToString, project, title);
    }

    /**
     * Sets task title.
     *
     * @param title This is the title of task object.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets task due date.
     *
     * @param dueDate This is the due date of the task.
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Sets task status
     *
     * @param status This is the status of the task.
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * Sets task project.
     *
     * @param project This is the project of the task belongs to.
     */
    public void setProject(String project) {
        this.project = project;
    }

    /**
     * Returns -1,0 or 1 depending on the result of the comparison.
     * @return integer -1, 0 or 1.
     */
    public int compareTo(Task task) {
        return dueDate.compareTo(task.getDueDate());
    }

}


