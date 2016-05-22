package testengine.dao.batch;

import testengine.beans.BatchInfo;
import testengine.beans.CandidateInfo;
import testengine.beans.SectionInfo;
import testengine.utility.Suppliments;

import java.sql.*;
import java.util.ArrayList;
public class BatchImpl implements BatchInterface{
	
	private static final String CREATE_BATCH_QRY = "insert into batch(batchId,batchName,batchDesc) values(?,?,?)";
	private static final String LOAD_BATCH_LIST_QRY = "SELECT * FROM BATCH";
	private static final String LOAD_SECTION_LIST_QRY = "SELECT * FROM SECTION";
	private static final String BATCH_CANDIDATE_MAP_QRY = "INSERT INTO BATCH_CANDIDATE_MAPPING(BATCHID,CANDIDATEID) VALUES(?,?)";
	private static final String LIST_CANDIDATES_OF_BATCH_QRY = "SELECT CANDIDATEID FROM BATCH_CANDIDATE_MAPPING WHERE BATCHID = ?";
	
	
	Connection connection;
	PreparedStatement stat;
	ResultSet rs;
	public int  createBatch(BatchInfo batchInfo) {
		
		int batchCreated = 0;
			try
			{
				connection = Suppliments.getConnection();
				stat = connection.prepareStatement(CREATE_BATCH_QRY);
				stat.setString(1, batchInfo.getBatchId());
				stat.setString(2, batchInfo.getBatchName());
				stat.setString(3, batchInfo.getBatchDesc());
				
				batchCreated = stat.executeUpdate();
				
			}
			catch(Exception e){
				System.out.println("Exception in creating batch->"+e);
			}
			finally
			{
				try
				{
					closeConnection(connection,null);
					stat.close();
				}
					
				catch(Exception e){
					System.out.println("Exception in creating batch->"+e);
				}
			}
		
		return batchCreated;
		
	}
	public ArrayList<BatchInfo> loadBatches() {
		
		ArrayList<BatchInfo> batchList = new ArrayList<BatchInfo>();
		
		try
		{
			connection = Suppliments.getConnection();
			stat = connection.prepareStatement(LOAD_BATCH_LIST_QRY);
			rs = stat.executeQuery();
			while(rs.next()) {
				
				BatchInfo batchInfo = new BatchInfo();
				batchInfo.setBatchId(rs.getString("batchId"));
				batchInfo.setBatchName(rs.getString("batchName"));
				batchInfo.setBatchDesc(rs.getString("batchDesc"));
				
				batchList.add(batchInfo);
				
			}
			
			
		}
		catch(Exception e) {
			
			System.out.println("Exception in load batch.."+e);
			
		}
		finally
		{
			try
			{
			closeConnection(connection,rs);
			}
			catch(SQLException se) {
				
				se.printStackTrace();
				
			}
		}
		return batchList;
		
	}
	
public ArrayList<SectionInfo> loadSections() {
		
		ArrayList<SectionInfo> sectionList = new ArrayList<SectionInfo>();
		
		try
		{
			connection = Suppliments.getConnection();
			stat = connection.prepareStatement(LOAD_SECTION_LIST_QRY);
			rs = stat.executeQuery();
			while(rs.next()) {
				
				SectionInfo sectionInfo = new SectionInfo();
				sectionInfo.setSectionId(rs.getInt("sectionid"));
				sectionInfo.setSectionName(rs.getString("sectionname"));
				sectionInfo.setSectionDesc(rs.getString("sectiondesc"));
				sectionList.add(sectionInfo);
			}
		}
		catch(Exception e) {
			
			System.out.println("Exception in load section.."+e);
			
		}
		finally
		{
			try
			{
			closeConnection(connection,rs);
			}
			catch(SQLException se) {
				
				se.printStackTrace();
				
			}
		}
		return sectionList;
		
	}

