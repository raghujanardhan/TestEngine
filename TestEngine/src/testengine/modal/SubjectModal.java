package testengine.modal;

import java.util.ArrayList;

import testengine.beans.SubjectInfo;
import testengine.service.SubjectService;

public class SubjectModal {
	
	public boolean addSubject(SubjectInfo subjectInfo) {
			
			SubjectService ss = new SubjectService();
			return ss.addSubject(subjectInfo);
			
		}
	public ArrayList<SubjectInfo> getSubjectList() {
		
		SubjectService ss = new SubjectService();
		return ss.getSubjectList();
		
	}



}
