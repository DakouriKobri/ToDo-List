

import datastructure.Task;
import datastructure.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * The class Menu  allows the user to see options, and make selection, and allows the code to then be executed
 * according to the selection option.
 *
 * @author Dakouri Maurille-Constant Kobri
 * @version 1.0
 * @since 2021.03.09
 */

public class Menu {
    private Scanner scanner;
    private TaskList taskList;
    private FileHandler fh;
    private String TABLE_HEADER = String.format(Task.TABLE_FORMAT, "DATE", "STATUS", "PROJECT", "TITLE");

    /**
     * Instantiates a scanner that reads text from the terminal.
     */
    public Menu() {
        scanner = new Scanner(System.in);
        fh = new FileHandler();
        taskList = fh.read();
    }

    /**
     * Prints a welcome message to the console and displays the menu.
     */
    public void displayMenu() {
        int option = 0;

        while (option != 4) {
            int numberOfTasks = taskList.getTaskListSize();
            int numberOfTasksDone = taskList.getNumberOfTasksDone();

            System.out.println();
            System.out.println(">> Welcome to your ToDo-List.");
            System.out.println(
                    ">> You have " + numberOfTasksDone + " tasks done and " + numberOfTasks + " tasks todo.");
            System.out.println(">>");
            System.out.println(">> What would you like to do?");
            System.out.println(">> (1) See datastructure.Task List (by date or project)");
            System.out.println(">> (2) Create New datastructure.Task.");
            System.out.println(">> (3) Edit datastructure.Task (update, mark as done, remove)");
            System.out.println(">> (4) Save and Quit.");
            System.out.print(">> ");

            option = validateInt(1, 4);

            switch (option) {
                case 1 -> {
                    if (taskList.isEmpty()) {
                        System.out.println();
                        System.out.println("No tasks to show.");
                        break;
                    }
                    this.displayTaskList();
                }
                case 2 -> this.createNewTask();
                case 3 -> {
                    if (taskList.isEmpty()) {
                        System.out.println();
                        System.out.println("No tasks to edit.");
                        break;
                    }
                    this.selectTaskToEdit();
                }
                case 4 -> this.saveAndQuit();
                default -> {
                    System.out.println();
                    System.out.println("Enter an option number!");
                }
            }

            option = 0;
        }
    }

    /**
     * Displays the options to see the task list by due date or project, or return to the menu.
     */
    private void displayTaskList() {
        int option = 0;

        while (option != 3) {
            System.out.println();
            System.out.println(">> Select an option:");
            System.out.println(">> (1) Show datastructure.Task list by Due Date.");
            System.out.println(">> (2) Show datastructure.Task list by Project.");
            System.out.println(">> (3) Return to Menu.");
            System.out.print(">> ");

            option = validateInt(1, 3);

            switch (option) {
                case 1 -> {
                    displayTaskListByDueDate();
                    //displayMenu();
                }
                case 2 -> {
                    selectProject();
                    //displayMenu();
                }
                case 3 -> {
                    return;
                }
                default -> System.out.println("Enter an option number.");
            }

            option = 0;
        }
    }

    /**
     * Displays the task list sorted by due date.
     */
    private void displayTaskListByDueDate() {
        System.out.println();
        System.out.println("datastructure.Task List by due date");
        System.out.println();
        System.out.println(TABLE_HEADER);
        List<Task> taskListSortedByDueDate = taskList.getTaskListByDueDate();

        for (Task t : taskListSortedByDueDate) {
            System.out.println(t);
        }
    }

    /**
     * Presents the option to display the list of tasks for a selected project.
     */
    private void selectProject() {
        List<String> projects = taskList.getProjects();
        String selectedProject;
        int option = 0;


        System.out.println();
        System.out.println(">> Select a project:");

        for (int i = 0; i < projects.size(); i++) {
            System.out.println(">> (" + (i + 1) + ") " + projects.get(i));
        }
        System.out.print(">> ");

        option = validateInt(1, projects.size());


        System.out.println();
        System.out.println("datastructure.Task List by project.");
        System.out.println();
        System.out.println(TABLE_HEADER);
        selectedProject = projects.get(option - 1);
        List<Task> selectedTask = taskList.getTaskListByProject(selectedProject);

        for (Task t : selectedTask) {
            System.out.println(t);
        }


    }

