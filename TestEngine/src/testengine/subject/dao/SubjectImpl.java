package testengine.subject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import testengine.beans.BatchInfo;
import testengine.beans.SubjectInfo;
import testengine.utility.Suppliments;

public class SubjectImpl implements SubjectInterface{

	
	Connection connection;
	PreparedStatement stat;
	ResultSet rs;
	private static final String CREATE_SUBJECT_QRY = "insert into subjects(subjectid,subjectname,subjectdesc) values(?,?,?)";
	private static final String LOAD_BATCH_LIST_QRY = "SELECT * FROM SUBJECTS";
	
	public int addSubject(SubjectInfo subjectInfo) {

		
		int subjectCreated = 0;
			try
			{
				connection = Suppliments.getConnection();
				stat = connection.prepareStatement(CREATE_SUBJECT_QRY);
				stat.setString(1, subjectInfo.getSubjectId());
				stat.setString(2, subjectInfo.getSubjectName());
				stat.setString(3, subjectInfo.getSubjectDesc());
				
				subjectCreated = stat.executeUpdate();
				
			}
			catch(Exception e){
				System.out.println("Exception in creating Subject->"+e);
			}
			finally
			{
				try
				{
					closeConnection(connection,null);
					stat.close();
				}
					
				catch(Exception e){
					System.out.println("Exception in creating Subject->"+e);
				}
			}
		
		return subjectCreated;
		
	
	}
	public ArrayList<SubjectInfo> getSubjectList() {
		

		
		ArrayList<SubjectInfo> subjectList = new ArrayList<SubjectInfo>();
		
		try
		{
			connection = Suppliments.getConnection();
			stat = connection.prepareStatement(LOAD_BATCH_LIST_QRY);
			rs = stat.executeQuery();
			while(rs.next()) {
				
				SubjectInfo subjectInfo = new SubjectInfo();
				subjectInfo.setSubjectId(rs.getString("subjectid"));
				subjectInfo.setSubjectName(rs.getString("subjectname"));
				subjectInfo.setSubjectDesc(rs.getString("subjectdesc"));
				
				subjectList.add(subjectInfo);
				
			}
			
			
		}
		catch(Exception e) {
			
			System.out.println("Exception in load batch.."+e);
			
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
		return subjectList;
		
	
		
	}
	public void closeConnection(Connection connection,ResultSet resultSet) throws SQLException{
		if(connection != null){
		connection.close();
		}
		if(resultSet != null){
			resultSet.close();
		}
	}

}
