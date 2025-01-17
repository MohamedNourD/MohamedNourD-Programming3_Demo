package mainFrame;

import javax.swing.*;

import employee_Interface.EmployeeOrderUI;
import loginInterface.GetStarted;
import loginInterface.SignInPanel;
import loginInterface.SignUp;
import mangerInterface.MealsManagementPanel;
import mangerInterface.RestaurantManagementPanel;
import orderInterface.OrderManagementUI;

import java.awt.*;


public class MainFrame extends JFrame {

    private static JPanel leftPanel;
    private static JPanel rightPanel;

    public MainFrame() {
        // Remove the default title bar
        setUndecorated(true);

        // Make the frame fullscreen and auto-resizable
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Add a border with the specified color to replace the title bar
        getRootPane().setBorder(BorderFactory.createLineBorder(new Color(0xFB8500), 1));

        // Create the main container panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Create the custom title bar
        JPanel titleBar = createTitleBar();
        mainPanel.add(titleBar, BorderLayout.NORTH);

        // Create the left panel for the fixed image
        leftPanel = new JPanel(new GridBagLayout()); // Center-align content
        leftPanel.setPreferredSize(new Dimension(250, getHeight())); // Fixed width for the left panel
        leftPanel.setBackground(Color.WHITE); // White background

        // Load and resize the fixed image
        ImageIcon icon = new ImageIcon("icons/fast-foodlogo.png");
        Image scaledIcon = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        JLabel iconLabel = new JLabel(new ImageIcon(scaledIcon));

        // Add the fixed image to the left panel
        leftPanel.add(iconLabel, new GridBagConstraints());

        // Create the right panel for dynamic content
        rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(new BorderLayout());

        // Add panels to the main container
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);

        // Add the main container to the frame
        add(mainPanel, BorderLayout.CENTER);
    }

   
    private JPanel createTitleBar() {
        JPanel titleBar = new JPanel(new BorderLayout());
        titleBar.setBackground(Color.WHITE);
        titleBar.setPreferredSize(new Dimension(getWidth(), 30));

        // Add a custom title label
        JLabel titleLabel = new JLabel();
        titleLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        titleLabel.setForeground(new Color(0xFB8500)); // Orange text
        titleBar.add(titleLabel, BorderLayout.CENTER);

       
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        buttonPanel.setBackground(Color.WHITE);

        // Minimize button
        JButton minimizeButton = createTitleBarButton("icons\\minus.png");
        minimizeButton.addActionListener(e -> setState(JFrame.ICONIFIED)); // Minimize the window
        buttonPanel.add(minimizeButton);
        // Close button
        JButton closeButton = createTitleBarButton("icons\\cancel.png");
        closeButton.addActionListener(e -> System.exit(0)); // Close the application
        buttonPanel.add(closeButton);

        titleBar.add(buttonPanel, BorderLayout.EAST);

        return titleBar;
    }

    
    private JButton createTitleBarButton(String iconPath) {
        JButton button = new JButton();
        button.setBackground(Color.WHITE); // White background
        button.setFocusPainted(false); // Remove focus border
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Add padding

        // Load and resize the icon
        ImageIcon icon = new ImageIcon(iconPath);
        Image scaledIcon = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledIcon));

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0xFB9000)); 
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.WHITE); // White background on exit
            }
        });

        return button;
    }

   
    public static void setPanel(JPanel panel) {
        rightPanel.removeAll();
        rightPanel.add(panel, BorderLayout.CENTER);
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setPanel( new SignUp());
            mainFrame.setVisible(true);

        });
    }
}