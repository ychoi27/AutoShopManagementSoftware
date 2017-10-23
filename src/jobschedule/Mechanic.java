package jobschedule;

public class Mechanic {
	JobSchedulingBlock active;
	MaxHeap jobs;
	
	public Mechanic(){
		jobs = new MaxHeap();
	}
	
	public boolean hasActiveJob(){
		if(active==null){
			return false;
		}
		return true;
	}

}
