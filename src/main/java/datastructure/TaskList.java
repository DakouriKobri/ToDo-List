package datastructure;

import datastructure.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The datastructure.TaskList class allows the instantiation of a dynamic collection of tasks, and their manipulation.
 *
 * @author Dakouri Maurille-Constant Kobri
 * @version 1.0
 * @since 2021.03.09
 */

public class TaskList implements Serializable
{

    private List<Task> taskList;

    /**
     * Creates a list of objects of the class datastructure.TaskList.
     */
    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task task to add to the list.
     */
    public void addTask(Task task) {

        taskList.add(task);
    }

    /**
     * Removes a task item from the list.
     *
     * @param task task to remove from the list.
     */
    public void removeTask(Task task) {

        taskList.remove(task);
    }

    /**
     * Sets a task as done.
     *
     * @param task The task to set as done.
     */
    public void setAsDone(Task task) {

        task.setStatus(true);
    }

    /**
     * Returns a list of all the tasks in the collection.
     *
     * @return The list of tasks.
     */
    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     * Returns the number of tasks in the collection.
     *
     * @return The number task elements in the of list.
     */
    public int getTaskListSize() {
        return taskList.size();
    }

    /**
     * Sorts the tasks by due date.
     *
     * @return the list of tasks by due date.
     */
    public List<Task> getTaskListByDueDate() {
        Collections.sort(taskList);
        return taskList;
    }

    /**
     * Gets the list of tasks belonging to the same project.
     *
     * @param project The project whose tasks are listed.
     * @return the list of tasks belonging to the same project.
     */
    public List<Task> getTaskListByProject(String project) {
        return taskList.stream().filter(task -> task.getProject().equals(project))
                .collect(Collectors.toList());
    }

    /**
     * Returns the list of projects.
     *
     * @return The list of projects.
     */
    public List<String> getProjects() {
        List<String> projects = new ArrayList<>();
        for (Task p : taskList) {
            if (!projects.contains(p.getProject())) {
                projects.add(p.getProject());
            }
        }
        return projects;
    }

    /**
     * Returns the number of tasks marked as done.
     *
     * @return The number of tasks done.
     */
    public int getNumberOfTasksDone() {
        int numberOfTasksDone = 0;
        for (Task t : taskList) {
            if (t.getStatus()) {
                numberOfTasksDone++;
            }
        }
        return numberOfTasksDone;
    }

    /**
     * Checks if the list of tasks is empty.
     *
     * @return The value true or false.
     */
    public boolean isEmpty() {
        return getTaskListSize() == 0;
    }
}