/////////////////////////////////////////////////////////////////////////////
//This class provides an interface for the software to interact with the
//jobs database. Methods include: createJob, updateJob, deleteJob and 
//findJob.
//
//createJob(jobStatus, handicap, priority, partsAvailable, jobLength, jobID, 
//walkIn, clientID, carID)
//
//updateJob(jobID, attributeName, newValue)
//
//deleteJob(jobID)
//
//for String and boolean attributes (jobStatus, partsAvailable, walkIn): 
//findJob(attribute, value)
//
//for int and double boolean attributes (priority, handicap, jobLength, jobID,
//clientID, carID):
//findJob(attribute, comparator, value)
//////////////////////////////////////////////////////////////////////////////

package connector;

import connector.SqliteConnection;
import java.sql.*;
import util.ConfigManager;

public class JobDatabaseConnector {
	private Connection conn;
    private ResultSet rs;
    private PreparedStatement pst;
    
	public JobDatabaseConnector(){
        ConfigManager config = new ConfigManager();
        config.load();
        SqliteConnection mainConn = new SqliteConnection();
        conn = mainConn.connect(config.getProp("dbpath"));
	}
	public int createJob(String jobStatus, double handicap, double priority, boolean partsAvailable, int jobLength, int jobID, boolean walkIn, int clientID, int carID, int mechanicID, Date jobStartDateHour, Date jobEndDateHour){
		try{
			String q = "INSERT INTO jobs (jobStatus, handicap, priority, partsAvailable, jobLength, jobID, walkIn, clientID, carID, mechanicID, jobStartDateHour, jobEndDateHour) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
			pst= conn.prepareStatement(q);
			pst.setString(1, jobStatus);
			pst.setDouble(2, handicap);
			pst.setDouble(3, priority);
			pst.setBoolean(4, partsAvailable);
			pst.setInt(5, jobLength);
			pst.setInt(6, jobID);
			pst.setBoolean(7, walkIn);
			pst.setInt(8, clientID);
			pst.setInt(9, carID);
			pst.setInt(10, mechanicID);
			pst.setDate(11, jobStartDateHour);
			pst.setDate(12, jobEndDateHour);
			int result= pst.executeUpdate();
			return result;
		}catch(Exception e){
			return -1000;
			//deal with exception
		}
	}
	public int createJob(String jobStatus, double handicap, double priority, boolean partsAvailable, int jobLength, int jobID, boolean walkIn, int clientID, int carID ){
		try{
			String q = "INSERT INTO jobs (jobStatus, handicap, priority, partsAvailable, jobLength, jobID, walkIn, clientID, carID) VALUES (?,?,?,?,?,?,?,?,?);";
			pst= conn.prepareStatement(q);
			pst.setString(1, jobStatus);
			pst.setDouble(2, handicap);
			pst.setDouble(3, priority);
			pst.setBoolean(4, partsAvailable);
			pst.setInt(5, jobLength);
			pst.setInt(6, jobID);
			pst.setBoolean(7, walkIn);
			pst.setInt(8, clientID);
			pst.setInt(9, carID);
			int result= pst.executeUpdate();
			return result;
		}catch(Exception e){
			return -1000;
			//deal with exception
		}
		
	}
	
	public int updateJobAttribute(int jobID, String attrName, Object newValue){
		try{
			String q = "UPDATE jobs SET " + attrName + "=? WHERE jobID=?";
			pst= conn.prepareStatement(q);
			switch(attrName.toLowerCase()){
			case "jobstatus":
				String stringVal = newValue.toString();
				pst.setString(1, stringVal);
				break;
			case "handicap":
			case "priority":
				double doubleVal = new Double(newValue.toString());
				pst.setDouble(1, doubleVal);
				break;
			case "partsavailable":
			case "walkin":
				boolean boolVal = (boolean) newValue;
				pst.setBoolean(1, boolVal);
				break;
			case "joblength":
			case "jobid":
			case "clientid":
			case "carid":
				int intVal = (int) newValue;
				pst.setInt(1, intVal);
				break;
			default:
				return -1001;
			}
			pst.setInt(2, jobID);
			int result = pst.executeUpdate();
			return result;
		}catch(Exception e){
			//deal with exception
			e.printStackTrace();
			return -1000;
		}
		
		
	}
	
