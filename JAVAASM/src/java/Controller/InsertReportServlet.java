package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.time.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Model.reportQuery;
import Model.DA;
import Model.reportQuery;


/**
 *
 * @author mikae
 */
@WebServlet(urlPatterns = {"/InsertReportServlet"})
public class InsertReportServlet extends HttpServlet 
{
    private DA rptDA;

    // Initialize variables
    @Override
    public void init() throws ServletException 
    {
        rptDA = new DA();
    }
    
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
        
        if(request.getParameter("createReport") != null);
        {
            // Obtain data from the form
            String reportTitle = request.getParameter("rptTitle");
            String listSize = request.getParameter("listSize");
            String startTime = request.getParameter("startTime");
            String endTime = request.getParameter("endTime");
            String product = request.getParameter("product");
            String category = request.getParameter("category");
            
            if(reportTitle == null || listSize == null || startTime == null || endTime == null || product == null || category == null)
            {
                out.println("dont be dumb");
                out.println("<a href=\"createReport.jsp\">Go back</a>");
                out.close();
            }
            else
            {
                String[] arrOfStrStart = startTime.split("/");
                String[] arrOfStrEnd = endTime.split("/");
                ArrayList<String> startDateArr = new ArrayList<>();
                ArrayList<String> endDateArr = new ArrayList<>();

                for(String a : arrOfStrStart)
                {
                    startDateArr.add(a);
                }

                for(String a : arrOfStrEnd)
                {
                    endDateArr.add(a);
                }

                String StartTime = startDateArr.get(2) + "-" + startDateArr.get(0) + "-" + startDateArr.get(1);
                String EndTime = endDateArr.get(2) + "-" + endDateArr.get(0) + "-" + endDateArr.get(1);

                reportQuery rpt = new reportQuery(reportTitle, listSize, StartTime, EndTime, product, category);

                String dataQuery = ("SELECT TOP '" + rpt.getListSize()) + "'";
                String conStr = "";
                String grpOrdByStr = "";

                if(rpt.getCategory() != null)
                {
                    if(rpt.getProduct() != null)
                    {
                        dataQuery += " P.PRODUCTNAME, O.PRODUCTID, C.CATEGORYNAME, O.CATEGORYID, SUM(O.QUANITY) FROM ORDER_TABLE O, PRODUCT_TABLE P, CATEGORY_TABLE C";
                        conStr = " P.PRODUCTNAME = O.PRODUCTNAME AND O.CATEGORYNAME = C.CATEGORYNAME AND O.PRODUCTNAME = '" + rpt.getProduct() + "' AND C.CATEGORYNAME = '" + rpt.getCategory() + "'";
                        grpOrdByStr = " GROUP BY P.PRODUCTNAME, O.PRODUCTID, C.CATEGORYNAME, O.CATEGORYID, O.QUANTITY ORDER BY O.QUANTITY DESC";
                    }
                    else
                    {
                        dataQuery += (" C.CATEGORYNAME, O.CATEGORYID, SUM(O.QUANITY) FROM ORDER_TABLE O, CATEGORY_TABLE C");
                        conStr = " O.CATEGORYNAME = C.CATEGORYNAME AND O.CATEGORYNAME = '" + rpt.getCategory() + "'";
                        grpOrdByStr  = " GROUP BY C.CATEGORYNAME, O.CATEGORYID, O.QUANTITY ORDER BY O.QUANTITY DESC";
                    }
                }

                if(rpt.getStartTime() == null ? rpt.getEndTime() == null : rpt.getStartTime().equals(rpt.getEndTime()))
                {
                    dataQuery += " WHERE STARTTIME = '" + rpt.getStartTime() + "'";
                    if(grpOrdByStr == null)
                    {
                        grpOrdByStr += " ORDER BY DATEPURCHASE DESC";
                    }
                }
                else
                {
                    dataQuery += " WHERE STARTTIME >= '" + rpt.getStartTime() + "' AND STARTTIME <= '" + rpt.getEndTime() + "'";
                    if(grpOrdByStr == null)
                    {
                        grpOrdByStr += " ORDER BY DATEPURCHASE DESC";
                    }
                }

                conStr += grpOrdByStr;
                dataQuery += conStr;

                String rptID = null;
                String num = "";
                String newRptId = null;

                try
                {
                    ResultSet rs = rptDA.findReportID();
                    if (rs.next()) 
                    {
                        rptID = rs.getString("ReportID");
                    }
                }
                catch(SQLException ex)
                {
                    out.println("ERROR: " + ex.getMessage());
                }

                if(rptID != null)
                {
                    for(int i = 0; i < rptID.length(); i++)
                    {
                        if (Character.isDigit(rptID.charAt(i)))
                        {
                            if(rptID.charAt(i) != 0)
                            {
                                num += (rptID.charAt(i));
                            }
                        }
                    }

                    int addNum = Integer.parseInt(num);

                    addNum += 1;

                    String newNum = String.valueOf(addNum);

                    if(newNum.length() == 1)
                    {
                        newRptId = "R000" + newNum;
                    }
                    else if(newNum.length() == 2)
                    {
                        newRptId = "R00" + newNum;
                    }
                    else if(newNum.length() == 3)
                    {
                        newRptId = "R0" + newNum;
                    }
                    else if(newNum.length() == 4)
                    {
                        newRptId = "R" + newNum;
                    }
                }
                else
                {
                    newRptId = "R0001";
                }

                try 
                {
                    rptDA.createReport(newRptId, rpt, dataQuery);
                } 
                catch (Exception ex) 
                {
                    out.println("ERROR: " + ex.getMessage());
                }
                finally
                {
                    out.close();
                }
            } 
        }
    }
}
