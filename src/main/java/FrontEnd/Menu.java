package FrontEnd;

import DataStructure.Task;
import DataStructure.TaskList;
import FileHandler.FileHandler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * The class frontend.Menu  allows the user to see options, and make selection, and allows the code to then be executed
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
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_PURPLE = "\u001B[35m";


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
            System.out.println(ANSI_BLUE + ">> Welcome to your ToDo-List." + ANSI_RESET);
            System.out.println(ANSI_BLUE + ">>" + ANSI_RESET
                    + " You have " + ANSI_CYAN + numberOfTasksDone + " task(s) done" + ANSI_RESET
                    + " and " + ANSI_RED  + numberOfTasks + " task(s) todo." + ANSI_RESET);
            System.out.println(ANSI_BLUE + ">>" + ANSI_RESET);
            System.out.println(ANSI_BLUE + ">>" + ANSI_RESET + "What would you like to do?" );
            System.out.println(ANSI_BLUE + ">>" + ANSI_RESET
                    + " (" + ANSI_PURPLE + "1" + ANSI_RESET + ") See Task List (by date or project)");
            System.out.println(ANSI_BLUE + ">>" + ANSI_RESET
                    + " (" + ANSI_PURPLE + "2" + ANSI_RESET + ") Create New Task.");
            System.out.println(ANSI_BLUE + ">>" + ANSI_RESET
                    + " (" + ANSI_PURPLE + "3" + ANSI_RESET + ") Edit Task (update, mark as done, remove)");
            System.out.println(ANSI_BLUE + ">>" + ANSI_RESET
                    + " (" + ANSI_PURPLE + "4" + ANSI_RESET + ") Save and Quit");
            System.out.print(ANSI_BLUE + ">>" + ANSI_RESET);

            option = validateInt(1, 4);

            switch (option) {
                case 1 -> {
                    if (taskList.isEmpty()) {
                        System.out.println();
                        System.out.println(ANSI_BLUE + "No tasks to show." + ANSI_RESET);
                        break;
                    }
                    this.displayTaskList();
                }
                case 2 -> this.createNewTask();
                case 3 -> {
                    if (taskList.isEmpty()) {
                        System.out.println();
                        System.out.println(ANSI_BLUE + "No tasks to edit." + ANSI_RESET);
                        break;
                    }
                    this.selectTaskToEdit();
                }
                case 4 -> this.saveAndQuit();
                default -> {
                    System.out.println();
                    System.out.println(ANSI_BLUE + ">>" + ANSI_RESET + "Enter an option number! ");
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
            System.out.println(ANSI_BLUE + ">>" + ANSI_RESET + " Select an option:");
            System.out.println(ANSI_BLUE + ">>" + ANSI_RESET
                    + " (" + ANSI_PURPLE + "1" + ANSI_RESET + ") Show Task list by Due Date.");
            System.out.println(ANSI_BLUE + ">>" + ANSI_RESET
                    + " (" + ANSI_PURPLE + "2" + ANSI_RESET + ") Show Task list by Project.");
            System.out.println(ANSI_BLUE + ">>" + ANSI_RESET
                    + " (" + ANSI_PURPLE + "3" + ANSI_RESET + ") Return to Menu.");
            System.out.print(ANSI_BLUE + ">>" + ANSI_RESET);

            option = validateInt(1, 3);

            switch (option) {
                case 1 -> {
                    displayTaskListByDueDate();
                }
                case 2 -> {
                    selectProject();
                }
                case 3 -> {
                    return;
                }
                default -> System.out.println("Enter an option number ");
            }

            option = 0;
        }
    }

    /**
     * Displays the task list sorted by due date.
     */
    private void displayTaskListByDueDate() {
        System.out.println();
        System.out.println(ANSI_BLUE + "Task List by Due Date" + ANSI_RESET);
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
        System.out.println(ANSI_BLUE + ">>" + ANSI_RESET + " Select a project ");

        for (int i = 0; i < projects.size(); i++) {
            System.out.println(ANSI_BLUE + ">> " + ANSI_RESET + "("
                    + ANSI_PURPLE + (i + 1) + ANSI_RESET + ") " + projects.get(i));
        }
        System.out.print(ANSI_BLUE + ">> " + ANSI_RESET);

        option = validateInt(1, projects.size());


        System.out.println();
        System.out.println(ANSI_BLUE + "Task List by Project " + ANSI_RESET);
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
        System.out.println(ANSI_BLUE + ">> " + ANSI_RESET + "Create a new task  ");
        System.out.println();
        String title = validateString("Title");

        LocalDate localDate = validateDate();
        if (localDate == null)
            return;

        boolean status = false;

        String project = validateString("Project");

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
        System.out.println(ANSI_BLUE + ">> " + ANSI_RESET + "Select task to edit ");
        System.out.println();
        System.out.println("       " + TABLE_HEADER);

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println(ANSI_BLUE + ">> " + ANSI_RESET + "("
                    + ANSI_PURPLE + (i + 1) + ANSI_RESET + ") " + task.toString());
        }

        System.out.print(ANSI_BLUE + ">> " + ANSI_RESET);

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
            System.out.println(ANSI_BLUE + ">>" + ANSI_RESET + "What would you like to do?");
            System.out.println(ANSI_BLUE + ">>" + ANSI_RESET
                    + " (" + ANSI_PURPLE + "1" + ANSI_RESET + ") Update (Title, Due date or Project)");
            System.out.println(ANSI_BLUE + ">>" + ANSI_RESET
                    + " (" + ANSI_PURPLE + "2" + ANSI_RESET + ") Mark as done");
            System.out.println(ANSI_BLUE + ">>" + ANSI_RESET
                    + " (" + ANSI_PURPLE + "3" + ANSI_RESET + ") Remove task");
            System.out.println(ANSI_BLUE + ">>" + ANSI_RESET
                    + " (" + ANSI_PURPLE + "4" + ANSI_RESET + ") Return to Homepage Menu");
            System.out.print(ANSI_BLUE + ">>" + ANSI_RESET );

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
                default -> System.out.println(ANSI_BLUE + ">> " + ANSI_RESET + "Write a valid value ");
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
        System.out.println(ANSI_BLUE + ">> " + ANSI_RESET + "Edit task ");
        System.out.println();
        System.out.println(ANSI_BLUE + ">> " + ANSI_RESET + "Current title: " + selectedTask.getTitle());

        String newTitle = validateString("New Title");

        System.out.println(ANSI_BLUE + ">> " + ANSI_RESET + "Current date: " + selectedTask.getDueDate());
        LocalDate invalidDate = validateDate();
        if (invalidDate == null)
            return;

        System.out.println(ANSI_BLUE + ">> " + ANSI_RESET + "Current project: " + selectedTask.getProject());
        String newProject = validateString("New Project");
    }

    /**
     * Sets a selected task as done.
     *
     * @param selectedTask The task to set as done.
     */
    private void markAsDone(Task selectedTask) {
        taskList.setAsDone(selectedTask);
        System.out.println();
        System.out.println(ANSI_BLUE + "Task marked as DONE!"+ ANSI_RESET);
    }

    /**
     * Removes a selected task from its list.
     *
     * @param selectedTask The task to remove.
     */
    private void removeTask(Task selectedTask) {
        taskList.removeTask(selectedTask);
        System.out.println();
        System.out.println(ANSI_BLUE + "Task removed!" + ANSI_RESET);
    }

    /**
     * Saves the changes and close the application.
     */
    private void saveAndQuit() {
        fh.write(taskList);
        scanner.close();

        System.out.println();
        System.out.println(ANSI_BLUE + "Your ToDo-List is now closed. Come back check often!");
        System.out.println(ANSI_BLUE + "GOOD BYE!");
        System.exit(0);
    }

    /**
     * Verifies if a task already has a project or title and allows to add one if there is none.
     * @param parameter parameter value to enter.
     * @return parameter value (project or title).
     */
    public String validateString(String parameter) {
        String data;
        while (true) {
            System.out.print(ANSI_BLUE + ">> " + ANSI_RESET + "Add a " + parameter + ": ");
            data = scanner.nextLine();

            if (!data.isEmpty()) {
                return data;

            } else {
                System.out.println(ANSI_BLUE + ">> " + ANSI_RESET + "Please, enter a valid " + parameter + "!");
            }
        }
    }

    /**
     *This method makes sure the user types in an integer between the minimum and maximum inclusive.
     * @param min minimum integer value to enter
     * @param max maximum integer value to enter
     * @return integer option number
     */

    public int validateInt(int min, int max) {
        while (true) {
            try {
                System.out.println(ANSI_BLUE + ">> " + ANSI_RESET + "Enter a number ");
                int option = Integer.parseInt(scanner.nextLine());
                if (option < min || option > max) {
                    System.out.println(ANSI_BLUE + ">> " + ANSI_RESET
                            + "Please, enter number between: " + min + " and " + max );
                    continue;
                }

                return option;
            } catch (NumberFormatException e) {
                System.out.println(ANSI_BLUE + ">> " + ANSI_RESET + "Please, enter a correct number ");
            }
        }
    }

    /**
     * Allows the user to enter the date in the right format
     *
     * @return dueDate in [yyyy-MM-dd] String format.
     */
    public LocalDate validateDate() {

        while (true) {
            System.out.print(ANSI_BLUE + ">> " + ANSI_RESET
                    + "Add a new due date [yyyy-MM-dd] (Press Enter to skip) ");
            String dueDateStr = scanner.nextLine();

            if (dueDateStr.isEmpty()) {
                return null;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            try {
                return LocalDate.parse(dueDateStr, formatter);
            } catch (Exception e) {
                System.out.println(ANSI_BLUE + ">> " + ANSI_RESET + "Enter a valid date! ");
            }
        }

    }


}

