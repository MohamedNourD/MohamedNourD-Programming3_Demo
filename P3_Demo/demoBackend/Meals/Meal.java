package Meals;

public class Meal {
    private String mealName;
    private String ingredients;
    private double price;
    int orderCnt;

    private Meal(String mealName, String ingredients, double price, int orderCnt) {
        this.mealName = mealName;
        this.ingredients = ingredients;
        this.price = price;
        this.orderCnt = orderCnt;
    }
    public Meal () {}
    public String getName () {
        return mealName;
    }
    public boolean createMeal (String mealName, String ingredients, int price) {
        try {
            if (mealName.isEmpty() || ingredients.isEmpty() || price == 0) {
                throw new Exception();
            }
            else {
                this.mealName = mealName;
                this.ingredients = ingredients;
                this.price = price;
            }
        }
        catch (Exception e) {
            System.out.println("There is an empty field");
            return false;
        }
        return true;
    }
    public String toString() {
        return mealName + "," + ingredients + "," + price + "," + orderCnt;
    }
    public static Meal fromString(String line) {
        String[] parts = line.split(",", 4);
        return new Meal(parts[0], parts[1], Double.parseDouble(parts[2]), Integer.parseInt(parts[3]));
    }
}