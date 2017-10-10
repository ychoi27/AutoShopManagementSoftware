package util;

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
		switch(attr.toLowerCase()){
		case "partsavailable":
			//Do SQL search for partsAvailable value and set rs to result set;
			return rs;
		case "walkin":
			//DO SQL search for walkIn value and set rs to result set;
			return rs;
		default:
			rs= null;
			return rs;
			
		}
		
	}
	
	public ResultSet findJob(String attr, int value){
		//search by jobLength, jobID, clientID or carID
		switch(attr.toLowerCase()){
		case "joblength":
			//Do SQL search for jobLength value and set rs to result set;
			return rs;
		case "jobid":
			//Do SQL search for jobID value and set rs to result set;
			return rs;
		case "clientid":
			//Do SQL search for clientID value and set rs to result set;
			return rs;
		case "carid":
			//Do SQL search for carID value and set rs to result set
			return rs;
		default:
			rs= null;
			return rs;
			
		}
	}

}
