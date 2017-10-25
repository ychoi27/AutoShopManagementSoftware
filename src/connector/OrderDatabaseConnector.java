package connector;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.ConfigManager;

public class OrderDatabaseConnector {
	private Connection conn;
    private ResultSet rs;
    private PreparedStatement pst;
    
    public OrderDatabaseConnector(){
        ConfigManager config = new ConfigManager();
        config.load();
        SqliteConnection mainConn = new SqliteConnection();
        conn = mainConn.connect(config.getProp("dbpath"));
        
    }
    public int createOrder(int partID, java.sql.Date arrivalDate){
		try{
			String q = "INSERT INTO orders (partID, arrivalDate) VALUES (?,?);";
			pst= conn.prepareStatement(q);
			pst.setInt(1, partID);
			pst.setDate(2, arrivalDate);
			int result= pst.executeUpdate();
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return -1000;
			//deal with exception
		}finally {
	        if(rs != null){
	             try{
	                  rs.close();
	             } catch(Exception e){
	                 e.printStackTrace();
	             }
	        }
	        if(pst != null){
	            try{
	                pst.close();
	            } catch(Exception e){
	                e.printStackTrace();
	            }
	        }
	    }
    }
	public int getMaxOrderID(){
		int max = 0;
		try{
			String q = "SELECT MAX(orderID) AS maxID FROM orders";
			pst = conn.prepareStatement(q);
			rs = pst.executeQuery();
			return rs.getInt("maxID");
		}catch(Exception e){
			e.printStackTrace();
		}finally {
	        if(rs != null){
	             try{
	                  rs.close();
	             } catch(Exception e){
	                 e.printStackTrace();
	             }
	        }
	        if(pst != null){
	            try{
	                pst.close();
	            } catch(Exception e){
	                e.printStackTrace();
	            }
	        }
	    }
		return max;	
	}
}
