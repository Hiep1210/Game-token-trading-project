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

    public static int getSellItemId(SellItems sellItem) {
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
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (SQLException s) {
                logger.log(Level.SEVERE, s.getMessage());
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
            String sql = "SELECT sl.id, si.exterior, si.sell_time, si.price, si.game_account_name, sl.seller_id, gi.skin_name, gi.item_name, gi.type, gi.rarity, gi.img "
                    + "FROM SellItems si "
                    + "INNER JOIN SellList sl ON si.id = sl.sell_item_id "
                    + "INNER JOIN GameItems gi ON si.item_id = gi.id "
                    + "WHERE sl.seller_id = " + sellerId + " ORDER BY id desc";
            statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                SellItems sellItem = new SellItems(rs.getInt("id"), rs.getString("exterior"), rs.getInt("sell_time"), rs.getDouble("price"), rs.getString("game_account_name"), rs.getInt("seller_id"));
                sellItem.setSkinName(rs.getString("skin_name"));
                sellItem.setItemName(rs.getString("item_name"));
                sellItem.setType(rs.getString("type"));
                sellItem.setRarity(rs.getString("rarity"));
                sellItem.setImg(rs.getString("img"));
                sellListItemsList.add(sellItem);
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

    public static SellItems getSellListItemInfo(int sellItemId) {
        SellItems sellItem = null;
        Connection con = null;
        PreparedStatement statement = null;
        try {
            DBContext db = new DBContext();
            con = db.getConnection();
            String sql = "SELECT si.id, si.exterior, si.sell_time, si.price, si.game_account_name, gi.id, gi.skin_name, gi.item_name, gi.type, gi.rarity, gi.img "
                    + "FROM SellItems si "
                    + "INNER JOIN GameItems gi ON si.item_id = gi.id "
                    + "WHERE si.id = ?";
            statement = con.prepareStatement(sql);
            statement.setInt(1, sellItemId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                sellItem = new SellItems();
                sellItem.setId(rs.getInt("id"));
                sellItem.setExterior(rs.getString("exterior"));
                sellItem.setSellTime(rs.getInt("sell_time"));
                sellItem.setPrice(rs.getDouble("price"));
                sellItem.setGameAccount(rs.getString("game_account_name"));
                sellItem.setSkinName(rs.getString("skin_name"));
                sellItem.setItemName(rs.getString("item_name"));
                sellItem.setType(rs.getString("type"));
                sellItem.setRarity(rs.getString("rarity"));
                sellItem.setImg(rs.getString("img"));
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
        return sellItem;
    }
    
    public static void deleteSellListItem(int sellerId, int sellItemId) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            DBContext db = new DBContext();
            con = db.getConnection();
            String sql = "DELETE FROM SellList WHERE (seller_id = ? AND sell_item_id = ?);";
            statement = con.prepareStatement(sql);
            statement.setInt(1, sellerId);
            statement.setInt(2, sellItemId);
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
    
    public static void deleteSellItemsItem(int sellerId, int itemId) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            DBContext db = new DBContext();
            con = db.getConnection();
            String sql = "DELETE FROM SellItems WHERE (seller_id = ? AND id = ?);";
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

    public static void main(String[] args) {

//        deleteSellListItem(2, 3);
        deleteSellItemsItem(2, 3);
//        ArrayList<SellItems> sellListItemsList = getUserSellList(2);
//        for (SellItems sellItems : sellListItemsList) {
//            System.out.println(sellItems.getId());
//            System.out.println(sellItems.getExterior());
//            System.out.println(sellItems.getSellTime());
//            System.out.println(sellItems.getPrice());
//            System.out.println(sellItems.getGameAccount());
//            System.out.println(sellItems.getSellerId());
//            System.out.println(sellItems.getImg());
    }
}
