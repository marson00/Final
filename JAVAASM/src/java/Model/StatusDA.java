/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author mikae
 */
import Model.Status;
import java.sql.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

public class StatusDA 
{
    //declare & initialize db connection variable
    private String host = "jdbc:derby://localhost:1527/javaassignmentdb";
    private String user = "javadb";
    private String password = "javadb";
    private Connection conn;
    private PreparedStatement stmt;
    //declare & initialize table & sql variable
    private String tableName = "STATUS_TABLE";
    private String status = "STATUS";
    private String statusTitle= "STATUSTITLE";
    private String sqlSelectStr = "SELECT * FROM " + tableName;
    private String sqlInsertStr = "INSERT INTO " + tableName + " VALUES(?,?)";
    private String sqlUpdateStr = "UPDATE " + tableName + " SET " + statusTitle + " = ? WHERE " + status + " = ?";
    
    public StatusDA()
    {
        createConnection();
    }
    
    public List<Status> GetAllStatus() throws SQLException
    {
        List<Status> statusList = new ArrayList<>();
        
        stmt = conn.prepareStatement(sqlSelectStr);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            Status s = new Status();
            s.setStatus(rs.getInt(status));
            s.setStatusTitle(rs.getString(statusTitle));
            statusList.add(s);
        }
        return statusList;
    }
    
    public Status RetrieveStatus(int st) throws SQLException
    {
        Status s = null;
        
        stmt = conn.prepareStatement(sqlSelectStr + " WHERE " + status + " = ?");
        stmt.setInt(1, st);
        
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            s= new Status(st, rs.getString(statusTitle));
        }
        return s;
    }
    
    public int InsertStatus(int st, String statusTitle) throws SQLException
    {
        int result = 0;
        stmt = conn.prepareStatement(sqlInsertStr);
        stmt.setInt(1, st);
        stmt.setString(2, statusTitle);
        result = stmt.executeUpdate();
        return result;
    }
    
    public void UpdateStatus(int st, String statusTitle) throws SQLException
    {
        stmt = conn.prepareStatement(sqlUpdateStr);
        stmt.setString(1, statusTitle);
        stmt.setInt(2, st);
        stmt.executeUpdate();
    }
    
    private void createConnection()
    {
        try 
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection(host, user, password);
        } 
        catch (ClassNotFoundException | SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void shutDown() 
    {
        if (conn != null) 
        {
            try 
            {
                conn.close();
            } 
            catch (SQLException ex) 
            {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
}
