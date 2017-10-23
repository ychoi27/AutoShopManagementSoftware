/////////////////////////////////////////////////////////////////////////////
//This class provides an interface for the software to interact with the
//jobs database. Methods include: createJob, updateJob, deleteJob and 
//findJob.
//
//createJob(jobStatus, handicap, priority, partsAvailable, jobLength, jobID, 
//walkIn, clientID, carID)
//
//createJob(jobStatus, handicap, priority, partsAvailable, jobLength, jobID, 
//walkIn, clientID, carID, mechanicID, jobStartDateHour, jobEndDateHour)
//
//updateJob(jobID, attributeName, newValue)
//
//deleteJob(jobID)
//
//for String and boolean attributes (jobStatus, partsAvailable, walkIn): 
//findJob(attribute, value)
//
//for int and double boolean attributes (priority, handicap, jobLength, jobID,
//clientID, carID):
//findJob(attribute, comparator, value)
//////////////////////////////////////////////////////////////////////////////

package connector;

import connector.SqliteConnection;
import java.sql.*;
import util.ConfigManager;

public class JobDatabaseConnector {
	private Connection conn;
    private ResultSet rs;
    private PreparedStatement pst;
    
	public JobDatabaseConnector(){
        ConfigManager config = new ConfigManager();
        config.load();
        SqliteConnection mainConn = new SqliteConnection();
        conn = mainConn.connect(config.getProp("dbpath"));
	}
	public int createJob(String jobStatus, int jobTypeID, double handicap, double priority, boolean partsAvailable, int jobLength, int jobID, boolean walkIn, int clientID, int carID, int mechanicID, java.sql.Timestamp jobStartDateHour, java.sql.Timestamp jobEndDateHour){
		try{
			String q = "INSERT INTO jobs (jobStatus, jobTypeID, handicap, priority, partsAvailable, jobLength, jobID, walkIn, clientID, carID, mechanicID, jobStartDateHour, jobEndDateHour) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
			pst= conn.prepareStatement(q);
			pst.setString(1, jobStatus);
			pst.setInt(2, jobTypeID);
			pst.setDouble(3, handicap);
			pst.setDouble(4, priority);
			pst.setBoolean(5, partsAvailable);
			pst.setInt(6, jobLength);
			pst.setInt(7, jobID);
			pst.setBoolean(8, walkIn);
			pst.setInt(9, clientID);
			pst.setInt(10, carID);
			pst.setInt(11, mechanicID);
			pst.setTimestamp(12, jobStartDateHour);
			pst.setTimestamp(13, jobEndDateHour);
			int result= pst.executeUpdate();
			return result;
		}catch(Exception e){
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
	public int createJob(String jobStatus, int jobTypeID, double handicap, double priority, boolean partsAvailable, int jobLength, int jobID, boolean walkIn, int clientID, int carID ){
		try{
			String q = "INSERT INTO jobs (jobStatus, jobTypeID, handicap, priority, partsAvailable, jobLength, jobID, walkIn, clientID, carID) VALUES (?,?,?,?,?,?,?,?,?,?);";
			pst= conn.prepareStatement(q);
			pst.setString(1, jobStatus);
			pst.setInt(2, jobTypeID);
			pst.setDouble(3, handicap);
			pst.setDouble(4, priority);
			pst.setBoolean(5, partsAvailable);
			pst.setInt(6, jobLength);
			pst.setInt(7, jobID);
			pst.setBoolean(8, walkIn);
			pst.setInt(9, clientID);
			pst.setInt(10, carID);
			int result= pst.executeUpdate();
			return result;
		}catch(Exception e){
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
	
	public int updateJobAttribute(int jobID, String attrName, Object newValue){
		try{
			String q = "UPDATE jobs SET " + attrName + "=? WHERE jobID=?";
			pst= conn.prepareStatement(q);
			switch(attrName.toLowerCase()){
			case "jobtype":
			case "jobstatus":
				String stringVal = newValue.toString();
				pst.setString(1, stringVal);
				break;
			case "handicap":
			case "priority":
				double doubleVal = new Double(newValue.toString());
				pst.setDouble(1, doubleVal);
				break;
			case "partsavailable":
			case "walkin":
				boolean boolVal = (boolean) newValue;
				pst.setBoolean(1, boolVal);
				break;
			case "joblength":
			case "jobid":
			case "clientid":
			case "carid":
			case "mechanicid":
				int intVal = (int) newValue;
				pst.setInt(1, intVal);
				break;
			case "jobstartdatehour":
			case "jobenddatehour":
				java.sql.Timestamp timeVal = (Timestamp) newValue;
				pst.setTimestamp(1, timeVal);
				break;
			default:
				return -1000;
			}
			pst.setInt(2, jobID);
			int result = pst.executeUpdate();
			return result;
		}catch(Exception e){
			//deal with exception
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
	
	public int deleteJob(int jobID){
		try{
			String q = "DELETE FROM jobs WHERE jobID=?";
			pst= conn.prepareStatement(q);
			pst.setInt(1, jobID);
			int result= pst.executeUpdate();
			return result;
		}catch(Exception e){
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
	public ResultSet getAllJobsByID(){
		try{
			String q = "SELECT jobID FROM jobs";
			pst = conn.prepareStatement(q);
			rs=pst.executeQuery();
			return rs;
		}catch(Exception e){
			e.printStackTrace();
			rs=null;
			return rs;
		}
	}
	
	public ResultSet findJob(String attr, String value){
		//search by status
		try{
			String q = "SELECT * FROM jobs WHERE " + attr + "=?";
			pst = conn.prepareStatement(q);
			switch(attr.toLowerCase()){
			case "jobtype":
			case "jobstatus":
				//Do SQL search for status value and set rs to result set;
				pst.setString(1, value);
				rs = pst.executeQuery();
				return rs;
			default:
				rs= null;
				return rs;
			
			}
		}catch(Exception e){
			e.printStackTrace();
			rs = null;
			return rs;
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
	
	public ResultSet findJob(String attr, String comparator, double value){
		//search handicap or priority. Must choose a comparator, eg "=", "<", etc.
		//IMPORTANT: double parameter must CLEARLY be a double (eg. 2.0, (double)2) or invalid results.
		try{
			String q = "SELECT * FROM jobs WHERE " + attr + comparator +"?";
			pst= conn.prepareStatement(q);
			switch(attr.toLowerCase()){
			case "handicap":
			case "priority":
				//DO SQL search for priority or handicap value and set rs to result set;
				pst.setDouble(1, value);
				rs = pst.executeQuery();
				return rs;
			default:
				rs= null;
				return rs;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			rs = null;
			return rs;
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
	
	public ResultSet findJob(String attr, boolean value){
		//search by partsAvailable or walkIn
		try{
			String q = "SELECT * FROM jobs WHERE " + attr + "=?";
			pst = conn.prepareStatement(q);
			switch(attr.toLowerCase()){
			case "partsavailable":
			case "walkin":
				//DO SQL search for walkIn or partsAvailable value and set rs to result set;
				pst.setBoolean(1, value);
				rs = pst.executeQuery();
				return rs;
			default:
				rs= null;
				return rs;
			
			}
		}catch(Exception e){
			e.printStackTrace();
			rs = null;
			return rs;
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
	
	public ResultSet findJob(String attr, String comparator, int value){
		//search by jobLength, jobID, clientID or carID. 
		try{
			String q = "SELECT * FROM jobs WHERE " + attr + comparator + "?";
			pst = conn.prepareStatement(q);
		switch(attr.toLowerCase()){
		case "joblength":
		case "jobid":
		case "clientid":
		case "carid":
		case "mechanicid":
			//Do SQL search for jobLength, jobID, clientID, carID, or mechanicID value and set rs to result set
			pst.setInt(1, value);
			rs = pst.executeQuery();
			return rs;
		default:
			rs= null;
			return rs;
			
		}
		}catch(Exception e){
			e.printStackTrace();
			rs= null;
			return rs;
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
	public ResultSet findJob(String attr, java.sql.Timestamp value){
		//search by jobLength, jobID, clientID or carID. 
		try{
			String q = "SELECT * FROM jobs WHERE " + attr + "=?";
			pst = conn.prepareStatement(q);
			switch(attr.toLowerCase()){
			case "jobstartdatehour":
			case "jobenddatehour":
				//Do SQL search for jobStartDateHour or jobEndDateHour value and set rs to result set
				pst.setTimestamp(1, value);
				rs = pst.executeQuery();
				return rs;
			default:
				rs= null;
				return rs;
			
			}
		}catch(Exception e){
			e.printStackTrace();
			rs= null;
			return rs;
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
	public int getMaxJobID(){
		int max = 0;
		try{
			String q = "SELECT MAX(jobID) AS maxID FROM jobs";
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