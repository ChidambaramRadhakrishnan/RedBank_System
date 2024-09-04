package BankingEvents.Events;

import java.io.IOException;

import BaseOperations.Operations.BankingOperationsKeywords;
import POJO.CustomerOperations.AuthenticateUser;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class AuthenticateExistingCustomer
 */
public class AuthenticateExistingCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String AccountNo;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthenticateExistingCustomer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
        session.setAttribute("username", request.getParameter("accountNumber"));
        session.setAttribute("password", request.getParameter("password"));
        
        AccountNo = (String) session.getAttribute("username");
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		AuthenticateUser userDatas = new AuthenticateUser();
		
		userDatas.setAccountNumber(request.getParameter("accountNumber"));
		
		userDatas.setPassword(request.getParameter("password"));
		
		BankingOperationsKeywords operationsKeywords = new BankingOperationsKeywords();
		
		String user= operationsKeywords.LoginCustomer(userDatas);
		
		RequestDispatcher rd = request.getRequestDispatcher(user);
		
		rd.forward(request, response);
	}

}
