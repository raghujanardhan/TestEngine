package testengine.controller.questionpaper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import testengine.modal.QuestionModal;
import testengine.modal.QuestionPaperModal;

/**
 * Servlet implementation class UploadQPController
 */
public class UploadQPController extends HttpServlet {

	private boolean isMultipart;
	private String filePath;
	private int maxFileSize = 50 * 1024;
	private int maxMemSize = 4 * 1024;
	private File file ;
	
	HashMap<String,String> fileMap = new HashMap<String,String>();

	public void init( ){
	   // Get the file location where it would be stored.
	   filePath = 
	          getServletContext().getInitParameter("file-upload"); 
	   
	}
	public void doPost(HttpServletRequest request, 
	            HttpServletResponse response)
	           throws ServletException, java.io.IOException {
	   // Check that we have a file upload request
	   isMultipart = ServletFileUpload.isMultipartContent(request);
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
	      if(fi.isFormField ())
	      {
	    	  String fieldName = fi.getFieldName();	   
	          fileMap.put(fieldName, fi.getString());
	      }
	      if ( !fi.isFormField () )	
	      {
	         // Get the uploaded file parameters
	         String fieldName = fi.getFieldName();
	         String fileName = fileMap.get("qpid")+"_"+fi.getName();
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
	         String filenameandpath = filePath+fileName;	         
	         fileMap.put("filepath", filenameandpath);
	      }
	      
	   }
	  
	}catch(SizeLimitExceededException ex) {
		request.setAttribute("uploadstatus", "<font color='red'>Size exceeded..</font>");
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/Questions/QPUpload.jsp");
		rd.forward(request, response);
		return;
	   
	}
	catch(Exception e) {
		System.out.println("UPLOAD -->"+e);
		
	}
	QuestionPaperModal paperModel = new QuestionPaperModal();
	int uploadStatus = paperModel.uploadQuestionPaperDetails(fileMap);
	if(uploadStatus > 0 ) {
		
		request.setAttribute("uploadstatus", "<font color='blue'>Question Paper Uploaded</font>");
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/Questions/QPUpload.jsp");
		rd.forward(request, response);
	}
	else
	{
		request.setAttribute("uploadstatus", "<font color='red'>Unable to upload</font>");
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/Questions/QPUpload.jsp");
		rd.forward(request, response);
	}
	
	
	}
	public void doGet(HttpServletRequest request, 
	                    HttpServletResponse response)
	     throws ServletException, java.io.IOException {
	     
	     throw new ServletException("GET method used with " +
	             getClass( ).getName( )+": POST method required.");
	} 
}
