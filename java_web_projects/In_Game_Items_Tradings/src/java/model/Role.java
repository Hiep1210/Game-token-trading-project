/*
*Programmer: Trần Thế Hùng 
*Description: This file describes the model of role 
 */
package model;

/**
 *
 * @author Asus
 */
public class Role {
    int role;
    String role_name;

    public Role() {
    }

    public Role(int role, String role_name) {
        this.role = role;
        this.role_name = role_name;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    @Override
    public String toString() {
        return "Role{" + "role=" + role + ", role_name=" + role_name + '}';
    }
    
    
    
}
