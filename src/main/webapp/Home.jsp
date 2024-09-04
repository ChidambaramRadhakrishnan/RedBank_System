
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="UtilityOperations.Operations.Operations"%>
<%@page import="BaseOperations.Operations.BankingOperationsKeywords"%>
<%@page import="POJO.CustomerOperations.AuthenticateUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>RedBank Home</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <link href="style.css" rel='stylesheet'>
</head>
<body>
	<% BankingOperationsKeywords operationsKeywords = new BankingOperationsKeywords();%>
  <% String name = (String) session.getAttribute("username"); %>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
      <a class="navbar-brand fw-bold" href="Home.jsp">RedBank</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText"
        aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <% String lastLogin = operationsKeywords.getCurrentCustomerData(name, 14); %>
      
      <div class="pe-5 ms-auto">
        <span class="lastlogin fw-bold">LastLogout : <% out.println(lastLogin); %></span>
      </div>
      <div class="user">Welcome, 
        <span class="fw-bold"><% String welcomeName = operationsKeywords.getCurrentCustomerData(name,1)+" "+operationsKeywords.getCurrentCustomerData(name,2);
              out.println(welcomeName);
              %></span>
      </div>
      <div class="ps-5 ms-2">
        <a href="Logout.jsp" class="text-decoration-none logout px-3 py-2 bg-primary text-white rounded">Logout</a>
      </div>
    </div>
  </nav>

  <hr>
    <ul class="nav nav-pills mb-2 mt-3 ms-3" id="pills-tab" role="tablist">
      <li class="nav-item" role="presentation">
        <button class="nav-link active" id="pills-home-tab" data-bs-toggle="pill" data-bs-target="#pills-home" type="button" role="tab" aria-controls="pills-home" aria-selected="true">Account Details</button>
      </li>
      <li class="nav-item" role="presentation">
        <button class="nav-link" id="pills-profile-tab" data-bs-toggle="pill" data-bs-target="#pills-profile" type="button" role="tab" aria-controls="pills-profile" aria-selected="false">Fund Transfer</button>
      </li>
      <li class="nav-item" role="presentation">
        <button class="nav-link" id="pills-contact-tab" data-bs-toggle="pill" data-bs-target="#pills-contact" type="button" role="tab" aria-controls="pills-contact" aria-selected="false">Deposit</button>
      </li>
    </ul>
    <div class="tab-content" id="pills-tabContent">
      <div class="tab-pane fade show active ms-3" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">
        <h1>Customer Details</h1>
        <div class="container">
        
          <div class="row">
            <div class="col">
              <div class="my-4 fs-5 fw-bold">Customer Name : <% String fullname = operationsKeywords.getCurrentCustomerData(name,1)+" "+operationsKeywords.getCurrentCustomerData(name,2);
              out.println(fullname);
              %></div> 
              <div class="my-4 fs-5 fw-bold">Account Number : <%
              	out.println(name);
              	%>
              </div> 
              <div class="my-4 fs-5 fw-bold">Account Type : <% String Accounttype = operationsKeywords.getCurrentCustomerData(name,7);
              out.println(Accounttype);
              %> </div>
              <div class="my-4 fs-5 fw-bold">Address : <% String address = operationsKeywords.getCurrentCustomerData(name,5)+","+operationsKeywords.getCurrentCustomerData(name,6);
              	out.println(address);
              %> </div>
            </div>
            <div class="col">
              <div class="my-4 fs-5 fw-bold">Email : <% String email = operationsKeywords.getCurrentCustomerData(name,4);
              out.println(email);
              %></div>
              <div class="my-4 fs-5 fw-bold">Phone Number : <% String phonenumber = operationsKeywords.getCurrentCustomerData(name,3);
              	out.println(phonenumber);
              %> </div> 
              <div class="my-4 fs-5 fw-bold">MPIN : <% String mpin = operationsKeywords.getCurrentCustomerData(name,11);
              out.println(mpin);
              %> </div>
              <div class="my-4 fs-5 fw-bold"> MTPIN : 
              <% String mtpin = operationsKeywords.getCurrentCustomerData(name,12);
              out.println(mtpin);
              %>
               </div>
            </div>
            <div class="col my-4">
              <a href="UpdateCustomerDetails.jsp" class="text-decoration-none bg-primary text-white p-2 rounded fw-bold">Edit Account Details</a>
            </div>
          </div>
        </div>
      </div>
      <div class="tab-pane fade ms-3" id="pills-profile" role="tabpanel" aria-labelledby="pills-profile-tab">
      	<h1 class="ms-3">Fund Transfer</h1>
        <div class="container">
          <span class="fs-3">Available Balance :
          <% String Balance = operationsKeywords.getCurrentCustomerData(name,8);
              out.println(Operations.IndianCurrency(Balance)); %> </span>
        </div>
        <hr>
        <div class="container">
          <div class="row">
              <form action="FundTransfer" method="post">
                <div class="col-6">
                <%
				Connection connection = 
         		DriverManager.getConnection("jdbc:mysql://localhost:3306/RedBank","ChidambaramDemoMYDB","@1mysqlpass1");

       			Statement statement = connection.createStatement() ;

       			ResultSet resultset =statement.executeQuery("select AccountNumber,concat(FirstName,LastName) from userdetails") ;
				%>
				<select class="form-select p-3 mt-3" name="Beneficiare" required>
					<option Selected>Select Beneficiary</option>
				<%  while(resultset.next()){ %>
					<option value="<%= resultset.getString(1)%>"><%= resultset.getString(1)+"  "+resultset.getString(2)%></option>
			 	<% }%>
				</select></div>
                
                <input type="text" class="col-3 mt-3 p-2" name ="Amount" placeholder="Amount" required> <br>
                <input type="text" class="col-1 mt-3 p-2" name="mtpin" placeholder="MTPIN" required> <br>
                <input type="submit" class="col-2 mt-3 bg-primary border-0 p-2 rounded text-white" value="Make Transaction">
             </form>
          </div>
        </div>
      </div>
      <div class="tab-pane fade ms-3" id="pills-contact" role="tabpanel" aria-labelledby="pills-contact-tab">
      		<h1 class="ms-3">Deposit Amount</h1>
        	<div class="container">
          		<span class="fs-3">Available Balance :
          		<% String Balance2 = operationsKeywords.getCurrentCustomerData(name,8);
              	out.println(Operations.IndianCurrency(Balance2)); %> </span>
        	</div>
        	<hr>
        	<div class="container">
        		<div class="row">
        			<form action="DepositAmount" method="post">
        				<input class="col-3 p-2" type="text" disabled placeholder="<% out.println(name);%>"> <br>
        				<input class ="col-4 p-2 mt-3" type="text" placeholder="Amount" name="DepositAmount" required> <br>
        				<input class="col-3 p-2 mt-3" type="text" placeholder="MPIN" name="mpin" required>  <br>
        				<input class="col-3 p-2 bg-primary mt-3 border-0 rounded text-white" type="submit">
        			</form>
        		</div>
        	</div>
      </div>
    </div>



  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
    integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
    crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
    integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
    crossorigin="anonymous"></script>
</body>

</html>