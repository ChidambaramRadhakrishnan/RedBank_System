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
import UtilityOperations.Operations.Operations;

/**
 * Servlet implementation class DepositAmount
 */
public class DepositAmount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public String CurrentCustomerAccountNumber;
	
	public String MPIN;
	
	public String DepositAmount;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepositAmount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		CurrentCustomerAccountNumber = (String) session.getAttribute("username");
		
		session.setAttribute("CustomerMPIN", request.getParameter("mpin"));
		
		MPIN = (String) session.getAttribute("CustomerMPIN"); 
		
		session.setAttribute("depositAmount", request.getParameter("DepositAmount"));
		
		DepositAmount = (String) session.getAttribute("depositAmount");
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String page ="";
		BankingOperationsKeywords operationsKeywords = new BankingOperationsKeywords();
		
		if(operationsKeywords.MPINVerification(CurrentCustomerAccountNumber, MPIN) == true) {
				operationsKeywords.DepositAmount(CurrentCustomerAccountNumber, DepositAmount);
				page = "DepositSuccess.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);
		}else {
			page ="MPINError.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
		
		
		
		
		
	}

}
