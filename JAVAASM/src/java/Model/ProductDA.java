/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import Model.Product;
import Model.StatusDA;

/**
 *
 * @author Marson
 */
public class ProductDA
{
    //declare & initialize db connection variable
    private String host = "jdbc:derby://localhost:1527/javaassignmentdb";
    private String user = "javadb";
    private String password = "javadb";
    private Connection conn;
    private PreparedStatement stmt;
    //declare & initialize table & sql variable
    private String tableName = "PRODUCT_TABLE";
    private String prodID = "PRODUCTID";
    private String prodName = "PRODUCTNAME";
    private String desc = "DESCRIPTION";
    private String price = "ORIGINALPRICE";
    private String imgUrl = "IMAGEURL";
    private String qty = "QUANTITY";
    private String statID = "STATUSID";
    private String catID = "CATEGORYID";
    private String sqlSelectStr = "SELECT * FROM " + tableName;
    private String sqlInsertStr = "INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?,?,?)";
    private String sqlUpdateStr = "UPDATE " + tableName + " SET " 
                                 + prodName + " = ?, " + desc + " = ?, "
                                 + price + " = ?, " + imgUrl + " = ?, " 
                                 + qty + " = ?, " + statID + "= ?, " 
                                 + catID + " = ? WHERE " + prodID + " = ?" ;
    private String sqlDORStr = "UPDATE " + tableName + " SET " + statID + " = ? WHERE " + prodID + " = ?";  //DOR = Delete Or Reactive
    private String sqlDeleteImgStr = "UPDATE " + tableName + " SET " + imgUrl + " = null WHERE " + prodID + " = ?";
    private int result = 0;
    
    public ProductDA()
    {
        createConnection();
    }
    
    public List<Product> GetAllProduct(int condition) throws SQLException
    {
        List<Product> prodList = new ArrayList<>();
        ResultSet rs = null;
        
        switch(condition)
        {
            case 0:     // 0 means get all product is inactive only
                stmt = conn.prepareStatement(sqlSelectStr + " WHERE " + statID + " = 0 ORDER BY " + prodID + " DESC"); 
                break;
            case 1:     // 1 means get all product is active only
                stmt = conn.prepareStatement(sqlSelectStr + " WHERE " + statID + " = 1 ORDER BY " + prodID + " DESC");   
                break;
            default:
                stmt = conn.prepareStatement(sqlSelectStr);
                break;    
        }        
  
        rs = stmt.executeQuery();
        
        while (rs.next()) 
        {
            Product prod = new Product();
            StatusDA statDA = new StatusDA();
            CategoryDA catDA = new CategoryDA();
            
            prod.setProdID(rs.getString(prodID));
            prod.setProdName(rs.getString(prodName));
            prod.setDesc(rs.getString(desc));
            prod.setPrice(rs.getInt(price));
            prod.setQty(rs.getInt(qty));
            prod.setImage(rs.getBlob(imgUrl));
            prod.setStatus(statDA.RetrieveStatus(rs.getInt(statID)));
            prod.setCategory(catDA.RetrieveCategory(rs.getString(catID)));   
            prodList.add(prod);
        }    

        rs.close();
        stmt.close();
        return prodList;
    }
    
    public Product RetrieveProduct(String productID) throws SQLException
    {
        Product prod = null;
        StatusDA statDA = new StatusDA();
        CategoryDA catDA = new CategoryDA();
        
        stmt = conn.prepareStatement(sqlSelectStr + " WHERE " + prodID + " = ?");
        stmt.setString(1, productID);
        
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            prod = new Product(productID, rs.getString(prodName), rs.getString(desc), rs.getInt(price), rs.getInt(qty), rs.getBlob(imgUrl), statDA.RetrieveStatus(rs.getInt(statID)), catDA.RetrieveCategory(rs.getString(catID)));
        }
        
        rs.close();
        stmt.close();
        return prod;
    }
    
    public int InsertProduct(String productID, String productName, String description, int oriPrice, InputStream is, int quantity, int statusID, String categoryID) throws SQLException
    {
        stmt = conn.prepareStatement(sqlInsertStr);

        stmt.setString(1, productID);
        stmt.setString(2, productName);
        stmt.setString(3, description);
        stmt.setInt(4, oriPrice);
        stmt.setBlob(5, is);
        stmt.setInt(6, quantity);
        stmt.setInt(7, statusID);
        stmt.setString(8, categoryID);
        
        result = stmt.executeUpdate();
        shutDown();
        stmt.close();
        return result;
    }
    
    public int UpdateProduct(String productID, String productName, String description, int oriPrice, InputStream is, int quantity, int statusID, String categoryID) throws SQLException
    {
        stmt = conn.prepareStatement(sqlUpdateStr);

        stmt.setString(1, productName);
        stmt.setString(2, description);
        stmt.setInt(3, oriPrice);
        stmt.setBlob(4, is);
        stmt.setInt(5, quantity);
        stmt.setInt(6, statusID);
        stmt.setString(7, categoryID);
        stmt.setString(8, productID);

        result = stmt.executeUpdate();
        shutDown();
        stmt.close();
        return result;
    }
    
    public int DORProduct(String productID, int status) throws SQLException
    {
        stmt = conn.prepareStatement(sqlDORStr);
        stmt.setInt(1, status);
        stmt.setString(2, productID);
        
        result = stmt.executeUpdate();
        stmt.close();
        shutDown();
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
