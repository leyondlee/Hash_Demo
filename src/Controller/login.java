package Controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Hash;
import Model.*;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
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
		
		UserDB udb = new UserDB();
		User user = udb.getUser(username);
		if (user != null) { //Check if user exists
			String pass = "0";
			
			//Calculate hash from input password
			byte[] salt = Hash.hexStringToByteArray(user.getSalt());
			String hpass = Hash.asHex(Hash.hashPassword(password.toCharArray(), salt, 1000, 512));
			
			String[] values = {password,user.getSalt(),hpass,user.getPassword()};
			request.setAttribute("values", values);
			
			if (hpass.equals(user.getPassword())) { //Check if password hashes are the same
				pass = "1";
			}
			
			request.setAttribute("pass", pass);
			
			RequestDispatcher rd = request.getRequestDispatcher("loginView.jsp");
			rd.forward(request, response);
		} else {
			out.println("User does not exist");
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
