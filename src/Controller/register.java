package Controller;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.*;
import util.*;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//Generate salt and hash password
		byte[] salt = Hash.getSalt();
		String hpass = Hash.asHex(Hash.hashPassword(password.toCharArray(), salt, 1000, 512));
		
		UserDB udb = new UserDB();
		if (udb.getUser(username) == null) { //Check if user exists
			//Add user to database
			udb.addUser(username, hpass, Hash.asHex(salt));
			
			String[] values = {password,Hash.asHex(salt),hpass};
			request.setAttribute("values", values);
			
			RequestDispatcher rd = request.getRequestDispatcher("registerView.jsp");
			rd.forward(request, response);
		} else {
			out.println("User already exists");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
