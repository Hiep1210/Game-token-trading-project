/*
*Programmer: Trần Thế Hùng 
*Description: This file is the Model of bid entity
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author Asus
 */
public class Bid {

    private int bidId;
    private int bidderId;
    private LocalDateTime bidTime;
    private Double amount;
    private String gameAccountName;
    private int auctionId;

    public Bid() {
    }

    public Bid(int bidderId, LocalDateTime bidTime, Double amount, String gameAccountName, int auctionId) {
        this.bidderId = bidderId;
        this.bidTime = bidTime;
        this.amount = amount;
        this.gameAccountName = gameAccountName;
        this.auctionId = auctionId;
    }

    public int getBidId() {
        return bidId;
    }

    public void setBidId(int bidId) {
        this.bidId = bidId;
    }

    public int getBidderId() {
        return bidderId;
    }

    public void setBidderId(int bidderId) {
        this.bidderId = bidderId;
    }

    public LocalDateTime getBidTime() {
        return bidTime;
    }

    public void setBidTime(LocalDateTime bidTime) {
        this.bidTime = bidTime;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getGameAccountName() {
        return gameAccountName;
    }

    public void setGameAccountName(String gameAccountName) {
        this.gameAccountName = gameAccountName;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    @Override
    public String toString() {
        return "Bid{" + "bidId=" + bidId + ", bidderId=" + bidderId + ", bidTime=" + bidTime + ", amount=" + amount + ", gameAccountName=" + gameAccountName + ", auctionId=" + auctionId + '}';
    }

}
