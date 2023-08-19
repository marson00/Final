<%-- 
    Document   : DisplayUsers
    Created on : Apr 5, 2022, 5:29:25 PM
    Author     : mikae
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Model.User"%>
<%@page import="java.util.List"%>
<%@page import="Model.UserDA"%>
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
                    //Display All Product Records From DB
                    UserDA userDA = UserDA.getInstance();
                    Boolean staffOnly = Boolean.parseBoolean(request.getParameter("staffOnly"));
                    List<User> users = userDA.findAll();
                    String roleID = (String) (request.getSession()).getAttribute("roleID");
                    List<User> result = new ArrayList<User>();
                    for (User user : users) {
                        if (staffOnly ? !user.getRoleID().equals("R04") : user.getRoleID().equals("R04")) {
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
                                <th scope="col" width="20%;">Login ID</th>
                                <th scope="col" width="20%;">Email</th>
                                <th scope="col" width="20%;">Status</th>
                                <th scope="col" width="20%;">Last Login Time :</th>
                                <th scope="col" width="8%;">Role ID</th>
                                <th scope="col" width="9%;">Action</th> 
                            </tr>
                        </thead>
                        <tbody>   
                            <c:forEach items="${users}" var="u">   
                                <tr>
                                    <td scope="col"><%= count%></td>
                                    <td scope="col">${u.getLoginID()}</td>
                                    <td scope="col">${u.getEmail()}</td>
                                    <td scope="col">${u.getStatus()}</td>
                                    <td scope="col">${u.getLastLoginTime()}</td>
                                    <td scope="col">${u.getRoleID()}</td>
                                    <td scope="col" style="text-align: center;"> 
                                        <a href="../Form/UserForm.jsp?action=view&loginID=${u.getLoginID()}&staffOnly=<%=staffOnly%>">
                                            <button type="button" class="btn btn-success p-2 m-0"> View </button> 
                                        </a>  
                                    </td>
                                </tr>
                                <% count++;%>
                            </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr>                    
                                <td colspan="7">
                                    <div class="d-flex justify-content-between">
                                        <div class="d-flex" style="gap:0.5rem">
                                            <a  class="btn btn-info" href="AdminMainPage.jsp" >Back</a>
                                            <a href="DisplayUsers.jsp?staffOnly=<%=staffOnly ? false : true%>">
                                                <button type="button" class="btn btn-info p-2 m-0"><%=staffOnly ? "View Customers" : "View Staffs"%></button>
                                            </a>   
                                        </div>

                                        <div class="<%=(roleID.equals("R01") || roleID.equals("R02")) ? "d-block" : "d-none"%>">
                                            <a href="../Form/UserForm.jsp?action=insert&staffOnly=true">
                                                <button type="button" class="btn btn-info p-2 m-0"> Insert New Staff </button>
                                            </a>   
                                        </div>

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
