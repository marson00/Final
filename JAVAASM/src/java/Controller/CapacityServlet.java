/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import Model.Capacity;
import Model.CapacityDA;

/**
 *
 * @author Marson(TingLe)
 */
@WebServlet(name = "CapacityServlet", urlPatterns = {"/CapacityServlet"})
public class CapacityServlet extends HttpServlet 
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Capacity> cap;
        CapacityDA capDA = new CapacityDA();
        
        int count = 0;
        int result = 0;
        String action = request.getParameter("submit");
        String capID = "";
        int capacity = Integer.parseInt(request.getParameter("capacity"));
        int addOns = Integer.parseInt(request.getParameter("addOns"));
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try{
            switch (action) 
            {
                case "Insert":
                    //Count Number of Records
                    cap = capDA.GetAllCapacity();
                    for (int i = 0; i <= cap.size(); i++) 
                    {
                        count++;
                    }
                    capID = "CP" + (count < 10 ? "0" : "") + count;

                    result = capDA.InsertCapacity(capID, capacity, addOns);     
                    if(result > 0)
                    {
                        response.sendRedirect("http://localhost:8080/JAVAASM/AdminView/Display/DisplayCapacity.jsp");
                    }
                    break;
                    
                case "Update":
                    capID = request.getParameter("capID").toUpperCase();
                    
                    if(capDA.RetrieveCapacity(capID) != null){
                        result = capDA.UpdateCapacity(capID, capacity, addOns);
                        if(result > 0)
                        {
                            response.sendRedirect("http://localhost:8080/JAVAASM/AdminView/Display/DisplayCapacity.jsp");
                        }
                    }else{
                        out.println("Category Record Not Found! Please re-enter.");
                    }
                    break;
                default:
                    throw new AssertionError();
            }
            
        }catch(SQLException ex)
        {
                out.println("ERROR: " + ex.toString() + "<br/><br/>");

                StackTraceElement[] elements = ex.getStackTrace();

                for(StackTraceElement e:elements)
                {
                    out.println("File Name: " + e.getFileName() + "<br/>");
                    out.println("Class Name: " + e.getClassName()+ "<br/>");
                    out.println("Method Name: " + e.getMethodName()+ "<br/>");
                    out.println("Line Name: " + e.getLineNumber()+ "<br/>");

                }

        }
        
    }

}
