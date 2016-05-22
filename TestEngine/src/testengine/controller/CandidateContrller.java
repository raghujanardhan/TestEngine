package testengine.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import testengine.beans.CandidateInfo;
import testengine.beans.UserInfo;
import testengine.dao.CandidateImpl;
import testengine.modal.CandidateModal;
import testengine.modal.UserModal;
import testengine.utility.Pagination;
import testengine.utility.Suppliments;

/**
 * Servlet implementation class CandidateContrller
 */
public class CandidateContrller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	RequestDispatcher rd;
	static final String ADD_CANDIDATE = "addCandidate";
	static final String REGISTER_CANDIDATE = "registerCandidate";
	static final String LIST_CANDIDATES = "listCandidates";
	static final String DELETE_CANDIDATE = "deleteCandidate";
	static final String SEARCH_CANDIDATE = "search";
	static final String EDIT_CANDIDATE = "editCandidate";
	static final String BULK_UPLOAD_CANDIDATE = "bulkUploadCandidates";
	static final String UPLOAD_CANDIDATE_DETAILS = "uploadCandidateDetails";
	static final String EDIT_SAVE = "editCandidateandSave";
	
	String candidateId;
	String candidateName;
	String order;
	private boolean isMultipart;
	private String filePath;
	private int maxFileSize = 50 * 1024;
	private int maxMemSize = 4 * 1024;
	private File file ;
	
	
    public CandidateContrller() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init( ){
    	   // Get the file location where it would be stored.
    	   filePath = getServletContext().getInitParameter("file-upload"); 
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
		
		
		
		
		if(request.getParameter("action").equalsIgnoreCase(ADD_CANDIDATE)){
			
			showRegisterScreen(request,response);
			listCandidates(request,response);
			rd = getServletContext().getRequestDispatcher("/Candidate/CandidateManagement.jsp");
			rd.forward(request, response);
		}
		else if(request.getParameter("action").equalsIgnoreCase(REGISTER_CANDIDATE)) {
			
			System.out.println("register Candidate");
			
			CandidateInfo candidateInfo = new  CandidateInfo();
			candidateInfo.setCandidateId(request.getParameter("canditateId"));
			candidateInfo.setCandidateName(request.getParameter("canditatename"));
			candidateInfo.setClassId(Integer.parseInt(request.getParameter("class")));
			candidateInfo.setSectionId(Integer.parseInt(request.getParameter("section")));
			candidateInfo.setAddress(request.getParameter("canditateaddress"));
			candidateInfo.setEmail(request.getParameter("canditateemail"));
			candidateInfo.setPhone(request.getParameter("canditatephone"));
			
			CandidateModal cm = new CandidateModal();
			int update = cm.addCandidate(candidateInfo);
			
			if(update>0){
				response.getWriter().write("Candidate Added");
			}
			else{
				response.getWriter().write("Unable to Add Candidate");
			}
			
			
			showRegisterScreen(request, response);
			
		}
		else if(request.getParameter("action").equalsIgnoreCase(LIST_CANDIDATES)){
			
			
			listCandidates(request,response);
			rd = getServletContext().getRequestDispatcher("/Candidate/ListCandidates.jsp");
			rd.forward(request, response);
			
		}
		
		else if(request.getParameter("action").equals(DELETE_CANDIDATE)){
			
			String candidateId = request.getParameter("candidateid");			
			CandidateModal cm = new CandidateModal();
			int deleted = cm.deleteCandidate(candidateId);
			if(deleted > 0) {
			listCandidates(request, response);
			rd = getServletContext().getRequestDispatcher("/Candidate/CandidateManagement.jsp");
			rd.forward(request, response);
			}
		}
		else if(request.getParameter("action").equalsIgnoreCase(SEARCH_CANDIDATE)) {
			
			candidateId = request.getParameter("candidateid");
			candidateName = request.getParameter("candidatename");
			order = request.getParameter("order");
			
			listCandidates(request, response);
			rd = getServletContext().getRequestDispatcher("/Candidate/ListCandidates.jsp");
			rd.forward(request, response);
			
		}
		else if(request.getParameter("action").equalsIgnoreCase(EDIT_CANDIDATE)){
			/*System.out.println("Edit candidate....");
			candidateId = request.getParameter("canditateid");
			System.out.println(candidateId);
			CandidateInfo canInfo = new CandidateInfo();
			canInfo.setCandidateId(candidateId);
			canInfo.setCandidateName(request.getParameter("canditatename"));
			canInfo.setClassId(Integer.parseInt(request.getParameter("class")));
			canInfo.setSectionId(Integer.parseInt(request.getParameter("section")));
			canInfo.setAddress(request.getParameter("canditateaddress"));
			canInfo.setEmail(request.getParameter("canditateemail"));
			canInfo.setPhone(request.getParameter("canditatephone"));
			
			System.out.println("NAME--->"+canInfo.getCandidateName());
			
			
			CandidateModal cm = new CandidateModal();
			cm.editCandidate(canInfo);
			System.out.println(candidateId);
			
			candidateId = null;
			listCandidates(request, response);
			rd = getServletContext().getRequestDispatcher("/Candidate/ListCandidates.jsp");
			rd.forward(request, response);
			
			
			*/
			
			
			String candidateId = request.getParameter("candidateid");
			ArrayList<CandidateInfo> candidateListforEdit = getCandidate(request, response, candidateId);
			ArrayList<CandidateInfo> classInfo = Suppliments.getClassDetails();
			request.setAttribute("classList", classInfo);
			request.setAttribute("canListEdit", candidateListforEdit);			
			request.setAttribute("fromWhere", "fromEditCandidate");			
			listCandidates(request,response);
			rd = getServletContext().getRequestDispatcher("/Candidate/CandidateManagement.jsp");
			rd.forward(request, response);
		}
		
		else if(request.getParameter("action").equalsIgnoreCase(BULK_UPLOAD_CANDIDATE)){
			
			rd = getServletContext().getRequestDispatcher("/Candidate/UploadCandidateDetails.jsp");
			rd.forward(request, response);
			
		}
		else if(request.getParameter("action").equalsIgnoreCase(EDIT_SAVE)) {
			
			candidateId = request.getParameter("canditateid");
			System.out.println(candidateId);
			CandidateInfo canInfo = new CandidateInfo();
			canInfo.setCandidateId(candidateId);
			canInfo.setCandidateName(request.getParameter("canditatename"));
			canInfo.setClassId(Integer.parseInt(request.getParameter("class")));
			canInfo.setSectionId(Integer.parseInt(request.getParameter("section")));
			canInfo.setAddress(request.getParameter("canditateaddress"));
			canInfo.setEmail(request.getParameter("canditateemail"));
			canInfo.setPhone(request.getParameter("canditatephone"));
			
			System.out.println("NAME--->"+canInfo.getCandidateName());
			
			
			CandidateModal cm = new CandidateModal();
			boolean editSuccess = cm.editCandidate(canInfo);
			if(editSuccess){
				response.getWriter().write("Candidate details Updated");
			}
			else{
				response.getWriter().write("Unable to update Candidate Details");
			}
			
			
			showRegisterScreen(request, response);
			
		}
		else if(request.getParameter("action").equalsIgnoreCase(UPLOAD_CANDIDATE_DETAILS)) {
			
			String filenameandpath = uploadFileToServer(request,response);
			CandidateModal cm = new  CandidateModal();
			int recordsAdded = cm.insertFileDataToDB(filenameandpath);
			
			if(recordsAdded == 1){
				
				request.setAttribute("message", "Candidate Details Uploaded Successfully");
				
			}
			else if(recordsAdded == -1){
				request.setAttribute("message", "Error uploading candidate Details..");
			}
			rd = getServletContext().getRequestDispatcher("/Candidate/UploadCandidateDetails.jsp");
			rd.forward(request, response);
		}
		
	}
	
	private void listCandidates(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		
		CandidateModal cm = new CandidateModal();
		List<CandidateInfo> candidateList;
		
		int page = 1;
        int recordsPerPage = 15;
        if(request.getParameter("page") != null)
        page = Integer.parseInt(request.getParameter("page"));
       /* 
        
        
        if(candidateId != null || candidateName != null || order != null){
        	 
        	candidateList = cm.listCandidates((page-1)*recordsPerPage,recordsPerPage,candidateId,candidateName,order);
        	 candidateId = null;
        	 candidateName = null;
        	 order = null;
        	 
        }
        else
        {
        	
        }*/
        candidateList = cm.listCandidates((page-1)*recordsPerPage,recordsPerPage);
        int noOfRecords = new CandidateImpl().getNumOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        
        System.out.println("Num of page="+noOfPages);
        request.setAttribute("candList", candidateList);
        request.setAttribute("totalRecords", noOfRecords);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
    }
	private void showRegisterScreen(HttpServletRequest request, HttpServletResponse response) {
		
		String seqNum = Suppliments.generateIdSequence("Candidate");
		HttpSession session = request.getSession();
		
		session.setAttribute("seqnum", seqNum);
		
		//Generate the sequence number
		//Pull the class and the section info from the database
		CandidateModal candidateModal = new CandidateModal();
		ArrayList<CandidateInfo> classList = candidateModal.getClassData();
		getServletContext().setAttribute("classInfo", classList);
	}
	
	private ArrayList<CandidateInfo> getCandidate(HttpServletRequest request,HttpServletResponse response,String candidateId) {
		CandidateModal cm = new CandidateModal();
		ArrayList<CandidateInfo> canList = cm.getCandidate(candidateId);
		return canList;
		/*int classid = canList.get(0).getClassId();
		int sectionid = canList.get(0).getSectionId();
		request.setAttribute("classSelected", classid);
		request.setAttribute("sectionSelect", sectionid);*/
	}
	
	private String uploadFileToServer(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		

		   // Check that we have a file upload request
		   isMultipart = ServletFileUpload.isMultipartContent(request);
		   String filenameandpath="";
		   response.setContentType("text/html");
		   java.io.PrintWriter out = response.getWriter( );
		   
		   DiskFileItemFactory factory = new DiskFileItemFactory();
		   // maximum size that will be stored in memory
		   factory.setSizeThreshold(maxMemSize);
		   // Location to save data that is larger than maxMemSize.
		   factory.setRepository(new File("D:\\temp"));

		   // Create a new file upload handler
		   ServletFileUpload upload = new ServletFileUpload(factory);
		   // maximum file size to be uploaded.
		   upload.setSizeMax( maxFileSize );

		   try{ 
		   // Parse the request to get file items.
		   List fileItems = upload.parseRequest(request);
			
		   // Process the uploaded file items
		   Iterator i = fileItems.iterator();

		   while ( i.hasNext () ) 
		   {
		      FileItem fi = (FileItem)i.next();
		      if ( !fi.isFormField () )	
		      {
		         // Get the uploaded file parameters
		         String fieldName = fi.getFieldName();
		         String fileName = fi.getName();
		         String contentType = fi.getContentType();
		         boolean isInMemory = fi.isInMemory();
		         long sizeInBytes = fi.getSize();
		         // Write the file
		         if( fileName.lastIndexOf("\\") >= 0 ){
		            file = new File( filePath + 
		            fileName.substring( fileName.lastIndexOf("\\"))) ;
		         }else{
		            file = new File( filePath + 
		            fileName.substring(fileName.lastIndexOf("\\")+1)) ;
		         }
		         fi.write( file ) ;
		         
		         System.out.println("PATH and file is "+filePath+fileName);
		         out.println("Uploaded Filename: " + fileName + "<br>");
		         
		         filenameandpath = filePath+fileName;
		         
		         
		         
		      }
		   }
		  
		}catch(Exception ex) {
		    System.out.println(ex);
		}
		   return filenameandpath;
		
		
	}
	
	

}

