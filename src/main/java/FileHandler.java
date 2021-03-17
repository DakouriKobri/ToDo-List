
//
//import java.io.*;
//
//
///**
// * The class WriteToFile writes into the file that store the tasks list. It uses
// * Gson to convert Java Objects into Json string.
// *
// * The class ReadFromFile reads from file that store the tasks list. It uses
// * Gson to convert a Json string to an equivalent Java object.
// *
// * @author Dakouri Maurille-Constant Kobri
// * @version 1.0
// * @since 2021.03.09
// *
// */
//public class FileHandler {
//
//    public void ReadFromFile {
//
//        /**
//         * Read tasks from file "todoly.json"
//         *
//         * @return The list of tasks.
//         */
//       /*
//        public static TaskList read() {
//            File file = new File("todoly.json");
//            TaskList taskList;
//
//            try {
//                FileInputStream fileInputStream = new FileInputStream(file);
//                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
//               // JsonReader jsonReader = new JsonReader(inputStreamReader);
//
//               // taskList = gson.fromJson(jsonReader, TaskList.class);
//            } catch (Exception e) {
//                taskList = new TaskList();
//            }
//
//            return taskList;
//        }
//        */
//    }
//
//    public class WriteToFile {
//
//        /**
//         * Save tasks to file "todoly.json"
//         *
//         * @param taskList The list of tasks.
//         */
//        public  void write(TaskList taskList) {
//            File file = new File("todoly.json");
//           // String json = gson.toJson(taskList);
//
//            try {
//                FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
//                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//               // bufferedWriter.write(json);
//                bufferedWriter.close();
//            } catch (Exception e) {
//            }
//        }
//    }
//
//}
