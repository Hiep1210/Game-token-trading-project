/*
*Programmer: Nguyễn Hoàng Hiệp 
*Description: This file describes the actions of the model MarketItems
 */
package dao;

import Context.DBContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.MarketItems;

/**
 *
 * @author Inspiron
 */
public class MarketItemsDao {

    //Function to get all items in the market 
    public static ArrayList<MarketItems> getAllMarketItems() {
        ArrayList<MarketItems> list = new ArrayList<>();
        MarketItems items = null;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            //if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql = "select m.user_id, m.price, g.* FROM marketitems m, gameitems g where m.item_id = g.id;\n";
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                //run a loop to save queries into model
                while (rs.next()) {
                    items = new MarketItems(0, rs.getInt(1), rs.getDouble(2), rs.getString(4), rs.getString(5), rs.getString(6),
                            rs.getString(7), rs.getString(8), rs.getString(9));
                    list.add(items);
                }
                call.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static ArrayList<MarketItems> Filter(String priceorder, String type, String rarity, String exterior) {
        ArrayList<MarketItems> list = new ArrayList<>();
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "select m.id, m.user_id, m.price, g.* FROM game_items_trading.marketitems m, gameitems g where "
                        + "m.item_id = g.id ";
                if (type != null) {
                    sql += " and type = '" + type + "'";
                }
                if (rarity != null) {
                    sql += " and rarity = '" + rarity + "'";
                }
                if (exterior != null) {
                    sql += " and exterior = '" + exterior + "'";
                }
                if (priceorder != null) {
                    sql += " order by price " + priceorder;
                }
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                //assign value for object items then return it
                while (rs.next()) {
                    list.add(new MarketItems(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getString(5), rs.getString(6), rs.getString(7),
                            rs.getString(8), rs.getString(9), rs.getString(10)));
                }
                call.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static ArrayList<MarketItems> Search(String[] name) {
        ArrayList<MarketItems> list = new ArrayList<>();
        MarketItems items = null;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            //if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql = "select m.id, m.user_id, m.price, g.* FROM game_items_trading.marketitems m, gameitems g where "
                        + "m.item_id = g.id ";
                for (int i = 0; i < name.length; i++) {
                    sql += " and (item_name Like '%" + name[i] + "%' or skin_name like '%" + name[i] + "%') ";
                }
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                //run a loop to save queries into model
                while (rs.next()) {
                    items = new MarketItems(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getString(5), rs.getString(6), rs.getString(7),
                            rs.getString(8), rs.getString(9), rs.getString(10));
                    list.add(items);
                }
                call.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static void main(String[] args) {
        getAllMarketItems();
        Filter("asc", "Pistol", "Covert", null);
        String name[] = {"Eagle", "Code"};
        Search(name);
    }
}
