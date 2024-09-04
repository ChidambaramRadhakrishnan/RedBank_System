<%@page import="BaseOperations.Operations.BankingOperationsKeywords"%>
<%@page import="UtilityOperations.Operations.Operations"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RedBank - Logout</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link href='style.css' rel='stylesheet'>
</head>
<body>
	<nav class="navbar navbar-light bg-light">
  		<div class="container-fluid">
    		<a class="navbar-brand fw-bold" href="#">RedBank</a>
  		</div>
	</nav>
	
	<% 
	HttpSession sesion = request.getSession();
	String logouttime = Operations.getCurrentTime();
	BankingOperationsKeywords operationsKeywords = new BankingOperationsKeywords();
	operationsKeywords.UpdateLogInTime((String) session.getAttribute("username"), logouttime);
	%>
	
	<% 
	sesion.invalidate();
	%>
	
	<div class="container justify-content-center">
			<h1 class="text-center mt-5"><span class="fw-bold text-secondary display-4">Logout Successfully</span></h1>
	</div>
	

	<div class="container jusity-content-center">
		<h5 class="text-center ">To do more banking <a href="index.jsp" class="text-center text-decoration-none bg-primary text-white p-2">Click Here</a></h5>
	</div>
	
	
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</body>
</html>