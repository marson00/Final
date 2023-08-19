/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.User;
import Model.UserDaoInterface;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class UserDA implements UserDaoInterface {

    private String host = "jdbc:derby://localhost:1527/javaassignmentdb";
    private String user = "javadb";
    private String password = "javadb";
    private String tableName = "LOGIN_REGISTER_TABLE";
    private Connection conn;
    private PreparedStatement stmt;

    public UserDA() {
        createConnection();
    }

    private static class SingletonHelper {

        private static final UserDA INSTANCE = new UserDA();
    }

    public static UserDA getInstance() {
        return SingletonHelper.INSTANCE;
    }

    private void createConnection() {
        try {
            conn = DriverManager.getConnection(host, user, password);
        } catch (SQLException ex) {
            System.out.print(ex);
        }
    }

    private void shutDown() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public Optional<User> find(String id) throws SQLException {
        String optionalsql = "SELECT * FROM " + tableName + " WHERE  loginID = ? "; //need check
        User user = null;
        stmt = conn.prepareStatement(optionalsql);
        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            try {
                String loginID = rs.getString("loginID");
                String email = rs.getString("email");
                String password = rs.getString("password");
                int status = rs.getInt("status");
                String lastLoginTimeString = rs.getString("lastLoginTime");
                Date lastLoginTime = lastLoginTimeString == null ? null : new SimpleDateFormat("yyyy-MM-dd").parse(lastLoginTimeString);
                String roleID = rs.getString("roleID");

                user = new User(loginID, email, password, status, lastLoginTime, roleID);

            } catch (ParseException error) {

            }
        }

        return Optional.ofNullable(user);
    }

    public Optional<User> findByEmail(String email) throws SQLException {
        String optionalsql = "SELECT * FROM " + tableName + " WHERE  email = ? "; //need check
        User user = null;
        stmt = conn.prepareStatement(optionalsql);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            try {
                String loginID = rs.getString("loginID");
                String usEmail = rs.getString("email");
                String password = rs.getString("password");
                int status = rs.getInt("status");
                String dateString = rs.getString("lastLoginTime");
                Date lastLoginTime = dateString == null ? null : new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("lastLoginTime"));
                String roleID = rs.getString("roleID");

                user = new User(loginID, usEmail, password, status, lastLoginTime, roleID);
                System.out.print(user);
            } catch (ParseException error) {

            }
        }

        return Optional.ofNullable(user);
    }

    public List<User> findAll() throws SQLException {
        List<User> listUser = new ArrayList<>();
        String getallsql = "SELECT * FROM " + tableName;
        stmt = conn.prepareStatement(getallsql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            User user;
            try {
                String loginID = rs.getString("loginID");
                String email = rs.getString("email");
                String password = rs.getString("password");
                int status = rs.getInt("status");

                String dateString = rs.getString("lastLoginTime");
                Date lastLoginTime = dateString == null ? null : new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("lastLoginTime"));

                String roleID = rs.getString("roleID");

                user = new User(loginID, email, password, status, lastLoginTime, roleID);
                listUser.add(user);
            } catch (ParseException error) {
                return null;
            }
        }

        return listUser;
    }

    public boolean save(User user) throws SQLException 
    {
        String insertsql = "INSERT INTO " + tableName + "(loginID, email, password, status, lastLoginTime, roleID) VALUES (?, ?, ?, ?, ?, ?)"; //need check
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        stmt = conn.prepareStatement(insertsql);
        stmt.setString(1, user.getLoginID());
        stmt.setString(2, user.getEmail());
        stmt.setString(3, user.getPassword());
        stmt.setInt(4, user.getStatus());
        stmt.setString(5, null);
        stmt.setString(6, user.getRoleID());

        return stmt.executeUpdate() > 0;
    }

    public boolean update(User user) throws SQLException {
        String updatesql = "UPDATE " + tableName + " SET loginID = ?, email = ? , password = ?, status = ?, lastLoginTime = ?, roleID = ? WHERE loginID = ? "; //need check
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        stmt = conn.prepareStatement(updatesql);
        stmt.setString(1, user.getLoginID());
        stmt.setString(2, user.getEmail());
        stmt.setString(3, user.getPassword());
        stmt.setInt(4, user.getStatus());
        stmt.setString(5, user.getLastLoginTime() == null ? null : dateFormat.format(user.getLastLoginTime()));

        System.out.print(user.getRoleID());

        stmt.setString(6, user.getRoleID());
        stmt.setString(7, user.getLoginID());

        return stmt.executeUpdate() > 0;
    }

}
