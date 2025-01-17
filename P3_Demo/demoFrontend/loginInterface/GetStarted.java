package loginInterface;

import javax.swing.*;
import java.awt.*;
import mainFrame.MainFrame;

public class GetStarted extends JPanel {
    

    // Variables declaration
    private JButton getStartedButton;
    private JLabel logoLabel;
    private JLabel titleLabel;
    private JLabel subtitleLabel;

    public GetStarted() {
        initComponents();
    }

    private void initComponents() {
        setBackground(Color.WHITE);

        // Title Label
        titleLabel = new JLabel("Start Your Delicious Journey Now");
        titleLabel.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 32));
        titleLabel.setForeground(new Color(255, 96, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Logo Label
        String iconPath = "icons/fastfood.png"; // Update the path as needed
        ImageIcon icon = new ImageIcon(iconPath);
        logoLabel = new JLabel(icon);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Subtitle Label
        subtitleLabel = new JLabel("No Line. No Waiting");
        subtitleLabel.setFont(new Font("Georgia", Font.ITALIC, 20));
        subtitleLabel.setForeground(new Color(120, 120, 120));
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Get Started Button
        getStartedButton = new JButton("Get Started");
        getStartedButton.setFont(new Font("Georgia", Font.BOLD, 22));
        getStartedButton.setBackground(new Color(255, 96, 18));
        getStartedButton.setForeground(Color.WHITE);
        getStartedButton.setFocusPainted(false);
        getStartedButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        getStartedButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        getStartedButton.addActionListener(evt -> onGetStartedButtonClicked());

        // Button hover effect
        getStartedButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                getStartedButton.setBackground(new Color(255, 130, 50));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                getStartedButton.setBackground(new Color(255, 96, 18));
            }
        });

        // Layout Configuration
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add Title Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(titleLabel, gbc);

        // Add Logo Label
        gbc.gridy = 1;
        gbc.insets = new Insets(40, 20, 20, 20);
        add(logoLabel, gbc);

        // Add Subtitle Label
        gbc.gridy = 2;
        gbc.insets = new Insets(20, 20, 20, 20);
        add(subtitleLabel, gbc);

        // Add Get Started Button
        gbc.gridy = 3;
        gbc.insets = new Insets(40, 20, 40, 20);
        add(getStartedButton, gbc);
    }

    private void onGetStartedButtonClicked() {
        SwingUtilities.invokeLater(() -> {
           
            MainFrame.setPanel(new SignInPanel());
           // dispose(); // Close the current window to display the sign-in panel
        });
    }

   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
           MainFrame mainFrame = new MainFrame();
           mainFrame.setPanel(new GetStarted());
           mainFrame.setVisible(true);
            
        });
    }
}
