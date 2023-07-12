/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Context.DBContext;
import static dao.CartDAO.logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import model.GameItems;
import model.SellItems;

/**
 *
 * @author VICTUS
 */
public class SellListDAO {

    public static void insertSellListItem(SellItems item) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            DBContext db = new DBContext();
            con = db.getConnection();
            String sql = "INSERT INTO SellItems (exterior, sell_time, price, game_account_name, seller_id, item_id) VALUES (?, ?, ?, ?, ?, ?) ";
            statement = con.prepareStatement(sql);
            statement.setString(1, item.getExterior());
            statement.setInt(2, item.getSellTime());
            statement.setDouble(3, item.getPrice());
            statement.setString(4, item.getGameAccount());
            statement.setInt(5, item.getSellerId());
            statement.setInt(6, item.getGameItemId());
            if (statement.executeUpdate() < 1) {
                throw new NullPointerException();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        } finally {
            try {
                statement.close();
                con.close();
            } catch (SQLException s) {
                logger.log(Level.SEVERE, s.getMessage());
            }
        }
    }

    public static int getSellItemId(SellItems sellItem) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        int sellItemId = 0;

        try {
            DBContext db = new DBContext();
            con = db.getConnection();
            String sql = "SELECT id FROM SellItems WHERE exterior = ? AND sell_time = ? AND price = ? AND game_account_name = ? AND seller_id = ?";
            st = con.prepareStatement(sql);
            st.setString(1, sellItem.getExterior());
            st.setInt(2, sellItem.getSellTime());
            st.setDouble(3, sellItem.getPrice());
            st.setString(4, sellItem.getGameAccount());
            st.setInt(5, sellItem.getSellerId());

            rs = st.executeQuery();
            if (rs.next()) {
                sellItemId = rs.getInt("id");
            }
        } finally {
            // Close the result set, statement, and connection
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return sellItemId;
    }

    public static void addToSellList(int sellerId, int itemId) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            DBContext db = new DBContext();
            con = db.getConnection();
            String sql = "INSERT INTO SellList (seller_id, sell_item_id) VALUES (?, ?) ";
            statement = con.prepareStatement(sql);
            statement.setInt(1, sellerId);
            statement.setInt(2, itemId);
            if (statement.executeUpdate() < 1) {
                throw new NullPointerException();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        } finally {
            try {
                statement.close();
                con.close();
            } catch (SQLException s) {
                logger.log(Level.SEVERE, s.getMessage());
            }
        }
    }

    public static ArrayList<SellItems> getUserSellList(int sellerId) {
        ArrayList<SellItems> sellListItemsList = new ArrayList<>();
        Connection con = null;
        PreparedStatement statement = null;
        try {
            DBContext db = new DBContext();
            con = db.getConnection();
            String sql = "SELECT * FROM SellList where seller_id = " + sellerId;
            statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                sellListItemsList.add(new SellItems(rs.getInt(1), sellerId, rs.getInt(3)));
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        } finally {
            try {
                statement.close();
                con.close();
            } catch (SQLException s) {
                logger.log(Level.SEVERE, s.getMessage());
            }
        }
        return sellListItemsList;
    }

    public static void main(String[] args) {

        SellItems sellItem = new SellItems("Factory New", 1, 300, "lamp", 2, 32);
        insertSellListItem(sellItem);

//        ArrayList<SellItems> list = getUserSellList(2);
//        System.out.println(list.size());
//        for (SellItems sellItems : list) {
//            System.out.println(sellItems.getId());
//            System.out.println(sellItems.getSellerId());
//        }
    }
}
