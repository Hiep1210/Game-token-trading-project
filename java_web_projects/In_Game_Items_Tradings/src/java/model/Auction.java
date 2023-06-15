/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
    private double lowestBid;
    private String gameAccountName;
    private LocalDateTime startingDate;
    private LocalDateTime endingDate;
    private User seller;
    private GameItems gameItem;

    public Auction() {
    }

    public Auction(int itemId, int sellerId, double lowestBid, String gameAccountName, LocalDateTime startingDate, LocalDateTime endingDate) {
        this.itemId = itemId;
        this.sellerId = sellerId;
        this.gameAccountName = gameAccountName;
        this.lowestBid = lowestBid;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

    public Auction(int auctionId, int itemId, int sellerId, double lowestBid, String gameAccountName, LocalDateTime startingDate, LocalDateTime endingDate) {
        this.auctionId = auctionId;
        this.itemId = itemId;
        this.sellerId = sellerId;
        this.gameAccountName = gameAccountName;
        this.lowestBid = lowestBid;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
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

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public GameItems getGameItem() {
        return gameItem;
    }

    public void setGameItem(GameItems gameItem) {
        this.gameItem = gameItem;
    }

    @Override
    public String toString() {
        return "Auction{" + "auctionId=" + auctionId + ", sellerId=" + sellerId + ", itemId=" + itemId + ", lowestBid=" + lowestBid + ", gameAccountName=" + gameAccountName + ", startingDate=" + startingDate + ", endingDate=" + endingDate + ", seller=" + seller + ", gameItem=" + gameItem + '}';
    }

}
