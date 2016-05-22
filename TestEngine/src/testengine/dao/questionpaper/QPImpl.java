package testengine.dao.questionpaper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.HashMap;

import testengine.utility.ConnectionFactory;

public class QPImpl implements QPInterface{

	private static final String QP_UPLOAD_QRY = "insert into question_paper (qp_id,class_id,section_id,qp_details,upd_timestamp,upd_user,total_questions,subject_id) values(?,?,?,?,?,?,?,?)";
	Connection connection;
    PreparedStatement stmt;
	
	
	public int uploadQuestionPaperDetails(HashMap questionPaperMap) {
		int dataUpdate = 0;
		
		System.out.println(questionPaperMap);
		
		String filePath = (String) questionPaperMap.get("filepath");
		String sectionId = (String)questionPaperMap.get("sectionId");
		String qpId = (String)questionPaperMap.get("qpid");
		String classId = (String)questionPaperMap.get("classId");
		String totalnum  = (String)questionPaperMap.get("totalnum");
		String subjectId = (String)questionPaperMap.get("subjectId");
		
		
		Calendar calendar = Calendar.getInstance();
		
		try
		{
			connection = getConnection();
			stmt = connection.prepareStatement(QP_UPLOAD_QRY);
			stmt.setString(1, qpId);
			stmt.setInt(2, Integer.parseInt(classId));
			stmt.setInt(3, Integer.parseInt(sectionId));
			stmt.setString(4, filePath);
			stmt.setLong(5, calendar.getTimeInMillis());
			stmt.setString(6, "admin");
			stmt.setInt(7, Integer.parseInt(totalnum));
			stmt.setString(8, subjectId);
			
			
			dataUpdate = stmt.executeUpdate();
		}
		catch (SQLException e) {
	           e.printStackTrace();
	       } catch (ClassNotFoundException e) {
	           e.printStackTrace();
	       }finally
	       {
	           try {
	               closeConnection1(connection, stmt);
	               } catch (SQLException e) {
	               e.printStackTrace();
	           }
	       }
		
		
		System.out.println("Data updated ?"+dataUpdate);
		
		
		return dataUpdate;
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
