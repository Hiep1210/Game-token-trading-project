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
    private static final String SELECTITEMS = "select m.id,m.game_account_name,m.user_id, m.price, m.begin_date, m.end_date, g.* "
            + "FROM marketitems m, gameitems g where m.item_id = g.id";
    //Function to get all items in the market 
    public static ArrayList<MarketItems> getAllMarketItems() {
        ArrayList<MarketItems> list = new ArrayList<>();
        MarketItems items = null;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            //if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql =SELECTITEMS;
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                //run a loop to save queries into model
                while (rs.next()) {
                    items = new MarketItems(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getString(5), rs.getString(6), rs.getInt(7),
                            rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13));
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
                String sql = SELECTITEMS;
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
                    list.add(new MarketItems(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getString(5), rs.getString(6), rs.getInt(7),
                            rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13)));
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
                String sql =SELECTITEMS;
                for (int i = 0; i < name.length; i++) {
                    sql += " and (item_name Like '%" + name[i] + "%' or skin_name like '%" + name[i] + "%') ";
                }
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                //run a loop to save queries into model
                while (rs.next()) {
                    items =new MarketItems(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getString(5), rs.getString(6), rs.getInt(7),
                            rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13));
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
