package test;
//new changes
import connector.JobDatabaseConnector;
import jobschedule.JobSchedulingBlock;
import util.*;
import java.sql.*;

public class TestDriver {
	public static void main(String[] args){
		JobSchedulingBlock jsb = new JobSchedulingBlock("sampleJobType",true,1,17);
	}

}
