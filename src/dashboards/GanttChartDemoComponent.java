package org.swiftgantt.demo;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

//test3
import org.swiftgantt.GanttChart;

import org.swiftgantt.common.EventLogger;
import org.swiftgantt.common.Time;
import org.swiftgantt.model.GanttModel;
import org.swiftgantt.model.Task;
import org.swiftgantt.ui.TimeUnit;

/**
 * This class is just for demonstration only, don't use this for you application.
 * 
 * @author Yuxing Wang
 * 
 */

//news changes from here
public final class GanttChartDemoComponent extends GanttChart {

	private static final long serialVersionUID = 1L;

	public GanttChartDemoComponent() {
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
		this.setLocation(0, 0);
		this.setSize(new Dimension(400, 200));
		this.addComponentListener(new ComponentListener() {
			public void componentHidden(ComponentEvent e) {
			}

			public void componentMoved(ComponentEvent e) {
			}

			public void componentResized(ComponentEvent e) {
				EventLogger.event(e, "[DEMO] The size of GanttChart resized to: " + getSize());
			}

			public void componentShown(ComponentEvent e) {
			}
		});

		logger.debug("[DEMO] ---- Init model for gantt chart completed ----");
	}

	//	Those field is for rest-up time axis only //
	Time startTime = null;
	Time fakeEndTime = null;
	Time time1 = null;
	Time time2 = null;
	Time time20 = null;
	Time time21 = null;
	Time time3 = null;
	Time restoutTime1 = null;
	Time restoutTime2 = null;
	Time realTime = null;
	Time endTime = null;

	// Tasks creation.
	Task taskMain = null;

	public void initHourlyModel() {
		logger.info("[DEMO] Start to init hourly model...");
		this.setTimeUnit(TimeUnit.Hour);
		//
		startTime = new Time(2007, 8, 16, 9, 30);
		fakeEndTime = new Time(2007, 8, 16, 12, 0); // Let latest task wide it.
		time1 = new Time(2007, 8, 16, 11, 30);
		time2 = new Time(2007, 8, 16, 13, 30);
		time20 = new Time(2007, 8, 16, 14, 30);
		time21 = new Time(2007, 8, 16, 16, 30);
		restoutTime1 = new Time(2007, 8, 16, 4, 0);
		restoutTime2 = new Time(2007, 8, 16, 19, 0);
		time3 = new Time(2007, 8, 17, 14, 0);
		endTime = new Time(2007, 8, 18, 14, 0);
		realTime = new Time().clone().increaseHours(3);//CalendarUtils.cloneTimeByHourOffset(new Time(), 3);// 3 hours after current time.
		//
		taskMain = new Task("Hourly Model Demo", startTime, fakeEndTime, null);
		this.toInitResoutableModel();
	}

	public void initDailyModel() {
		logger.debug("[DEMO] Start to init daily model...");
		this.setTimeUnit(TimeUnit.Day);
		//
		startTime = new Time(2007, 6, 1, 12, 0, 0);
		fakeEndTime = new Time(2007, 6, 5, 12, 0, 0);
		time1 = new Time(2007, 6, 5, 1, 0, 0);
		time2 = new Time(2007, 6, 5, 2, 0, 0);
		time20 = new Time(2007, 6, 7, 0, 0, 0);
		time21 = new Time(2007, 6, 8, 0, 0, 0);
		time3 = new Time(2007, 6, 24, 12, 0, 0);
		restoutTime1 = new Time(2007, 6, 10, 12, 0, 0);
		restoutTime2 = new Time(2007, 6, 24, 12, 0, 0);
		endTime = new Time(2007, 7, 10, 12, 0, 0);
		realTime = new Time().clone().increaseDates(3);// CalendarUtils.cloneTimeByDateOffset(new Time(), 3);// 3 days after current time.
		//
		taskMain = new Task("Daily Model Demo", startTime, fakeEndTime, null);
		toInitResoutableModel();
	}

	/*
	 * for all models that having rest-out .
	 */
	private void toInitResoutableModel() {
		GanttModel model = new GanttModel();//gantt.getModel();
		model.removeAll();
		model.setKickoffTime(startTime);
		model.setDeadline(fakeEndTime);
		Task task2 = new Task("Predecessor Task", startTime, time1, 100);
		Task task3 = new Task("Task With Predecessor", time1, time2);
		Task task4 = new Task("Task With Same Predecessor", "With description and progress when constructed", time20, time21, 3, null);
		Task task21 = new Task("Task Start And End At Rest-out Time", restoutTime1, restoutTime2);
		task21.setProgress(3);
		Task task22 = new Task("Task Has Exceeded Progress", restoutTime2, endTime);
		task22.setProgress(300);
		Task task6 = new Task("Sub Task Group", time1, time3);
		task6.setProgress(5);
		Task task7 = new Task("Task With Minus Progress At Sub Task Group", time2, restoutTime2);
		task7.setProgress(-1);
		task6.add(task7);
		Task task8 = new Task("Task that has current time", time2, realTime);

		//Relationship
		task3.addPredecessor(task2);
		task4.addPredecessor(task2);
		taskMain.add(new Task[] { task2, task3, task4, task21, task22, task6, task8 });
		//		task2.setBackcolor(Color.PINK);

		model.addTask(taskMain);
		this.setModel(model);
	}

