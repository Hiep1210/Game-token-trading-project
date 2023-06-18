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
import java.util.HashMap;
import model.Auction;
import model.Bid;
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
                    gameItem.setSkinName(rs.getString("skin_name"));
                    gameItem.setItemName(rs.getString("item_name"));
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
                    gameItem.setSkinName(rs.getString("skin_name"));
                    gameItem.setItemName(rs.getString("item_name"));
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

    public static HashMap<Bid, Auction> getAllSuccessfullAuction() {
        HashMap<Bid, Auction> auctionList = new HashMap<Bid, Auction>();
        Auction auction = null;
        GameItems gameItem = null;
        Bid bid = null;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            //if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql = "SELECT a.id, a.seller_id ,a.game_account_name, b.bidder_id, b.game_account_name ,b.amount, a.item_id\n"
                        + "FROM bid b\n"
                        + "JOIN auction a ON b.auction_id = a.id\n"
                        + "WHERE b.amount = (\n"
                        + "  SELECT MAX(amount)\n"
                        + "  FROM bid\n"
                        + "  WHERE auction_id = b.auction_id\n"
                        + ") AND NOW() > a.ending_date";
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                //run a loop to save queries into model
                while (rs.next()) {
                    auction = new Auction();
                    gameItem = new GameItems();
                    bid = new Bid();
                    //Get auction information
                    auction.setAuctionId(rs.getInt(1));
                    auction.setSellerId(rs.getInt(2));
                    auction.setGameAccountName(rs.getString(3));
                    //Get bid information
                    bid.setBidderId(rs.getInt(4));
                    bid.setGameAccountName(rs.getString(5));
                    bid.setAmount(rs.getDouble(6));
                    //Get game item information
                    gameItem.setId(rs.getInt(7));
                    auction.setGameItem(gameItem);
                    auctionList.put(bid, auction);
                }
                call.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return auctionList;
    }

    public static void deleteUnsuccessfulAuction() {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            String sql = " DELETE FROM Auction auc\n"
                    + "WHERE auc.id IN\n"
                    + "(SELECT id FROM (\n"
                    + "	select auc.id \n"
                    + "	from auction auc\n"
                    + "	left outer join bid bid\n"
                    + "	on auc.id = bid.auction_id\n"
                    + "	where bid.id is null) as auction) ";
            Statement call = con.createStatement();
            //Loop through all payment request id and delete it from the table
            call.executeUpdate(sql);
            call.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error in deleteUnsuccessfulAuction ");
        }
    }

    public static void main(String[] args) {
        deleteUnsuccessfulAuction();

        HashMap<Bid, Auction> list = getAllSuccessfullAuction();
        for (Bid bid : list.keySet()) {
            System.out.println(bid);
            System.out.println(list.get(bid));
        }
    }
}
