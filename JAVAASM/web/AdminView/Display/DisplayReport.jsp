<%-- 
    Document   : ReportPage
    Created on : Mar 24, 2022, 11:40:38 AM
    Author     : mikae
--%>

<%@page import="Model.DA"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.report"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
                    <h2>Report</h2>
                </div>
            </nav>
            
            <a href="ReportForm.jsp" class="btn btn-success" style="margin-left:2.5%;margin-bottom:1%;">Create new report</a>
            
            <%
                DA getRpt = new DA();
                request.setAttribute("reports", getRpt.getAllReport());
                int count = 1;
            %>

            <table class="table table-bordered mx-auto text-center" style="width:95%;">
                <colgroup>
                    <col span="1" style="width: 3%;">
                    <col span="1" style="width: 7%;">
                    <col span="1" style="width: 65%;">
                    <col span="1" style="width: 10%;">
                    <col span="1" style="width: 10%;">
                    <col span="1" style="width: 5%;">
                </colgroup>
                <tr>
                    <td class="text-center" style="background-color:#C2C2C2;">No.</td>
                    <td class="text-center" style="background-color:#C2C2C2;">Report ID</td>
                    <td class="text-center" style="background-color:#C2C2C2;">Report Title</td>
                    <td class="text-center" style="background-color:#C2C2C2;">Created Date</td>
                    <td class="text-center" style="background-color:#C2C2C2;">Created By</td>
                    <td class="text-center" style="background-color:#C2C2C2;">View</td>
                </tr>
                <c:forEach items="${reports}" var="report">
                    <tr>
                        <td><% out.print(count); %></td>
                        <td><c:out value="${report.reportID}" /></td>
                        <td><c:out value="${report.reportTitle}" /></td>
                        <td><c:out value="${report.reportCreatedDate}"/></td>
                        <td>Coming Soon</td>
                        <td><button class="btn btn-primary mx-auto">View</button></td>
                        <%count++;%>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <script type="text/javascript">
            $(document).ready(function() 
            {
                $('#sidebarCollapse').on('click', function () 
                {
                    $('#sidebar').toggleClass('active');
                });
            });
        </script>
    </body>
</html>
