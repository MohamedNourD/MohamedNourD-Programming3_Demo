package employeeInterface;

import Orders.OrderManagement;
import Orders.Order;
import Orders.OrderItem;

import javax.swing.*;

import Meals.MealsManagment;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class EmployeeOrderUI extends JPanel {

    private List<Order> orders;

    public EmployeeOrderUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));

        loadOrders();

        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
        orderPanel.setBackground(new Color(240, 240, 240));

        for (Order order : orders) {
            orderPanel.add(createOrderCard(order));
        }

        JScrollPane scrollPane = new JScrollPane(orderPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);

        JButton refreshButton = new JButton("Refresh Orders");
        refreshButton.setBackground(new Color(251, 133, 0));
        refreshButton.setForeground(Color.WHITE);
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
            orders = OrderManagement.getOrdersEmployee();
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
        card.setPreferredSize(new Dimension(900, 250)); 

        
        StringBuilder details = new StringBuilder();
        details.append("Order ID: ").append(order.getId()).append("\n");
        details.append("Customer ID: ").append(order.getCustomerID()).append("\n");
        details.append("Order Date: ").append(order.getOrderDate()).append("\n");
        details.append("\nSelected Meals:\n");
        for (OrderItem item : order.getOrderItems()) {
            try {
                details.append(MealsManagment.getMealById(item.getMealId()).getName()).append(" x ").append(item.getQuantity()).append(" = $")
                        .append(String.format("%.2f", item.getPrice())).append("\n");
            } catch (IOException e) {
              
                e.printStackTrace();
            }
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

        // Status dropdown
        JComboBox<String> statusComboBox = new JComboBox<>(
                new String[] { "Preparing", "Ready", "Done", "Deleted" });
        statusComboBox.setSelectedItem(order.getStatus());
        statusComboBox.addActionListener(e -> {
            String newStatus = (String) statusComboBox.getSelectedItem();
            updateOrderStatusUI(order, newStatus); // Update the order status
            statusLabel.setText("Status: " + newStatus);
        });
        statusPanel.add(statusComboBox);

        card.add(statusPanel, BorderLayout.SOUTH);

        return card;
    }

    private void updateOrderStatusUI(Order order, String newStatus) {
        try {
        //   Order order2 = new Order (order.getOrderId(), order.getCustomerID(), order.getOrderDate(), order.getOrderItems(), order.getOrderType(), newStatus, order.getTip());
        //     OrderManagement.updateOrderStatus(order2); 
        order.updateStatus(newStatus);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error updating order status: " + e.getMessage());
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