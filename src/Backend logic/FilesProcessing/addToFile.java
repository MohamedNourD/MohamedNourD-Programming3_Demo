package FilesProcessing;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

    public class addToFile <T> extends Thread {
        T object = null;

        public addToFile(T object) {
            this.object = object;
        }

        public void run() {
            String className = object.getClass().getName();
            String fileName = null;

            switch (className) {
                case "Users.Customer":
                    fileName = "FilesProcessing\\customers.txt";
                    break;
                case "Users.Employee":
                    fileName = "FilesProcessing\\employees.txt";
                    break;
                case "Meals.Meal":
                    fileName = "FilesProcessing\\meals.txt";
                    break;
                case "Orders.Order":
                    fileName = "FilesProcessing\\orders.txt";
                    break;
            }

            if (fileName != null) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                    writer.write(object.toString());
                    writer.newLine();

                    System.out.println("Successfully written to " + fileName);
                } catch (IOException e) {
                    System.out.println("Error writing to file: " + fileName);
                    e.printStackTrace();
                }
            } else {
                System.err.println("Unknown object type: " + className);
            }

        }
    }
