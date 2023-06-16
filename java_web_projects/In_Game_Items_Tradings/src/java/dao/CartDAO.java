/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Cart;

/**
 *
 * @author Inspiron
 */
public class CartDAO {
    private static final String SELECTITEMS = "SELECT c.id,c.buyer_id,m.id, m.game_account_name, m.user_id, "
            + "m.price, m.begin_date, m.end_date,g.* FROM cart c, marketitems m, gameitems g "
            + "where c.market_items_id = m.id and m.item_id = g.id";
    public static ArrayList<Cart> getAllCartItems() throws SQLException {
        ArrayList<Cart> list = new ArrayList<>();
        Cart items = null;
        Connection con = null;
        Statement statement = null;
        try {
            DBContext db = new DBContext();
            con = db.getConnection();
            //if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql = SELECTITEMS;
                statement = con.createStatement();
                ResultSet rs = statement.executeQuery(sql);
                //run a loop to save queries into model
                while (rs.next()) {
                    items = new Cart(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getDouble(6),
                            rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10), rs.getString(11), rs.getString(12), 
                            rs.getString(13), rs.getString(14), rs.getString(15));
                    list.add(items);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
            statement.close();
            con.close();
        return list;
    }
    public static boolean insertCartItem(int buyerid, int marketid ){
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            String sql = "INSERT INTO cart (buyer_id, market_items_id) VALUES (?, ?); ";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, buyerid);
            statement.setInt(2, marketid);
           if(statement.executeUpdate() < 1) throw new Exception();
            con.close();
            statement.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static boolean deleteCartItem(int id){
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            String sql = "delete from cart where id = "+id;
            Statement statement = con.createStatement();
            if(statement.executeUpdate(sql) < 1) throw new Exception();
            con.close();
            statement.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static void main(String[] args) throws SQLException {
        getAllCartItems();
        insertCartItem(2, 3);
        deleteCartItem(21);
    }
}
