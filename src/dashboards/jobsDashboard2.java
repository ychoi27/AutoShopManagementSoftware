package dashboards;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;

import org.swiftgantt.Config;
import org.swiftgantt.GanttChart;
import org.swiftgantt.model.GanttModel;
import org.swiftgantt.model.Task;
import org.swiftgantt.ui.TimeUnit;

import connector.JobDatabaseConnector;
import jobschedule.JobScheduler;
import jobschedule.JobSchedulingBlock;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Vector;

public class jobsDashboard2 extends Dashboard{
	JobScheduler js;
	
	JPanel main;
	JPanel ganttPanel;
	JPanel functions;
	JPanel calendarFunctions;
	JPanel infoDisplay;
	
	JButton addJob;
	JButton deleteJob;
	JButton increasePriority;
	JButton decreasePriority;
	JButton refreshSchedule;
	JButton lastWeek;
	JButton thisWeek;
	JButton nextWeek;
	
	JScrollPane jobScroll;
	
	JTable jobsTable;
	DefaultTableModel tableModel;
	jobsTableMouseClicked jtm;
	
    private GanttChart gantt;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    
    int koMonth;
    int koYear;
    int koDate;
    
    int dlMonth;
    int dlYear;
    int dlDate;
    
    Config config;
    GanttModel model;
	
	public void init(){
		super.init();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		main = new JPanel();
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		main.setBackground(Color.gray);
		
		lastWeek = new JButton("<< Last week");
		lastWeek.addActionListener(new lastWeekButtonListener());
		
		thisWeek = new JButton("This week");
		thisWeek.addActionListener(new thisWeekButtonListener());
		
		nextWeek = new JButton("Next week >>");
		nextWeek.addActionListener(new nextWeekButtonListener());
		
		calendarFunctions = new JPanel();
		calendarFunctions.setMinimumSize(new Dimension(dim.width-108,40));
		calendarFunctions.setMaximumSize(new Dimension(dim.width-108,40));
		calendarFunctions.add(lastWeek);
		calendarFunctions.add(thisWeek);
		calendarFunctions.add(nextWeek);
		main.add(calendarFunctions);
		
		ganttPanel = new JPanel();
		ganttPanel.setLayout(null);
		ganttPanel.setMinimumSize(new Dimension(dim.width-108, 500));
		ganttPanel.setMaximumSize(new Dimension(dim.width-108, 500));
		
		//configure gantt chart
		gantt = new GanttChart();
        gantt.setTimeUnit(TimeUnit.Hour);
        startDate= LocalDateTime.now().plusDays(1);
        endDate= LocalDateTime.now().plusDays(7);
        
        koMonth = startDate.getMonthValue();
        koYear = startDate.getYear();
        koDate = startDate.getDayOfMonth();
        
        dlMonth= endDate.getMonthValue();
        dlYear = endDate.getYear();
        dlDate = endDate.getDayOfMonth();
        
		js = new JobScheduler(startDate.toLocalDate());
		
        config = gantt.getConfig();
        config.setWorkingTimeBackColor(Color.YELLOW);//Set background color for working time.
        config.setTimeUnitWidth(100);//Set width for time unit
        config.setWorkingDaysSpanOfWeek(new int[]{Calendar.SUNDAY, Calendar.SATURDAY});//Set span of working days in each week
        config.setWorkingHoursSpanOfDay(new int[]{9,17});
        model = new GanttModel();
        model.setKickoffTime( new org.swiftgantt.common.Time(koYear, koMonth, koDate));
        model.setDeadline( new org.swiftgantt.common.Time(dlYear, dlMonth, dlDate));
        gantt.setModel(model);
        gantt.setVisible(true);
        redrawGantt();
        //gantt configuration done
        
        gantt.setBounds(0, 25, dim.width-108, 500);
        gantt.setBackground(Color.white);
        ganttPanel.add(gantt);
		main.add(ganttPanel);
		
		addJob = new JButton("Add new job");
		addJob.addActionListener(new addJobButtonListener());
		
		deleteJob = new JButton("Delete job");
		deleteJob.addActionListener(new deleteJobButtonListener());
		increasePriority = new JButton("Increase job priority");
		decreasePriority = new JButton("Decrease job priority");
		
		refreshSchedule = new JButton("Refresh schedule");
		refreshSchedule.addActionListener(new refreshScheduleButtonListener());
		
		functions = new JPanel();
		functions.setMinimumSize(new Dimension(dim.width-108,40));
		functions.setMaximumSize(new Dimension(dim.width-108,40));
		functions.add(addJob);
		functions.add(deleteJob);
		functions.add(increasePriority);
		functions.add(decreasePriority);
		functions.add(refreshSchedule);
		main.add(functions);
		
		JobDatabaseConnector jdc = new JobDatabaseConnector();
		try{
			tableModel = buildTableModel(jdc.getAllJobs());
			jobsTable = new JTable(tableModel);
			jtm = new jobsTableMouseClicked();
			jobsTable.addMouseListener(jtm);
		
		}catch(SQLException e){}
		jobScroll = new JScrollPane(jobsTable);
		jobScroll.setMinimumSize(new Dimension((dim.width-108), 100));
		jobScroll.setMaximumSize(new Dimension((dim.width-108), 100));
        jobScroll.setBounds(0, 0, (dim.width-108), 100);
		
		infoDisplay = new JPanel();
		infoDisplay.setLayout(null);
		infoDisplay.setMinimumSize(new Dimension(dim.width-108, 100));
		infoDisplay.setMaximumSize(new Dimension(dim.width-108, 100));
		infoDisplay.add(jobScroll);
		infoDisplay.setBackground(Color.GREEN);
		
		main.add(infoDisplay);
		

		window.add(BorderLayout.CENTER, main);
		window.setVisible(true);
	}
	