	public int allocateCandidatesToBatch(String[] allocatedCandidates, String batchId) {
		int recordsAdded = 0;
		try
		{
			connection = Suppliments.getConnection();
			stat = connection.prepareStatement(BATCH_CANDIDATE_MAP_QRY);
			for(int i = 0;i<allocatedCandidates.length;i++) {
				
				stat.setString(1, batchId);
				stat.setString(2, allocatedCandidates[i]);
				recordsAdded = stat.executeUpdate();
				
			}
			
			
		}
		catch(Exception e) {
			
			System.out.println("Exception in allocateCandidatesToBatch "+e);
		}
		
		finally {
			try
			{
				closeConnection(connection, null);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return recordsAdded;
	}
	
	public int updateAllocationFlag(String[] allocatedCandidates) {
		
		
		int updated = 0;
		
		String inClause = Suppliments.buildInClause(allocatedCandidates);
		
		String updateFlagQry = "UPDATE CANDIDATEREG SET ALLOCATIONFLAG = 1 WHERE CANDIDATEID IN ("+inClause+")";
		
		System.out.println("Query is "+updateFlagQry);
		try
		{
			connection = Suppliments.getConnection();
			Statement stat = connection.createStatement();
			updated = stat.executeUpdate(updateFlagQry);
		}
		catch (Exception e) {
			
			System.out.println("Exceptiokn in flag update.."+e);
			
		}
		finally
		{
			try
			{
				closeConnection(connection, null);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		return updated;
	}
	
	public ArrayList<CandidateInfo> getAllocatedCandidateDetails(String[] allocatedCandidates) {
		
		ArrayList<CandidateInfo> candidateList = new ArrayList<CandidateInfo>();
		CandidateInfo candidateInfo = null;
		
		try
		{
			connection = Suppliments.getConnection();
			Statement stat = connection.createStatement();
			String inClause = Suppliments.buildInClause(allocatedCandidates);
			String qry = "select * from candidatereg where candidateid in ("+inClause+")";
			System.out.println(qry);
			
			ResultSet rs = stat.executeQuery(qry);
			
			while(rs.next()) {
				
					candidateInfo = new CandidateInfo();
	               candidateInfo.setCandidateId(rs.getString("candidateid"));
	               candidateInfo.setClassId((rs.getInt("classid")));
	               candidateInfo.setCandidateName(rs.getString("candidatename"));
	               candidateInfo.setClassName(Suppliments.getClass(rs.getInt("classid")));
	               candidateInfo.setSectionId((rs.getInt("sectionid")));
	               candidateInfo.setSectionName(Suppliments.getSection(rs.getInt("sectionid")));
	               candidateInfo.setEmail(rs.getString("candidateemail"));
	               candidateInfo.setPhone(rs.getString("candidatephone"));
	               candidateInfo.setAddress(rs.getString("candidateaddress"));
	               candidateList.add(candidateInfo);
				
			}
			
			
			
		}
		catch(Exception e) {
			
			System.out.println("Exception is "+e);
			
		}
		finally {
			try
			{
				closeConnection(connection, rs);
			}
			catch(SQLException se){
				se.printStackTrace();
			}
		}
		return candidateList;
	}
	
	public ArrayList<CandidateInfo> getCandidateListForBatch(String batchId) {
		ArrayList<CandidateInfo> candidateList = new ArrayList<CandidateInfo>();
		CandidateInfo candidateInfo = null;
		try
		{
			connection = Suppliments.getConnection();
			PreparedStatement stat = connection.prepareStatement(LIST_CANDIDATES_OF_BATCH_QRY);
			stat.setString(1, batchId);
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				
				   candidateInfo = new CandidateInfo();
	               candidateInfo.setCandidateId(rs.getString("candidateid"));	               
	               candidateList.add(candidateInfo);
			}
		}
		catch(Exception e) {
			System.out.println("Exception is "+e);
		}
		finally {
			try
			{
				closeConnection(connection, rs);
			}
			catch(SQLException se){
				se.printStackTrace();
			}
		}
		return candidateList;
	
		
	}
	
	
	public void closeConnection(Connection connection,ResultSet resultSet) throws SQLException{
		if(connection != null){
		connection.close();
		}
		if(resultSet != null){
			resultSet.close();
		}
	}
	

}

