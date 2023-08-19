package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.RoleDA;
import Model.Role;

@WebServlet(name = "RoleServlet", urlPatterns = {"/RoleServlet"})
public class RoleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Role> r;
        RoleDA rDA = new RoleDA();
        
        int count = 0;
        String action = request.getParameter("submit");
        
        String roleID = "";
        String roleTitle = request.getParameter("roleTitle");
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

          try{
            switch (action) 
            {
                case "Insert":
                    //Count Number of Records
                    r = rDA.GetAllRole();
                    for (int i = 0; i <= r.size(); i++) 
                    {
                        count++;
                    }
                    roleID = "R" + (count < 10 ? "0" : "") + count;
                    
                    int output = rDA.InsertRole(roleID, roleTitle);
                    if(output>0){
                    response.sendRedirect("http://localhost:8080/JAVAASM/AdminView/Display/DisplayRole.jsp");
                    }else{
                        out.println("Failed to Insert Role Record");
                    }
                    
                    break;
                    
                case "Update":
                    roleID = request.getParameter("roleID").toUpperCase();
                    
                    if(rDA.RetrieveRole(roleID) != null){
                        rDA.UpdateRole(roleID, roleTitle);
                        response.sendRedirect("http://localhost:8080/JAVAASM/AdminView/Display/DisplayRole.jsp");
                    }else{
                        out.println("Role Record Not Found! Please re-enter.");
                    }
                    break;
                default:
                    throw new AssertionError();
            }
            
        }catch(SQLException ex){
                out.println("ERROR: " + ex.toString() + "<br/><br/>");

                StackTraceElement[] elements = ex.getStackTrace();

                for(StackTraceElement e:elements){
                    out.println("File Name: " + e.getFileName() + "<br/>");
                    out.println("Class Name: " + e.getClassName()+ "<br/>");
                    out.println("Method Name: " + e.getMethodName()+ "<br/>");
                    out.println("Line Name: " + e.getLineNumber()+ "<br/>");
                }

            }
    } 

}