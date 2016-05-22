package testengine.dao.batch;

import java.util.ArrayList;

import testengine.beans.BatchInfo;
import testengine.beans.CandidateInfo;
import testengine.beans.SectionInfo;

public interface BatchInterface {
	
	public int createBatch(BatchInfo batchInfo);
	
	public ArrayList<BatchInfo> loadBatches();
	
	public ArrayList<SectionInfo> loadSections();
	
	public int allocateCandidatesToBatch(String[] allocatedCandidates, String batchId) ;
	
	public int updateAllocationFlag(String[] allocatedCandidates);
	
	public ArrayList<CandidateInfo> getAllocatedCandidateDetails(String[] allocatedCandidates);
	
	public ArrayList<CandidateInfo> getCandidateListForBatch(String batchId);

}
