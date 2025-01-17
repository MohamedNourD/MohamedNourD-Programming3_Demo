package employee_Interface;

import Orders.OrderManagement;
import Orders.Order;
import Orders.OrderItem;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class EmployeeOrderUI extends JPanel {

    // List to store orders
    private List<Order> orders;

    public EmployeeOrderUI() {
        // Panel setup
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240)); // Light gray background

        // Load orders from the file
        loadOrders();

        // Panel to display orders
        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
        orderPanel.setBackground(new Color(240, 240, 240));

        // Add each order to the panel
        for (Order order : orders) {
            orderPanel.add(createOrderCard(order));
        }

        JScrollPane scrollPane = new JScrollPane(orderPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);

        // Button to refresh orders
        JButton refreshButton = new JButton("Refresh Orders");
        refreshButton.setBackground(new Color(251, 133, 0)); // Orange background
        refreshButton.setForeground(Color.WHITE); // White text
        refreshButton.setFont(new Font("Arial", Font.BOLD, 16));
        refreshButton.setFocusPainted(false);
        refreshButton.addActionListener(e -> {
            loadOrders();
            orderPanel.removeAll();
            for (Order order : orders) {
                orderPanel.add(createOrderCard(order));
            }
            orderPanel.revalidate();
            orderPanel.repaint();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 240, 240));
        buttonPanel.add(refreshButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadOrders() {
        try {
            // Load orders using the OrderManagment class
            orders = OrderManagement.getOrders();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading orders: " + e.getMessage());
        }
    }

    private JPanel createOrderCard(Order order) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        card.setPreferredSize(new Dimension(900, 150));

        // Order details
        StringBuilder details = new StringBuilder();
        details.append("Order ID: ").append(order.getId()).append("\n");
        details.append("Customer ID: ").append(order.getCustomerID()).append("\n");
        details.append("Order Date: ").append(order.getOrderDate()).append("\n");
        details.append("\nSelected Meals:\n");
        for (OrderItem item : order.getOrderItems()) {
            details.append(item.getMealName()).append(" x ").append(item.getQuantity()).append(" = $")
                    .append(String.format("%.2f", item.getPrice())).append("\n");
        }
        details.append("\nTotal: $").append(String.format("%.2f", order.getOrderPrice())).append("\n");

        JTextArea detailsArea = new JTextArea(details.toString());
        detailsArea.setEditable(false);
        detailsArea.setLineWrap(true);
        detailsArea.setWrapStyleWord(true);
        detailsArea.setBackground(Color.WHITE);
        detailsArea.setFont(new Font("Arial", Font.PLAIN, 14));
        card.add(detailsArea, BorderLayout.CENTER);

        // Status panel
        JPanel statusPanel = new JPanel();
        statusPanel.setBackground(Color.WHITE);
        statusPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JLabel statusLabel = new JLabel("Status: " + order.getStatus());
        statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        statusPanel.add(statusLabel);

        // Combo box to change status
        JComboBox<String> statusComboBox = new JComboBox<>(
                new String[] { "Preparing", "Cooking", "Ready for Delivery", "Delivered" });
        statusComboBox.setSelectedItem(order.getStatus());
        statusComboBox.addActionListener(e -> {
            order.setStatus((String) statusComboBox.getSelectedItem());
            statusLabel.setText("Status: " + order.getStatus());
            saveOrderStatus(order); // Save the updated status
        });
        statusPanel.add(statusComboBox);

        card.add(statusPanel, BorderLayout.SOUTH);

        return card;
    }

    private void saveOrderStatus(Order order) {
        try {
            // Save the updated status back to the file
            OrderManagement.updateOrderStatus(order);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving order status: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Employee Order Management");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 800);
            frame.add(new EmployeeOrderUI());
            frame.setVisible(true);
        });
    }
}