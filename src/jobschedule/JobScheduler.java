package jobschedule;


import java.sql.*;
import connector.*;


public class JobScheduler {
	MaxHeap jobsList;
	Mechanic mech[] = {new Mechanic(), new Mechanic(), new Mechanic(), new Mechanic(), new Mechanic()};
	JobDatabaseConnector jdc;
	
	
	public JobScheduler(){
		jobsList = new MaxHeap();
		jdc = new JobDatabaseConnector();
		
	}
	
	public void scheduleJob(JobSchedulingBlock jsb){
		populateListFromDatabase();
		double maxPriority = -1.0;
		Mechanic topContender = null;
		int topContenderIndex = 0;
		int index = 0;
		while(jobsList.peekMax()!=null){
			for(Mechanic mechanic : mech){
				if(jobsList.peekMax()==null) return;
				if(mechanic.jobs.peekMax()!=null){
					double currentPriority = mechanic.jobs.peekMax().getPriority();
					if(currentPriority > maxPriority){
						maxPriority = currentPriority;
						topContender = mechanic;
						topContenderIndex = index;
					}
				}else{
					jobsList.peekMax().setMechanic(index);
					mechanic.jobs.addJob(jobsList.getMax());
					topContender = null;
				}
				index++;
			}
			if(topContender != null){
				jobsList.peekMax().setMechanic(topContenderIndex);
				topContender.jobs.addJob(jobsList.getMax());
			}
			for(Mechanic mechanic : mech){
				if(!mechanic.hasActiveJob() && (mechanic.jobs.peekMax() != null)){
					mechanic.active = mechanic.jobs.getMax();
				}
			}
		}
		writeJobsToDatabase();
		
	}
	private void populateListFromDatabase(){
		if(!jobsList.isEmpty()){
			jobsList = new MaxHeap();
		}
		ResultSet rs;
		
		rs = jdc.getAllJobsByID();
		
		if(rs!=null){
			try{
				while(rs.next()){
					System.out.println(rs.getInt("jobID"));
					JobSchedulingBlock jsb = new JobSchedulingBlock(rs.getInt("jobID"));
					jobsList.addJob(jsb);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	private void writeJobsToDatabase(){
		
	}
}