    /**
     * Create a new task in the list.
     */
    private void createNewTask() {
        System.out.println();
        System.out.println("Create a new task.");
        System.out.println();
        String title = validateString("title");

        LocalDate localDate = validateDate();
        if (localDate == null)
            return;

        boolean status = false;

        String project = validateString("project");

        Task newTask = new Task(title, localDate, status, project);
        taskList.addTask(newTask);

    }

    /**
     * Displays the option of selecting a task to edit
     */
    private void selectTaskToEdit() {
        List<Task> tasks = taskList.getTaskList();
        int option = 0;


        System.out.println();
        System.out.println("Select task to edit:");
        System.out.println();
        System.out.println("       " + TABLE_HEADER);

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println(">> (" + (i + 1) + ") " + task.toString());
        }

        System.out.print(">> ");

        option = validateInt(1, tasks.size());

        Task selectedTask = tasks.get(option - 1);
        selectOptionToEdit(selectedTask);

    }

    /**
     * Displays the option to edit a task.
     *
     * @param selectedTask The task to edit.
     */
    private void selectOptionToEdit(Task selectedTask) {
        int option = 0;

        while (option != 4) {
            System.out.println();
            System.out.println(">> What would you like to do?");
            System.out.println(">> (1) Update (Title, Due date or Project)");
            System.out.println(">> (2) Mark as done");
            System.out.println(">> (3) Remove task");
            System.out.println(">> (4) Return to Menu");
            System.out.print(">> ");

            option = validateInt(1, 4);

            switch (option) {
                case 1 -> {
                    updateTask(selectedTask);
                    //displayMenu();
                }
                case 2 -> {
                    markAsDone(selectedTask);
                    // displayMenu();
                }
                case 3 -> {
                    removeTask(selectedTask);
                    // displayMenu();
                }
                case 4 -> {
                    return;
                }
                default -> System.out.println("Write a valid value.");
            }
        }
    }

    /**
     * Updates the task title, due date and project.
     *
     * @param selectedTask The task to update.
     */
    private void updateTask(Task selectedTask) {
        System.out.println();
        System.out.println("Edit task.");
        System.out.println();
        System.out.println("Current title: " + selectedTask.getTitle());
        //System.out.print("Add a new title: ");

        String newTitle = validateString("new title");

        System.out.println("Current date: " + selectedTask.getDueDate());
        LocalDate invalidDate = validateDate();
        if (invalidDate == null)
            return;

        System.out.println("Current project: " + selectedTask.getProject());
        String newProject = validateString("new project");
    }

    /**
     * Sets a selected task as done.
     *
     * @param selectedTask The task to set as done.
     */
    private void markAsDone(Task selectedTask) {
        taskList.setAsDone(selectedTask);
        System.out.println();
        System.out.println("datastructure.Task marked as DONE!");
    }

    /**
     * Removes a selected task from its list.
     *
     * @param selectedTask The task to remove.
     */
    private void removeTask(Task selectedTask) {
        taskList.removeTask(selectedTask);
        System.out.println();
        System.out.println("datastructure.Task removed!");
    }

    /**
     * Saves the changes and close the application.
     */
    private void saveAndQuit() {
        fh.write(taskList);
        scanner.close();

        System.out.println();
        System.out.println("Your ToDo-List is now closed. Come back check often!");
        System.out.println("GOOD BYE!");
        System.exit(0);
    }

    /* ============================================
     */
    public String validateString(String parameter) {
        String data;
        while (true) {
            System.out.print("Add a " + parameter + "!");
            data = scanner.nextLine();

            if (!data.isEmpty()) {
                return data;
            } else {
                System.out.println("Please, enter a valid " + parameter + "!");
            }
        }
    }

    public int validateInt(int min, int max) {
        while (true) {
            try {
                System.out.println("Enter a number!");
                int option = Integer.parseInt(scanner.nextLine());
                if (option < min || option > max) {
                    System.out.println("Please, enter number between: " + min + " and " + max);
                    continue;
                }

                return option;
            } catch (NumberFormatException e) {
                System.out.println("Please, enter a correct number!");
            }
        }
    }

    public LocalDate validateDate() {

        while (true) {
            System.out.print("Add a new due date [yyyy-MM-dd] (Press Enter to skip): ");
            String dueDateStr = scanner.nextLine();

            if (dueDateStr.isEmpty()) {
                return null;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            try {
                LocalDate newDueDate = LocalDate.parse(dueDateStr, formatter);
                return newDueDate;
            } catch (Exception e) {
                System.out.println("Enter a valid date.");
            }
        }

    }


}

