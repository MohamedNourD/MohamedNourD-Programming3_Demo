package loginInterface;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.nio.file.Paths;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Users.UsersManagement;
import mainFrame.MainFrame;
import Execptions.Status;

public class SignUp extends javax.swing.JPanel {

        // Components
        private javax.swing.JButton jButton1;
        private javax.swing.JCheckBox jCheckBox1;
        private javax.swing.JLabel welcomeMassage;
        private javax.swing.JLabel firstNameLabel;
        private javax.swing.JLabel lastNameLabel;
        private javax.swing.JLabel emailLabel;
        private javax.swing.JLabel passwordLabel;
        private javax.swing.JLabel password2Label;
        private javax.swing.JLabel prettyMassage;
        private javax.swing.JLabel bigIcon;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JPanel jPanel4;
        private javax.swing.JPanel jPanel5;
        private javax.swing.JPasswordField jPasswordField1;
        private javax.swing.JPasswordField jPasswordField2;
        private javax.swing.JTextField firstNameText;
        private javax.swing.JTextField lastNameText;
        private javax.swing.JTextField emailText;

        public SignUp() {
                initComponents();
        }

        private void initComponents() {
                jPanel1 = new JPanel();
                jPanel2 = new JPanel();
                welcomeMassage = new javax.swing.JLabel();
                jPanel3 = new JPanel();
                firstNameLabel = new javax.swing.JLabel();
                lastNameLabel = new javax.swing.JLabel();
                emailLabel = new javax.swing.JLabel();
                passwordLabel = new javax.swing.JLabel();
                password2Label = new javax.swing.JLabel();
                firstNameText = new javax.swing.JTextField();
                lastNameText = new javax.swing.JTextField();
                emailText = new javax.swing.JTextField();
                jPasswordField1 = new javax.swing.JPasswordField();
                jPasswordField2 = new javax.swing.JPasswordField();
                jButton1 = new javax.swing.JButton();
                jCheckBox1 = new javax.swing.JCheckBox();
                jPanel4 = new JPanel();
                prettyMassage = new javax.swing.JLabel();
                jPanel5 = new JPanel();
                bigIcon = new javax.swing.JLabel();

                // Main panel setup
                jPanel1.setBackground(new Color(255, 255, 255));
                jPanel1.setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();

                // Welcome message panel
                jPanel2.setBackground(new Color(255, 255, 255));
                welcomeMassage.setFont(new java.awt.Font("Goudy Old Style", 3, 36));
                welcomeMassage.setForeground(new Color(255, 96, 18));
                welcomeMassage.setText("Welcome to Orange Restaurant");

                jPanel2.setLayout(new GridBagLayout());
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.insets = new Insets(20, 10, 20, 10);
                jPanel2.add(welcomeMassage, gbc);

                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.gridwidth = 2;
                jPanel1.add(jPanel2, gbc);
                // Input fields panel
                jPanel3.setBackground(new Color(255, 255, 255));
                jPanel3.setLayout(new GridBagLayout());

                gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 10, 5, 10); // Padding for all components
                gbc.fill = GridBagConstraints.HORIZONTAL; // Allow text fields to expand horizontally

                // First Name
                firstNameLabel.setForeground(new Color(102, 102, 102));
                firstNameLabel.setText("First Name:");
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.anchor = GridBagConstraints.LINE_END; // Align label to the right
                jPanel3.add(firstNameLabel, gbc);

                firstNameText.setForeground(new Color(102, 102, 102));
                firstNameText.setColumns(20);
                gbc.gridx = 1;
                gbc.anchor = GridBagConstraints.LINE_START; // Align field to the left
                jPanel3.add(firstNameText, gbc);

                // Last Name
                lastNameLabel.setForeground(new Color(102, 102, 102));
                lastNameLabel.setText("Last Name:");
                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.anchor = GridBagConstraints.LINE_END;
                jPanel3.add(lastNameLabel, gbc);

                lastNameText.setForeground(new Color(102, 102, 102));
                lastNameText.setColumns(20);
                gbc.gridx = 1;
                gbc.anchor = GridBagConstraints.LINE_START;
                jPanel3.add(lastNameText, gbc);

                // Email
                emailLabel.setForeground(new Color(102, 102, 102));
                emailLabel.setText("Email:");
                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.anchor = GridBagConstraints.LINE_END;
                jPanel3.add(emailLabel, gbc);

                emailText.setForeground(new Color(102, 102, 102));
                emailText.setColumns(20);
                gbc.gridx = 1;
                gbc.anchor = GridBagConstraints.LINE_START;
                jPanel3.add(emailText, gbc);

                // Password
                passwordLabel.setForeground(new Color(102, 102, 102));
                passwordLabel.setText("Password:");
                gbc.gridx = 0;
                gbc.gridy = 3;
                gbc.anchor = GridBagConstraints.LINE_END;
                jPanel3.add(passwordLabel, gbc);

