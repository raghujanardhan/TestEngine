package testengine.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import testengine.beans.BatchInfo;
import testengine.beans.ModuleInfo;
import testengine.beans.SubjectInfo;
import testengine.beans.UserInfo;
import testengine.utility.ConnectionFactory;
import testengine.utility.Suppliments;

public class UserImpl implements UserInterface{
	
	private static final String SAVE_USER_QRY = "insert into testengine_users (user_name,name,email,phone,password,last_upd_time,last_upd_user,modules_assigned) values(?,?,?,?,?,?,?,?)";
	private static final String LIST_USERS_QRY = "select SQL_CALC_FOUND_ROWS * from testengine_users limit ?,?";
	private static final String LIST_MODULES_QRY = "SELECT * FROM modules";
	private static final String DEL_USER_QRY = "delete from testengine_users where user_id = ?";
	private static final String LOAD_USER_QRY = "SELECT * FROM testengine_users where user_id = ?";
	private static final String EDIT_USER_QRY = "UPDATE TESTENGINE_USERS SET USER_NAME=?,NAME=?,EMAIL=?,PHONE=?,PASSWORD=?,LAST_UPD_TIME=?,LAST_UPD_USER=?,MODULES_ASSIGNED=? WHERE USER_ID=?";
	private static final String SEARCH_USER_QRY = "select SQL_CALC_FOUND_ROWS * FROM testengine_users where user_id= ? or user_name = ? limit ?,? ";
	
	Connection connection;
    PreparedStatement stmt;
    ResultSet rs;
    int noOfRecords;
	
