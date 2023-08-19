<%-- 
    Document   : DisplayProduct
    Created on : Mar 29, 2022, 11:04:02 PM
    Author     : TingLe
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.Category"%>
<%@page import="Model.Product"%>
<%@page import="Model.Status"%>
<%@page import="Model.ProductDA"%>

<html>
    <head>
        <title>Product Table</title>
        <meta charset="UTF-8">
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
                    <button type="button" id="sidebarCollapse" class="btn btn-dark">
                        <i class="fas fa-bars"></i>
                    </button>
                    <h2>Product Table</h2>
                </div>
            </nav>
                
                <%
                    //Display All Product Records From DB
                    ProductDA prodDA = new ProductDA();
                    String view = "";
                    view = request.getParameter("view");
                    if(view.equals("Inactive")){
                        request.setAttribute("product", prodDA.GetAllProduct(0));
                    }else{
                        request.setAttribute("product", prodDA.GetAllProduct(1)); 
                    }
                    int count = 1;
                %>
                <form method="POST" action="" class="mx-auto m-3" style="width: 95%;">
                    <!--Start Table-->
                    <table class="table table-bordered table-hover" style="text-align:center;">
                        <thead>
                            <tr style="text-align: center;">
                              <th scope="col" width="3%;">#</th>
                              <th scope="col" width="10%;">Product Name</th>
                              <th scope="col" width="35%;">Description</th>
                              <th scope="col" width="8%;">Price (RM)</th>
                              <th scope="col" width="15%;">Image</th>
                              <th scope="col" width="5%;">Quantity</th>
                              <th scope="col" width="5%;">Category</th>
                              <th scope="col" width="9%;">Action</th>  
                            </tr>
                        </thead>
                        <tbody>   
                            <c:forEach items="${product}" var="p">   
                                <tr>
                                    <td scope="col"><%= count %></td>
                                    <td scope="col">${p.prodName}</td>
                                    <td scope="col" style="text-align:left;">
                                      <table style="width: 100%;">
                                          <tr>
                                              <td width="30%;">Camera</td><td>${p.desc.split(",")[0]}</td>
                                          </tr>
                                          <tr>
                                              <td>Display</td><td>${p.desc.split(",")[1]}</td>
                                          </tr>
                                          <tr>
                                              <td>Screen</td><td>${p.desc.split(",")[2]}"</td>
                                          </tr>
                                          <tr>
                                              <td>Battery</td><td>Up to ${p.desc.split(",")[3]} hours video playback</td>
                                          </tr>
                                      </table>
                                    </td>
                                    <td scope="col">${p.price}.00</td>             
                                    <td scope="col"><img src="../../DisplayImgServlet?prodID=${p.prodID}" width="200" /></td>
                                    <td scope="col">${p.qty}</td>
                                    <td scope="col">${p.category.catName}</td>
                                    <td scope="col" style="text-align: center;"> 
                                        <% if(view.equals("Inactive")){ %>  
                                            <a href="${pageContext.request.contextPath}/ProductServlet?submit=Reactive&prodID=${p.prodID}">
                                                <button type="button" class="btn btn-danger p-2 m-0"> Reactivate </button> 
                                            </a>
                                        <% }else{ %>
                                            <a href="../Form/ProductForm.jsp?submit=Update&prodID=${p.prodID}">
                                                <button type="button" class="btn btn-warning p-2 m-0"> Update </button> 
                                            </a><br/><br/>
                                            <button type="button" class="btn btn-danger  p-2 m-0" data-toggle="modal" data-target="#exampleModal">
                                                Delete
                                            </button>
                                            
                                            <!-- Modal -->
                                            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                              <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                  <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLabel">Are You Sure?</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                      <span aria-hidden="true">&times;</span>
                                                    </button>
                                                  </div>
                                                  <div class="modal-body">
                                                      Are you sure to delete the <b>product</b> record?
                                                  </div>
                                                  <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary mr-auto" data-dismiss="modal">Close</button>
                                                    <a href="../../ProductServlet?submit=Delete&prodID=${p.prodID}"><button type="button" class="btn btn-info" value="Update" name="submit">Confirm</button></a>
                                                  </div>
                                                </div>
                                              </div>
                                            </div>
                                            <!-- End of Modal -->
                                            
                                        <% } %>  
                                    </td>
                                </tr>
                                <% count++; %>
                            </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr>
                                <% if(view.equals("Inactive")){ %>
                                <td colspan="9" style="text-align: center;"> 
                                    <a href="DisplayProduct.jsp?view=Active">
                                        <button type="button" class="btn btn-info p-2 m-0"> Back to Active Products </button>
                                    </a>
                                </td>
                                <% }else{ %>
                                <td colspan="6" style="text-align:left;">
                                    <a href="DisplayProduct.jsp?view=Inactive">
                                        <button type="button" class="btn btn-info p-2 m-0"> View Inactive Products </button>
                                    </a>
                                </td>
                                <td colspan="3">
                                    <a href="../Form/ProductForm.jsp?submit=Insert">
                                        <button type="button" class="btn btn-info p-2 m-0" > Insert New Category </button>
                                    </a>
                                </td>
                                <% } %>
                            </tr>
                        </tfoot>
                    </table>            
                    <!--End Table-->
                    
                </form>     
            </div>
        </div>

    </body>
</html>