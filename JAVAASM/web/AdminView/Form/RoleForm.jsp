<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Role Form</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <%@include file="../JSCSS/js.jsp" %>
    </head>
    <body>
      <form method="POST" action="${pageContext.request.contextPath}/RoleServlet" class="mx-auto w-50 m-3">
        <% 
            String action = request.getParameter("submit"); 
           String roleID = request.getParameter("id");
            if (action.equals("Insert")) {
        %> 

        <div class="form-group">
            <label for="roleTitle">New Role Title: </label>
            <input type="text" class="form-control" id="statusTitle" name="roleTitle" required/>
        </div>

        <input type="submit" class="btn btn-info" value="Insert" name="submit"/>
        
        <% } else if(action.equals("Update"))
            { 
            String roleTitle = request.getParameter("name");
        %>

        <div class="form-group">
            <label for="roleID">Role ID: </label>
            <input type="text" class="form-control" value="<%=roleID%>" id="roleID" name="roleID" readonly/>
        </div>

        <div class="form-group">
            <label for="roleTitle">New Role Title: </label>
            <input type="text" class="form-control" id="roleTitle" name="roleTitle" placeholder="<%=roleTitle%>" required/>
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
                  Are you sure to update the <b>role</b> record?
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

