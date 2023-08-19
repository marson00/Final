<%-- 
    Document   : Login
    Created on : Apr 5, 2022, 1:05:13 AM
    Author     : mikae
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>LOGIN PAGE</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <%@include file="js.jsp" %>
    </head>
    <body>
        <form class="mx-auto bg-light p-5 rounded-lg" style="width:30%; margin-top:10%;" method="POST" action="/JAVAASM/Controller/UserServlet/login">
            <h3 class="text-center mb-3">LOGIN PAGE</h3>
            <div class="form-group mb-4">
                <label class="form-label" for="form2Example1">Email address</label>
                <input name="email" id="email" class="form-control" required/>
            </div>

            <!-- Password input -->
            <div class="form-group mb-4">
                <label class="form-label" for="form2Example2">Password</label>
                <input type="password" name="password" id="password" class="form-control" required />
            </div>

            <!-- Submit button -->
            <div class="form-group pb-0 text-center">
                <button type="submit" class="btn btn-primary">Login</button>
            </div>
            <div class='pb-4'>
                <p class="float-left">
                    Don't have an account?<br>
                    <a href="/JAVAASM/CustomerView/Display/CustomerRegister.jsp">Register here</a>
                </p>
                <p class='float-right text-right'>
                    Forgot Password?<br>
                    <a href="/JAVAASM/AdminView/Form/ForgotPassword.jsp">Click here</a>
                </p>
            </div>
            
            
            
        </form>  
    </body>
</html>