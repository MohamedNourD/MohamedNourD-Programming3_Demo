package mangerInterface;

import java.awt.*;
import Execptions.Status;

import javax.swing.*;
import Users.UsersManagement;
import loginInterface.SignInPanel;
import mainFrame.MainFrame;

public class AddEmployee extends JPanel {

    // Components
    private JPasswordField PasswordField;
    private JButton addEmployeeButton;
    private JTextField emailField;
    private JLabel emailLabel;
    private JLabel firstName;
    private JTextField firstNamefield;
    private JLabel jLabel1;
    private JLabel jLabel6;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JTextField lastNameFileld;
    private JLabel lastNameLabel;
    private JLabel paswordLabel;
    private JPasswordField re_PasswordField;

    public AddEmployee() {
        initComponents();
        addActionListeners(); // Add action listeners for buttons
    }

    private void initComponents() {
        // Main Panel
        jPanel1 = new JPanel();
        jPanel1.setBackground(new Color(255, 255, 255));
        jPanel1.setLayout(new BorderLayout());

        // Left Panel with Image
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(251, 133, 0));
        leftPanel.setPreferredSize(new Dimension(400, 600));
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(new ImageIcon("icons\\BURGUR.png")); 
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        leftPanel.add(imageLabel);
        jPanel1.add(leftPanel, BorderLayout.WEST);

        // Right Panel (Form)
        jPanel2 = new JPanel();
        jPanel2.setBackground(new Color(255, 255, 255));
        jPanel2.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title Label
        jLabel1 = new JLabel("Add New Employee");
        jLabel1.setFont(new Font("Georgia", Font.BOLD, 24));
        jLabel1.setForeground(new Color(251, 133, 0));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        jPanel2.add(jLabel1, gbc);

        // First Name
        firstName = new JLabel("First Name");
        firstName.setForeground(new Color(153, 153, 153));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        jPanel2.add(firstName, gbc);

        firstNamefield = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        jPanel2.add(firstNamefield, gbc);

        // Last Name
        lastNameLabel = new JLabel("Last Name");
        lastNameLabel.setForeground(new Color(153, 153, 153));
        gbc.gridx = 0;
        gbc.gridy = 2;
        jPanel2.add(lastNameLabel, gbc);

        lastNameFileld = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        jPanel2.add(lastNameFileld, gbc);

        // Email
        emailLabel = new JLabel("Email");
        emailLabel.setForeground(new Color(153, 153, 153));
        gbc.gridx = 0;
        gbc.gridy = 3;
        jPanel2.add(emailLabel, gbc);

        emailField = new JTextField(20);
        emailField.setPreferredSize(new Dimension(200, 30));
        emailField.setBorder(BorderFactory.createCompoundBorder(
                emailField.getBorder(),
                BorderFactory.createEmptyBorder(5, 30, 5, 5)));
        gbc.gridx = 1;
        gbc.gridy = 3;
        jPanel2.add(emailField, gbc);

        // Password
        paswordLabel = new JLabel("Password");
        paswordLabel.setForeground(new Color(153, 153, 153));
        gbc.gridx = 0;
        gbc.gridy = 4;
        jPanel2.add(paswordLabel, gbc);

        PasswordField = new JPasswordField(20);
        PasswordField.setBorder(BorderFactory.createCompoundBorder(
                PasswordField.getBorder(),
                BorderFactory.createEmptyBorder(5, 30, 5, 5)));
        gbc.gridx = 1;
        gbc.gridy = 4;
        jPanel2.add(PasswordField, gbc);

        // Confirm Password
        jLabel6 = new JLabel("Confirm Password");
        jLabel6.setForeground(new Color(153, 153, 153));
        gbc.gridx = 0;
        gbc.gridy = 5;
        jPanel2.add(jLabel6, gbc);

        re_PasswordField = new JPasswordField(20);
        re_PasswordField.setBorder(BorderFactory.createCompoundBorder(
                re_PasswordField.getBorder(),
                BorderFactory.createEmptyBorder(5, 30, 5, 5)));
        gbc.gridx = 1;
        gbc.gridy = 5;
        jPanel2.add(re_PasswordField, gbc);

        // Add Employee Button
        addEmployeeButton = new JButton("Create Account");
        addEmployeeButton.setBackground(new Color(251, 133, 0));
        addEmployeeButton.setForeground(Color.WHITE);
        addEmployeeButton.setFont(new Font("Serif", Font.BOLD, 18));
        addEmployeeButton.setBorder(null);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        jPanel2.add(addEmployeeButton, gbc);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(251, 133, 0));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Serif", Font.BOLD, 18));
        backButton.setBorder(null);
        backButton.addActionListener(evt -> onBackButtonClick());
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        jPanel2.add(backButton, gbc);

        jPanel1.add(jPanel2, BorderLayout.CENTER);

        // Add the main panel to this panel
        setLayout(new BorderLayout());
        add(jPanel1, BorderLayout.CENTER);
    }

    private void addActionListeners() {
        addEmployeeButton.addActionListener(evt -> onAddEmployeeButtonClick());
    }

    private void onAddEmployeeButtonClick() {
        String firstNameText = firstNamefield.getText();
        String lastNameText = lastNameFileld.getText();
        String emailText = emailField.getText();
        String passwordText = new String(PasswordField.getPassword());
        String confirmPasswordText = new String(re_PasswordField.getPassword());
        Status  status  = UsersManagement
                .createEmployeeAccount(firstNameText, lastNameText, emailText,
                        passwordText, confirmPasswordText)
                ;
        if (!status.isDone()) {
            JOptionPane.showMessageDialog(
                    this,
                  status.getMsg(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Employee added successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void onBackButtonClick() {
       
        SwingUtilities.invokeLater(() -> {

            MainFrame.setPanel(new WelcomeMangerPanel());

        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Add Employee");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.add(new AddEmployee());
            frame.setVisible(true);
        });
    }
}