package Model;

import Model.Category;
import java.sql.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Marson(TingLe)
 */
public class CategoryDA 
{
    //declare & initialize db connection variable
    private String host = "jdbc:derby://localhost:1527/javaassignmentdb";
    private String user = "javadb";
    private String password = "javadb";
    private Connection conn;
    private PreparedStatement stmt;
    //declare & initialize table & sql variable
    private String tableName = "CATEGORY_TABLE";
    private String catID = "CATEGORYID";
    private String catName = "CATEGORYNAME";
    private String sqlSelectStr = "SELECT * FROM " + tableName;
    private String sqlInsertStr = "INSERT INTO " + tableName + " VALUES(?,?)";
    private String sqlUpdateStr = "UPDATE " + tableName + " SET " + catName + " = ? WHERE " + catID + " = ?";
    private int result = 0;
    
    public CategoryDA()
    {
        createConnection();
    }
    
    public List<Category> GetAllCategory() throws SQLException
    {
        List<Category> catList = new ArrayList<>();
        
        stmt = conn.prepareStatement(sqlSelectStr);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            Category cat = new Category();
            cat.setCatID(rs.getString(catID));
            cat.setCatName(rs.getString(catName));
            catList.add(cat);
        }
        return catList;
    }
    
    public Category RetrieveCategory(String id) throws SQLException
    {
        Category cat = null;
        
        stmt = conn.prepareStatement(sqlSelectStr + " WHERE " + catID + " = ?");
        stmt.setString(1, id);
        
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            cat = new Category(id, rs.getString(catName));
        }
        return cat;
    }
    
    public int InsertCategory(String catID, String catName) throws SQLException
    {
        stmt = conn.prepareStatement(sqlInsertStr);
        stmt.setString(1, catID);
        stmt.setString(2, catName);
        result = stmt.executeUpdate();
        return result;
    }
    
    public int UpdateCategory(String catID, String catName) throws SQLException
    {
        stmt = conn.prepareStatement(sqlUpdateStr);
        stmt.setString(1, catName);
        stmt.setString(2, catID);
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
