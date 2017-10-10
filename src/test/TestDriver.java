package test;

import util.*;
import java.sql.*;

import connector.JobDatabaseConnector;

public class TestDriver {
	public static void main(String[] args){
		JobDatabaseConnector jdc = new JobDatabaseConnector();

		ResultSet result = jdc.findJob("priority","<", 2);
		try{
		    ResultSetMetaData rsmd = result.getMetaData();
		    int columnsNumber = rsmd.getColumnCount();
		    while (result.next()) {
		        for (int i = 1; i <= columnsNumber; i++) {
		            if (i > 1) System.out.print(" | ");
		            System.out.print(result.getString(i));
		        }
		        System.out.println("");
		    }
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
