/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.UserDA;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mikae
 */
@WebServlet(name = "ForgotPasswordServlet", urlPatterns = {"/Controller/ForgotPasswordServlet"})
public class ForgotPasswordServlet extends HttpServlet 
{
    private UserDA userDA = UserDA.getInstance();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Optional<User> user = null;
        
        String email = request.getParameter("emailPassedAgain");
        String password = request.getParameter("password");
        
        try
        {
            user = userDA.findByEmail(email);
            if(user.isPresent())
            {
                User exist = user.get();
                exist.setPassword(password);
                userDA.update(exist);
                
                alertAndRedirect("Your password have been successfully changed.", "/JAVAASM/index.jsp", response);
            }
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(ForgotPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void alertAndRedirect(String alertMessage, String redirect, HttpServletResponse res) throws IOException 
    {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('" + alertMessage + "');");
        out.println("window.location.href = \"" + redirect + " \";");
        out.println("</script>");
    }
}
