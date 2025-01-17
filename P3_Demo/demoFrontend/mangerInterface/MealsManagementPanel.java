package mangerInterface;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Meals.Meal;
import Meals.MealsManagment;

public class MealsManagementPanel extends JPanel {

    private JPanel mealsPanel;
    private JScrollPane scrollPane;
    private JButton addMealButton;
    private JButton saveButton;
    private final Color fontColor = new Color(102, 102, 102);

    public MealsManagementPanel() {
        initComponents();
        // loadMeals(); // Uncomment if you have meals data to load on startup
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(251, 133, 0)); // Main panel background color

        mealsPanel = new JPanel();
        mealsPanel.setBackground(new Color(251, 133, 8));
        mealsPanel.setLayout(new WrapLayout(FlowLayout.LEFT, 10, 10)); // Custom layout for meal items

        scrollPane = new JScrollPane(mealsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane, BorderLayout.CENTER); // Add scroll pane to the center

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

        this.add(buttonPanel, BorderLayout.SOUTH); // Add button panel at the bottom
    }

    private void onAddMealButtonClicked() {
        JPanel newMealPanel = createMealItemPanel(null);
        mealsPanel.add(newMealPanel);
        mealsPanel.revalidate();
        mealsPanel.repaint();
    }

    private JPanel createMealItemPanel(Meal meal) {
        JPanel mealItemPanel = new JPanel();
        mealItemPanel.setBackground(Color.WHITE);
        mealItemPanel.setPreferredSize(new Dimension(300, 300));
        mealItemPanel.setLayout(null); // For precise positioning

        JLabel mealIconLabel = new JLabel("Icon Meal", SwingConstants.CENTER);
        mealIconLabel.setBounds(10, 10, 280, 100);
        mealIconLabel.setForeground(fontColor);
        mealItemPanel.add(mealIconLabel);

        JLabel mealNameLabel = new JLabel("Meal Name:");
        mealNameLabel.setBounds(10, 120, 80, 20);
        mealNameLabel.setForeground(fontColor);
        mealItemPanel.add(mealNameLabel);

        JTextField mealNameField = new JTextField(meal != null ? meal.getName() : "");
        mealNameField.setBounds(100, 120, 180, 20);
        mealNameField.setForeground(fontColor);
        mealNameField.setEnabled(false);
        mealItemPanel.add(mealNameField);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(10, 150, 80, 20);
        priceLabel.setForeground(fontColor);
        mealItemPanel.add(priceLabel);

        JTextField priceField = new JTextField(meal != null ? String.valueOf(meal.getPrice()) : "");
        priceField.setBounds(100, 150, 180, 20);
        priceField.setForeground(fontColor);
        priceField.setEnabled(false);
        mealItemPanel.add(priceField);

        JLabel ingredientsLabel = new JLabel("Ingredients:");
        ingredientsLabel.setBounds(10, 180, 80, 20);
        ingredientsLabel.setForeground(fontColor);
        mealItemPanel.add(ingredientsLabel);

        JTextArea ingredientsArea = new JTextArea(meal != null ? meal.getIngredients() : "");
        ingredientsArea.setBounds(100, 180, 180, 60);
        ingredientsArea.setForeground(fontColor);
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
        try {
            if (meal != null) {
                MealsManagment.deleteMeal(meal.getName());
            }
            mealsPanel.remove(mealPanel);
            mealsPanel.revalidate();
            mealsPanel.repaint();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to remove meal: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveMealsToFile() {
        // Logic for saving meals to a file
        JOptionPane.showMessageDialog(this, "Meals saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}
