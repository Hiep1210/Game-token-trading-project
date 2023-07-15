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
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cart;
import model.GameItems;
import model.OfferItem;
import model.ReceiveItem;
import model.TradeItem;
import model.User;

/**
 *
 * @author Inspiron
 */
public class TradeDAO {

    static Logger logger
            = Logger.getLogger(CartDAO.class.getName());
    private static final String SELECTTRADE = "SELECT * FROM tradeitems t, useraccount u where t.creator = u.id;";
    private static final String SELECTOFFERITEMS = "SELECT * FROM game_items_trading.offeritem o, gameitems g where o.give_id = g.id and o.trade_id = ?";
    private static final String SELECTRECITEMS = "SELECT * FROM receiveitem r, gameitems g where r.rec_id = g.id and r.trade_id = ?";
    public static ArrayList<TradeItem> getAllTradeOffers(){
        ArrayList<TradeItem> list = new ArrayList<>();
        TradeItem items = null;
        Connection con = null;
        PreparedStatement statement = null;
        try {
            DBContext db = new DBContext();
            con = db.getConnection();
            //if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql = SELECTTRADE;
                statement = con.prepareStatement(sql);
                ResultSet rs = statement.executeQuery();
                //run a loop to save queries into model
                while (rs.next()) {
                    items = new TradeItem(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getObject(4, LocalDateTime.class), 
                            rs.getObject(5, LocalDateTime.class), new User(rs.getInt(6), "", "", "", "", "", "", 0, 0));
                    list.add(items);
                }
            }
            con.close();
            statement.close();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return list;
    }
    public static ArrayList<OfferItem> getAllOffersInATrade(int tradeId){
        ArrayList<OfferItem> list = new ArrayList<>();
        OfferItem items = null;
        Connection con = null;
        PreparedStatement statement = null;
        try {
            DBContext db = new DBContext();
            con = db.getConnection();
            //if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql = SELECTOFFERITEMS;
                statement = con.prepareStatement(sql);
                statement.setInt(1, tradeId);
                ResultSet rs = statement.executeQuery();
                //run a loop to save queries into model
                while (rs.next()) {
                    items = new OfferItem(rs.getInt(1), rs.getInt(2), rs.getInt(3), new GameItems(rs.getInt(4), rs.getString(5), rs.getString(6),
                            rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)));
                    list.add(items);
                }
            }
            con.close();
            statement.close();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return list;
    }
    public static ArrayList<ReceiveItem> getAllRecsInATrade(int tradeId){
        ArrayList<ReceiveItem> list = new ArrayList<>();
        ReceiveItem items = null;
        Connection con = null;
        PreparedStatement statement = null;
        try {
            DBContext db = new DBContext();
            con = db.getConnection();
            //if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql = SELECTOFFERITEMS;
                statement = con.prepareStatement(sql);
                statement.setInt(1, tradeId);
                ResultSet rs = statement.executeQuery();
                //run a loop to save queries into model
                while (rs.next()) {
                    items = new ReceiveItem(rs.getInt(1), rs.getInt(2), rs.getInt(3), new GameItems(rs.getInt(4), rs.getString(5), rs.getString(6),
                            rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)));
                    list.add(items);
                }
            }
            con.close();
            statement.close();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return list;
    }
    public static boolean insertTrade(String gAcc, int creatorId, LocalDateTime begin, LocalDateTime end, int[] offers, int[] rec) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            DBContext db = new DBContext();
            con = db.getConnection();
            String sql = "INSERT INTO tradeitems (`game_account_name`, `creator`, `begin_date`, `end_date`) "
                    + "VALUES (?, ?,?,?); ";
            statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, gAcc);
            statement.setInt(2, creatorId);
            statement.setObject(3, begin);
            statement.setObject(4, end);
            if (statement.executeUpdate() < 1) {
                throw new NullPointerException();
            }
            int tradeItemId = 0;
            ResultSet generatedKeys = statement.getGeneratedKeys();
            //get generated id incremental
            if (generatedKeys.next()) {
                tradeItemId = generatedKeys.getInt(1);
            }
            for (int i = 0; i < offers.length; i++) {
                //if insert failed for an item return false
                if (!insertOfferItems(offers[i], tradeItemId)) {
                    return  false;
                }
            }
            for (int i = 0; i < rec.length; i++) {
                //if insert failed for an item return false
                if (!insertReceiveItems(rec[i], tradeItemId)) {
                    return  false;
                }
            }
            return true;
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
        return false;
    }

    public static boolean insertOfferItems(int offers, int tradeId) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            DBContext db = new DBContext();
            con = db.getConnection();
            String sql = "INSERT INTO offeritem (give_id, trade_id) VALUES (?, ?); ";
            statement = con.prepareStatement(sql);
            statement.setInt(1, offers);
            statement.setInt(2, tradeId);
            if (statement.executeUpdate() < 1) {
                throw new NullPointerException();
            }
            return true;
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
        return false;
    }

    public static boolean insertReceiveItems(int rec, int tradeId) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            DBContext db = new DBContext();
            con = db.getConnection();
            String sql = "INSERT INTO receiveitem (rec_id,trade_id) VALUES (?, ?); ";
            statement = con.prepareStatement(sql);
            statement.setInt(1, rec);
            statement.setInt(2, tradeId);
            if (statement.executeUpdate() < 1) {
                throw new NullPointerException();
            }
            return true;
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
        return false;
    }
    public static void main(String[] args) {
        getAllTradeOffers();
        getAllOffersInATrade(5);
        int[] offers = {1,2,3};
        int[] rec = {5,4,6};
        insertTrade("", 1, LocalDateTime.now(), LocalDateTime.now(), offers, rec);
    }
}
