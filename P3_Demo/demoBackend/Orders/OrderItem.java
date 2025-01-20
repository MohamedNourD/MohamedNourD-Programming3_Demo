package Orders;

import Meals.*;

import java.io.IOException;

public class OrderItem {
    private int mealId;
    private int quantity;
    private double price;

    public OrderItem(Meal meal, int quantity) {
        this.mealId = meal.getId();
        this.quantity = quantity;
        this.price = meal.getPrice() * quantity;
        System.out.println("quantity:" + quantity);
        meal.addCnt(quantity);
    }

    private OrderItem(int mealId, int quantity, double price) throws IOException {
        this.mealId = mealId;
        this.quantity = quantity;
        this.price = price;
    }
    // private OrderItem(String mealName, int quantity) {
    //     this.mealName = mealName;
    //     this.quantity = quantity;
    //     this.price = price;
    // }
    public OrderItem () {}

    public int getMealId() {
        return mealId;
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
        return mealId + "|" + quantity + "|" + price;
    }
    public static OrderItem fromString(String str) throws IOException {
        String[] parts = str.split("\\|", 3);
        return new OrderItem(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Double.parseDouble(parts[2]));
    }
}
