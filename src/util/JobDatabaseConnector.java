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
	
	public void createJob(int jobID){
		
	}
	
	public void updateJobAttribute(String attrName, String newValue){
		
	}
	
	public void deleteJob(int jobID){
		
	}
	
	public void findJob(String attr1, String value){
		//search by status
		
	}
	public void findJob(String attr1, String value1, String attr2, double value2){
		//search by status and handicap or by status and priority
	}
	public void findJob(String attr1, String value1, String attr2, double){
		
	}
	public

}
