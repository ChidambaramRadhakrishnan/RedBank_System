<%@page import="UtilityOperations.Operations.Operations"%>
<%@page import="POJO.CustomerOperations.CustomerDetails"%>
<%@page import="org.apache.catalina.ant.ThreaddumpTask"%>
<%@page import="BaseOperations.Operations.BankingOperationsKeywords"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RedBank Account Creation</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link href='style.css' rel='stylesheet'>
</head>
<body>
	<div class="container text-center">
		<div class="logo col-12 py-3">RedBank</div>
	</div>
	 <div class="container d-flex justify-content-center">
        <div class="row">
            <form class="col-12" action="RegisterCustomer" method="post">
                <div class="display-6 text-center">Create Customer Account</div>
                <div class="row row-cols-1 d-flex justify-content-center mt-3">
                    <div class="col-8 d-flex justify-content-center my-2">
                        <input class ="form-control me-3" type="text" name="firstname" id="" placeholder="First Name" required>
                        <input class ="form-control" type="text" name="lastname" id="" placeholder="Last Name" required>
                    </div>
                    <div class="col-8 d-flex justify-content-center my-2">
                        <input class ="form-control" type="text" name="phonenumber" id="" placeholder="Phone Number" required>
                    </div>
                    <div class="col-8 d-flex justify-content-center my-2">
                        <input class ="form-control" type="email" name="email" placeholder="Email" required>
                        
                    </div>
                    <div class="col-8 d-flex justify-content-center my-2">
                        <input class ="form-control me-3" type="text" name="city" id="" placeholder="City" required>
                        <input class ="form-control" type="text" name="state" id="" placeholder="State" required>
                    </div>
                    <div class="col-8 d-flex justify-content-center my-2">
                       <select class="form-select me-4" name="accountType" id="">
                        <option value="Value not selected" selected>Select Account Type</option>
                        <option value="Savings Account">Savings Account</option>
                        <option value="Current Account">Current Account</option>
                       </select>
                        <input class ="form-control" type="text" name="minimumdeposit" placeholder="Minimum Deposit Amount" required>
                    </div>
                    <div class="col-8 d-flex justify-content-center my-2">
                        <input type="password" class="form-control me-3" name="loginPassword" placeholder="Login Password" required>
                        <input type="password" class="form-control me-3" name="confirmPassword" placeholder="Confirm Login Password" required>
                     </div>
                     <div class="col-8 d-flex justify-content-center my-2">
                        <input class ="form-control me-3" type="text" name="mpin" id="" placeholder="MPIN" required>
                        <input class ="form-control" type="text" name="mtpin" id="" placeholder="MTPIN" required>
                     </div>
                    <div class="col-5 d-flex justify-content-center">
                        <input class ="form-control login-btn" type="submit" value="Sign Up">
                    </div>
                </div>
                <div class="text-center mt-3">
                	<span class="text-center">If you are a existing customer <a href="AuthenticateExistingCustomer.jsp">Login Here</a></span>
                </div>
            </form>
            
            
        </div>
   </div>



<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</body>
</html>