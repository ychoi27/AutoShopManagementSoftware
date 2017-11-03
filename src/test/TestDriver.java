package test;
//new changes
import connector.JobDatabaseConnector;
import jobschedule.*;
import java.util.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TestDriver {
	
	public static void main(String[] args){
		
		JobDatabaseConnector jdc = new JobDatabaseConnector();
		ResultSet rs = jdc.findJob("mechanicID", "=", 1);
		try{
		while(rs.next()){
			System.out.println(rs.getInt("jobID"));
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
