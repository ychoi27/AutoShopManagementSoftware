package jobschedule;
import connector.*;
import java.sql.*;
import java.util.Calendar;

public class JobSchedulingBlock {
	private JobDatabaseConnector jdc=new JobDatabaseConnector();
	private JobTypeConnector jtdc= new JobTypeConnector();
	private PartDatabaseConnector pdc = new PartDatabaseConnector();
	private String jobStatus;
	private int jobTypeID;//
	private double handicap;//
	private double priority;
	private boolean partsAvailable;//
	private int jobLength;//
	private int jobID;//
	private boolean walkIn;//
	private int mechanicID;
	private Date jobStartDateHour;
	private Date jobEndDateHour;
	
	
	public JobSchedulingBlock(String jobType, boolean walkIn, int clientID, int carID){
		//creates a job in the database and initializes the JobSchedulingBlock
		jdc= new JobDatabaseConnector();
		
		this.jobID = jdc.getMaxJobID() + 1;
		this.jobTypeID = jtdc.getJobTypeID(jobType);
		this.handicap = 1;
		this.partsAvailable = pdc.checkPartAvailability(jobTypeID);
 		Calendar calendar = Calendar.getInstance();
		if(!partsAvailable){
			calendar.add(Calendar.DATE, 2);
		}
		jobStartDateHour= new java.sql.Date(calendar.getTime().getTime());
		this.jobStatus= (partsAvailable? "ready":"waiting");
		this.jobLength = jtdc.getJobLength(jobTypeID);
		this.walkIn = walkIn;
		this.priority= 1;
		generatePriority();
		jdc.createJob(this.jobStatus, this.jobTypeID, this.handicap, this.priority, this.partsAvailable, this.jobLength, this.jobID, this.walkIn, clientID, carID);
		jdc.updateJobAttribute(this.jobID, "jobStartDateHour", this.jobStartDateHour);
		
		
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
	
	private void generatePriority(){
		this.priority= 1/Math.pow(jobLength, 2) * Math.pow(handicap, 3) * (walkIn? 1:0.1) *  this.priority;
		
		
		
	}
	
	
	
	

}
