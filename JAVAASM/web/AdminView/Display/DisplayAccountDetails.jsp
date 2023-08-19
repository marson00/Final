<%-- 
    Document   : DisplayAccountDetails
    Created on : Apr 4, 2022, 7:46:22 PM
    Author     : mikae
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Model.CustomerDetail"%>
<%@page import="java.util.List"%>
<%@page import="Model.CustomerDA"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <%@include file="../JSCSS/js.jsp" %>
    </head>
    <body style="height:1080px; overflow-y:hidden;">
        <div class="wrapper">
            <!-- Navigation Bar -->
            <%@include file="../../CustomerView/JSCSS/NavigationBar.jsp" %>

            <!-- Page Content  -->
            <div id="content">
                <nav class="navbar navbar-expand-lg navbar-light bg-light">
                    <div class="container-fluid">
                        <button type="button" id="sidebarCollapse" class="btn btn-info">
                            <i class="fas fa-bars"></i>
                        </button>
                        <h2>Overview</h2>
                    </div>
                </nav>
                
                <%
                    CustomerDA customerDA = new CustomerDA();
                    Boolean staffOnly = Boolean.parseBoolean(request.getParameter("staffOnly"));
                    List<CustomerDetail> users = customerDA.findAll();
                    List<CustomerDetail> result = new ArrayList<CustomerDetail>();
                    for (CustomerDetail user : users) {
                        if (staffOnly ? user.getAccountID().startsWith("S") : user.getAccountID().startsWith("A")) {
                            result.add(user);
                        }
                    }
                    users = result;
                    request.setAttribute("users", users);
                    int count = 1;
                %>
                <form method="POST" action="" class="mx-auto m-3" style="width: 90%;">
                    <!--Start Table-->
                    <table class="table table-bordered table-hover" style="text-align:center;">
                        <thead>
                            <tr style="text-align: center;">
                                <th scope="col" width="3%;">#</th>
                                <th scope="col" width="4%;">Account ID</th>
                                <th scope="col" width="20%;">Username</th>
                                <th scope="col" width="3%;">Gender</th>
                                <th scope="col" width="3%;">Age</th>
                                <th scope="col" width="10%;">Phone</th>
                                <th scope="col" width="30%;">Address</th>
                                <th scope="col" width="4%;">Points</th>
                                <th scope="col" width="5%;">Login ID</th>
                                <th scope="col" width="8%;">Action</th> 
                            </tr>
                        </thead>
                        <tbody>   
                            <c:forEach items="${users}" var="u">   
                                <tr>
                                    <td scope="col"><%= count%></td>
                                    <td scope="col">${u.getAccountID()}</td>
                                    <td scope="col">${u.getUsername()}</td>
                                    <c:choose>
                                        <c:when test="${u.getGender() == 'n'}">
                                            <td scope="col"></td>
                                        </c:when>    
                                        <c:otherwise>
                                            <td scope="col">${u.getGender()}</td>
                                        </c:otherwise>
                                    </c:choose>      
                                    <c:choose>
                                        <c:when test="${u.getAge() == 0}">
                                            <td scope="col"></td>
                                        </c:when>    
                                        <c:otherwise>
                                            <td scope="col">${u.getAge()}</td>
                                        </c:otherwise>
                                    </c:choose>   
                                    <c:choose>
                                        <c:when test="${u.getPhone() == 'n'}">
                                            <td scope="col"></td>
                                        </c:when>    
                                        <c:otherwise>
                                            <td scope="col">${u.getPhone()}</td>
                                        </c:otherwise>
                                    </c:choose>   
                                    <c:choose>
                                        <c:when test="${u.getAddress() == 'n'}">
                                            <td scope="col"></td>
                                        </c:when>    
                                        <c:otherwise>
                                            <td scope="col">${u.getAddress()}</td>
                                        </c:otherwise>
                                    </c:choose>   
                                    <td scope="col">${u.getPoints()}</td>
                                    <td scope="col">${u.getLoginID()}</td>
                                    <td scope="col" style="text-align: center;"> 
                                        <a href="../Form/AccountDetailsForm.jsp?loginID=${u.getLoginID()}&staffOnly=<%=staffOnly ? true : false%>">
                                            <button type="button" class="btn btn-success p-2 m-0"> View </button> 
                                        </a>  
                                    </td>
                                </tr>
                                <% count++;%>
                            </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr>                    
                                <td colspan="10">
                                    <div class="d-flex" style="gap:0.5rem">
                                        <a  class="btn btn-info" href="AdminMainPage.jsp?staffOnly=<%=staffOnly%>" >Back</a>
                                        <a href="DisplayAccountDetails.jsp?staffOnly=<%=staffOnly ? false : true%>">
                                            <button type="button" class="btn btn-info p-2 m-0"><%=staffOnly ? "View Customers" : "View Staffs"%></button>
                                        </a>    
                                    </div>
                                </td>
                            </tr>
                        </tfoot>
                    </table>            
                    <!--End Table-->
                </form>
                
            </div>
        </div>

        <script type="text/javascript">
            $(document).ready(function () 
            {
                $('#sidebarCollapse').on('click', function () 
                {
                    $('#sidebar').toggleClass('active');
                });
            });
        </script>
    </body>
</html>
