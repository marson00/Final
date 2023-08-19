/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Capacity;
import java.sql.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Marson(TingLe)
 */
public class CapacityDA
{
    //declare & initialize db connection variable
    private String host = "jdbc:derby://localhost:1527/javaassignmentdb";
    private String user = "javadb";
    private String password = "javadb";
    private Connection conn;
    private PreparedStatement stmt;
    //declare & initialize table & sql variable
    private String tableName = "CAPACITY_TABLE";
    private String capID = "CAPACITYID";
    private String capacity = "CAPACITY";
    private String addOns = "ADDONS";
    private String sqlSelectStr = "SELECT * FROM " + tableName;
    private String sqlInsertStr = "INSERT INTO " + tableName + " VALUES(?,?,?)";
    private String sqlUpdateStr = "UPDATE " + tableName + " SET " + capacity + " = ?, " + addOns + " = ? WHERE " + capID + " = ?";
    private int result = 0;
    
    public CapacityDA()
    {
        createConnection();
    }
    
    public List<Capacity> GetAllCapacity() throws SQLException
    {
        List<Capacity> capList = new ArrayList<>();
        
        stmt = conn.prepareStatement(sqlSelectStr);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            Capacity cap = new Capacity();
            cap.setCapID(rs.getString(capID));
            cap.setCapacity(Integer.parseInt(rs.getString(capacity)));
            cap.setAddOns(Integer.parseInt(rs.getString(addOns)));
            capList.add(cap);
        } 
        return capList;
    }
    
    public Capacity RetrieveCapacity(String id) throws SQLException
    {
        Capacity cap = null;
        
        stmt = conn.prepareStatement(sqlSelectStr + " WHERE " + capID + " = ?");
        stmt.setString(1, id);
        
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            cap = new Capacity(id, Integer.parseInt(rs.getString(capacity)), Integer.parseInt(rs.getString(addOns)));
        }
        return cap;
    }
    
    public int InsertCapacity(String capID, int capacity, int addOns) throws SQLException
    {
        stmt = conn.prepareStatement(sqlInsertStr);
        stmt.setString(1, capID);
        stmt.setInt(2, capacity);
        stmt.setInt(3, addOns);
        result = stmt.executeUpdate();
        return result;
    }
    
    public int UpdateCapacity(String capID, int capacity, int addOns) throws SQLException
    {
        stmt = conn.prepareStatement(sqlUpdateStr);;
        stmt.setInt(1, capacity);
        stmt.setInt(2, addOns);
        stmt.setString(3, capID);
        result = stmt.executeUpdate();
        return result;
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
