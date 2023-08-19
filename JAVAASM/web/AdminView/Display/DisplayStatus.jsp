<%-- 
    Document   : adminMainPage
    Created on : Mar 24, 2022, 11:12:15 AM
    Author     : mikae
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="Model.Status"%>
<%@page import="Model.StatusDA"%>
<!DOCTYPE html>
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
                    <h2>Status Table</h2>
                </div>
            </nav>
            
            <form method="POST" action="" class="mx-auto w-50 m-3">
                <%
                    //Display All Status Records From DB
                    StatusDA stDA = new StatusDA();
                    request.setAttribute("status", stDA.GetAllStatus());
                    int count = 1;
                %> 

                <!--Start Table-->
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr style="text-align: center;">
                          <th scope="col">#</th>
                          <th scope="col">Status</th>
                          <th scope="col">Status Title</th>
                          <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${status}" var="st">  
                        <tr>
                          <td scope="col"><% out.print(count); %></td>
                          <td scope="col"><c:out value="${st.status}"/></td>
                          <td scope="col"><c:out value="${st.statusTitle}"/></td>
                          <td scope="col" style="text-align: center;">     
                            <a href="../Form/StatusForm.jsp?submit=Update&id=<c:out value="${st.status}"/>&name=<c:out value="${st.statusTitle}"/>">
                                <button type="button" class="btn btn-info p-2 m-0"> Update </button>
                            </a>           
                          </td>
                          <%count++;%>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="4" style="text-align: center;">
                                <a href="../Form/StatusForm.jsp?submit=Insert">
                                    <button type="button" class="btn btn-info"> Insert New Status </button>
                                </a>    
                            </td>
                        </tr>
                    </tfoot>
                </table>
                <!--End Table-->

            </form>
            
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
