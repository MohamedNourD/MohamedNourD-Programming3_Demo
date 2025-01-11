package mangerInterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.nio.file.Paths;
import javax.swing.*;

public class WelcomeManger extends javax.swing.JFrame {

        // Components
        private JPanel mainPanel;
        private JButton addEmployeeButton;
        private JButton controlButton;
        private JButton statisticsInformationButton;
        private JLabel welcomeMessageLabel;
        private JPanel iconPanel;
        private JLabel iconLabel;

        public WelcomeManger() {
                initComponents();
        }

        private void initComponents() {
                // Frame settings
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setTitle("Pet-Friendly Manager Interface");
                setPreferredSize(new Dimension(800, 600));
                setBackground(Color.WHITE);

                // Main panel
                mainPanel = new JPanel();
                mainPanel.setBackground(Color.WHITE);
                mainPanel.setLayout(new BorderLayout(10, 10)); // Use BorderLayout for better organization

                // Welcome message
                welcomeMessageLabel = new JLabel("Welcome, Manager");
                welcomeMessageLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 48)); // Playful font
                welcomeMessageLabel.setForeground(new Color(251, 133, 0)); // Warm color
                welcomeMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                mainPanel.add(welcomeMessageLabel, BorderLayout.NORTH);

                // Icon panel
                iconPanel = new JPanel();
                iconPanel.setBackground(Color.WHITE);
                iconPanel.setLayout(new BorderLayout());

                // Load pet-themed icon
                String iconPath ="DemoProject\\icons\\fastfood.png"; // Replace with your pet icon path
                ImageIcon icon = new ImageIcon(iconPath);
                iconLabel = new JLabel(icon);
                iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
                iconLabel.setVerticalAlignment(SwingConstants.CENTER);
                iconPanel.add(iconLabel, BorderLayout.CENTER);

                mainPanel.add(iconPanel, BorderLayout.CENTER);

                // Button panel (small buttons at the bottom)
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
                mainPanel.add(buttonPanel, BorderLayout.SOUTH);

                // Add main panel to the frame
                getContentPane().add(mainPanel, BorderLayout.CENTER);

                // Auto-size the frame
                pack();
                setLocationRelativeTo(null); // Center the frame on the screen
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
                new AddEmployee().setVisible(true);
                this.dispose();
        }

        private void controlButtonAction(ActionEvent evt) {
                new MealsManagmentInterface().setVisible(true);
                this.dispose();
        }

        private void statisticsInformationAction(ActionEvent evt) {
                new RestaurantManagementInterface().setVisible(true);
                this.dispose();
        }

        public static void main(String[] args) {
                SwingUtilities.invokeLater(() -> new WelcomeManger().setVisible(true));
        }
}