    public int saveUser(UserInfo userInfo) {
    	int userCreated = 0;
		Calendar calendar = Calendar.getInstance();
			try
			{
				connection = getConnection();
				stmt = connection.prepareStatement(SAVE_USER_QRY);				
				stmt.setString(1, userInfo.getUserName());
				stmt.setString(2, userInfo.getName());
				stmt.setString(3, userInfo.getEmail());
				stmt.setString(4, userInfo.getMobile());
				stmt.setString(5, "user123");
				stmt.setLong(6, calendar.getTimeInMillis());
				stmt.setString(7, "admin");
				stmt.setString(8, userInfo.getModulesSelected());
				
				userCreated = stmt.executeUpdate();
				
			}
			catch(Exception e){
				System.out.println("Exception in creating User->"+e);
			}
			finally
			{
				try
				{
					closeConnection(connection,null);
					stmt.close();
				}
					
				catch(Exception e){
					System.out.println("Exception in creating User->"+e);
				}
			}
		return userCreated;
	}
    public ArrayList<UserInfo> listUsers(int offset, int noOfRecords) {
    	ArrayList<UserInfo> usersList = new ArrayList<UserInfo>();
		
		try
		{
			connection = getConnection();
			stmt = connection.prepareStatement(LIST_USERS_QRY);
			stmt.setInt(1, offset);
			stmt.setInt(2, noOfRecords);
			rs = stmt.executeQuery();
			while(rs.next()) {
				
				UserInfo userInfo = new UserInfo();
				userInfo.setUserId(rs.getInt("user_id"));
				userInfo.setUserName(rs.getString("user_name"));
				userInfo.setName(rs.getString("name"));
				userInfo.setEmail(rs.getString("email"));
				userInfo.setMobile(rs.getString("phone"));
				userInfo.setModulesSelected(rs.getString("modules_assigned"));
				usersList.add(userInfo);
			}
		}
		catch(Exception e) {
			System.out.println("Exception in load users.."+e);
		}
		finally
		{
			try
			{
			closeConnection(connection,rs);
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
		return usersList;
	}
    public ArrayList<ModuleInfo> getAllModules(){

    	ArrayList<ModuleInfo> moduleList = new ArrayList<ModuleInfo>();
		
		try
		{
			connection = getConnection();
			stmt = connection.prepareStatement(LIST_MODULES_QRY);
			rs = stmt.executeQuery();
			while(rs.next()) {
				
				ModuleInfo moduleInfo = new ModuleInfo();
				moduleInfo.setModuleId(rs.getInt("module_id"));
				moduleInfo.setModuleName(rs.getString("module_name"));
				moduleInfo.setModuleDesc(rs.getString("module_desc"));	
				moduleList.add(moduleInfo);
				
			}
		}
		catch(Exception e) {
			System.out.println("Exception in load modules.."+e);
		}
		finally
		{
			try
			{
			closeConnection(connection,rs);
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
		return moduleList;
	
    	
    }
    public int deleteUser(String userId) {
		
		 
		int delete = 0;
		PreparedStatement stmt=null;
		try {
			
	           connection = getConnection();
	           stmt = connection.prepareStatement(DEL_USER_QRY);
	           stmt.setString(1, userId);
	           delete = stmt.executeUpdate();
	           
		}
		catch(Exception e) {
			
			e.printStackTrace();
			
		}
		finally{
			try
			{
				closeConnection1(connection, stmt);
			}
			catch(SQLException s){
				
				
			}
		}
		return delete;
		
	}
    public ArrayList<UserInfo> loadUser(String userId) {
    	

    	ArrayList<UserInfo> usersList = new ArrayList<UserInfo>();
		
		try
		{
			connection = getConnection();
			stmt = connection.prepareStatement(LOAD_USER_QRY);
			stmt.setInt(1, Integer.parseInt(userId));
			rs = stmt.executeQuery();
			while(rs.next()) {
				
				UserInfo userInfo = new UserInfo();
				userInfo.setUserId(rs.getInt("user_id"));
				userInfo.setUserName(rs.getString("user_name"));
				userInfo.setName(rs.getString("name"));
				userInfo.setEmail(rs.getString("email"));
				userInfo.setMobile(rs.getString("phone"));
				userInfo.setModulesSelected(rs.getString("modules_assigned"));
				usersList.add(userInfo);
			}
		}
		catch(Exception e) {
			System.out.println("Exception in load users.."+e);
		}
		finally
		{
			try
			{
			closeConnection(connection,rs);
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
		return usersList;
	
    	
    }
    public int editUser(UserInfo userInfo) {
    	int userUpdated = 0;
		Calendar calendar = Calendar.getInstance();
			try
			{
				connection = getConnection();
				stmt = connection.prepareStatement(EDIT_USER_QRY);				
				stmt.setString(1, userInfo.getUserName());
				stmt.setString(2, userInfo.getName());
				stmt.setString(3, userInfo.getEmail());
				stmt.setString(4, userInfo.getMobile());
				stmt.setString(5, "user123");
				stmt.setLong(6, calendar.getTimeInMillis());
				stmt.setString(7, "admin");
				stmt.setString(8, userInfo.getModulesSelected());
				stmt.setInt(9, userInfo.getUserId());
				
				userUpdated = stmt.executeUpdate();
				
			}
			catch(Exception e){
				System.out.println("Exception in creating User->"+e);
			}
			finally
			{
				try
				{
					closeConnection(connection,null);
					stmt.close();
				}
					
				catch(Exception e){
					System.out.println("Exception in creating User->"+e);
				}
			}
		return userUpdated;
	}
    
    public int getNumOfRecords() {
		ResultSet rs=null;
		Statement stmt;
		try {
	           connection = getConnection();
	           stmt = connection.createStatement();
	            rs = stmt.executeQuery("SELECT count(*) as rowcount FROM testengine_users ");
	            if(rs.next()){
		        	   System.out.println("Entering this");
		               this.noOfRecords = rs.getInt("rowcount");		             		        	   
		           }
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		finally
		{
			try {
	               closeConnection(connection, rs);
	               } catch (SQLException e) {
	               e.printStackTrace();
	           }
		}
		return this.noOfRecords;
	} 
    public ArrayList<UserInfo> searchUsers(String searchParam , int offset, int noOfRecords) {
    	ArrayList<UserInfo> usersList = new ArrayList<UserInfo>();
		
		try
		{
			connection = getConnection();
			stmt = connection.prepareStatement(SEARCH_USER_QRY);
			stmt.setString(1, searchParam);
			stmt.setString(2, searchParam);
			stmt.setInt(3, offset);
			stmt.setInt(4, noOfRecords);
			rs = stmt.executeQuery();
			while(rs.next()) {
				
				UserInfo userInfo = new UserInfo();
				userInfo.setUserId(rs.getInt("user_id"));
				userInfo.setUserName(rs.getString("user_name"));
				userInfo.setName(rs.getString("name"));
				userInfo.setEmail(rs.getString("email"));
				userInfo.setMobile(rs.getString("phone"));
				userInfo.setModulesSelected(rs.getString("modules_assigned"));
				usersList.add(userInfo);
			}
		}
		catch(Exception e) {
			System.out.println("Exception in Search users.."+e);
		}
		finally
		{
			try
			{
			closeConnection(connection,rs);
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
		return usersList;
	}

    public int getNumOfRecordsForSearch(String searchParam) {
		ResultSet rs=null;
		Statement stmt;
		try {
	           connection = getConnection();
	           stmt = connection.createStatement();
	           
	           String usersAfterSearchCount = "SELECT count(*) as rowcount FROM testengine_users where user_id = '"+searchParam+"' or user_name = '"+searchParam+"'";
	            rs = stmt.executeQuery(usersAfterSearchCount);
	            if(rs.next()){
		               this.noOfRecords = rs.getInt("rowcount");		             		        	   
		           }
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		finally
		{
			try {
	               closeConnection(connection, rs);
	               } catch (SQLException e) {
	               e.printStackTrace();
	           }
		}
		return this.noOfRecords;
	}
    
    
    
	private static Connection getConnection() throws SQLException, ClassNotFoundException 
	{
	    Connection con = ConnectionFactory.getInstance().getConnection();
	    return con;
	}
	
	private void closeConnection(Connection con, ResultSet rs) throws SQLException{
		// TODO Auto-generated method stub
		con.close();
		rs.close();
	}
	private void closeConnection1(Connection con, PreparedStatement stat) throws SQLException{
		// TODO Auto-generated method stub
		con.close();
		stat.close();
	}

}