	public void initAllDayModel() {
		logger.info("[DEMO] Start to init All Day model...");
		this.setTimeUnit(TimeUnit.AllDay);
		GanttModel model = new GanttModel();//gantt.getModel();
		model.removeAll();
		//
		Time st16_09 = new Time(2007, 8, 16, 9, 0);
		Time t16_11 = new Time(2007, 8, 16, 11, 0);
		Time t16_13 = new Time(2007, 8, 16, 13, 0);
		Time t16_14 = new Time(2007, 8, 16, 14, 0);
		Time t16_16 = new Time(2007, 8, 16, 16, 0);
		Time t16_19 = new Time(2007, 8, 16, 19, 0);
		Time t17_14 = new Time(2007, 8, 17, 14, 0);
		Time fet16_12 = new Time(2007, 8, 16, 12, 0); // Let latest task wide it.
		Time et18_14 = new Time(2007, 8, 18, 14, 0);
		realTime = new Time().clone().increaseHours(3);//CalendarUtils.cloneTimeByHourOffset(new Time(), 3);// 3 hours after current time.

		//
		taskMain = new Task("All Day Model Demo", st16_09, fet16_12, null);

		// Taks 2 for test
		Task task2 = new Task("Predecessor Task", st16_09, t16_16);
		task2.setProgress(3);
		Task task3 = new Task("Task With Predecessor", t16_11, t16_13);
		Task task4 = new Task("Task With Same Predecessor", t16_13, t16_14);
		task4.setProgress(3);
		Task task5 = new Task("Task Without Predecessor", t16_14, t16_16);
		task5.setProgress(20);
		Task task8 = new Task("Task that has current time", t17_14, realTime);
		task8.setProgress(500);

		// Task group 1
		Task taskGroup1 = new Task("Sub Task Group 1", t16_14, t16_16);
		taskGroup1.setProgress(5);
		Task task7 = new Task("Task Exceed Group", t16_14, t16_19);
		task7.setProgress(-1);
		taskGroup1.add(task7);

		// Task group 2
		Task taskGroup2 = new Task("Sub Task Group 2", t16_14, t17_14);
		taskGroup1.setProgress(5);
		Task task21 = new Task("Task Shrink To Group", t16_16, t16_19);
		taskGroup2.add(task21);

		// Relationship
		task3.addPredecessor(task2);
		task4.addPredecessor(task2);
		taskMain.add(new Task[] { task2, task3, task4, task5, taskGroup1, taskGroup2, task8 });

		// Add task to model after all sub tasks has been added to the task.
		model.addTask(taskMain);
		model.setKickoffTime(st16_09);
		model.setDeadline(et18_14);
		this.setModel(model);
	}

	/**
	 * Weekly
	 */
	public void initWeeklyModel() {
		logger.debug("[DEMO] Start to init weekly model...");
		this.setTimeUnit(TimeUnit.Week);
		GanttModel model = new GanttModel(); //this.getModel();

		Time startTime = new Time(2007, 1, 1, 0, 0, 0);
		Time time6 = new Time(2007, 2, 6, 0, 0, 0);
		Time time9 = new Time(2007, 2, 25, 0, 0, 0);
		Time time12 = new Time(2007, 3, 23, 0, 0, 0);
		Time time18 = new Time(2007, 4, 30, 0, 0, 0);
		Time time24 = new Time(2007, 6, 15, 0, 0, 0);
		realTime = new Time().clone().increaseWeeks(3);//CalendarUtils.cloneTimeByWeekOffset(new Time(), 3);// 3 hours after current time.

		model.setKickoffTime(startTime);
		model.setDeadline(time12);
		model.removeAll();
		Task taskMain = new Task("Weekly Model Demo", startTime, time6);
		// Taks 2 for test
		Task task2 = new Task("Predecessor Task", startTime, time6, 5);
		Task task3 = new Task("Task With Predecessor", time6, time9);
		Task task4 = new Task("Task With Same Predecessor", "Description", time6, time12, 3, null);
		Task task5 = new Task("Task Without Predecessor", time9, time18);
		task5.setProgress(20);
		Task task6 = new Task("Sub Task Group", time6, time12);
		task6.setProgress(5);
		Task task7 = new Task("Task Without Predecessor", time9, time24);
		task7.setProgress(-1);
		task6.add(task7);
		Task task8 = new Task("Task that has current time", time12, realTime);
		task8.setProgress(500);

		// Relationship
		task3.addPredecessor(task2);
		task4.addPredecessor(task2);
		taskMain.add(new Task[] { task2, task3, task4, task5, task6, task8 });

		// Add task to model after all sub tasks has been added to the task.
		model.addTask(taskMain);

		this.setModel(model);
		logger.debug("[DEMO] Gantt Object when init:" + this.getModel());
	}

