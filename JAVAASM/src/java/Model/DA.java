package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mikae
 */

import java.time.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class DA
{
    private String host = "jdbc:derby://localhost:1527/javaassignmentdb";
    private String user = "javadb";
    private String password = "javadb";
    private String tablename = "REPORT_TABLE";
    private Connection conn;
    private PreparedStatement stmt;
    
    public DA()
    {
       createConnection();
    }
    
    public void createReport(String rptID, reportQuery rpt, String dataQuery)
    {
        String queryStr = "INSERT INTO " + tablename + " VALUES (?, ?, ?, ?)";
        try
        {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, rptID);
            stmt.setString(2, rpt.getReportTitle());
            stmt.setString(3, dataQuery);
            stmt.setDate(4, Date.valueOf(LocalDate.now()));
            stmt.executeUpdate();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    public ResultSet findReportID()
    {
        ResultSet report = null;
        String queryStr = "SELECT REPORTID FROM REPORT_TABLE ORDER BY REPORTID DESC FETCH FIRST 1 ROWS ONLY";
        try
        {
            stmt = conn.prepareStatement(queryStr);
            report = stmt.executeQuery();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } 
        return report;
    }
    
    public List<report> getAllReport()
    {
        List<report> reports = new ArrayList<report>();
        ResultSet result;
        String queryStr = "SELECT * FROM REPORT_TABLE";
        try
        {
            stmt = conn.prepareStatement(queryStr);
            result = stmt.executeQuery();
            
            while(result.next())
            {
                report rpt = new report();
                rpt.setReportID(result.getString("reportID"));
                rpt.setReportTitle(result.getString("reportTitle"));
                rpt.setReportQuery(result.getString("dataQuery"));
                rpt.setReportCreatedDate(result.getDate("createdTime"));
                reports.add(rpt);
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return reports;     
    }
    
    private void createConnection()
    {
        try 
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection(host, user, password);
        } 
        catch (Exception ex) 
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}

