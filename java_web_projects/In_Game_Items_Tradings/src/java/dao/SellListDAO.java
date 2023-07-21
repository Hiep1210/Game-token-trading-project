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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import model.SellItems;

/**
 *
 * @author VICTUS
 */
public class SellListDAO {

    public static void insertSellItemsItem(SellItems item) {
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
            String sql = "SELECT id FROM SellItems WHERE exterior = ? AND sell_time = ? AND price = ? AND game_account_name = ? AND seller_id = ? ORDER BY id desc";
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
            String sql = "SELECT si.id, si.exterior, si.sell_time, si.price, si.game_account_name, si.seller_id, gi.id, gi.skin_name, gi.item_name, gi.type, gi.rarity, gi.img \n"
                    + "FROM SellItems si \n"
                    + "INNER JOIN GameItems gi ON si.item_id = gi.id \n"
                    + "WHERE si.seller_id = " + sellerId + " ORDER BY si.id desc";
            statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                SellItems sellItem = new SellItems(rs.getInt(1), rs.getString(2), 
                        rs.getInt(3), rs.getDouble(4), rs.getString(5), 
                        rs.getInt(6), rs.getInt(7), rs.getString(8), 
                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12));
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

    public static SellItems getSellItemInfo(int sellItemId) {
        SellItems sellItem = null;
        Connection con = null;
        PreparedStatement statement = null;
        try {
            DBContext db = new DBContext();
            con = db.getConnection();
            String sql = "SELECT si.id, si.seller_id, si.exterior, si.sell_time, si.price, si.game_account_name, gi.id, gi.skin_name, gi.item_name, gi.type, gi.rarity, gi.img "
                    + "FROM SellItems si "
                    + "INNER JOIN GameItems gi ON si.item_id = gi.id "
                    + "WHERE si.id = ?";
            statement = con.prepareStatement(sql);
            statement.setInt(1, sellItemId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                sellItem = new SellItems();
                sellItem.setId(rs.getInt(1));
                sellItem.setSellerId(rs.getInt(2));
                sellItem.setExterior(rs.getString(3));
                sellItem.setSellTime(rs.getInt(4));
                sellItem.setPrice(rs.getDouble(5));
                sellItem.setGameAccount(rs.getString(6));
                sellItem.setGameItemId(rs.getInt(7));
                sellItem.setSkinName(rs.getString(8));
                sellItem.setItemName(rs.getString(8));
                sellItem.setType(rs.getString(10));
                sellItem.setRarity(rs.getString(11));
                sellItem.setImg(rs.getString(12));
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

    public static void sellToMarket(SellItems sellItem, Timestamp beginDate, Timestamp endDate) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            DBContext db = new DBContext();
            con = db.getConnection();
            String sql = "INSERT INTO MarketItems (game_account_name, user_id, item_id, price, begin_date, end_date) VALUES (?, ?, ?, ?, ?, ?)";
            statement = con.prepareStatement(sql);
            statement.setString(1, sellItem.getGameAccount());
            statement.setInt(2, sellItem.getSellerId());
            statement.setInt(3, sellItem.getGameItemId());
            statement.setDouble(4, sellItem.getPrice());
            statement.setTimestamp(5, beginDate);
            statement.setTimestamp(6, endDate);

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

}
