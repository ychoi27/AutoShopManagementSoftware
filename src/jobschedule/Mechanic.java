package jobschedule;
import connector.*;

public class Mechanic {
	JobSchedulingBlock active;
	MaxHeap jobs;
	long maxBlocks;
	int blocksScheduled;
	JobDatabaseConnector jdc;
	int mechanicID;
	
	public Mechanic(int mechanicID, long maxBlocks){
		jobs = new MaxHeap();
		this.maxBlocks=maxBlocks;
		blocksScheduled=0;
		jdc= new JobDatabaseConnector();
		this.mechanicID = mechanicID;
	}
	
	public boolean hasActiveJob(){
		if(active==null){
			return false;
		}
		return true;
	}
	
	public void addJob(JobSchedulingBlock jsb){
		jobs.addJob(jsb);
		blocksScheduled += jsb.getJobLength();
		jdc.updateJobAttribute(jsb.getJobID(), "mechanicID", this.mechanicID);
	}
	
	public void completeJob(){
		jdc.deleteJob(active.getJobID());
		if(jobs.peekMax() != null){
			jobs.peekMax().setJobStatus("active");
			active = jobs.getMax();
		}
	}

}
