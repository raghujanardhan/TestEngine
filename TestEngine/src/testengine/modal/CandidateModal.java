package testengine.modal;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import testengine.beans.CandidateInfo;
import testengine.service.CandidateService;

public class CandidateModal {
	
	public ArrayList<CandidateInfo> getClassData(){
		
		CandidateService cs = new CandidateService();
		return cs.getClassData();
		
	}
	public int addCandidate(CandidateInfo candidateInfo){
		
		CandidateService cs = new CandidateService();
		return cs.addCandidate(candidateInfo);
	}
	
	public List<CandidateInfo> listCandidates(int paginate, int recordsPerPage) {
		
		CandidateService cs = new CandidateService();
		return cs.listCandidates(paginate, recordsPerPage);
		
	}
	
	public List<CandidateInfo> listCandidates(int paginate, int recordsPerPage,String canId,String canName,String order) {
		
		CandidateService cs = new CandidateService();
		return cs.listCandidates(paginate, recordsPerPage,canId,canName,order);
		
	}
	
	public int getNumOfRecords(){
		CandidateService cs = new CandidateService();
		return cs.getNumOfRecords();
		
	}
	public int deleteCandidate(String candidateId){
		CandidateService cs = new CandidateService();
		return cs.deleteCandidate(candidateId);
	}
	public ArrayList<CandidateInfo> getCandidate(String candidateId) {
		CandidateService cs = new CandidateService();
		return cs.getCandidate(candidateId);
		
	}
	
	public boolean editCandidate(CandidateInfo candidateInfo) {
		CandidateService cs = new CandidateService();
		return cs.editCandidate(candidateInfo);
	}
	
	public int insertFileDataToDB(String pathandname) {
		CandidateService cs = new CandidateService();
		return cs.insertFileDataToDB(pathandname);
	}

}
