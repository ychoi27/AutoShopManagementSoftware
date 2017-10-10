package test;

import util.*;
import java.sql.*;

import connector.JobDatabaseConnector;

public class TestDriver {
	public static void main(String[] args){
		JobDatabaseConnector jdc = new JobDatabaseConnector();
		System.out.println(jdc.getMaxJobID());

	}

}
