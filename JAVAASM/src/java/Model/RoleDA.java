/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Role;
import java.sql.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

public class RoleDA 
{
    //declare & initialize db connection variable
    private String host = "jdbc:derby://localhost:1527/javaassignmentdb";
    private String user = "javadb";
    private String password = "javadb";
    private Connection conn;
    private PreparedStatement stmt;
    //declare & initialize table & sql variable
    private String tableName = "ROLE_TABLE";
    private String roleID = "ROLEID";
    private String roleTitle= "ROLETITLE";
    private String sqlSelectStr = "SELECT * FROM " + tableName;
    private String sqlInsertStr = "INSERT INTO " + tableName + " VALUES(?,?)";
    private String sqlUpdateStr = "UPDATE " + tableName + " SET " + roleTitle + " = ? WHERE " + roleID + " = ?";
    
    public RoleDA()
    {
        createConnection();
    }
    
    public List<Role> GetAllRole() throws SQLException
    {
        List<Role> roleList = new ArrayList<>();
        
        stmt = conn.prepareStatement(sqlSelectStr);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            Role r = new Role();
            
            r.setRoleID(rs.getString(roleID));
            r.setRoleTitle(rs.getString(roleTitle));
            roleList.add(r);
        }
        return roleList;
    }
    
    public Role RetrieveRole(String rID) throws SQLException
    {
        Role r = null;
        
        stmt = conn.prepareStatement(sqlSelectStr + " WHERE " + roleID + " = ?");
        stmt.setString(1, rID);
        
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            r= new Role(rID, rs.getString(roleTitle));
        }
        return r;
    }
    
    public int InsertRole(String rID, String roleTitle) throws SQLException
    {
        int result = 0;
        stmt = conn.prepareStatement(sqlInsertStr);
        stmt.setString(1,rID);
        stmt.setString(2, roleTitle);
        result = stmt.executeUpdate();
        return result;
    }
    
    public void UpdateRole(String rID, String roleTitle) throws SQLException
    {
        stmt = conn.prepareStatement(sqlUpdateStr);
        stmt.setString(1, roleTitle);
        stmt.setString(2,rID);
        stmt.executeUpdate();
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
