<%-- 
    Document   : AdminDisplayCategory
    Created on : Mar 29, 2022, 11:04:02 PM
    Author     : mikae
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.Category"%>
<%@page import="Model.CategoryDA"%>

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
                    <h2>Category Table</h2>
                </div>
            </nav>
            
            <form method="POST" action="" class="mx-auto w-50 m-3">
            <%
                //Display All Category Records From DB
                CategoryDA catDA = new CategoryDA();
                request.setAttribute("category", catDA.GetAllCategory());
                int count = 1;
            %> 

            <!--Start Table-->
            <table class="table table-bordered table-hover">
                <thead>
                    <tr style="text-align: center;">
                      <th scope="col">#</th>
                      <th scope="col">Category ID</th>
                      <th scope="col">Category Name</th>
                      <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${category}" var="cat">  
                    <tr>
                      <td scope="col"><% out.print(count); %></td>
                      <td scope="col">${cat.catID}</td>
                      <td scope="col">${cat.catName}</td>
                      <td scope="col" style="text-align: center;">     
                        <a href="../Form/CategoryForm.jsp?submit=Update&id=${cat.catID}&name=${cat.catName}">
                            <button type="button" class="btn btn-info p-2 m-0"> Update </button>
                        </a>           
                      </td>
                      <%count++;%>
                    </tr>
                </c:forEach>
                </tbody>
                <tfoot>
                    <tr>
                      <td colspan="4" style="text-align: right;">
                        <a href="../Form/CategoryForm.jsp?submit=Insert">
                            <button type="button" class="btn btn-info p-2 m-0"> Insert New Category </button>
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
