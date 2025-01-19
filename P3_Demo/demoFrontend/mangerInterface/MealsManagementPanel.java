package mangerInterface;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Meals.Meal;
import Meals.MealsManagment;
import loginInterface.GetStarted;
import mainFrame.MainFrame;

public class MealsManagementPanel extends JPanel {

    private JPanel mealsPanel;
    private JScrollPane scrollPane;
    private JButton addMealButton;
    private JButton saveButton;
    private final Color fontColor = new Color(102, 102, 102);
    private final Color textFieldColor = new Color(51, 51, 153); // New font color for text fields and text areas

    public MealsManagementPanel() {
        initComponents();
        loadMeals(); // Load and display existing meals
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(251, 133, 0));

        mealsPanel = new JPanel();
        mealsPanel.setBackground(new Color(251, 133, 8));
        mealsPanel.setLayout(new WrapLayout(FlowLayout.LEFT, 10, 10)); // Use WrapLayout

        // Set a preferred size for the mealsPanel to ensure it has a fixed width
        mealsPanel.setPreferredSize(new Dimension(800, 600)); // Adjust as needed

        scrollPane = new JScrollPane(mealsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane, BorderLayout.CENTER);

        // Create a bottom panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(251, 133, 0));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Center-align buttons with a gap

        addMealButton = new JButton("Add New Meal");
        addMealButton.setBackground(new Color(251, 133, 0));
        addMealButton.setForeground(Color.WHITE);
        addMealButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        addMealButton.addActionListener(evt -> onAddMealButtonClicked());
        buttonPanel.add(addMealButton);

        saveButton = new JButton("Save");
        saveButton.setBackground(new Color(251, 133, 0));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        saveButton.addActionListener(evt -> saveMealsToFile());
        buttonPanel.add(saveButton);

