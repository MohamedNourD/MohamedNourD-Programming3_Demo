package Orders;

import Meals.*;

class OrderItem {
    private String meal;
    private int quantity;

    public OrderItem(Meal meal, int quantity) {
        this.meal = meal.getName();
        this.quantity = quantity;
    }
    public OrderItem(String meal, int quantity) {
        this.meal = meal;
        this.quantity = quantity;
    }
    public OrderItem () {}

    public String getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal.getName();
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String toString() {
        return meal + "|" + quantity;
    }
    public static OrderItem fromString(String str) {
        String[] parts = str.split("\\|", 2);
        return new OrderItem(parts[0], Integer.parseInt(parts[1]));
    }
}
