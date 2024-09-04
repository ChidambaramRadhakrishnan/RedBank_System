<%@page import="UtilityOperations.Operations.Operations"%>
<%@page import="BaseOperations.Operations.BankingOperationsKeywords"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RedBank - Transaction Successful</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link href='style.css' rel='stylesheet'>
</head>
<body>
	<% BankingOperationsKeywords operationsKeywords = new BankingOperationsKeywords();%>
	<% String name = (String) session.getAttribute("username"); %>
	<% String mail = operationsKeywords.getCurrentCustomerData(name, 4);%>
	<%String fullname = operationsKeywords.getCurrentCustomerData(name,1)+" "+operationsKeywords.getCurrentCustomerData(name,2); %>
	
	<% Operations.SendDepositMail(mail, name+" "+fullname, Operations.IndianCurrency((String) session.getAttribute("depositAmount"))); %>
	<nav class="navbar navbar-light bg-light">
  		<div class="container-fluid">
    		<a class="navbar-brand fw-bold" href="Home.jsp">RedBank</a>
  		</div>
	</nav>
	
	<div class="container justify-content-center">
			<h1 class="text-center mt-5"><span class="fw-bold text-secondary display-4">Deposited Successfully</span></h1>
	</div>
	
	<div class="container justify-content-center mt-4">
			<h3 class="text-center "><span>Self </span><span class="fw-bold"><% out.println(name +","+operationsKeywords.getCurrentCustomerData(name,1)+" "+
	operationsKeywords.getCurrentCustomerData(name,2)); %></span> </h3>
			<h3 class="text-center"><span>Amount </span><span class="fw-bold"><% out.println( Operations.IndianCurrency((String) session.getAttribute("depositAmount"))); %></span> </h3>
	</div>
	<div class="container jusity-content-center">
		<h5 class="text-center ">To do more banking <a href="Home.jsp" class="text-center text-decoration-none bg-primary text-white p-2">Click Here</a></h5>
	</div>
	
	
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</body>
</html>