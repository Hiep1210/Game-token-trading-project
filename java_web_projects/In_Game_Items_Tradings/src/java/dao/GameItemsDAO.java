/*
*Programmer: Nguyễn Hoàng Hiệp 
*Description: This file describes the actions of the model GameItems
 */
package dao;

import Context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public static ArrayList<GameItems> Search(String[] name) {
        ArrayList<GameItems> list = new ArrayList<>();
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM game_items_trading.gameitems where true ";
                for (int i = 0; i < name.length; i++) {
                    sql += " and (item_name Like '%" + name[i] + "%' or skin_name like '%" + name[i] + "%') ";
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
                String sql1 = "INSERT INTO GameItems (skin_name, "
                        + "item_name, type ,rarity, exterior, img) VALUES ( ?, ?, ?, ?,Factory New,?)";
                String sql2 = "INSERT INTO GameItems (skin_name, "
                        + "item_name, type ,rarity, exterior, img) VALUES ( ?, ?, ?, ?,Minimal Wear,?)";
                String sql3 = "INSERT INTO GameItems (skin_name, "
                        + "item_name, type ,rarity, exterior, img) VALUES ( ?, ?, ?, ?,Field-Tested,?)";
                String sql4 = "INSERT INTO GameItems (skin_name, "
                        + "item_name, type ,rarity, exterior, img) VALUES ( ?, ?, ?, ?,Well-Worn,?)";
                String sql5 = "INSERT INTO GameItems (skin_name, "
                        + "item_name, type ,rarity, exterior, img) VALUES ( ?, ?, ?, ?,Battle-Scared,?)";
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
                preparedStatement1.setString(5, gameItem.getExterior());
                preparedStatement1.setString(6, gameItem.getImg());
                preparedStatement2.setString(1, gameItem.getSkinName());
                preparedStatement2.setString(2, gameItem.getItemName());
                preparedStatement2.setString(3, gameItem.getType());
                preparedStatement2.setString(4, gameItem.getRarity());
                preparedStatement2.setString(5, gameItem.getExterior());
                preparedStatement2.setString(6, gameItem.getImg());
                preparedStatement3.setString(1, gameItem.getSkinName());
                preparedStatement3.setString(2, gameItem.getItemName());
                preparedStatement3.setString(3, gameItem.getType());
                preparedStatement3.setString(4, gameItem.getRarity());
                preparedStatement3.setString(5, gameItem.getExterior());
                preparedStatement3.setString(6, gameItem.getImg());
                preparedStatement4.setString(1, gameItem.getSkinName());
                preparedStatement4.setString(2, gameItem.getItemName());
                preparedStatement4.setString(3, gameItem.getType());
                preparedStatement4.setString(4, gameItem.getRarity());
                preparedStatement4.setString(5, gameItem.getExterior());
                preparedStatement4.setString(6, gameItem.getImg());
                preparedStatement5.setString(1, gameItem.getSkinName());
                preparedStatement5.setString(2, gameItem.getItemName());
                preparedStatement5.setString(3, gameItem.getType());
                preparedStatement5.setString(4, gameItem.getRarity());
                preparedStatement5.setString(5, gameItem.getExterior());
                preparedStatement5.setString(6, gameItem.getImg());
                // if insert command failed
                if (preparedStatement1.executeUpdate() != 1) {
                    System.out.println("ERROR INSERTING PAYMENTREQUEST, "
                            + "NO ROWS AFFECTED");
                }
                generatedKey = preparedStatement1.getGeneratedKeys();
                if (generatedKey.next()) {
                    key = generatedKey.getInt(1);
                } else {
                    throw new SQLException("ERROR INSERTING PAYMENTREQUEST,"
                            + " NO ID OBTAINED.");
                }
                con.close();
            }
        } catch (Exception e) {
            System.out.println("Error in insertPaymentRequest");
            System.out.println(e);
        }
        return String.valueOf(key);
    }

    public static void main(String[] args) {
        String trimedName = "CZ75-Auto_Victoria";
        GameItems item = getItemBySkinName(trimedName, "Factory New");
        System.out.println(item.getId());
    }
}
