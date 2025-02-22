package orderInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import Orders.OrderItem;
import Orders.OrderManagement;
import loginInterface.SignInPanel;
import Meals.*;

public class OrderManagementUI extends JPanel {

    private List<Meal> meals = null;

    private final Map<Meal, Integer> selectedMeals = new HashMap<>();

    private String orderType = "";

    // Path to the order file
    private static final String ORDER_FILE_PATH = "Files\\orders.txt";

    public OrderManagementUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240)); // Light gray background

        // Welcome panel
        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(Color.WHITE);
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JLabel welcomeLabel = new JLabel("Welcome to Our Restaurant! 🍔🍕🍝", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 28));
        welcomeLabel.setForeground(new Color(251, 133, 0)); // Orange text
        welcomePanel.add(welcomeLabel);

        add(welcomePanel, BorderLayout.NORTH);

        // Scrollable meal panel
        JPanel mealPanel = new JPanel();
        mealPanel.setLayout(new BoxLayout(mealPanel, BoxLayout.Y_AXIS));
        mealPanel.setBackground(new Color(240, 240, 240));
        try {
            meals = MealsManagment.getMeals();
        } catch (IOException e) {

            e.printStackTrace();
        }
        for (Meal meal : meals) {
            mealPanel.add(createMealCard(meal));
        }

        JScrollPane scrollPane = new JScrollPane(mealPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);

        // Order type selection panel
        JPanel orderTypePanel = new JPanel();
        orderTypePanel.setBackground(new Color(240, 240, 240));
        orderTypePanel.setBorder(BorderFactory.createTitledBorder("Order Type (Mandatory)"));
        orderTypePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JRadioButton dineIn = new JRadioButton("Dine-In");
        JRadioButton special = new JRadioButton("Special");
        JRadioButton delivery = new JRadioButton("Delivery");

        ButtonGroup orderTypeGroup = new ButtonGroup();
        orderTypeGroup.add(dineIn);
        orderTypeGroup.add(special);
        orderTypeGroup.add(delivery);

        dineIn.addActionListener(e -> orderType = "Dine-In");
        special.addActionListener(e -> orderType = "Special");
        delivery.addActionListener(e -> orderType = "Delivery");

        orderTypePanel.add(dineIn);
        orderTypePanel.add(special);
        orderTypePanel.add(delivery);

        // Place order button panel
        JPanel placeOrderPanel = new JPanel();
        placeOrderPanel.setBackground(new Color(240, 240, 240));
        placeOrderPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JButton placeOrderButton = createStyledButton("Place Order");
        placeOrderButton.addActionListener(e -> {
            if (orderType.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select an order type!");
                return;
            }
            if (selectedMeals.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No meals selected!");
                return;
            }
            showBillDialog();
        });

        // Button to show previous orders
        JButton showPreviousOrdersButton = new JButton("Show Previous Orders");
        showPreviousOrdersButton.setBackground(new Color(251, 133, 0));
        showPreviousOrdersButton.setForeground(Color.WHITE);
        showPreviousOrdersButton.setFont(new Font("Arial", Font.BOLD, 12));
        showPreviousOrdersButton.setFocusPainted(false);
        showPreviousOrdersButton.addActionListener(e -> showPreviousOrders());

        placeOrderPanel.add(placeOrderButton);
        placeOrderPanel.add(showPreviousOrdersButton);

        // Add components to the bottom panel
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(240, 240, 240));
        bottomPanel.add(orderTypePanel, BorderLayout.CENTER);
        bottomPanel.add(placeOrderPanel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(251, 133, 0));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.ITALIC, 15));
        button.setFocusPainted(false);
        return button;
    }

    private JPanel createMealCard(Meal meal) {
        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        card.setPreferredSize(new Dimension(900, 150));

        // Icon
        ImageIcon icon = new ImageIcon(meal.getIconPath());
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setBounds(20, 10, 130, 130);
        card.add(iconLabel);

        // Name
        JLabel nameLabel = new JLabel(meal.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setBounds(170, 10, 200, 20);
        card.add(nameLabel);

        // Price
        JLabel priceLabel = new JLabel("$" + String.format("%.2f", meal.getPrice()));
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        priceLabel.setBounds(170, 40, 100, 20);
        card.add(priceLabel);

        // Ingredients
        JTextArea ingredientsArea = new JTextArea(meal.getIngredients());
        ingredientsArea.setEditable(false);
        ingredientsArea.setLineWrap(true);
        ingredientsArea.setWrapStyleWord(true);
        ingredientsArea.setBounds(170, 70, 400, 60);
        card.add(ingredientsArea);

        // Spinner (default to 0)
        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        quantitySpinner.setBounds(600, 30, 50, 30);
        card.add(quantitySpinner);

        // Add Button
        JButton addButton = createStyledButton("Add to Order");
        addButton.setBounds(600, 70, 120, 30);
        addButton.addActionListener(e -> {
            int quantity = (int) quantitySpinner.getValue();
            if (quantity > 0) {
                selectedMeals.put(meal, quantity);
            } else {
                selectedMeals.remove(meal);
            }
            JOptionPane.showMessageDialog(this, quantity + " " + meal.getName() + " added to order!");
        });
        card.add(addButton);

        return card;
    }

    private void showBillDialog() {
        JDialog billDialog = new JDialog();
        billDialog.setTitle("Bill Summary");
        billDialog.setSize(400, 300);
        billDialog.setLocationRelativeTo(this);
        billDialog.getContentPane().setBackground(Color.WHITE);

        JPanel billPanel = new JPanel();
        billPanel.setLayout(new BoxLayout(billPanel, BoxLayout.Y_AXIS));
        billPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        billPanel.setBackground(Color.WHITE);

        // Selected meals
        JLabel selectedMealsLabel = new JLabel("Selected Meals:");
        billPanel.add(selectedMealsLabel);

        for (Map.Entry<Meal, Integer> entry : selectedMeals.entrySet()) {
            Meal meal = entry.getKey();
            int quantity = entry.getValue();
            JLabel mealLabel = new JLabel(
                    meal.getName() + " x " + quantity + " = $" + String.format("%.2f", meal.getPrice() * quantity));
            billPanel.add(mealLabel);
        }

        // Subtotal
        double subtotal = calculateSubtotal();
        JLabel subtotalLabel = new JLabel("Subtotal: $" + String.format("%.2f", subtotal));
        billPanel.add(subtotalLabel);

        // Tip
        JLabel tipLabel = new JLabel("Tip:");
        JTextField tipField = new JTextField(10);
        tipField.setMaximumSize(new Dimension(200, 30));
        JPanel tipPanel = new JPanel();
        tipPanel.setBackground(Color.WHITE);
        tipPanel.add(tipLabel);
        tipPanel.add(tipField);
        billPanel.add(tipPanel);

        // Total
        JLabel totalLabel = new JLabel("Total: $" + String.format("%.2f", subtotal));
        billPanel.add(totalLabel);

        // Confirm button
        JButton confirmButton = createStyledButton("Confirm Bill");
        confirmButton.addActionListener(e -> {
            try {
                double tip = Double.parseDouble(tipField.getText());
                double total = subtotal + tip;
                totalLabel.setText("Total: $" + String.format("%.2f", total));
                JOptionPane.showMessageDialog(billDialog, "Bill confirmed! Total: $" + String.format("%.2f", total));
                billDialog.dispose();
                saveOrderToFile(subtotal, tip, total,SignInPanel.customerID,orderType); 
                selectedMeals.clear();
                showOrderProgressUI();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(billDialog, "Please enter a valid tip amount!");
            }
        });

        billDialog.add(billPanel, BorderLayout.CENTER);
        billDialog.add(confirmButton, BorderLayout.SOUTH);
        billDialog.setVisible(true);
    }

    public void saveOrderToFile(double subtotal, double tip, double total, int customerId, String orderType) {
        try {
            List<OrderItem> orderItems = new ArrayList<>();
            for (Map.Entry<Meal, Integer> entry : selectedMeals.entrySet()) {
                Meal meal = entry.getKey();
                int quantity = entry.getValue();
                orderItems.add(new OrderItem(meal, quantity));
            }
    
            int orderTypeId = getOrderTypeId(orderType); // Use a helper method for order type mapping
    
            OrderManagement.createOrder(customerId, orderItems, orderTypeId, tip); 
    
            JOptionPane.showMessageDialog(this, "Order details saved to " + ORDER_FILE_PATH);
    
        } catch (Exception e) {
            // Log the exception for debugging
            System.err.println("Error saving order details: " + e.getMessage()); 
            // Display a user-friendly error message
            JOptionPane.showMessageDialog(this, "An error occurred while saving the order. Please try again later."); 
        }
    }
    
    private int getOrderTypeId(String orderType) {
        if (orderType.equals("Dine-In")) {
            return 1;
        } else if (orderType.equals("Special")) {
            return 2;
        } else {
            return 3; 
        }
    }

    private void showPreviousOrders() {
        try {
            // Read the contents of the order file
            String ordersContent = OrderManagement.getAllOrders(SignInPanel.customerID);

            if (ordersContent.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No previous orders found.");
                return;
            }

            
            JTextArea textArea = new JTextArea(ordersContent);
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(600, 400));

            JOptionPane.showMessageDialog(this, scrollPane, "Previous Orders", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading order file: " + e.getMessage());
        }
    }

    private void showOrderProgressUI() {
        OrderProgressUI orderProgressUI = new OrderProgressUI();
        orderProgressUI.setVisible(true);
    }

    private double calculateSubtotal() {
        double subtotal = 0;
        for (Map.Entry<Meal, Integer> entry : selectedMeals.entrySet()) {
            subtotal += entry.getKey().getPrice() * entry.getValue();
        }
        return subtotal;
    }

    public List<OrderItem> getOrderItems() {
        List<OrderItem> orderItems = new ArrayList<>();

        for (Map.Entry<Meal, Integer> entry : selectedMeals.entrySet()) {
            Meal meal = entry.getKey();
            int quantity = entry.getValue();
            double price = meal.getPrice();

            OrderItem orderItem = new OrderItem(meal, quantity);
            orderItems.add(orderItem);
        }

        return orderItems;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Order Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 800);
            frame.add(new OrderManagementUI());
            frame.setVisible(true);
        });
    }
}