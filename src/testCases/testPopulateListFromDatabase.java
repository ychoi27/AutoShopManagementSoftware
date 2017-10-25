/*This test passes if populateListFromDatabase returns an array containing n
 * job scheduling blocks, all of which have the "active" job status. Notation:
 * t=total_number_of_jobs and n=total_number_of_active_jobs. !!!THERE MAY NOT BE
 * MORE THAN 5 ACTIVE JOBS AT A SINGLE TIME. That is, your chosen n MUST BE <=5.
 * 
 * The jobsList maxHeap of the JobScheduler should also be populated with t-n job
 * scheduling blocks, all of which have the "ready" status. Before running this
 * program, make sure the jobs table of the database contains n instances that
 * have jobStatus=active and t-n instances where jobStatus=ready. Example, if n=5 and
 * t=10:
 * format:{jobStatus, handicap, priority, partsAvailable, jobLength, jobID, walkIn, clientID, carID, mechanicID, jobStartDateHour, jobEndDateHour, jobTypeID, datePartsIn}
 * remark: '?' indicates that value does not matter.
 * {active, 1, 0.1111111111111111, 1, 3, 1, 1, 1, 17, ?, ?, 1, ?}
 * {ready, 1, 0.1111111111111111, 1, 3, 2, 1, 1, 17, ?, ?, 1, ?}
 * {active, 1, 0.1111111111111111, 1, 3, 3, 1, 1, 17, ?, ?, 1, ?}
 * {active, 1, 0.1111111111111111, 1, 3, 4, 1, 1, 17, ?, ?, 1, ?}
 * {active, 1, 0.1111111111111111, 1, 3, 5, 1, 1, 17, ?, ?, 1, ?}
 * {active, 1, 0.1111111111111111, 1, 3, 6, 1, 1, 17, ?, ?, 1, ?}
 * {ready, 1, 0.1111111111111111, 1, 3, 7, 1, 1, 17, ?, ?, 1, ?}
 * {ready, 1, 0.1111111111111111, 1, 3, 8, 1, 1, 17, ?, ?, 1, ?}
 * {ready, 1, 0.1111111111111111, 1, 3, 9, 1, 1, 17, ?, ?, 1, ?}
 * {ready, 1, 0.1111111111111111, 1, 3, 10, 1, 1, 17, ?, ?, 1, ?}
 * */
package testCases;
import java.time.LocalDate;
import java.util.*;

import jobschedule.*;

public class testPopulateListFromDatabase {
	public static void main(String args[]){
		JobScheduler js = new JobScheduler(LocalDate.now());
		JobSchedulingBlock[] testActive = js.populateListFromDatabase();
		Scanner console = new Scanner(System.in);
		int n;
		int t;
		
		System.out.println("Enter number of active jobs in databse (must be <= 5!!)");
		n = console.nextInt();
		System.out.println("Enter total number of jobs in database");
		t = console.nextInt();
		boolean activeArrayPass = true;
		for(int i = 0; i<n; i++){
			if(testActive[i] == null){
				System.out.println("active job was improperly classified.");
				activeArrayPass = false;
			}
			if(!testActive[i].getJobStatus().equals("active")){
				System.out.println("A job that isn't active was classified as active.");
				activeArrayPass = false;
			}
		}
		if(activeArrayPass){
			System.out.println("Active array PASS. All all active jobs were properly classified.");
		}else{
			System.out.println("Active array FAIL.");
		}
		
		int numberJobsInList=0;
		int expectedNumberJobsInList = t-n;
		boolean jobsListPass = true;
		while(js.jobsList.peekMax()!=null){
			numberJobsInList++;
			if(js.jobsList.getMax().getJobStatus().equals("active")){
				System.out.println("active job misclassified in jobsList");
				jobsListPass=false;
			}
		}
		if(numberJobsInList != expectedNumberJobsInList){
			System.out.println("Unexpected number of jobs in jobsList");
			jobsListPass = false;
		}
		if(jobsListPass){
			System.out.println("jobs list PASS");
		}else{
			System.out.println("jobs list FAIL");
		}
		
		if(activeArrayPass && jobsListPass)
		{		
			System.out.println("TEST PASS");
		}else{
			System.out.println("TEST FAIL");
		}
	}

}
