<%@page import="BaseOperations.Operations.BankingOperationsKeywords"%>
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
      
      <div class="pe-5 ms-auto">
        <span class="lastlogin fw-bold">LastLogout : <% out.println(operationsKeywords.getCurrentCustomerData(name, 14));%></span>
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

	<div class="container">
		<h1>Update Your Details</h1>
	</div>
	
	<div class="container justify-content-center mt-4">
    <div class="row">
      <div class="col-4">
        <form action="UpdateCustomerName" method="post" class="">
          <h3>Customer Name</h3>
          <input type="text" class="col-12 mt-2" name="firstname" placeholder="First Name" required> <br>
          <input type="text" class="col-12 mt-2" name="lastname" placeholder="Last Name" required> <br>
          <input type="submit" class="col-6 mt-3 bg-primary border-0 p-1 rounded text-white" value="Update Name">
       </form>
      </div>
      <div class="col-4">
        <form action="UpdateContact" method="post">
          <h3>Contact Details</h3>
          <input type="text" class="col-12 mt-2" name="PhoneNumber" placeholder="Phone Number" required> <br>
          <input type="text" class="col-12 mt-2" name="Email" placeholder="Email" required> <br>
          <input type="submit" class="col-6 mt-3 bg-primary border-0 p-1 rounded text-white" value="Update Contact">
        </form>
      </div>
      <div class="col-4 align-items-center">
        <form action="UpdateAdressline" method="post">
          <h3>Address Details</h3>
          <input type="text" class="col-12 mt-2" name="City" placeholder="City" required> <br>
          <input type="text" class="col-12 mt-2" name="State" placeholder="State" required> <br>
          <input type="submit" class="col-6 mt-3 bg-primary border-0 p-1 rounded text-white align-items-center" value="Update Address">
       </form>
      </div>
    </div> 
  </div>

  <div class="container mt-5">
    <div class="row">
      <div class="col-4">
        <form action="UpdatePassword" method="post">
          <h3 >Password</h3>
          <input type="text" class="col-12 mt-2" name="password" placeholder="Password" required> <br>
          <input type="submit" class="col-6 mt-2 bg-primary border-0 p-1 rounded text-white" value="Update Password">
       </form>
      </div>
      <div class="col-4">
        <form action="UpdatedMpin" method="post">
          <h3>MPIN</h3>
          <input type="text" class="col-12 mt-2" name="mpin" placeholder="MPIN" required> <br>
          <input type="submit" class="col-6 mt-2 bg-primary border-0 p-1 rounded text-white" value="Update MPIN">
       </form>
       </div>
       <div class="col-4">
        <form action="UpdateMTPIN" method="post">
          <h3>MTPIN</h3>
          <input type="text" class="col-12 mt-2" name="mtpin" placeholder="MTPIN" required> <br>
          <input type="submit" class="col-6 mt-2 bg-primary border-0 p-1 rounded text-white" value="Update MTPIN">
       </form>
      </div>
    </div>
    
    <br>
      <span class="fst-italic">Note: Don't leave fields empty.</span> <br>
      <span class="fst-italic">Note: You can't updated all field at a time. can do it individual.</span>
    
  </div>



  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
    integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
    crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
    integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
    crossorigin="anonymous"></script>
</body>

</html>