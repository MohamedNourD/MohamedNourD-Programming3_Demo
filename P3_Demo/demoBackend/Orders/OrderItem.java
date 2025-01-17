package Orders;

import Meals.*;

public class OrderItem {
    private String mealName;
    private int quantity;
    private double price;

    public OrderItem(Meal meal, int quantity) {
        this.mealName = meal.getName();
        this.quantity = quantity;
        this.price = meal.getPrice() * quantity;
        meal.addCnt(quantity);
    }

    private OrderItem(String mealName, int quantity, double price) {
        this.mealName = mealName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getMealName() {
        return mealName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        return mealName + "|" + quantity + "|" + price;
    }
    public static OrderItem fromString(String str) {
        String[] parts = str.split("\\|", 3);
        return new OrderItem(parts[0], Integer.parseInt(parts[1]), Double.parseDouble(parts[2]));
    }
}
