package testengine.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import testengine.beans.ClassInfo;
import testengine.beans.ModuleInfo;
import testengine.beans.QuestionInfo;
import testengine.beans.QuestionSearchInfo;
import testengine.beans.SubjectInfo;
import testengine.beans.UserInfo;
import testengine.dao.user.UserImpl;
import testengine.modal.CandidateModal;
import testengine.modal.QuestionModal;
import testengine.modal.SubjectModal;
import testengine.utility.Suppliments;

/**
 * Servlet implementation class QuestionController
 */
public class QuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher rd;
	private final String LOAD_ADD_QUESTIONS_PAGE = "loadAddQuestionsPage"; 
	private final String ADD_QUESTION = "Add Question";
	private final String LIST_QUESTION = "listQuestionsPage";
	private final String SEARCH_QUESTIONS = "searchQuestions";
	
	private final int recordsPerPage = 15;


	private boolean isMultipart;
	private String filePath;
	private int maxFileSize = 5000 * 1024;
	private int maxMemSize = 4 * 1024;
	private File file ;
	String fieldName;
	String fileName;
	FileItem fi;
	DiskFileItemFactory factory;
	Map<String,String> questionMap = new HashMap<String,String>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
  
    public QuestionController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(){
    filePath = 
             getServletContext().getInitParameter("question-upload"); 
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
	long time = System.currentTimeMillis();
	
	String subfolder = ""+time;
	String finalPath = filePath+subfolder;
	HttpSession userSession = request.getSession();
	String username = userSession.getAttribute("user").toString();
	questionMap.put("user", username);
	questionMap.put("filepath", finalPath);
	
	isMultipart = ServletFileUpload.isMultipartContent(request);
		if(isMultipart) {
			response.setContentType("text/html");
	     java.io.PrintWriter out = response.getWriter();
	     if( !isMultipart ){         
	        return;
	     }
	     factory = new DiskFileItemFactory();
	     // maximum size that will be stored in memory
	     factory.setSizeThreshold(maxMemSize);
	     // Location to save data that is larger than maxMemSize.
	     factory.setRepository(new File("c:\\temp"));
	     
	     File f = new File(filePath+subfolder);
	     if(!f.exists()){
	    	 f.mkdirs();
	    }
	
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
	             fi = (FileItem)i.next();
	            if ( !fi.isFormField () ) 
	            {
	               // Get the uploaded file parameters
	               fieldName = fi.getFieldName();
	               fileName = fi.getName();
	               String contentType = fi.getContentType();
	               boolean isInMemory = fi.isInMemory();
	               long sizeInBytes = fi.getSize();
	               // Write the file
		               if(fileName.trim().length() > 0){
			               if( fileName.lastIndexOf("\\") >= 0 ){
			                  file = new File( filePath +subfolder+"\\"+ fileName.substring( fileName.lastIndexOf("\\"))) ;
			               }else{
			                  file = new File( filePath +subfolder+"\\"+ fileName.substring(fileName.lastIndexOf("\\")+1)) ;
			               }
		               }
	               questionMap.put(fieldName, fileName);	              
	            }
	            else
	            {
		            String name  = fi.getFieldName();		            
		            questionMap.put(name, fi.getString());
	            }
	            if(file != null) {
	            	fi.write( file ) ;
	            }
	         }
	         
	         int saved = saveQuestion(questionMap);
	         if(saved > 0){	        	
	        	 request.setAttribute("save", "Question Added");
	        	 showQuestionScreen(request,response);
	         }
	         else
	         {
	        	 request.setAttribute("save", "Unable to save question");
	        	 showQuestionScreen(request,response);
	         }
	         
	         rd = getServletContext().getRequestDispatcher("/Questions/AddQuestionsTemp.jsp");
	         rd.forward(request, response);
	         
	        
	      }catch(Exception ex) {
	          System.out.println("The Exception is "+ex);
	      }
}
else if(request.getParameter("action").equals(LOAD_ADD_QUESTIONS_PAGE)) {
	showQuestionScreen(request,response);
	rd = getServletContext().getRequestDispatcher("/Questions/AddQuestionsTemp.jsp");
	rd.forward(request, response);
}
else if(request.getParameter("action").equals(LIST_QUESTION)) {
	
	loadQuestionManagement(request,response);
	/*
	rd = getServletContext().getRequestDispatcher("/Questions/QuestionListForEdit.jsp");
	rd.forward(request, response);
	*/
}
else if(request.getParameter("action").equals(SEARCH_QUESTIONS))
{
	int page = 1;
	
	QuestionSearchInfo questionSearchInfo = new QuestionSearchInfo();	
	questionSearchInfo.setQuestionId(request.getParameter("questionId"));
	questionSearchInfo.setKeyword(request.getParameter("keyword"));
	questionSearchInfo.setClassId(Integer.parseInt(request.getParameter("class")));
	questionSearchInfo.setSubjectId(request.getParameter("subject"));
	questionSearchInfo.setAddedby((request.getParameter("addedby")));
	
	ArrayList<QuestionInfo> searchList = searchQuestions(questionSearchInfo);
	request.setAttribute("questList", searchList);
	
	ArrayList<ClassInfo> classList = loadClass();
	request.setAttribute("cList", classList);
	
	ArrayList<SubjectInfo> subList = Suppliments.getSubjects();
	request.setAttribute("sList", subList);
	
	/*int totalNumOfRec = new UserImpl().getNumOfRecords();
	request.setAttribute("totalUsers", totalNumOfRec);*/
	

	
	int noOfRecords = new UserImpl().getNumOfRecords();
    int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
    
    request.setAttribute("totalRecords", noOfRecords);
    request.setAttribute("noOfPages", noOfPages);
    request.setAttribute("currentPage", page);
    rd = getServletContext().getRequestDispatcher("/Questions/ListQuestions.jsp");
	rd.forward(request, response);
	
	
	
	
	
}

