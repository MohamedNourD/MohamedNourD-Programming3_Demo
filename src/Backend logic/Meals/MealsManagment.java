package Meals;

import Execptions.Status;
import FilesProcessing.addToFile;
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
            createMeal(updatedMeal.getName(), updatedMeal.getIngredients(), updatedMeal.getPrice(), updatedMeal.getIconPath());
            return new Status("Meal with ID " + mealID + " not found! it will be added at the end");
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
        n.start();

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
                n.start();
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
                int nextMealId = meals.isEmpty() ? 1 : meals.get(meals.size() - 1).getId() + 1;

                Meal meal = new Meal(nextMealId, mealName, ingredients, price, iconPath);
                addToFile<Meal> file = new addToFile<>(meal);
                file.start();

                Notification n = new Notification("Done", "The meal has been added successfully!");
                n.start();

            }
        } catch (Exception e) {
            return new Status("Not all fields are complete.");
        }

        Notification n = new Notification("Done!", "The meal has been added successfully 🍴");
        n.start();

        return new Status();
    }

    public static Meal mostOrderedMeal() throws IOException {
        List<Meal> meals = getMeals();
        return Collections.max(meals, Comparator.comparingInt(Meal::getOrderCnt));
    }

    public static Meal getMealById(int id) throws IOException {
        List<Meal> meals = getMeals();

        for (Meal meal : meals) {
            if (meal.getId() == id) {
                return meal;
            }
        }
        return null;
    }

    public static int getIdByMealName(String mealName) throws IOException {
        List<Meal> meals = getMeals();

        for (Meal meal : meals) {
            if (meal.getName().equals(mealName)) {
                return meal.getId();
            }
        }
        return 0;
    }

    public static String getIconPathById(int id) throws IOException {
        List<Meal> meals = getMeals();

        for (Meal meal : meals) {
            if (meal.getId() == id) {
                return meal.getIconPath();
            }
        }
        return "";
    }

    static Status updateMealInternally(int mealID, Meal updatedMeal) {
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
        System.out.println("before updating: " + updatedMeal.getOrderCnt());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Files\\meals.txt", false))) {
            for (Meal meal : meals) {
                writer.write(meal.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            return new Status(e.getMessage());
        }

        return new Status();
    }

}
