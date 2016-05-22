package testengine.dao;

import java.util.ArrayList;
import java.util.Map;

import testengine.beans.QuestionInfo;
import testengine.beans.QuestionSearchInfo;

public interface QuestionInterface {
	public int saveQuestion(Map<String,String> questionMap);
	public ArrayList<QuestionInfo> listAllQUestions();
	public ArrayList<QuestionInfo> searchQuestions(QuestionSearchInfo questionSearchInfo);

}
