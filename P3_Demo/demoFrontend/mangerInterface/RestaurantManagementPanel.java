package mangerInterface;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

import Meals.Meal;
import Meals.MealsManagment;
import Orders.OrderManagement;
import Users.UsersManagement;
import mainFrame.MainFrame;

public class RestaurantManagementPanel extends JPanel {

    private JPanel reportPanel;
    private JButton dailyOrdersButton;
    private JButton mostRequestedMealButton;
    private JButton dailyRevenuesButton;
    private JButton regularCustomerButton;
    private JButton backButton;

 
   

    public RestaurantManagementPanel() {
        initComponents();
       // populateSampleData(); // Populate sample data for demonstration
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));

        // Header panel with welcome message
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(251, 133, 0));
        headerPanel.setPreferredSize(new Dimension(800, 80));
        JLabel welcomeLabel = new JLabel("Welcome to Restaurant Management System", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.WHITE);
        headerPanel.add(welcomeLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Report panel
        reportPanel = new JPanel();
        reportPanel.setLayout(new BorderLayout());
        reportPanel.setBackground(Color.WHITE);
        reportPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add a label above the report area
        JLabel reportLabel = new JLabel("Report Details:", SwingConstants.LEFT);
        reportLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        reportLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        reportPanel.add(reportLabel, BorderLayout.NORTH);

        add(reportPanel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 5, 10, 10));
        buttonPanel.setBackground(new Color(200, 200, 200));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Style buttons
        Color buttonColor = new Color(251, 133, 0);
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);

        dailyOrdersButton = createButton("Daily Orders", buttonColor, buttonFont, e -> showDailyOrders());
        mostRequestedMealButton = createButton("Most Requested Meal", buttonColor, buttonFont,
                e -> showMostRequestedMeal());
        dailyRevenuesButton = createButton("Daily Revenues", buttonColor, buttonFont, e -> showDailyRevenues());
        regularCustomerButton = createButton("Regular Customer", buttonColor, buttonFont, e -> {
            try {
                showRegularCustomer();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        backButton = createButton("Back", buttonColor, buttonFont, e -> onBackButtonClicked());

        buttonPanel.add(dailyOrdersButton);
        buttonPanel.add(mostRequestedMealButton);
        buttonPanel.add(dailyRevenuesButton);
        buttonPanel.add(regularCustomerButton);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Helper method to create styled buttons
    private JButton createButton(String text, Color color, Font font, java.awt.event.ActionListener listener) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(font);
        button.addActionListener(listener);
        return button;
    }

    // Methods to handle report generation
    private void showDailyOrders() {
        reportPanel.removeAll(); // Clear previous content
         Map<String, Integer> dailyOrders = null;
        try {
            dailyOrders = OrderManagement.countOrdersForToday();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // Date -> Number of
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Daily Orders:");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        contentPanel.add(titleLabel);

        for (Map.Entry<String, Integer> entry : dailyOrders.entrySet()) {
            JLabel orderLabel = new JLabel(entry.getKey() + ": " + entry.getValue() + " orders");
            orderLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            contentPanel.add(orderLabel);
        }
        reportPanel.add(new JScrollPane(contentPanel), BorderLayout.CENTER);
        reportPanel.revalidate();
        reportPanel.repaint();
    }

    private void showMostRequestedMeal() {
        reportPanel.removeAll(); // Clear previous content
        Meal meal = null ;
        try {
            meal = MealsManagment.mostOrderedMeal();
        } catch (IOException e) {

            e.printStackTrace();
        }

        JPanel mealPanel = createMealItemPanel();
        for (Component component : mealPanel.getComponents()) {
            if (component instanceof JTextField textField) {
                if (textField.getBounds().y == 120) { 
                    textField.setText(meal.getName());
                } else if (textField.getBounds().y == 150) { 
                    textField.setText(new String(meal.getPrice() + " $")); 
                }
            } else if (component instanceof JTextArea textArea) { 
                textArea.setText( meal.getIngredients());
            }else if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
               label.setIcon(new ImageIcon(meal.getIconPath()));
            }
        }

        JLabel titleLabel = new JLabel("Most Requested Meal:");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        reportPanel.add(titleLabel, BorderLayout.NORTH);
        reportPanel.add(mealPanel, BorderLayout.CENTER);
        reportPanel.revalidate();
        reportPanel.repaint();
    }

    private void showDailyRevenues() {
        reportPanel.removeAll(); // Clear previous content
          Map<String, Double> dailyRevenues = null;
        try {
            dailyRevenues = OrderManagement.dailyRevenues();
        } catch (IOException e) {
            
            e.printStackTrace();
        } 

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Daily Revenues:");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        contentPanel.add(titleLabel);

        for (Map.Entry<String, Double> entry : dailyRevenues.entrySet()) {
            JLabel revenueLabel = new JLabel(entry.getKey() + ": $" + String.format("%.2f", entry.getValue()));
            revenueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            contentPanel.add(revenueLabel);
        }

        reportPanel.add(new JScrollPane(contentPanel), BorderLayout.CENTER);
        reportPanel.revalidate();
        reportPanel.repaint();
    }

    private void showRegularCustomer() throws IOException {
        reportPanel.removeAll(); // Clear previous content

       

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Regular Customer:");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        contentPanel.add(titleLabel);

        JLabel customerLabel = new JLabel(UsersManagement.loyalCustomer());
        customerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        contentPanel.add(customerLabel);

        reportPanel.add(new JScrollPane(contentPanel), BorderLayout.CENTER);
        reportPanel.revalidate();
        reportPanel.repaint();
    }

    // Back button action
    private void onBackButtonClicked() {
        SwingUtilities.invokeLater(() -> {
            MainFrame.setPanel(new WelcomeMangerPanel());
        });
        reportPanel.removeAll(); // Clear the report panel
        reportPanel.revalidate();
        reportPanel.repaint();
    }

    // Create a meal item panel
    private JPanel createMealItemPanel() {
        JPanel mealItemPanel = new JPanel();
        mealItemPanel.setBackground(Color.WHITE);
        mealItemPanel.setPreferredSize(new Dimension(300, 300));
        mealItemPanel.setLayout(null);

        JLabel mealIconLabel = new JLabel("Icon Meal", SwingConstants.CENTER);
        mealIconLabel.setBounds(10, 10, 280, 100);
        mealIconLabel.setForeground(new Color(102, 102, 102));
        mealItemPanel.add(mealIconLabel);

        JLabel mealNameLabel = new JLabel("Meal Name:");
        mealNameLabel.setBounds(10, 120, 80, 20);
        mealNameLabel.setForeground(new Color(102, 102, 102));
        mealItemPanel.add(mealNameLabel);

        JTextField mealNameField = new JTextField();
        mealNameField.setBounds(100, 120, 180, 20);
        mealNameField.setForeground(new Color(102, 102, 102));
        mealNameField.setEnabled(false);
        mealItemPanel.add(mealNameField);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(10, 150, 80, 20);
        priceLabel.setForeground(new Color(102, 102, 102));
        mealItemPanel.add(priceLabel);

        JTextField priceField = new JTextField();
        priceField.setBounds(100, 150, 180, 20);
        priceField.setForeground(new Color(102, 102, 102));
        priceField.setEnabled(false);
        mealItemPanel.add(priceField);

        JLabel ingredientsLabel = new JLabel("Ingredients:");
        ingredientsLabel.setBounds(10, 180, 80, 20);
        ingredientsLabel.setForeground(new Color(102, 102, 102));
        mealItemPanel.add(ingredientsLabel);

        JTextArea ingredientsArea = new JTextArea();
        ingredientsArea.setBounds(100, 180, 180, 60);
        ingredientsArea.setForeground(new Color(102, 102, 102));
        ingredientsArea.setEnabled(false);
        mealItemPanel.add(ingredientsArea);

        return mealItemPanel;
    }

    // // Populate sample data for demonstration
    // private void populateSampleData() {
    //     dailyOrders.put("2023-10-01", 50);
    //     dailyOrders.put("2023-10-02", 60);
    //     dailyOrders.put("2023-10-03", 45);

    //     mealRequests.put("Burger", 120);
    //     mealRequests.put("Pizza", 90);
    //     mealRequests.put("Pasta", 75);

    //     dailyRevenues.put("2023-10-01", 1500.0);
    //     dailyRevenues.put("2023-10-02", 1800.0);
    //     dailyRevenues.put("2023-10-03", 1350.0);

    //     customerVisits.put("John Doe", 10);
    //     customerVisits.put("Jane Smith", 15);
    //     customerVisits.put("Alice Johnson", 8);
    // }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Restaurant Management");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 800);
            frame.add(new RestaurantManagementPanel());
            frame.setVisible(true);
        });
    }
}