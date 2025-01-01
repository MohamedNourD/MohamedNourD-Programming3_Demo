<<<<<<< HEAD
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
=======
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
>>>>>>> 46c045cff2f9432d444d013d858ce590c6f58777
