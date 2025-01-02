package Users;

import Orders.Order;

import java.util.*;

public class Customer extends User {
    private List<Integer> ordersNum;
    public Customer (String firstName, String lastName, String email, String password, int userType) {
        super(firstName, lastName, email, password, userType);
    }
    public void newOrder(Order order) {
        ordersNum.add(order.getId());
    }
    public int getCntOrders() {
        return ordersNum.size();
    }
}
