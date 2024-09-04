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
 * Servlet implementation class UpdatedMpin
 */

public class UpdatedMpin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public String MPIN;
	public String AccountNo;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatedMpin() {
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
		
		session.setAttribute("Mpin", request.getParameter("mpin"));
		
		MPIN = (String) session.getAttribute("Mpin");
		AccountNo = (String) session.getAttribute("username");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		BankingOperationsKeywords operationsKeywords = new BankingOperationsKeywords();
		
		operationsKeywords.UpdateSingleField(AccountNo, "MPIN", MPIN);
		
		RequestDispatcher rd = request.getRequestDispatcher("Updated.jsp");
		
		rd.forward(request, response);
	}

}
