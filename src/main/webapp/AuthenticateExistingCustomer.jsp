<%@page import="POJO.CustomerOperations.AuthenticateUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RedBank Login</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link href="style.css" rel='stylesheet'>
</head>
<body>
	<div class="container text-center">
		<div class="logo col-12 py-3">RedBank</div>
	</div>
	<div class="container d-flex justify-content-center my-4">
        <div class="row">
            <form class="col-12 form-section" action="AuthenticateExistingCustomer" method="post" >
            	<div class="display-6 text-center">Login Customer</div>
                <div class="row row-cols-1 d-flex justify-content-center mt-5">
                    <div class="col-10 d-flex justify-content-center">
                        <input class ="form-control inputControl" type="text" name="accountNumber" id="" placeholder="Account No" required>
                    </div>
                    <div class="col-10 d-flex justify-content-center my-3">
                        <input class ="form-control inputControl" type="password" name="password" id="" placeholder="Password" required>
                    </div>
                    <div class="col-4 d-flex justify-content-center">
                        <input class ="form-control p-2 login-btn" type="submit" value="Login">
                    </div>
                </div>
                <div class="text-center mt-3">
                	<span class="text-center">If you are a new customer <a href="AccountCreation.jsp">Register here</a></span>
                </div>
            </form>
        </div>
   </div>
   
	
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</body>
</html>