package mangerInterface;

import java.awt.event.ActionEvent;

public class WelcomeManger extends javax.swing.JFrame {

    
    public WelcomeManger() {
        initComponents();
    }

    
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        addEmploeeyButton = new javax.swing.JButton();
        controlButton = new javax.swing.JButton();
        statistcsInformationButton = new javax.swing.JButton();
        welcomMessageManger = new javax.swing.JLabel();
        panelIconManger = new javax.swing.JPanel();
        IconMangerLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(800, 600));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 600));


        addEmploeeyButton.setBackground(new java.awt.Color(251, 133, 0));
        addEmploeeyButton.setFont(new java.awt.Font("Segoe UI", 3, 18)); 
        addEmploeeyButton.setForeground(new java.awt.Color(255, 255, 255));
        addEmploeeyButton.setText("Add employee");
        addEmploeeyButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        addEmploeeyButton.addActionListener(evt -> addEmployeeAction(evt));
        
        controlButton.setBackground(new java.awt.Color(251, 133, 0));
        controlButton.setFont(new java.awt.Font("Segoe UI", 3, 18)); 
        controlButton.setForeground(new java.awt.Color(255, 255, 255));
        controlButton.setText("Control menu");
        controlButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));


        statistcsInformationButton.setBackground(new java.awt.Color(251, 133, 0));
        statistcsInformationButton.setFont(new java.awt.Font("Segoe UI", 3, 18)); 
        statistcsInformationButton.setForeground(new java.awt.Color(255, 255, 255));
        statistcsInformationButton.setText("Show statistics");
        statistcsInformationButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));


        welcomMessageManger.setBackground(new java.awt.Color(255, 255, 255));
        welcomMessageManger.setFont(new java.awt.Font("Serif", 3, 48)); 
        welcomMessageManger.setForeground(new java.awt.Color(251, 133, 0));
        welcomMessageManger.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcomMessageManger.setText("Welcome, Manager");


        panelIconManger.setBackground(new java.awt.Color(255, 255, 255));

        
        javax.swing.GroupLayout panelIconMangerLayout = new javax.swing.GroupLayout(panelIconManger);
        panelIconManger.setLayout(panelIconMangerLayout);
        panelIconMangerLayout.setHorizontalGroup(
            panelIconMangerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(IconMangerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
        );
        panelIconMangerLayout.setVerticalGroup(
            panelIconMangerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(IconMangerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(addEmploeeyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                .addComponent(controlButton, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95)
                .addComponent(statistcsInformationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
            .addComponent(welcomMessageManger, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelIconManger, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(163, 163, 163))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(welcomMessageManger, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelIconManger, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(controlButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statistcsInformationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addEmploeeyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(98, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }
    private void addEmployeeAction(ActionEvent evt) {
      System.out.println("the button is clicked");

            new AddEmployee().setVisible(true);
               // this.dispose();
        
      
    }

    // Variables declaration - do not modify   without imports the classes /**/               
    private javax.swing.JLabel IconMangerLabel;
    private javax.swing.JButton addEmploeeyButton;
    private javax.swing.JButton controlButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelIconManger;
    private javax.swing.JButton statistcsInformationButton;
    private javax.swing.JLabel welcomMessageManger;
                   
}
