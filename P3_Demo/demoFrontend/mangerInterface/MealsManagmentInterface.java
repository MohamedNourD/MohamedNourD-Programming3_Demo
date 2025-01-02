package mangerInterface;

import javax.swing.*;

import Meals.Meal;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public class MealsManagmentInterface extends JFrame {

    private JPanel mainPanel;
    private JPanel mealsPanel;
    private JScrollPane scrollPane;
    private JButton addMealButton;
    private int panelWidth = 300;
    private int panelHeight = 300;

    public MealsManagmentInterface() {
        initComponents();
    }

    private void initComponents() {
        mainPanel = new JPanel();
        mealsPanel = new JPanel();
        addMealButton = new JButton("Add New Meal");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1010, 640));
        mainPanel.setBackground(new Color(251, 133, 0));
        mainPanel.setPreferredSize(new Dimension(1000, 600));
        mainPanel.setLayout(null);

        mealsPanel.setBackground(new Color(251, 133, 8));
        mealsPanel.setLayout(null);

        scrollPane = new JScrollPane(mealsPanel);
        scrollPane.setBounds(15, 15, 950, 500);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        mainPanel.add(scrollPane);

        addMealButton.setBackground(new Color(251, 133, 0));
        addMealButton.setForeground(Color.WHITE);
        addMealButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        addMealButton.setBounds(400, 530, 200, 50);
        addMealButton.addActionListener(evt -> onAddMealButtonClicked());
        mainPanel.add(addMealButton);

        add(mainPanel);
        pack();
    }

    private JPanel createMealItemPanel(int x, int y) {
        JPanel mealItemPanel = new JPanel();
        mealItemPanel.setBackground(Color.WHITE);
        mealItemPanel.setBounds(x, y, panelWidth, panelHeight);
        mealItemPanel.setLayout(null);

        JLabel mealIconLabel = new JLabel("Icon Meal", SwingConstants.CENTER);
        mealIconLabel.setBounds(10, 10, 280, 100);
        mealItemPanel.add(mealIconLabel);

        JLabel mealNameLabel = new JLabel("Meal Name:");
        mealNameLabel.setBounds(10, 120, 80, 20);
        mealItemPanel.add(mealNameLabel);

        JTextField mealNameField = new JTextField();
        mealNameField.setBounds(100, 120, 180, 20);
        mealNameField.setEnabled(false); // Initially disabled
        mealItemPanel.add(mealNameField);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(10, 150, 80, 20);
        mealItemPanel.add(priceLabel);

        JTextField priceField = new JTextField();
        priceField.setBounds(100, 150, 180, 20);
        priceField.setEnabled(false); // Initially disabled
        mealItemPanel.add(priceField);

        JLabel ingredientsLabel = new JLabel("Ingredients:");
        ingredientsLabel.setBounds(10, 180, 80, 20);
        mealItemPanel.add(ingredientsLabel);

        JTextArea ingredientsArea = new JTextArea();
        ingredientsArea.setBounds(100, 180, 180, 60);
        ingredientsArea.setEnabled(false); // Initially disabled
        mealItemPanel.add(ingredientsArea);

        JButton editButton = new JButton("Edit");
        editButton.setBounds(100, 250, 80, 30);
        editButton.setBackground(new Color(251, 133, 0));
        editButton.setForeground(Color.WHITE);
        editButton.addActionListener(evt -> onEditButtonClicked(mealNameField, priceField, ingredientsArea));
        mealItemPanel.add(editButton);

        // Add the Remove button
        JButton removeButton = new JButton("Remove");
        removeButton.setBounds(190, 250, 80, 30);
        removeButton.setBackground(new Color(251, 133, 0));
        removeButton.setForeground(Color.WHITE);
        removeButton.addActionListener(evt -> onRemoveMealButtonClicked(mealItemPanel));
        mealItemPanel.add(removeButton);

        // Make mealIconLabel clickable to open file chooser
        mealIconLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onMealIconClicked(mealIconLabel);
            }
        });

        return mealItemPanel;
    }

    private void onEditButtonClicked(JTextField mealNameField, JTextField priceField, JTextArea ingredientsArea) {
        // Enable fields for editing
        mealNameField.setEnabled(true);
        priceField.setEnabled(true);
        ingredientsArea.setEnabled(true);
    }

    private void onAddMealButtonClicked() {
        int count = mealsPanel.getComponentCount();
        int x = (count % 3) * (panelWidth + 10); // Three panels per row
        int y = (count / 3) * (panelHeight + 10);
        JPanel newMealPanel = createMealItemPanel(x, y);
        mealsPanel.add(newMealPanel);

        // Update the preferred size to ensure scrolling
        int rows = (count / 3) + 1;
        mealsPanel.setPreferredSize(new Dimension(950, rows * (panelHeight + 10)));
        mealsPanel.revalidate();
        mealsPanel.repaint();
    }

    private void onRemoveMealButtonClicked(JPanel mealPanel) {
        mealsPanel.remove(mealPanel);
        mealsPanel.revalidate();
        mealsPanel.repaint();

        // Update the preferred size after removal
        int rows = (mealsPanel.getComponentCount() / 3) + 1;
        mealsPanel.setPreferredSize(new Dimension(950, rows * (panelHeight + 10)));
    }

    private void onMealIconClicked(JLabel mealIconLabel) {
        // Create a file chooser
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Meal Icon");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            // Get the selected file
            String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();

            // Set the selected file as the icon
            ImageIcon icon = new ImageIcon(selectedFilePath);
            mealIconLabel.setIcon(icon);
            mealIconLabel.setText(""); // Remove the "Icon Meal" text
        }
    }
    public void addMealsToPanel(List<Meal> meals) {
    int x = 0;
    int y = 0;
    for (Meal meal : meals) {
        JPanel mealPanel = createMealItemPanel(x, y);
        mealPanel.setName(meal.getName());  // Store the meal name as a reference (optional)

        // Set the meal name, price, and ingredients in the corresponding fields
        for (Component component : mealPanel.getComponents()) {
            if (component instanceof JTextField) {
                JTextField textField = (JTextField) component;
                if (textField.getBounds().y == 120) {
                    textField.setText(meal.getName());
                } else if (textField.getBounds().y == 150) {
                    textField.setText(String.valueOf(meal.getPrice()));
                }
            } else if (component instanceof JTextArea) {
                JTextArea textArea = (JTextArea) component;
                textArea.setText(meal.getIngredients());
            }
        }

        // Set the icon for the meal
        for (Component component : mealPanel.getComponents()) {
            if (component instanceof JLabel && ((JLabel) component).getText().equals("Icon Meal")) {
                JLabel mealIconLabel = (JLabel) component;
               // ImageIcon icon = new ImageIcon(meal.getIconPath());
              //  mealIconLabel.setIcon(icon);
                mealIconLabel.setText("");  // Remove default text
            }
        }

        mealsPanel.add(mealPanel);

        // Update the preferred size after adding new panel
        int count = mealsPanel.getComponentCount();
        int rows = (count / 3) + 1;
        mealsPanel.setPreferredSize(new Dimension(950, rows * (panelHeight + 10)));
    }

    mealsPanel.revalidate();
    mealsPanel.repaint();
}
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MealsManagmentInterface().setVisible(true));
    }
}