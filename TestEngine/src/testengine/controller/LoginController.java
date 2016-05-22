package testengine.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import testengine.beans.UserInfo;
import testengine.exceptions.UserNotFoundException;
import testengine.modal.LoginModal;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher rd;
	static final String USER_LOGIN = "Login";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("action").equalsIgnoreCase(USER_LOGIN)){
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			UserInfo userInfo = new  UserInfo();
			userInfo.setUserName(username);
			userInfo.setPassword(password);
			
			if(validateUser(userInfo)) {
				HttpSession userSession = request.getSession();
				userSession.setAttribute("user", username);
				String assignedModules = loadUserModuleDetails(userInfo);
				String modArray[] = assignedModules.split(",");
				ArrayList modList = new ArrayList();
				for(int i=0;i<modArray.length;i++) {
					modList.add(modArray[i]);
				}
				userSession.setAttribute("assignedmodules", modList);
				rd = getServletContext().getRequestDispatcher("/TestConfiguration.jsp");
				rd.forward(request, response);
			}
			else
			{
				request.setAttribute("validUser", "Invalid Credentials");
				rd = getServletContext().getRequestDispatcher("/CETESTWelcome.jsp");
				rd.forward(request, response);
			}
			
	
			
			
		}
	}
	public boolean validateUser(UserInfo userInfo) {
		
		LoginModal loginModel = new LoginModal();
		boolean validUser = false;
		try
		{
			validUser = loginModel.validateUser(userInfo);
		}
		catch(UserNotFoundException une) {
			//This has to be dispatched....
			System.out.println(une);
			
		}
		return validUser;
		
	}
	public String loadUserModuleDetails(UserInfo userInfo) {
		
		LoginModal loginModel = new LoginModal();
		return loginModel.loadUserModuleDetails(userInfo);
		
	}

}
