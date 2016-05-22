package testengine.controller.subject;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import testengine.beans.BatchInfo;
import testengine.beans.SubjectInfo;
import testengine.modal.BatchModal;
import testengine.modal.SubjectModal;
import testengine.utility.Suppliments;

/**
 * Servlet implementation class SubjectController
 */
public class SubjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADD_SUBJECT = "addSubject";
	private static final String ADD_NEW_SUBJECT = "addNewSubject";
	RequestDispatcher rd;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubjectController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("action").equalsIgnoreCase(ADD_SUBJECT)) {
			generateSubjectId(request,response);
			rd = getServletContext().getRequestDispatcher("/Subject/AddSubject.jsp");
			rd.forward(request, response);
		}
		else if(request.getParameter("action").equalsIgnoreCase(ADD_NEW_SUBJECT))  {
			
			

			
			String subjectId = request.getParameter("subjectId");
			String subjectName = request.getParameter("subjectname");
			String subjectDesc = request.getParameter("subjectdesc");
			
			SubjectInfo subjectInfo = new SubjectInfo();
			subjectInfo.setSubjectId(subjectId);
			subjectInfo.setSubjectName(subjectName);
			subjectInfo.setSubjectDesc(subjectDesc);
			
			SubjectModal subjectModal = new SubjectModal();
			boolean subjectCreated = subjectModal.addSubject(subjectInfo);
			rd = getServletContext().getRequestDispatcher("/Subject/AddSubject.jsp");
			
			
			if(subjectCreated){
				request.setAttribute("status", "Subject Added");
				generateSubjectId(request, response);
			}
			else
			{
				request.setAttribute("status", "Unable to Add Subject");
			}
			rd.forward(request, response);
			
			
		
			
		}
		
		
	}
	
	private void generateSubjectId(HttpServletRequest request, HttpServletResponse response) {
		
		String seqNum = Suppliments.generateIdSequence("subject");
		HttpSession session = request.getSession();
		session.setAttribute("seqnum", seqNum);
	}

}
