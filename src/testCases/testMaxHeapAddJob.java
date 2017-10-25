/*This test passes if after adding any number of job, the max heap property is maintained.
 * That is, the priority of the node at each position is >= to priority of its
 * children nodes.*/
package testCases;

import java.util.*;
import jobschedule.*;

public class testMaxHeapAddJob {
	public static void main(String[] args){
		int numberOfJobs;
		Scanner console = new Scanner(System.in);
		System.out.println("Enter number of jobs to add to the MaxHeap:");
		numberOfJobs = console.nextInt();
		JobSchedulingBlock[] jsbGroup = new JobSchedulingBlock[numberOfJobs];
		for(int i = 0; i < numberOfJobs; i++){
			System.out.println("Enter job type (1, 2 or 3)");
			int jobTypeInt = console.nextInt();
			String jobType;
			switch (jobTypeInt){
			case 1:
				jobType = "sampleJobType";
				break;
			case 2:
				jobType = "sampleJobType2";
				break;
			case 3:
				jobType = "sampleJobType3";
				break;
			default:
				System.out.println("invalid input.");
				jobType="";
				System.exit(1);
			}
			System.out.println("Enter walk in (t or f)");
			String walkInStr = console.next();
			boolean walkIn;
			switch (walkInStr){
			case "t":
				walkIn = true;
				break;
			case "f":
				walkIn = false;
				break;
			default:
				System.out.println("invalid input.");
				walkIn=false;
				System.exit(1);
			}
			int clientID=1;
			int carID=17;
			
			jsbGroup[i] = new JobSchedulingBlock(jobType, walkIn, clientID, carID);
		}
		
		MaxHeap heap = new MaxHeap();
		for(JobSchedulingBlock jsb : jsbGroup){
			heap.addJob(jsb);
		}
		//test max heap property
		boolean maxHeapPass = true;
		if(heap.peekMax() != null){
			double lastPriority  = heap.getMax().getPriority();
			while(!heap.isEmpty()){
				double currentPriority = heap.getMax().getPriority();
				if(lastPriority < currentPriority){
					System.out.println("Max heap FAIL: " + lastPriority + " < " + currentPriority);
					maxHeapPass = false;
				}
				lastPriority=currentPriority;
			}
		}
		if(maxHeapPass){
			System.out.println("Max Heap PASS: Max Heap property properly maintained");
		}
	}
	
	
}
