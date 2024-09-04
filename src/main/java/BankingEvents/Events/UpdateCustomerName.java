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
 * Servlet implementation class UpdateCustomerName
 */
public class UpdateCustomerName extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public String Firstname;
	public String Lastname;
	
	public String AccountNo;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCustomerName() {
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
		
		session.setAttribute("firstName", request.getParameter("firstname"));
		session.setAttribute("lastName", request.getParameter("lastname"));
		
		Firstname = (String) session.getAttribute("firstName");
		Lastname = (String) session.getAttribute("lastName");
		AccountNo = (String) session.getAttribute("username");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		BankingOperationsKeywords operationsKeywords = new BankingOperationsKeywords();
		
		operationsKeywords.UpdateMultipleField(AccountNo, "FirstName",Firstname, "LastName" ,Lastname);
		
		RequestDispatcher rd = request.getRequestDispatcher("Updated.jsp");
		
		rd.forward(request, response);
	}

}
