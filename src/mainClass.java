
import connector.SqliteConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.ConfigManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ck437
 */
public class mainClass {
    public static void main(String [] args) throws SQLException {
        ConfigManager config = new ConfigManager();
        config.load();
        SqliteConnection mainConn = new SqliteConnection();
        Connection conn = mainConn.connect(config.getProp("dbpath"));
       
        Statement statement = conn.createStatement();
        String query = "SELECT * FROM sqlite_master;";
        System.out.println("dd");
        ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println("Printing result...");
                String albumName = resultSet.getString(0);
                System.out.println("\tAlbum: " + albumName);
            } 
        
        
    }
    
}
