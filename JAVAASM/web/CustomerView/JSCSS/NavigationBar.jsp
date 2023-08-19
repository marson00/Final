<%-- 
    Document   : NavigationBar
    Created on : Apr 6, 2022, 5:08:36 PM
    Author     : TingLe
--%>

<!-- Sidebar  -->
<%@page import="Model.UserDA"%>
<%@page import="Model.User"%>
<%@page import="Model.CustomerDetail"%>
<%@page import="java.util.List"%>
<%@page import="Model.CustomerDA"%>
<% 
    String loginID = (String) (request.getSession()).getAttribute("loginID");
    CustomerDA getName = CustomerDA.getInstance();
    String username = getName.findUsername(loginID);
    request.setAttribute("username", username);
%>
<nav id="sidebar">
    <div class="sidebar-header">
        <h3 class="text-center">PineApple</h3>
        <strong>PA</strong>     
    </div>

    <ul class="list-unstyled mt-2">
        <li class="bg-dark" style="color:white;">
            <a href="../Form/AccountDetailsForm.jsp?loginID=<%=(request.getSession()).getAttribute("loginID")%>&init=true">
                <i class="fa-solid fa-address-card text-left w-25 ms-2"></i>
                ${username}
            </a>
        </li>
    </ul>    

    <ul class="list-unstyled components"> 
        <li>
            <a href="AdminMainPage.jsp">
                <i class="fa-regular fa-clipboard text-left w-25 ms-2"></i>
                Overview
            </a>
        </li>
        <li>
            <div class="dropdown dropright">
                <a class="dropdown-toggle" data-toggle="dropdown">
                    <i class="fa-solid fa-users-line text-left w-25 ms-2"></i>
                    Accounts
                </a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="DisplayUsers.jsp?staffOnly=true">Account</a>
                    <a class="dropdown-item" href="DisplayAccountDetails.jsp?staffOnly=true">Account Details</a>
                    <a class="dropdown-item" href="DisplayStatus.jsp">Account Status</a>
                    <a class="dropdown-item" href="DisplayRole.jsp">Account Role</a>
                </div>
            </div>
        </li>
        <li>
            <div class="dropdown dropright">
                <a class="dropdown-toggle" data-toggle="dropdown">
                    <i class="fa-solid fa-p text-left w-25 ms-2"></i>
                    Product
                </a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="DisplayProduct.jsp?view=All">Product</a>
                    <a class="dropdown-item" href="DisplayCategory.jsp">Category</a>   
                    <a class="dropdown-item" href="DisplayCapacity.jsp">Capacity</a>
                </div>
            </div>
        </li>
        <li>
            <a href="">
                <i class="fa-solid fa-basket-shopping text-left w-25 ms-2" ></i>
                Order History
            </a>
        </li>
        <li>
            <a href="">
                <i class="fa-regular fa-credit-card text-left w-25 ms-2"></i>
                Order
            </a>
        </li>
        <li>
            <a href="DisplayReport.jsp">
                <i class="fa-regular fa-file text-left w-25 ms-2"></i>
                Report
            </a>
        </li>
        <li style="margin-top:25px;">
            <a href="">
                <i class="fa-solid fa-right-from-bracket text-left w-25 ms-2"></i>
                Log out
            </a>
        </li>
    </ul>
</nav>
