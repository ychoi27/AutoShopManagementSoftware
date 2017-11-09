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
    public int createOrder(int partID, java.sql.Date arrivalDate, int partQuantity){
		try{
			String q = "INSERT INTO orders (partID, arrivalDate, partQuantity) VALUES (?,?,?);";
			pst= conn.prepareStatement(q);
			pst.setInt(1, partID);
			pst.setDate(2, arrivalDate);
			pst.setInt(1, partQuantity);
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
    
    public void deleteOrder(int orderID){
    	try{
    	String q = "DELETE FROM orders WHERE orderID=?";
    	pst = conn.prepareStatement(q);
    	pst.setInt(1, orderID);
    	pst.executeQuery();
    	}catch(Exception e){}
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
	public ResultSet getAllOrders(){
		try{
			String q = "SELECT orders.orderID, orders.partID, parts.partsName, orders.arrivalDate FROM (orders INNER JOIN parts ON parts.parts_id = orders.partID);";
			pst = conn.prepareStatement(q);
			rs = pst.executeQuery();
			return rs;
		}catch(Exception e){
			e.printStackTrace();
			rs= null;
			return rs;
		}
	}
	public int getPartID(int orderID){
		try{
			String q = "SELECT partID FROM orders WHERE orderID=?";
			pst = conn.prepareStatement(q);
			pst.setInt(1, orderID);
			rs = pst.executeQuery();
			while(rs.next()){
				return rs.getInt("partID");
			}
		}catch(Exception e){}
		return 0;
	}
	public int getPartQuantity(int orderID){
		try{
			String q = "SELECT partQuantity FROM orders WHERE orderID=?";
			pst = conn.prepareStatement(q);
			pst.setInt(1, orderID);
			rs = pst.executeQuery();
			while(rs.next()){
				return rs.getInt("partQuantity");
			}
		}catch(Exception e){}
		return 0;
	}
}
