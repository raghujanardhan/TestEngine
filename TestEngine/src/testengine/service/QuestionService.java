package testengine.service;

import java.util.ArrayList;
import java.util.Map;

import testengine.beans.QuestionInfo;
import testengine.beans.QuestionSearchInfo;
import testengine.dao.QuestionInterface;
import testengine.factory.TestEngineFactory;
import testengine.subject.dao.SubjectInterface;

public class QuestionService {
	
	public int saveQuestion(Map<String,String> questionMap){
		
		TestEngineFactory testFactory = new TestEngineFactory();
		QuestionInterface questionInterface = testFactory.getQuestionDAO("question");
		return questionInterface.saveQuestion(questionMap);
		
	}
	public ArrayList<QuestionInfo> listAllQUestions() {
		TestEngineFactory testFactory = new TestEngineFactory();
		QuestionInterface questionInterface = testFactory.getQuestionDAO("question");
		return questionInterface.listAllQUestions();
		
	}
	public ArrayList<QuestionInfo> searchQuestions(QuestionSearchInfo questionSearchInfo){
		TestEngineFactory testFactory = new TestEngineFactory();
		QuestionInterface questionInterface = testFactory.getQuestionDAO("question");
		return questionInterface.searchQuestions(questionSearchInfo);
		
	}

}
