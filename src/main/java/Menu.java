

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
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
 *
 */

public class Menu {
    private Scanner scanner;
    private TaskList taskList;
    private String TABLE_HEADER = String.format(Task.TABLE_FORMAT, "DATE", "STATUS", "PROJECT", "TITLE");

    /**
     * Instantiates a scanner that reads text from the terminal.
     */
    public Menu() {
        scanner = new Scanner(System.in);
        //taskList = ReadFromFile.read();
        this.displayMenu();
    }

    /**
     * Prints a welcome message to the console and displays the menu.
     */
    private void displayMenu() {
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
            System.out.println(">> (1) See Task List (by date or project)");
            System.out.println(">> (2) Create New Task.");
            System.out.println(">> (3) Edit Task (update, mark as done, remove)");
            System.out.println(">> (4) Save and Quit.");
            System.out.print(">> ");

            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
            }

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
                    System.out.println("Write a valid value.");
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
            System.out.println(">> Pick an option:");
            System.out.println(">> (1) Show Task List By due date.");
            System.out.println(">> (2) Filter Task List By project.");
            System.out.println(">> (3) Return to Menu.");
            System.out.print(">> ");

            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
            }

            switch (option) {
                case 1 -> {
                    displayTaskListByDueDate();
                    displayMenu();
                }
                case 2 -> {
                    selectProject();
                    displayMenu();
                }
                case 3 -> displayMenu();
                default -> System.out.println("Write a valid value.");
            }

            option = 0;
        }
    }

    /**
     * Displays the task list sorted by due date.
     */
    private void displayTaskListByDueDate() {
        System.out.println();
        System.out.println("Task List by due date");
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

        while (true) {
            System.out.println();
            System.out.println(">> Select a project:");

            for (int i = 0; i < projects.size(); i++) {
                System.out.println(">> (" + (i + 1) + ") " + projects.get(i));
            }
            System.out.print(">> ");

            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
            }

            if (option > 0 && option <= projects.size()) {
                System.out.println();
                System.out.println("Task List by project.");
                System.out.println();
                System.out.println(TABLE_HEADER);
                selectedProject = projects.get(option - 1);
                List<Task> selectedTask = taskList.getTaskListByProject(selectedProject);
                for (Task t : selectedTask) {
                    System.out.println(t);
                }
                break;
            } else {
                System.out.println("Write a valid value.");
            }
        }
    }

    /**
     * Create a new task in the list.
     */
    private void createNewTask() {
        System.out.println();
        System.out.println("Create a new task.");
        System.out.println();

        boolean invalidTitle = true;
        String title = null;

        while (invalidTitle) {
            System.out.print("Add a title: ");
            title = scanner.nextLine();

            if (!title.isEmpty()) {
                invalidTitle = false;
            } else {
                System.out.println("Write a valid title.");
            }
        }

        boolean invalidDate = true;
        LocalDate localDate = null;

        while (invalidDate) {
            System.out.print("Add a due date [yyyy-MM-dd]: ");
            String dueDateStr = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                localDate = LocalDate.parse(dueDateStr, formatter);
                invalidDate = false;
            } catch (Exception e) {
                System.out.println("Write a valid date.");
            }
        }

        Boolean status = false;

        boolean invalidProject = true;
        String project = null;

        while (invalidProject) {
            System.out.print("Add a project: ");
            project = scanner.nextLine();

            if (!project.isEmpty()) {
                invalidProject = false;
            } else {
                System.out.println("Add a valid project.");
            }
        }

        Task newTask = new Task(title, localDate, status, project);
        taskList.addTask(newTask);
    }

    /**
     * Displays the option of selecting a task to edit
     */
    private void selectTaskToEdit() {
        List<Task> tasks = taskList.getTaskList();
        int option = 0;

        while (true) {
            System.out.println();
            System.out.println("Select task to edit:");
            System.out.println();
            System.out.println("       " + TABLE_HEADER);

            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                System.out.println(">> (" + (i + 1) + ") " + task.toString());
            }
            System.out.print(">> ");

            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
            }

            if (option > 0 && option <= tasks.size()) {
                Task selectedTask = tasks.get(option - 1);
                selectOptionToEdit(selectedTask);
                break;
            } else {
                System.out.println("Write a valid value.");
            }
        }
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

            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
            }

            switch (option) {
                case 1 -> {
                    updateTask(selectedTask);
                    displayMenu();
                }
                case 2 -> {
                    markAsDone(selectedTask);
                    displayMenu();
                }
                case 3 -> {
                    removeTask(selectedTask);
                    displayMenu();
                }
                case 4 -> displayMenu();
                default -> System.out.println("Write a valid value.");
            }

            option = 0;
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
        System.out.print("Add a new title (Press Enter to skip): ");
        String newTitle = scanner.nextLine();
        if (!newTitle.isEmpty()) {
            selectedTask.setTitle(newTitle);
        }

        boolean invalidDate = true;

        while (invalidDate) {
            System.out.println("Current due date: " + selectedTask.getDueDate());
            System.out.print("Add a new due date [yyyy-MM-dd] (Press Enter to skip): ");
            String dueDateStr = scanner.nextLine();
            if (dueDateStr.isEmpty()) {
                break;
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                LocalDate newDueDate = LocalDate.parse(dueDateStr, formatter);
                selectedTask.setDueDate(newDueDate);
                invalidDate = false;
            } catch (Exception e) {
                System.out.println("Enter a valid date.");
            }
        }

        System.out.println("Current project: " + selectedTask.getProject());
        System.out.print("Create a new project (Press Enter to skip): ");
        String newProject = scanner.nextLine();
        if (!newProject.isEmpty()) {
            selectedTask.setProject(newProject);
        }
    }

    /**
     * Sets a selected task as done.
     *
     * @param selectedTask The task to set as done.
     */
    private void markAsDone(Task selectedTask) {
        taskList.setAsDone(selectedTask);
        System.out.println();
        System.out.println("Task marked as DONE!");
    }

    /**
     * Removes a selected task from its list.
     *
     * @param selectedTask The task to remove.
     */
    private void removeTask(Task selectedTask) {
        taskList.removeTask(selectedTask);
        System.out.println();
        System.out.println("Task removed!");
    }

    /**
     * Saves the changes and close the application.
     */
    private void saveAndQuit() {
        //WriteToFile.write(taskList);
        scanner.close();

        System.out.println();
        System.out.println("Your ToDo-List is closed. Come back soon!");
        System.exit(0);
    }
}

