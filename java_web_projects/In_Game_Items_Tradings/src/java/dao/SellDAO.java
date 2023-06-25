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
import model.GameItems;
import model.SellList;

/**
 *
 * @author VICTUS
 */
public class SellDAO {

    static Logger logger
            = Logger.getLogger(SellDAO.class.getName());
    private static final String SELECTITEMS = "SELECT sl.id,sl.seller_id, s.id, s.game_account_name, s.user_id, "
            + "s.price, s.begin_date, s.end_date,g.* FROM SellList sl, SellItems s, gameitems g "
            + "where sl.sell_items_id = s.id and s.item_id = g.id and sl.seller_id = ?";

    private static final String SELECTONEITEM = "SELECT sl.id,sl.seller_id, s.id, s.game_account_name, s.user_id, "
            + "s.price, s.begin_date, s.end_date,g.* FROM SellList sl, SellItems s, gameitems g "
            + "where sl.sell_items_id = s.id and s.item_id = g.id and sl.id = ?";

    public static boolean checkDuplicateSell(int sellid, int sellerid) {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM sellList WHERE sell_items_id = ? and seller_id = ?";
                PreparedStatement st = con.prepareStatement(sql);
                st.setInt(1, sellid);
                st.setInt(2, sellerid);
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

    public static ArrayList<SellList> getAllSellListItems(int sellerid) {
        ArrayList<SellList> list = new ArrayList<>();
        SellList items = null;
        Connection con = null;
        PreparedStatement statement = null;
        try {
            DBContext db = new DBContext();
            con = db.getConnection();
            //if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql = SELECTITEMS;
                statement = con.prepareStatement(sql);
                statement.setInt(1, sellerid);
                ResultSet rs = statement.executeQuery();
                //run a loop to save queries into model
                while (rs.next()) {
                    items = new SellList(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getDouble(6),
                            rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10), rs.getString(11), rs.getString(12),
                            rs.getString(13), rs.getString(14), rs.getString(15));
                    list.add(items);
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        try {
            statement.close();
            con.close();
        } catch (SQLException s) {
            logger.log(Level.SEVERE, s.getMessage());
        }
        return list;
    }

    public static ArrayList<GameItems> getAllSellItems() {
        ArrayList<GameItems> list = new ArrayList<>();
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "SELECT DISTINCT skin_name, item_name, type, rarity, img "
                        + "FROM GameItems "
                        + "WHERE (skin_name) IN (SELECT DISTINCT skin_name FROM GameItems)";
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                //assign value for object items then return it
                while (rs.next()) {
                    list.add(new GameItems(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
                }
                call.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static ArrayList<GameItems> getTopTwelveItems() {
        ArrayList<GameItems> list = new ArrayList<>();
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "SELECT DISTINCT skin_name, item_name, type, rarity, img "
                        + "FROM GameItems "
                        + "WHERE (skin_name) IN (SELECT DISTINCT skin_name FROM GameItems) "
                        + "ORDER BY rarity, skin_name, item_name "
                        + "LIMIT 12";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                //assign value for object items then return it
                while (rs.next()) {
                    list.add(new GameItems(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
                }
                st.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static ArrayList<GameItems> getNextTwelveItems(int amount) {
        ArrayList<GameItems> list = new ArrayList<>();
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "SELECT DISTINCT skin_name, item_name, type, rarity, img "
                        + "FROM GameItems "
                        + "WHERE (skin_name) IN (SELECT DISTINCT skin_name FROM GameItems) "
                        + "ORDER BY rarity, skin_name,item_name "
                        + "LIMIT 12 OFFSET ?";
                PreparedStatement st = con.prepareStatement(sql);
                st.setInt(1, amount);
                ResultSet rs = st.executeQuery();
                //assign value for object items then return it
                while (rs.next()) {
                    list.add(new GameItems(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
                }
                st.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static ArrayList<GameItems> filterByType(String[] types, String searchTerm, String sortOrder) {
        ArrayList<GameItems> list = new ArrayList<>();
        if (types == null) {
            types = new String[0];
        }
        int numberOfTypes = types.length;
        String selectedTypes = "";
        if (sortOrder == null) {
            sortOrder = "";
        }
        for (int i = 0; i < numberOfTypes; i++) {
            //Open Parentheses at start
            if (i == 0) {
                selectedTypes += "AND (";
            }
            selectedTypes += "type = ? ";
            //Add OR except last element
            if (i < numberOfTypes - 1) {
                selectedTypes += "OR ";
            }
            //Close Parentheses at end
            if (i == numberOfTypes - 1) {
                selectedTypes += ")";
            }
        }
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "SELECT DISTINCT skin_name, item_name, type, rarity, img "
                        + "FROM GameItems "
                        + "WHERE (skin_name) IN (SELECT DISTINCT skin_name FROM GameItems) "
                        + selectedTypes
                        + "AND (CONCAT(type, ' ', item_name, ' ', skin_name) LIKE ? "
                        + "OR CONCAT(type, ' ', skin_name) LIKE ? "
                        + "OR CONCAT(item_name, ' ', skin_name) LIKE ? ) "
                        + "ORDER BY CASE rarity "
                        + "    WHEN 'covert' THEN 1 "
                        + "    WHEN 'classified' THEN 2 "
                        + "    WHEN 'restricted' THEN 3 "
                        + "    WHEN 'mil-spec' THEN 4 "
                        + "    WHEN 'industrial' THEN 5 "
                        + "    ELSE 6 "
                        + "END " + sortOrder + ", skin_name, item_name " + sortOrder + " ;";
                PreparedStatement st = con.prepareStatement(sql);
                //Set value for selected types
                for (int i = 1; i <= numberOfTypes; i++) {
                    st.setString(i, types[i - 1]);
                }
                // Set the search term parameter values
                st.setString(numberOfTypes + 1, "%" + searchTerm + "%");
                st.setString(numberOfTypes + 2, "%" + searchTerm + "%");
                st.setString(numberOfTypes + 3, "%" + searchTerm + "%");
                ResultSet rs = st.executeQuery();
                //assign value for object items then return it
                while (rs.next()) {
                    list.add(new GameItems(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
                }
                st.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static boolean insertSellItem(int sellerid, int sellid) {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            String sql = "INSERT INTO SellList (seller_id, sell_items_id) VALUES (?, ?); ";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, sellerid);
            statement.setInt(2, sellid);
            if (statement.executeUpdate() < 1) {
                throw new Exception();
            }
            con.close();
            statement.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean deleteSellItem(int id) {
        boolean deleteStatus = true;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            String sql = "delete from SellList where id = " + id;
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
