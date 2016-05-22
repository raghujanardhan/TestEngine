package testengine.dao;

import testengine.beans.UserInfo;
import testengine.exceptions.UserNotFoundException;
import testengine.utility.ConnectionFactory;
import testengine.utility.JDBCConnection;

import java.sql.*;

public class UserDaoImpl implements UserLoginInterface{
	
	static String validateUserQry = "SELECT * FROM TESTENGINE_USERS WHERE USER_NAME = ? AND PASSWORD = ?";
	private static final String USER_MODULE_QRY = "SELECT MODULES_ASSIGNED FROM TESTENGINE_USERS WHERE USER_NAME = ?";
	Connection connection;
	PreparedStatement stat;
	ResultSet rs;
	
	public boolean validateUser(UserInfo userInfo) throws UserNotFoundException {
		int rowCount = 0;
		boolean validUser = false;
		
		try
		{
			Class.forName(JDBCConnection.getDriver());
			
			Connection con = DriverManager.getConnection(JDBCConnection.getUrl(),JDBCConnection.getUserName(),JDBCConnection.getPassword());
			PreparedStatement stat = con.prepareStatement(validateUserQry);
			stat.setString(1, userInfo.getUserName());
			stat.setString(2, userInfo.getPassword());
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {				
				rowCount++;
				if(rs.getString("USER_NAME").equals(userInfo.getUserName()) && rs.getString("PASSWORD").equals(userInfo.getPassword())){
					
					validUser = true;
					
				}
			}
			if(rowCount == 0 ){
				
				throw new UserNotFoundException("User Does not Exist");
				
			}
			
			
			
		}
		catch(UserNotFoundException unf){
			
			throw unf;
			
		}
		catch(Exception e){
			
			
		}
		return validUser;
	}
	public String loadUserModuleDetails(UserInfo userInfo) {
		String modules = "";
		try
		{
			connection = getConnection();
			stat = connection.prepareStatement(USER_MODULE_QRY);
			stat.setString(1, userInfo.getUserName());
			rs = stat.executeQuery();
			while(rs.next()) {
				modules = rs.getString("modules_assigned");
			}
		}
		catch(Exception e) {
			System.out.println("Exception while loading modules for user..."+e);
		}
		return modules;
	}
	private static Connection getConnection() throws SQLException, ClassNotFoundException 
	{
	    Connection con = ConnectionFactory.getInstance().getConnection();
	    return con;
	}
	

}
