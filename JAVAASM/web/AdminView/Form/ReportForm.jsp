<%-- 
    Document   : createReport.jsp
    Created on : Mar 23, 2022, 3:55:35 PM
    Author     : mikae
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
        <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
        <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div class="container">
            <center>
                <input type="button" class="btn btn-primary" value="Exception Report" onclick = "Openform1();">
                <input type="button" class="btn btn-primary" value="Sales Report" onclick = "Openform2();">
            </center>
        </div>
        
        <form method="post" action="${pageContext.request.contextPath}/InsertReportServlet" id="form1" class="mx-auto w-50 m-3 bg-light pt-5 pr-5 pl-5" style="display:block;">
            <div class="form-group">
                <label for="rptTitle">Report Title: </label>
                <input type="text" class="form-control" id="rptTitle" name="rptTitle" required="required">
            </div>
            <div class="form-group">
                <label for="listSize">Select List Size:</label>
                <select class="form-control" id="listSize" name="listSize">
                    <option>3</option>
                    <option>5</option>
                    <option>10</option>
                    <option>15</option>
                </select>
            </div>
            <div class="form-group">
                <label for="listSize">Report Duration:</label>
                <select class="form-control" id="listSize" name="listSize">
                    <option>Daily</option>
                    <option>Weekly</option>
                    <option>Monthly</option>
                    <option>Yearly</option>
                </select>
            </div>
            <div class="clearfix pb-4 pt-3">
                <a href="ReportPage.jsp" class="btn btn-info float-left">Cancel</a>
                <input type="submit" class="btn btn-info float-right" value="Submit" name="createReport"/>
            </div>
        </form> 
        
        <form method="post" action="${pageContext.request.contextPath}/InsertReportServlet" id="form2" class="mx-auto w-50 m-3 bg-light p-5" style="display:none;">
            <div class="form-group">
                <label for="rptTitle">Report Title: </label>
                <input type="text" class="form-control" id="rptTitle" name="rptTitle">
            </div>
            <div class="form-group">
                <label for="listSize">Report Duration:</label>
                <select class="form-control" id="listSize" name="listSize">
                    <option>Daily</option>
                    <option>Weekly</option>
                    <option>Monthly</option>
                    <option>Yearly</option>
                </select>
            </div>
            <div class="form-group">
                <label for="category">Category Name: </label>
                <input type="text" name="category" class="form-control" id="category">
            </div>
            <a href="ReportPage.jsp" class="btn btn-info">Cancel</a>
            <input type="submit" class="btn btn-info" value="Submit" name="createReport">
        </form> 
        
        <script>
            $('#startTime').datepicker({
                uiLibrary: 'bootstrap4'
            });
            $('#endTime').datepicker({
                uiLibrary: 'bootstrap4'
            });
            
            function Openform1()
            {
                document.getElementById('form1').style.display = 'block';
                document.getElementById('form2').style.display = 'none';
            }
            
            function Openform2()
            {
                document.getElementById('form1').style.display = 'none';
                document.getElementById('form2').style.display = 'block';
            }
            
            $(".custom-file-input").on("change", function() 
            {
                var fileName = $(this).val().split("\\").pop();
                $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
            });
        </script>
    </body>
</html>
