/*
 * ScheduleTab.java
 *
 * Created on 2009-1-3, 23:23:27
 */
package org.swiftgantt.demo.tab;

import org.swiftgantt.common.ExTree;
import org.swiftgantt.demo.GanttChartDemoComponent;
import org.swiftgantt.event.SelectionChangeEvent;
import org.swiftgantt.event.SelectionChangeListener;
import org.swiftgantt.model.Task;
import org.swiftgantt.model.TaskTreeModel;
import org.swiftgantt.ui.TimeUnit;
import java.awt.GridLayout;
import javax.swing.event.TreeModelEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import org.apache.commons.lang.StringUtils;

/**
 * Schedule tabed panel
 * @author Wang Yuxing
 */
public class ScheduleTab extends javax.swing.JPanel {

	private GanttChartDemoComponent ganttComp = null;
	private ExTree taskTree = null;

	public ScheduleTab() {
		initComponents();
	}

	public ScheduleTab(GanttChartDemoComponent gantt) {
		this.ganttComp = gantt;
		this.ganttComp.addSelectionChangeListener(new SelectionChangeListener() {

			public void selectionChanged(SelectionChangeEvent e) {
				Task task = e.getSelection();
				((ExTree) taskTree).select(task);
				if (task != null) {
					taskTree.expandTreeNode(task);
				}
				taskTree.updateUI();
			}
		});
		initComponents();
		taskTree = new ExTree();
		pnlTreeView.setLayout(new GridLayout());
		pnlTreeView.add(taskTree);
			int index = 0;
			if (gantt.getTimeUnit() == TimeUnit.Hour) {
				index = 0;
			} else if (gantt.getTimeUnit() == TimeUnit.AllDay) {
				index = 1;
			} else if (gantt.getTimeUnit() == TimeUnit.Day) {
				index = 2;
			} else if (gantt.getTimeUnit() == TimeUnit.Week) {
				index = 3;
			} else if (gantt.getTimeUnit() == TimeUnit.Month) {
				index = 4;
			} else if (gantt.getTimeUnit() == TimeUnit.Year) {
				index = 5;
			}
			cmbTimeUnit.setSelectedIndex(index);
	}

