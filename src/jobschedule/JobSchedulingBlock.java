package jobschedule;
import connector.*;
import java.sql.*;

public class JobSchedulingBlock {
	private JobDatabaseConnector jdc;
	private String jobStatus;
	private double handicap;
	private double priority;
	private boolean partsAvailable;
	private int jobLength;
	private int jobID;
	private boolean walkIn;
	private int mechanicID;
	private Date jobStartDateHour;
	private Date jobEndDateHour;
	
	
	public JobSchedulingBlock(){
		//creates a job in the database and initializes the JobSchedulingBlock
		jdc= new JobDatabaseConnector();
		
		this.jobID = jdc.getMaxJobID() + 1;
		this.handicap = 1;
		
		
	}
	
	public JobSchedulingBlock(int jobID){
		//initializes the JobSchedulingBlock for an existing job in the database
		jdc= new JobDatabaseConnector();
		try{
		ResultSet rs = jdc.findJob("jobID", "=", jobID);
		
		this.jobStatus = rs.getString("jobID");
		this.handicap = rs.getDouble("handicap");
		this.priority = rs.getDouble("priority");
		this.partsAvailable = rs.getBoolean("partsAvailable");
		this.jobLength = rs.getInt("jobLength");
		this.jobID = rs.getInt("jobID");
		this.walkIn = rs.getBoolean("walkIn");
		this.mechanicID = rs.getInt("mechanicID");
		this.jobStartDateHour = rs.getDate("jobStartDateHour");
		this.jobEndDateHour = rs.getDate("jobEndDateHour");
		
		}catch(Exception e){
			e.printStackTrace();
			System.exit(-1);
		}
		
		
	}
	
	
	
	

}
