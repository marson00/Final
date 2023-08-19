/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.sql.SQLException;
import java.util.List;
import Model.CategoryDA;
import Model.Category;


/**
 *
 * @author Marson(TingLe)
 */
@WebServlet(name = "CategoryServlet", urlPatterns = {"/CategoryServlet"})
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> cat;
        CategoryDA catDA = new CategoryDA();
        
        int count = 0;
        int result = 0;
        String action = request.getParameter("submit");
        String catID = "";
        String catName = request.getParameter("catName");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try{
            switch (action) 
            {
                case "Insert":
                    //Count Number of Records
                    cat = catDA.GetAllCategory();
                    for (int i = 0; i <= cat.size(); i++) 
                    {
                        count++;
                    }
                    catID = "C" + (count < 10 ? "0" : "") + count;

                    result = catDA.InsertCategory(catID, catName); 
                    if(result > 0)
                    {
                        response.sendRedirect("http://localhost:8080/JAVAASM/AdminView/Display/DisplayCategory.jsp");
                    }
                    break;
                    
                case "Update":
                    catID = request.getParameter("catID").toUpperCase();
                    
                    if(catDA.RetrieveCategory(catID) != null){
                        result = catDA.UpdateCategory(catID, catName);
                        if(result > 0)
                        {
                            response.sendRedirect("http://localhost:8080/JAVAASM/AdminView/Display/DisplayCategory.jsp");
                        }
                    }else{
                        out.println("Category Record Not Found! Please re-enter.");
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
