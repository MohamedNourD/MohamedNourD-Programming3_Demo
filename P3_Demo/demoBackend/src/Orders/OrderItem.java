<<<<<<< HEAD
package Orders;

import Meals.*;

class OrderItem {
    private String mealName;
    private int quantity;

    public OrderItem(Meal meal, int quantity) {
        this.mealName = meal.getName();
        this.quantity = quantity;
    }
    public OrderItem(String mealName, int quantity) {
        this.mealName = mealName;
        this.quantity = quantity;
    }
    public OrderItem () {}

    public String getMeal() {
        return mealName;
    }

    public void setMeal(Meal meal) {
        this.mealName = meal.getName();
    }

    public void setMeal(String mealName) {
        this.mealName = mealName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String toString() {
        return mealName + "|" + quantity;
    }
    public static OrderItem fromString(String str) {
        String[] parts = str.split("\\|", 2);
        return new OrderItem(parts[0], Integer.parseInt(parts[1]));
    }
}
=======
package Orders;

import Meals.*;

public class OrderItem {
    private String mealName;
    private int quantity;

    public OrderItem(Meal meal, int quantity) {
        this.mealName = meal.getName();
        this.quantity = quantity;
        meal.addCnt(quantity);
    }
    private OrderItem(String mealName, int quantity) {
        this.mealName = mealName;
        this.quantity = quantity;
    }
    public OrderItem () {}

    public String getMeal() {
        return mealName;
    }

    public int getQuantity() {
        return quantity;
    }

    public String toString() {
        return mealName + "|" + quantity;
    }
    public static OrderItem fromString(String str) {
        String[] parts = str.split("\\|", 2);
        return new OrderItem(parts[0], Integer.parseInt(parts[1]));
    }
}
>>>>>>> 46c045cff2f9432d444d013d858ce590c6f58777
