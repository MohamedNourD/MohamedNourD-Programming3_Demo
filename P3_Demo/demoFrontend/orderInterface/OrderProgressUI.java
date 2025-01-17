package orderInterface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class OrderProgressUI extends JPanel {

    private JTable statusTable;
    private DefaultTableModel tableModel;
    private JLabel messageLabel;

    public OrderProgressUI() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Create the table model with columns: Status and Time
        String[] columns = {"Status", "Time"};
        tableModel = new DefaultTableModel(columns, 0);
        statusTable = new JTable(tableModel);
        statusTable.setFont(new Font("Arial", Font.PLAIN, 14));
        statusTable.setRowHeight(25);
        statusTable.setEnabled(false); // Make the table non-editable
        statusTable.setBackground(Color.WHITE); // Ensure table background is white

        // Add the table to a scroll pane with a border
        JScrollPane scrollPane = new JScrollPane(statusTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        scrollPane.getViewport().setBackground(Color.WHITE); // Ensure scroll pane's viewport is white
        scrollPane.setPreferredSize(new Dimension(480, 250)); // Adjusted size for scroll pane

        // Styled message label to guide the customer
        messageLabel = new JLabel("Your order is being prepared. Please wait...", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        messageLabel.setForeground(Color.WHITE); // White text
        messageLabel.setOpaque(true);
        messageLabel.setBackground(new Color(251, 133, 0)); // Orange background
        messageLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Add components to the main panel
        add(scrollPane, BorderLayout.CENTER);
        add(messageLabel, BorderLayout.SOUTH);

        // Simulate employee updating the order status
        simulateOrderProgress();
    }

    private void simulateOrderProgress() {
        new Thread(() -> {
            try {
                updateStatus("Preparing", "Just started");
                Thread.sleep(3000); // Preparing
                updateStatus("Cooking", "In progress");
                Thread.sleep(3000); // Cooking
                updateStatus("Ready for Delivery", "Almost done");
                Thread.sleep(3000); // Ready for Delivery
                updateStatus("Delivered", "Completed");
                messageLabel.setText("Your order has been delivered. Thank you for waiting!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void updateStatus(String status, String time) {
        SwingUtilities.invokeLater(() -> {
            // Add a new row to the table with the status and time
            tableModel.addRow(new Object[]{status, time});

            // Scroll to the last row to ensure the latest status is visible
            statusTable.scrollRectToVisible(statusTable.getCellRect(tableModel.getRowCount() - 1, 0, true));
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Order Progress");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 400);
            frame.add(new OrderProgressUI());
            frame.setVisible(true);
        });
    }
}