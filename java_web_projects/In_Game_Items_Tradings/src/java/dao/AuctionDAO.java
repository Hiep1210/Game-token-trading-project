/*
*Programmer: Trần Thế Hùng 
*Description: This file is the DAO for doing CRUD operations on auction table
 */
package dao;

import Context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import model.Auction;
import model.GameItems;

/**
 *
 * @author Asus
 */
public class AuctionDAO {

    //Function to get all items in the market 
    public static ArrayList<Auction> getAllAuctions() {
        ArrayList<Auction> auctionList = new ArrayList<>();
        Auction auction = null;
        GameItems gameItem = null;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            //if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql = "SELECT * FROM auction auc, gameitems gei "
                        + " WHERE NOW() < auc.ending_date AND auc.item_id = gei.id "
                        + " ORDER BY auc.ending_date DESC";
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                //run a loop to save queries into model
                while (rs.next()) {
                    auction = new Auction();
                    gameItem = new GameItems();
                    //Get auction information
                    auction.setAuctionId(rs.getInt("id"));
                    auction.setSellerId(rs.getInt("seller_id"));
                    auction.setLowestBid(rs.getDouble("lowest_bid"));
                    auction.setStartingDate(rs.getObject("starting_date", LocalDateTime.class));
                    auction.setEndingDate(rs.getObject("ending_date", LocalDateTime.class));
                    auction.setGameAccountName(rs.getString("game_account_name"));
                    //Get game item information
                    gameItem.setId(rs.getInt("id"));
                    gameItem.setSkinname(rs.getString("skin_name"));
                    gameItem.setItemname(rs.getString("item_name"));
                    gameItem.setType(rs.getString("type"));
                    gameItem.setRarity(rs.getString("rarity"));
                    gameItem.setExterior(rs.getString("exterior"));
                    gameItem.setImg(rs.getString("img"));
                    //Add game item object to auction object
                    auction.setGameItem(gameItem);
                    auctionList.add(auction);
                } 
                call.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return auctionList;
    }

    public static Auction getAuction(int auctionId) {
        Auction auction = null;
        GameItems gameItem = null;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            //if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql = "SELECT * FROM auction auc, gameitems gei "
                        + " WHERE auc.id = " + auctionId + " AND auc.item_id = gei.id";
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                //run a loop to save queries into model
                while (rs.next()) {
                    auction = new Auction();
                    gameItem = new GameItems();
                    //Get auction information
                    auction.setAuctionId(rs.getInt("id"));
                    auction.setSellerId(rs.getInt("seller_id"));
                    auction.setLowestBid(rs.getDouble("lowest_bid"));
                    auction.setStartingDate(rs.getObject("starting_date", LocalDateTime.class));
                    auction.setEndingDate(rs.getObject("ending_date", LocalDateTime.class));
                    auction.setGameAccountName(rs.getString("game_account_name"));
                    //Get game item information
                    gameItem.setId(rs.getInt("id"));
                    gameItem.setSkinname(rs.getString("skin_name"));
                    gameItem.setItemname(rs.getString("item_name"));
                    gameItem.setType(rs.getString("type"));
                    gameItem.setRarity(rs.getString("rarity"));
                    gameItem.setExterior(rs.getString("exterior"));
                    gameItem.setImg(rs.getString("img"));
                    //Add game item object to auction object
                    auction.setGameItem(gameItem);
  
                }
                call.close();
                rs.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return auction;
    }

    public static boolean insertAuction(Auction auction) {
        //Return true if insert process was successfull
        boolean insertStatus = true;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            //if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql = "INSERT INTO game_items_trading.auction ( seller_id, "
                        + " item_id, lowest_bid, starting_date,"
                        + " ending_date, game_account_name) VALUES "
                        + " ( ?, ?, ?, ?, ?, ?);";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setInt(1, auction.getSellerId());
                preparedStatement.setInt(2, auction.getItemId());
                preparedStatement.setDouble(3, auction.getLowestBid());
                preparedStatement.setObject(4, auction.getStartingDate());
                preparedStatement.setObject(5, auction.getEndingDate());
                preparedStatement.setString(6, auction.getGameAccountName());
                // if insert command failed
                if (preparedStatement.executeUpdate() != 1) {
                    insertStatus = false;
                    System.out.println("ERROR INSERTING PAYMENTREQUEST, NO ROWS AFFECTED");
                }
                preparedStatement.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println("Error in insertPaymentRequest");
            System.out.println(e);
        }
        return insertStatus;
    }

    public static boolean deleteAuctions(ArrayList<Integer> auctionIds) {
        //Return true if delete process was successfull
        boolean deleteStatus = true;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            con.setAutoCommit(false);
            String sql = "DELETE FROM Auction WHERE id = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            //Loop through all payment request id and delete it from the table
            for (int auctionId : auctionIds) {
                preparedStatement.setInt(1, auctionId);
                if (preparedStatement.executeUpdate() != 1) {
                    con.rollback();
                    deleteStatus = false;
                    throw new Exception("ERROR DELETING AUCTION, NO CHANGES WAS COMMITED");
                }
            }
            con.commit();
            preparedStatement.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error in delete auction");
        }
        return deleteStatus;
    }

    public static void main(String[] args) {
//        insertAuction(new Auction(1, 1, 1, 10, "Dave", LocalDateTime.of(2023, 06, 01, 0, 0, 0), LocalDateTime.of(2023, 06, 20, 05, 10, 50)));
//        ArrayList<Auction> list = getAuctions();
//        for (Auction auction : list) {
//            System.out.println(auction);
//            System.out.println(auction.getEndingDate().getHour());
//            System.out.println(auction.getEndingDate().getMinute());
//            System.out.println(auction.getEndingDate().getSecond());
//        }
//        ArrayList<Integer> list = new ArrayList<>();
//        list.add(1);
//        if (deleteAuctions(list)) {
//            System.out.println("delete successful");
//        }
//        System.out.println(getAuction(1));

    }
}
