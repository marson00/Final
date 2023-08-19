/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

public class Role{
        
    private String roleID;
    private String roleTitle;

    public Role() {
    
    }

    public Role(String roleID, String roleTitle) {
        this.roleID = roleID;
        this.roleTitle = roleTitle;
    }

    public String getRoleID() {
        return roleID;
    }

    public String getRoleTitle() {
        return roleTitle;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }
}
