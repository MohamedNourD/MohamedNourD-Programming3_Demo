package Users;

import Orders.Order;

import java.util.*;

public class Customer extends User {
    private List<Integer> ordersID;
    public Customer (String firstName, String lastName, String email, String password, int userType) {
        super(firstName, lastName, email, password, userType);
    }
    public void newOrder(int orderId) {
        ordersID.add(orderId);
    }
    public int getCntOrders() {
        return ordersID.size();
    }
}
