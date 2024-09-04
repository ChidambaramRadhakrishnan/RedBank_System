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
 * Servlet implementation class UpdateMTPIN
 */
public class UpdateMTPIN extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public String MTPIN;
	public String AccountNo;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMTPIN() {
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
		
		session.setAttribute("Mtpin", request.getParameter("mtpin"));
		
		MTPIN = (String) session.getAttribute("Mtpin");
		AccountNo = (String) session.getAttribute("username");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		BankingOperationsKeywords operationsKeywords = new BankingOperationsKeywords();
		
		operationsKeywords.UpdateSingleField(AccountNo, "MTPIN", MTPIN);
		
		RequestDispatcher rd = request.getRequestDispatcher("Updated.jsp");
		
		rd.forward(request, response);
	}

}
