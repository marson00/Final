<%-- 
    Document   : DisplayCapacity
    Created on : Mar 25, 2022, 8:56:40 AM
    Author     : Marson
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.Capacity"%>
<%@page import="Model.CapacityDA"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <%@include file="../JSCSS/js.jsp" %>
    </head>
    <body style="height:1080px;">
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
                
                
                    <form method="POST" action="" class="mx-auto w-50 m-3">
                        <%
                            //Display All Category Records From DB
                            CapacityDA capDA = new CapacityDA();
                            request.setAttribute("capacity", capDA.GetAllCapacity());
                            int count = 1;
                        %>   

                        <!--Start Table-->
                        <table class="table table-bordered table-hover" style="text-align: center;">
                            <thead>
                                <tr>
                                  <th scope="col">#</th>
                                  <th scope="col">Capacity ID</th>
                                  <th scope="col">Capacity (GB)</th>
                                  <th scope="col">Add Ons (RM)</th>
                                  <th scope="col">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${capacity}" var="cap">   
                                <tr>
                                  <td scope="col"><% out.print(count); %></td>
                                  <td scope="col">${cap.capID}</td>
                                  <td scope="col">${cap.capacity}</td>
                                  <td scope="col">${cap.addOns}.00</td>
                                  <td scope="col">     
                                    <a href="../Form/CapacityForm.jsp?submit=Update&id=${cap.capID}&cap=${cap.capacity}&add=${cap.addOns}">
                                        <button type="button" class="btn btn-info p-2 m-0"> Update </button>
                                    </a>           
                                  </td>
                                  <%count++;%>
                                </tr>
                            </c:forEach>
                            </tbody>
                            <tfoot>
                                <tr>
                                  <td colspan="5" style="text-align: right;">
                                    <a href="../Form/CapacityForm.jsp?submit=Insert">
                                        <button type="button" class="btn btn-info p-2 m-0"> Insert New Capacity </button>
                                    </a>    
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
