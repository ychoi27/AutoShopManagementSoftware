
import connector.SqliteConnection;
import java.sql.SQLException;
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
        SqliteConnection conn = new SqliteConnection();
        try {
        conn.connect(config.getProp("dbpath"));
        conn.viewTable("db");
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        
        
    }
    
}
