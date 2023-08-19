/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.UserDA;
import Model.User;
import Model.CustomerDetail;
import Model.CustomerDA;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/Controller/UserServlet/*"})
public class UserServlet extends HttpServlet 
{

    private UserDA userDA = UserDA.getInstance();
    private CustomerDA detailDA = CustomerDA.getInstance();
    private static final Logger LOGGER = Logger.getLogger(CustomerServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String uri = request.getRequestURI();
        String action = uri.substring(uri.lastIndexOf("/"), (uri.contains("?") ? uri.indexOf("?") : uri.length()));
        System.out.print(action);
         try {
            switch (action) {
                case "/login":
                    login(request, response);
                    break;
                case "/logout":
                    logout(request, response);
                    break;
                case "/register":
                    register(request, response);
                    break;
                case "/update":
                    update(request, response);
                    break;
                case "/insert":
                    insert(request, response);
                    break;
                default:
                    break;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQL Error", e);
        }
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        doPost(request, response);
    }

    void login(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException 
    {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Optional<User> user = userDA.findByEmail(email);

        if (user.isPresent()) 
        {
            User exist = user.get();

            exist.setLastLoginTime(new Date());
         
            if (exist.getPassword().equals(password)) 
            {

                // check if user status is 0 or 1, if 0 = tell them they are banned, if 1 = continue login
                if (exist.getStatus() > 0) 
                {
                    setSesssionAttr(request, "loginID", exist.getLoginID());
                    setSesssionAttr(request, "roleID", exist.getRoleID());
                    userDA.update(exist);
                    String roleId = exist.getRoleID();
                   
                    switch (roleId) 
                    {
                        case "R01":
                            System.out.print("check");
                            alertAndRedirect("Login Success", "/JAVAASM/AdminView/Display/AdminMainPage.jsp", response);
                            break;
                        case "R02":
                            alertAndRedirect("Login Success", "/JAVAASM/AdminView/Display/AdminMainPage.jsp", response);
                            break;
                        case "R03":
                            alertAndRedirect("Login Success", "/JAVAASM/AdminView/Display/AdminMainPage.jsp", response);
                            break;
                        case "R04":
                            alertAndRedirect("Login Success", "/JAVAASM/CustomerView/Display/HomePage.jsp", response);
                            break;
                    }
                } else {
                    alertAndRedirect("Incorrect Username or Password", "/JAVAASM/index.jsp", response);
                }
            } else {
                 alertAndRedirect("Incorrect Username or Password", "/JAVAASM/index.jsp", response);
            }
        } else {
             alertAndRedirect("Incorrect Username or Password", "/JAVAASM/index.jsp", response);
        }
    }

    void register(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException 
    {
        // get email and password from req parameter,
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // generate loginId, status, and roleID for user,
        List<User> users = userDA.findAll();
        System.out.print(users);
        String loginID = generateUserID(users != null ? users : new ArrayList<>());
        // insert into db via da,
        User user = new User(loginID, email, password, 1, null, "R04");
        userDA.save(user);
        
        int age = 0;
        int point = 0;
        String gender = "n";
        String address = "n";
        String phone = "n";
        User exist = (userDA.find(loginID)).get();
        String accountID = generateAccID(detailDA.findAll(), exist.getRoleID());
        CustomerDetail AccDetail = new CustomerDetail(accountID, username, gender, age, phone, address, point, loginID);
        
        Boolean result = detailDA.save(AccDetail);

        if (result) {
            alertAndRedirect("Register Success", "/JAVAASM/index.jsp", response);
        } else {
            alertAndRedirect("Opps, something went wrong, pls try again", "/JAVAASM/CustomerView/Display/CustomerRegister.jsp", response);
        }
        // if success, navigate to index

    }

    void logout(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException 
    {
        HttpSession session = request.getSession(false);
        if (session != null) 
        {
            session.removeAttribute("loginID");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/JAVAASM/index.jsp.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void insert(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
    {
        List<User> users = userDA.findAll();
        String loginID = generateUserID(users != null ? users : new ArrayList<>());
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String roleID = request.getParameter("roleID");

        //TODO: Error handling
        User newUser = new User(loginID, email, password, 1, null, roleID);
        userDA.save(newUser);
        
        int age = 0;
        int point = 0;
        String gender = "n";
        String address = "n";
        String phone = "n";
        User exist = (userDA.find(loginID)).get();
        String accountID = generateAccID(detailDA.findAll(), exist.getRoleID());
        CustomerDetail AccDetail = new CustomerDetail(accountID, username, gender, age, phone, address, point, loginID);
        detailDA.save(AccDetail);

        String pathParam = Boolean.parseBoolean(request.getParameter("staffOnly"))
                ? ("?action=view&loginID=" + loginID + "&staffOnly=true")
                : ("?action=view&loginID=" + loginID + "&staffOnly=false");

        alertAndRedirect("Inserted successfully!", "/JAVAASM/AdminView/Display/DisplayUsers.jsp" + pathParam, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException 
    {
        int status = Integer.parseInt(request.getParameter("status"));
        String username = request.getParameter("username");
        String roleID = request.getParameter("roleID");
        String loginID = request.getParameter("loginID");
        User exist = (userDA.find(loginID)).get();
        
        exist.setStatus(status);
        exist.setRoleID(roleID);
        
        userDA.update(exist);
        
        String pathParam = Boolean.parseBoolean(request.getParameter("staffOnly"))
                ? ("?action=view&loginID=" + loginID + "&staffOnly=true")
                : ("?action=view&loginID=" + loginID + "&staffOnly=false");

        alertAndRedirect("Updated successfully!", "/JAVAASM/AdminView/Display/DisplayUsers.jsp" + pathParam, response);

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

    private void setSesssionAttr(HttpServletRequest request, String attributeName, String attributeVal) 
    {
        HttpSession session = request.getSession();
        session.setAttribute(attributeName, attributeVal);
    }

    private String getSessionAttribute(HttpServletRequest request, String attributeName) 
    {
        HttpSession session = request.getSession();
        return (String) session.getAttribute(attributeName);
    }

    private String generateUserID(List<User> user) 
    {
        Integer loginID = user.size() <= 0 ? 1000 : 1000 + (user.size() + 1);
        return loginID.toString();
    }
    
    private String generateAccID(List<CustomerDetail> customerDetails, String roleID) {
        Integer idNumber = customerDetails.size() + 1;

        return (roleID.equals("R04") ? "A" : "S") + (idNumber < 10 ? "00" : (idNumber < 100 ? "0" : "")) + idNumber.toString();
    }

}
