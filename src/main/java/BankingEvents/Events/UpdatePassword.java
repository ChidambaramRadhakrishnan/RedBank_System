package BankingEvents.Events;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import BaseOperations.Operations.BankingOperationsKeywords;

/**
 * Servlet implementation class UpdatePassword
 */

public class UpdatePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public String password;
	public String AccountNo;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpSession session = request.getSession();
		
		session.setAttribute("Password", request.getParameter("password"));
		
		password = (String) session.getAttribute("Password");
		AccountNo = (String) session.getAttribute("username");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		BankingOperationsKeywords operationsKeywords = new BankingOperationsKeywords();
		
		int count = operationsKeywords.UpdateSingleField(AccountNo, "ConfirmPassword", password);
		
		if(count >=1) {
			RequestDispatcher rd = request.getRequestDispatcher("Updated.jsp");
			
			rd.forward(request, response);
		}
		
		
	}

}