	public int deleteJob(int jobID){
		try{
			String q = "DELETE FROM jobs WHERE jobID=?";
			pst= conn.prepareStatement(q);
			pst.setInt(1, jobID);
			int result= pst.executeUpdate();
			return result;
		}catch(Exception e){
			return -1000;
			//deal with exception
		}
		
	}
	
	public ResultSet findJob(String attr, String value){
		//search by status
		try{
			String q = "SELECT * FROM jobs WHERE " + attr + "=?";
			pst = conn.prepareStatement(q);
			switch(attr.toLowerCase()){
			case "jobstatus":
				//Do SQL search for status value and set rs to result set;
				pst.setString(1, value);
				rs = pst.executeQuery();
				return rs;
			default:
				rs= null;
				return rs;
			
			}
		}catch(Exception e){
			e.printStackTrace();
			rs = null;
			return rs;
		}
	}
	
	public ResultSet findJob(String attr, String comparator, double value){
		//search handicap or priority. Must choose a comparator, eg "=", "<", etc.
		//IMPORTANT: double parameter must CLEARLY be a double (eg. 2.0, (double)2) or invalid results.
		try{
			String q = "SELECT * FROM jobs WHERE " + attr + comparator +"?";
			pst= conn.prepareStatement(q);
			switch(attr.toLowerCase()){
			case "handicap":
			case "priority":
				//DO SQL search for priority or handicap value and set rs to result set;
				pst.setDouble(1, value);
				rs = pst.executeQuery();
				return rs;
			default:
				rs= null;
				return rs;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			rs = null;
			return rs;
		}
	}
	
	public ResultSet findJob(String attr, boolean value){
		//search by partsAvailable or walkIn
		try{
			String q = "SELECT * FROM jobs WHERE " + attr + "=?";
			pst = conn.prepareStatement(q);
			switch(attr.toLowerCase()){
			case "partsavailable":
			case "walkin":
				//DO SQL search for walkIn or partsAvailable value and set rs to result set;
				pst.setBoolean(1, value);
				rs = pst.executeQuery();
				return rs;
			default:
				rs= null;
				return rs;
			
			}
		}catch(Exception e){
			e.printStackTrace();
			rs = null;
			return rs;
		}
		
	}
	
	public ResultSet findJob(String attr, String comparator, int value){
		//search by jobLength, jobID, clientID or carID. 
		try{
			String q = "SELECT * FROM jobs WHERE " + attr + comparator + "?";
			pst = conn.prepareStatement(q);
		switch(attr.toLowerCase()){
		case "joblength":
		case "jobid":
		case "clientid":
		case "carid":
		case "mechanicid":
			//Do SQL search for jobLength, jobID, clientID, carID, or mechanicID value and set rs to result set
			pst.setInt(1, value);
			rs = pst.executeQuery();
			return rs;
		default:
			rs= null;
			return rs;
			
		}
		}catch(Exception e){
			e.printStackTrace();
			rs= null;
			return rs;
		}
	}
	public ResultSet findJob(String attr, Date value){
		//search by jobLength, jobID, clientID or carID. 
		try{
			String q = "SELECT * FROM jobs WHERE " + attr + "=?";
			pst = conn.prepareStatement(q);
		switch(attr.toLowerCase()){
		case "jobstartdatehour":
		case "jobenddatehour":
			//Do SQL search for jobStartDateHour or jobEndDateHour value and set rs to result set
			pst.setDate(1, value);
			rs = pst.executeQuery();
			return rs;
		default:
			rs= null;
			return rs;
			
		}
		}catch(Exception e){
			e.printStackTrace();
			rs= null;
			return rs;
		}
	}

}