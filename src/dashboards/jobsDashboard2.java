package dashboards;

import javax.swing.*;

import org.swiftgantt.Config;
import org.swiftgantt.GanttChart;
import org.swiftgantt.model.GanttModel;
import org.swiftgantt.model.Task;
import org.swiftgantt.ui.TimeUnit;

import connector.JobDatabaseConnector;
import jobschedule.JobScheduler;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Calendar;

public class jobsDashboard2 extends Dashboard{
	
	JPanel main;
	JPanel ganttPanel;
	JPanel functions;
	
	JButton addJob;
	JButton deleteJob;
	JButton increasePriority;
	JButton decreasePriority;
	
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
		
		main = new JPanel();
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		main.setBackground(Color.BLUE);
		
		ganttPanel = new JPanel();
		ganttPanel.setLayout(null);
		
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
        populateGantt();
        //gantt configuration done
        
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        gantt.setBounds(0, 0, dim.width-108, 500);
        ganttPanel.add(gantt);
		main.add(ganttPanel);
		
		functions = new JPanel();
		
		

		window.add(BorderLayout.CENTER, main);
		window.setVisible(true);
	}
	
    private void populateGantt(){
        //add tasks
        JobScheduler js = new JobScheduler(startDate.toLocalDate());
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
	
	public static void main(String[] args){
		jobsDashboard2 jd = new jobsDashboard2();
		jd.init();
	}
	

}
