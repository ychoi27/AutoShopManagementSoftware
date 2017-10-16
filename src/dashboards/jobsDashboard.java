/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboards;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Calendar;
import javax.swing.JScrollPane;
import org.swiftgantt.Config;
import org.swiftgantt.GanttChart;
import org.swiftgantt.model.GanttModel;
import org.swiftgantt.model.Task;
import org.swiftgantt.ui.TimeUnit;

/**
 *
 * @author jimmy_000
 */
public class jobsDashboard extends javax.swing.JFrame {

    /**
     * Creates new form Inventory_tab
     */
    public jobsDashboard() {
        initComponents();
        
        GanttChart gantt = new GanttChart();
        gantt.setTimeUnit(TimeUnit.Day);
        
        Config config = gantt.getConfig();
        config.setWorkingTimeBackColor(Color.YELLOW);//Set background color for working time.
        config.setTimeUnitWidth(50);//Set width for time unit
        config.setWorkingDaysSpanOfWeek(new int[]{Calendar.MONDAY, Calendar.THURSDAY});//Set span of working days in each week
        GanttModel model = new GanttModel();
        model.setKickoffTime( new org.swiftgantt.common.Time(2010, 0, 1));
        model.setDeadline( new org.swiftgantt.common.Time(2012, 0, 30));

        Task taskGroup = new Task("My Work 1", new org.swiftgantt.common.Time(2010, 5, 1), new org.swiftgantt.common.Time(2010, 6, 30));
        Task task1 = new Task("Sub-task 1", new org.swiftgantt.common.Time(2010, 0, 1), new org.swiftgantt.common.Time(2010, 0, 5));
        org.swiftgantt.model.Task task2 = new Task();
        task2.setName("Sub-task 2");
        task2.setStart(new org.swiftgantt.common.Time(2010, 0, 6));
        task2.setEnd(new org.swiftgantt.common.Time(2010, 0, 18));// Since version 0.3.0, the end time set to a task is included in duration of the task

        taskGroup.add(new org.swiftgantt.model.Task[]{task1, task2});

        task2.addPredecessor(task1);
        model.addTask(taskGroup);
        gantt.setModel(model);
        gantt.setVisible(true);
        
        chartPanel.setLayout(new GridLayout());
	chartPanel.add(gantt, null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jList1 = new javax.swing.JList<>();
        jobsButton = new javax.swing.JButton();
        inventoryButton = new javax.swing.JButton();
        customerButton = new javax.swing.JButton();
        signoutButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        addJobButton = new javax.swing.JButton();
        searchInput = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        Category = new javax.swing.JComboBox<>();
        displayCurrentDate = new javax.swing.JTextField();
        allJobsTable = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jobDescription = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        refreshTable = new javax.swing.JButton();
        nextDate = new javax.swing.JButton();
        previousDate = new javax.swing.JButton();
        chartPanel = new javax.swing.JPanel();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1345, 1251));
        setResizable(false);

        jobsButton.setText("JOBS");
        jobsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jobsButtonMouseClicked(evt);
            }
        });
        jobsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jobsButtonActionPerformed(evt);
            }
        });

        inventoryButton.setText("INVENTORY");
        inventoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inventoryButtonActionPerformed(evt);
            }
        });

        customerButton.setText("CUSTOMERS");
        customerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerButtonActionPerformed(evt);
            }
        });

        signoutButton.setText("SIGN OUT");
        signoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signoutButtonActionPerformed(evt);
            }
        });

        backButton.setText("BACK");

        addJobButton.setText("Add Job");

        searchInput.setText("[Enter text]");

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        Category.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        displayCurrentDate.setText("CurrentDate");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        allJobsTable.setViewportView(jTable1);

        jTextPane1.setText("<JobDescriptions>");
        jobDescription.setViewportView(jTextPane1);

        refreshTable.setText("Refresh");

        nextDate.setText("Nextdate");

        previousDate.setText("previousDate");

        javax.swing.GroupLayout chartPanelLayout = new javax.swing.GroupLayout(chartPanel);
        chartPanel.setLayout(chartPanelLayout);
        chartPanelLayout.setHorizontalGroup(
            chartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1225, Short.MAX_VALUE)
        );
        chartPanelLayout.setVerticalGroup(
            chartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 533, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jobsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(signoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(inventoryButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customerButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(previousDate, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(displayCurrentDate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nextDate, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(chartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jobDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                            .addComponent(allJobsTable, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(4, 4, 4))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addJobButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 789, Short.MAX_VALUE)
                        .addComponent(refreshTable, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchInput, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Category, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchButton)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(allJobsTable, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jobDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(displayCurrentDate, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nextDate)
                                    .addComponent(previousDate))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(chartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(64, 64, 64))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jobsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(inventoryButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(customerButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(signoutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 507, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchButton)
                            .addComponent(Category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addJobButton)
                            .addComponent(refreshTable))))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1649, 1330));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jobsButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jobsButtonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jobsButtonMouseClicked

    private void customerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerButtonActionPerformed
        // TODO add your handling code here:
        clientsDashboard clientsDash = new clientsDashboard();
        clientsDash.setVisible(true);
        dispose();
    }//GEN-LAST:event_customerButtonActionPerformed

    private void jobsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jobsButtonActionPerformed
        // TODO add your handling code here:
        jobsDashboard jobsDash = new jobsDashboard();
        jobsDash.setVisible(true);
        dispose();
    }//GEN-LAST:event_jobsButtonActionPerformed

    private void inventoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventoryButtonActionPerformed
        // TODO add your handling code here:
        partsDashboard partsDash = new partsDashboard();
        partsDash.setVisible(true);
        dispose();
    }//GEN-LAST:event_inventoryButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchButtonActionPerformed

    private void signoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signoutButtonActionPerformed
        // TODO add your handling code here:
        loginDashboard loginDash = new loginDashboard();
        loginDash.setVisible(true);
        dispose();
    }//GEN-LAST:event_signoutButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         *//*
                 
             
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Job_tab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Job_tab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Job_tab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Job_tab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jobsDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Category;
    private javax.swing.JButton addJobButton;
    private javax.swing.JScrollPane allJobsTable;
    private javax.swing.JButton backButton;
    private javax.swing.JPanel chartPanel;
    private javax.swing.JButton customerButton;
    private javax.swing.JTextField displayCurrentDate;
    private javax.swing.JButton inventoryButton;
    private javax.swing.JList<String> jList1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JScrollPane jobDescription;
    private javax.swing.JButton jobsButton;
    private javax.swing.JButton nextDate;
    private javax.swing.JButton previousDate;
    private javax.swing.JButton refreshTable;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchInput;
    private javax.swing.JButton signoutButton;
    // End of variables declaration//GEN-END:variables
}
