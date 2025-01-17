
import Meals.Meal;
import Meals.MealsManagment;
import Orders.OrderItem;
import Orders.OrderManagement;
import Users.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main (String[] args) throws IOException {
        System.out.println(MealsManagment.createMeal("Pizza", "Cheese", 25.0, "icons\\\\Pizza.png").getMsg());
//        List<Meal> meals = MealsManagment.getMeals();
//
//        List<OrderItem> orderItems = new ArrayList<>();
//        orderItems.add(new OrderItem(meals.get(0), 3));
//        orderItems.add(new OrderItem(meals.get(1), 2));
//
//
//        System.out.println(OrderManagement.createOrder(3, orderItems, 1, 2.5).getMsg());

        System.out.println(OrderManagement.countOrdersForToday());
    }
}
