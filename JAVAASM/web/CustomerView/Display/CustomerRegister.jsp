<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>LOGIN PAGE</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <%@include file="../JSCSS/js.jsp" %>
    </head>
    <body>
        <form class="mt-5 bg-light mx-auto p-5" style="width: 40%;" method="POST" action="/JAVAASM/Controller/UserServlet/register">
            <h3 class="text-center mb-4">REGISTER</h3>
            <div class="form-group mb-4">
                <label class="form-label" for="form2Example1">Email address</label>
                <input name="email" id="email" class="form-control" required/>
            </div>
            
            <div class="form-group mb-4">
                <label class="form-label" for="form2Example2">Username</label>
                <input name="username" id="username" class="form-control" required />
            </div>

            <!-- Password input -->
            <div class="form-group mb-4">
                <label class="form-label" for="form2Example2">Password</label>
                <input type="password" name="password" id="password" class="form-control" required />
            </div>

            <!-- Submit button -->
            <div class="form-group mb-4">
                <button type="submit" class="btn btn-success float-right mb-4">Register</button>
                <button onclick="history.back()" class="btn btn-info">Back</button>
            </div>
        </form>  
    </body>
</html>
