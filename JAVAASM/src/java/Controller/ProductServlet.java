/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import java.sql.SQLException;
import java.util.List;
import Model.Product;
import Model.ProductDA;

/**
 *
 * @author User
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/ProductServlet"})
@MultipartConfig(maxFileSize = 16777215)
public class ProductServlet extends HttpServlet 
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {         
        doPost(request, response);    
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        ProductDA prodDA = new ProductDA();
        List<Product> prod;
        
        String action = request.getParameter("submit");
        
        String prodID = "";
        String prodName = "";
        String camera = "";
        String display = "";
        float screenSize = 0;
        int battery = 0;
        int price = 0;
        int qty = 0;
        int status = 0;
        String catID = "";
        String desc = "";
        Part part = null;
        InputStream is = null;
        int result = 0;
        int count = 0;
        
        if(!action.equals("Delete") && !action.equals("Reactive"))
        {
            prodName = request.getParameter("prodName");
            camera = request.getParameter("camera");
            display = request.getParameter("display");
            screenSize = Float.parseFloat(request.getParameter("screen"));
            battery = Integer.parseInt(request.getParameter("battery"));
            price = Integer.parseInt(request.getParameter("price"));
            qty = Integer.parseInt(request.getParameter("qty"));
            status = 1; //Always set the insert record to active, except delete
            catID = request.getParameter("category");
            desc = camera + "," + display + "," + screenSize + "," + battery;
            part = request.getPart("image");
            is = part.getInputStream(); 
        }
        
        if(!action.equals("Insert"))
        {
            prodID = request.getParameter("prodID"); 
        }
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        try
        {
            switch(action)
            {
                case "Insert":
                    //Count number of Product Records
                    prod = prodDA.GetAllProduct(2);
                    for (int i = 0; i <= prod.size(); i++) 
                    {
                        count++;
                    }

                    prodID = "P" + (count < 100 ? ( (count < 10) ? "00" : "0" ) : "") + count;
                    out.println(prodID);
                    result = prodDA.InsertProduct(prodID, prodName, desc, price, is, qty, status, catID);
                    if(result > 0)
                    {
                        response.sendRedirect("http://localhost:8080/JAVAASM/AdminView/Display/DisplayProduct.jsp?view=All");
                    }
                    break;
                    
                case "Update":
                    result = prodDA.UpdateProduct(prodID, prodName, desc, price, is, qty, status, catID);
                    if(result > 0)
                    {
                        response.sendRedirect("http://localhost:8080/JAVAASM/AdminView/Display/DisplayProduct.jsp?view=All");
                    }else{
                        out.println("Fail to Update");
                    }
                    break; 
                    
                case "Delete":
                    result = prodDA.DORProduct(prodID, status);
                    if(result > 0)
                    {
                        response.sendRedirect("http://localhost:8080/JAVAASM/AdminView/Display/DisplayProduct.jsp?view=All");
                    }else{
                        out.println("Fail to Delete");
                    }
                    break;
                
                case "Reactive":
                    status = 1;
                    result = prodDA.DORProduct(prodID, status);
                    if(result > 0)
                    {
                        response.sendRedirect("http://localhost:8080/JAVAASM/AdminView/Display/DisplayProduct.jsp?view=All");
                    }else{
                        out.println("Fail to Delete");
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