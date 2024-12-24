package Meals;

import java.io.*;
import java.util.*;

public class MealsManagment {
    public static void addMeal(Meal meal) throws IOException {
        try (FileWriter writer = new FileWriter("meals.csv", true)) {
            writer.write(meal.toString() + "\n");
        }
    }
    public static void updateMeal(String mealName, Meal updatedMeal) throws IOException {
        List<Meal> meals = getMeals();
        try (FileWriter writer = new FileWriter("meals.csv")) {
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
        try (FileWriter writer = new FileWriter("meals.csv")) {
            for (Meal meal : meals) {
                if (!meal.getName().equals(mealName)) {
                    writer.write(meal.toString() + "\n");
                }
            }
        }
    }
    public static List<Meal> getMeals() throws IOException {
        List<Meal> meals = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("meals.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                meals.add(Meal.fromString(line));
            }
        }
        return meals;
    }

}
