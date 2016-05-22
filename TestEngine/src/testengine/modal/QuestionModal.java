package testengine.modal;

import java.util.ArrayList;
import java.util.Map;

import testengine.beans.QuestionInfo;
import testengine.beans.QuestionSearchInfo;
import testengine.service.QuestionService;

public class QuestionModal {
	
	public int saveQuestion(Map<String,String> questionMap){
		
		QuestionService questionService  = new QuestionService();
		return questionService.saveQuestion(questionMap);
		
	}
	public ArrayList<QuestionInfo> listAllQUestions() {
		QuestionService questionService  = new QuestionService();
		return questionService.listAllQUestions();
	}
	public ArrayList<QuestionInfo> searchQuestions(QuestionSearchInfo questionSearchInfo) {
		QuestionService questionService  = new QuestionService();
		return questionService.searchQuestions(questionSearchInfo);
	}
	

}
