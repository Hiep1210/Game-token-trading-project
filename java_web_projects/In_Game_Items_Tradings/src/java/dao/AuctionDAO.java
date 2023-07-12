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
        Auction auction;
        GameItems gameItem;
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

    public static ArrayList<Auction> getAllAuctionFromUser(int userId) {
        ArrayList<Auction> auctionList = new ArrayList<>();
        Auction auction;
        GameItems gameItem;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            //if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql = "SELECT * FROM auction auc, gameitems gei "
                        + " WHERE NOW() < auc.ending_date AND auc.item_id = gei.id AND auc.seller_id = " + userId
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

    public static boolean deleteAuction(int auctionId) {
        //Return true if delete process was successfull
        boolean deleteStatus = true;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            String sql = "DELETE FROM Auction WHERE id = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, auctionId);
            if (preparedStatement.executeUpdate() != 1) {
                deleteStatus = false;
            }
            preparedStatement.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error in delete auction");
        }
        return deleteStatus;
    }

    //Function to get all auction that has ended
    public static ArrayList<Auction> getAllEndedSuccessfullAuction() {
        ArrayList<Auction> auctionList = new ArrayList<>();
        Auction auction = null;
        GameItems gameItem = null;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            //if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql = "SELECT * "
                        + "FROM auction auc, gameitems gei , bid bid "
                        + "WHERE NOW() > auc.ending_date AND auc.item_id = gei.id AND auc.id = bid.auction_id "
                        + "AND NOT EXISTS ( "
                        + "	SELECT 1 FROM processitems p   "
                        + "	WHERE  p.transaction_id = auc.id ) "
                        + "ORDER BY auc.id, bid.amount DESC";
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                //run a loop to save queries into model
                while (rs.next()) {
                    auction = new Auction();
                    gameItem = new GameItems();
                    //Get auction information
                    //Querry result has 2 column named id , so only take auction id from 1st collumn
                    auction.setAuctionId(rs.getInt(1));
                    auction.setItemId(rs.getInt("item_id"));
                    auction.setSellerId(rs.getInt("seller_id"));
                    auction.setLowestBid(rs.getDouble("lowest_bid"));
                    auction.setStartingDate(rs.getObject("starting_date", LocalDateTime.class));
                    auction.setEndingDate(rs.getObject("ending_date", LocalDateTime.class));
                    auction.setGameAccountName(rs.getString("game_account_name"));
                    //Get game item information
                    gameItem.setId(rs.getInt("item_id"));
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

    public static ArrayList<Auction> filter(String priceorder, String[] type, String[] rarity, String[] exterior) {
        ArrayList<Auction> auctionList = new ArrayList<>();
        Auction auction;
        GameItems gameItem;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM auction auc, gameitems gei "
                        + " WHERE NOW() < auc.ending_date AND auc.item_id = gei.id ";
                //Sql for odering by price needs to have a different base sql
                if (priceorder != null) {
                    sql = "WITH bid as( "
                            + "	SELECT auction_id, max(amount) amount "
                            + "    FROM bid "
                            + "	GROUP BY auction_id "
                            + "    ) "
                            + " SELECT *  "
                            + " FROM auction auc  "
                            + " LEFT OUTER JOIN bid b  ON b.auction_id = auc.id "
                            + " LEFT OUTER JOIN gameitems gei on auc.item_id = gei.id "
                            + " WHERE NOW() < auc.ending_date ";
                }
                if (type != null) {
                    sql += " and (";
                    for (int i = 0; i < type.length - 1; i++) {
                        sql += " type = '" + type[i] + "' or ";
                    }
                    sql += " type = '" + type[type.length - 1] + "') ";
                }
                if (rarity != null) {
                    sql += " and (";
                    for (int i = 0; i < rarity.length - 1; i++) {
                        sql += " rarity = '" + rarity[i] + "' or ";
                    }
                    sql += " rarity = '" + rarity[rarity.length - 1] + "') ";
                }
                if (exterior != null) {
                    sql += " and (";
                    for (int i = 0; i < exterior.length - 1; i++) {
                        sql += " exterior = '" + exterior[i] + "' or ";
                    }
                    sql += " exterior = '" + exterior[exterior.length - 1] + "') ";
                }
                if (priceorder != null) {
                    sql += " ORDER BY b.amount " + priceorder + ", auc.lowest_bid " + priceorder;
                }
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                //assign value for object items then return it
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

    public static ArrayList<Auction> search(String[] name) {
        ArrayList<Auction> auctionList = new ArrayList<>();
        Auction auction;
        GameItems gameItem;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            //if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql = "SELECT * FROM auction auc, gameitems gei "
                        + " WHERE NOW() < auc.ending_date AND auc.item_id = gei.id ";
                for (int i = 0; i < name.length; i++) {
                    sql += " and (item_name Like '%" + name[i] + "%' or skin_name like '%" + name[i] + "%') ";
                }
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

//    public static ArrayList<Auction> getAllEndedUnsuccessfullAuction() {
//        ArrayList<Bid> bidList = null;
//        ArrayList<Auction> auctionList = null;
//        Auction auction = null;
//        GameItems gameItem = null;
//        Bid bid = null;
//        try {
//            DBContext db = new DBContext();
//            Connection con = db.getConnection();
//            //if connection is secured, proceed to execute query and retrieve data into and return a list
//            if (con != null) {
//                String sql = "SELECT a.id, a.seller_id ,a.game_account_name, b.bidder_id, b.game_account_name ,b.amount, a.item_id"
//                        + "FROM bid b"
//                        + "JOIN auction a ON b.auction_id = a.id"
//                        + "WHERE b.amount = ("
//                        + "  SELECT MAX(amount)"
//                        + "  FROM bid"
//                        + "  WHERE auction_id = b.auction_id"
//                        + ") AND NOW() > a.ending_date";
//                Statement call = con.createStatement();
//                ResultSet rs = call.executeQuery(sql);
//                //run a loop to save queries into model
//                while (rs.next()) {
//                    bidList = new ArrayList<Bid>();
//                    auction = new Auction();
//                    gameItem = new GameItems();
//                    bid = new Bid();
//                    //Get auction information
//                    auction.setAuctionId(rs.getInt(1));
//                    auction.setSellerId(rs.getInt(2));
//                    auction.setGameAccountName(rs.getString(3));
//                    //Get bid information
//                    bid.setBidderId(rs.getInt(4));
//                    bid.setGameAccountName(rs.getString(5));
//                    bid.setAmount(rs.getDouble(6));
//                    bidList.add(bid);
//                    auction.setBidList(bidList);
//                    //Get game item information
//                    gameItem.setId(rs.getInt(7));
//                    auction.setGameItem(gameItem);
//                }
//                call.close();
//                con.close();
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return auctionList;
//    }
    public static void deleteAllEndedUnsuccessfullAuction() {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            String sql = " DELETE FROM Auction auc"
                    + "WHERE auc.id IN"
                    + "(SELECT id FROM ("
                    + "	select auc.id "
                    + "	from auction auc"
                    + "	left outer join bid bid"
                    + "	on auc.id = bid.auction_id"
                    + "	where bid.id is null) as auction) "
                    + " AND NOW() > auc.ending_date ";
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
//
//        ArrayList<Auction> auctionList = getAllEndedAuction();
//        for (Auction auction : auctionList) {
//            System.out.println(auction);
//        }
    }
}
