<%-- 
    Document   : ForgotPassword
    Created on : Mar 26, 2022, 12:25:31 PM
    Author     : mikae
--%>
<%@page import="java.util.Optional"%>
<%@page import="Model.User"%>
<%@page import="Model.UserDA"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password</title>
        <script src="https://smtpjs.com/v3/smtp.js"></script>  
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
        <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
        <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
        <script>
            var GlobalOTPCode = createOTP();
            var email;
                     
            function createOTP()
            {
                let OTP = "";
                for(let i = 0; i < 6; i++)
                {
                    OTP += Math.floor(Math.random() * 10);
                }
                return OTP;
            }
            
            function changeForm()
            {
                document.getElementById('enterEmail').style.display = 'none';
                document.getElementById('enterOTP').style.display = 'block';
            }

            function sendEmail() 
            {
                email = document.getElementById('emailAddress').value;
                Email.send({
                Host: "smtp.gmail.com",
                Username : "pineapplefoandco@gmail.com",
                Password : "marsonisbad12",
                To : email,
                From : "pineapplefoandco@gmail.com",
                Subject : "OTP code for password change",
                Body : "This is your OTP code : " + GlobalOTPCode
                }).then
                (
                    message => alert("Email sent successfully! Please check your email.")
                );
                changeForm();
                document.getElementById("emailPassedAgain").value = email;
            }
                       
            function checkOTP()
            {
                var OTPEntered = document.getElementById('OTPCode').value;
                
                if(GlobalOTPCode === OTPEntered)
                {
                    document.getElementById('enterOTP').style.display = 'none';
                    document.getElementById('changePassword').style.display = 'block';
                }
                else
                {
                    alert("You have entered the wrong OTP.");
                }
            }
        </script>
    </head>
    <body>
        <form class="mx-auto w-25 m-5 bg-light pt-4 pr-5 pl-5" id="enterEmail" style="display: block;">
            <h3 class="text-center pb-3">Please enter your email address to receive your OTP code</h3>
            <div class="form-group">
                <label for="emailAddress">Email Address: </label>
                <input type="text" class="form-control" id="emailAddress" name="emailAddress" required="required">
            </div>
            <div class="form-group text-center">
                <input type="button" class="btn btn-success mb-4" value="Send Email" onclick="sendEmail();"/>
            </div>
	</form>  
        
        <form class="mx-auto w-25 m-5 bg-light pt-4 pr-5 pl-5" id="enterOTP" style="display: none;">
            <h3 class="text-center pb-3">Please enter the OTP you receive from your email</h3>
            <div class="form-group">
                <label for="OTPCode">OTP Code: </label>
                <input type="text" class="form-control" id="OTPCode" name="OTPCode" required="required">
            </div>
            <div class="form-group text-center">
                <input type="button" class="btn btn-success mb-4" value="Check OTP" onclick="checkOTP()"/>
            </div>
	</form>  
        
        <form method="post" action="/JAVAASM/Controller/ForgotPasswordServlet" class="mx-auto w-25 m-5 bg-light pt-4 pr-5 pl-5" id="changePassword" style="display: none;">
            <h3 class="text-center pb-3">Please enter a new password</h3>
            <div class="form-group">
                <input type="hidden" id="emailPassedAgain" name="emailPassedAgain" value=""/>
            </div>
            <div class="form-group">
                <label for="password">Change Password: </label>
                <input type="text" class="form-control" id="password" name="password" required="required">
            </div>
            <div class="form-group text-center">
                <input type="submit" class="btn btn-success mb-4" name="formSubmit" id="formSubmit" value="Change Password"/>
            </div>
	</form> 
    </body>
</html>
