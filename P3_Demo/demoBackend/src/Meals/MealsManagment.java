package Meals;

import Execptions.Status;

import java.io.*;
import java.util.*;

public class MealsManagment {
    private static void addMeal(Meal meal) throws IOException {
        try (FileWriter writer = new FileWriter("Files\\meals.txt", true)) {
            writer.write(meal.toString() + "\n");
        }
    }
    public static Status createMeal (String mealName, String ingredients, double price) {
        try{
            if (mealName.isEmpty() || ingredients.isEmpty() || price == 0.0) {
                throw new Exception();
            }
            else {
                List<Meal> meals = getMeals();
                int nextMealId = meals.isEmpty() ? 1 : meals.get(meals.size() - 1).getId() + 1;

                Meal meal = new Meal(nextMealId, mealName, ingredients, price);
                addMeal(meal);
                return new Status();
            }
        }
        catch (Exception e) {
            return new Status("Not all fields are complete.");
        }
    }
    
    private static void updateMeal(int mealId, Meal updatedMeal) throws IOException {
        List<Meal> meals = getMeals();
        try (FileWriter writer = new FileWriter("Files\\meals.txt")) {
            for (Meal meal : meals) {
                if (meal.getId() == mealId) {
                    writer.write(updatedMeal.toString() + "\n");
                } else {
                    writer.write(meal.toString() + "\n");
                }
            }
        }
    }

    public static Status updateMeal (int mealID, String mealName, String ingredients, double price) {
        try{
            if (mealName.isEmpty() || ingredients.isEmpty() || price == 0.0) {
                throw new Exception();
            }
            else {
                Meal meal = new Meal(mealID ,mealName, ingredients, price);
                updateMeal(mealID, meal);
                return new Status();
            }
        }
        catch (Exception e) {
            return new Status("Not all fields are complete.");
        }
    }

    public static void deleteMeal(int mealId) throws IOException {
        List<Meal> meals = getMeals();
        try (FileWriter writer = new FileWriter("Files\\meals.txt")) {
            for (Meal meal : meals) {
                if (meal.getId() != mealId) {
                    writer.write(meal.toString() + "\n");
                }
            }
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

}
