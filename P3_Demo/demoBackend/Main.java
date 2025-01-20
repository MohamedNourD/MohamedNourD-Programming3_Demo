
import Meals.Meal;
import Meals.MealsManagment;
import Orders.Order;
import Orders.OrderItem;
import Orders.OrderManagement;
import Users.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main (String[] args) throws IOException {
//        System.out.println(MealsManagment.createMeal("Pizza", "Cheese", 25.0, "icons\\\\Pizza.png").getMsg());
//        List<Meal> meals = MealsManagment.getMeals();
//
//        List<OrderItem> orderItems = new ArrayList<>();
//        orderItems.add(new OrderItem(meals.get(0), 3));
//        orderItems.add(new OrderItem(meals.get(1), 2));
//
//        System.out.println(OrderManagement.getOrdersEmployee());
//
//        System.out.println(OrderManagement.countOrdersForToday());
//        System.out.println(OrderManagement.countOrdersForToday());
//        System.out.println(OrderManagement.dailyRevenues());
        UsersManagement.createCustomerAccount("firas", "saeed", "f@s.com", "123", "123");

    }
}
