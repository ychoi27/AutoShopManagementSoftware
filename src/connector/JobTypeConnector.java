package connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.ConfigManager;

public class JobTypeConnector {
	private Connection conn;
    private ResultSet rs;
    private PreparedStatement pst;
    
	public JobTypeConnector(){
        ConfigManager config = new ConfigManager();
        config.load();
        SqliteConnection mainConn = new SqliteConnection();
        conn = mainConn.connect(config.getProp("dbpath"));
	}
	public int getJobTypeID(String jobName){
		int result=-100;
		try{
		String q = "SELECT jobTypeID FROM jobTypes WHERE jobType=?";
		pst= conn.prepareStatement(q);
		pst.setString(1, jobName);
		rs=pst.executeQuery();
		while(rs.next()){
			result = rs.getInt(1);
		}
		return result;

		}catch(Exception e){
			rs=null;
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
		
		public String getJobType(int jobTypeID){
			String result="";
			try{
			String q = "SELECT jobType FROM jobTypes WHERE jobTypeID=?";
			pst= conn.prepareStatement(q);
			pst.setInt(1, jobTypeID);
			rs=pst.executeQuery();
			while(rs.next()){
				result= rs.getString(1);
			}
			return result;
			}catch(Exception e){
				rs=null;
				return "";
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
	
	public int getJobLength(int jobTypeID){
		int result=-100;
		try{
		String q = "SELECT jobLength FROM jobTypes WHERE partID=?";
		pst= conn.prepareStatement(q);
		pst.setInt(1, jobTypeID);
		rs=pst.executeQuery();
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
	
	public int getNumberOfParts(int jobTypeID){
		int result = 0;
		try{
		String q = "SELECT numberOfParts FROM jobTypes WHERE partID=?";
		pst= conn.prepareStatement(q);
		pst.setInt(1, jobTypeID);
		rs=pst.executeQuery();
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
	public int getPartID(int jobTypeID){
		int result = 0;
		try{
			String q = "SELECT partID FROM jobTypes WHERE jobTypeID=?";
			pst= conn.prepareStatement(q);
			pst.setInt(1, jobTypeID);
			rs=pst.executeQuery();
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
