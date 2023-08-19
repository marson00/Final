/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CustomerDA;
import Model.CustomerDetail;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import Model.User;
import Model.UserDA;

/**
 *
 * @author User
 */
@WebServlet(name = "CustomerServlet", urlPatterns = {"/Controller/CustomerServlet/*"})
public class CustomerServlet extends HttpServlet {

    private CustomerDA customerDA = CustomerDA.getInstance();
    private UserDA userDA = UserDA.getInstance();
    private static final Logger LOGGER = Logger.getLogger(CustomerServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String uri = request.getRequestURI();
        System.out.print(uri);

        String action = uri.substring(uri.lastIndexOf("/"), (uri.contains("?") ? uri.indexOf("?") : uri.length()));
        try {
            switch (action) {
                case "/insert":
                    System.out.print("test");
                    insertDetail(request, response);
                    break;
                case "/update":
                    System.out.print("hello");
                    updateDetail(request, response);
                    break;
                case "/listall":
                    listDetail(request, response);
                    break;
                default:
                    break;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQL Error", e);
        }
    }

    private void insertDetail(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException 
    {
        String loginID = getSessionAttribute(request, "loginID");
        User exist = (userDA.find(loginID)).get();
        String accountID = generateID(customerDA.findAll(), exist.getRoleID());
        String gender = request.getParameter("gender");
        int age = Integer.parseInt(request.getParameter("age"));
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        int points = 0;
        String username = request.getParameter("username");

        Boolean isInit = Boolean.parseBoolean((String) request.getParameter("init"));

        CustomerDetail newCustomerDetail = new CustomerDetail(accountID, username, gender, age, phone, address, points, loginID);
        customerDA.save(newCustomerDetail);

        String pathParam = isInit ? (exist.getRoleID().equals("R04") ? " " : "&init=true") : " ";

        alertAndRedirect("Inserted successfully!", "/JAVAASM/AdminView/Form/AccountDetailsForm.jsp?loginID=" + loginID + pathParam, response);
    }

    private void updateDetail(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String accountID = request.getParameter("accountID");
        String username = request.getParameter("username");
        String gender = request.getParameter("gender");
        int age = Integer.parseInt(request.getParameter("age"));
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        int points = Integer.parseInt(request.getParameter("points"));

        String loginID = request.getParameter("loginID");
        User exist = (userDA.find(loginID)).get();
        Boolean isInit = Boolean.parseBoolean((String) request.getParameter("init"));
        CustomerDetail customerDetail = new CustomerDetail(accountID, username, gender, age, phone, address, points, loginID);
        customerDA.update(customerDetail);

        String pathParam = Boolean.parseBoolean(request.getParameter("staffOnly"))
                ? ("&staffOnly=true")
                : ("&staffOnly=false");

        pathParam += isInit ? (exist.getRoleID().equals("R04") ? " " : "&init=true") : " ";
        alertAndRedirect("Updated successfully!", "/JAVAASM/AdminView/Form/AccountDetailsForm.jsp?loginID=" + loginID + pathParam, response);

    }

    private void listDetail(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminView/Display/DisplayAccountDetails.jsp");

        List<CustomerDetail> listCustomerDetail = customerDA.findAll();
        request.setAttribute("listCustomerDetail", listCustomerDetail);

        dispatcher.forward(request, response);
    }

    private String generateID(List<CustomerDetail> customerDetails, String roleID) {
        Integer idNumber = customerDetails.size() + 1;

        return (roleID.equals("R04") ? "A" : "S") + (idNumber < 10 ? "00" : (idNumber < 100 ? "0" : "")) + idNumber.toString();
    }

    private String getLoginID(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String loginID = null;
        try {
            loginID = (String) session.getAttribute("loginID");
        } catch (Exception ex) {
            loginID = " ";
        }
        return loginID;
    }

    private void alertAndRedirect(String alertMessage, String redirect, HttpServletResponse res) throws IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('" + alertMessage + "');");
        out.println("window.location.href = \"" + redirect + " \";");
        out.println("</script>");
    }

    private String getSessionAttribute(HttpServletRequest request, String attributeName) {
        HttpSession session = request.getSession();
        return (String) session.getAttribute(attributeName);
    }
}

