<%-- 
    Document   : CapacityForm
    Created on : Mar 25, 2022, 8:58:13 AM
    Author     : Marson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Capacity Form</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <%@include file="../JSCSS/js.jsp" %>
    </head>
    <body>
      <form method="POST" action="${pageContext.request.contextPath}/CapacityServlet" class="mx-auto w-50 m-3">
        <% 
            String action = request.getParameter("submit"); 
            String capID = request.getParameter("id");
            if (action.equals("Insert")) {
        %> 

        <div class="form-group">
            <label for="capacity">Capacity:  </label>
            <input type="number" class="form-control" min="1" id="capacity" name="capacity" required/>
        </div>
        <div class="form-group">
            <label for="capacity">Add Ons: </label>
            <input type="number" class="form-control" min="0" id="addOns" name="addOns" required/>
        </div>
        <input type="submit" class="btn btn-info" value="Insert" name="submit" required/>
        
        <% } else if(action.equals("Update")){
            int capacity = Integer.parseInt(request.getParameter("cap"));
            int addOns = Integer.parseInt(request.getParameter("add"));
        %>
        
        <div class="form-group">
            <label for="capID">Capacity ID: </label>
            <input type="text" class="form-control" value="<%=capID%>" id="capID" name="capID" readonly />
        </div>
        <div class="form-group">
            <label for="capacity">New Capacity: </label>
            <input type="number" class="form-control" min="1" id="capacity" name="capacity" placeholder="<%=capacity%>" required/>
        </div>      
        <div class="form-group">
            <label for="capacity">Add Ons: </label>
            <input type="number" class="form-control" min="0" id="addOns" name="addOns" placeholder="<%=addOns%>" required/>
        </div>
        
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
                  Are you sure to update the <b>capacity</b> record?
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary mr-auto" data-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-info" value="Update" name="submit">Confirm</button>
              </div>
            </div>
          </div>
        </div>
        <!-- End of Modal -->
        
      </form>
         
        <% } else{%>
        
            <h1>Error!</h1>

        <% } %>

    </body>
</html>
