package testengine.service;

import java.util.ArrayList;

import testengine.beans.BatchInfo;
import testengine.beans.CandidateInfo;
import testengine.beans.SectionInfo;
import testengine.dao.batch.BatchInterface;
import testengine.factory.TestEngineFactory;

public class BatchService {
	public boolean createBatch(BatchInfo batchInfo){
	
	TestEngineFactory testFactory = new TestEngineFactory();
	BatchInterface bi = testFactory.getBatchDAO("batch");
	int batchCreate = bi.createBatch(batchInfo);
	if(batchCreate > 0){
		return true;
	}
	else
	{
		return false;
	}
	}
	public ArrayList<BatchInfo> loadBatches() {
		TestEngineFactory testFactory = new TestEngineFactory();
		BatchInterface bi = testFactory.getBatchDAO("batch");
		
		return bi.loadBatches();
		
		
	}
	public ArrayList<SectionInfo> loadSections() {
		TestEngineFactory testFactory = new TestEngineFactory();
		BatchInterface bi = testFactory.getBatchDAO("batch");
		
		return bi.loadSections();
		
		
	}
	
	public int allocateCandidatesToBatch(String[] allocatedCandidates, String batchId) {
		
		TestEngineFactory testFactory = new TestEngineFactory();
		BatchInterface bi = testFactory.getBatchDAO("batch");
		
		return bi.allocateCandidatesToBatch( allocatedCandidates, batchId);
		
	}
	
	public int updateAllocationFlag(String[] allocatedCandidates) {
		
		TestEngineFactory testFactory = new TestEngineFactory();
		BatchInterface bi = testFactory.getBatchDAO("batch");
		
		return bi.updateAllocationFlag( allocatedCandidates);
		
		
	}
	public ArrayList<CandidateInfo> getAllocatedCandidateDetails(String[] allocatedCandidates)  {
		
		TestEngineFactory testFactory = new TestEngineFactory();
		BatchInterface bi = testFactory.getBatchDAO("batch");
		
		return bi.getAllocatedCandidateDetails( allocatedCandidates);
	}
	
	public ArrayList<CandidateInfo> getCandidateListForBatch(String batchId) {
		
		TestEngineFactory testFactory = new TestEngineFactory();
		BatchInterface bi = testFactory.getBatchDAO("batch");
		
		return bi.getCandidateListForBatch(batchId);
		
	}
	
	
	

}
