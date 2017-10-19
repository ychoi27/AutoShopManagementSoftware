package test;
//new changes
import connector.JobDatabaseConnector;
import jobschedule.JobSchedulingBlock;
import util.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TestDriver {
	public static void main(String[] args){
		LocalDateTime ldt = LocalDateTime.now();
		java.sql.Timestamp date = java.sql.Timestamp.valueOf(ldt);
		System.out.println(ldt);
		System.out.println(date);
		
		JobDatabaseConnector jdc = new JobDatabaseConnector();
		jdc.updateJobAttribute(2, "jobEndDateHour", java.sql.Timestamp.valueOf(LocalDateTime.now().plusHours(3)));
	}
}
