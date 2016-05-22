package testengine.modal;

import java.util.HashMap;

import testengine.service.QuestionPaperService;

public class QuestionPaperModal {
	
	public int uploadQuestionPaperDetails(HashMap questionPaperMap)
	{
		QuestionPaperService qps = new QuestionPaperService();
		return qps.uploadQuestionPaperDetails(questionPaperMap);
	}

}
