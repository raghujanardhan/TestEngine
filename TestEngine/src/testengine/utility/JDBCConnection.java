package testengine.utility;

public class JDBCConnection {
	
	public static String getDriver(){
		return "com.mysql.jdbc.Driver";
	}
	public static String getUrl(){
		return "jdbc:mysql://localhost:3306/testengine";
	}
	public static String getUserName(){
		return "root";
	}
	public static String getPassword(){
		return "admin";
	}

}
