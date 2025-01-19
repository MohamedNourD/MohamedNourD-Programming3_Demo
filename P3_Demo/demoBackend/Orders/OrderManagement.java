package Orders;

import Execptions.Status;
import Notifications.Notification;
import Users.Customer;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class OrderManagement {
    private static void addOrder(Order order) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Files\\orders.txt", true))) {
            writer.write(order.toString());
            writer.newLine();
        }
        System.out.println("added");
    }

    private static List<Order> getOrders() throws IOException {
        List<Order> orders = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Files\\orders.txt"))) {
            String line;
            System.out.println("fdjfsdfsf");
            while ((line = reader.readLine()) != null) {
                orders.add(Order.fromString(line));
            }
        }
        return orders;
    }

    public static List<Order> getOrdersEmployee() throws IOException {
        List<Order> orders = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Files\\orders.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Order order = Order.fromString(line);
                System.out.println(order.getOrderStatus());
                if (order.getOrderStatus().equals("pending"))
                    orders.add(order);
            }
        }
        return orders;
    }

    public static Status createOrder(int customerID, List<OrderItem> orderItems, int orderType, double tip) {
        try {
            if (customerID < 0 || orderItems.isEmpty()) {
                throw new Exception("Not all fields are complete.");
            }
            else {
                List<Order> orders = getOrders();
                int nextOrderId = orders.isEmpty() ? 0 : orders.get(orders.size() - 1).getId() + 1;

                Order order = new Order(nextOrderId, customerID, orderItems, orderType, tip);
                System.out.println("to add");
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

    public static HashMap<LocalDate, Integer> countOrdersForToday() throws IOException {
        List<Order> orders = getOrders();
        HashMap<LocalDate, Integer> cntMap = new HashMap<>();

        for (Order order : orders) {
            LocalDate dateKey = order.getOrderDate().toLocalDate();
            cntMap.put(dateKey, cntMap.getOrDefault(dateKey, 0) + 1);
        }

        return cntMap;
    }

    public static HashMap<LocalDate, Double> dailyRevenues() throws IOException {
        List<Order> orders = getOrders();
        HashMap<LocalDate, Double> revenueMap = new HashMap<>();

        for (Order order : orders) {
            LocalDate dateKey = order.getOrderDate().toLocalDate();
            revenueMap.put(dateKey, revenueMap.getOrDefault(dateKey, 0.0) + order.getOrderPrice());
        }

        return revenueMap;
    }
    
    public static void updateOrderStatus(Order updatedOrder) throws IOException {
        List<Order> orders = getOrders();
        for (Order order : orders) {
            if (order.getId() == updatedOrder.getId()) {
                order.updateStatus(updatedOrder.getStatus());
                break;
            }
        }
    }

    public static Status updateOrder(int orderID, Order updatedOrder) {
        List<Order> orders;

        try {
            orders = getOrders();
        } catch (IOException e) {
            return new Status(e.getMessage());
        }

        boolean orderFound = false;

        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId() == orderID) {
                orders.set(i, updatedOrder);
                orderFound = true;
                break;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Files\\orders.txt", false))) {
            for (Order order : orders) {
                writer.write(order.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            return new Status(e.getMessage());
        }

        Notification n = new Notification("Done!", "The order status has been updates successfully");
        n.run();

        return new Status();
    }
}
