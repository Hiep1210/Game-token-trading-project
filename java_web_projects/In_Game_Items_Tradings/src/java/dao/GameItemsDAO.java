/*
*Programmer: Nguyễn Hoàng Hiệp 
*Description: This file describes the actions of the model GameItems
 */
package dao;

import Context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.GameItems;

/**
 *
 * @author Inspiron
 */
public class GameItemsDAO {

    private GameItems gameItems;
    private String trimedSkinName;

    public GameItemsDAO() {
    }

    public GameItemsDAO(GameItems gameItems, String trimedSkinName) {
        this.gameItems = gameItems;
        this.trimedSkinName = trimedSkinName;
    }

    public GameItems getGameItems() {
        return gameItems;
    }

    public void setGameItems(GameItems gameItems) {
        this.gameItems = gameItems;
    }

    public String getTrimedSkinName() {
        return trimedSkinName;
    }

    public void setTrimedSkinName(String trimedSkinName) {
        this.trimedSkinName = trimedSkinName;
    }

    public static ArrayList<GameItems> getAllGameItems() {
        ArrayList<GameItems> list = new ArrayList<>();
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "SELECT * from GameItems";
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                //assign value for object items then return it
                while (rs.next()) {
                    list.add(new GameItems(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
                }
                call.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static GameItems getGameItemById(int id) {
        GameItems item = null;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "Select * from GameItems where id = ?";
                PreparedStatement call = con.prepareStatement(sql);
                call.setInt(1, id);
                ResultSet rs = call.executeQuery();
                //assign value for object items then return it
                while (rs.next()) {
                    item = new GameItems(rs.getInt(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                }
                call.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return item;
    }

    public static ArrayList<GameItems> Filter(String type, String rarity, String exterior) {
        ArrayList<GameItems> list = new ArrayList<>();
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM game_items_trading.gameitems where true ";
                if (type != null) {
                    sql += " and type = '" + type + "'";
                }
                if (rarity != null) {
                    sql += " and rarity = '" + rarity + "'";
                }
                if (exterior != null) {
                    sql += " and exterior = '" + exterior + "'";
                }
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

    public static ArrayList<GameItems> Search(String name) {
        ArrayList<GameItems> list = new ArrayList<>();
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM game_items_trading.gameitems where true ";
                    sql += " and (item_name Like '%" + name + "%' or skin_name like '%" + name + "%') ";
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
    public static ArrayList<GameItems> filterTrade(String[] types, String[] exterior, String searchTerm, String sortOrder) {
        ArrayList<GameItems> list = new ArrayList<>();
        String selectedTypes = "";
        String ex = "";
        if (exterior == null) {
            exterior = new String[0];
            ex = "AND (exterior = 'nonexisttype')";
        }
        if (types == null) {
            types = new String[0];
            selectedTypes = "AND (type = 'nonexisttype')";
        }
        int numberOfTypes = types.length;
        int exNum = exterior.length;
        if (sortOrder == null) {
            sortOrder = "";
        }
        //iterate through all element in selectedType
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
        for (int i = 0; i < exNum; i++) {
            //Open Parentheses at start
            if (i == 0) {
                ex += "AND (";
            }
            ex += "type = ? ";
            //Add OR except last element
            if (i < exNum - 1) {
                ex += "OR ";
            }
            //Close Parentheses at end
            if (i == exNum - 1) {
                ex += ")";
            }
        }
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "SELECT DISTINCT skin_name, item_name, type, rarity,exter img "
                        + "FROM GameItems "
                        + "WHERE (skin_name) IN (SELECT DISTINCT skin_name FROM GameItems) "
                        + selectedTypes
                        + ex
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
    public static GameItems getItemBySkinName(String skinName, String exterior) {
        GameItems item = new GameItems();
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "SELECT * "
                        + "FROM GameItems "
                        + "WHERE img = '" + skinName + "'"
                        + "AND exterior = '" + exterior + "'";
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                //assign value for object items then return it
                while (rs.next()) {
                    item.setId(rs.getInt(1));
                    item.setSkinName(rs.getString(2));
                    item.setItemName(rs.getString(3));
                    item.setType(rs.getString(4));
                    item.setRarity(rs.getString(5));
                    item.setImg(rs.getString(6));
                }
                call.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return item;
    }

     public static String insertGameItem(GameItems gameItem) {
        ResultSet generatedKey;
        int key = 0;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            //if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql1 = "INSERT INTO GameItems (skin_name,"
                        + "item_name, type ,rarity, exterior, img) VALUES ( ?, ?, ?, ?,'Factory New',?)";
                String sql2 = "INSERT INTO GameItems (skin_name,"
                        + "item_name, type ,rarity, exterior, img) VALUES ( ?, ?, ?, ?,'Minimal Wear',?)";
                String sql3 = "INSERT INTO GameItems (skin_name,"
                        + "item_name, type ,rarity, exterior,img) VALUES ( ?, ?, ?, ?,'Field-Tested',?)";
                String sql4 = "INSERT INTO GameItems (skin_name,"
                        + "item_name, type ,rarity, exterior,img) VALUES ( ?, ?, ?, ?,'Well-Worn',?)";
                String sql5 = "INSERT INTO GameItems (skin_name,"
                        + "item_name, type ,rarity, exterior, img) VALUES ( ?, ?, ?, ?,'Battle-Scared',?)";
                PreparedStatement preparedStatement1 = con.prepareStatement(sql1,
                        Statement.RETURN_GENERATED_KEYS);
                PreparedStatement preparedStatement2 = con.prepareStatement(sql2,
                        Statement.RETURN_GENERATED_KEYS);
                PreparedStatement preparedStatement3 = con.prepareStatement(sql3,
                        Statement.RETURN_GENERATED_KEYS);
                PreparedStatement preparedStatement4 = con.prepareStatement(sql4,
                        Statement.RETURN_GENERATED_KEYS);
                PreparedStatement preparedStatement5 = con.prepareStatement(sql5,
                        Statement.RETURN_GENERATED_KEYS);
                preparedStatement1.setString(1, gameItem.getSkinName());
                preparedStatement1.setString(2, gameItem.getItemName());
                preparedStatement1.setString(3, gameItem.getType());
                preparedStatement1.setString(4, gameItem.getRarity());
                preparedStatement1.setString(5, gameItem.getImg());
                preparedStatement2.setString(1, gameItem.getSkinName());
                preparedStatement2.setString(2, gameItem.getItemName());
                preparedStatement2.setString(3, gameItem.getType());
                preparedStatement2.setString(4, gameItem.getRarity());
                preparedStatement2.setString(5, gameItem.getImg());
                preparedStatement3.setString(1, gameItem.getSkinName());
                preparedStatement3.setString(2, gameItem.getItemName());
                preparedStatement3.setString(3, gameItem.getType());
                preparedStatement3.setString(4, gameItem.getRarity());
                preparedStatement3.setString(5, gameItem.getImg());
                preparedStatement4.setString(1, gameItem.getSkinName());
                preparedStatement4.setString(2, gameItem.getItemName());
                preparedStatement4.setString(3, gameItem.getType());
                preparedStatement4.setString(4, gameItem.getRarity());
                preparedStatement4.setString(5, gameItem.getImg());
                preparedStatement5.setString(1, gameItem.getSkinName());
                preparedStatement5.setString(2, gameItem.getItemName());
                preparedStatement5.setString(3, gameItem.getType());
                preparedStatement5.setString(4, gameItem.getRarity());
                preparedStatement5.setString(5, gameItem.getImg());
                // if insert command failed
                
                preparedStatement1.executeUpdate();
                preparedStatement2.executeUpdate();
                preparedStatement3.executeUpdate();
                preparedStatement4.executeUpdate();
                preparedStatement5.executeUpdate();
                con.close();
            }
        } catch (Exception e) {
            System.out.println("Error in insertGameItem");
            System.out.println(e);
        }
        return String.valueOf(key);
    }

    public static void editGameItem(GameItems gameItem) {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();

            // Prepare the SQL statement
            String sql = "UPDATE GameItems "
                    + "SET skin_name = ?, item_name = ?, type = ?, rarity = ?, img= ? "
                    + "WHERE id = " + gameItem.getId();
            PreparedStatement st = con.prepareStatement(sql);
            st = con.prepareStatement(sql);
            st.setString(1, gameItem.getSkinName());
            st.setString(2, gameItem.getItemName());
            st.setString(3, gameItem.getType());
            st.setString(4, gameItem.getRarity());
            st.setString(5, gameItem.getImg());
            // Execute the SQL statement
            st.executeUpdate();
            if (st.executeUpdate() != 1) {
                System.out.println("ERROR INSERTING User");
            }
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean deleteGameItemById(int id) {
        boolean deleteStatus = true;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            String sql = "DELETE FROM GameItems WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st = con.prepareStatement(sql);
            
            st.setInt(1, id);
            
            con.close();
            st.executeUpdate();
            if (st.executeUpdate() != 1) {
                System.out.println("ERROR INSERTING User");
            }
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return deleteStatus;
    }

    public static void main(String[] args) {
        String trimedName = "CZ75-Auto_Victoria";
        GameItems item = getItemBySkinName(trimedName, "Factory New");
        System.out.println(item.getId());
    }
}
