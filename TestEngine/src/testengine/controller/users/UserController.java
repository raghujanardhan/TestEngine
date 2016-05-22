package testengine.controller.users;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import testengine.beans.ModuleInfo;
import testengine.beans.UserInfo;
import testengine.dao.CandidateImpl;
import testengine.dao.user.UserImpl;
import testengine.modal.CandidateModal;
import testengine.modal.UserModal;

/**
 * Servlet implementation class UserController
 */
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SAVE_USER = "saveUser";
	private static final String DELETE_USER = "deleteUser";
	private static final String EDIT_USER = "editUser";
	private static final String EDIT_SAVE_USER = "editandsaveUser";
	private static final String SEARCH_USER = "searchUser";
	
	String fromWhere="";
	
	RequestDispatcher rd ;
	private boolean isEdit = false;
	String moduleSel[];
	int recordsPerPage = 10;
	int page = 1;
	//Demo
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
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
		
		if(request.getParameter("page") != null)
        {
        	page = Integer.parseInt(request.getParameter("page"));
        }
		
		
		if(request.getParameter("action").equalsIgnoreCase("userManagement")) {
			loadUserManagement(request,response);
		}
		else if(request.getParameter("action").equalsIgnoreCase(SAVE_USER)) {			
			String userName = request.getParameter("username");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String phone  = request.getParameter("phone");
			String modulesSelected = request.getParameter("modules");
			
			UserInfo user = new UserInfo();
			user.setUserName(userName);
			user.setName(name);
			user.setEmail(email);
			user.setMobile(phone);
			user.setModulesSelected(modulesSelected);
			
			UserModal userModal = new UserModal();
			boolean userCreated = userModal.saveUser(user);
			
			response.setContentType("text/html;charset=UTF-8");
			if(userCreated){
				response.getWriter().write("User Created");
			}
			else{
				response.getWriter().write("Unable to create user");
			}
		}
		else if(request.getParameter("action").equals(DELETE_USER)){
			
			String userId = request.getParameter("userId");
			UserModal userModal = new UserModal();
			int deleted = userModal.deleteUser(userId);
			if(deleted > 0) {
				loadUserManagement(request, response);			
			}
		}
		else if(request.getParameter("action").equals(EDIT_USER)) {
			isEdit = true;
			String userId = request.getParameter("userId");
			UserModal userModal = new UserModal();
			ArrayList<UserInfo> userListforEdit = userModal.loadUser(userId);
			
			String modules = userListforEdit.get(0).getModulesSelected();			
			if(modules.trim().length() > 0){
			ArrayList<Integer> mList = new ArrayList<Integer>();
			moduleSel = modules.split(",");
			
			for(int i = 0;i<moduleSel.length;i++) {
				mList.add(Integer.parseInt(moduleSel[i]));
			}
			request.setAttribute("modList", mList);
			}
			request.setAttribute("uListEdit", userListforEdit);
			request.setAttribute("fromWhere", "fromEditUser");			
			loadUserManagement(request, response);	
			
		}
		else if(request.getParameter("action").equals(EDIT_SAVE_USER)) {
			
			String userId = request.getParameter("userid");			
			String userName = request.getParameter("username");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String phone  = request.getParameter("phone");
			String modulesSelected = request.getParameter("modules");
			
			UserInfo user = new UserInfo();
			user.setUserId(Integer.parseInt(userId));
			user.setUserName(userName);
			user.setName(name);
			user.setEmail(email);
			user.setMobile(phone);
			user.setModulesSelected(modulesSelected);
			
			UserModal userModal = new UserModal();
			boolean userEdited = userModal.editUser(user); 
			
			response.setContentType("text/html;charset=UTF-8");
			if(userEdited){
				response.getWriter().write("User details Updated");
			}
			else{
				response.getWriter().write("Unable to Update user Details");
			}
			
		}
		else if(request.getParameter("action").equals(SEARCH_USER)) {
			
			String searchParam = request.getParameter("searchParam");
			ArrayList<UserInfo> usersList =  searchUsers(request,response);
			
			HttpSession session = request.getSession();
			session.setAttribute("uList", usersList);
			//request.setAttribute("uList", usersList);
			
			fromWhere = "search";
			
			ArrayList<ModuleInfo> moduleList = getAllModules();
			request.setAttribute("mList", moduleList);
			
			int totalNumOfRec = new UserImpl().getNumOfRecordsForSearch(searchParam);
			request.setAttribute("totalUsers", totalNumOfRec);
			int noOfRecords = new UserImpl().getNumOfRecordsForSearch(searchParam);
	        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
	        
	        request.setAttribute("totalRecords", noOfRecords);
	        request.setAttribute("noOfPages", noOfPages);
	        request.setAttribute("currentPage", page);
	        rd = getServletContext().getRequestDispatcher("/Users/UsersList.jsp");
			rd.forward(request, response);
		}
	}
	public ArrayList<UserInfo> listUsers(HttpServletRequest request, HttpServletResponse response) {
		
        //int recordsPerPage = 15;
        if(request.getParameter("page") != null)
        {
        	page = Integer.parseInt(request.getParameter("page"));
        }
		UserModal userModel = new UserModal();
		request.setAttribute("currentPage", page);
		return userModel.listUsers((page-1)*recordsPerPage,recordsPerPage);
		
	}
	
	public ArrayList<ModuleInfo> getAllModules(){
		UserModal userModel = new UserModal();
		return userModel.getAllModules();
	}
	
	public void loadUserManagement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//This will be a generic function to load the users but the users list will be bases on from where it is getting loaded.....
		
		int page = 1;
		System.out.println("From where -->"+fromWhere);
			HttpSession session = request.getSession();
			
			if(fromWhere.equals("search") && session.getAttribute("uList") != null) {
				request.setAttribute("uList", session.getAttribute("uList"));
			}
			
			else if(fromWhere.equals("search") && session.getAttribute("uList") == null){
			  
			  ArrayList<UserInfo> usersList = searchUsers(request, response);
			  session.setAttribute("uList", usersList);
//			  request.setAttribute("uList", usersList);
			  
		  }
			else
			  {
				  ArrayList<UserInfo> usersList = listUsers(request,response );
				  session.setAttribute("uList", usersList);
				  //request.setAttribute("uList", usersList);
			  }
			ArrayList<ModuleInfo> moduleList = getAllModules();
			request.setAttribute("mList", moduleList);
			
			int totalNumOfRec = new UserImpl().getNumOfRecords();
			request.setAttribute("totalUsers", totalNumOfRec);
			
	
			
			int noOfRecords = new UserImpl().getNumOfRecords();
	        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
	        
	        request.setAttribute("totalRecords", noOfRecords);
	        request.setAttribute("noOfPages", noOfPages);
	        request.setAttribute("currentPage", page);
	        
	       
	        rd = getServletContext().getRequestDispatcher("/Users/UserManagement.jsp");
	        
			rd.forward(request, response);
	}
	public ArrayList<UserInfo> searchUsers(HttpServletRequest request,HttpServletResponse response) {
		String searchParam = request.getParameter("searchParam");
		int page = 1;
        //int recordsPerPage = 15;        
		UserModal userModel = new UserModal();		
		return userModel.searchUsers(searchParam,(page-1)*recordsPerPage,recordsPerPage);
		
	}
	public ArrayList<UserInfo> loadUser(String userId) {
		
		UserModal userModel = new UserModal();
		return userModel.loadUser(userId);
		
	}
	

}
