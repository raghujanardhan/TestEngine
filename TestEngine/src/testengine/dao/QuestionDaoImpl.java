package testengine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Map;

import testengine.beans.QuestionInfo;
import testengine.beans.QuestionSearchInfo;
import testengine.utility.ConnectionFactory;
import testengine.utility.Suppliments;

public class QuestionDaoImpl implements QuestionInterface{
	
	private static final String SAVE_QUESTION_QRY = "insert into question_bank (questionId,question,question_img,difficulty_lvl,option1,option1_img,option2,option2_img,option3,option3_img,option4,option4_img,answer,class_id,subject_id,filepath,upd_by,last_upd_time)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String LIST_QUESTIONS_QRY = " SELECT * FROM QUESTION_BANK ";
	private static final String SEARCH_QUESTIONS_QRY = "SELECT * FROM QUESTION_BANK WHERE ";
	Connection connection;
	PreparedStatement stat;
	ResultSet resultSet;
	int saveQuestion;
	
	public int saveQuestion(Map<String, String> questionMap) {
		// TODO Auto-generated method stub
		GregorianCalendar cal = new GregorianCalendar();
		try
		{
			
			connection = getConnection();
			stat = connection.prepareStatement(SAVE_QUESTION_QRY);
			stat.setString(1, questionMap.get("questionid"));
			stat.setString(2, questionMap.get("question"));
			stat.setString(3, questionMap.get("file_question"));
			stat.setInt(4, Integer.parseInt(questionMap.get("difficulty")));
			stat.setString(5, questionMap.get("option1"));
			stat.setString(6, questionMap.get("file_op1"));
			stat.setString(7, questionMap.get("option2"));
			stat.setString(8, questionMap.get("file_op2"));
			stat.setString(9, questionMap.get("option3"));
			stat.setString(10, questionMap.get("file_op3"));
			stat.setString(11, questionMap.get("option4"));
			stat.setString(12, questionMap.get("file_op4"));
			stat.setString(13, questionMap.get("answer"));
			stat.setInt(14, Integer.parseInt(questionMap.get("classId")));
			stat.setString(15,questionMap.get("subjectId"));
			stat.setString(16, questionMap.get("filepath"));
			stat.setString(17,questionMap.get("user"));
			stat.setLong(18, cal.getTimeInMillis());
			
			saveQuestion = stat.executeUpdate();
			
			
			
		}
		catch(Exception e) {
			
			System.out.println("Exception in saving question...."+e);
			
		}
		return saveQuestion;
	}
	public ArrayList<QuestionInfo> listAllQUestions() {
		ArrayList<QuestionInfo> questionList = new ArrayList<QuestionInfo>();
		try
		{
			connection = getConnection();
			stat = connection.prepareStatement(LIST_QUESTIONS_QRY);
			resultSet = stat.executeQuery(); 
			while(resultSet.next()) {
				QuestionInfo questInfo = new QuestionInfo();
				questInfo.setQuestionId(resultSet.getString("questionId"));
				questInfo.setQuestion(resultSet.getString("question"));
				questInfo.setClassName(Suppliments.getClass(resultSet.getInt("class_id")));
				questInfo.setSubject(Suppliments.getSubject(resultSet.getString("subject_id")));
				questInfo.setUpdUser(resultSet.getString("upd_by"));
				
				questionList.add(questInfo);
				
			}
		}
		catch (Exception e) {
			System.out.println("Exception in listing questions "+e);
		}
		return questionList;
		
	}
	public ArrayList<QuestionInfo> searchQuestions(QuestionSearchInfo questionSearchInfo) {

		ArrayList<QuestionInfo> questionList = new ArrayList<QuestionInfo>();
		try
		{
			connection = getConnection();
			String whereClauseForQuestionSearch = buildWhereClause(questionSearchInfo);
			
			
			if(whereClauseForQuestionSearch.trim().endsWith("and")){
				
				whereClauseForQuestionSearch = whereClauseForQuestionSearch.substring(0, whereClauseForQuestionSearch.length()-4);
				
			}
			stat = connection.prepareStatement(SEARCH_QUESTIONS_QRY+whereClauseForQuestionSearch);
			resultSet = stat.executeQuery(); 
			while(resultSet.next()) {
				QuestionInfo questInfo = new QuestionInfo();
				questInfo.setQuestionId(resultSet.getString("questionId"));
				questInfo.setQuestion(resultSet.getString("question"));
				questInfo.setClassName(Suppliments.getClass(resultSet.getInt("class_id")));
				questInfo.setSubject(Suppliments.getSubject(resultSet.getString("subject_id")));
				questInfo.setUpdUser(resultSet.getString("upd_by"));
				
				questionList.add(questInfo);
				
			}
		}
		catch (Exception e) {
			System.out.println("Exception in listing questions "+e);
		}
		return questionList;
	}
	private static Connection getConnection() throws SQLException, ClassNotFoundException 
    {
        Connection con = ConnectionFactory.getInstance().getConnection();
        return con;
    }
	private String buildWhereClause(QuestionSearchInfo questionSearchInfo){
		
		StringBuffer buffer = new StringBuffer();
		
		if(questionSearchInfo.getQuestionId().trim().length() > 0){
			
			buffer.append("questionId = '"+questionSearchInfo.getQuestionId().trim()+"' and ");
			
		}
		if(questionSearchInfo.getKeyword().trim().length() > 0){
			
			buffer.append("question like '%"+questionSearchInfo.getKeyword()+"%' and ");
			
		}
		if(questionSearchInfo.getClassId() != -1) {
			
			buffer.append("class_id = "+questionSearchInfo.getClassId()+" and ");
			
		}
		if(! questionSearchInfo.getSubjectId().trim().equals("-1")) {
			buffer.append("subject_id = '"+questionSearchInfo.getSubjectId()+"' and ");
		}
		if(questionSearchInfo.getAddedby().trim().length() > 0){
			
			buffer.append("upd_by like '%"+questionSearchInfo.getAddedby().trim()+"%'  ");
			
		}
		
		return buffer.toString();
	}
	

}
