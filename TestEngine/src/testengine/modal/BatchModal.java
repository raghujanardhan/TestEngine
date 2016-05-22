package testengine.modal;

import java.util.ArrayList;

import testengine.beans.BatchInfo;
import testengine.beans.CandidateInfo;
import testengine.beans.SectionInfo;
import testengine.service.BatchService;

public class BatchModal {
	
	public boolean createBatch(BatchInfo batchInfo) {
		
		BatchService bs = new BatchService();
		return bs.createBatch(batchInfo);
		
	}
	
	public ArrayList<BatchInfo> loadBatches() {
		
		BatchService bs = new BatchService();
		return bs.loadBatches();
	}
	
	public ArrayList<SectionInfo> loadSections() {
		
		BatchService bs = new BatchService();
		return bs.loadSections();
	}
	
	public int allocateCandidatesToBatch(String[] allocatedCandidates, String batchId) {
		BatchService bs = new BatchService();
		return bs.allocateCandidatesToBatch(allocatedCandidates,batchId);
		
	}
	
	public int updateAllocationFlag(String[] allocatedCandidates ) {
		
		BatchService bs = new BatchService();
		return bs.updateAllocationFlag(allocatedCandidates);
		
	}
	
	public ArrayList<CandidateInfo> getAllocatedCandidateDetails(String[] allocatedCandidates) {
		
		BatchService bs = new BatchService();
		return bs.getAllocatedCandidateDetails(allocatedCandidates);
		
	}
	public ArrayList<CandidateInfo> getCandidateListForBatch(String batchId) {
		
		BatchService bs = new BatchService();
		return bs.getCandidateListForBatch(batchId);
		
	}
	

}
