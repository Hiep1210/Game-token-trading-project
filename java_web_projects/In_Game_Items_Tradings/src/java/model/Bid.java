/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
    private int auctionId;

    public Bid() {
    }
    
    public Bid(int bidderId,LocalDateTime bidTime, Double amount, int auctionId) {
        this.bidderId = bidderId;
        this.bidTime = bidTime;
        this.amount = amount;
        this.auctionId = auctionId;
    }
    
    public Bid(int bidId, int bidderId,LocalDateTime bidTime, Double amount, int auctionId) {
        this.bidId = bidId;
        this.bidderId = bidderId;
        this.bidTime = bidTime;
        this.amount = amount;
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

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    @Override
    public String toString() {
        return "Bid{" + "bidId=" + bidId + ", bidderId=" + bidderId + ", bidTime=" + bidTime + ", amount=" + amount + ", auctionId=" + auctionId + '}';
    }
    
    
    
    
}
