package Orders;

import Users.Customer;

import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Order {
    private int orderId;
    private String customerName;
    private LocalDateTime orderDate;
    private List<OrderItem> orderItems;
    private double orderPrice;
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    private int orderType;
    private String statusOrder;
    private String details;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Order(int orderId, Customer customer, List<OrderItem> orderItems) {
        this.orderId = orderId;
        this.customerName = customer.getName();
        this.orderDate = LocalDateTime.now();
        this.orderItems = orderItems;
        customer.newOrder(orderId);
    }

    public Order(int orderId, String customerName, List<OrderItem> orderItems) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.orderDate = LocalDateTime.now();
        this.orderItems = orderItems;
    }

    public Order() {
        this.orderDate = LocalDateTime.now();
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

    @Override
    public String toString() {
        String itemsString = "";
        for (int i = 0; i < orderItems.size(); i++) {
            itemsString = itemsString.concat(orderItems.get(i).toString());
            if (i < orderItems.size() - 1) {
                itemsString = itemsString.concat("||");
            }
        }

        return orderId + "," + customerName + "," + orderDate.format(DATE_FORMATTER) + "," + orderPrice + ","
                + itemsString;
    }

    public static Order fromString(String line) {
        String[] parts = line.split(",", 4);
        int orderId = Integer.parseInt(parts[0]);
        String customerName = parts[1];
        LocalDateTime orderDate = LocalDateTime.parse(parts[2], DATE_FORMATTER); // Parse the orderDate
        List<OrderItem> items = new ArrayList<>();

        String[] itemParts = parts[3].split("\\|\\|");
        for (String item : itemParts) {
            items.add(OrderItem.fromString(item));
        }

        Order order = new Order(orderId, customerName, items);
        order.setOrderDate(orderDate);
        return order;
    }

    public Order(String details) {
        this.details = details;
        this.statusOrder = "Preparing"; // Default status
    }

    public String getDetails() {
        return details;
    }

    public String getStatus() {
        return statusOrder;
    }

    public void setStatus(String statusOrder) {
        this.statusOrder = statusOrder;
    }
}