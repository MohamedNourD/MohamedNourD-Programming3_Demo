package Orders;

import Users.Customer;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class OrderManagment {
    public static void addOrder(Customer customer, List<OrderItem> items) throws IOException {
        Order order = new Order(0 ,customer.getName(), items);
        customer.newOrder(order.getId());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Files\\orders.txt", true))) {
            writer.write(order.toString());
            writer.newLine();
        }
    }
    public static List<Order> getOrders() throws IOException {
        List<Order> orders = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Files\\orders.txt"))) {
            String line;
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
                .filter(order -> order.getOrderDate().equals(today))
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
