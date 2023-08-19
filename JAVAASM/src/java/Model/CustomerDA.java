/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.CustomerDetail;
import Model.CustomerDaoInterface;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.*;

public class CustomerDA implements CustomerDaoInterface 
{

    private String host = "jdbc:derby://localhost:1527/javaassignmentdb";
    private String user = "javadb";
    private String password = "javadb";
    private String tableName = "ACCOUNT_DETAILS_TABLE";
    private Connection conn;
    private PreparedStatement stmt;

    public CustomerDA() 
    {
        createConnection();
    }

    private static class SingletonHelper 
    {
        private static final CustomerDA INSTANCE = new CustomerDA();
    }

    public static CustomerDA getInstance()
    {
        return SingletonHelper.INSTANCE;
    }

    private void createConnection() 
    {
        try 
        {
            conn = DriverManager.getConnection(host, user, password);
        } 
        catch (SQLException ex)
        {
            System.out.print(ex);
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
    
    public String findUsername(String id) throws SQLException 
    {
        String optionalsql = "SELECT USERNAME FROM " + tableName + " WHERE  loginID = ? "; //need check
        String username = null;
        stmt = conn.prepareStatement(optionalsql);
        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) 
        {
            username = rs.getString("username");
        }
        //System.out.print(custDetail.getAge());
        return username;
    }

    public Optional<CustomerDetail> find(String id) throws SQLException 
    {
        String optionalsql = "SELECT * FROM " + tableName + " WHERE  loginID = ? "; //need check
        CustomerDetail custDetail = null;
        stmt = conn.prepareStatement(optionalsql);
        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) 
        {
            String accountID = rs.getString("accountID");
            String username = rs.getString("username");
            String gender = rs.getString("gender");
            int age = rs.getInt("age");
            String phone = rs.getString("phone");
            String address = rs.getString("address");
            int points = rs.getInt("points");
            String loginID = rs.getString("loginID");
            System.out.print(age);
            
            custDetail = new CustomerDetail(accountID, username, gender, age, phone, address, points, loginID);
        }
        //System.out.print(custDetail.getAge());
        return Optional.ofNullable(custDetail);
    }

    public List<CustomerDetail> findAll() throws SQLException 
    {
        List<CustomerDetail> listCustomerDetail = new ArrayList<>();
        String getallsql = "SELECT * FROM " + tableName;
        stmt = conn.prepareStatement(getallsql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) 
        {
            String accountID = rs.getString("accountID");
            String username = rs.getString("username");
            String gender = rs.getString("gender");
            int age = rs.getInt("age");
            String phone = rs.getString("phone");
            String address = rs.getString("address");
            int points = rs.getInt("points");
            String loginID = rs.getString("loginID");

            CustomerDetail customerDetail = new CustomerDetail(accountID, username, gender, age, phone, address, points, loginID);
            listCustomerDetail.add(customerDetail);
        }

        return listCustomerDetail;
    }

    @Override
    public boolean save(CustomerDetail customerDetail) throws SQLException 
    {
        String insertsql = "INSERT INTO " + tableName + "(accountID, username, gender, age, phone, address, points, loginID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"; //need check
        stmt = conn.prepareStatement(insertsql);
        stmt.setString(1, customerDetail.getAccountID());
        stmt.setString(2, customerDetail.getUsername());
        stmt.setString(3, String.valueOf(customerDetail.getGender()));
        stmt.setInt(4, customerDetail.getAge());
        stmt.setString(5, customerDetail.getPhone());
        stmt.setString(6, customerDetail.getAddress());
        stmt.setInt(7, customerDetail.getPoints());
        stmt.setString(8, customerDetail.getLoginID());
        return stmt.executeUpdate() > 0;
    }
    
    public boolean register(CustomerDetail customerDetail) throws SQLException 
    {
        String insertsql = "INSERT INTO " + tableName + "(accountID, username, loginID) VALUES (?, ?, ?)"; //need check
        stmt = conn.prepareStatement(insertsql);
        stmt.setString(1, customerDetail.getAccountID());
        stmt.setString(2, customerDetail.getUsername());
        stmt.setString(3, customerDetail.getLoginID());
        return stmt.executeUpdate() > 0;
    }

    public boolean update(CustomerDetail customerDetail) throws SQLException 
    {
        String updatesql = "UPDATE " + tableName + " SET accountID = ?, username = ? , gender = ?, age = ?, phone = ?, address = ?, points = ? WHERE loginID = ? "; //need check
        System.out.print(updatesql);
        stmt = conn.prepareStatement(updatesql);
        stmt.setString(1, customerDetail.getAccountID());
        stmt.setString(2, customerDetail.getUsername());
        stmt.setString(3, String.valueOf(customerDetail.getGender()));
        stmt.setInt(4, customerDetail.getAge());
        stmt.setString(5, customerDetail.getPhone());
        stmt.setString(6, customerDetail.getAddress());
        stmt.setInt(7, customerDetail.getPoints());
        stmt.setString(8, customerDetail.getLoginID());
        return stmt.executeUpdate() > 0;
    }
    
}