	public void setTaskTreeModel(TaskTreeModel taskTreeModel) {
		this.taskTree.setTreeModel(taskTreeModel);
		taskTreeModel.addTreeModelListener(new javax.swing.event.TreeModelListener() {

			public void treeNodesChanged(TreeModelEvent e) {
			}

			public void treeNodesInserted(TreeModelEvent e) {
			}

			public void treeNodesRemoved(TreeModelEvent e) {
			}

			public void treeStructureChanged(TreeModelEvent e) {
			}
		});
		//        System.err.println("[setTaskTreeModel] Is TaskTreeModel?" + (taskTree.getTreeModel() instanceof TaskTreeModel));
		this.taskTree.expandAllTreeNode((DefaultMutableTreeNode) taskTreeModel.getRoot());
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbTimeUnit = new javax.swing.JComboBox();
        btnAddTaskToRoot = new javax.swing.JButton();
        btnEditSelectedTask = new javax.swing.JButton();
        btnAddTaskToSelectTask = new javax.swing.JButton();
        btnRemoveTask = new javax.swing.JButton();
        btnRemoveAllTasks = new javax.swing.JButton();
        pnlTreeView = new javax.swing.JPanel();

        jLabel1.setText("Time Unit:");

        cmbTimeUnit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Hour", "AllDay", "Day", "Week", "Month", "Year" }));
        cmbTimeUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTimeUnitActionPerformed(evt);
            }
        });

        btnAddTaskToRoot.setText("Add Task to Root");
        btnAddTaskToRoot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTaskToRootActionPerformed(evt);
            }
        });

        btnEditSelectedTask.setText("Edit Selected Task");
        btnEditSelectedTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditSelectedTaskActionPerformed(evt);
            }
        });

        btnAddTaskToSelectTask.setText("Add Task to Selection");
        btnAddTaskToSelectTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTaskToSelectTaskActionPerformed(evt);
            }
        });

        btnRemoveTask.setText("Remove Selected Task");
        btnRemoveTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveTaskActionPerformed(evt);
            }
        });

        btnRemoveAllTasks.setText("Remove All Tasks");
        btnRemoveAllTasks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveAllTasksActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(cmbTimeUnit, 0, 183, Short.MAX_VALUE)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .add(btnAddTaskToRoot, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .add(btnEditSelectedTask, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .add(btnAddTaskToSelectTask, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .add(btnRemoveTask, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .add(btnRemoveAllTasks, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(cmbTimeUnit, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnAddTaskToRoot)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnEditSelectedTask)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnAddTaskToSelectTask)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnRemoveTask)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnRemoveAllTasks)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout pnlTreeViewLayout = new org.jdesktop.layout.GroupLayout(pnlTreeView);
        pnlTreeView.setLayout(pnlTreeViewLayout);
        pnlTreeViewLayout.setHorizontalGroup(
            pnlTreeViewLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 430, Short.MAX_VALUE)
        );
        pnlTreeViewLayout.setVerticalGroup(
            pnlTreeViewLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 254, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(pnlTreeView, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, pnlTreeView, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

	private void cmbTimeUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTimeUnitActionPerformed
		if (cmbTimeUnit.getSelectedItem().equals("Hour")) {
			ganttComp.initHourlyModel();
		} else if (cmbTimeUnit.getSelectedItem().equals("AllDay")) {
			ganttComp.initAllDayModel();
		} else if (cmbTimeUnit.getSelectedItem().equals("Day")) {
			ganttComp.initDailyModel();
		} else if (cmbTimeUnit.getSelectedItem().equals("Week")) {
			ganttComp.initWeeklyModel();
		} else if (cmbTimeUnit.getSelectedItem().equals("Month")) {
			ganttComp.initMonthlyModel();
		} else if (cmbTimeUnit.getSelectedItem().equals("Year")) {
			ganttComp.initYearModel();
		}
		taskTree.setTreeModel(ganttComp.getGanttModel().getTaskTreeModel());
		taskTree.updateUI();
		taskTree.expandAllTreeNode((DefaultMutableTreeNode) ganttComp.getGanttModel().getTaskTreeModel().getRoot());
	}//GEN-LAST:event_cmbTimeUnitActionPerformed

	private void btnAddTaskToRootActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTaskToRootActionPerformed
		TaskDialog td = new TaskDialog();
		td.setVisible(true);
		// Root node isn't a Task node.
		if(td.getTask() == null || StringUtils.isEmpty(td.getTask().getName())){
			return;
		}
		TaskTreeModel ttm = (TaskTreeModel) taskTree.getTreeModel();
		ttm.add(td.getTask());
		taskTree.updateUI();
	}//GEN-LAST:event_btnAddTaskToRootActionPerformed

	private void btnEditSelectedTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditSelectedTaskActionPerformed
		Task selectedTask = (Task) taskTree.getSelectedNode();
		if (selectedTask == null) {
			return;
		}
		ganttComp.suspendUI();
		TaskDialog td = new TaskDialog(selectedTask);
		td.setVisible(true);// Display
		selectedTask.copy(td.getTask());
		taskTree.updateUI();
		ganttComp.resumeUI();
	}//GEN-LAST:event_btnEditSelectedTaskActionPerformed

	private void btnAddTaskToSelectTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTaskToSelectTaskActionPerformed
		Task selectedTask = (Task) taskTree.getSelectedNode();
		if (selectedTask == null) {
			return;
		}
		TaskDialog td = new TaskDialog();
		td.setVisible(true);
		TaskTreeModel model = (TaskTreeModel) taskTree.getTreeModel();
		model.addTo(selectedTask, td.getTask());
		taskTree.expandTreeNode(selectedTask);
		taskTree.updateUI();
	}//GEN-LAST:event_btnAddTaskToSelectTaskActionPerformed

	private void btnRemoveTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveTaskActionPerformed
		Task selectedTask = (Task) taskTree.getSelectedNode();
		if (selectedTask == null) {
			return;
		}
		((TaskTreeModel) taskTree.getTreeModel()).removeTask(selectedTask);
	}//GEN-LAST:event_btnRemoveTaskActionPerformed

	private void btnRemoveAllTasksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveAllTasksActionPerformed
		ganttComp.getModel().removeAll();
	}//GEN-LAST:event_btnRemoveAllTasksActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddTaskToRoot;
    private javax.swing.JButton btnAddTaskToSelectTask;
    private javax.swing.JButton btnEditSelectedTask;
    private javax.swing.JButton btnRemoveAllTasks;
    private javax.swing.JButton btnRemoveTask;
    private javax.swing.JComboBox cmbTimeUnit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel pnlTreeView;
    // End of variables declaration//GEN-END:variables
}
