package BankingEvents.Events;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

import java.io.IOException;

import BaseOperations.Operations.BankingOperationsKeywords;

/**
 * Servlet implementation class FundTransfer
 */
public class FundTransfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public String CurrentBeneficiary;
	
	public String TransferringAmount;
	
	public String CurrentCustomerAccountNumber;
	
	public String MTPIN;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FundTransfer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		session.setAttribute("CurrentBeneficiare", request.getParameter("Beneficiare"));
		
		CurrentBeneficiary = (String) session.getAttribute("CurrentBeneficiare");
		
		session.setAttribute("TransferAmount", request.getParameter("Amount"));
		
		TransferringAmount = (String) session.getAttribute("TransferAmount");
		
		CurrentCustomerAccountNumber = (String) session.getAttribute("username");
		
		
		MTPIN = request.getParameter("mtpin");
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
		BankingOperationsKeywords operationsKeywords = new BankingOperationsKeywords();	
		Boolean MTPINstatus = operationsKeywords.MTPINVerification(CurrentCustomerAccountNumber, MTPIN);
		
		String fundTransferStatus = null;
		
		if(MTPINstatus == true) {
			fundTransferStatus =operationsKeywords.FundTransfer(CurrentBeneficiary, CurrentCustomerAccountNumber, TransferringAmount);
			RequestDispatcher rd = request.getRequestDispatcher(fundTransferStatus);
			rd.forward(request, response);
		}else {
			System.out.println("is it working");
			String action = "MTPINError.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(action);
			rd.forward(request, response);
		}
		 
		
		
	}

}
