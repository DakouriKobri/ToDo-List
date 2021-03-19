package fileHandler;

import datastructure.TaskList;

import java.io.*;

/**
 * The class fileHandler.FileHandler stores task list in the file todoly.txt
 * through the method write(),
 * and accesses task list details through the method read().
 *
 * @author Dakouri Maurille-Constant Kobri
 * @version 1.0
 * @since 2021.03.09
 */
public class FileHandler {

    /**
     * Saves task list to file "todoly.txt"
     *
     * @param taskList The list of tasks.
     */
    public void write(TaskList taskList) {
        try {
            File file = new File("todoly.txt");
            FileOutputStream fileStream = new FileOutputStream(file);
            ObjectOutputStream writer = new ObjectOutputStream(fileStream);

            writer.writeObject(taskList);
            writer.close();
            fileStream.close();

        } catch (IOException e) {
            System.out.println("Error writing to a file : " + e.toString());
        }
    }

    /**
     * Reads tasks from the file "todoly.txt"
     *
     * @return The list of tasks.
     */
    public TaskList read() {
        TaskList taskList = new TaskList();
        try {
            File file = new File("todoly.txt");
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream reader = new ObjectInputStream(fileInputStream);

            taskList = (TaskList) reader.readObject();

            reader.close();
            fileInputStream.close();

        } catch (IOException e) {
            System.out.println("problem with reading ... " + e.toString());
        } catch (ClassNotFoundException e) {
            System.out.println("the file has different data " + e.toString());
        }

        return taskList;

    }

}
