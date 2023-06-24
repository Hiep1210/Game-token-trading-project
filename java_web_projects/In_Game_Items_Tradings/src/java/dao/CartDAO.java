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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cart;

/**
 *
 * @author Inspiron
 */
public class CartDAO {
    static Logger logger
            = Logger.getLogger(CartDAO.class.getName());
    private static final String SELECTITEMS = "SELECT c.id,c.buyer_id,m.id, m.game_account_name, m.user_id, "
            + "m.price, m.begin_date, m.end_date,g.* FROM cart c, marketitems m, gameitems g "
            + "where c.market_items_id = m.id and m.item_id = g.id and c.buyer_id = ?";
    
    private static final String SELECTONEITEM = "SELECT c.id,c.buyer_id,m.id, m.game_account_name, m.user_id, "
            + "m.price, m.begin_date, m.end_date,g.* FROM cart c, marketitems m, gameitems g "
            + "where c.market_items_id = m.id and m.item_id = g.id and c.id = ?";
    
    public static ArrayList<Cart> getAllCartItems(int buyerid) {
        ArrayList<Cart> list = new ArrayList<>();
        Cart items = null;
        Connection con = null;
        PreparedStatement statement = null;
        try {
            DBContext db = new DBContext();
            con = db.getConnection();
            //if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql = SELECTITEMS;
                statement = con.prepareStatement(sql);
                statement.setInt(1, buyerid);
                ResultSet rs = statement.executeQuery();
                //run a loop to save queries into model
                while (rs.next()) {
                    items = new Cart(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getDouble(6),
                            rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10), rs.getString(11), rs.getString(12),
                            rs.getString(13), rs.getString(14), rs.getString(15));
                    list.add(items);
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        } 
        try{
            statement.close();
            con.close();
        }catch(SQLException s){
            logger.log(Level.SEVERE, s.getMessage());
        }
        return list;
    }
    public static boolean checkDuplicateCart(int marketid, int buyerid) {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM Cart WHERE market_items_id = ? and buyer_id = ?";
                PreparedStatement st = con.prepareStatement(sql);
                st.setInt(1, marketid);
                st.setInt(2, buyerid);
                ResultSet rs = st.executeQuery();
                // Check if any rows exist in the result set
                if (rs.next()) {
                    throw new NullPointerException();
                }
                st.close();
                con.close();
                return false;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return true;
    }
    public static boolean insertCartItem(int buyerid, int marketid ){
        Connection con = null;
        PreparedStatement statement = null;
        try {
            DBContext db = new DBContext();
             con = db.getConnection();
            String sql = "INSERT INTO cart (buyer_id, market_items_id) VALUES (?, ?); ";
             statement = con.prepareStatement(sql);
            statement.setInt(1, buyerid);
            statement.setInt(2, marketid);
           if(statement.executeUpdate() < 1){
               throw new NullPointerException();
           }
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }finally{
        try{
            statement.close();
            con.close();
        }catch(SQLException s){
            logger.log(Level.SEVERE, s.getMessage());
        }
        }
        return false;
        
    }
    
    public static boolean deleteCartWithMarketItemId(int marketetItemId) {
        boolean deleteStatus = true;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            String sql = "delete from cart where market_items_id = " + marketetItemId;
            Statement statement = con.createStatement();
            if (statement.executeUpdate(sql) < 1) {
                deleteStatus = false;
                throw new Exception();
            }
            con.close();
            statement.close();
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return deleteStatus;
    }
    
    public static boolean deleteCartItem(int id) {
        boolean deleteStatus = true;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            String sql = "delete from cart where id = " + id;
            Statement statement = con.createStatement();
            if (statement.executeUpdate(sql) < 1) {
                deleteStatus = false;
                throw new Exception();
            }
            con.close();
            statement.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return deleteStatus;
    }

}
