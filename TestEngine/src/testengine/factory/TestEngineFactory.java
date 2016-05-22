package testengine.factory;

import testengine.dao.CandidateImpl;
import testengine.dao.CandidateInterface;
import testengine.dao.QuestionDaoImpl;
import testengine.dao.QuestionInterface;
import testengine.dao.UserDaoImpl;
import testengine.dao.UserLoginInterface;
import testengine.dao.batch.BatchImpl;
import testengine.dao.batch.BatchInterface;
import testengine.dao.questionpaper.QPImpl;
import testengine.dao.questionpaper.QPInterface;
import testengine.dao.user.UserImpl;
import testengine.dao.user.UserInterface;
import testengine.subject.dao.SubjectImpl;
import testengine.subject.dao.SubjectInterface;

public class TestEngineFactory {
	
	public CandidateInterface getDAO(String daoType){
		
		if(daoType == null){
			
			return null;
			
		}
		else if(daoType == "candidate"){
			
			return new CandidateImpl();
		}
		return null;
	}
	
	public BatchInterface getBatchDAO(String daoType){
		
		if(daoType == null){
					
					return null;
					
				}
				else if(daoType.equals("batch")){
					
					return new BatchImpl();
				}
				return null;
				
			}
	public SubjectInterface getSubjectDAO(String daoType){
		
		if(daoType == null){
					
					return null;
					
				}
				else if(daoType.equals("subject")){
					
					return new SubjectImpl();
				}
				return null;
				
			}
	public QPInterface getQPDAO(String daoType){
		
		if(daoType == null){
					
					return null;
					
				}
				else if(daoType.equals("questionPaper")){
					
					return new QPImpl();
				}
				return null;
				
			}
	public UserInterface getUserDAO(String daoType) {
			if(daoType == null){
					
					return null;
					
				}
				else if(daoType.equals("user")){
					
					return new UserImpl();
				}
				return null;
				
			
		
	}
	public UserLoginInterface getLoginDAO(String daoType) {
		if(daoType == null){
				
				return null;
				
			}
			else if(daoType.equals("login")){
				
				return new UserDaoImpl();
			}
			return null;
			
		
	
	}
	public QuestionInterface getQuestionDAO(String daoType) {
		if(daoType == null){
				
				return null;
				
			}
			else if(daoType.equals("question")){
				
				return new QuestionDaoImpl();
			}
			return null;
	}

}
