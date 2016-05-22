package testengine.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import testengine.exceptions.UserNotFoundException;
import testengine.modal.UserValidate;

/**
 * Servlet implementation class ValidateUser
 */
public class ValidateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher rd;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateUser() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {/*
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		
		UserValidate uv = new UserValidate();
		try
		{
			
			if(uv.validateUser(userName, password))
			{
				
				rd = getServletContext().getRequestDispatcher("/TestConfiguration.jsp");
				rd.forward(request, response);
			}
		}
		catch(UserNotFoundException une){
			
			request.setAttribute("message", une);
			rd = getServletContext().getRequestDispatcher("/WelcomeScreen.jsp");
			rd.forward(request, response);
			
		}
		
	*/}

}
