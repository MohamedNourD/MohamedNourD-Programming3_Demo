package loginInterface;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Users.UsersManagement;
import employee_Interface.EmployeeOrderUI;
import mainFrame.MainFrame;
import Execptions.Status;
import mangerInterface.WelcomeMangerPanel;
import orderInterface.OrderManagementUI;

public class SignInPanel extends javax.swing.JPanel {

    // Components
    private javax.swing.JButton signin;
    private javax.swing.JButton register;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JCheckBox jCheckBox1;

    public SignInPanel() {
        initComponents();
    }

    private void initComponents() {
        jPanel1 = new JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        signin = new javax.swing.JButton();
        register = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();

        // Main panel setup
        jPanel1.setBackground(new Color(255, 255, 255));
        jPanel1.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Title label
        jLabel1.setFont(new java.awt.Font("Georgia", 1, 24));
        jLabel1.setForeground(new Color(255, 96, 18));
        jLabel1.setText("Signing In");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 20, 10);
        jPanel1.add(jLabel1, gbc);

        // Username field and icon
        JPanel usernamePanel = new JPanel();
        usernamePanel.setBackground(Color.WHITE);
        usernamePanel.setLayout(new GridBagLayout());
        jTextField1.setForeground(new Color(153, 153, 153));
        jTextField1.setColumns(20);
        String iconPath2 = "icons\\user_icon.png";
        ImageIcon icon2 = new ImageIcon(iconPath2);
        jLabel3.setIcon(icon2);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        usernamePanel.add(jTextField1, gbc);

        gbc.gridx = 1;
        usernamePanel.add(jLabel3, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        jPanel1.add(usernamePanel, gbc);

        // Password field and icon
        JPanel passwordPanel = new JPanel();
        passwordPanel.setBackground(Color.WHITE);
        passwordPanel.setLayout(new GridBagLayout());
        jPasswordField1.setForeground(new Color(153, 153, 153));
        jPasswordField1.setColumns(20);
        String iconPath = "icons\\lock_icon.png";
        ImageIcon icon = new ImageIcon(iconPath);
        jLabel4.setIcon(icon);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        passwordPanel.add(jPasswordField1, gbc);

        gbc.gridx = 1;
        passwordPanel.add(jLabel4, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        jPanel1.add(passwordPanel, gbc);

        // Show password checkbox
        jCheckBox1.setText("show Password");
        jCheckBox1.setForeground(new Color(153, 153, 153));
        jCheckBox1.setFont(new java.awt.Font("Georgia", 2, 10));
        jCheckBox1.addActionListener(evt -> jCheckBox1ActionPerformed(evt));

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        jPanel1.add(jCheckBox1, gbc);

        // Sign-in button
        signin.setBackground(new Color(255, 96, 18));
        signin.setFont(new java.awt.Font("Segoe UI", 1, 18));
        signin.setForeground(Color.WHITE);
        signin.setText("Sign In");
        signin.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        signin.addActionListener(evt -> signinActionPerformed(evt));

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        jPanel1.add(signin, gbc);

        // Register button
        register.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14));
        register.setForeground(new Color(255, 96, 18));
        register.setBackground(Color.WHITE);
        register.setText("Register");
        register.setBorder(null);
        register.addActionListener(evt -> registerActionPerformed(evt));

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 20, 10);
        jPanel1.add(register, gbc);

        // Restaurant logo
        // String iconPath3 = "icons\\logoResutrant.png";
        // ImageIcon icon3 = new ImageIcon(iconPath3);
        // jLabel2.setIcon(icon3);

        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 6;
        gbc.insets = new Insets(20, 20, 20, 20);
        jPanel1.add(jLabel2, gbc);

        // Footer text
        // jLabel5.setFont(new java.awt.Font("Nirmala UI", 3, 22));
        // jLabel5.setForeground(new Color(255, 96, 18));
        // jLabel5.setText("Delicious Food");

        // gbc = new GridBagConstraints();
        // gbc.gridx = 2;
        // gbc.gridy = 6;
        // gbc.insets = new Insets(10, 10, 20, 10);
        // jPanel1.add(jLabel5, gbc);

        // Final panel layout
        setLayout(new java.awt.BorderLayout());
        add(jPanel1, java.awt.BorderLayout.CENTER);
    }

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        if (jCheckBox1.isSelected()) {
            jPasswordField1.setEchoChar((char) 0); // Show password
        } else {
            jPasswordField1.setEchoChar('*'); // Hide password
        }
    }

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
        // Handle username field action
    }

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {
        // Handle password field action
    }

    private void signinActionPerformed(java.awt.event.ActionEvent evt) {
        String email = jTextField1.getText();
        String password = new String(jPasswordField1.getPassword());

        Status loginStatus = UsersManagement.logIn(email, password);

        if (loginStatus.isDone()) {
            try {
                int userType = UsersManagement.getUserByEmail(email).getUserType();
                System.out.println(userType);
                // Redirect based on user type
                switch (userType) {
                    case 1: //cusomer
                    SwingUtilities.invokeLater(() -> {
                       
                        MainFrame.setPanel(new OrderManagementUI());
                        
            
                    });
            
                        ((javax.swing.JFrame) getTopLevelAncestor()).dispose();
                        break;
                    case 2: // Employee
                    SwingUtilities.invokeLater(() -> {
                       
                        MainFrame.setPanel(new EmployeeOrderUI());
                        
            
                    });
                        ((javax.swing.JFrame) getTopLevelAncestor()).dispose();
                        break;
                    case 3: // manger
                    SwingUtilities.invokeLater(() -> {
                       
                        MainFrame.setPanel(new WelcomeMangerPanel());
                        
            
                    });
                        ((javax.swing.JFrame) getTopLevelAncestor()).dispose();
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "An error occurred while fetching user details.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, loginStatus.getMsg(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registerActionPerformed(java.awt.event.ActionEvent evt) {
        SwingUtilities.invokeLater(() -> {
           
            MainFrame.setPanel(new SignUp());
           

        });

    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sign In");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.add(new SignInPanel());
            frame.setVisible(true);
        });
    }
}