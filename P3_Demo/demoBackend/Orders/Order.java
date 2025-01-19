package Orders;

import Users.UsersManagement;

import java.io.IOException;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Order {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private int orderId;
    private int customerID;
    private LocalDateTime orderDate;
    private List<OrderItem> orderItems;
    private double orderPrice;
    private double tip;
    private int orderType;
    private String orderStatus;

    public int getOrderId() {
        return orderId;
    }

    public int getCustomerID() {
        return customerID;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public int getOrderType() {
        return orderType;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getId() {
        return orderId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public double getTip() {
        return tip;
    }

    public Order(int orderId, int customerID, List<OrderItem> orderItems, int orderType, double tip) throws IOException {
        this.orderId = orderId;
        this.customerID = customerID;
        this.orderDate = LocalDateTime.now();
        this.orderItems = orderItems;
        this.orderType = orderType;
        this.orderStatus = "pending";
        this.tip = tip;

        orderPrice = 0;
        for (OrderItem orderItem : orderItems) {
            orderPrice += orderItem.getPrice();
        }
        UsersManagement.getCustomerById(customerID).newOrder(orderId);
    }

    private Order (int orderId, int customerID, LocalDateTime orderDate, List<OrderItem> orderItems, int orderType, String statusOrder, double tip) throws IOException {
        this.orderId = orderId;
        this.customerID = customerID;
        this.orderDate = orderDate;
        this.orderItems = orderItems;
        this.orderType = orderType;
        this.orderStatus = statusOrder;
        this.tip = tip;

        orderPrice = 0;
        for (OrderItem orderItem : orderItems) {
            orderPrice += orderItem.getPrice();
        }
    }

    public String getStatus() {
        return orderStatus;
    }

    public void updateStatus(String orderStatus) throws IOException {
        if (this.orderStatus == orderStatus) {
            this.orderStatus = orderStatus;
            OrderManagement.updateOrder(orderId, new Order(orderId, customerID, orderDate, orderItems, orderType, this.orderStatus, tip));
        }
        System.out.println("same status");
    }


    @Override
    public String toString() {
        String itemsString = "";
        for (int i = 0; i < orderItems.size(); i++) {
            itemsString = itemsString.concat(orderItems.get(i).toString());
            if (i < orderItems.size() - 1) {
                itemsString = itemsString.concat("||");
            }
        }

        return orderId + "," + customerID + "," + orderDate.format(DATE_FORMATTER) + "," + orderType + "," + orderStatus + "," + tip + "," + itemsString;
    }

    public static Order fromString(String line) throws IOException {
        String[] parts = line.split(",", 7);
        int orderId = Integer.parseInt(parts[0]);
        int customerId = Integer.parseInt(parts[1]);
        LocalDateTime orderDate = LocalDateTime.parse(parts[2], DATE_FORMATTER);
        int orderType = Integer.parseInt(parts[3]);
        String orderStatus = parts[4];
        double tip = Double.parseDouble(parts[5]);

        List<OrderItem> items = new ArrayList<>();

        String[] itemParts = parts[6].split("\\|\\|");
        for (String item : itemParts) {
            items.add(OrderItem.fromString(item));
        }

        Order order = new Order(orderId, customerId, orderDate, items, orderType, orderStatus, tip);
        return order;
    }

}