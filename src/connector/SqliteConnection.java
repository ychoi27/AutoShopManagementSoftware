package connector;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import util.ConfigManager;
 
/**
 *
 * @author sqlitetutorial.net
 */
public class SqliteConnection {
    private Connection conn;
    
    public SqliteConnection()
    {
        this.conn = null;
    }   
    public Connection connect(String url) 
    {
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        return conn;
    }
    public void close()
    {
        try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
    }
    
    public void viewTable(String dbName)
    	    throws SQLException {

    	    Statement stmt = null;
    	    String query = 
    	    		"SELECT * FROM mechanic";
    	    try {
    	        stmt = conn.createStatement();
    	        ResultSet rs = stmt.executeQuery(query);
    	        while (rs.next()) {
    	            String name = rs.getString("id")+" "+rs.getString("firstname")+" "+rs.getString("lastname");
    	            System.out.println(name);
    	        }
    	    } catch (SQLException e ) {
    	        System.out.println(e.toString());
    	    } finally {
    	        if (stmt != null) { stmt.close(); }
    	    }
    	}
    public static void main(String[] args) throws SQLException {
        ConfigManager config = new ConfigManager();
        config.load();
        SqliteConnection conn = new SqliteConnection();
        conn.connect(config.getProp("dbpath"));
        conn.viewTable("db");
    }
}