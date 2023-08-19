<%-- 
    Document   : UserForm
    Created on : Apr 4, 2022, 7:59:11 PM
    Author     : mikae
--%>

<%@page import="java.util.Optional"%>
<%@page import="Model.User"%>
<%@page import="Model.UserDA"%>
<%@page import="Model.CustomerDetail"%>
<%@page import="Model.CustomerDA"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>


<!DOCTYPE html>
<html>
    <head>
        <title>User Details Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>        
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous" />
        <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
        <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
    </head>
    <body>

        <%
            UserDA userDA = UserDA.getInstance();
            CustomerDA detailDA = CustomerDA.getInstance();
            String action = request.getParameter("action");
            String loginID = " ";
            User user = null;
            CustomerDetail username = null;
            Boolean staffOnly = Boolean.parseBoolean(request.getParameter("staffOnly"));

            if (action.equals("view")) 
            {
                loginID = request.getParameter("loginID");
                user = (userDA.find(loginID)).get();
                username = (detailDA.find(loginID)).get();
            }
        %>

        <form method="POST" action="<%=user == null ? "/JAVAASM/Controller/UserServlet/insert?staffOnly=" + staffOnly :
                "/JAVAASM/Controller/UserServlet/update?staffOnly=" + staffOnly %>" style='width:30%;' class="mx-auto p-5 mt-4 bg-light">
            <div class="<%if (user == null) {%> d-none <%}%>">
                <div class="form-group" >
                    <label for="loginID"> Login ID :</label>
                    <input type="text" class="form-control" id="loginID" name="loginID"  readonly
                           value="<%=user == null ? "" : user.getLoginID()%>"/>
                </div>
            </div>

            <div class="form-group" >
                <label for="email"> Email :</label>
                <input type="text" class="form-control" id="email" name="email"
                       <%if (user != null) {%> readonly <%}%>
                       value="<%=user == null ? "" : user.getEmail()%>"/>
            </div>
            
            <div class="form-group" >
                <label for="email"> Username :</label>
                <input type="text" class="form-control" id="username" name="username"
                       <%if (username != null) {%> readonly <%}%>
                       value="<%=username == null ? "" : username.getUsername()%>"/>
            </div>

            <div class="<%if (user != null) {%> d-none <%}%>">
                <div class="form-group" >
                    <label for="email"> Password :</label>
                    <input type="text" class="form-control" id="password" name="password"/>
                </div>
            </div>


            <div class="form-group" >
                <label for="status"> Status :</label>
                <select class="form-control" id="status" name="status">
                    <option <%if (user != null && (user.getStatus() == 0)) {%> selected <%}%> value="0" > 0 </option>
                    <option <%if (user != null && (user.getStatus() == 1)) {%> selected <%}%> value="1" > 1 </option>
                </select>
            </div>

            <div class="<%if (user == null) {%> d-none <%}%>">
                <div class="form-group" >
                    <label for="lastLoginTime"> Last Login Time :</label>
                    <input type="text" class="form-control" id="lastLoginTime" name="lastLoginTime" readonly
                           value="<%=user == null ? "" : user.getLastLoginTime()%>"/>
                </div>
            </div>

            <div class="form-group" >
                <label for="roleID"> Role ID :</label>
                <select class="form-control" id="roleID" name="roleID">
                    <option <%if (user != null && (user.getRoleID().equals("R01"))) {%> selected <%}%> value="R01" > R01 </option>
                    <option <%if (user != null && (user.getRoleID().equals("R02"))) {%> selected <%}%> value="R02" > R02 </option>
                    <option <%if (user != null && (user.getRoleID().equals("R03"))) {%> selected <%}%> value="R03" > R03 </option>
                </select>
            </div>

            <div class="d-flex justify-content-between">
                <a  class="btn btn-info float-left"  href="/JAVAASM/AdminView/Display/DisplayUsers.jsp?staffOnly=<%=staffOnly%>" >Back</a>
                <button type="submit" class="btn btn-success float-right" value="<%=action.equals("view") ? "update" : "insert"%>">
                    <%=action.equals("view") ? "Update" : "Insert"%></button>
            </div>
        </form>

    </body>

</html>
