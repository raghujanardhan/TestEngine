package testengine.controller.batch;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import testengine.beans.BatchInfo;
import testengine.beans.CandidateInfo;
import testengine.beans.ClassInfo;
import testengine.beans.SectionInfo;
import testengine.modal.BatchModal;
import testengine.modal.CandidateModal;
import testengine.utility.Suppliments;

/**
 * Servlet implementation class BatchController
 */
public class BatchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CREATE_BATCH = "createBatch";
	private static final String CREATE_NEW_BATCH = "addBatch";
	private static final String ADD_CANDIDATES_TO_BATCH = "addCandidatesToBatch";
	private static final String ALLOCATE_CANDIDATES_TO_BATCH = "allocateToBatch";
	private static final String VIEW_CANDIDATES_IN_BATCH = "viewCandidatesInBatch";
	
	RequestDispatcher rd;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BatchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("--->"+request.getParameter("action"));
		
		if(request.getParameter("action").equalsIgnoreCase(CREATE_BATCH)) {
			generateBatchId(request,response);
			ArrayList<BatchInfo> batchList = loadBatches();
			request.setAttribute("bList", batchList);
			rd = getServletContext().getRequestDispatcher("/Batch/BatchManagement.jsp");
			rd.forward(request, response);
		}
		else if(request.getParameter("action").equalsIgnoreCase(CREATE_NEW_BATCH)) {
			
			String batchId = request.getParameter("batchId");
			String batchName = request.getParameter("batchname");
			String batchDesc = request.getParameter("batchdesc");
			
			BatchInfo batchInfo = new BatchInfo();
			batchInfo.setBatchId(batchId);
			batchInfo.setBatchName(batchName);
			batchInfo.setBatchDesc(batchDesc);
			
			BatchModal batchModal = new BatchModal();
			boolean batchCreated = batchModal.createBatch(batchInfo);
			//rd = getServletContext().getRequestDispatcher("/Batch/BatchManagement.jsp");
			ArrayList<BatchInfo> batchList = loadBatches();
			request.setAttribute("bList", batchList);
			
			if(batchCreated){
				generateBatchId(request, response);
				response.getWriter().write("Batch Created");
				
			}
			else{
				generateBatchId(request, response);
				response.getWriter().write("Unable to Craete Batch");
			}
			
			//rd.forward(request, response);
			
			
		}
		else if(request.getParameter("action").equalsIgnoreCase(ADD_CANDIDATES_TO_BATCH)) {
			
			ArrayList<BatchInfo> batchList = loadBatches();
			ArrayList<ClassInfo> classList = loadClass();
			ArrayList<SectionInfo> sectionList = loadSections();
			
			request.setAttribute("bList", batchList);
			request.setAttribute("cList", classList);
			request.setAttribute("sList", sectionList);
			
			System.out.println(batchList.size());
			
			rd = getServletContext().getRequestDispatcher("/Batch/AddCandidates.jsp");
			rd.forward(request, response);
			
		}
		else if(request.getParameter("action").equalsIgnoreCase(ALLOCATE_CANDIDATES_TO_BATCH)){
			
			int flagUpdate = 0;
			
			String bid = request.getParameter("batchId");			
			String allocatedCandidates[] = request.getParameterValues("batchallocation");
			
			BatchModal bm = new BatchModal();
			int batchAllocated = bm.allocateCandidatesToBatch(allocatedCandidates, bid);
			if(batchAllocated > 0) {
				
				flagUpdate = updateAllocationFlag(allocatedCandidates) ;
				
			}
			if(flagUpdate > 0) {
				
				ArrayList<CandidateInfo> candidateList = getAllocatedCandidateDetails(allocatedCandidates);
				request.setAttribute("canList", candidateList);
				request.setAttribute("batchid", bid);
				
				
				rd = getServletContext().getRequestDispatcher("/Batch/showAllocatedCandidates.jsp");
				rd.forward(request, response);
			}
			
		}
		else if(request.getParameter("action").equalsIgnoreCase(VIEW_CANDIDATES_IN_BATCH)) {
			
			ArrayList<BatchInfo> batchList = loadBatches();
			request.setAttribute("bList", batchList);
			rd = getServletContext().getRequestDispatcher("/Batch/ViewCandidatesInBatch.jsp");
			rd.forward(request, response);
			
		}
		else if(request.getParameter("action").equalsIgnoreCase("viewCandidates") ) {
			
			BatchModal batchModal = new BatchModal();
			ArrayList<CandidateInfo> candidateIdList = batchModal.getCandidateListForBatch(request.getParameter("batch_id"));
			
			request.setAttribute("batchId", request.getParameter("batch_id"));
			
			String candidateIds[]  = new String[candidateIdList.size()];
			for(int i = 0;i<candidateIdList.size();i++) { 
				
				candidateIds[i] = candidateIdList.get(i).getCandidateId();
				
			}
			
			if(!candidateIdList.isEmpty()) {				
				ArrayList<CandidateInfo> candidateList = batchModal.getAllocatedCandidateDetails(candidateIds);
				request.setAttribute("canList", candidateList);
			}
			else
			{
				request.setAttribute("canList", "-1");
			}
			rd = getServletContext().getRequestDispatcher("/Batch/viewCandidateListForBatch.jsp");
			rd.forward(request, response);
		}
		
	}
	private int updateAllocationFlag(String[] allocatedCandidates ) {
		
		BatchModal bm = new BatchModal();
		int batchAllocated = bm.updateAllocationFlag(allocatedCandidates);
		return batchAllocated;
		
	}
	
	private ArrayList<CandidateInfo> getAllocatedCandidateDetails(String[] allocatedCandidates) {
		
		BatchModal bm = new BatchModal();
		return bm.getAllocatedCandidateDetails(allocatedCandidates);
	}
	
	
	private void generateBatchId(HttpServletRequest request, HttpServletResponse response) {
		
		String seqNum = Suppliments.generateIdSequence("batch");
		HttpSession session = request.getSession();
		session.setAttribute("seqnum", seqNum);
		
	}
	
	private ArrayList<BatchInfo> loadBatches() {
		
		BatchModal bm = new BatchModal();
		return bm.loadBatches();
	}
	private ArrayList<ClassInfo> loadClass() {
		
		ArrayList<ClassInfo> classList = new ArrayList<ClassInfo>();
		
		CandidateModal cm = new CandidateModal();
		ArrayList al = cm.getClassData();
		classList.addAll(al);
		return classList;
	}
	
	private ArrayList<SectionInfo> loadSections() {
		
		BatchModal bm = new BatchModal();
		return bm.loadSections();
		
	
	}
	
	

}
