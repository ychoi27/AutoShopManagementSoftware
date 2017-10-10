
import java.awt.Color;
import java.util.Calendar;
import javax.swing.JScrollPane;
import org.swiftgantt.common.Time;
import org.swiftgantt.Config;
import org.swiftgantt.GanttChart;

import org.swiftgantt.model.GanttModel;
import org.swiftgantt.model.Task;
import org.swiftgantt.ui.TimeUnit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ck437
 */
public class swiftGannchart {


    public static void main(String [] args){
        
        GanttChart gantt = new GanttChart();
        gantt.setTimeUnit(TimeUnit.Week);
        
        Config config = gantt.getConfig();
        config.setWorkingTimeBackColor(Color.YELLOW);//Set background color for working time.
        config.setTimeUnitWidth(50);//Set width for time unit
        config.setWorkingDaysSpanOfWeek(new int[]{Calendar.MONDAY, Calendar.THURSDAY});//Set span of working days in each week
        GanttModel model = new GanttModel();
        model.setKickoffTime( new org.swiftgantt.common.Time(2008, 0, 1));
        model.setDeadline( new org.swiftgantt.common.Time(2008, 0, 30));

        Task taskGroup = new Task("My Work 1", new org.swiftgantt.common.Time(2008, 0, 1), new org.swiftgantt.common.Time(2008, 0, 30));
        Task task1 = new Task("Sub-task 1", new org.swiftgantt.common.Time(2008, 0, 1), new org.swiftgantt.common.Time(2008, 0, 5));
        org.swiftgantt.model.Task task2 = new Task();
        task2.setName("Sub-task 2");
        task2.setStart(new org.swiftgantt.common.Time(2008, 0, 6));
        task2.setEnd(new org.swiftgantt.common.Time(2008, 0, 18));// Since version 0.3.0, the end time set to a task is included in duration of the task

        taskGroup.add(new org.swiftgantt.model.Task[]{task1, task2});

        task2.addPredecessor(task1);
        model.addTask(taskGroup);
        gantt.setModel(model);
        gantt.setVisible(true);
        JScrollPane scrollPane = new JScrollPane( gantt );
        scrollPane.setVisible(true);
        }
}


