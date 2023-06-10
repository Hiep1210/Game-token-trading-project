/*
*Programmer: Nguyễn Hoàng Hiệp 
*Description: This file describes the actions of the model GameItems
 */
package dao;

import Context.DBContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.GameItems;

/**
 *
 * @author Inspiron
 */
public class GameItemsDAO {
    public static ArrayList<GameItems> getAllGameItems(){
         ArrayList<GameItems> list = new ArrayList<>();
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "Select * from GameItems";
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                //assign value for object items then return it
                while (rs.next()) {
                    list.add(new GameItems(rs.getInt(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
                }
                call.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    public static GameItems getItemInfo(int item_id) {
        GameItems items = null;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "Select * from GameItems where id = " + item_id;
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                //assign value for object items then return it
                while (rs.next()) {
                    items = new GameItems(item_id, rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                }
                call.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return items;
    }

    public static void main(String[] args) {
        getAllGameItems();
    }
}
