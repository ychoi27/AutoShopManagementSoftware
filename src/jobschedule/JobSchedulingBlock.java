package jobschedule;
import connector.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Calendar;

public class JobSchedulingBlock {
	private JobDatabaseConnector jdc;
	private JobTypeConnector jtdc;
	private PartDatabaseConnector pdc;
	private String jobStatus;
	private int jobTypeID;//
	private double handicap;//
	private double priority;
	private boolean partsAvailable;//
	private int jobLength;//
	private int jobID;//
	private boolean walkIn;//
	private int mechanicID;
	private java.sql.Timestamp jobStartDateHour;
	private java.sql.Timestamp jobEndDateHour;
	
	
	public JobSchedulingBlock(String jobType, boolean walkIn, int clientID, int carID){
		//creates a job in the database and initializes the JobSchedulingBlock
		this.jdc = new JobDatabaseConnector();
		this.jtdc= new JobTypeConnector();
		this.pdc = new PartDatabaseConnector();
		this.jobID = jdc.getMaxJobID() + 1;
		this.jobTypeID = jtdc.getJobTypeID(jobType);
		this.handicap = 1;
		this.partsAvailable = pdc.checkPartAvailability(jobTypeID);
 		Calendar calendar = Calendar.getInstance();
		if(!partsAvailable){
			calendar.add(Calendar.DATE, 2);
		}
		jobStartDateHour= java.sql.Timestamp.valueOf(LocalDateTime.now());
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
		this.jdc = new JobDatabaseConnector();
		this.jtdc= new JobTypeConnector();
		this.pdc = new PartDatabaseConnector();
		
		try{
		ResultSet rs = jdc.findJob("jobID", "=", jobID);
		
		while(rs.next()){
			System.out.println(rs.getString("jobStatus"));
			this.jobStatus = rs.getString("jobStatus");
			this.handicap = rs.getDouble("handicap");
			this.priority = rs.getDouble("priority");
			this.partsAvailable = rs.getBoolean("partsAvailable");
			this.jobLength = rs.getInt("jobLength");
			this.jobID = rs.getInt("jobID");
			this.walkIn = rs.getBoolean("walkIn");
			this.mechanicID = rs.getInt("mechanicID");
			this.jobStartDateHour = rs.getTimestamp("jobStartDateHour");
			this.jobEndDateHour = rs.getTimestamp("jobEndDateHour");
		}
		
		}catch(Exception e){
			e.printStackTrace();
			System.exit(-1);
		}
		
		
	}
	
	private void generatePriority(){
		this.priority= 1/Math.pow(jobLength, 2) * Math.pow(handicap, 3) * (walkIn? 1:0.1) *  this.priority;
		
	}
	
	public double getPriority(){
		return this.priority;
	}
	
	public int getJobID(){
		return this.jobID;
	}
	
	public void setMechanic(int mechanicID){
		this.mechanicID=mechanicID;
	}
	
	
	
	

}
