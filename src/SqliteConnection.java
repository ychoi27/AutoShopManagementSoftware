import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
/**
 *
 * @author sqlitetutorial.net
 */
public class SqliteConnection {
     /**
     * Connect to a sample database
     */
    public static Connection connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:D:/SoftwareEngineeringClass/AutoShopManagementSoftware/database/db.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                	conn.isClosed();
                    //conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return conn;
    }
    /**
     * @param args the command line arguments
     */
    public static void viewTable(Connection con, String dbName)
    	    throws SQLException {

    	    Statement stmt = null;
    	    String query = 
    	    		"SELECT * FROM mechanic";
    	    try {
    	        stmt = con.createStatement();
    	        ResultSet rs = stmt.executeQuery(query);
    	        while (rs.next()) {
    	            String name = rs.getString("id");
    	            System.out.println(name);
    	        }
    	    } catch (SQLException e ) {
    	        System.out.println(e.toString());
    	    } finally {
    	        if (stmt != null) { stmt.close(); }
    	    }
    	}
    public static void main(String[] args) throws SQLException {
        viewTable(connect(),"db");
    }
}