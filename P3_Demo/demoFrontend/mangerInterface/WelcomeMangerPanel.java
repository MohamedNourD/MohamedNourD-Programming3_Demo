package mangerInterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

import mainFrame.MainFrame;

public class WelcomeMangerPanel extends JPanel {

    // Components
    private JLabel welcomeMessageLabel;
    private JPanel iconPanel;
    private JLabel iconLabel;
    private JButton addEmployeeButton;
    private JButton controlButton;
    private JButton statisticsInformationButton;

    public WelcomeMangerPanel() {
        initComponents();
    }

    private void initComponents() {
        // Set panel layout and background
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout for better organization
        setBackground(Color.WHITE);

        // Welcome message
        welcomeMessageLabel = new JLabel("Welcome, Manager");
        welcomeMessageLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 48)); // Playful font
        welcomeMessageLabel.setForeground(new Color(251, 133, 0)); // Warm color
        welcomeMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeMessageLabel, BorderLayout.NORTH);

        // Icon panel
        iconPanel = new JPanel();
        iconPanel.setBackground(Color.WHITE);
        iconPanel.setLayout(new BorderLayout());

        // Load icon
        String iconPath = "Project-Programming3_Demo\\icons\\BURGUR.png"; // Replace with your icon path
        ImageIcon icon = new ImageIcon(iconPath);
        iconLabel = new JLabel(icon);
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel.setVerticalAlignment(SwingConstants.CENTER);
        iconPanel.add(iconLabel, BorderLayout.CENTER);

        add(iconPanel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new GridLayout(1, 3, 20, 10)); // Use GridLayout for buttons

        // Add Employee Button
        addEmployeeButton = createButton("Add Employee");
        addEmployeeButton.addActionListener(this::addEmployeeAction);
        buttonPanel.add(addEmployeeButton);

        // Control Menu Button
        controlButton = createButton("Control Menu");
        controlButton.addActionListener(this::controlButtonAction);
        buttonPanel.add(controlButton);

        // Show Statistics Button
        statisticsInformationButton = createButton("Show Statistics");
        statisticsInformationButton.addActionListener(this::statisticsInformationAction);
        buttonPanel.add(statisticsInformationButton);

        // Add button panel to the bottom
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Helper method to create styled buttons
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(251, 133, 0)); // Warm color
        button.setFont(new Font("Comic Sans MS", Font.BOLD, 14)); // Smaller font for buttons
        button.setForeground(Color.WHITE); // White text
        button.setBorder(BorderFactory.createRaisedBevelBorder()); // Raised border
        button.setPreferredSize(new Dimension(120, 40)); // Smaller button size
        return button;
    }

    // Action for Add Employee button
    private void addEmployeeAction(ActionEvent evt) {

        SwingUtilities.invokeLater(() -> {
            MainFrame.setPanel(new AddEmployee());

        });
        
    }

    private void controlButtonAction(ActionEvent evt) {
     
        SwingUtilities.invokeLater(() -> {

            MainFrame.setPanel(new MealsManagementPanel());

        });
      
    }

    private void statisticsInformationAction(ActionEvent evt) {
        SwingUtilities.invokeLater(() -> {

            MainFrame.setPanel(new RestaurantManagementPanel());

        });
    }

   
    public static void main(String[] args) {
        JFrame testFrame = new JFrame("Welcome Manager Test");
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testFrame.setSize(800, 600);
        testFrame.add(new WelcomeMangerPanel());
        testFrame.setLocationRelativeTo(null); // Center the frame
        testFrame.setVisible(true);
    }
}
