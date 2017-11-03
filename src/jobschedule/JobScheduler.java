package jobschedule;


import java.sql.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import connector.*;


public class JobScheduler {
	MaxHeap jobsList;
	public Mechanic mech[];
	JobDatabaseConnector jdc;
	LocalDate startDate;
	long maxBlocks;
	LocalDateTime jobStartDateHour;
	LocalDateTime endOfDay;
	
	public JobScheduler(LocalDate startDate){
		jobsList = new MaxHeap();
		jdc = new JobDatabaseConnector();
		this.startDate = startDate;
		endOfDay = LocalDateTime.of(startDate.getYear(), startDate.getMonthValue(), startDate.getDayOfMonth(), 18, 0);
		if(LocalDate.now().isEqual(startDate)){
			if(LocalDateTime.now().getMinute() < 30){
				jobStartDateHour = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS).plusMinutes(30);
			}else {
				jobStartDateHour = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS).plusHours(1);
			}
			Duration d = Duration.between(jobStartDateHour, endOfDay);
			long minutesLeft = d.toMinutes();
			maxBlocks = minutesLeft/30;
		}else{
			this.maxBlocks = 18;
			jobStartDateHour = LocalDateTime.of(startDate.getYear(), startDate.getMonthValue(), startDate.getDayOfMonth(), 9, 0);
		}
		this.mech = new Mechanic[5];
		for(int i = 0; i <5; i++){
			mech[i] = new Mechanic(i+1, maxBlocks);
		}
		buildSchedule();
	}
	
	public void scheduleJob(JobSchedulingBlock jsb){
		buildSchedule();	
	}
	public java.sql.Date getStartDate(){
		return java.sql.Date.valueOf(startDate);
	}
	
	public void buildSchedule(){
		JobSchedulingBlock[] activeJobs = populateListFromDatabase();
		
		for(int i=0; i<activeJobs.length; i++){
			if(activeJobs[i] == null) break;
			mech[i].active = activeJobs[i];
			
		}

		while(jobsList.peekMax()!=null){
			boolean noSpaceForJob =true;
			for(Mechanic mechanic : mech){
				if(jobsList.peekMax()==null) break;
				if(mechanic.maxBlocks < (mechanic.blocksScheduled+jobsList.peekMax().getJobLength())) continue;
				noSpaceForJob=false;
				mechanic.addJob(jobsList.getMax());
			}
			for(Mechanic mechanic : mech){
				if(!mechanic.hasActiveJob() && (mechanic.jobs.peekMax() != null)){
					mechanic.jobs.peekMax().setJobStatus("active");
					jdc.updateJobAttribute(mechanic.jobs.peekMax().getJobID(), "jobStatus", "active");
					mechanic.active = mechanic.jobs.getMax();
				}
			}
			if(noSpaceForJob){
				jobsList.getMax();
			}
		}
		writeJobsToDatabase();	
	}
		

	private JobSchedulingBlock[] populateListFromDatabase(){
		if(!jobsList.isEmpty()){
			jobsList = new MaxHeap();
		}
		ResultSet rs;
		JobSchedulingBlock activeJobs[] = new JobSchedulingBlock[mech.length];
		int index = 0;
		
		rs = jdc.getCurrentJobsByID(Date.valueOf(startDate));
		
		if(rs!=null){
			try{
				while(rs.next()){
					JobSchedulingBlock jsb = new JobSchedulingBlock(rs.getInt("jobID"));
					if(jsb.getJobStatus().equals("active")){
						activeJobs[index] = jsb;
						index++;
					}else{
						jobsList.addJob(jsb);
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return activeJobs;
		
	}
	private void writeJobsToDatabase(){
		for(Mechanic mechanic: mech){
			LocalDateTime jobCursor = jobStartDateHour;
			mechanic.active.setJobStartDateHour(java.sql.Timestamp.valueOf(jobCursor));
			mechanic.active.setJobEndDateHour(java.sql.Timestamp.valueOf(jobCursor.plusMinutes(30*mechanic.active.getJobLength())));
			jobCursor = jobCursor.plusMinutes(30*mechanic.active.getJobLength());
			while(mechanic.jobs.peekMax()!=null){
				mechanic.jobs.peekMax().setJobStartDateHour(java.sql.Timestamp.valueOf(jobCursor));
				mechanic.jobs.peekMax().setJobEndDateHour(java.sql.Timestamp.valueOf(jobCursor.plusMinutes(30*mechanic.jobs.peekMax().getJobLength())));
				jobCursor = jobCursor.plusMinutes(30*mechanic.jobs.peekMax().getJobLength());
				mechanic.jobs.getMax();
			}
		}
	}
}
