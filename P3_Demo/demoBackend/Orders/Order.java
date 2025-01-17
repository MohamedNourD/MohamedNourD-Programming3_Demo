package Orders;

import Users.Customer;
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
    private String statusOrder;

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

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    public int getId() {
        return orderId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }



    public Order(int orderId, Customer customer, List<OrderItem> orderItems, int orderType, double tip) {
        this.orderId = orderId;
        this.customerID = customer.getId();
        this.orderDate = LocalDateTime.now();
        this.orderItems = orderItems;
        this.orderType = orderType;
        this.statusOrder = "pending";
        this.tip = tip;

        orderPrice = 0;
        for (OrderItem orderItem : orderItems) {
            orderPrice += orderItem.getPrice();
        }
        customer.newOrder(orderId);
    }

    public Order(int orderId, int customerID, List<OrderItem> orderItems, int orderType, double tip) throws IOException {
        this.orderId = orderId;
        this.customerID = customerID;
        this.orderDate = LocalDateTime.now();
        this.orderItems = orderItems;
        this.orderType = orderType;
        this.statusOrder = "pending";
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
        this.statusOrder = statusOrder;
        this.tip = tip;

        orderPrice = 0;
        for (OrderItem orderItem : orderItems) {
            orderPrice += orderItem.getPrice();
        }
    }

    public String getStatus() {
        return statusOrder;
    }

    public void setStatus(String statusOrder) {
        this.statusOrder = statusOrder;
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

        return orderId + "," + customerID + "," + orderDate.format(DATE_FORMATTER) + "," + orderType + "," + statusOrder + "," + tip + "," + itemsString;
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