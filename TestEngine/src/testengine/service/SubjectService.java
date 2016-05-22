package testengine.service;
import java.util.ArrayList;

import testengine.beans.SubjectInfo;
import testengine.factory.TestEngineFactory;
import testengine.subject.dao.SubjectInterface;

public class SubjectService {
	
	public boolean addSubject(SubjectInfo subjectInfo){
		
		TestEngineFactory testFactory = new TestEngineFactory();
		SubjectInterface si = testFactory.getSubjectDAO("subject");
		int subjectCreate = si.addSubject(subjectInfo);
		if(subjectCreate > 0){
			return true;
		}
		else
		{
			return false;
		}
		}
	
	 public ArrayList<SubjectInfo> getSubjectList() {
		
		 TestEngineFactory testFactory = new TestEngineFactory();
		 SubjectInterface si = testFactory.getSubjectDAO("subject");
		 return si.getSubjectList();
		 
	 }

}
