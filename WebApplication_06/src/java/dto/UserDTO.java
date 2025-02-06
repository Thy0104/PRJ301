/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author baothy2004
 */
public class UserDTO {
    private String useID;
    private String fullname;
    private String roleID;
    private String password;

    public UserDTO(String useID, String fullname, String roleID, String password) {
        this.useID = useID;
        this.fullname = fullname;
        this.roleID = roleID;
        this.password = password;
    }

    public String getUseID() {
        return useID;
    }

    public void setUseID(String useID) {
        this.useID = useID;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "useID=" + useID + ", fullname=" + fullname + ", roleID=" + roleID + ", password=" + password + '}';
    }
    
}




