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

		
		JobScheduler js = new JobScheduler(LocalDate.now());
	}
}
