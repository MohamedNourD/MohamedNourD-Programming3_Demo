package loginInterface;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.nio.file.Paths;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Users.UserManagement;

public class SignInFrame extends javax.swing.JFrame {

    public SignInFrame() {
        initComponents();
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        signin = new javax.swing.JButton();
        register = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox(); // New checkbox for showing password

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // Panel setup
        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        // Title label
        jLabel1.setBackground(new java.awt.Color(255, 96, 18));
        jLabel1.setFont(new java.awt.Font("Georgia", 1, 24));
        jLabel1.setForeground(new java.awt.Color(255, 96, 18));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Signing In");

        // Username field
        jTextField1.setForeground(new java.awt.Color(153, 153, 153));
        jTextField1.setText("Email");
        jTextField1.addActionListener(evt -> jTextField1ActionPerformed(evt));

        // Password field
        jPasswordField1.setForeground(new java.awt.Color(153, 153, 153));
        jPasswordField1.setText("Password");
        jPasswordField1.addActionListener(evt -> jPasswordField1ActionPerformed(evt));

        // Checkbox for showing password
        jCheckBox1.setText("Show Password");
        jCheckBox1.setForeground(new java.awt.Color(153, 153, 153));
        jCheckBox1.setFont(new java.awt.Font("Georgia", 1, 11));
        jCheckBox1.addActionListener(evt -> jCheckBox1ActionPerformed(evt));
        // Labels for icons
        String iconPath = Paths.get("icons", "user_icon.png").toString();
        ImageIcon icon = new ImageIcon(iconPath);
        // Side panel icon
        jLabel4.setIcon(icon);

        String iconPath2 = Paths.get("icons", "lock_icon.png").toString();
        ImageIcon icon2 = new ImageIcon(iconPath2);
        jLabel3.setIcon(icon2);

        // Sign-in button
        signin.setBackground(new java.awt.Color(255, 96, 18));
        signin.setFont(new java.awt.Font("Segoe UI", 1, 18));
        signin.setForeground(new java.awt.Color(255, 255, 255));
        signin.setText("Sign In");
        signin.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        signin.addActionListener(evt -> signinActionPerformed(evt));

        // Register button
        register.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14));
        register.setForeground(new java.awt.Color(255, 96, 18));
        register.setBackground(Color.WHITE);
        register.setText("Register");
        register.setBorder(null);
        register.addActionListener(evt -> registerActionPerformed(evt));

        String iconPath3 = Paths.get("icons", "hamburger_icon.png").toString();
        ImageIcon icon3 = new ImageIcon(iconPath3);
        // Side panel icon
        jLabel2.setIcon(icon3);
       // jLabel2.setIcon(new ImageIcon("frontend/icons/hamburger_icon.png"));
       
        

        // Footer text
        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Nirmala UI", 3, 22));
        jLabel5.setForeground(new java.awt.Color(255, 96, 18));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Delicious Food");

        // Setting layout for main panel
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGroup(jPanel1Layout
                                                                .createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jTextField1,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 226,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jPasswordField1,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 226,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addPreferredGap(
                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(jPanel1Layout
                                                                .createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel4,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 53,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jLabel3,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 53,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addComponent(signin, javax.swing.GroupLayout.PREFERRED_SIZE, 180,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(register, javax.swing.GroupLayout.PREFERRED_SIZE, 86,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jCheckBox1)) // Add checkbox to layout
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 194,
                                        Short.MAX_VALUE)
                                .addGroup(jPanel1Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 270,
                                                Short.MAX_VALUE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(43, 43, 43))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(298, 298, 298)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 194,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 49,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 49,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 47,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 47,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox1) // Add checkbox to layout
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 300,
                                        Short.MAX_VALUE)
                                .addComponent(register, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(92, 92, 92))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 256,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82)
                                .addComponent(signin, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)));

        // Final frame layout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
                jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
                jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
    }

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        if (jCheckBox1.isSelected()) {
            jPasswordField1.setEchoChar((char) 0); // Show password
        } else {
            jPasswordField1.setEchoChar('*'); // Hide password
        }
    }


    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void signinActionPerformed(java.awt.event.ActionEvent evt) {
        
       try {
        boolean isExist = UserManagement.logIn(jTextField1.getText(),new String(jPasswordField1.getPassword()));
        System.out.println(isExist+"   the value of the isExist");
        if (isExist)
                System.out.println("the customer is existing");
        else if(isExist == false)
        {
        JLabel messageLabel = new JLabel("An error occurred while signing up");
        messageLabel.setForeground(Color.decode("#fb8500")); // Set text color
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Optional: Set font and size

        // Display the JOptionPane with the custom label
        JOptionPane.showMessageDialog(null,messageLabel,  "Error",JOptionPane.ERROR_MESSAGE);
        }
      } catch (IOException e) {
        
       System.out.println("an error occurred in login methode ");
     } 
     // link withthe four Interface order interface in prograsse working 

     //new MenuItem().setVisible(isExist);
    // this.disable();

    }

    private void registerActionPerformed(java.awt.event.ActionEvent evt) {
        new SignUp().setVisible(true);
        this.dispose(); // Close current window after register click 
    }

    // Variables declaration
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
}