        // Add Back Button
        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(251, 133, 0));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        backButton.addActionListener(evt -> onBackButtonClicked());
        buttonPanel.add(backButton);

        this.add(buttonPanel, BorderLayout.SOUTH); // Add button panel at the bottom
    }

    private void onAddMealButtonClicked() {
        JPanel newMealPanel = createMealItemPanel(null);
        mealsPanel.add(newMealPanel);
        mealsPanel.revalidate();
        mealsPanel.repaint();
        scrollPane.revalidate(); // Force the scroll pane to update its view
        scrollPane.repaint();
    }

    private JPanel createMealItemPanel(Meal meal) {
        JPanel mealItemPanel = new JPanel();
        mealItemPanel.setBackground(Color.WHITE);
        mealItemPanel.setPreferredSize(new Dimension(300, 300)); // Ensure this is consistent
        mealItemPanel.setLayout(null);
        JLabel mealIconLabel = new JLabel("Icon Meal", SwingConstants.CENTER);
        mealIconLabel.setBounds(10, 10, 280, 100);
        mealIconLabel.setForeground(fontColor);
        mealItemPanel.add(mealIconLabel); // For precise positioning

        if (meal != null) {
            try {
                ImageIcon icon = new ImageIcon(meal.getIconPath());
                mealIconLabel.setIcon(icon);
                mealIconLabel.setBounds(10, 10, 280, 100);
                mealIconLabel.setText("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Add a mouse listener to the icon label to open a file chooser
        mealIconLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Select Meal Icon");
                int result = fileChooser.showOpenDialog(mealItemPanel);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String iconPath = fileChooser.getSelectedFile().getAbsolutePath();
                    mealIconLabel.setIcon(new ImageIcon(iconPath));
                    mealIconLabel.setToolTipText(iconPath); // Store the icon path in the tooltip
                    mealIconLabel.setText("");
                }
            }
        });

        JLabel mealNameLabel = new JLabel("Meal Name:");
        mealNameLabel.setBounds(10, 120, 80, 20);
        mealNameLabel.setForeground(fontColor);
        mealItemPanel.add(mealNameLabel);

        JTextField mealNameField = new JTextField(meal != null ? meal.getName() : "");
        mealNameField.setBounds(100, 120, 180, 20);
        mealNameField.setForeground(textFieldColor); // Set new font color
        mealNameField.setEnabled(false);
        mealItemPanel.add(mealNameField);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(10, 150, 80, 20);
        priceLabel.setForeground(fontColor);
        mealItemPanel.add(priceLabel);

        JTextField priceField = new JTextField(meal != null ? String.valueOf(meal.getPrice()) : "");
        priceField.setBounds(100, 150, 180, 20);
        priceField.setForeground(textFieldColor); // Set new font color
        priceField.setEnabled(false);
        mealItemPanel.add(priceField);

        JLabel ingredientsLabel = new JLabel("Ingredients:");
        ingredientsLabel.setBounds(10, 180, 80, 20);
        ingredientsLabel.setForeground(fontColor);
        mealItemPanel.add(ingredientsLabel);

        JTextArea ingredientsArea = new JTextArea(meal != null ? meal.getIngredients() : "");
        ingredientsArea.setBounds(100, 180, 180, 60);
        ingredientsArea.setForeground(textFieldColor); // Set new font color
        ingredientsArea.setEnabled(false);
        mealItemPanel.add(ingredientsArea);

        JButton editButton = new JButton("Edit");
        editButton.setBounds(100, 250, 80, 30);
        editButton.setBackground(new Color(251, 133, 0));
        editButton.setForeground(Color.WHITE);
        editButton.addActionListener(evt -> onEditButtonClicked(mealNameField, priceField, ingredientsArea));
        mealItemPanel.add(editButton);

        JButton removeButton = new JButton("Remove");
        removeButton.setBounds(190, 250, 80, 30);
        removeButton.setBackground(new Color(251, 133, 0));
        removeButton.setForeground(Color.WHITE);
        removeButton.addActionListener(evt -> onRemoveMealButtonClicked(mealItemPanel, meal));
        mealItemPanel.add(removeButton);

        return mealItemPanel;
    }

    private void onEditButtonClicked(JTextField mealNameField, JTextField priceField, JTextArea ingredientsArea) {
        mealNameField.setEnabled(true);
        priceField.setEnabled(true);
        ingredientsArea.setEnabled(true);
    }

    private void onRemoveMealButtonClicked(JPanel mealPanel, Meal meal) {
        if (meal != null) {
            try {
                MealsManagment.deleteMeal(meal.getId());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Failed to remove meal: " + e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        mealsPanel.remove(mealPanel);
        mealsPanel.revalidate();
        mealsPanel.repaint();
        scrollPane.revalidate(); // Force the scroll pane to update its view
        scrollPane.repaint();
    }

    private void saveMealsToFile() {
        List<Meal> meals = new ArrayList<>();
        boolean allFieldsFilled = true;
    
        // Collect data from the UI
        for (Component component : mealsPanel.getComponents()) {
            if (component instanceof JPanel) {
                JPanel mealPanel = (JPanel) component;
                String mealName = "";
                double price = 0.0;
                String ingredients = "";
                String iconPath ="";
    
                for (Component innerComponent : mealPanel.getComponents()) {
                    if (innerComponent instanceof JTextField) {
                        JTextField textField = (JTextField) innerComponent;
                        if (textField.getBounds().y == 120) { // Meal Name
                            mealName = textField.getText();
                        } else if (textField.getBounds().y == 150) { // Price
                            price = Double.parseDouble(textField.getText());
                        }
                    } else if (innerComponent instanceof JTextArea) {
                        JTextArea textArea = (JTextArea) innerComponent;
                        ingredients = textArea.getText();
                    } else if (innerComponent instanceof JLabel) {
                        JLabel label = (JLabel) innerComponent;
                        if (label.getToolTipText() != null) {
                            iconPath = label.getToolTipText(); // Get the icon path from the tooltip
                        }
                    }
                }
    
                // Validate data
                if (mealName.isEmpty() || price <= 0 || ingredients.isEmpty() ) {
                    allFieldsFilled = false;
                    break;
                }
    
                // Add meal to the list
                meals.add(new Meal(0, mealName, ingredients, price, iconPath));
            }
        }
    
        // Check if all fields are filled
        if (!allFieldsFilled) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields for each meal.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        // Save meals to file
        try {
            for (Meal meal : meals) {
                MealsManagment.createMeal(meal.getName(), meal.getIngredients(), meal.getPrice(), meal.getIconPath());
            }
            JOptionPane.showMessageDialog(this, "Meals saved successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to save meals: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
     
    

    private void loadMeals() {
        try {
            List<Meal> meals = MealsManagment.getMeals(); // Fetch the list of meals
            System.out.println("Loading meals. Number of meals: " + meals.size()); // Debugging

            for (Meal meal : meals) {
                JPanel mealPanel = createMealItemPanel(meal);
                mealsPanel.add(mealPanel);
            }

            // Debugging: Print the mealsPanel size and layout
            System.out.println("mealsPanel width: " + mealsPanel.getWidth()); // Debugging
            System.out.println("mealsPanel layout: " + mealsPanel.getLayout()); // Debugging

            // Force layout update
            mealsPanel.doLayout();

            mealsPanel.revalidate();
            mealsPanel.repaint();
            scrollPane.revalidate(); // Force the scroll pane to update its view
            scrollPane.repaint();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to load meals: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onBackButtonClicked() {
        SwingUtilities.invokeLater(() -> {

            MainFrame.setPanel(new WelcomeMangerPanel());

        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Meals Management Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        MealsManagementPanel mealsPanel = new MealsManagementPanel();
        frame.add(mealsPanel);
        frame.setVisible(true);
    }
}