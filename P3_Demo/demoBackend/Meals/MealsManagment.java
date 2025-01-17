package Meals;

import Execptions.Status;
import Notifications.Notification;

import java.io.*;
import java.util.*;

public class MealsManagment {
    private static void addMeal(Meal meal) throws IOException {
        try (FileWriter writer = new FileWriter("Files\\meals.txt", true)) {
            writer.write(meal.toString() + "\n");
        }
    }

    public static Status updateMeal(int mealID, Meal updatedMeal) {
        List<Meal> meals;

        try {
            meals = getMeals();
        } catch (IOException e) {
            return new Status(e.getMessage());
        }

        boolean mealFound = false;

        for (int i = 0; i < meals.size(); i++) {
            if (meals.get(i).getId() == mealID) {
                meals.set(i, updatedMeal);
                mealFound = true;
                break;
            }
        }

        if (!mealFound) {
            return new Status("Meal with ID " + mealID + " not found!");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Files\\meals.txt", false))) {
            for (Meal meal : meals) {
                writer.write(meal.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            return new Status(e.getMessage());
        }

        Notification n = new Notification("Done!", "The meal has been updated successfully 🔁");
        n.run();

        return new Status();
    }


    public static Status deleteMeal(int mealID) {
        File file = new File("Files\\meals.txt");
        StringBuilder fileContent = new StringBuilder();
        boolean isDeleted = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Meal meal = Meal.fromString(line);
                if (meal.getId() != mealID) {
                    fileContent.append(line).append("\n");
                } else {
                    isDeleted = true;
                }
            }
        } catch (IOException e) {
            return new Status(e.getMessage());
        }

        if (isDeleted) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(fileContent.toString());
                Notification n = new Notification("Done!", "The meal has been deleted successfully 🗑️");
                n.run();
                return new Status();
            } catch (IOException e) {
                return new Status(e.getMessage());
            }
        } else {
            return new Status("Meal not found.");
        }
    }


    public static List<Meal> getMeals() throws IOException {
        List<Meal> meals = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Files\\meals.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                meals.add(Meal.fromString(line));
            }
        }
        return meals;
    }

    public static Status createMeal(String mealName, String ingredients, double price, String iconPath) {
        try {
            if (mealName.isEmpty() || ingredients.isEmpty() || price == 0.0 || iconPath.isEmpty()) {
                throw new Exception();
            } else {
                List<Meal> meals = getMeals();
                int nextMealId = meals.isEmpty() ? 0 : meals.get(meals.size() - 1).getId() + 1;

                Meal meal = new Meal(nextMealId, mealName, ingredients, price, iconPath);
                addMeal(meal);
            }
        } catch (Exception e) {
            return new Status("Not all fields are complete.");
        }

        Notification n = new Notification("Done!", "The meal has been added successfully 🍴");
        n.run();

        return new Status();
    }

    public static String mostOrderedMeal () throws IOException {
        List<Meal> meals = getMeals();
        return Collections.max(meals, Comparator.comparingInt(Meal::getOrderCnt)).getName();
    }

}