    private void populateGantt(){
        //add tasks
        js = new JobScheduler(startDate.toLocalDate());
        JobDatabaseConnector jdc = new JobDatabaseConnector();
        Task mechanicTaskGroups[] = new Task[5];
        for (int i = 0; i < mechanicTaskGroups.length; i++){
       	 mechanicTaskGroups[i] = new Task("Mechanic "+ (i+1), new org.swiftgantt.common.Time(koYear, koMonth, koDate), new org.swiftgantt.common.Time(dlYear, dlMonth, dlDate));
       	 ResultSet rs = jdc.findJob("mechanicID", "=", (i+1));
       	 try{
       		 Task predecessor = null;
       		 int iter = 0;
       		 while(rs.next()){
       			 Task job = new Task("Job: " + rs.getInt("jobID"), new org.swiftgantt.common.Time(rs.getTimestamp("jobStartDateHour")), new org.swiftgantt.common.Time(rs.getTime("jobEndDateHour")));
       			 mechanicTaskGroups[i].add(job);
       			 if(iter>0){
       				 mechanicTaskGroups[i].addPredecessor(predecessor);
       			 }
       			 predecessor = job;
       			 iter++;
       		 }
       	 }catch(Exception e){
       		 e.printStackTrace();
       	 }
       	 model.addTask(mechanicTaskGroups[i]);
        }
        
        gantt.setVisible(true);
   }
    private void redrawGantt(){
    	model.removeAll();
    	js.buildSchedule();
    	populateGantt();
    	gantt.setVisible(true);
    }
    
    public static DefaultTableModel buildTableModel(ResultSet rs)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);

    }
	
	public static void main(String[] args){
		JobSchedulingBlock jsb = new JobSchedulingBlock("sampleJobType", false, 1, 17);
		
		jobsDashboard2 jd = new jobsDashboard2();
		jd.init();
	}
	
	class addJobButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			addJobWindow ajw = new addJobWindow(js);
			ajw.init();
		}
	}
	
	class refreshScheduleButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			redrawGantt();
		}
	}
	
	class lastWeekButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
	    	startDate = startDate.minusDays(7);
	    	endDate = endDate.minusDays(7);
	    	
	        koMonth = startDate.getMonthValue();
	        koYear = startDate.getYear();
	        koDate = startDate.getDayOfMonth();
	        
	        dlMonth= endDate.getMonthValue();
	        dlYear = endDate.getYear();
	        dlDate = endDate.getDayOfMonth();
	        
	        model.setKickoffTime( new org.swiftgantt.common.Time(koYear, koMonth, koDate));
	        model.setDeadline( new org.swiftgantt.common.Time(dlYear, dlMonth, dlDate));
		}
	}
	
	class thisWeekButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
	        gantt = new GanttChart();
	        gantt.setTimeUnit(TimeUnit.Hour);
	        startDate= LocalDateTime.now().plusDays(1);
	        endDate= LocalDateTime.now().plusDays(7);
	        
	        koMonth = startDate.getMonthValue();
	        koYear = startDate.getYear();
	        koDate = startDate.getDayOfMonth();
	        
	        dlMonth= endDate.getMonthValue();
	        dlYear = endDate.getYear();
	        dlDate = endDate.getDayOfMonth();
	        model.setKickoffTime( new org.swiftgantt.common.Time(koYear, koMonth, koDate));
	        model.setDeadline( new org.swiftgantt.common.Time(dlYear, dlMonth, dlDate));
		}
	}
	
	class nextWeekButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
	    	startDate = startDate.plusDays(7);
	    	endDate = endDate.plusDays(7);
	    	
	        koMonth = startDate.getMonthValue();
	        koYear = startDate.getYear();
	        koDate = startDate.getDayOfMonth();
	        
	        dlMonth= endDate.getMonthValue();
	        dlYear = endDate.getYear();
	        dlDate = endDate.getDayOfMonth();
	        
	        model.setKickoffTime( new org.swiftgantt.common.Time(koYear, koMonth, koDate));
	        model.setDeadline( new org.swiftgantt.common.Time(dlYear, dlMonth, dlDate));
		}
	}
	
	class deleteJobButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			int jobID = jtm.jobID;
			int row = jtm.row;
			JobDatabaseConnector jdc = new JobDatabaseConnector();
			if(jdc.jobStatus(jobID).equals("active")){
				js.mech[jdc.mechanicID(jobID)-1].setActive(null);
			}
			jdc.deleteJob(jobID);
			redrawGantt();
		
		}
	}
	
	class jobsTableMouseClicked implements MouseListener{
		int jobID;
		int row;
		@Override
		public void mouseClicked(MouseEvent e) {
	        row = jobsTable.getSelectedRow();
	        jobID = (int)jobsTable.getModel().getValueAt(row, 0);
	        
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	

}