	public void initMonthlyModel() {
		logger.debug("[DEMO] Start to init monthly model...");
		this.setTimeUnit(TimeUnit.Month);
		GanttModel model = new GanttModel();
		model.removeAll();
		Time startTime = new Time(2007, 1, 7); // Start from 07 - Jan
		Time fakeEndTime = new Time(2007, 3, 8); // Faked end at 07 - Mar
		Time time7_3 = new Time(2007, 3, 9);
		Time time7_5 = new Time(2007, 5, 10);
		Time time7_7 = new Time(2007, 5, 11);
		Time time7_8 = new Time(2007, 8, 12);
		Time time7_11 = new Time(2007, 20, 13);
		Time time8_5 = new Time(2008, 5, 14);
		Time time8_10 = new Time(2008, 10, 15);
		Time time8_12 = new Time(2008, 12, 18);
		realTime = new Time().clone().increaseMonths(3);//CalendarUtils.cloneTimeByMonthOffset(new Time(), 3);// 3 hours after current time.

		Task taskMain = new Task("Monthly Model Demo", startTime, fakeEndTime);
		// Taks 2 for test
		Task task2 = new Task("Predecessor Task", startTime, time7_3, 100);
		Task task3 = new Task("Task With Predecessor", time7_3, time7_5);
		Task task4 = new Task("Task With Same Predecessor", "Description", time7_3, time7_11, 3, null);
		Task task5 = new Task("Task Without Predecessor", time7_3, time7_8);
		task5.setProgress(20);

		Task taskGroup1 = new Task("Sub Task Group 1", time7_8, time7_11);
		taskGroup1.setProgress(5);
		Task task7 = new Task("Task Exceed Group", time7_5, time8_5);
		task7.setProgress(-1);
		taskGroup1.add(task7);
		Task task8 = new Task("Task that has current time", time7_8, realTime);
		task8.setProgress(500);

		Task taskGroup2 = new Task("Sub Task Group 2", time7_5, time8_12);
		taskGroup1.setProgress(5);
		Task task21 = new Task("Task Shrink To Group", time8_5, time8_10);
		taskGroup2.add(task21);

		// Relationship
		task3.addPredecessor(task2);
		task4.addPredecessor(task2);
		taskMain.add(new Task[] { task2, task3, task4, task5, taskGroup1, taskGroup2, task8 });

		// Add task to model after all sub tasks has been added to the task.
		model.addTask(taskMain);

		model.setKickoffTime(startTime);
		model.setDeadline(time7_7);
		this.setModel(model);
	}

	public void initYearModel() {
		this.setTimeUnit(TimeUnit.Year);

		GanttModel model = new GanttModel();
		model.removeAll();
		Time startTime = new Time(1997, 11, 1);
		Time fakeEndTime = new Time(2002, 6, 5);
		Time time1 = new Time(2000, 2, 1);
		Time time2 = new Time(2002, 2, 1);
		Time time3 = new Time(2007, 2, 1);
		Time time4 = new Time(2015, 2, 1);

		model.setKickoffTime(startTime);
		model.setDeadline(time4);

		Task taskMain = new Task("Yearly Model Demo -- My Farm", startTime, fakeEndTime);
		// Taks 2 for test
		Task task1 = new Task("Work Together", time3, time4);

		Task taskGroup1 = new Task("Field 1", startTime, time3);
		Task task11 = new Task("Plant Apple", startTime, time1);
		Task task12 = new Task("Plant Orange", "Description", time1, time3, 0, null);

		Task taskGroup2 = new Task("Field 2", startTime, time3);
		Task task5 = new Task("Plant Watermelon", startTime, time2);
		Task task6 = new Task("Plant Grape", time2, time3);

		taskGroup1.add(new Task[] { task11, task12 });
		taskGroup2.add(new Task[] { task5, task6 });
		taskMain.add(new Task[] { task1, taskGroup1, taskGroup2 });

		// Add task to model after all sub tasks has been added to the task.
		model.addTask(new Task[] { taskMain });
		this.setModel(model);
	}

	public GanttModel getGanttModel() {
		return this.getModel();
	}

} // @jve:decl-index=0:visual-constraint="10,10"
