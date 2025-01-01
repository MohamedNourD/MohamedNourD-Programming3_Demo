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
    public static void updateMeal(String mealName, Meal updatedMeal) throws IOException {
        List<Meal> meals = getMeals();
        try (FileWriter writer = new FileWriter("Files\\meals.txt")) {
            for (Meal meal : meals) {
                if (meal.getName().equals(mealName)) {
                    writer.write(updatedMeal.toString() + "\n");
                } else {
                    writer.write(meal.toString() + "\n");
                }
            }
        }
    }
    public static void deleteMeal(String mealName) throws IOException {
        List<Meal> meals = getMeals();
        try (FileWriter writer = new FileWriter("Files\\meals.txt")) {
            for (Meal meal : meals) {
                if (!meal.getName().equals(mealName)) {
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

    public static Status createMeal (String mealName, String ingredients, double price) {
        try{
            if (mealName.isEmpty() || ingredients.isEmpty() || price == 0.0) {
                throw new Exception();
            }
            else {
                Meal meal = new Meal(mealName, ingredients, price);
                addMeal(meal);
            }
        }
        catch (Exception e) {
            return new Status("Not all fields are complete.");
        }
        return new Status();
    }

}
