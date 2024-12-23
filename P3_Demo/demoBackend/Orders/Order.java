package Orders;

import java.util.*;

public class Order {
    private int orderId;
    private String customerName;
    private List<OrderItem> orderItems;

    public Order(int orderId, String customerName, List<OrderItem> orderItems) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.orderItems = orderItems;
    }

    public int getId() {
        return orderId;
    }
    public String toString() {
        String itemsString = "";
        for (int i = 0; i < orderItems.size(); i++) {
            itemsString = itemsString.concat(orderItems.get(i).toString());
            if (i < orderItems.size() - 1) {
                itemsString = itemsString.concat("||");
            }
        }
        return orderId + "," + customerName + "," + itemsString;
    }

    public static Order fromString(String line) {
        String[] parts = line.split(",", 3);
        List<OrderItem> items = new ArrayList<>();
        String[] itemParts = parts[2].split("\\|\\|");
        for (String item : itemParts) {
            items.add(OrderItem.fromString(item));
        }

        return new Order(Integer.parseInt(parts[0]), parts[1], items);
    }
}
