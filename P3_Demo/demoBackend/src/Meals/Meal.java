package Meals;

public class Meal {

    private int mealId;
    private String mealName;
    private String ingredients;
    private double price;
    private int orderCnt;

    public Meal(int mealId, String mealName, String ingredients, double price, int orderCnt) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.ingredients = ingredients;
        this.price = price;
        this.orderCnt = orderCnt;
    }

    public Meal(int mealId, String mealName, String ingredients, double price) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.ingredients = ingredients;
        this.price = price;
        this.orderCnt = 0;
    }

    public Meal() {}

    public int getId() {
        return mealId;
    }

    public String getName() {
        return mealName;
    }

    public void setName(String mealName) {
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

    public void addCnt(int num) {
        orderCnt += num;
    }

    public int getOrderCnt() {
        return orderCnt;
    }

    @Override
    public String toString() {
        return mealId + "||" + mealName + "||" + ingredients + "||" + price + "||" + orderCnt;
    }

    public static Meal fromString(String line) {
        String[] parts = line.split("\\|\\|", 5);
        return new Meal(
                Integer.parseInt(parts[0]),
                parts[1],
                parts[2],
                Double.parseDouble(parts[3]),
                Integer.parseInt(parts[4])
        );
    }
}
