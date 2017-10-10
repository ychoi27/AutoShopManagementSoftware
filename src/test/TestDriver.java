package test;

import util.*;
import java.sql.*;

public class TestDriver {
	public static void main(String[] args){
		JobDatabaseConnector jdc = new JobDatabaseConnector();
		int result = jdc.updateJobAttribute(1, "walkIn", true);
		System.out.println(result);
	}

}
