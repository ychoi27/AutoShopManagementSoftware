package test;
//new changes
import connector.JobDatabaseConnector;
import jobschedule.*;
import util.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TestDriver {
	public static void main(String[] args){
		JobScheduler js = new JobScheduler();
		JobSchedulingBlock job = new JobSchedulingBlock("sampleJobType", false, 1, 17);
		js.scheduleJob(job);
	}
}
