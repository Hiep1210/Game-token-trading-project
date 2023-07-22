/*
*Programmer: Trần Thế Hùng 
*Description: This file is the Model of auction entity
 */
package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class Auction {

    private int auctionId;
    private int sellerId;
    private int itemId;
    private int bidIncrement;
    private double lowestBid;
    private String gameAccountName;
    private LocalDateTime startingDate;
    private LocalDateTime endingDate;
    private GameItems gameItem;
    private ArrayList bidList;

    public Auction() {
    }

    public Auction(int sellerId, int itemId, int bidIncrement, double lowestBid, String gameAccountName,
            LocalDateTime startingDate, LocalDateTime endingDate, GameItems gameItem) {
        this.sellerId = sellerId;
        this.itemId = itemId;
        this.bidIncrement = bidIncrement;
        this.lowestBid = lowestBid;
        this.gameAccountName = gameAccountName;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.gameItem = gameItem;
        this.bidList = bidList;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getBidIncrement() {
        return bidIncrement;
    }

    public void setBidIncrement(int bidIncrement) {
        this.bidIncrement = bidIncrement;
    }

    public double getLowestBid() {
        return lowestBid;
    }

    public void setLowestBid(double lowestBid) {
        this.lowestBid = lowestBid;
    }

    public String getGameAccountName() {
        return gameAccountName;
    }

    public void setGameAccountName(String gameAccountName) {
        this.gameAccountName = gameAccountName;
    }

    public LocalDateTime getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDateTime startingDate) {
        this.startingDate = startingDate;
    }

    public LocalDateTime getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(LocalDateTime endingDate) {
        this.endingDate = endingDate;
    }

    public GameItems getGameItem() {
        return gameItem;
    }

    public void setGameItem(GameItems gameItem) {
        this.gameItem = gameItem;
    }

    public ArrayList getBidList() {
        return bidList;
    }

    public void setBidList(ArrayList bidList) {
        this.bidList = bidList;
    }

    
}
