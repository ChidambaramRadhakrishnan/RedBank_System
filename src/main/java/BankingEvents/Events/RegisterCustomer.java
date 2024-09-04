package BankingEvents.Events;


import java.io.IOException;

import BaseOperations.Operations.BankingOperationsKeywords;
import POJO.CustomerOperations.CustomerDetails;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class RegisterCustomer
 */
public class RegisterCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterCustomer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		session.setAttribute("password", request.getParameter("confirmPassword"));
		session.setAttribute("email", request.getParameter("email"));
		
		System.out.println((String) session.getAttribute("email")+" --- confirm mail");
		
		System.out.println("outlet");
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		CustomerDetails customerDetails = new CustomerDetails();
		
		customerDetails.setFirstName(request.getParameter("firstname"));
		customerDetails.setLastName(request.getParameter("lastname"));
		customerDetails.setPhoneNumber(request.getParameter("phonenumber"));
		customerDetails.setEmail(request.getParameter("email"));
		customerDetails.setCity(request.getParameter("city"));
		customerDetails.setState(request.getParameter("state"));
		customerDetails.setAccountType(request.getParameter("accountType"));
		customerDetails.setBalance(request.getParameter("minimumdeposit"));
		customerDetails.setPassword(request.getParameter("loginPassword"));
		customerDetails.setConfirmPassword(request.getParameter("confirmPassword"));
		customerDetails.setMPIN(request.getParameter("mpin"));
		customerDetails.setMTPIN(request.getParameter("mtpin"));
		
		BankingOperationsKeywords operationKeywords = new BankingOperationsKeywords();
		
		HttpSession session = request.getSession();
		
		
		String addUser = operationKeywords.addUser(customerDetails);
		
		RequestDispatcher rd = request.getRequestDispatcher(addUser);
		
		rd.forward(request, response);
		
	}

}
