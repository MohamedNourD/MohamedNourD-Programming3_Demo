package Users;

import java.util.*;

public class Customer extends User {
    private int customerId;
    private List<Integer> ordersID;
    public Customer (int customerId, String firstName, String lastName, String email, String password, int userType) {
        super(firstName, lastName, email, password, userType);
    }
    public Customer (int customerId, String firstName, String lastName, String email, String password, int userType, List<Integer> ordersID) {
        super(firstName, lastName, email, password, userType);
        this.customerId = customerId;
        this.ordersID = ordersID;
    }
    public int getId() {return customerId;}
    public void newOrder(int orderId) {
        ordersID.add(orderId);
    }
    public int getCntOrders() {
        return ordersID.size();
    }

    public String toString() {
        String idString = "";
        for (int i = 0; i < ordersID.size(); i++) {
            idString = idString.concat(ordersID.get(i).toString());
            if (i < ordersID.size() - 1) {
                idString = idString.concat("|");
            }
        }

        return customerId + "," + getFirstName() + "," + getLastName() + "," + getEmail() + "," + getPassword() + "," + getUserType() + "," + idString;
    }
    public static Customer fromString(String line) {
        String[] parts = line.split(",", 6);
        List<Integer> ordersID = new ArrayList<>();

        String[] idParts = parts[3].split("\\|");
        for (String id : idParts) {
            ordersID.add(Integer.valueOf(id));
        }
        return new Customer(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4], Integer.parseInt(parts[5]), ordersID);
    }
}
