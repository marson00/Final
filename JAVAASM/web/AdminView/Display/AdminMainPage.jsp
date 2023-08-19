<%-- 
    Document   : adminMainPage
    Created on : Mar 24, 2022, 11:12:15 AM
    Author     : mikae
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
