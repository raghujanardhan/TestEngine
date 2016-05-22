package testengine.utility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import testengine.beans.CandidateInfo;
import testengine.beans.SubjectInfo;
import testengine.modal.CandidateModal;

public class Suppliments {

	
	
	public static String getDate()
	{
		Calendar curdate = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("MMM dd, yyyy");
		String today = formater.format(curdate.getTime());
		
		return today;
	}
	public static String generateIdSequence(String sequenceFor){
		
		String sequence="";
		
		if(sequenceFor.equalsIgnoreCase("candidate")){
			long time = System.currentTimeMillis();
			sequence = "C"+time;
			
		}
		else if(sequenceFor.equalsIgnoreCase("batch")){
			
			long time = System.currentTimeMillis();
			String tempseq = ""+time;			
			sequence = "B"+tempseq.substring(7);
			
			
		}
		else if(sequenceFor.equalsIgnoreCase("subject")){
			
			long time = System.currentTimeMillis();
			String tempseq = ""+time;
			sequence = "S"+tempseq.substring(7);
			
			
		}
		else if(sequenceFor.equalsIgnoreCase("question")){
			
			long time = System.currentTimeMillis();
			String tempseq = ""+time;
			sequence = "Q"+tempseq.substring(7);
			
			
		}
		else if(sequenceFor.equalsIgnoreCase("questionpaper")){
			
			long time = System.currentTimeMillis();
			String tempseq = ""+time;
			sequence = "QP"+tempseq.substring(7);
			
			
		}
		
		
		return sequence;
		
	}
	
	public static String[] getFormParameters(String formName){
		
		
		
		if(formName.equalsIgnoreCase("Registercandidate")){
			
			String formParam[] = {"canditateId" ,"canditatename","class","section","canditateaddress","canditateemail","canditatephone"};
			return formParam;
			
		}
		return null;
		
		
	}
	public static Connection getConnection() throws SQLException, ClassNotFoundException 
    {
        Connection con = ConnectionFactory.getInstance().getConnection();
        return con;
    }
	
	
	public static String getClass(int classId) {
		Connection connection=null;
		PreparedStatement stmt;
		ResultSet rs;
		String className="";
		try {
	           connection = getConnection();
	           stmt = connection.prepareStatement("Select classname from class where classid = ?");
	           stmt.setInt(1, classId);
	           rs = stmt.executeQuery();
	           while(rs.next()){
	        	   
	        	   className = rs.getString("classname");
	           }
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return className;
		
	}
	
	public static String getSection(int sectionId) {
		Connection connection=null;
		PreparedStatement stmt;
		ResultSet rs;
		String className="";
		try {
	           connection = getConnection();
	           stmt = connection.prepareStatement("Select sectionname from section where sectionid = ?");
	           stmt.setInt(1, sectionId);
	           rs = stmt.executeQuery();
	           while(rs.next()){
	        	   
	        	   className = rs.getString("sectionname");
	           }
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return className;
		
	}
	public static String getSubject(String subjectId) {
		Connection connection=null;
		PreparedStatement stmt;
		ResultSet rs;
		String subjectName="";
		try {
	           connection = getConnection();
	           stmt = connection.prepareStatement("Select subjectname from subjects where subjectid = ?");
	           stmt.setString(1, subjectId);
	           rs = stmt.executeQuery();
	           while(rs.next()){
	        	   
	        	   subjectName = rs.getString("subjectname");
	           }
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return subjectName;
		
	}
	
	
	public static String buildInClause(String[] data) {
		
		String inClause="";
		
		
		for(int i = 0;i<data.length;i++){
			
			if(i<data.length-1)
			{
				inClause = inClause+"'"+data[i]+"',";
				System.out.println(inClause);
			}
			else
			{
				inClause = inClause+"'"+data[i]+"'";
			}
		}
		return inClause;

		
	}
	public static  ArrayList<CandidateInfo> getClassDetails() {
		CandidateModal candidateModal = new CandidateModal();
		ArrayList<CandidateInfo> classList = candidateModal.getClassData();
		return classList;
	}
	public static ArrayList<SubjectInfo> getSubjects(){
		
		
		Connection connection=null;
		PreparedStatement stmt;
		ResultSet rs;
		ArrayList<SubjectInfo> subList = new ArrayList<SubjectInfo>();
		try {
	           connection = getConnection();
	           stmt = connection.prepareStatement("Select * from subjects");
	           
	           rs = stmt.executeQuery();
	           while(rs.next()){
	        	   SubjectInfo sinfo = new SubjectInfo();
	        	   sinfo.setSubjectId(rs.getString("subjectid"));
	        	   sinfo.setSubjectName(rs.getString("subjectname"));
	        	   sinfo.setSubjectDesc(rs.getString("subjectdesc"));
	        	   subList.add(sinfo);
	        	   
	           }
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return subList;
		
	}
	
	
	
}
