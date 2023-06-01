/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Context.DBContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.Role;

/**
 *
 * @author Asus
 */
public class RoleDAO {

    public static ArrayList<Role> getRoleList() {
        ArrayList<Role> list = new ArrayList<>();
        Role role;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            //if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql = "SELECT * FROM Role ";
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                //run a loop to save queries into model   
                while (rs.next()) {
                    role = new Role();
                    role.setRole(rs.getInt("id"));
                    role.setRole_name(rs.getString("role_name"));
                    list.add(role);
                }
                call.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

   
}
