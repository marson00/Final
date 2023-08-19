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
import Model.StatusDA;
import Model.Status;

@WebServlet(name = "StatusServlet", urlPatterns = {"/StatusServlet"})
public class StatusServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int count = 0;
        String action = request.getParameter("submit");
        int status = 0;
        String statusTitle = request.getParameter("statusTitle");
        List<Status> st;
        StatusDA stDA = new StatusDA();
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try{
            switch (action) {
                case "Insert":
                    //Count Number of Records
                    st = stDA.GetAllStatus();
                    for (int i = 0; i <= st.size(); i++) {
                        count++;
                    }
                    count = count - 1;
                    int result = stDA.InsertStatus(count, statusTitle); 
                    if(result>0){
                        response.sendRedirect("http://localhost:8080/JAVAASM/AdminView/Display/DisplayStatus.jsp");
                    }else{
                        out.println("Failed to Insert Status Record");
                    }
                    break;
                    
                case "Update":
                    int sts = Integer.parseInt(request.getParameter("status"));   
                    if(stDA.RetrieveStatus(sts) != null){
                        stDA.UpdateStatus(sts, statusTitle);
                        response.sendRedirect("http://localhost:8080/JAVAASM/AdminView/Display/DisplayStatus.jsp");
                    }else{
                        out.println("Status Record Not Found! Please re-enter.");
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