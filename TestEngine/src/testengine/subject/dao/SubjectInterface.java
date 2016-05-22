package testengine.subject.dao;

import java.util.ArrayList;

import testengine.beans.SubjectInfo;

public interface SubjectInterface {
	
	public int addSubject(SubjectInfo subjectInfo);
	public ArrayList<SubjectInfo> getSubjectList();

}
