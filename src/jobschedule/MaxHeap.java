package jobschedule;

public class MaxHeap {
	JobSchedulingBlock[] jobHeap = new JobSchedulingBlock[1000];
	int lastIndex;
	//constructor
	public MaxHeap(){
		lastIndex = 0;
	}
	public JobSchedulingBlock getMax(){
		//removes and returns the maximum element of the heap: job scheduling block with highest priority
		JobSchedulingBlock max = jobHeap[1];
		jobHeap[1] = jobHeap[lastIndex];
		jobHeap[lastIndex--] = null;
		maxHeapify(1);
		return max;
	}
	public boolean isEmpty(){
		if(lastIndex == 0){
			return true;
		}
		return false;
	}
	public JobSchedulingBlock peekMax(){
		return jobHeap[1];
	}
	public void addJob(JobSchedulingBlock jsb){
		//adds a new process to the heap
		jobHeap[++lastIndex] = jsb;
		int index = lastIndex;
		if(jobHeap[index/2] == null){
			return;
		}
		while((jobHeap[index/2] != null) && (jobHeap[index/2].getPriority() < jsb.getPriority())){ //while parent node priority is less than child node priority
			//swap child and parent process position in the heap
			JobSchedulingBlock temp = jobHeap[index/2];
			jobHeap[index/2] = jsb;
			jobHeap[index] = temp;
			index = index/2;
		}
		
	}
	public void maxHeapify(int i){
		//maintains max-heap property of heap
		int left = 2*i;
		int right = 2*i+1;
		int greatest;
		if((left<=lastIndex)&&(jobHeap[left].getPriority() > jobHeap[i].getPriority())){
			greatest = left;
		}else{
			greatest = i;
		}
		if((right<=lastIndex)&&(jobHeap[right].getPriority() > jobHeap[greatest].getPriority())){
			greatest = right;
		}
		if(greatest != i){
			//swap processHeap[i] with processHeap[greatest]
			JobSchedulingBlock temp = jobHeap[i];
			jobHeap[i] = jobHeap[greatest];
			jobHeap[greatest] = temp;
			//max-heapify
			maxHeapify(greatest);
		}
	}
	public void removeValue(int index){
		
	}
}
