package Orders;

import Execptions.Status;
import Notifications.Notification;
import Users.Customer;
import Users.Employee;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class OrderManagement {
    private static void addOrder(Order order) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Files\\orders.txt", true))) {
            writer.write(order.toString());
            writer.newLine();
        }
    }

    public static Status createOrder(Customer customer, List<OrderItem> orderItems, int orderType, double tip) {
        try {
            if (customer == null || orderItems.isEmpty()) {
                throw new Exception("Not all fields are complete.");
            }
            else {
                List<Order> orders = getOrders();
                int nextOrderId = orders.isEmpty() ? 0 : orders.get(orders.size() - 1).getId() + 1;

                Order order = new Order(nextOrderId, customer, orderItems, orderType, tip);
                addOrder(order);

                Notification n = new Notification("Done!", "Your order has been successfully added ✅\nYou can follow up on the order via the history");
                n.run();
            }
        }
        catch (Exception e) {
            return new Status(e.getMessage());
        }
        return new Status();
    }

    public static Status createOrder(int customerID, List<OrderItem> orderItems, int orderType, double tip) {
        try {
            if (customerID < 0 || orderItems.isEmpty()) {
                throw new Exception("Not all fields are complete.");
            }
            else {
                List<Order> orders = getOrders();
                System.out.println("hi");
                int nextOrderId = orders.isEmpty() ? 0 : orders.get(orders.size() - 1).getId() + 1;

                Order order = new Order(nextOrderId, customerID, orderItems, orderType, tip);
                addOrder(order);

                Notification n = new Notification("Done!", "Your order has been successfully added ✅\nYou can follow up on the order via the history");
                n.run();
            }
        }
        catch (Exception e) {
            return new Status(e.getMessage());
        }
        return new Status();
    }

    public static List<Order> getOrders() throws IOException {
        List<Order> orders = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Files\\orders.txt"))) {
            String line;
            System.out.println("fdjf");
            while ((line = reader.readLine()) != null) {
                orders.add(Order.fromString(line));
            }
        }
        return orders;
    }

    public static long countOrdersForToday() throws IOException {
        List<Order> orders = getOrders();
        LocalDate today = LocalDate.now();
        return orders.stream()
                .filter(order -> order.getOrderDate().toLocalDate().equals(today))
                .count();
    }
    
    public static void updateOrderStatus(Order updatedOrder) throws IOException {
        List<Order> orders = getOrders();
        for (Order order : orders) {
            if (order.getId() == updatedOrder.getId()) {
                order.setStatus(updatedOrder.getStatus());
                break;
            }
        }}
}
