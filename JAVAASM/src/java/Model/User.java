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
import java.util.Date;

/**
 *
 * @author User
 */
public class User 
{
    private String loginID;
    private String email;
    private String password;
    private int status;
    private Date lastLoginTime;
    private String roleID;

    public User(String loginID, String email, String password, int status, Date lastLoginTime, String roleID) 
    {
        this.loginID = loginID;
        this.email = email;
        this.password = password;
        this.status = status;
        this.lastLoginTime = lastLoginTime;
        this.roleID = roleID;
    }

    public String getLoginID() 
    {
        return loginID;
    }

    public void setLoginID(String loginID) 
    {
        this.loginID = loginID;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }

    public int getStatus() 
    {
        return status;
    }

    public void setStatus(int status) 
    {
        this.status = status;
    }

    public Date getLastLoginTime() 
    {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) 
    {
        this.lastLoginTime = lastLoginTime;
    }

    public String getRoleID() 
    {
        return roleID;
    }

    public void setRoleID(String roleID) 
    {
        this.roleID = roleID;
    }

}
