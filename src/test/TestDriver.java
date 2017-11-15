package test;
//new changes
import connector.JobDatabaseConnector;
import dashboards.Dashboard;
import dashboards.jobsDashboard2;
import dashboards.partsDashboard;
import jobschedule.*;
import java.util.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TestDriver {
	
	public static void main(String[] args){
		partsDashboard db = new partsDashboard();
		db.init();
	}

}
