/*
*Programmer: Trần Thế Hùng 
*Description: This file is the DAO for doing CRUD operations on bid table
 */
package dao;

import Context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import model.Bid;

/**
 *
 * @author Asus
 */
public class BidDAO {

    //Function to get all bids in an auction
    public static ArrayList<Bid> getBidsFromAuctionId(int auctionId) {
        ArrayList<Bid> bidsList = new ArrayList<>();
        Bid bid = null;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            //if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql = "SELECT * FROM game_items_trading.bid "
                        + " WHERE auction_id = " + auctionId
                        + " ORDER BY amount DESC";
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                //run a loop to save queries into model
                while (rs.next()) {
                    bid = new Bid();
                    bid.setBidId(rs.getInt("id"));
                    bid.setBidderId(rs.getInt("bidder_id"));
                    bid.setAuctionId(rs.getInt("auction_id"));
                    bid.setBidTime(rs.getObject("bid_time", LocalDateTime.class));
                    bid.setAmount(rs.getDouble("amount"));
                    bidsList.add(bid);
                }
                rs.close();
                call.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println("Error in get bids ");
            System.out.println(e.getMessage());
        }
        return bidsList;
    }

    public static double getHighestBidFromAuctionId(int auctionId) {
        double bid = -1;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            //if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql = " SELECT * FROM game_items_trading.bid "
                        + " WHERE auction_id = " + auctionId
                        + " ORDER BY amount DESC ";
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                //run a loop to save queries into model
                while (rs.next()) {
                    bid = rs.getDouble("amount");
                    break;
                }
                rs.close();
                call.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println("Error in get highest bids ");
            System.out.println(e.getMessage());
        }
        return bid;
    }

    public static boolean insertBid(Bid bid) {
        //Return true if insert process was successfull
        boolean insertStatus = true;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            //if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql = "INSERT INTO game_items_trading.bid ( bidder_id, auction_id, bid_time, amount, game_account_name) "
                        + " VALUES( ?, ?, ?, ?, ?);";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setInt(1, bid.getBidderId());
                preparedStatement.setInt(2, bid.getAuctionId());
                preparedStatement.setObject(3, bid.getBidTime());
                preparedStatement.setDouble(4, bid.getAmount());
                preparedStatement.setString(5, bid.getGameAccountName());
                // if insert command failed
                if (preparedStatement.executeUpdate() != 1) {
                    insertStatus = false;
                    System.out.println("ERROR INSERTING BIDS, NO ROWS AFFECTED");
                }
                preparedStatement.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return insertStatus;
    }

    public static boolean changeBidAmount(int bidId, double amount, LocalDateTime bidTime) {
        //Return true if change bid amount process was successfull
        boolean changeBidAmountStatus = true;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = " UPDATE game_items_trading.bid "
                        + " SET amount = amount + ?, bid_time = ? "
                        + " WHERE id = ?";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setDouble(1, amount);
                preparedStatement.setObject(2, bidTime);
                preparedStatement.setInt(3, bidId);
                //if no row updated, throw exception
                if (preparedStatement.executeUpdate() != 1) {
                    changeBidAmountStatus = false;
                    throw new Exception("ERROR CHANGING BID AMOUNT, NO ROWS AFFECTED");
                }
                preparedStatement.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println("Error in change bid amount");
            System.out.println(e.getMessage());
        }
        return changeBidAmountStatus;
    }

    public static boolean deleteBids(ArrayList<Integer> auctionIds) {
        //Return true if delete process was successfull
        boolean deleteStatus = true;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            con.setAutoCommit(false);
            String sql = "DELETE FROM Bid "
                    + " WHERE auction_id = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            //Loop through all auction id and delete it from the bid table
            for (int auctionId : auctionIds) {
                preparedStatement.setInt(1, auctionId);
                if (preparedStatement.executeUpdate() == 0) {
                    con.rollback();
                    deleteStatus = false;
                    throw new Exception("ERROR DELETING BIDS, NO CHANGES WAS COMMITED");
                }
            }
            con.commit();
            preparedStatement.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error in delete bids");
        }
        return deleteStatus;
    }

    public static void main(String[] args) {
        //insertBid(new Bid(1, LocalDateTime.of(2023, 06, 10, 0, 0, 0) , 100.0 , 3));
//        changeBidAmount(3, 100.0, LocalDateTime.of(2024, 06, 10, 0, 0, 0));
//        ArrayList<Integer> auctionList = new ArrayList<>();
//        auctionList.add(2);
//        deleteBids(auctionList);
        ArrayList<Bid> list = getBidsFromAuctionId(2);
        for (Bid bid : list) {
            System.out.println(bid);
        }
    }
}
