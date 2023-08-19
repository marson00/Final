<%-- 
    Document   : AccountDetailsForm
    Created on : Apr 4, 2022, 7:45:41 PM
    Author     : mikae
--%>

<%@page import="java.util.Optional"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.CustomerDetail"%>
<%@page import="Model.CustomerDA"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Customer Details Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>        
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous" />
        <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
        <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
    </head>
    <body>

        <%
            CustomerDA custDA = CustomerDA.getInstance();
            
            String isInitParam = request.getParameter("init");
            Boolean isInit = isInitParam != null ? Boolean.parseBoolean(isInitParam) : false;
                    
            Boolean isAdmin = !((request.getSession()).getAttribute("roleID")).equals("R04");
            String loginID = (String)request.getParameter("loginID");
            Optional<CustomerDetail> exist = custDA.find(loginID);
            CustomerDetail customerDetail = exist.isPresent() ? exist.get() : null;
            
            String action = customerDetail == null ?
                    "/JAVAASM/Controller/CustomerServlet/insert" + (isInit ? "?init=true " : " ") :
                    "/JAVAASM/Controller/CustomerServlet/update?staffOnly="+ request.getParameter("staffOnly") +"&loginID=" + loginID + (isInit ? "&init=true" : " ");
            
        %>

        <form method="POST" action="<%=action%>" class="mx-auto mt-5 bg-light p-4" style='width:30%;'>
            <div class="d-none">
                <div class="form-group d-flex" >
                    <label for="accountID"> Account ID :</label>
                    <input type="text" class="form-control" id="accountID" name="accountID" 
                           value="<%=customerDetail == null ? "" : customerDetail.getAccountID()%>"/>
                </div>
            </div>


            <div class="form-group" >
                <label for="username"> Username :</label>
                <input type="text" class="form-control" id="username" name="username" 
                       value="<%=customerDetail == null ? "" : customerDetail.getUsername()%>"/>
            </div>

            <div class="form-group" >
                <label for="gender"> Gender :</label>
                <input type="text" class="form-control" id="gender" name="gender"
                       value="<%=customerDetail.getGender() == 'n' ? "" : customerDetail.getGender()%>"/>
            </div>

            <div class="form-group" >
                <label for="age"> Age :</label>
                <input type="text" class="form-control" id="age" name="age"
                       value="<%=customerDetail.getAge() == Integer.parseInt("0") ? "" : customerDetail.getAge()%>"/>
            </div>

            <div class="form-group" >
                <label for="phone"> Phone :</label>
                <input type="text" class="form-control" id="phone" name="phone"
                       value="<%=customerDetail.getPhone().equals("n") ? "" : customerDetail.getPhone()%>"/>
            </div>

            <div class="form-group" >
                <label for="address"> Address :</label>
                <input type="text" class="form-control" id="address" name="address"
                       value="<%=customerDetail.getAddress().equals("n") ? "" : customerDetail.getAddress()%>"/>
            </div>

            <div class="form-group <%= (isAdmin == true) ? "" : "d-none"%>" >
                <label for="points"> Points :</label>
                <input type="text" class="form-control" id="points" name="points" readonly
                       value="<%=customerDetail == null ? "" : customerDetail.getPoints()%>"/>
            </div>
            
            
            <div class="form-group pb-3">
                <a  class="btn btn-info float-left"
                    href="<%=isInit ? "../Display/AdminMainPage.jsp" : isAdmin ? "../Display/DisplayAccountDetails.jsp?staffOnly=" + request.getParameter("staffOnly") :
                        "../../CustomerView/Display/HomePage.jsp"%> ">
                    Back
                </a>
                <input type="submit" class="btn btn-success float-right" name="submit"/>
            </div>
            
            
        </form>

    </body>

</html>
