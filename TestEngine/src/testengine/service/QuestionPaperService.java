package testengine.service;

import java.util.HashMap;

import testengine.dao.CandidateInterface;
import testengine.dao.questionpaper.QPInterface;
import testengine.factory.TestEngineFactory;

public class QuestionPaperService {
	
	public int uploadQuestionPaperDetails(HashMap questionPaperMap)
	{
		TestEngineFactory testFactory = new TestEngineFactory();
		QPInterface qi = testFactory.getQPDAO("questionPaper");
		return qi.uploadQuestionPaperDetails(questionPaperMap);
		
	}

}
