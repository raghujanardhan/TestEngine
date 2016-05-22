package testengine.controller.questionpaper;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import testengine.beans.ClassInfo;
import testengine.beans.SectionInfo;
import testengine.beans.SubjectInfo;
import testengine.modal.BatchModal;
import testengine.modal.CandidateModal;
import testengine.modal.SubjectModal;
import testengine.utility.Suppliments;

/**
 * Servlet implementation class QuestionPaperController
 */
public class QuestionPaperController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SHOW_UPLOAD_QP_PAGE = "loadUploadQuestionPaperPage";
	RequestDispatcher rd = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionPaperController() {
        super();
        
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("action").equals(SHOW_UPLOAD_QP_PAGE)) {
			showQPUploadScreen(request,response);
			rd = getServletContext().getRequestDispatcher("/Questions/QPUpload.jsp");
			rd.forward(request, response);
		}
		
		
		
	}
	public void showQPUploadScreen(HttpServletRequest request, HttpServletResponse response) {
		//Autogenerate QP id
		//class
		//section
		//upload paper
		String seqNum = Suppliments.generateIdSequence("questionpaper");
		HttpSession session = request.getSession();
		session.setAttribute("seqnum", seqNum);
		ArrayList<ClassInfo> classList = loadClass();
		ArrayList<SectionInfo> sectionList = loadSections();
		ArrayList<SubjectInfo> subjectList  = loadSubjects();
		
		request.setAttribute("cList", classList);
		request.setAttribute("sList", sectionList);
		request.setAttribute("subList", subjectList);
		
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
	private ArrayList<SubjectInfo> loadSubjects() {
		
		SubjectModal sm = new SubjectModal();
		return sm.getSubjectList();
		
	}

}
