package Meals;

public class Meal {
    private String mealName;
    private String ingredients;
    private double price;
    int orderCnt;

    public String getMealName() {
        return mealName;
    }
    public void setMealName(String mealName) {
        this.mealName = mealName;
    }
    public String getIngredients() {
        return ingredients;
    }
    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    private Meal(String mealName, String ingredients, double price, int orderCnt) {
        this.mealName = mealName;
        this.ingredients = ingredients;
        this.price = price;
        this.orderCnt = orderCnt;
    }
    public Meal(String mealName, String ingredients, double price) {
        this.mealName = mealName;
        this.ingredients = ingredients;
        this.price = price;
        this.orderCnt = 0;
    }
    public Meal () {}
    public String getName () {
        return mealName;
    }
    public String toString() {
        return mealName + "||" + ingredients + "||" + price + "||" + orderCnt;
    }
    public static Meal fromString(String line) {
        String[] parts = line.split("||", 4);
        return new Meal(parts[0], parts[1], Double.parseDouble(parts[2]), Integer.parseInt(parts[3]));
    }
}