//else if(request.getParameter("action").equals(ADD_QUESTION)){}
}

private void showQuestionScreen(HttpServletRequest request, HttpServletResponse response) {
	String seqNum = Suppliments.generateIdSequence("Question");
	HttpSession session = request.getSession();
	session.setAttribute("seqnum", seqNum);
	//Generate the sequence number
	//Pull the class and the section info from the database
	CandidateModal candidateModal = new CandidateModal();
	ArrayList<CandidateInfo> classList = candidateModal.getClassData();
	getServletContext().setAttribute("classInfo", classList);
	SubjectModal subjectModal = new SubjectModal();
	ArrayList<SubjectInfo> subjectList = subjectModal.getSubjectList();
	getServletContext().setAttribute("sList", subjectList);
}

private int saveQuestion(Map<String,String> questionMap) {
	
	QuestionModal questionModal = new QuestionModal();
	return questionModal.saveQuestion(questionMap);
	
	
}

public void loadQuestionManagement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	int page = 1;
	ArrayList<QuestionInfo> questionList = listQuestions(request,response );
	request.setAttribute("questList", questionList);
	
	ArrayList<ClassInfo> classList = loadClass();
	request.setAttribute("cList", classList);
	
	ArrayList<SubjectInfo> subList = Suppliments.getSubjects();
	request.setAttribute("sList", subList);
	
	/*int totalNumOfRec = new UserImpl().getNumOfRecords();
	request.setAttribute("totalUsers", totalNumOfRec);*/
	

	
	int noOfRecords = new UserImpl().getNumOfRecords();
    int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
    
    request.setAttribute("totalRecords", noOfRecords);
    request.setAttribute("noOfPages", noOfPages);
    request.setAttribute("currentPage", page);
    rd = getServletContext().getRequestDispatcher("/Questions/QuestionListForEdit.jsp");
	rd.forward(request, response);
	
	
}
private ArrayList<QuestionInfo> listQuestions(HttpServletRequest request,HttpServletResponse response) {
	QuestionModal questionModal = new QuestionModal();
	return questionModal.listAllQUestions();
	
}
private ArrayList<ClassInfo> loadClass() {
	
	ArrayList<ClassInfo> classList = new ArrayList<ClassInfo>();
	
	CandidateModal cm = new CandidateModal();
	ArrayList al = cm.getClassData();
	classList.addAll(al);
	return classList;
}
public ArrayList<QuestionInfo> searchQuestions(QuestionSearchInfo questionSearchInfo) {
	QuestionModal questionModal = new QuestionModal();
	return questionModal.searchQuestions(questionSearchInfo);
		
	
}

}