                jPasswordField1.setForeground(new Color(102, 102, 102));
                jPasswordField1.setColumns(20);
                gbc.gridx = 1;
                gbc.anchor = GridBagConstraints.LINE_START;
                jPanel3.add(jPasswordField1, gbc);

                // Rewrite Password
                password2Label.setForeground(new Color(102, 102, 102));
                password2Label.setText("Rewrite Password:");
                gbc.gridx = 0;
                gbc.gridy = 4;
                gbc.anchor = GridBagConstraints.LINE_END;
                jPanel3.add(password2Label, gbc);

                jPasswordField2.setForeground(new Color(102, 102, 102));
                jPasswordField2.setColumns(20);
                gbc.gridx = 1;
                gbc.anchor = GridBagConstraints.LINE_START;
                jPanel3.add(jPasswordField2, gbc);

                // Show Password Checkbox
                jCheckBox1.setForeground(new Color(102, 102, 102));
                jCheckBox1.setText("Show Password");
                gbc.gridx = 1;
                gbc.gridy = 5;
                gbc.anchor = GridBagConstraints.LINE_START;
                jPanel3.add(jCheckBox1, gbc);
                jCheckBox1.addActionListener(evt -> jCheckBox1ActionPerformed(evt));

                // Create Account Button
                jButton1.setBackground(new Color(255, 96, 18));
                jButton1.setFont(new java.awt.Font("Dubai", 3, 18));
                jButton1.setForeground(Color.WHITE);
                jButton1.setText("Create New Account");
                jButton1.addActionListener(evt -> jButton1ActionPerformed());
                gbc.gridx = 0;
                gbc.gridy = 6;
                gbc.gridwidth = 2; // Center the button by spanning two columns
                gbc.insets = new Insets(20, 10, 10, 10);
                jPanel3.add(jButton1, gbc);

                // Add the jPanel3 to jPanel1
                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.gridwidth = 1;
                jPanel1.add(jPanel3, gbc);

                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.gridwidth = 1;
                jPanel1.add(jPanel3, gbc);

                // Restaurant logo panel
                jPanel5.setBackground(new Color(255, 255, 255));
                String iconPath = Paths.get("icons", "fastfood.png").toString();
                ImageIcon icon = new ImageIcon(iconPath);
                bigIcon.setIcon(icon);

                jPanel5.setLayout(new GridBagLayout());
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.insets = new Insets(20, 20, 20, 20);
                jPanel5.add(bigIcon, gbc);

                gbc.gridx = 1;
                gbc.gridy = 1;
                jPanel1.add(jPanel5, gbc);

                // Pretty message panel
                jPanel4.setBackground(new Color(255, 255, 255));
                prettyMassage.setFont(new java.awt.Font("Sitka Text", 3, 18));
                prettyMassage.setForeground(new Color(255, 96, 18));
                prettyMassage.setText("Embark on a Culinary Adventure");

                jPanel4.setLayout(new GridBagLayout());
                gbc.gridx = 0;
                gbc.gridy = 0;
                jPanel4.add(prettyMassage, gbc);

                gbc.gridx = 1;
                gbc.gridy = 2;
                jPanel1.add(jPanel4, gbc);

                // Final panel layout
                setLayout(new java.awt.BorderLayout());
                add(jPanel1, java.awt.BorderLayout.CENTER);
        }

        private void jButton1ActionPerformed() {
                String firstName = firstNameText.getText();
                String lastName = lastNameText.getText();
                String email = emailText.getText();
                String password1 = new String(jPasswordField1.getPassword());
                String password2 = new String(jPasswordField2.getPassword());

                
                UsersManagement usersManagement = new UsersManagement();
                Status status = usersManagement.createCustomerAccount(firstName, lastName, email, password1, password2);

                if (usersManagement.createCustomerAccount(firstName, lastName, email, password1, password2).isDone()== true ) {
                        JOptionPane.showMessageDialog(this, "Registration successful!", "Success",
                                        JOptionPane.INFORMATION_MESSAGE);
                        ((javax.swing.JFrame) getTopLevelAncestor()).dispose();
                        SwingUtilities.invokeLater(() -> {

                                MainFrame.setPanel(new SignInPanel());

                        });

                        
                } else {
                        JOptionPane.showMessageDialog(this, status.getMsg(), "Error", JOptionPane.ERROR_MESSAGE);
                }
        }

        private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {
                if (jCheckBox1.isSelected()) {
                        jPasswordField1.setEchoChar((char) 0); // Show password
                } else {
                        jPasswordField1.setEchoChar('*'); // Hide password
                }
        }

        public static void main(String[] args) {
                javax.swing.SwingUtilities.invokeLater(() -> {
                        JFrame frame = new JFrame("Sign Up");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setSize(800, 600);
                        frame.add(new SignUp());
                        frame.setVisible(true);
                });
        }
}