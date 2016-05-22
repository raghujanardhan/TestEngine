package testengine.service;

import java.util.ArrayList;
import java.util.List;

import testengine.beans.CandidateInfo;
import testengine.dao.CandidateInterface;
import testengine.factory.TestEngineFactory;

public class CandidateService {
	public ArrayList<CandidateInfo> getClassData(){
		
		TestEngineFactory testFactory = new TestEngineFactory();
		CandidateInterface ci = testFactory.getDAO("candidate");
		return ci.getClassData();
	}
	
	public int addCandidate(CandidateInfo candidateInfo) {
		
		TestEngineFactory testFactory = new TestEngineFactory();
		CandidateInterface ci = testFactory.getDAO("candidate");
		return ci.addCandidate(candidateInfo);
		
	}
	
	public List<CandidateInfo> listCandidates(int paginate, int recordsPerPage) {
		
		TestEngineFactory testFactory = new TestEngineFactory();
		CandidateInterface ci = testFactory.getDAO("candidate");
		return ci.listCandidates(paginate, recordsPerPage);
	}
	
	public List<CandidateInfo> listCandidates(int paginate, int recordsPerPage,String canId,String canName,String order) {
		TestEngineFactory testFactory = new TestEngineFactory();
		CandidateInterface ci = testFactory.getDAO("candidate");
		return ci.listCandidates(paginate, recordsPerPage,canId,canName,order);
	}
	
	public int getNumOfRecords(){
		TestEngineFactory testFactory = new TestEngineFactory();
		CandidateInterface ci = testFactory.getDAO("candidate");
		return ci.getNumOfRecords();
	}
	
	public int deleteCandidate(String candidateId) {
		TestEngineFactory testFactory = new TestEngineFactory();
		CandidateInterface ci = testFactory.getDAO("candidate");
		return ci.deleteCandidate(candidateId);
		
		
	}
	public ArrayList<CandidateInfo> getCandidate(String candidateId)  {
	
		TestEngineFactory testFactory = new TestEngineFactory();
		CandidateInterface ci = testFactory.getDAO("candidate");
		return ci.getCandidate(candidateId);
		
	}
	
	public boolean editCandidate(CandidateInfo candidateInfo) {
		TestEngineFactory testFactory = new TestEngineFactory();
		CandidateInterface ci = testFactory.getDAO("candidate");
		return ci.editCandidate(candidateInfo);
		
	}
	public int insertFileDataToDB(String pathandname) {
		TestEngineFactory testFactory = new TestEngineFactory();
		CandidateInterface ci = testFactory.getDAO("candidate");
		return ci.insertFileDataToDB(pathandname);
		
	}
}
