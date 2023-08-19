<%-- 
    Document   : ProductForm
    Created on : Mar 26, 2022, 4:41:43 PM
    Author     : Marson(TingLe)
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.Category"%>
<%@page import="Model.Product"%>
<%@page import="Model.CategoryDA"%>
<%@page import="Model.ProductDA"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Product Form</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <%@include file="../JSCSS/js.jsp" %>
        <script>
            $(document).ready(function(){
                $('#myModal').on('shown.bs.modal', function () {
                    $('#myInput').trigger('focus')
                })
            });
        </script>
    </head>
    <body>  
      <%
          String id = "";
          CategoryDA catDA = new CategoryDA();
          ProductDA prodDA = new ProductDA();
          String action = request.getParameter("submit"); 
          if(!action.equals("Insert")){
              id = request.getParameter("prodID");
              request.setAttribute("prod", prodDA.RetrieveProduct(id));
              request.setAttribute("catID",prodDA.RetrieveProduct(id).getCategory().getCatID());
          }
          request.setAttribute("category", catDA.GetAllCategory());
          
      %>  
      <form method="POST" action="${pageContext.request.contextPath}/ProductServlet" enctype="multipart/form-data" class="mx-auto w-50 m-3" >
        <%
            if(action.equals("Update")){
        %>  
        <div class="form-group">
              <label for="prodID">Product ID</label>
              <input type="text" class="form-control" value="${prod.prodID}" id="prodID" name="prodID" readonly>
        </div><br/>
        <% } %>
        <div class="form-group">
              <label for="prodName">Product name</label>
              <input type="text" class="form-control" value="${prod.prodName}" id="prodName" name="prodName" placeholder="Product Name" required>
        </div><br/>
        <div class="form-row">
            <div class="col-md-6 mb-3">
              <label for="camera">Camera Feature</label>
              <input type="text" class="form-control" value="${prod.desc.split(",")[0]}" id="camera" name="camera" placeholder="Dual‑camera system" required>
            </div>
            <div class="col-md-6 mb-3">
              <label for="display">Display Feature</label>
              <input type="text" class="form-control" value="${prod.desc.split(",")[1]}" id="display" name="display" placeholder="LCD display" required>
            </div>     
        </div><br/>
        <div class="form-row">
            <div class="col-md-2 mb-3">
              <label for="screen">Screen Size </label>
              <select class="custom-select" id="screen" name="screen" required>
               <% 
                for(double i=1; i<17; i=i+0.5){
                  request.setAttribute("screen", i);
               %>
                <option value="<%=i%>" ${prod.desc.split(",")[2] == screen ? "selected" : ""}><%=i%></option>
               <% ; } %>
              </select>
            </div>
            <div class="col-md-2 mb-3">
              <label for="battery">Battery</label>
              <input type="number" class="form-control" value="${prod.desc.split(",")[3]}" id="battery" name="battery" min="0" placeholder="10" required>
            </div>
            <div class="col-md-3 mb-3">
              <label for="price">Price (RM)</label>
              <input type="number" class="form-control" value="${prod.price}" id="price" name="price" min="0" placeholder="3000" required>
            </div> 
            <div class="col-md-3 mb-3">
              <label for="category">Category</label>
              <select class="custom-select" id="category" name="category" required>
              <c:forEach items="${category}" var="cat">
                <option value="${cat.catID}" ${cat.catID == catID ? "selected" : ""}>${cat.catName}</option>
              </c:forEach>
              </select>
            </div>
            <div class="col-md-2 mb-3">
              <label for="qty">Quantity</label>
              <input type="number" class="form-control" value="${prod.qty}" id="qty" name="qty" min="0" placeholder="500" required>
            </div>            
        </div><br/>
        <div class="form-group custom-file">
              <input type="file" class="custom-file-input" id="image" name="image" lang="pl-Pl" required="required">
              <label class="custom-file-label" for="image">Upload A Picture</label>
        </div><br/><br/>
         
        <%
            if(action.equals("Insert")){
        %> 
        <input type="submit" class="btn btn-info" value="Insert" id="submit" name="submit"/>    
        <% }else{ %>
        
        <button type="button" class="btn btn-info" data-toggle="modal" data-target="#exampleModal">
            Update
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
                  Are you sure to update the <b>product</b> record?
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary mr-auto" data-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-info" value="Update" name="submit">Confirm</button>
              </div>
            </div>
          </div>
        </div>
        <!-- End of Modal -->
        <% } %>
        
      </form>
        
    <script>
        $(".custom-file-input").on("change", function() 
        {
            var fileName = $(this).val().split("\\").pop();
            $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
        }); 
    </script>
    </body>
</html>