package connector;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

import util.ConfigManager;
import connector.*;

public class PartDatabaseConnector {
	private Connection conn;
    private ResultSet rs;
    private PreparedStatement pst;
    private JobTypeConnector jtdc;
    private OrderDatabaseConnector odc;
    
    public PartDatabaseConnector(){
        ConfigManager config = new ConfigManager();
        config.load();
        SqliteConnection mainConn = new SqliteConnection();
        conn = mainConn.connect(config.getProp("dbpath"));
        jtdc= new JobTypeConnector();
        odc= new OrderDatabaseConnector();
        
    }
    
    public boolean checkPartAvailability(int jobTypeID){
    	try{
    		int partID = jtdc.getPartID(jobTypeID);
    		int partsInStock = getPartQuantity(partID);
    		//GET NUMBER OF PARTS NEEDED FROM JOBTYPE TABLE
    		int partsNeeded = jtdc.getNumberOfParts(jobTypeID);
    		if(partsInStock-partsNeeded < 0){
    			Calendar calendar = Calendar.getInstance();
    			calendar.add(Calendar.DATE, 2);
    			java.sql.Date arrivalDate = new java.sql.Date(calendar.getTime().getTime());
    			odc.createOrder(partID, arrivalDate, partsNeeded);
    			return false;
    		
    		}else{
    			updatePartQuantity(partID, partsInStock-partsNeeded);
    			return true;
    		}
    	
    	}catch(Exception e){
    		e.printStackTrace();
    		return false;
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
    public int updatePartQuantity(int partID, int partsQuantity){
    	try{
    		String q = "UPDATE parts SET partsQuantity = ? WHERE parts_id=?";
    		pst = conn.prepareStatement(q);
    		pst.setInt(1, partsQuantity);
    		pst.setInt(2, partID);
    		return pst.executeUpdate();
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		return -1000;
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
	public ResultSet getAllParts(){
		try{
			String q = "SELECT parts_id, partsName, partsQuantity, parts_serial_number FROM parts";
			pst = conn.prepareStatement(q);
			rs = pst.executeQuery();
			return rs;
		}catch(Exception e){
			e.printStackTrace();
			rs= null;
			return rs;
		}
	}
    public int getPartQuantity(int partID){
    	int result=0;
    	try{
    		String q = "SELECT partsQuantity from parts WHERE parts_id=?";
    		pst = conn.prepareStatement(q);
    		pst.setInt(1, partID);
    		rs = pst.executeQuery();
    		while(rs.next()){
    			result= rs.getInt(1);
    		}
    		return result;
    	}catch(Exception e){
    		e.printStackTrace();
    		return -1000;
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
    
}
