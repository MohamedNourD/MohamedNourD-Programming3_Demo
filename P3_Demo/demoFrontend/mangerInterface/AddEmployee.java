package mangerInterface;

import java.awt.*;

import javax.swing.*;

import Users.UserManagement;
import loginInterface.SignInFrame;

public class AddEmployee extends JFrame {

    public AddEmployee() {
        initComponents();
        addActionListeners(); // Add action listeners for buttons
    }

    private void initComponents() {
        // Main Panel
        jPanel1 = new javax.swing.JPanel();
        jPanel1.setBackground(new Color(255, 255, 255));
        jPanel1.setLayout(new BorderLayout());

        // Left Panel with Image
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(251, 133, 0));
        leftPanel.setPreferredSize(new Dimension(400, 600));
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(new ImageIcon("DemoProject\\icons\\BURGUR.png")); 
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        leftPanel.add(imageLabel);
        jPanel1.add(leftPanel, BorderLayout.WEST);

        // Right Panel (Form)
        jPanel2 = new javax.swing.JPanel();
        jPanel2.setBackground(new Color(255, 255, 255));
        jPanel2.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title Label
        jLabel1 = new javax.swing.JLabel("Add New Employee");
        jLabel1.setFont(new Font("Georgia", Font.BOLD, 24));
        jLabel1.setForeground(new Color(251, 133, 0));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        jPanel2.add(jLabel1, gbc);

        // First Name
        firstName = new javax.swing.JLabel("First Name");
        firstName.setForeground(new Color(153, 153, 153));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        jPanel2.add(firstName, gbc);

        firstNamefield = new javax.swing.JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        jPanel2.add(firstNamefield, gbc);

        // Last Name
        lastNameLabel = new javax.swing.JLabel("Last Name");
        lastNameLabel.setForeground(new Color(153, 153, 153));
        gbc.gridx = 0;
        gbc.gridy = 2;
        jPanel2.add(lastNameLabel, gbc);

        lastNameFileld = new javax.swing.JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        jPanel2.add(lastNameFileld, gbc);

        // Email
        emailLabel = new javax.swing.JLabel("Email");
        emailLabel.setForeground(new Color(153, 153, 153));
        gbc.gridx = 0;
        gbc.gridy = 3;
        jPanel2.add(emailLabel, gbc);

        emailField = new javax.swing.JTextField(20);
        emailField.setPreferredSize(new Dimension(200, 30));
       
        emailField.setBorder(BorderFactory.createCompoundBorder(
                emailField.getBorder(),
                BorderFactory.createEmptyBorder(5, 30, 5, 5)));
       // emailField.add(emailIcon);
        gbc.gridx = 1;
        gbc.gridy = 3;
        jPanel2.add(emailField, gbc);

        // Password
        paswordLabel = new javax.swing.JLabel("Password");
        paswordLabel.setForeground(new Color(153, 153, 153));
        gbc.gridx = 0;
        gbc.gridy = 4;
        jPanel2.add(paswordLabel, gbc);

        PasswordField = new javax.swing.JPasswordField(20);
        PasswordField.setBorder(BorderFactory.createCompoundBorder(
                PasswordField.getBorder(),
                BorderFactory.createEmptyBorder(5, 30, 5, 5)));
        gbc.gridx = 1;
        gbc.gridy = 4;
        jPanel2.add(PasswordField, gbc);

        // Confirm Password
        jLabel6 = new javax.swing.JLabel("Confirm Password");
        jLabel6.setForeground(new Color(153, 153, 153));
        gbc.gridx = 0;
        gbc.gridy = 5;
        jPanel2.add(jLabel6, gbc);

        re_PasswordField = new javax.swing.JPasswordField(20);
        re_PasswordField.setBorder(BorderFactory.createCompoundBorder(
                re_PasswordField.getBorder(),
                BorderFactory.createEmptyBorder(5, 30, 5, 5)));
        gbc.gridx = 1;
        gbc.gridy = 5;
        jPanel2.add(re_PasswordField, gbc);

        // Add Employee Button
        addEmployeeButton = new javax.swing.JButton("Create Account");
        addEmployeeButton.setBackground(new Color(251, 133, 0));
        addEmployeeButton.setForeground(Color.WHITE);
        addEmployeeButton.setFont(new Font("Serif", Font.BOLD, 18));
        addEmployeeButton.setBorder(null);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        jPanel2.add(addEmployeeButton, gbc);
        addEmployeeButton.addActionListener(evt -> onAddEmployeeButtonClick());

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

        // Frame Settings
        setContentPane(jPanel1);
        pack();
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        boolean isDone = UserManagement
                .createCustomerAccount(firstNameText, lastNameText, emailText,
                        passwordText, confirmPasswordText, 2)
                .isDone();
        if (!isDone) {
            JOptionPane.showMessageDialog(
                    this,
                    UserManagement.createCustomerAccount(firstNameText, lastNameText, emailText,
                            passwordText, confirmPasswordText, 2).getMsg(),
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
        new WelcomeManger().setVisible(true);
        this.dispose();
    }

    // Variables declaration - do not modify
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JButton addEmployeeButton;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel firstName;
    private javax.swing.JTextField firstNamefield;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField lastNameFileld;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JLabel paswordLabel;
    private javax.swing.JPasswordField re_PasswordField;
    // End of variables declaration
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AddEmployee().setVisible(true);
        });
    }
}