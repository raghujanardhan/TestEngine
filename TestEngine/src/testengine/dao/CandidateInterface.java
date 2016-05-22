package testengine.dao;

import java.util.ArrayList;
import java.util.List;

import testengine.beans.CandidateInfo;

public interface CandidateInterface {
	
	public ArrayList<CandidateInfo> getClassData();
	public int addCandidate(CandidateInfo candidateInfo);
	public List<CandidateInfo> listCandidates(int paginate, int recordsPerPage);
	public List<CandidateInfo> listCandidates(int paginate, int recordsPerPage,String canId,String canName,String order) ;
	public int getNumOfRecords();
	public int deleteCandidate(String candidateId);
	public ArrayList<CandidateInfo> getCandidate(String candidateId) ;
	public boolean editCandidate(CandidateInfo candidateInfo) ;
	public int insertFileDataToDB(String pathandname) ;
	

}
